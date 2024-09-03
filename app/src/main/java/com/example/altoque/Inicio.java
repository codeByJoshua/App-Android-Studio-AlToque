package com.example.altoque;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        @SuppressLint("WrongViewCast")
        ImageView imageView = findViewById(R.id.gifpensar);

        Glide.with(this)
                .load(R.drawable.pensando)
                .into(imageView);


        ImageView secondImage = findViewById(R.id.gifsarten);

        Glide.with(this)
                .load(R.drawable.sarten)
                .into(secondImage);


        Button subir = findViewById(R.id.subir);
        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, SubirReceta.class);
                startActivity(intent);

            }
        });

        Button buscar = findViewById(R.id.buscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, BuscarReceta.class);
                startActivity(intent);

            }
        });
    }
}