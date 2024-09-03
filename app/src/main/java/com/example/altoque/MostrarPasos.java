package com.example.altoque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MostrarPasos extends AppCompatActivity {

    TextView viewPreparacion, viewConsejo;
    ArrayList<String> listaPasos;
    ArrayList<String> listaConsejos;
    Button nextPaso;
    private int currentIngredientIndex = 0;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_pasos);

        viewPreparacion = findViewById(R.id.pasoMostrado);
        viewConsejo = findViewById(R.id.consejoMostrado);
        nextPaso = findViewById(R.id.botonNext);

        imageView = findViewById(R.id.gifCocinando);
        Glide.with(this)
                .load(R.drawable.cocinando)
                .into(imageView);

        Intent intent = getIntent();
        listaPasos = intent.getStringArrayListExtra("preparacion");
        listaConsejos = intent.getStringArrayListExtra("consejos");

        for(String consejo : listaConsejos){
            viewConsejo.append(consejo + "\n");
        }


        //MOSTRAR SIGUIENTE PASO
        mostrarSiguientePaso();

        //CONFIGURAR EL BOTON LISTENER PARA EL BOTON DE SIGUIENTE
        nextPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSiguientePaso();
            }
        });

    }

    private void mostrarSiguientePaso(){
        if(currentIngredientIndex < listaPasos.size()){
            String siguienteIngrediente = listaPasos.get(currentIngredientIndex);
            viewPreparacion.setText(siguienteIngrediente);
            currentIngredientIndex++;
        }
        else {
            //Toast.makeText(this, "No hay más pasos", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MostrarPasos.this, PantallaFinal.class);
            startActivity(intent);
            finish();
        }
    }
}