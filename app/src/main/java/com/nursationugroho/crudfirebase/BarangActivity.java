package com.nursationugroho.crudfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.nursationugroho.crudfirebase.adapter.BarangAdapter;
import com.nursationugroho.crudfirebase.adapter.BarangModel;
import com.nursationugroho.crudfirebase.databinding.ActivityBarangBinding;

import java.util.ArrayList;
import java.util.List;

public class BarangActivity extends AppCompatActivity {
    private ActivityBarangBinding binding;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    private DatabaseReference myRef;
    private RecyclerView recyclerView;
    private List<BarangModel> barangModelList;
    private BarangAdapter barangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_barang);

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Barang");
        mStorage = FirebaseStorage.getInstance();
        recyclerView = binding.rvData;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        barangModelList = new ArrayList<BarangModel>();
        barangAdapter=new BarangAdapter(BarangActivity.this, barangModelList);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                BarangModel barangModel=snapshot.getValue(BarangModel.class);
                barangModelList.add(barangModel);
                barangAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(barangAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}