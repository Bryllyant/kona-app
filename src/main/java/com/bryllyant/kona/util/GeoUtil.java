package com.bryllyant.kona.util;

import com.bryllyant.kona.app.model.geo.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

/*
 * NOTE: slightly modified methods from Google's Android library:
 * https://github.com/googlemaps/android-maps-utils/tree/master/library/src/com/google/maps/android
 */

public class GeoUtil {
	private static Logger logger = LoggerFactory.getLogger(GeoUtil.class);


	
    /**
     * The earth's radius, in meters.
     * Mean radius as defined by IUGG.
     */
    static final double EARTH_RADIUS = 6371009;



    /**
     * Returns tan(latitude-at-lng3) on the great circle (lat1, lng1) to (lat2, lng2). lng1==0.
     * See http://williams.best.vwh.net/avform.htm .
     */
    private static double tanLatGC(double lat1, double lat2, double lng2, double lng3) {
        return (tan(lat1) * sin(lng2 - lng3) + tan(lat2) * sin(lng3)) / sin(lng2);
    }

    /**
     * Returns mercator(latitude-at-lng3) on the Rhumb line (lat1, lng1) to (lat2, lng2). lng1==0.
     */
    private static double mercatorLatRhumb(double lat1, double lat2, double lng2, double lng3) {
        return (mercator(lat1) * (lng2 - lng3) + mercator(lat2) * lng3) / lng2;
    }

    /**
     * Computes whether the vertical segment (lat3, lng3) to South Pole intersects the segment
     * (lat1, lng1) to (lat2, lng2).
     * Longitudes are offset by -lng1; the implicit lng1 becomes 0.
     */
    private static boolean intersects(double lat1, double lat2, double lng2,
                                      double lat3, double lng3, boolean geodesic) {
        // Both ends on the same side of lng3.
        if ((lng3 >= 0 && lng3 >= lng2) || (lng3 < 0 && lng3 < lng2)) {
            return false;
        }
        // Point is South Pole.
        if (lat3 <= -PI/2) {
            return false;
        }
        // Any segment end is a pole.
        if (lat1 <= -PI/2 || lat2 <= -PI/2 || lat1 >= PI/2 || lat2 >= PI/2) {
            return false;
        }
        if (lng2 <= -PI) {
            return false;
        }
        double linearLat = (lat1 * (lng2 - lng3) + lat2 * lng3) / lng2;
        // Northern hemisphere and point under lat-lng line.
        if (lat1 >= 0 && lat2 >= 0 && lat3 < linearLat) {
            return false;
        }
        // Southern hemisphere and point above lat-lng line.
        if (lat1 <= 0 && lat2 <= 0 && lat3 >= linearLat) {
            return true;
        }
        // North Pole.
        if (lat3 >= PI/2) {
            return true;
        }
        // Compare lat3 with latitude on the GC/Rhumb segment corresponding to lng3.
        // Compare through a strictly-increasing function (tan() or mercator()) as convenient.
        return geodesic ?
            tan(lat3) >= tanLatGC(lat1, lat2, lng2, lng3) :
            mercator(lat3) >= mercatorLatRhumb(lat1, lat2, lng2, lng3);
    }

    public static boolean containsLocation(Point point, List<? extends Point> polygon, boolean geodesic) {
        return containsLocation(point.getLatitude(), point.getLongitude(), polygon, geodesic);
    }

    /**
     * Computes whether the given point lies inside the specified polygon.
     * The polygon is always considered closed, regardless of whether the last point equals
     * the first or not.
     * Inside is defined as not containing the South Pole -- the South Pole is always outside.
     * The polygon is formed of great circle segments if geodesic is true, and of rhumb
     * (loxodromic) segments otherwise.
     */
    public static boolean containsLocation(double latitude, double longitude, List<? extends Point> polygon, boolean geodesic) {
        final int size = polygon.size();
        if (size == 0) {
            return false;
        }
        double lat3 = toRadians(latitude);
        double lng3 = toRadians(longitude);
        Point prev = polygon.get(size - 1);
        double lat1 = toRadians(prev.getLatitude());
        double lng1 = toRadians(prev.getLongitude());
        int nIntersect = 0;
        for (Point point2 : polygon) {
            double dLng3 = wrap(lng3 - lng1, -PI, PI);
            // Special case: point equal to vertex is inside.
            if (lat3 == lat1 && dLng3 == 0) {
                return true;
            }
            double lat2 = toRadians(point2.getLatitude());
            double lng2 = toRadians(point2.getLongitude());
            // Offset longitudes by -lng1.
            if (intersects(lat1, lat2, wrap(lng2 - lng1, -PI, PI), lat3, dLng3, geodesic)) {
                ++nIntersect;
            }
            lat1 = lat2;
            lng1 = lng2;
        }
        return (nIntersect & 1) != 0;
    }

