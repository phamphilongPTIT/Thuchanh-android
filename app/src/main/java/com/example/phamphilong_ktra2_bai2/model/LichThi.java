package com.example.phamphilong_ktra2_bai2.model;

public class LichThi {

    private int id;
    private String tenMon;
    private String ngayThi;
    private String gioBatDau;
    private String ThiViet;

    public LichThi(int id, String tenMon, String ngayThi, String gioBatDau, String thiViet) {
        this.id = id;
        this.tenMon = tenMon;
        this.ngayThi = ngayThi;
        this.gioBatDau = gioBatDau;
        ThiViet = thiViet;
    }

    public LichThi(String tenMon, String ngayThi, String gioBatDau, String thiViet) {
        this.tenMon = tenMon;
        this.ngayThi = ngayThi;
        this.gioBatDau = gioBatDau;
        ThiViet = thiViet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public String getThiViet() {
        return ThiViet;
    }

    public void setThiViet(String thiViet) {
        ThiViet = thiViet;
    }
}
