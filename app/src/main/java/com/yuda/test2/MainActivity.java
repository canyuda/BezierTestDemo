package com.yuda.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void secondBezierTest(View view){
        Intent intent = new Intent(MainActivity.this,SecondBezierActivity.class);
        startActivity(intent);
    }
    public void ThirdBezierTest(View view){
        Intent intent = new Intent(MainActivity.this,ThirdBezierActivity.class);
        startActivity(intent);
    }
    public void DrawBoardTest(View view){
        Intent intent = new Intent(MainActivity.this,DrawBoard.class);
        startActivity(intent);
    }
    public void PathMorthing(View view){
        Intent intent = new Intent(MainActivity.this,PathMorthingAcitvity.class);
        startActivity(intent);
    }
    public void WaveBezierTest(View view){
        Intent intent = new Intent(MainActivity.this,WaveBezierActivity.class);
        startActivity(intent);
    }
    public void ShoppingCartTest(View view){
        Intent intent = new Intent(MainActivity.this,ShoppingCartActivity.class);
        startActivity(intent);
    }
}