    private static final double DEFAULT_TOLERANCE = 0.1;  // meters.

    /**
     * Computes whether the given point lies on or near the edge of a polygon, within a specified
     * tolerance in meters. The polygon edge is composed of great circle segments if geodesic
     * is true, and of Rhumb segments otherwise. The polygon edge is implicitly closed -- the
     * closing segment between the first point and the last point is included.
     */
    public static boolean isLocationOnEdge(Point point, List<? extends Point> polygon, boolean geodesic,
                                           double tolerance) {
        return isLocationOnEdgeOrPath(point, polygon, true, geodesic, tolerance);
    }

    /**
     * Same as {@link #isLocationOnEdge(Point, List, boolean, double)}
     * with a default tolerance of 0.1 meters.
     */
    public static boolean isLocationOnEdge(Point point, List<? extends Point> polygon, boolean geodesic) {
        return isLocationOnEdge(point, polygon, geodesic, DEFAULT_TOLERANCE);
    }

    /**
     * Computes whether the given point lies on or near a polyline, within a specified
     * tolerance in meters. The polyline is composed of great circle segments if geodesic
     * is true, and of Rhumb segments otherwise. The polyline is not closed -- the closing
     * segment between the first point and the last point is not included.
     */
    public static boolean isLocationOnPath(Point point, List<? extends Point> polyline,
                                           boolean geodesic, double tolerance) {
        return isLocationOnEdgeOrPath(point, polyline, false, geodesic, tolerance);
    }

    /**
     * Same as {@link #isLocationOnPath(Point, List, boolean, double)}
     *
     * with a default tolerance of 0.1 meters.
     */
    public static boolean isLocationOnPath(Point point, List<? extends Point> polyline,
                                           boolean geodesic) {
        return isLocationOnPath(point, polyline, geodesic, DEFAULT_TOLERANCE);
    }

    private static boolean isLocationOnEdgeOrPath(Point point, List<? extends Point> poly, boolean closed,
                                                  boolean geodesic, double toleranceEarth) {
        int idx = locationIndexOnEdgeOrPath(point, poly, closed, geodesic, toleranceEarth);

        return (idx >= 0);
    }

    /**
     * Computes whether (and where) a given point lies on or near a polyline, within a specified tolerance.
     * The polyline is not closed -- the closing segment between the first point and the last point is not included.
     * @param point our needle
     * @param poly our haystack
     * @param geodesic the polyline is composed of great circle segments if geodesic
     *                 is true, and of Rhumb segments otherwise
     * @param tolerance tolerance (in meters)
     * @return -1 if point does not lie on or near the polyline.
     *          0 if point is between poly[0] and poly[1] (inclusive),
     *          1 if between poly[1] and poly[2],
     *          ...,
     *          poly.size()-2 if between poly[poly.size() - 2] and poly[poly.size() - 1]
     */
    public static int locationIndexOnPath(Point point, List<? extends Point> poly,
                                           boolean geodesic, double tolerance) {
        return locationIndexOnEdgeOrPath(point, poly, false, geodesic, tolerance);
    }

    /**
     * Same as {@link #locationIndexOnPath(Point, List, boolean, double)}
     *
     * with a default tolerance of 0.1 meters.
     */
    public static int locationIndexOnPath(Point point, List<? extends Point> polyline,
                                           boolean geodesic) {
        return locationIndexOnPath(point, polyline, geodesic, DEFAULT_TOLERANCE);
    }

