package com.bryllyant.kona.app.service;

import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MediaCodecService extends KService {
    String SERVICE_PATH = "rpc/MediaCodecService";


    // NOTE format is the file extension associated with the content-type of the file
    byte[] concatVideoFiles(List<byte[]> files, String format) throws IOException;

    byte[] mergeAudioVideoFiles(String format, byte[] audio, byte[] video) throws IOException;

    byte[] composeWEBMToMP4(byte[] video1, byte[] video2, Date start1, Date start2) throws IOException;

    List<byte[]> syncAndComposeWebmToMP4(byte[] video1, byte[] video2, Date start1, Date start2) throws IOException;

    byte[] convertMP4(String format, byte[] video, BigDecimal startPosition, BigDecimal duration) throws IOException;

    /**
     * Concat a set of audio stream data into a single file specified by the format.
     *
     * The streams are assumed to be all the same codec.
     *
     * Each byte[] is assumed to have a single audio stream at position 0
     *
     * For example, 4 input MP3 files might look like this:
     *
     * 		Stream #0:0: Audio: mp3, 22050 Hz, mono, s16p, 48 kb/s
     * 		Stream #1:0: Audio: mp3, 22050 Hz, mono, s16p, 48 kb/s
     * 		Stream #2:0: Audio: mp3, 22050 Hz, mono, s16p, 48 kb/s
     * 		Stream #3:0: Audio: mp3, 22050 Hz, mono, s16p, 48 kb/s
     *
     * @param format
     * @param streamList
     * @return media file containing the concatenated streams
     * @throws IOException
     */
    byte[] concatAudioStreams(String format, List<byte[]> streamList, Double pauseLength) throws IOException;

    byte[] addMediaBackground(String format, byte[] media, byte[] background) throws IOException;

    /**
     * Return the length in seconds of the media file.
     *
     * @param video byte array
     * @return time in seconds
     * @throws IOException
     */
    Double getMediaDuration(byte[] video) throws IOException;

    String getMediaFormat(byte[] data) throws IOException;


    /**
     * Add splashscreen to video.
     * @param format format of the video file
     * @param video video binary
     * @param image splashscreen binary
     * @param duration time in seconds of the splashscreen. Note: the splash screen will fade in and out for .5 seconds so duration should be at least 1000ms.
     * @return new video binary with embedded splashscreen
     * @throws IOException
     */
    byte[] addSplashScreen(String format, byte[] video, byte[] image, Double duration) throws IOException;

    byte[] addSilence(String format, byte[] video, Double duration) throws IOException;
    
}
