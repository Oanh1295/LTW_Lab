<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HCMIU Registration Result</title>
    <style>
        /* Thêm chút CSS cho bảng đẹp và dễ nhìn hơn */
        table { border-collapse: collapse; width: 300px; margin-top: 15px; }
        th, td { border: 1px solid black; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>HCMIU Registration Result Data</h2>
    
    <%
        // Thiết lập UTF-8 để không lỗi font và lấy dữ liệu
        request.setCharacterEncoding("UTF-8");
        String fname = request.getParameter("fname");
        String sid = request.getParameter("sid");

        // Tránh lỗi null nếu truy cập trực tiếp
        if (fname == null) fname = "";
        if (sid == null) sid = "";
    %>

    <table>
        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>
        <tr>
            <td>First Name</td>
            <td><%= fname %></td>
        </tr>
        <tr>
            <td>Student ID</td>
            <td><%= sid %></td>
        </tr>
    </table>
    
    <br><br>
    <a href="HCMIU_RegisterForm.html">Back to Form</a>
</body>
</html>