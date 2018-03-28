package com.example.goa.braingame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.chandora.testyourmaths.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {
            case R.id.addition:
                intent = new Intent(this, SecondActivity.class);
                intent.putExtra("TAG", 1);
                startActivity(intent);
                break;
            case R.id.subtraction:
                intent = new Intent(this, SecondActivity.class);
                intent.putExtra("TAG", 2);
                startActivity(intent);
                break;
            case R.id.multiplication:
                intent = new Intent(this, SecondActivity.class);
                intent.putExtra("TAG", 3);
                startActivity(intent);
                break;
            case R.id.division:
                intent = new Intent(this, SecondActivity.class);
                intent.putExtra("TAG", 4);
                startActivity(intent);
                break;
        }

    }
}
