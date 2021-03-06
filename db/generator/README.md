After DAO's are generated make sure to update
the insert SQL to add a default `coords`


`PointFromText(concat('POINT(',ifnull(longitude, 0),' ',ifnull(latitude, 0),')')))`


For example:

```xml
  <insert id="insert" parameterType="com.bryllyant.kona.app.entity.ApiLog">
    <!--
      WARNING - @mbg.generated
      This element is automatically generated by MyBatis Generator, do not modify.
      This element was generated on Sun Nov 19 04:45:21 EST 2017.
    -->
    <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
      SELECT LAST_INSERT_ID()
    </selectKey>
    insert into `kona__api_log` (`uid`, `owner_id`, `app_id`, 
      `version_id`, `app_client_id`, `user_id`, 
      `access_token`, `end_point`, `class_name`, 
      `method_name`, `hostname`, `user_agent`, 
      `latitude`, `longitude`, `created_date`, 
      `updated_date`
      ,`coords`
      )
    values (#{uid,jdbcType=VARCHAR}, #{ownerId,jdbcType=BIGINT}, #{appId,jdbcType=BIGINT}, 
      #{versionId,jdbcType=BIGINT}, #{appClientId,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, 
      #{accessToken,jdbcType=VARCHAR}, #{endPoint,jdbcType=VARCHAR}, #{className,jdbcType=VARCHAR}, 
      #{methodName,jdbcType=VARCHAR}, #{hostname,jdbcType=VARCHAR}, #{userAgent,jdbcType=VARCHAR}, 
      #{latitude,jdbcType=DOUBLE}, #{longitude,jdbcType=DOUBLE}, #{createdDate,jdbcType=TIMESTAMP}, 
      #{updatedDate,jdbcType=TIMESTAMP}
      , ST_PointFromText(concat('POINT(',ifnull(longitude, 0),' ',ifnull(latitude, 0),')'))
      )
  </insert>
```




Necessary since as of MySQL 5.6 Geometry columns must be NOT NULL.


- AddressBookMapper.xml
- ApiLogMapper.xml
- AppUserMapper.xml
- CampaignEventMapper.xml
- CartMapper.xml
- MediaMapper.xml
- PartnerMapper.xml
- PaymentMapper.xml
- PlaceMapper.xml
- PositionMapper.xml
- RegistrationMapper.xml
- TokenMapper.xml



Current definition of selectProxmiate

```xml

    <select id="selectProximate" parameterType="map" resultMap="BaseResultMap">
        <!-- measured at 39 degrees lat (center of US) -->
        set @METERS_PER_LAT_DEGREE = 111015.45;
        set @METERS_PER_LON_DEGREE = 86626.37;

        <!-- radius in meters -->
        set @EARTH_RADIUS = 6372797.560856;

        set @lat = #{latitude,jdbcType=DOUBLE};
        set @lon = #{longitude,jdbcType=DOUBLE};
        set @dist = #{radius,jdbcType=DOUBLE};

        set @rlat1 = @lat - (@dist / @METERS_PER_LAT_DEGREE);
        set @rlon1 = @lon - (@dist/abs(cos(radians(@lat)) *
        @METERS_PER_LAT_DEGREE));

        set @rlat2 = @lat + (@dist / @METERS_PER_LAT_DEGREE);
        set @rlon2 = @lon + (@dist / abs(cos(radians(@lat)) *
        @METERS_PER_LAT_DEGREE));


        <!-- Great circle distance calculation This function returns distance in 
            KM CREATE FUNCTION slc (lat1 double, lon1 double, lat2 double, lon2 double) 
            RETURNS double RETURN 6371 * acos(cos(radians(lat1)) * cos(radians(lat2)) 
            * cos(radians(lon2) - radians(lon1)) + sin(radians(lat1)) * sin(radians(lat2))); -->


        select
        <include refid="Base_Column_List" />
        , (@EARTH_RADIUS *
        acos(cos(radians(@lat)) *
        cos(radians(y(coords))) *
        cos(radians(x(coords)) - radians(@lon)) +
        sin(radians(@lat)) *
        sin(radians(y(coords))))) as distance

        from kona__position

        <!-- filter out bogus records -->
        where latitude is not null
        and longitude is not null
        and coords is not null

        <if test="startDate != null">
            and position_date &gt; #{startDate,jdbcType=TIMESTAMP}
        </if>

        <if test="endDate != null">
            and position_date &lt; #{endDate,jdbcType=TIMESTAMP}
        </if>

        <!-- match within radius -->
        and st_within(coords,
        st_envelope(
        linestring(point(@rlon1, @rlat1), point(@rlon2, @rlat2))
        )
        )

        order by st_distance(point(@lon, @lat), coords)
    </select>
```


With Mysql 5.7.6+


```xml

    <select id="selectProximate" parameterType="map" resultMap="BaseResultMap">
        <!-- measured at 39 degrees lat (center of US) -->
        set @METERS_PER_LAT_DEGREE = 111015.45;
        set @METERS_PER_LON_DEGREE = 86626.37;

        <!-- radius in meters -->
        set @EARTH_RADIUS = 6372797.560856;

        set @lat = #{latitude,jdbcType=DOUBLE};
        set @lon = #{longitude,jdbcType=DOUBLE};
        set @dist = #{radius,jdbcType=DOUBLE};

        set @rlat1 = @lat - (@dist / @METERS_PER_LAT_DEGREE);
        set @rlon1 = @lon - (@dist/abs(cos(radians(@lat)) * @METERS_PER_LAT_DEGREE));

        set @rlat2 = @lat + (@dist / @METERS_PER_LAT_DEGREE);
        set @rlon2 = @lon + (@dist / abs(cos(radians(@lat)) * @METERS_PER_LAT_DEGREE));


        <include refid="Base_Column_List" />
        , ST_Distance_Sphere(coords, Point(@lon, @lat), geom) as distance

        from kona__position
        
          <!-- filter out bogus records -->
                where latitude is not null
                and longitude is not null
                and coords is not null
        
                <if test="startDate != null">
                    and position_date &gt; #{startDate,jdbcType=TIMESTAMP}
                </if>
        
                <if test="endDate != null">
                    and position_date &lt; #{endDate,jdbcType=TIMESTAMP}
                </if>
        
                <!-- limit query to MBR -->
                <!-- ST_Contains(g1, g2) - g1 contains g2 -->
                <!-- ST_Within(g1, g2) - g1 within g2 -->
                <!-- ST_MakeEnvelope(p1, p2) - form MBR from diagonal points
                
                and ST_Contains(ST_MakeEnvelope(Point(@lon1, @lat1), Point(@lon2, @lat2)), coords)
        
                order by distance
    </select>
```