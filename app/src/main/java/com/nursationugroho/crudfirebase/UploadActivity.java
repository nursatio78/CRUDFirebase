package com.nursationugroho.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.nursationugroho.crudfirebase.databinding.ActivityUploadBinding;

public class UploadActivity extends AppCompatActivity {
    private ActivityUploadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload);

//        binding.ivBarang.set
    }
}