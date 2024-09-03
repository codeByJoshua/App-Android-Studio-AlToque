package com.example.altoque;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.altoque.db.DbRecetas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubirReceta extends AppCompatActivity {

    EditText txtNombre, txtDescripcion, txtIngredientes, txtPreparacion, txtConsejos;
    Button btnGuarda;
    Button btnCamara;
    ImageView imageView;
    String rutaImagen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_receta);

        txtNombre = findViewById(R.id.nombreReceta);
        txtDescripcion = findViewById(R.id.descripcionReceta);
        txtIngredientes = findViewById(R.id.ingredienteReceta);
        txtPreparacion = findViewById(R.id.preparacionReceta);
        txtConsejos = findViewById(R.id.consejosReceta);

        btnGuarda = findViewById(R.id.subirReceta);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreReceta = txtNombre.getText().toString().trim();
                String descripcionReceta = txtDescripcion.getText().toString().trim();
                String ingredientesReceta = txtIngredientes.getText().toString().trim();
                String preparacionReceta = txtPreparacion.getText().toString().trim();
                String consejosReceta = txtConsejos.getText().toString().trim();

                if (nombreReceta.isEmpty() || descripcionReceta.isEmpty() || ingredientesReceta.isEmpty() || preparacionReceta.isEmpty() || consejosReceta.isEmpty()) {
                    Toast.makeText(SubirReceta.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else if (rutaImagen.isEmpty()) {
                    Toast.makeText(SubirReceta.this, "Por favor, toma una foto", Toast.LENGTH_SHORT).show();
                } else {
                    DbRecetas dbRecetas = new DbRecetas(SubirReceta.this);
                    long id = dbRecetas.insertaReceta(nombreReceta, descripcionReceta, ingredientesReceta, preparacionReceta, consejosReceta, rutaImagen);

                    if (id > 0) {
                        Toast.makeText(SubirReceta.this, "RECETA GUARDADA", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(SubirReceta.this, "ERROR AL GUARDAR RECETA", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //imagen
        btnCamara = findViewById(R.id.botonCamara);
        imageView = findViewById(R.id.fotoComida);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });

    }

    private void limpiar(){
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtIngredientes.setText("");
        txtPreparacion.setText("");
        txtConsejos.setText("");
        imageView.setImageDrawable(null);
    }

    private void abrirCamara() {
        Intent takePictureIntent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            someActivityResultLauncher.launch(takePictureIntent);
        }
        else {
            showToast("No se puede abrir la cámara");
        }
    }

    private final ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        Bitmap imageBitmap;

                        // Nueva foto capturada
                        imageBitmap = (Bitmap) extras.get("data");
                        // Guardar la imagen en el almacenamiento interno
                        rutaImagen = saveImageToInternalStorage(imageBitmap);

                        imageView.setImageBitmap(imageBitmap);

                    } else {
                        showToast("Operación cancelada");
                    }
                }
            });

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private String saveImageToInternalStorage(Bitmap imageBitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        FileOutputStream outputStream = null;
        try {
            imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            outputStream = new FileOutputStream(imageFile);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageFile.getAbsolutePath();
    }
}