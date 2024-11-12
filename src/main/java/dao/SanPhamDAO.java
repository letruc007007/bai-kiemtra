/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.sanpham;
/**
 *
 * @author Administrator
 */
public class SanPhamDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    public ArrayList<sanpham> getTop6() {
        ArrayList<sanpham> ds = new ArrayList<>();
        String sql = "select top 6 * from Hoa order by gia desc";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new sanpham(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }
    //Phương thức đọc hoa theo thể loại
    public ArrayList<sanpham> getByCategoryId(int maloai) {
        ArrayList<sanpham> ds = new ArrayList<>();
        String sql = "select * from Hoa where maloai=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maloai);
            rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new sanpham(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }
    //phuong thuc doc tat ca san pham (Hoa) từ CSDL
    public ArrayList<sanpham> getAll() {
        ArrayList<sanpham> ds = new ArrayList<>();
        String sql = "select * from Hoa";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new sanpham(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }
    
    //phuong thuc doc tat ca san pham (Hoa) từ CSDL theo pageIndex,pageSiza
    public ArrayList<sanpham> getByPage(int pageIndex, int pageSize) {
        ArrayList<sanpham> ds = new ArrayList<>();
        String sql = "select * from Hoa order by mahoa OFFSET ?  Row Fetch Next ? Row Only";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,(pageIndex-1)*pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new sanpham(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }
    //Phương thức them mới sản phẩm (Hoa)
    public boolean Insert(sanpham sanpham) {
        String sql = "insert into sanpham (tensanpham,gia,hinh,maloai,ngaycapnhat) values (?,?,?,?,?)";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sanpham.getTenSanPham());
            ps.setDouble(2, sanpham.getGia());
            ps.setString(3, sanpham.getHinh());
            ps.setInt(4, sanpham.getMaloai());
            ps.setDate(5, sanpham.getNgaycapnhat());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return false;
    }
    //Phương thức cập nhật sản phẩm (Hoa)
    public boolean Update(sanpham sanpham) {
        String sql = "update sanpham set tensapham=?,gia=?,hinh=?,maloai=?,ngaycapnhat=? where masanpham=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sanpham.getTenSanPham());
            ps.setDouble(2, sanpham.getGia());
            ps.setString(3, sanpham.getHinh());
            ps.setInt(4, sanpham.getMaloai());
            ps.setDate(5, sanpham.getNgaycapnhat());
            ps.setInt(6, sanpham.getMaSanPham());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return false;
    }
    //Phương thức xoá sản phẩm (Hoa)
    public boolean Delete(int masanpham) {
        String sql = "delete from hoa where mahoa=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, masanpham);
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return false;
    }
    //Phương thức lấy thông tin sản phẩm (Hoa) theo mã hoa 
    public sanpham getById(int masanpham) {
        sanpham kq = null;
        String sql = "select * from sanpham where masanpham=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, masanpham);
            rs = ps.executeQuery();
            if (rs.next()) {
                kq = new sanpham(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
            }
        } catch (Exception ex) {
            System.out.println("Loi: " + ex.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return kq;
    }

    public static void main(String[] args) {
        SanPhamDAO SanPhamDAO = new SanPhamDAO();
        System.out.println("Lay tat ca hoa");
        ArrayList<sanpham> dssanpham = SanPhamDAO.getAll();
        for (sanpham hoa : dssanpham) {
            System.out.println(hoa);
        }
    }
}