package com.example.altoque.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.altoque.Entidades.Receta;
import com.example.altoque.PantallaInfoReceta;
import com.example.altoque.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaRecetasAdapter extends RecyclerView.Adapter<ListaRecetasAdapter.RecetaViewHolder> {

    ArrayList<Receta> listaRecetas;
    ArrayList<Receta> listaOriginal;
    Context context;

    public ListaRecetasAdapter(Context context, ArrayList<Receta> listaRecetas){

        this.listaRecetas = listaRecetas;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaRecetas);
        this.context = context;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_receta, null, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {

        Receta receta = listaRecetas.get(position);

        holder.viewNombre.setText(listaRecetas.get(position).getNombre());
        holder.viewDescripcion.setText(listaRecetas.get(position).getDescripcion());

        //cargar imagen con Glide- con esto cargas solo de camara
        Glide.with(context).load(listaRecetas.get(position).getRutaImagen()).into(holder.viewImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "CardView clickeado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, PantallaInfoReceta.class);
                intent.putExtra("nombre", receta.getNombre());
                intent.putExtra("ingredientes", receta.getIngredientes());
                intent.putExtra("preparacion", receta.getPreparacion());
                intent.putExtra("consejos", receta.getTips());

                context.startActivity(intent);
            }
        });

    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud == 0){
            listaRecetas.clear();
            listaRecetas.addAll(listaOriginal);
        }
        else{
            List<Receta> collecion = listaRecetas.stream().filter(i -> i.getNombre()
                    .toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
            listaRecetas.clear();
            listaRecetas.addAll(collecion);
        }

        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public class RecetaViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewDescripcion;
        ImageView viewImage;
        CardView cardView;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDescripcion = itemView.findViewById(R.id.viewDescripcion);
            viewImage = itemView.findViewById(R.id.imageView);

            cardView = itemView.findViewById(R.id.myCardView);
        }
    }

}