    /**
     * Computes whether (and where) a given point lies on or near a polyline, within a specified tolerance.
     * If closed, the closing segment between the last and first points of the polyline is not considered.
     * @param point our needle
     * @param poly our haystack
     * @param closed whether the polyline should be considered closed by a segment connecting the last point back to the first one
     * @param geodesic the polyline is composed of great circle segments if geodesic
     *                 is true, and of Rhumb segments otherwise
     * @param toleranceEarth tolerance (in meters)
     * @return -1 if point does not lie on or near the polyline.
     *          0 if point is between poly[0] and poly[1] (inclusive),
     *          1 if between poly[1] and poly[2],
     *          ...,
     *          poly.size()-2 if between poly[poly.size() - 2] and poly[poly.size() - 1]
     */
    private static int locationIndexOnEdgeOrPath(Point point, List<? extends Point> poly, boolean closed,
                                          boolean geodesic, double toleranceEarth) {
        int size = poly.size();
        if (size == 0) {
            return -1;
        }
        double tolerance = toleranceEarth / EARTH_RADIUS;
        double havTolerance = hav(tolerance);
        double lat3 = toRadians(point.getLatitude());
        double lng3 = toRadians(point.getLongitude());
        Point prev = poly.get(closed ? size - 1 : 0);
        double lat1 = toRadians(prev.getLatitude());
        double lng1 = toRadians(prev.getLongitude());
        int idx = 0;
        if (geodesic) {
            for (Point point2 : poly) {
                double lat2 = toRadians(point2.getLatitude());
                double lng2 = toRadians(point2.getLongitude());
                if (isOnSegmentGC(lat1, lng1, lat2, lng2, lat3, lng3, havTolerance)) {
                    return Math.max(0, idx - 1);
                }
                lat1 = lat2;
                lng1 = lng2;
                idx++;
            }
        } else {
            // We project the points to mercator space, where the Rhumb segment is a straight line,
            // and compute the geodesic distance between point3 and the closest point on the
            // segment. This method is an approximation, because it uses "closest" in mercator
            // space which is not "closest" on the sphere -- but the error is small because
            // "tolerance" is small.
            double minAcceptable = lat3 - tolerance;
            double maxAcceptable = lat3 + tolerance;
            double y1 = mercator(lat1);
            double y3 = mercator(lat3);
            double[] xTry = new double[3];
            for (Point point2 : poly) {
                double lat2 = toRadians(point2.getLatitude());
                double y2 = mercator(lat2);
                double lng2 = toRadians(point2.getLongitude());
                if (max(lat1, lat2) >= minAcceptable && min(lat1, lat2) <= maxAcceptable) {
                    // We offset longitudes by -lng1; the implicit x1 is 0.
                    double x2 = wrap(lng2 - lng1, -PI, PI);
                    double x3Base = wrap(lng3 - lng1, -PI, PI);
                    xTry[0] = x3Base;
                    // Also explore wrapping of x3Base around the world in both directions.
                    xTry[1] = x3Base + 2 * PI;
                    xTry[2] = x3Base - 2 * PI;
                    for (double x3 : xTry) {
                        double dy = y2 - y1;
                        double len2 = x2 * x2 + dy * dy;
                        double t = len2 <= 0 ? 0 : clamp((x3 * x2 + (y3 - y1) * dy) / len2, 0, 1);
                        double xClosest = t * x2;
                        double yClosest = y1 + t * dy;
                        double latClosest = inverseMercator(yClosest);
                        double havDist = havDistance(lat3, latClosest, x3 - xClosest);
                        if (havDist < havTolerance) {
                            return Math.max(0, idx - 1);
                        }
                    }
                }
                lat1 = lat2;
                lng1 = lng2;
                y1 = y2;
                idx++;
            }
        }
        return -1;
    }

