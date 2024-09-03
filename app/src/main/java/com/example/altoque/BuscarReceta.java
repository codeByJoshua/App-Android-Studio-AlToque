package com.example.altoque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.altoque.Adaptadores.ListaRecetasAdapter;
import com.example.altoque.Entidades.Receta;
import com.example.altoque.db.DbRecetas;

import java.util.ArrayList;

public class BuscarReceta extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView txtBuscar;
    RecyclerView listaRecetas;
    ArrayList<Receta> listaArrayRecetas;
    ListaRecetasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_receta);

        txtBuscar = findViewById(R.id.txtBuscar);

        listaRecetas = findViewById(R.id.listaRecetas);
        listaRecetas.setLayoutManager(new LinearLayoutManager(this));

        DbRecetas dbRecetas = new DbRecetas(BuscarReceta.this);

        listaArrayRecetas = new ArrayList<>();

        adapter = new ListaRecetasAdapter(this, dbRecetas.mostrarReceta());
        listaRecetas.setAdapter(adapter);


        txtBuscar.setOnQueryTextListener(this);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;

    }
}