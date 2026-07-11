package model;

public class Student {

    private int id;
    private String hoTen;
    private String lop;
    private String mssv;
    private String email;

    public Student() {
    }

    public Student(int id, String hoTen, String lop,
                   String mssv, String email) {
        this.id = id;
        this.hoTen = hoTen;
        this.lop = lop;
        this.mssv = mssv;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}