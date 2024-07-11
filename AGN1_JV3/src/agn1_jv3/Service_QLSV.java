/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agn1_jv3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Service_QLSV {

    private static Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public Service_QLSV() {
        connection = DBConnect.getConnection();
    }

    public ArrayList<Model_QLSV> getAll() {
        sql = "select MaSV,Hoten,Email,SoDT,Gioitinh,DiaChi,Hinh from STUDENTS";
        ArrayList lstqlsv = new ArrayList();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String masv;
                String hoten;
                String email;
                String sdt;
                boolean gioitinh;
                String diachi;
                String hinh;

                masv = rs.getString(1);
                hoten = rs.getString(2);
                email = rs.getString(3);
                sdt = rs.getString(4);
                gioitinh = rs.getBoolean(5);
                diachi = rs.getString(6);
                hinh = rs.getString(7);
                Model_QLSV model_QLSV = new Model_QLSV(masv, hoten, email, sdt, gioitinh, diachi, hinh);
                lstqlsv.add(model_QLSV);
            }
            return lstqlsv;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int Add(Model_QLSV model_QLSV) {
        sql = "insert into STUDENTS(MaSV,Hoten,Email,SoDT,Gioitinh,DiaChi) values (?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, model_QLSV.getMasv());
            ps.setObject(2, model_QLSV.getHoten());
            ps.setObject(3, model_QLSV.getEmail());
            ps.setObject(4, model_QLSV.getSdt());
            ps.setObject(5, model_QLSV.isGioitinh());
            ps.setObject(6, model_QLSV.getDiachi());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int Delete(String ma) {
        sql = "exec dbo.xoaData ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int Update(String ma, Model_QLSV model_QLSV) {
        sql = "Update STUDENTS\n"
                + "set Hoten = ?,Email = ?,SoDT = ?,Gioitinh = ?,DiaChi = ?\n"
                + "where MaSV = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, model_QLSV.getHoten());
            ps.setObject(2, model_QLSV.getEmail());
            ps.setObject(3, model_QLSV.getSdt());
            ps.setObject(4, model_QLSV.isGioitinh());
            ps.setObject(5, model_QLSV.getDiachi());
            ps.setObject(6, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Model_QLSV checkTrungMa(String ma_Form) {
        sql = "select MaSV,Hoten,Email,SoDT,Gioitinh,DiaChi,Hinh from STUDENTS where MaSV like ?";
        Model_QLSV mqlsv = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, ma_Form);
            rs = ps.executeQuery();
            while (rs.next()) {
                String masv;
                String hoten;
                String email;
                String sdt;
                boolean gioitinh;
                String diachi;
                String hinh;

                masv = rs.getString(1);
                hoten = rs.getString(2);
                email = rs.getString(3);
                sdt = rs.getString(4);
                gioitinh = rs.getBoolean(5);
                diachi = rs.getString(6);
                hinh = rs.getString(7);
                Model_QLSV model_QLSV = new Model_QLSV(masv, hoten, email, sdt, gioitinh, diachi, hinh);
                mqlsv = model_QLSV;
            }
            return mqlsv;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
