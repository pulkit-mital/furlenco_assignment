package com.video.videomanager;

import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.video.videomanager.utils.Utils;
import com.video.videomanager.video.VideoDownloadAndPlayService;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        if (Utils.checkStoragePermission(this)) {
            startServer();
        }


    }

    /**
     * Intializing the view
     */
    private void initializeViews() {
        videoView = (VideoView) findViewById(R.id.videoView);
    }


    /**
     * playing video from local server url
     * @param videourl
     */
    private void settingVideoView(String videourl) {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            Uri video = Uri.parse(videourl);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    videoView.start();
                }
            });


        } catch (Exception e) {
            System.out.println("Video Play Error :" + e.toString());
        }

    }

    //starting the local server to play video for the first time from internet and
    // after that it will play from internal storage

    private void startServer() {

        String videoURL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        final String pathToVideo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/big_buck_bunny.mp4";

        VideoDownloadAndPlayService videoDownloadAndPlayService = VideoDownloadAndPlayService.startServer(this, videoURL, pathToVideo, "127.0.0.1", new VideoDownloadAndPlayService.VideoStreamInterface() {
            @Override
            public void onServerStart(String videoStreamUrl) {

                settingVideoView(videoStreamUrl);

            }
        });

    }

    //check whether you have permission to access the storage to read and write
    // in android version marshmallow or above
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utils.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startServer();
                }
                break;

        }
    }
}

