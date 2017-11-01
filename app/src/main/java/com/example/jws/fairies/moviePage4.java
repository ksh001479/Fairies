package com.example.jws.fairies;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by 김요셉 on 2017-10-29.
 */

public class moviePage4 extends AppCompatActivity {
    final private static String RECORDED_FILE = "/sdcard/recorded.mp4";

    MediaPlayer player;
    MediaRecorder recorder;

    int playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drama_page);

        final VideoView videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setVideoPath("http://limong.iptime.org/~m20507/video/movie1.mp4");

        final MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setPadding(0, 0, 0, 0);

        videoView.start();
        videoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaController.show(0);
                videoView.pause();
            }
        }, 100);

        Button recordBtn = (Button) findViewById(R.id.recordBtn);
        Button recordStopBtn = (Button) findViewById(R.id.recordStopBtn);
        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button playStopBtn = (Button) findViewById(R.id.playStopBtn);

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkRecordingPermission();

            }
        });

        recordStopBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (recorder == null)
                    return;

                recorder.stop();
                recorder.release();
                recorder = null;

                Toast.makeText(getApplicationContext(),
                        "녹음이 중지되었습니다.", Toast.LENGTH_LONG).show();

            }
        });


        playBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    playAudio(RECORDED_FILE);

                    Toast.makeText(getApplicationContext(), "음악파일 재생 시작됨.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        playStopBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (player != null) {
                    playbackPosition = player.getCurrentPosition();
                    player.pause();
                    Toast.makeText(getApplicationContext(), "음악 파일 재생 중지됨.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        player = new MediaPlayer();
        player.setDataSource(url);
        player.prepare();
        player.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (player != null) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    protected void onPause() {
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
        if (player != null) {
            player.release();
            player = null;
        }

        super.onPause();

    }

    //---------------------------------
    //요샌 보안이 강화되어서 매니페스트에서 퍼미션 줬어도
    //다시한번 앱 내부에서 허락을 받아야됨. 써도 되는지 안되는지 허락받고 써야됨.
    //안그러면 안돌아감
    static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 5554;
    static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 5555;
    private void checkRecordingPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            }
        }else{
            recordingJob();
        }
    }

    //---------------------------------
    //매니페스트에서 스토리지 리드 퍼미션을 주었어도, 요샌 보안이 강화되어서
    //내부에서도 퍼미션 체크를 해줘야됨.
    private void checkStorageReadPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            }
        }else{
            //---------------------------------함수 만들어거 갖다붙이기
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    recordingJob();

                } else {
                    Toast.makeText(this, "녹음을 허가해주어야 이 기능을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                //--------------------------------- 위에 케이스문이랑 비슷하게
                break;
        }
    }


    private void recordingJob(){
        /*if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }*/

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        recorder.setOutputFile(RECORDED_FILE);

        try {
            Toast.makeText(getApplicationContext(), "녹음을 시작합니다.",
                    Toast.LENGTH_LONG).show();

            //------------------------------------------------------------------
            //이부분을 어떻게 알아서 함수화하시오. 프리페어 할때 에러나니까, 퍼미션 체크를 하고
            //통과되었을 경우에만 프리페어를 해야됨.
            recorder.prepare();
            recorder.start();
        } catch (Exception ex) {
            Log.e("SampleAudioRecorder", "Exception : ", ex);
        }
    }

}