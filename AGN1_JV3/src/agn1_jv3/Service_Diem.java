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
import javax.swing.JOptionPane;

public class Service_Diem {

    private static Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql;

    public Service_Diem() {
        connection = DBConnect.getConnection();
    }

    public ArrayList<Model_Diem> getAll() {
        sql = "select * from dbo.V_Top3SV";
        ArrayList lst_Diem = new ArrayList();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSV;
                String hoTen;
                int tiengAnh;
                int tinHoc;
                int GDTC;
                double diemTB;
                maSV = rs.getString(1);
                hoTen = rs.getString(2);
                tiengAnh = rs.getInt(3);
                tinHoc = rs.getInt(4);
                GDTC = rs.getInt(5);
                diemTB = rs.getDouble(6);
                Model_Diem model_Diem = new Model_Diem(maSV, hoTen, tiengAnh, tinHoc, GDTC, diemTB);
                lst_Diem.add(model_Diem);
            }
            return lst_Diem;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Model_Diem> Find(String ma) {
        sql = "select MaSV,Hoten,Tienganh,Tinhoc,GDTC,DiemTB\n"
                + "from V_QLSV where MaSV like ?";
        ArrayList lst_Diem = new ArrayList();
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSV;
                String hoTen;
                int tiengAnh;
                int tinHoc;
                int GDTC;
                double diemTB;
                maSV = rs.getString(1);
                hoTen = rs.getString(2);
                tiengAnh = rs.getInt(3);
                tinHoc = rs.getInt(4);
                GDTC = rs.getInt(5);
                diemTB = rs.getDouble(6);
                Model_Diem model_Diem = new Model_Diem(maSV, hoTen, tiengAnh, tinHoc, GDTC, diemTB);
                lst_Diem.add(model_Diem);
            }
            return lst_Diem;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int Add(Model_Diem model_Diem) {
        
        if (isMaSVExists(model_Diem.getMaSV())) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên đã tồn tại!");
            return 0;
        }
        if (!isMaSVInStudentsTable(model_Diem.getMaSV())) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên không tồn tại trong bảng Students!");
            return 0;
        }
        sql = "insert into GRADE(MaSV,Tienganh,Tinhoc,GDTC) values (?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, model_Diem.getMaSV());
            ps.setObject(2, model_Diem.getTiengAnh());
            ps.setObject(3, model_Diem.getTinHoc());
            ps.setObject(4, model_Diem.getGDTC());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int Update(String ma, Model_Diem model_Diem) {
        sql = "update GRADE\n"
                + "set Tienganh = ?,Tinhoc = ?,GDTC = ?\n"
                + "where MaSV = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setObject(1, model_Diem.getTiengAnh());
            ps.setObject(2, model_Diem.getTinHoc());
            ps.setObject(3, model_Diem.getGDTC());
            ps.setObject(4, ma);
            return ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    public int Delete(String ma){
        sql = "delete from GRADE where  MaSV = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
      
     private boolean isMaSVExists(String maSV) {
        // Kiểm tra xem mã sinh viên đã tồn tại trong bảng GRADE chưa
        String sql = "SELECT COUNT(*) FROM GRADE WHERE MaSV = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, maSV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isMaSVInStudentsTable(String maSV) {
        // Kiểm tra xem mã sinh viên có tồn tại trong bảng Students không
        String sql = "SELECT COUNT(*) FROM Students WHERE MaSV = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, maSV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
