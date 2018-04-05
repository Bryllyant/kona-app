package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.service.MediaCodecService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.io.KScript;
import com.bryllyant.kona.io.KScriptException;
import com.bryllyant.kona.util.KFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(MediaCodecService.SERVICE_PATH)
public class MediaCodecServiceImpl implements MediaCodecService {

	private static Logger logger = LoggerFactory.getLogger(MediaCodecServiceImpl.class);

	@Autowired
	private KConfig config;

	protected String ffmpeg() {
		String FFMPEG_BINDIR = config.getString("ffmpeg.bindir");
		if (!FFMPEG_BINDIR.endsWith("/")) {
			FFMPEG_BINDIR += "/";
		}
		return FFMPEG_BINDIR + "ffmpeg";
	}

	protected String ffprobe() {
		String FFMPEG_BINDIR = config.getString("ffmpeg.bindir");
		if (!FFMPEG_BINDIR.endsWith("/")) {
			FFMPEG_BINDIR += "/";
		}
		return FFMPEG_BINDIR + "ffprobe";
	}


    @Override
    // ffmpeg -y -i video.webm -i screen.webm -filter_complex "[0:v]setpts=PTS-STARTPTS, pad=iw:ih[bg]; [1:v]scale=320:240,setpts=PTS-STARTPTS[fg]; [bg][fg]overlay=main_w-overlay_w-10:main_h-overlay_h-10" fullRecording.webm
    // ffmpeg -y -i 6354797637490482-1.webm -i 6354797637490482-2.webm fullRecording.webm

