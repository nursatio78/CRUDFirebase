package com.nursationugroho.crudfirebase.adapter;

public class BarangModel {
    String namaBarang, hargaBarang, deskripsiBarang, kategori, image;

    public BarangModel() {
    }

    public BarangModel(String namaBarang, String hargaBarang, String deskripsiBarang, String kategori, String image) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.deskripsiBarang = deskripsiBarang;
        this.kategori = kategori;
        this.image = image;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(String hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public void setDeskripsiBarang(String deskripsiBarang) {
        this.deskripsiBarang = deskripsiBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
