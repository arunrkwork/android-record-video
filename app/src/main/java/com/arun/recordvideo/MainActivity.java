package com.arun.recordvideo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_VIDEO = 101;
    Button btnRecordVideo, btnPlayVideo;
    private Uri videoUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecordVideo = findViewById(R.id.btnRecordVideo);
        btnPlayVideo = findViewById(R.id.btnPlayVideo);

        btnRecordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordVideo();
            }
        });

        btnPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
            }
        });

    }

    private void playVideo() {
        Intent intent = new Intent(this, PlayVideoActivity.class);
        intent.putExtra("videouri", videoUri.toString());
        startActivity(intent);
    }

    private void recordVideo() {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if (videoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(videoIntent, REQ_VIDEO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_VIDEO && resultCode == RESULT_OK) {
            videoUri =  data.getData();
        }
    }
}
