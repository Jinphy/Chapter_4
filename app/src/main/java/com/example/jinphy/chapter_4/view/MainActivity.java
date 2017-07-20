package com.example.jinphy.chapter_4.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinphy.chapter_4.R;
import com.jinphy.annotation.inject.InjectView;
import com.jinphy.annotation.inject.Injector;
import com.jinphy.annotation.inject.OnClick;

public class MainActivity extends AppCompatActivity {


    @InjectView(id = R.id.text_view)
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.text_view);
        Injector.injectViews(this);
        Injector.injectListeners(this);
        textView.setText("注入成功");
    }

    @OnClick(ids = {R.id.text_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view:
                Toast.makeText(this, "监听器注入成功", Toast.LENGTH_SHORT).show();
        }
    }
}
