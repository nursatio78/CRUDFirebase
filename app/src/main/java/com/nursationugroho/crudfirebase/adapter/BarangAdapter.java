package com.nursationugroho.crudfirebase.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nursationugroho.crudfirebase.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.Viewholder>{

    Context context;
    List<BarangModel> barangModelsList;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference("Barang");

    public BarangAdapter(Context context, List<BarangModel> barangModelsList) {
        this.context = context;
        this.barangModelsList = barangModelsList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        BarangModel barangModels = barangModelsList.get(position);
        holder.tvNamaBarang.setText(barangModels.getNamaBarang());
        holder.tvHargaBarang.setText(barangModels.getHargaBarang());
        holder.tvDeskripsiBarang.setText(barangModels.getDeskripsiBarang());
        holder.tvKategoriBarang.setText(barangModels.getKategori());

        String imageUri=null;
        imageUri=barangModels.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.child(barangModels.getNamaBarang()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Gagal menghapus data!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("Apakah Yakin Mau Menghapus ?" + barangModels.getNamaBarang());
                builder.show();
            }
        });

        //TODO = UPDATE BARANG!
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return barangModelsList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvNamaBarang, tvHargaBarang, tvDeskripsiBarang, tvKategoriBarang;
        Button btnUpdate, btnHapus;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.ivPhoto);
            tvNamaBarang=itemView.findViewById(R.id.tvNama);
            tvHargaBarang=itemView.findViewById(R.id.tvHarga);
            tvDeskripsiBarang=itemView.findViewById(R.id.tvDeskripsi);
            tvKategoriBarang=itemView.findViewById(R.id.tvKategori);
            btnUpdate=itemView.findViewById(R.id.btUpdate);
            btnHapus=itemView.findViewById(R.id.btHapus);
        }
    }
}
