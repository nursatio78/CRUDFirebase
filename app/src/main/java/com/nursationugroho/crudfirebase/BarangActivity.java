package com.nursationugroho.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.nursationugroho.crudfirebase.callback.BarangViewModel;
import com.nursationugroho.crudfirebase.databinding.ActivityBarangBinding;

public class BarangActivity extends AppCompatActivity {
    private ActivityBarangBinding binding;
    private BarangViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_barang);
        viewModel = new ViewModelProvider(this).get(BarangViewModel.class);


    }
}