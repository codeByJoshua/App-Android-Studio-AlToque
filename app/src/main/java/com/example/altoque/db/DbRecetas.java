package com.example.altoque.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.altoque.Entidades.Receta;

import java.util.ArrayList;

public class DbRecetas extends DbHelper{

    Context context;

    public DbRecetas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaReceta(String nombre, String descripcion, String ingredientes, String preparacion, String consejos, String rutaImagen){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("descripcion", descripcion);
            values.put("ingredientes", ingredientes);
            values.put("preparacion", preparacion);
            values.put("consejos", consejos);
            values.put("ruta", rutaImagen);

            id = db.insert(TABLE_RECETA, null, values);
        }
        catch (Exception e){
            e.toString();
        }

        return id;

    }


    public ArrayList<Receta> mostrarReceta(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Receta> listaRecetas = new ArrayList<>();
        Receta receta = null;
        Cursor cursorRecetas = null;

        cursorRecetas = db.rawQuery("SELECT * FROM " + TABLE_RECETA, null);

        if(cursorRecetas.moveToFirst()){
            do{
                receta = new Receta();
                receta.setId(cursorRecetas.getInt(0));
                receta.setNombre(cursorRecetas.getString(1));
                receta.setDescripcion(cursorRecetas.getString(2));
                receta.setIngredientes(cursorRecetas.getString(3));
                receta.setPreparacion(cursorRecetas.getString(4));
                receta.setTips(cursorRecetas.getString(5));
                receta.setRutaImagen(cursorRecetas.getString(6));

                listaRecetas.add(receta);
            } while (cursorRecetas.moveToNext());
        }

        cursorRecetas.close();

        return listaRecetas;

    }
}
