package com.fjnuse.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

public class XMLMenu extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlmenu);
        Button btn1=(Button)findViewById(R.id.bk);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView=findViewById(R.id.tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 利用菜单填充器将菜单资源文件映射成菜单
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.fontmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item:
                Toast.makeText(this, "你单击了[普通菜单选项]菜单项！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.red:
                textView.setTextColor(RED);
//                Toast.makeText(this, "你单击了[字体大小]菜单项！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.black:
                textView.setTextColor(BLACK);
//                Toast.makeText(this, "你单击了[字体大小]菜单项！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.small:
                textView.setTextSize(10);
                break;
            case R.id.middle:
                textView.setTextSize(16);
                break;
            case R.id.large:
                textView.setTextSize(20);
                break;
        }
        return true;
     }
}
