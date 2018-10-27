package com.khieuthichien.thicoban;

public class Sinhvien {
    private String masv;
    private String malop;
    private int diemtoan;
    private int diemvan;

    public Sinhvien(String masv, String malop, int diemtoan, int diemvan) {
        this.masv = masv;
        this.malop = malop;
        this.diemtoan = diemtoan;
        this.diemvan = diemvan;
    }

    public Sinhvien() {
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public int getDiemtoan() {
        return diemtoan;
    }

    public void setDiemtoan(int diemtoan) {
        this.diemtoan = diemtoan;
    }

    public int getDiemvan() {
        return diemvan;
    }

    public void setDiemvan(int diemvan) {
        this.diemvan = diemvan;
    }
}
