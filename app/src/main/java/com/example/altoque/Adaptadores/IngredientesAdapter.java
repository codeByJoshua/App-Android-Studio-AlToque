package com.example.altoque.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.altoque.R;

import java.util.ArrayList;

public class IngredientesAdapter extends RecyclerView.Adapter<IngredientesAdapter.RecetaViewHolder>{

    ArrayList<String> listaIngredientes;

    public IngredientesAdapter(ArrayList<String> listaIngredientes){
        this.listaIngredientes = listaIngredientes;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_ingredientes_receta, null, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        String ingredientes = listaIngredientes.get(position);
        String ingGuion = "- " + ingredientes;
        holder.ingreDiente.setText(ingGuion);
    }

    @Override
    public int getItemCount() {
        return listaIngredientes.size();
    }

    public class RecetaViewHolder extends RecyclerView.ViewHolder {

        TextView ingreDiente;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);

            ingreDiente = itemView.findViewById(R.id.ingrediente);

        }
    }

}
