package com.nursationugroho.crudfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.nursationugroho.crudfirebase.callback.ActionListener;
import com.nursationugroho.crudfirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this, new BarangVMFactory(actionListener)).get(MainViewModel.class);

        binding.btAdd.setOnClickListener(this::onViewClicked);
        binding.ivBarang.setOnClickListener(this::onViewClicked);
    }

    public boolean validasidata() {
        return !TextUtils.isEmpty(binding.etNamaBarang.getText().toString()) &&
                !TextUtils.isEmpty(binding.etHargaBarang.getText().toString()) &&
                !TextUtils.isEmpty(binding.etDeskripsiBarang.getText().toString());
    }

    public void pushData() {
        viewModel.pushData(binding.etNamaBarang.getText().toString(),
                "Rp. "+binding.etHargaBarang.getText().toString(),
                binding.etDeskripsiBarang.getText().toString(),
                binding.category.getSelectedItem().toString());
    }

    private void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btAdd:
                if (validasidata()) {
                    pushData();
                } else {
                    Snackbar.make(binding.getRoot(), "Isian harus diisi!", BaseTransientBottomBar.LENGTH_LONG).setAnchorView(R.id.btAdd).show();
                }
                break;
            case R.id.ivBarang:
                pilihPhoto();
                break;
        }
    }

    private void pilihPhoto() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null) {
            imageUri = data.getData();
            binding.ivBarang.setImageURI(imageUri);
        }
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses...", BaseTransientBottomBar.LENGTH_INDEFINITE).setAnchorView(binding.etNamaBarang).show();
            binding.btAdd.setEnabled(false);
        }

        @Override
        public void onSuccess(String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(binding.etNamaBarang).show();
            binding.btAdd.setEnabled(true);
            new Handler().postDelayed(() -> {
                startActivity(new Intent(MainActivity.this, BarangActivity.class));
                finishAffinity();
            },1000);
        }

        @Override
        public void onError(String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).setAnchorView(binding.etNamaBarang).show();
            binding.btAdd.setEnabled(true);
        }
    };
}