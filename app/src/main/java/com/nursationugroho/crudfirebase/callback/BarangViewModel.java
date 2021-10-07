package com.nursationugroho.crudfirebase.callback;

import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarangViewModel extends ViewModel {
    private DatabaseReference myRef;

    public BarangViewModel() {
        this.myRef = FirebaseDatabase.getInstance().getReference("Barang");
    }

//    public void getAllData()
}