    /**
     * Returns sin(initial bearing from (lat1,lng1) to (lat3,lng3) minus initial bearing
     * from (lat1, lng1) to (lat2,lng2)).
     */
    private static double sinDeltaBearing(double lat1, double lng1, double lat2, double lng2,
                                          double lat3, double lng3) {
        double sinLat1 = sin(lat1);
        double cosLat2 = cos(lat2);
        double cosLat3 = cos(lat3);
        double lat31 = lat3 - lat1;
        double lng31 = lng3 - lng1;
        double lat21 = lat2 - lat1;
        double lng21 = lng2 - lng1;
        double a = sin(lng31) * cosLat3;
        double c = sin(lng21) * cosLat2;
        double b = sin(lat31) + 2 * sinLat1 * cosLat3 * hav(lng31);
        double d = sin(lat21) + 2 * sinLat1 * cosLat2 * hav(lng21);
        double denom = (a * a + b * b) * (c * c + d * d);
        return denom <= 0 ? 1 : (a * d - b * c) / sqrt(denom);
    }

    private static boolean isOnSegmentGC(double lat1, double lng1, double lat2, double lng2,
                                         double lat3, double lng3, double havTolerance) {
        double havDist13 = havDistance(lat1, lat3, lng1 - lng3);
        if (havDist13 <= havTolerance) {
            return true;
        }
        double havDist23 = havDistance(lat2, lat3, lng2 - lng3);
        if (havDist23 <= havTolerance) {
            return true;
        }
        double sinBearing = sinDeltaBearing(lat1, lng1, lat2, lng2, lat3, lng3);
        double sinDist13 = sinFromHav(havDist13);
        double havCrossTrack = havFromSin(sinDist13 * sinBearing);
        if (havCrossTrack > havTolerance) {
            return false;
        }
        double havDist12 = havDistance(lat1, lat2, lng1 - lng2);
        double term = havDist12 + havCrossTrack * (1 - 2 * havDist12);
        if (havDist13 > term || havDist23 > term) {
            return false;
        }
        if (havDist12 < 0.74) {
            return true;
        }
        double cosCrossTrack = 1 - 2 * havCrossTrack;
        double havAlongTrack13 = (havDist13 - havCrossTrack) / cosCrossTrack;
        double havAlongTrack23 = (havDist23 - havCrossTrack) / cosCrossTrack;
        double sinSumAlongTrack = sinSumFromHav(havAlongTrack13, havAlongTrack23);
        return sinSumAlongTrack > 0;  // Compare with half-circle == PI using sign of sin().
    }

    /**
     * Simplifies the given poly (polyline or polygon) using the Douglas-Peucker decimation
     * algorithm.  Increasing the tolerance will result in fewer points in the simplified polyline
     * or polygon.
     *
     * When the providing a polygon as input, the first and last point of the list MUST have the
     * same latitude and longitude (i.e., the polygon must be closed).  If the input polygon is not
     * closed, the resulting polygon may not be fully simplified.
     *
     * The time complexity of Douglas-Peucker is O(n^2), so take care that you do not call this
     * algorithm too frequently in your code.
     *
     * @param poly polyline or polygon to be simplified.  Polygon should be closed (i.e.,
     *              first and last points should have the same latitude and longitude).
     * @param tolerance in meters.  Increasing the tolerance will result in fewer points in the
     *                  simplified poly.
     * @return a simplified poly produced by the Douglas-Peucker algorithm
     */
    
