package com.example.btl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_QUIZ =1 ;
    public static final String SHARED_PREFS ="sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    TextView tvw_highscore;
    int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadHighsocre();
        Button btn_start=findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_play();
            }
        });
    }
    private void start_play(){
        Intent intent_start_play = new Intent(MainActivity.this ,quizActivity.class);
        startActivityForResult(intent_start_play,REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_QUIZ){
            if(resultCode== RESULT_OK){
                int score = data.getIntExtra(quizActivity.EXTRA_SCORE,0);
                    if(score > highscore){
                        updateHighscore(score);
                    }

            }
        }
    }

    public void loadHighsocre(){
        SharedPreferences prefs =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE,0);
        tvw_highscore=(TextView)findViewById(R.id.tvw_highscore);
        tvw_highscore.setText("Highscore: "+highscore);
    }

    public void updateHighscore(int newHighscore){
            highscore=newHighscore;
            tvw_highscore=(TextView)findViewById(R.id.tvw_highscore);
            tvw_highscore.setText("Highscore: "+highscore);

        SharedPreferences prefs =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();

    }

}
