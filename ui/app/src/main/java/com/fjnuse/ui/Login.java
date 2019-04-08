package com.fjnuse.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.DialogInterface;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private android.view.LayoutInflater LayoutInflater;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //此处引用布局文件
        setContentView(R.layout.login);
//        getSupportActionBar().hide();
        LayoutInflater=LayoutInflater.from(Login.this);
        view=LayoutInflater.inflate(R.layout.dialog, null);
        Button btn1=(Button)findViewById(R.id.lg);
        Button btn2=(Button)findViewById(R.id.bk);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater=LayoutInflater.from(Login.this);
                view=LayoutInflater.inflate(R.layout.dialog, null);

                AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
                builder.setView(view);
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", null);
                AlertDialog d=builder.create();
                d.show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