    public static List<Point> simplify(List<Point> poly, double tolerance) {
        final int n = poly.size();
        if (n < 1) {
            throw new IllegalArgumentException("Polyline must have at least 1 point");
        }
        if (tolerance <= 0) {
            throw new IllegalArgumentException("Tolerance must be greater than zero");
        }

        boolean closedPolygon = isClosedPolygon(poly);
        Point lastPoint = null;

        // Check if the provided poly is a closed polygon
        if (closedPolygon) {
            // Add a small offset to the last point for Douglas-Peucker on polygons (see #201)
            final double OFFSET = 0.00000000001;
            lastPoint = poly.get(poly.size() - 1);
            // Point.latitude and .longitude are immutable, so replace the last point
            poly.remove(poly.size() - 1);
            poly.add(Point.from(lastPoint.getLatitude() + OFFSET, lastPoint.getLongitude() + OFFSET));
        }

        int idx;
        int maxIdx = 0;
        Stack<int[]> stack = new Stack<>();
        double[] dists = new double[n];
        dists[0] = 1;
        dists[n - 1] = 1;
        double maxDist;
        double dist = 0.0;
        int[] current;

        if (n > 2) {
            int[] stackVal = new int[]{0, (n - 1)};
            stack.push(stackVal);
            while (stack.size() > 0) {
                current = stack.pop();
                maxDist = 0;
                for (idx = current[0] + 1; idx < current[1]; ++idx) {
                    dist = distanceToLine(poly.get(idx), poly.get(current[0]),
                            poly.get(current[1]));
                    if (dist > maxDist) {
                        maxDist = dist;
                        maxIdx = idx;
                    }
                }
                if (maxDist > tolerance) {
                    dists[maxIdx] = maxDist;
                    int[] stackValCurMax = {current[0], maxIdx};
                    stack.push(stackValCurMax);
                    int[] stackValMaxCur = {maxIdx, current[1]};
                    stack.push(stackValMaxCur);
                }
            }
        }

        if (closedPolygon) {
            // Replace last point w/ offset with the original last point to re-close the polygon
            poly.remove(poly.size() - 1);
            poly.add(lastPoint);
        }

        // Generate the simplified line
        idx = 0;

        ArrayList<Point> simplifiedLine = new ArrayList<>();

        for (Point l : poly) {
            if (dists[idx] != 0) {
                simplifiedLine.add(l);
            }

            idx++;
        }

        return simplifiedLine;
    }

    /**
     * Returns true if the provided list of points is a closed polygon (i.e., the first and last
     * points are the same), and false if it is not
     * @param poly polyline or polygon
     * @return true if the provided list of points is a closed polygon (i.e., the first and last
     * points are the same), and false if it is not
     */
    public static boolean isClosedPolygon(List<? extends Point> poly) {
        Point firstPoint = poly.get(0);
        Point lastPoint = poly.get(poly.size()-1);
        return firstPoint.equals(lastPoint);
    }

    /**
     * Computes the distance on the sphere between the point p and the line segment start to end.
     *
     * @param p the point to be measured
     * @param start the beginning of the line segment
     * @param end the end of the line segment
     * @return the distance in meters (assuming spherical earth)
     */
    public static double distanceToLine(final Point p, final Point start, final Point end) {
        if (start.equals(end)) {
            return computeDistanceBetween(end, p);
        }

        final double s0lat = toRadians(p.getLatitude());
        final double s0lng = toRadians(p.getLongitude());
        final double s1lat = toRadians(start.getLatitude());
        final double s1lng = toRadians(start.getLongitude());
        final double s2lat = toRadians(end.getLatitude());
        final double s2lng = toRadians(end.getLongitude());

        double s2s1lat = s2lat - s1lat;
        double s2s1lng = s2lng - s1lng;
        final double u = ((s0lat - s1lat) * s2s1lat + (s0lng - s1lng) * s2s1lng)
                / (s2s1lat * s2s1lat + s2s1lng * s2s1lng);
        if (u <= 0) {
            return computeDistanceBetween(p, start);
        }
        if (u >= 1) {
            return computeDistanceBetween(p, end);
        }
        Point sa = Point.from(p.getLatitude() - start.getLatitude(), p.getLongitude() - start.getLongitude());
        Point sb = Point.from(u * (end.getLatitude() - start.getLatitude()), u * (end.getLongitude() - start.getLongitude()));
        return computeDistanceBetween(sa, sb);
    }

    /**
     * Decodes an encoded path string into a sequence of KGeoPoints.
     */
    public static List<? extends Point> decode(final String encodedPath) {
        int len = encodedPath.length();

        // For speed we preallocate to an upper bound on the final length, then
        // truncate the array before returning.
        final List<Point> path = new ArrayList<>();
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(Point.from(lat * 1e-5, lng * 1e-5));
        }

