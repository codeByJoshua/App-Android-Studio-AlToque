package com.example.altoque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PantallaFinal extends AppCompatActivity {

    ImageView imageView;
    Button btnCerrar, btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_final);

        imageView = findViewById(R.id.gifPlatos);

        Glide.with(this)
                .load(R.drawable.platos)
                .into(imageView);

        btnInicio = findViewById(R.id.botonInicio);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantallaFinal.this, Inicio.class);
                startActivity(intent);
                finish();
            }
        });

        btnCerrar = findViewById(R.id.botonCerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}