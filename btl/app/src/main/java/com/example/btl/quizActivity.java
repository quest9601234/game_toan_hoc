package com.example.btl;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioDeviceCallback;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl.R;

import java.util.Locale;
import java.util.Random;

public class quizActivity extends AppCompatActivity {
    private long backPressedTime;

    private SoundPool soundPool;
    public int sound_true,sound_false;
    private AudioManager audioManager;


    public static final String DIEMSO = "diemso";
    public static final String EXTRA_SCORE ="extraScore";
    public static final long COUNTDOWN_IN_MILLIS = 3000;
    CountDownTimer countDownTimer;
    long timeLeftInMillis;
     int x,y,z;
     int score=0;
     TextView diem,thoigian,cauhoi;
     Button dung,sai;
   // Intent intent_hienthiketqua = new Intent(getApplicationContext(),pop_up_ketqua.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        rand1();
        anhxa();

        if (Build.VERSION.SDK_INT >= 21) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder soundPool = new SoundPool.Builder();
            soundPool.setAudioAttributes(audioAttrib).setMaxStreams(3).build();

            //this.soundPool = soundPool.build();
        } else {

            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        sound_true = soundPool.load(this, R.raw.gun, 1);
        sound_false = soundPool.load(this, R.raw.destroy, 1);



////        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
////            AudioAttributes audioAttributes = new AudioAttributes().Builder()
////                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
////                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
////                    .build();
//
//
//        }else{
//            soundPool =new SoundPool(3, AudioManager.STREAM_MUSIC,0,);
//        }

        dung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ketquadung=x+y;
                if(z==ketquadung){
                    play_sound_true();
                    score++;
                    diem.setText("Score:"+score);
                    countDownTimer.cancel();
                    if(score < 10&& score >=0){
                        rand1();
                    }else if (score>10 && score <20){
                        rand2();
                    }else {
                        rand3();
                    }
                    //rand1();
                }
                else
                    //Intent intent_hienthiketqua = new Intent(quizActivity.this,pop_up_ketqua.class);
                //startActivity(intent_hienthiketqua);
                //showSolution();
                {   play_sound_false();
                    finishQuiz();}
            }
        });
        sai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ketquadung=x+y;
                if(z!=ketquadung){
                    play_sound_true();
                    score++;
                    diem.setText("Score:"+score);
                    countDownTimer.cancel();
                    //rand1();
                    if(score < 10&& score >=0){
                        rand1();
                    }else if (score>10 && score <20){
                        rand2();
                    }else {
                        rand3();
                    }
                }
                else {
                    play_sound_false();
                    //Intent intent_hienthiketqua = new Intent(quizActivity.this,pop_up_ketqua.class);
                    //startActivity(intent_hienthiketqua);
                    //showSolution();
                    finishQuiz();

                }
            }
        });
    }

    public void play_sound_true(){
        soundPool.play(sound_true,1,1,0,0,1);
    }
    public void play_sound_false(){
        soundPool.play(sound_false,1,1,0,0,1);
    }

    public void rand1(){
        Random rd =new Random();
        x=1+rd.nextInt(5);
        y=1+rd.nextInt(5);
        z=(x+y-1)+rd.nextInt(2);
        anhxa();
        cauhoi.setText(""+x+"+"+y+"="+z);
        timeLeftInMillis =COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    public void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis =0;
                showSolution();

            }
        }.start();
    }

    public void updateCountDownText(){
        int minutes =(int)(timeLeftInMillis/1000)/60;
        int seconds =(int)(timeLeftInMillis/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        anhxa();
        thoigian.setText(timeFormatted);
    }

    public void anhxa(){
        diem=(TextView)findViewById(R.id.tvw_score);
        thoigian =(TextView)findViewById(R.id.tvw_countdown);
        cauhoi=(TextView)findViewById(R.id.cauhoi);
        dung=(Button)findViewById(R.id.btn_true);
        sai=(Button)findViewById(R.id.btn_false);
    }
//    public void finish(){
//
//    }
    public void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultIntent);
        showSolution();
    }
    public void showSolution(){
        Intent intent_showSolution = new Intent(quizActivity.this,pop_up_ketqua.class);
        anhxa();
        intent_showSolution.putExtra(DIEMSO,diem.getText().toString());
        startActivity(intent_showSolution);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
        soundPool.release();
        soundPool = null;
    }

    public void rand2(){
        Random rd =new Random();
        x=5+rd.nextInt(5);
        y=5+rd.nextInt(5);
        z=(x+y-1)+rd.nextInt(2);
        anhxa();
        cauhoi.setText(""+x+"+"+y+"="+z);
        timeLeftInMillis =COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    public void rand3(){
        Random rd =new Random();
        x=5+rd.nextInt(10);
        y=5+rd.nextInt(10);
        z=(x+y-1)+rd.nextInt(2);
        anhxa();
        cauhoi.setText(""+x+"+"+y+"="+z);
        timeLeftInMillis =COUNTDOWN_IN_MILLIS;
        startCountDown();
    }
    @Override
    public void onBackPressed() {

            if(backPressedTime + 2000 > System.currentTimeMillis()){
                showSolution();
            }else{
                Toast.makeText(this,"quay lai de ket thuc",Toast.LENGTH_SHORT).show();
            }
            backPressedTime =System.currentTimeMillis();
    }

}
