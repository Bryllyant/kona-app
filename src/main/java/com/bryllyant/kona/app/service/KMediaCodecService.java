package com.bryllyant.kona.app.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bryllyant.kona.remote.service.KService;

public interface KMediaCodecService extends KService {
    
    // NOTE format is the file extension associated with the content-type of the file
    public byte[] concatVideoFiles(List<byte[]> files, String format) throws IOException;

	public byte[] mergeAudioVideoFiles(String format, byte[] audio, byte[] video) throws IOException;

	public byte[] composeWEBMToMP4(byte[] video1, byte[] video2, Date start1, Date start2) throws IOException;

	public List<byte[]> syncAndComposeWebmToMP4(byte[] video1, byte[] video2, Date start1, Date start2) throws IOException;

	public byte[] convertMP4(String format, byte[] video, BigDecimal startPosition, BigDecimal duration) throws IOException;

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
	 * @param audioList
	 * @return media file containing the concatenated streams
	 * @throws IOException
	 */
	public byte[] concatAudioStreams(String format, List<byte[]> streamList, Double pauseLength) throws IOException;

	public byte[] addMediaBackground(String format, byte[] media, byte[] background) throws IOException;

	/**
	 * Return the length in seconds of the media file.
	 * 
	 * @param video byte array
	 * @return time in seconds
	 * @throws IOException
	 */
	public Double getMediaDuration(byte[] video) throws IOException;

	public String getMediaFormat(byte[] data) throws IOException;

	
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
