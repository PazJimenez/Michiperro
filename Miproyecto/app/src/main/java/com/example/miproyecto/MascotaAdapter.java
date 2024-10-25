package com.example.miproyecto;

// MascotaAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Intent;


public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {
    private final List<Mascota> mascotas;
    private final Context context;

    public MascotaAdapter(Context context, List<Mascota> mascotas) {
        this.context = context;
        this.mascotas = mascotas;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);
        holder.nombreMascota.setText(mascota.getNombre());
        holder.imagenMascota.setImageResource(mascota.getImagenResId());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleMascotaActivity.class);
            intent.putExtra("nombre", mascota.getNombre());
            intent.putExtra("descripcion", mascota.getDescripcion());
            intent.putExtra("edad", mascota.getEdad());
            intent.putExtra("foto", mascota.getImagenResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    static class MascotaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenMascota;
        TextView nombreMascota;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenMascota = itemView.findViewById(R.id.imagenMascota);
            nombreMascota = itemView.findViewById(R.id.nombreMascota);
        }
    }
}

