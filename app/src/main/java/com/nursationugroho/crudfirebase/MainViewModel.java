package com.nursationugroho.crudfirebase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nursationugroho.crudfirebase.callback.ActionListener;

import java.util.HashMap;
import java.util.Map;

public class MainViewModel extends ViewModel {
    private DatabaseReference myRef;
    private StorageReference storageReference;
    private ActionListener actionListener;

    public MainViewModel(ActionListener actionListener) {
        this.actionListener = actionListener;
        this.myRef = FirebaseDatabase.getInstance().getReference();
    }

    public void pushData(String namaBarang, String hargaBarang, String deskripsiBarang) {
        myRef = FirebaseDatabase.getInstance().getReference().child("barang");

        Map<String, Object> barangHashMap = new HashMap<>();
        barangHashMap.put("namaBarang", namaBarang);
        barangHashMap.put("hargaBarang", hargaBarang);
        barangHashMap.put("deskripsiBarang", deskripsiBarang);

        myRef.updateChildren(barangHashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            actionListener.onSuccess("Push data berhasil");
                        } else {
                            actionListener.onError("Push data gagal");
                        }
                    }
                });
    }

    public void pushPhoto(String photo) {
        myRef = FirebaseDatabase.getInstance().getReference().child("barang");
    }
}
