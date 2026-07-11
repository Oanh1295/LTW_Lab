<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<%@ page import="model.User" %>
<%@ page import="dao.StudentDAO" %>

<%
    User account = (User) session.getAttribute("account");

    if (account == null) {
        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
        return;
    }

    StudentDAO studentDAO = new StudentDAO();
    List<Student> students = studentDAO.getAllStudents();

    Student editStudent = null;
    String editId = request.getParameter("editId");

    if (editId != null && !editId.trim().isEmpty()) {
        try {
            editStudent = studentDAO.getStudentById(
                    Integer.parseInt(editId)
            );
        } catch (NumberFormatException ignored) {
        }
    }

    String message = request.getParameter("message");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Quản lý sinh viên</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/styles.css">
</head>

<body class="student-page">

<div class="main-container">

    <!-- TIÊU ĐỀ -->
    <div class="top-bar">

        <div class="top-bar-info">
            <h1>HỆ THỐNG QUẢN LÝ SINH VIÊN</h1>

            <p>
                Xin chào,
                <strong><%= account.getFullname() %></strong>
            </p>
        </div>

        <a class="btn btn-danger"
            href="<%= request.getContextPath() %>/logout">
            Đăng xuất
        </a>

    </div>

    <!-- THÔNG BÁO -->
    <% if ("add-success".equals(message)) { %>
        <div class="alert alert-success">
            Thêm sinh viên thành công.
        </div>
    <% } %>

    <% if ("update-success".equals(message)) { %>
        <div class="alert alert-success">
            Cập nhật sinh viên thành công.
        </div>
    <% } %>

    <% if ("delete-success".equals(message)) { %>
        <div class="alert alert-success">
            Xóa sinh viên thành công.
        </div>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <!-- FORM THÊM / SỬA SINH VIÊN -->
    <section class="panel">

        <div class="panel-title panel-title-green">
            <%= editStudent == null
                    ? "THÊM SINH VIÊN MỚI"
                    : "CẬP NHẬT SINH VIÊN" %>
        </div>

        <form action="<%= request.getContextPath() %>/student"
              method="post"
              class="student-form"
              onsubmit="return validateStudentForm();">

            <input type="hidden"
                   name="action"
                   value="<%= editStudent == null ? "add" : "update" %>">

            <% if (editStudent != null) { %>
                <input type="hidden"
                       name="id"
                       value="<%= editStudent.getId() %>">
            <% } %>

            <div class="student-field">
                <label for="hoTen">Họ và tên</label>

                <input type="text"
                       id="hoTen"
                       name="hoTen"
                       placeholder="Nhập họ và tên"
                       value="<%= editStudent != null
                               ? editStudent.getHoTen()
                               : "" %>">
            </div>

            <div class="student-field">
                <label for="lop">Lớp</label>

                <input type="text"
                       id="lop"
                       name="lop"
                       placeholder="Nhập lớp"
                       value="<%= editStudent != null
                               ? editStudent.getLop()
                               : "" %>">
            </div>

            <div class="student-field">
                <label for="mssv">MSSV</label>

                <input type="text"
                       id="mssv"
                       name="mssv"
                       placeholder="Nhập mã số sinh viên"
                       value="<%= editStudent != null
                               ? editStudent.getMssv()
                               : "" %>">
            </div>

            <div class="student-field">
                <label for="email">Email</label>

                <input type="email"
                       id="email"
                       name="email"
                       placeholder="Nhập email"
                       value="<%= editStudent != null
                               ? editStudent.getEmail()
                               : "" %>">
            </div>

            <div class="student-form-actions">

                <button type="submit"
                        class="btn btn-success">
                    <%= editStudent == null ? "Thêm" : "Cập nhật" %>
                </button>

                <% if (editStudent != null) { %>
                    <a href="<%= request.getContextPath() %>/index.jsp"
                       class="btn btn-secondary">
                        Hủy
                    </a>
                <% } %>

            </div>

        </form>

    </section>

    <!-- BẢNG DANH SÁCH SINH VIÊN -->
    <section class="panel">

        <div class="panel-title panel-title-blue">
            DANH SÁCH SINH VIÊN
        </div>

        <div class="table-wrapper">

            <table class="student-table">

                <thead>
                <tr>
                    <th>MSSV</th>
                    <th>Họ và tên</th>
                    <th>Lớp</th>
                    <th>Email</th>
                    <th>Hành động</th>
                </tr>
                </thead>

                <tbody>

                <% if (students == null || students.isEmpty()) { %>

                    <tr>
                        <td colspan="5" class="empty-data">
                            Chưa có sinh viên nào.
                        </td>
                    </tr>

                <% } else { %>

                    <% for (Student student : students) { %>

                        <tr>
                            <td><%= student.getMssv() %></td>
                            <td><%= student.getHoTen() %></td>
                            <td><%= student.getLop() %></td>
                            <td><%= student.getEmail() %></td>

                            <td>
                                <div class="action-cell">

                                    <a class="btn btn-warning btn-small"
                                       href="<%= request.getContextPath() %>/index.jsp?editId=<%= student.getId() %>">
                                        Sửa
                                    </a>

                                    <a class="btn btn-danger btn-small"
                                       href="<%= request.getContextPath() %>/student?action=delete&id=<%= student.getId() %>"
                                       onclick="return confirmDelete();">
                                        Xóa
                                    </a>

                                </div>
                            </td>
                        </tr>

                    <% } %>

                <% } %>

                </tbody>

            </table>

        </div>

    </section>

</div>

<script src="<%= request.getContextPath() %>/js/script.js"></script>

</body>
</html>