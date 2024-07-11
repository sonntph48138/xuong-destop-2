/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agn1_jv3;

/**
 *
 * @author anhso
 */
public class Model_Diem {

    private String maSV;
    private String hoTen;
    private int tiengAnh;
    private int tinHoc;
    private int GDTC;
    private double diemTB;

    public Model_Diem() {
    }

    public Model_Diem(String maSV, int tiengAnh, int tinHoc, int GDTC) {
        this.maSV = maSV;
        this.tiengAnh = tiengAnh;
        this.tinHoc = tinHoc;
        this.GDTC = GDTC;
    }
    

    public Model_Diem(String maSV, String hoTen, int tiengAnh, int tinHoc, int GDTC, double diemTB) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.tiengAnh = tiengAnh;
        this.tinHoc = tinHoc;
        this.GDTC = GDTC;
        this.diemTB = diemTB;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(int tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public int getTinHoc() {
        return tinHoc;
    }

    public void setTinHoc(int tinHoc) {
        this.tinHoc = tinHoc;
    }

    public int getGDTC() {
        return GDTC;
    }

    public void setGDTC(int GDTC) {
        this.GDTC = GDTC;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.maSV, this.hoTen, this.tiengAnh, this.tinHoc, this.GDTC, this.diemTB
        };
    }
}
