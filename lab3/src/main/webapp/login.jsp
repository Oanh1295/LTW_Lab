<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("account") != null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Đăng nhập</title>

    <link rel="stylesheet"
      href="<%= request.getContextPath() %>/css/styles.css">
</head>

<body class="auth-page">

    <div class="auth-container">

        <div class="auth-card">

            <h1>Đăng nhập hệ thống</h1>

            <p class="auth-description">
                Hệ thống quản lý sinh viên
            </p>

            <% if (Boolean.TRUE.equals(request.getAttribute("registerSuccess"))) { %>
                <div class="alert alert-success">
                    Đăng ký tài khoản thành công. Vui lòng đăng nhập.
                </div>
            <% } %>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <form action="<%= request.getContextPath() %>/login"
                    method="post"
                    class="auth-form"
                    onsubmit="return validateLoginForm();">

                <div class="form-group">
                    <label for="username">Tên đăng nhập</label>

                    <input type="text"
                        id="username"
                        name="username"
                        placeholder="Nhập tên đăng nhập"
                        autocomplete="username">
                </div>

                <div class="form-group">
                    <label for="password">Mật khẩu</label>

                    <div class="password-wrapper">

                        <input type="password"
                            id="password"
                            name="password"
                            placeholder="Nhập mật khẩu"
                            autocomplete="current-password">

                        <button type="button"
                                class="toggle-password"
                                onclick="togglePassword('password', this)">
                            Hiện
                        </button>

                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-full">
                    Đăng nhập
                </button>

            </form>

            <p class="auth-link">
                Chưa có tài khoản?
                <a href="<%= request.getContextPath() %>/register">
                    Đăng ký ngay
                </a>
            </p>

            <p class="demo-account">
                Tài khoản mẫu: <strong>admin</strong> /
                <strong>123456</strong>
            </p>

        </div>

    </div>

    <script src="<%= request.getContextPath() %>/js/script.js"></script>

</body>
</html>