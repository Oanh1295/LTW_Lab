package dao;

import context.DBContext1;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Lấy toàn bộ danh sách sinh viên
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();

        String sql = """
                SELECT id, ho_ten, lop, mssv, email
                FROM students
                ORDER BY id DESC
                """;

        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("ho_ten"),
                        rs.getString("lop"),
                        rs.getString("mssv"),
                        rs.getString("email")
                );

                list.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy một sinh viên theo id
    public Student getStudentById(int id) {
        String sql = """
                SELECT id, ho_ten, lop, mssv, email
                FROM students
                WHERE id = ?
                """;

        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("ho_ten"),
                            rs.getString("lop"),
                            rs.getString("mssv"),
                            rs.getString("email")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Thêm sinh viên
    public boolean insertStudent(Student student) {
        String sql = """
                INSERT INTO students(ho_ten, lop, mssv, email)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, student.getHoTen());
            ps.setString(2, student.getLop());
            ps.setString(3, student.getMssv());
            ps.setString(4, student.getEmail());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật sinh viên
    public boolean updateStudent(Student student) {
        String sql = """
                UPDATE students
                SET ho_ten = ?, lop = ?, mssv = ?, email = ?
                WHERE id = ?
                """;

        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, student.getHoTen());
            ps.setString(2, student.getLop());
            ps.setString(3, student.getMssv());
            ps.setString(4, student.getEmail());
            ps.setInt(5, student.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Xóa sinh viên
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Kiểm tra MSSV đã tồn tại chưa
    public boolean isMssvExists(String mssv) {
        String sql = "SELECT id FROM students WHERE mssv = ?";

        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, mssv);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Kiểm tra email đã tồn tại chưa
    public boolean isEmailExists(String email) {
        String sql = "SELECT id FROM students WHERE email = ?";

        try (
                Connection conn = new DBContext1().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}