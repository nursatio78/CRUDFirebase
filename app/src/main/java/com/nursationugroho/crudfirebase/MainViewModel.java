package com.nursationugroho.crudfirebase;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.nursationugroho.crudfirebase.callback.ActionListener;

import java.util.HashMap;
import java.util.Map;

public class MainViewModel extends ViewModel {
    private DatabaseReference myRef;
    private StorageReference storageReference;
    private ActionListener actionListener;

    public MainViewModel(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void pushData(String namaBarang, String hargaBarang, String deskripsiBarang, String kategori, Uri imageUri) {
        myRef = FirebaseDatabase.getInstance().getReference("Barang").child(namaBarang);
        storageReference = FirebaseStorage.getInstance().getReference().child("imagePost").child(imageUri.getLastPathSegment());
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String t=task.getResult().toString();

                        Map<String, Object> barangHashMap = new HashMap<>();
                        barangHashMap.put("namaBarang", namaBarang);
                        barangHashMap.put("hargaBarang", hargaBarang);
                        barangHashMap.put("deskripsiBarang", deskripsiBarang);
                        barangHashMap.put("kategori", kategori);
                        barangHashMap.put("image", t);

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
                });
            }
        });
    }
}
