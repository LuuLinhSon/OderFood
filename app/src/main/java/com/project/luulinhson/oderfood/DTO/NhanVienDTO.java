package com.project.luulinhson.oderfood.DTO;

/**
 * Created by Admin on 1/11/2017.
 */

public class NhanVienDTO {
    int manhanvien;
    String tendn;
    String matkhau;
    String ngaysinh;
    String gioitinh;
    String cmnd;

//    public NhanVienDTO(int manhanvien, String tendn, String matkhau, String ngaysinh, String gioitinh, String cmnd) {
//        this.manhanvien = manhanvien;
//        this.tendn = tendn;
//        this.matkhau = matkhau;
//        this.ngaysinh = ngaysinh;
//        this.gioitinh = gioitinh;
//        this.cmnd = cmnd;
//    }



    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getTendn() {
        return tendn;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

}
