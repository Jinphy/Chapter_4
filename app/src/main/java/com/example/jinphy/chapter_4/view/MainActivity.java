package com.example.jinphy.chapter_4.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinphy.chapter_4.R;
import com.jinphy.annotation.inject.InjectView;
import com.jinphy.annotation.inject.Injector;
import com.jinphy.annotation.inject.OnClick;

public class MainActivity extends AppCompatActivity {


    @InjectView(id = R.id.text_view_1,onClick = "onClick")
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Injector.injectViews(this);
        Injector.injectListeners(this);
        textView.setText("注入成功");
    }


   @OnClick(ids = {R.id.button_1,R.id.button_2,R.id.text_view1})
    public void onClick(View view) {
       Toast.makeText(MainActivity.this, "监听器注入成功", Toast.LENGTH_SHORT).show();

   }
}