        return path;
    }

    /**
     * Encodes a sequence of KGeoPoints into an encoded path string.
     */
    public static String encode(final List<? extends Point> path) {
        long lastLat = 0;
        long lastLng = 0;

        final StringBuffer result = new StringBuffer();

        for (final Point point : path) {
            long lat = round(point.getLatitude() * 1e5);
            long lng = round(point.getLongitude() * 1e5);

            long dLat = lat - lastLat;
            long dLng = lng - lastLng;

            encode(dLat, result);
            encode(dLng, result);

            lastLat = lat;
            lastLng = lng;
        }
        return result.toString();
    }

    private static void encode(long v, StringBuffer result) {
        v = v < 0 ? ~(v << 1) : v << 1;
        while (v >= 0x20) {
            result.append(Character.toChars((int) ((0x20 | (v & 0x1f)) + 63)));
            v >>= 5;
        }
        result.append(Character.toChars((int) (v + 63)));
    }
    
    
    
    /**
     * Returns the heading from one Point to another Point. Headings are
     * expressed in degrees clockwise from North within the range [-180,180).
     * @return The heading in degrees clockwise from north.
     */
    public static double computeHeading(Point from, Point to) {
        // http://williams.best.vwh.net/avform.htm#Crs
        double fromLat = toRadians(from.getLatitude());
        double fromLng = toRadians(from.getLongitude());
        double toLat = toRadians(to.getLatitude());
        double toLng = toRadians(to.getLongitude());
        double dLng = toLng - fromLng;
        double heading = atan2(
                sin(dLng) * cos(toLat),
                cos(fromLat) * sin(toLat) - sin(fromLat) * cos(toLat) * cos(dLng));
        return wrap(toDegrees(heading), -180, 180);
    }

    /**
     * Returns the Point resulting from moving a distance from an origin
     * in the specified heading (expressed in degrees clockwise from north).
     * @param from     The Point from which to start.
     * @param distance The distance to travel.
     * @param heading  The heading in degrees clockwise from north.
     */
    public static Point computeOffset(Point from, double distance, double heading) {
        distance /= EARTH_RADIUS;
        heading = toRadians(heading);
        // http://williams.best.vwh.net/avform.htm#LL
        double fromLat = toRadians(from.getLatitude());
        double fromLng = toRadians(from.getLongitude());
        double cosDistance = cos(distance);
        double sinDistance = sin(distance);
        double sinFromLat = sin(fromLat);
        double cosFromLat = cos(fromLat);
        double sinLat = cosDistance * sinFromLat + sinDistance * cosFromLat * cos(heading);
        double dLng = atan2(
                sinDistance * cosFromLat * sin(heading),
                cosDistance - sinFromLat * sinLat);
        return Point.from(toDegrees(asin(sinLat)), toDegrees(fromLng + dLng));
    }

    /**
     * Returns the location of origin when provided with a Point destination,
     * meters travelled and original heading. Headings are expressed in degrees
     * clockwise from North. This function returns null when no solution is
     * available.
     * @param to       The destination Point.
     * @param distance The distance travelled, in meters.
     * @param heading  The heading in degrees clockwise from north.
     */
    public static Point computeOffsetOrigin(Point to, double distance, double heading) {
        heading = toRadians(heading);
        distance /= EARTH_RADIUS;
        // http://lists.maptools.org/pipermail/proj/2008-October/003939.html
        double n1 = cos(distance);
        double n2 = sin(distance) * cos(heading);
        double n3 = sin(distance) * sin(heading);
        double n4 = sin(toRadians(to.getLatitude()));
        // There are two solutions for b. b = n2 * n4 +/- sqrt(), one solution results
        // in the latitude outside the [-90, 90] range. We first try one solution and
        // back off to the other if we are outside that range.
        double n12 = n1 * n1;
        double discriminant = n2 * n2 * n12 + n12 * n12 - n12 * n4 * n4;
        if (discriminant < 0) {
            // No real solution which would make sense in Point-space.
            return null;
        }
        double b = n2 * n4 + sqrt(discriminant);
        b /= n1 * n1 + n2 * n2;
        double a = (n4 - n2 * b) / n1;
        double fromLatRadians = atan2(a, b);
        if (fromLatRadians < -PI / 2 || fromLatRadians > PI / 2) {
            b = n2 * n4 - sqrt(discriminant);
            b /= n1 * n1 + n2 * n2;
            fromLatRadians = atan2(a, b);
        }
        if (fromLatRadians < -PI / 2 || fromLatRadians > PI / 2) {
            // No solution which would make sense in Point-space.
            return null;
        }
        double fromLngRadians = toRadians(to.getLongitude()) -
                atan2(n3, n1 * cos(fromLatRadians) - n2 * sin(fromLatRadians));
        return Point.from(toDegrees(fromLatRadians), toDegrees(fromLngRadians));
    }

    /**
     * Returns the Point which lies the given fraction of the way between the
     * origin Point and the destination Point.
     * @param from     The Point from which to start.
     * @param to       The Point toward which to travel.
     * @param fraction A fraction of the distance to travel.
     * @return The interpolated Point.
     */
    public static Point interpolate(Point from, Point to, double fraction) {
        // http://en.wikipedia.org/wiki/Slerp
        double fromLat = toRadians(from.getLatitude());
        double fromLng = toRadians(from.getLongitude());
        double toLat = toRadians(to.getLatitude());
        double toLng = toRadians(to.getLongitude());
        double cosFromLat = cos(fromLat);
        double cosToLat = cos(toLat);

        // Computes Spherical interpolation coefficients.
        double angle = computeAngleBetween(from, to);
        double sinAngle = sin(angle);
        if (sinAngle < 1E-6) {
            return from;
        }
        double a = sin((1 - fraction) * angle) / sinAngle;
        double b = sin(fraction * angle) / sinAngle;

        // Converts from polar to vector and interpolate.
        double x = a * cosFromLat * cos(fromLng) + b * cosToLat * cos(toLng);
        double y = a * cosFromLat * sin(fromLng) + b * cosToLat * sin(toLng);
        double z = a * sin(fromLat) + b * sin(toLat);

        // Converts interpolated vector back to polar.
        double lat = atan2(z, sqrt(x * x + y * y));
        double lng = atan2(y, x);
        return Point.from(toDegrees(lat), toDegrees(lng));
    }

    /**
     * Returns distance on the unit sphere; the arguments are in radians.
     */
    private static double distanceRadians(double lat1, double lng1, double lat2, double lng2) {
        return arcHav(havDistance(lat1, lat2, lng1 - lng2));
    }
    
    /**
     * Returns the angle between two KGeoPoints, in radians. This is the same as the distance
     * on the unit sphere.
     */
    static double computeAngleBetween(Point from, Point to) {
        return distanceRadians(toRadians(from.getLatitude()), toRadians(from.getLongitude()),
                               toRadians(to.getLatitude()), toRadians(to.getLongitude()));
    }

    /**
     * Returns the distance between two KGeoPoints, in meters.
     */
    public static double computeDistanceBetween(Point from, Point to) {
        return computeAngleBetween(from, to) * EARTH_RADIUS;
    }

    /**
     * Returns the length of the given path, in meters, on Earth.
     */
    public static double computeLength(List<? extends Point> path) {
        if (path.size() < 2) {
            return 0;
        }
        double length = 0;
        Point prev = path.get(0);
        double prevLat = toRadians(prev.getLatitude());
        double prevLng = toRadians(prev.getLongitude());
        for (Point point : path) {
            double lat = toRadians(point.getLatitude());
            double lng = toRadians(point.getLongitude());
            length += distanceRadians(prevLat, prevLng, lat, lng);
            prevLat = lat;
            prevLng = lng;
        }
        return length * EARTH_RADIUS;
    }

    /**
     * Returns the area of a closed path on Earth.
     * @param path A closed path.
     * @return The path's area in square meters.
     */
    public static double computeArea(List<? extends Point> path) {
        return abs(computeSignedArea(path));
    }

    /**
     * Returns the signed area of a closed path on Earth. The sign of the area may be used to
     * determine the orientation of the path.
     * "inside" is the surface that does not contain the South Pole.
     * @param path A closed path.
     * @return The loop's area in square meters.
     */
    public static double computeSignedArea(List<? extends Point> path) {
        return computeSignedArea(path, EARTH_RADIUS);
    }

    /**
     * Returns the signed area of a closed path on a sphere of given radius.
     * The computed area uses the same units as the radius squared.
     * Used by SphericalUtilTest.
     */
    static double computeSignedArea(List<? extends Point> path, double radius) {
        int size = path.size();
        if (size < 3) { return 0; }
        double total = 0;
        Point prev = path.get(size - 1);
        double prevTanLat = tan((PI / 2 - toRadians(prev.getLatitude())) / 2);
        double prevLng = toRadians(prev.getLongitude());
        // For each edge, accumulate the signed area of the triangle formed by the North Pole
        // and that edge ("polar triangle").
        for (Point point : path) {
            double tanLat = tan((PI / 2 - toRadians(point.getLatitude())) / 2);
            double lng = toRadians(point.getLongitude());
            total += polarTriangleArea(tanLat, lng, prevTanLat, prevLng);
            prevTanLat = tanLat;
            prevLng = lng;
        }
        return total * (radius * radius);
    }

    /**
     * Returns the signed area of a triangle which has North Pole as a vertex.
     * Formula derived from "Area of a spherical triangle given two edges and the included angle"
     * as per "Spherical Trigonometry" by Todhunter, page 71, section 103, point 2.
     * See http://books.google.com/books?id=3uBHAAAAIAAJ&pg=PA71
     * The arguments named "tan" are tan((pi/2 - latitude)/2).
     */
    private static double polarTriangleArea(double tan1, double lng1, double tan2, double lng2) {
        double deltaLng = lng1 - lng2;
        double t = tan1 * tan2;
        return 2 * atan2(t * sin(deltaLng), 1 + t * cos(deltaLng));
    }
    
    



    /**
     * Restrict x to the range [low, high].
     */
    static double clamp(double x, double low, double high) {
        return x < low ? low : (x > high ? high : x);
    }

    /**
     * Wraps the given value into the inclusive-exclusive interval between min and max.
     * @param n   The value to wrap.
     * @param min The minimum.
     * @param max The maximum.
     */
    static double wrap(double n, double min, double max) {
        return (n >= min && n < max) ? n : (mod(n - min, max - min) + min);
    }

    /**
     * Returns the non-negative remainder of x / m.
     * @param x The operand.
     * @param m The modulus.
     */
    static double mod(double x, double m) {
        return ((x % m) + m) % m;
    }

    /**
     * Returns mercator Y corresponding to latitude.
     * See http://en.wikipedia.org/wiki/Mercator_projection .
     */
    static double mercator(double lat) {
        return log(tan(lat * 0.5 + PI/4));
    }

    /**
     * Returns latitude from mercator Y.
     */
    static double inverseMercator(double y) {
        return 2 * atan(exp(y)) - PI / 2;
    }

    /**
     * Returns haversine(angle-in-radians).
     * hav(x) == (1 - cos(x)) / 2 == sin(x / 2)^2.
     */
    static double hav(double x) {
        double sinHalf = sin(x * 0.5);
        return sinHalf * sinHalf;
    }

    /**
     * Computes inverse haversine. Has good numerical stability around 0.
     * arcHav(x) == acos(1 - 2 * x) == 2 * asin(sqrt(x)).
     * The argument must be in [0, 1], and the result is positive.
     */
    static double arcHav(double x) {
        return 2 * asin(sqrt(x));
    }

    // Given h==hav(x), returns sin(abs(x)).
    static double sinFromHav(double h) {
        return 2 * sqrt(h * (1 - h));
    }

    // Returns hav(asin(x)).
    static double havFromSin(double x) {
        double x2 = x * x;
        return x2 / (1 + sqrt(1 - x2)) * .5;
    }

    // Returns sin(arcHav(x) + arcHav(y)).
    static double sinSumFromHav(double x, double y) {
        double a = sqrt(x * (1 - x));
        double b = sqrt(y * (1 - y));
        return 2 * (a + b - 2 * (a * y + b * x));
    }

    /**
     * Returns hav() of distance from (lat1, lng1) to (lat2, lng2) on the unit sphere.
     */
    static double havDistance(double lat1, double lat2, double dLng) {
        return hav(lat1 - lat2) + hav(dLng) * cos(lat1) * cos(lat2);
    }
}