    // ./ffmpeg -f concat -safe 0 -i files.txt -c copy output2.webm
    public byte[] concatVideoFiles(List<byte[]> files, String format) throws IOException {
        logger.debug("concatVideoFiles: format: " + format);

        String extension = "." + format;

        File outFile = KFileUtil.createTempFile(extension);

        int i=0;
        String s = "";
        for (byte[] b : files) {
            File f = KFileUtil.writeTempFile(b, extension);
            s += "file '" + f.getAbsolutePath() + "'\n";
            logger.debug("concatVideoFiles: file[" + i++ + "] size: " + b.length + "  path: " + f.getAbsolutePath());
        }

        File concatScript = KFileUtil.writeTempFile(s, ".txt");

        try {
            String[] args = {
                    "-y",
                    //"-stats",
                    //"-v", "debug", // error
                    "-f", "concat",
                    "-safe", "0",
                    "-i", concatScript.getAbsolutePath(),
                    "-c", "copy",
                    outFile.getAbsolutePath()
            };

            logger.debug("concatVideoFiles: KScript: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("concatVideoFiles: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("concatVideoFiles: result size:" + result.length);

        outFile.delete();
        concatScript.delete();

        return result;
    }



    //ffmpeg -i audio-file.wav -itsoffset -00:00:01 -i video-file.webm -map 0:0 -map 1:0 output-file-name.webm
    // https://github.com/muaz-khan/WebRTC-Experiment/tree/master/RecordRTC/RecordRTC-to-Nodejs
    @Override
    public byte[] mergeAudioVideoFiles(String format, byte[] audio, byte[] video) throws IOException {
        logger.debug("mergeAudioVideoFiles: audio size: " + audio.length + "  video size: " + video.length);

        String audioFormat = getMediaFormat(audio);
        String videoFormat = getMediaFormat(video);

        File outFile = KFileUtil.createTempFile("." + format);
        File audioFile = KFileUtil.writeTempFile(audio, "." + audioFormat);
        File videoFile = KFileUtil.writeTempFile(video, "." + videoFormat);

        try {
            String[] args = {
                    "-y",
                    "-i", audioFile.getAbsolutePath(),
                    "-i", videoFile.getAbsolutePath(),
                    "-map", "0:0",
                    "-map", "1:0",
                    outFile.getAbsolutePath()
            };

            logger.debug("mergeAudioVideoFiles: KScript: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("mergeAudioVideoFiles: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("mergeAudioVideoFiles: result size:" + result.length);

        outFile.delete();
        audioFile.delete();
        videoFile.delete();

        return result;
    }



    @Override
    public byte[] concatAudioStreams(String format, List<byte[]> streamList, Double pauseLength) throws IOException {
        logger.debug("concatStream: stream files: " + streamList.size());
        String extension = "." + format;

        File outFile = KFileUtil.createTempFile(extension);

        List<File> tmpFiles = new ArrayList<File>();
        tmpFiles.add(outFile);

        List<String> argList = new ArrayList<String>();

        // -y: overwrite output file if exists
        argList.add("-y");

        // dynamically create filter expression
        // https://ffmpeg.org/ffmpeg-filters.html#concat
        String filter = "";

        int i=0;
        for (byte[] stream : streamList) {
            File file = KFileUtil.writeTempFile(stream, ".tmp");
            tmpFiles.add(file);

            argList.add("-i");
            argList.add(file.getAbsolutePath());

            filter += "[" + i++ + ":0]";

            if (pauseLength != null) {
                // add pause between files
                // NOTE: order matters! -f then -t then -i
                argList.add("-f");
                argList.add("lavfi");
                argList.add("-t");
                argList.add(pauseLength.toString());
                argList.add("-i");
                argList.add("anullsrc");

                filter += "[" + i++ + ":0]";
            }
        }

        filter += "concat=n=" + i + ":v=0:a=1 [a0]";


        // Force two channel stereo
        argList.add("-ac");
        argList.add("2");

        // Make output 128k bitrate
        argList.add("-ab");
        argList.add("128k");

        argList.add("-filter_complex");
        argList.add(filter);

        argList.add("-map");
        argList.add("[a0]");

        argList.add(outFile.getAbsolutePath());

        try {
            String[] args = argList.toArray(new String[0]);

            logger.debug("concatStream: KScript: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("concatAudio: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("concatStream: result size:" + result.length);

        for (File file : tmpFiles) {
            file.delete();
        }

        return result;
    }


    /*
     * sudo /opt/ffmpeg/bin/ffmpeg -y \
    -loop 1 \
    -i /Users/sharif/Projects/seeloo/getjabby/animation/background.png \
    -i /opt/tomcat/temp/14710177567911451386709748082703.mp4 \
    -c:v libx264 \
    -r 30 \
    -b:a 192k \
    -movflags +faststart \
    -shortest \
    /opt/tomcat/temp/14710177567911451386709748082703.mp4
     */

    @Override
    public byte[] addSilence(String format, byte[] video, Double duration) throws IOException {

        BigDecimal _duration = new BigDecimal(duration);
        _duration = _duration.setScale(3, BigDecimal.ROUND_HALF_UP);

        logger.debug("addSilence: called ...");
        String extension = "." + format;

        File outFile = KFileUtil.createTempFile(extension);

        List<File> tmpFiles = new ArrayList<File>();
        tmpFiles.add(outFile);

        File videoFile = KFileUtil.writeTempFile(video, ".tmp");
        tmpFiles.add(videoFile);

        List<String> argList = new ArrayList<String>();


        // -y: overwrite output file if exists
        argList.add("-y");

        // add silence
        // NOTE: order matters! -f then -t then -i
        argList.add("-f");
        argList.add("lavfi");

        argList.add("-t");
        argList.add(_duration.toString());

        argList.add("-i");
        argList.add("anullsrc");
        // silence

        argList.add("-i");
        argList.add(videoFile.getAbsolutePath());


        String filter = "[0:0]concat=n=" + 2 + ":v=0:a=1";
        //String filter = "[0:0]concat=n=" + 2 + ":v=0:a=1 [a0]";

            /*
        // Force two channel stereo
        argList.add("-ac");
        argList.add("2");

        // Make output 128k bitrate
        argList.add("-ab");
        argList.add("128k");
        */

        argList.add("-filter_complex");
        argList.add(filter);

            /*
        argList.add("-map");
        argList.add("[a0]");
        */

        argList.add(outFile.getAbsolutePath());

        try {
            String[] args = argList.toArray(new String[0]);

            logger.debug("concatStream: KScript: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("concatAudio: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("concatStream: result size:" + result.length);

        for (File file : tmpFiles) {
            file.delete();
        }

        return result;
    }



    /*
     * add splashscreen
     * http://stackoverflow.com/questions/41469150/adding-splash-screen-using-ffmpeg
     *
     *
     * ffmpeg -i video.mp4 -loop 1 -t 2 -i image.png \
     * -filter_complex \
     * "[1]fade=t=in:st=0:d=0.500000,fade=t=out:st=1.500000:d=0.500000,setsar=1[i]; \
     * [i][0]concat=n=2:v=1:a=0" \
     * -c:v libx264 -crf 23 output.mp4
     */
    @Override
    public byte[] addSplashScreen(String format, byte[] video, byte[] image, Double duration) throws IOException {
        if (duration == null || duration < 1.0) {
            duration = 1.0;
        }

        logger.debug("addSplashScreen: called ...");
        String extension = "." + format;

        File outFile = KFileUtil.createTempFile(extension);

        List<File> tmpFiles = new ArrayList<File>();
        tmpFiles.add(outFile);

        File splashScreenFile = KFileUtil.writeTempFile(image, ".tmp");
        tmpFiles.add(splashScreenFile);

        File videoFile = KFileUtil.writeTempFile(video, ".tmp");
        tmpFiles.add(videoFile);

        List<String> argList = new ArrayList<String>();

        BigDecimal fadeDuration = new BigDecimal("0.5");

        BigDecimal _duration = new BigDecimal(duration);
        _duration = _duration.setScale(3, BigDecimal.ROUND_HALF_UP);

        BigDecimal fadeOutStartTime = _duration.subtract(fadeDuration);

        String filter = "[1]fade=t=in:st=0:d="+ fadeDuration.toString()
                + ",fade=t=out:st=" + fadeOutStartTime.toString()
                + ":d=" + fadeDuration.toString() + ",setsar=1[i];"
                + "[i][0]concat=n=2:v=1:a=0";

        // -y: overwrite output file if exists
        argList.add("-y");

        argList.add("-i");
        argList.add(videoFile.getAbsolutePath());

        argList.add("-loop");
        argList.add("1");

        argList.add("-t");
        argList.add(_duration.toString());  // total duration of splashscreen

        argList.add("-i");
        argList.add(splashScreenFile.getAbsolutePath());

        argList.add("-filter_complex");
        argList.add(filter);

        argList.add("-c:v");
        argList.add("libx264");

        argList.add("-crf");
        argList.add("23");


        argList.add(outFile.getAbsolutePath());

        try {
            String[] args = argList.toArray(new String[0]);

            logger.debug("concatStream: KScript: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("concatAudio: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("concatStream: result size:" + result.length);

        for (File file : tmpFiles) {
            file.delete();
        }

        result = addSilence(format, result, duration);

        return result;
    }


    @Override
    public byte[] addMediaBackground(String format, byte[] media, byte[] background) throws IOException {
        logger.debug("addBackground: called ...");
        String extension = "." + format;

        File outFile = KFileUtil.createTempFile(extension);

        List<File> tmpFiles = new ArrayList<File>();
        tmpFiles.add(outFile);

        File backgroundFile = KFileUtil.writeTempFile(background, ".tmp");
        tmpFiles.add(backgroundFile);

        File mediaFile = KFileUtil.writeTempFile(media, ".tmp");
        tmpFiles.add(mediaFile);

        List<String> argList = new ArrayList<String>();

        // -y: overwrite output file if exists
        argList.add("-y");

        argList.add("-loop");
        argList.add("1");


        argList.add("-i");
        argList.add(backgroundFile.getAbsolutePath());

        argList.add("-i");
        argList.add(mediaFile.getAbsolutePath());


        argList.add("-c:v");
        argList.add("libx264");

        argList.add("-r");
        argList.add("30");

        // set the pixel format
        argList.add("-pix_fmt");
        argList.add("yuv420p");

        // video bitrate
        argList.add("-b:v");
        argList.add("2048K");

        // audio bitrate
        argList.add("-b:a");
        argList.add("128k");

        // set maxWidth to 640px and keep aspect ratio
        argList.add("-vf");
        argList.add("scale=640:-1");

        argList.add("-movflags");
        argList.add("+faststart");

        argList.add("-shortest");

        argList.add(outFile.getAbsolutePath());

        try {
            String[] args = argList.toArray(new String[0]);

            logger.debug("concatStream: KScript: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("concatAudio: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("concatStream: result size:" + result.length);

        for (File file : tmpFiles) {
            file.delete();
        }

        return result;
    }




    // create a single video with both videos side by side
    @Override
    public byte[] composeWEBMToMP4(byte[] video1, byte[] video2, Date start1, Date start2) throws IOException {
        logger.debug("composeMP4: video1 size: " + video1.length + "  video2 size: " + video2.length);

        File outFile = KFileUtil.createTempFile(".mp4");
        File video1File = KFileUtil.writeTempFile(video1, ".webm");
        File video2File = KFileUtil.writeTempFile(video2, ".webm");

        BigDecimal seek1Position = new BigDecimal(0);
        BigDecimal seek2Position = new BigDecimal(0);

        if (start1 != null && start2 != null) {
            if (start1.getTime() > start2.getTime()) {
                double offset = (double) (start1.getTime() - start2.getTime())/1000L;
                seek2Position = new BigDecimal(offset);
                seek2Position = seek2Position.setScale(3, BigDecimal.ROUND_HALF_UP);
            } else if (start2.getTime() > start1.getTime()) {
                double offset = (double) (start2.getTime() - start1.getTime())/1000L;
                seek1Position = new BigDecimal(offset);
                seek1Position = seek1Position.setScale(3, BigDecimal.ROUND_HALF_UP);
            }
        }


        try {
            String filter = "[0:v] setpts=PTS-STARTPTS, pad=iw:(ih*2+20) [top];"
                    + "[1:v] setpts=PTS-STARTPTS [bottom];"
                    + "[top][bottom] overlay=shortest=1:(h+20);"
                    + "amerge";

            String[] args = {
                    "-y",
                    "-ss", seek1Position.toString(),
                    "-i", video1File.getAbsolutePath(),
                    "-ss", seek2Position.toString(),
                    "-i", video2File.getAbsolutePath(),
                    "-filter_complex", filter,
                    outFile.getAbsolutePath()
            };

            logger.debug("composeWEBMToMP4: KScript: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("composeWEBMToMP4: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("composeWEBMToMP4: result size:" + result.length);

        outFile.delete();
        video1File.delete();
        video2File.delete();

        return result;
    }



    // create a single video with both videos side by side
    // input videos are expected to be MP4 and already syncd
    private byte[] composeMP4(byte[] video1, byte[] video2) throws IOException {
        logger.debug("composeMP4: video1 size: " + video1.length + "  video2 size: " + video2.length);

        File outFile = KFileUtil.createTempFile(".mp4");
        File video1File = KFileUtil.writeTempFile(video1, ".mp4");
        File video2File = KFileUtil.writeTempFile(video2, ".mp4");

        try {
            String filter = "[0:v] setpts=PTS-STARTPTS, pad=iw:(ih*2+20) [top];"
                    + "[1:v] setpts=PTS-STARTPTS [bottom];"
                    + "[top][bottom] overlay=shortest=1:(h+20);"
                    + "amerge";

            String[] args = {
                    "-y",
                    "-i", video1File.getAbsolutePath(),
                    "-i", video2File.getAbsolutePath(),
                    "-filter_complex", filter,
                    outFile.getAbsolutePath()
            };

            logger.debug("composeMP4: KScript: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("composeMP4: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("composeMP4: result size:" + result.length);

        outFile.delete();
        video1File.delete();
        video2File.delete();

        return result;
    }



    @Override
    public List<byte[]> syncAndComposeWebmToMP4(byte[] video1, byte[] video2, Date start1, Date start2) throws IOException {
        logger.debug("syncWEBMToMP4: video1 size: " + video1.length + "  video2 size: " + video2.length);

        BigDecimal seek1Position = new BigDecimal(0);
        BigDecimal seek2Position = new BigDecimal(0);

        if (start1 != null && start2 != null) {
            if (start1.getTime() > start2.getTime()) {
                double offset = (double) (start1.getTime() - start2.getTime())/1000L;
                seek2Position = new BigDecimal(offset);
                seek2Position = seek2Position.setScale(3, BigDecimal.ROUND_HALF_UP);
            } else if (start2.getTime() > start1.getTime()) {
                double offset = (double) (start2.getTime() - start1.getTime())/1000L;
                seek1Position = new BigDecimal(offset);
                seek1Position = seek1Position.setScale(3, BigDecimal.ROUND_HALF_UP);
            }
        }

        // convert both files to MP4
        video1 = convertMP4("webm", video1, seek1Position, null);
        video2 = convertMP4("webm", video2, seek2Position, null);

        // get the length of each video
        Double length1 = getMediaDuration(video1);
        Double length2 = getMediaDuration(video2);

        // clip the longer MP4 video to the shorter length
        if (length1 > length2) {
            BigDecimal duration = new BigDecimal(length2);
            duration = duration.setScale(3, BigDecimal.ROUND_HALF_UP);
            video1 = convertMP4("mp4", video1, null, duration);
        } else if (length2 > length1) {
            BigDecimal duration = new BigDecimal(length1);
            duration = duration.setScale(3, BigDecimal.ROUND_HALF_UP);
            video2 = convertMP4("mp4", video2, null, duration);
        }

        byte[] composed = composeMP4(video1, video2);

        List<byte[]> result = new ArrayList<byte[]>();
        result.add(video1);
        result.add(video2);
        result.add(composed);

        return result;
    }



    @Override
    public Double getMediaDuration(byte[] video) throws IOException {
           /*
   		File videoFile = KFileUtil.writeTempFile(video, ".mp4");
   		Double length = null;


   		// to get length of webm file directly:
   		// 		/opt/ffmpeg/bin/ffmpeg -i video.webm -f null - 2>&1 | grep "time="
   		// 		frame=  777 fps=0.0 q=-0.0 Lsize=N/A time=00:00:32.66 bitrate=N/A speed= 243x

 		try {
 			// ffprobe -v error -show_entries format=duration   -of default=noprint_wrappers=1:nokey=1 video.mp4
  			String[] args = {
  					"-v", "error",
  					"-show_entries", "format=duration",
  					"-of", "default=noprint_wrappers=1:nokey=1",
  					videoFile.getAbsolutePath()
  			};

  			logger.debug("getMediaDuration: KScript: START");
  			KScript proc = new KScript(ffprobe(), args);
  			String out = proc.run();
  			if (out != null) {
  				length = Double.valueOf(out);
  			}
  			logger.debug("getMediaDuration: KScript: END");
  		} catch (KScriptException e) {
  			throw new IOException(e);
  		}

 		videoFile.delete();

 		return length;
         */

        Double length = null;

        // returns length of media file in seconds
        String value = probe(video, "duration");

        if (value != null) {
            length = Double.valueOf(value);
        }

        return length;
    }



    // field: duration | format_name
    protected String probe(byte[] data, String field) throws IOException {
        File dataFile = KFileUtil.writeTempFile(data, ".tmp");

        String result = null;

        try {
            // ffprobe -v error -show_entries format=duration   -of default=noprint_wrappers=1:nokey=1 video.mp4
            String[] args = {
                    "-v", "error",
                    "-show_entries", "format=" + field,
                    "-of", "default=noprint_wrappers=1:nokey=1",
                    dataFile.getAbsolutePath()
            };

            logger.debug("probe [" + field + "]: KScript: START");

            KScript proc = new KScript(ffprobe(), args);

            String out = proc.run();

            if (out != null) {
                result = out;
            }

            logger.debug("probe [" + field + "]: KScript: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        dataFile.delete();

        return result;
    }



    @Override
    public String getMediaFormat(byte[] data) throws IOException {
           /*
   		File dataFile = KFileUtil.writeTempFile(data, ".tmp");

   		String format = null;


   		try {
   			// ffprobe -v error -show_entries format=duration   -of default=noprint_wrappers=1:nokey=1
   			// ffprobe -v error -show_entries format=format_name -of default=noprint_wrappers=1:nokey=1
   			String[] args = {
   					"-v", "error",
   					"-show_entries", "format=format_name",
   					"-of", "default=noprint_wrappers=1:nokey=1",
   					dataFile.getAbsolutePath()
   			};

   			logger.debug("getMediaFormat: KScript: START");
   			KScript proc = new KScript(ffprobe(), args);
   			String out = proc.run();
   			if (out != null) {
   				String formats = out;
                 format = formats.split(",")[0].trim();

   			}
   			logger.debug("getMediaFormat: KScript: END");
   		} catch (KScriptException e) {
   			throw new IOException(e);
   		}

   		dataFile.delete();

   		return format;
           */

        String format = null;

        String value = probe(data, "format_name");

        if (value != null) {
            format = value.split(",")[0].trim();
        }

        return format;
    }



    @Override
    public byte[] convertMP4(String format, byte[] video, BigDecimal startPosition, BigDecimal duration) throws IOException {
        File outFile = KFileUtil.createTempFile(".mp4");
        File videoFile = KFileUtil.writeTempFile(video, "." + format);

        try {
            List<String> argList = new ArrayList<String>();
            argList.add("-y");

            if (startPosition != null) {
                argList.add("-ss");
                argList.add(startPosition.toString());
            }

            if (duration != null) {
                argList.add("-t");
                argList.add(duration.toString());
            }

            argList.add("-i");
            argList.add(videoFile.getAbsolutePath());
            argList.add(outFile.getAbsolutePath());
            String[] args = argList.toArray(new String[0]);


            logger.debug("convertMP4: Script: START");
            KScript proc = new KScript(ffmpeg(), args);
            proc.run();
            logger.debug("convertMP4: Script: END");
        } catch (KScriptException e) {
            throw new IOException(e);
        }

        byte[] result = KFileUtil.toByteArray(outFile);
        logger.debug("convertMP4: result size:" + result.length);

        outFile.delete();
        videoFile.delete();

        return result;
    }
	
}

