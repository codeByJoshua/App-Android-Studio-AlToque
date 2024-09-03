package com.example.altoque.Secundarios;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.altoque.MainActivity;
import com.example.altoque.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //splash
        new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
        }, 3000);


        @SuppressLint("WrongViewCast")
        ImageView imageView = findViewById(R.id.gifchef);

        Glide.with(this)
                .load(R.drawable.chef)
                .into(imageView);

    }


}