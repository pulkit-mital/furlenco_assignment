package com.video.videomanager.video;

import android.app.Activity;

import com.video.videomanager.app.VideoManagerApplication;

import java.io.File;


public class VideoDownloadAndPlayService
{
    private static FileStreamingServer server;

    private VideoDownloadAndPlayService(FileStreamingServer server)
    {
        this.server = server;
    }

    public static VideoDownloadAndPlayService startServer(final Activity activity, final String videoUrl, final String pathToSaveVideo, final String ipOfServer, final VideoStreamInterface callback)
    {
        if(!VideoManagerApplication.getPref().getBoolean("Downloaded")) {
            new VideoDownloader().execute(videoUrl, pathToSaveVideo);
        }
        server = new FileStreamingServer(new File(pathToSaveVideo));
        server.setSupportPlayWhileDownloading(true);
        server.setFileUrl(videoUrl);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                server.init(ipOfServer);

                activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        server.start();
                        String vidUrl = VideoManagerApplication.getPref().getBoolean("Downloaded") ? pathToSaveVideo: server.getFileUrl();
                        callback.onServerStart(vidUrl);
                    }
                });
            }
        }).start();

        return new VideoDownloadAndPlayService(server);
    }

    public void start(){
        server.start();
    }

    public void stop(){
        server.stop();
    }

    public static interface VideoStreamInterface{
        public void onServerStart(String videoStreamUrl);
    }
}
