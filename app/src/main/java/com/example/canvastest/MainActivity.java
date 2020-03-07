package com.example.canvastest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyCanvasView myCanvasView = findViewById(R.id.CanvasView);
        //myCanvasView.(R.id.frameView);
        //setContentView(myCanvasView);

        Button bt1 = findViewById(R.id.buttonSize);
        TextView textView = findViewById(R.id.textViewSize);

        bt1.setOnClickListener(v -> {
            if(myCanvasView.getPouet()==null)
                textView.setText("pouet");
            else
                textView.setText(myCanvasView.getPouet());
        });

    }
}