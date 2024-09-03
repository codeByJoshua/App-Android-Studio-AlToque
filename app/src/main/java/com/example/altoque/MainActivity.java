package com.example.altoque;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.altoque.Secundarios.diferenciaDias;
import com.example.altoque.db.DbHelper;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    Button secondPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //codigo para calcular cuantos dias faltan
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaEntrega = LocalDate.of(2024, 2, 29);

        long diferenciaDia = diferenciaDias.calcularDias(fechaActual, fechaEntrega);

        TextView dia = findViewById(R.id.dias);
        dia.setText("Dias para la entrega: "+diferenciaDia);
        //fin del codigo


        secondPage = findViewById(R.id.tosecondpage);
        secondPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(MainActivity.this, "BIENVENIDO", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(MainActivity.this, Inicio.class);
                startActivity(intent);
                finish();
            }
        });




    }
}