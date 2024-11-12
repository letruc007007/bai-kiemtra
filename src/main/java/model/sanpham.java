/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class sanpham{
    private int masanpham;
    private String tensanpham;
    private double gia;
    private String hinh;
    private int maloai;
    private Date ngaycapnhat;

    public sanpham () {
    }

    public sanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public sanpham(int mahoa, String tenhoa, double gia, String hinh, int maloai, Date ngaycapnhat) {
        this.masanpham = mahoa;
        this.tensanpham= tenhoa;
        this.gia = gia;
        this.hinh = hinh;
        this.maloai = maloai;
        this.ngaycapnhat = ngaycapnhat;
    }

    public int getMaSanPham() {
        return masanpham;
    }

    public void setMaSanPham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTenSanPham() {
        return tensanpham;
    }

    public void setTenSanPham(String tensanpham) {
        this.tensanpham= tensanpham;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public Date getNgaycapnhat() {
        return ngaycapnhat;
    }

    public void setNgaycapnhat(Date ngaycapnhat) {
        this.ngaycapnhat = ngaycapnhat;
    }

    @Override
    public String toString() {
        return "Hoa{" + "mahoa=" + masanpham + ", tenhoa=" + tensanpham + ", gia=" + gia + ", hinh=" + hinh + ", maloai=" + maloai + ", ngaycapnhat=" + ngaycapnhat + '}';
    }
    
}
