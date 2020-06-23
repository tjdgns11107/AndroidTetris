package com.chobocho.tetrisgame;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chobocho.tetris.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_SCORE = 123;

    private View.OnClickListener listener = v -> {
        if(v.getId() == R.id.tetrisBtn) {
            Intent i = new Intent(this, ChoboTetrisActivity.class);
//            startActivityForResult(i, REQ_SCORE);
            startActivity(i);
        }
    };

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQ_SCORE) {
//            String resultStr = "Result Code: " + resultCode;
//            if(data != null) {
//                String result  = data.getStringExtra("result");
//                if(result != null) {
//                    result = (" " + result);
//                    Log.d("TAG", "ddddd: " + result);
//                }
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tetrisBtn).setOnClickListener(listener);
    }
}