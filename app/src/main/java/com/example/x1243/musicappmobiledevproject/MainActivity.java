package com.example.x1243.musicappmobiledevproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    Button fade;
    Animation animation;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView)findViewById(R.id.imageView);
        fade = (Button)findViewById(R.id.button);

        fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                iv.startAnimation(animation);
            }
        });

        ImageButton musicBtn = (ImageButton) findViewById(R.id.imageButton);
        final Context context = this;
        musicBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, MusicPlayer.class);
                startActivity(myIntent);
            }

        });

        ImageButton cameraBtn = (ImageButton) findViewById(R.id.imageButton2);
        cameraBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, Camera.class);
                startActivity(myIntent);

            }
        });

        ImageButton playlistBtn = (ImageButton) findViewById(R.id.imageButton4);
        playlistBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, PlaylistClass.class);
                startActivity(myIntent);

            }
        });







    }
}
