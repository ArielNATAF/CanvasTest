package com.example.canvastest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Double val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MyCanvasView myCanvasView = findViewById(R.id.CanvasView);
        //myCanvasView.(R.id.frameView);
        //setContentView(myCanvasView);

        MyCanvasView myCanvasView = new MyCanvasView(this);
        myCanvasView.setMyCanvasListener(new MyCanvasView.MyCanvasListener() {
            @Override
            public void canvasLength(Double Canvasval) {
                val = Canvasval;
            }
        });


        Button bt1 = findViewById(R.id.buttonSize);
        TextView textView = findViewById(R.id.textViewSize);

        bt1.setOnClickListener(v -> {
            if (val!=null)
                textView.setText(String.valueOf(val));
            else
                textView.setText("null");
        });

    }
}