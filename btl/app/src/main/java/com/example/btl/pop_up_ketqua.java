package com.example.btl;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class pop_up_ketqua extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_ketqua);
//        DisplayMetrics dm =new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width =dm.widthPixels;
//        int height =dm.heightPixels;
//
//        getWindow().setLayout((int)width*8,(int)height*5);
//
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity = Gravity.CENTER;
//        params.x =0;
//        params.y =-20;
//
//        getWindow().setAttributes(params);


        String xxx= getIntent().getStringExtra(quizActivity.DIEMSO);
        TextView tvw_diemso =(TextView)findViewById(R.id.tvw_diemso);
        Button btn_restart =(Button)findViewById(R.id.btn_restart);
        tvw_diemso.setText(xxx);
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goilai = new Intent(pop_up_ketqua.this,quizActivity.class);
                startActivity(goilai);
            }
        });



    }
}