package com.example.altoque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.altoque.Adaptadores.IngredientesAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class PantallaInfoReceta extends AppCompatActivity {

    RecyclerView listaIngredientes;
    Button cambiaraPasos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_info_receta);

        listaIngredientes = findViewById(R.id.listaIngredientes);
        listaIngredientes.setLayoutManager(new LinearLayoutManager(this));

        //Obtengo la informacion del intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");

        String ingredientes = intent.getStringExtra("ingredientes");
        String[] arrayIngredientes = ingredientes.split("\n");
        ArrayList<String> IngredientesLista = new ArrayList<>(Arrays.asList(arrayIngredientes));

        String preparacion = intent.getStringExtra("preparacion");
        String[] arrayPasos = preparacion.split("\n");
        ArrayList<String> listaPasos = new ArrayList<>(Arrays.asList(arrayPasos));

        String consejos = intent.getStringExtra("consejos");
        String[] arrayTips = consejos.split("\n");
        ArrayList<String> listaConsejos = new ArrayList<>(Arrays.asList(arrayTips));

        cambiaraPasos = findViewById(R.id.comenzemos);
        cambiaraPasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PantallaInfoReceta.this, MostrarPasos.class);
                intent2.putExtra("preparacion", listaPasos);
                intent2.putExtra("consejos", listaConsejos);
                startActivity(intent2);
                finish();
            }
        });


        TextView nombreReceta = findViewById(R.id.recetaNombre);
        nombreReceta.setText(nombre);

        IngredientesAdapter adapter = new IngredientesAdapter(IngredientesLista);
        listaIngredientes.setAdapter(adapter);
    }

}