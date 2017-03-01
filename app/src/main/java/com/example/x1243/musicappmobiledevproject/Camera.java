package com.example.x1243.musicappmobiledevproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Camera extends Activity {

    VideoView mVideoView;
    Button b1;
    private static final int REQUEST_VIDEO_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        b1 = (Button)findViewById(R.id.button1);

        mVideoView = (VideoView)findViewById(R.id.videoView1);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(i,REQUEST_VIDEO_CAPTURE);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK){
            Uri uri = data.getData();
            MediaController mc = new MediaController(this);
            if (mc != null){
                mc.refreshDrawableState();
            }

            mVideoView.setMediaController(mc);
            mVideoView.setVideoURI(uri);
            mVideoView.start();
        }

    }
    }
