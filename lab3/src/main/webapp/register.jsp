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

    <title>Đăng ký tài khoản</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/styles.css">
</head>

<body class="auth-page">

    <div class="auth-container">

        <div class="auth-card">

            <h1>Đăng ký tài khoản</h1>

            <p class="auth-description">
                Tạo tài khoản để sử dụng hệ thống quản lý sinh viên
            </p>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <form action="<%= request.getContextPath() %>/register"
                    method="post"
                    class="auth-form"
                    onsubmit="return validateRegisterForm();">

                <div class="form-group">
                    <label for="fullname">Họ và tên</label>

                    <input type="text"
                        id="fullname"
                        name="fullname"
                        placeholder="Nhập họ và tên"
                        value="<%= request.getParameter("fullname") != null
                                ? request.getParameter("fullname") : "" %>">
                </div>

                <div class="form-group">
                    <label for="username">Tên đăng nhập</label>

                    <input type="text"
                        id="username"
                        name="username"
                        placeholder="Nhập tên đăng nhập"
                        value="<%= request.getParameter("username") != null
                                ? request.getParameter("username") : "" %>"
                        autocomplete="username">
                </div>

                <div class="form-group">
                    <label for="email">Email</label>

                    <input type="email"
                        id="email"
                        name="email"
                        placeholder="Nhập email"
                        value="<%= request.getParameter("email") != null
                                ? request.getParameter("email") : "" %>"
                        autocomplete="email">
                </div>

                <div class="form-group">
                    <label for="password">Mật khẩu</label>

                    <div class="password-wrapper">

                        <input type="password"
                            id="password"
                            name="password"
                            placeholder="Nhập mật khẩu"
                            autocomplete="new-password">

                        <button type="button"
                                class="toggle-password"
                                onclick="togglePassword('password', this)">
                            Hiện
                        </button>

                    </div>
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Xác nhận mật khẩu</label>

                    <div class="password-wrapper">

                        <input type="password"
                            id="confirmPassword"
                            name="confirmPassword"
                            placeholder="Nhập lại mật khẩu"
                            autocomplete="new-password">

                        <button type="button"
                                class="toggle-password"
                                onclick="togglePassword('confirmPassword', this)">
                            Hiện
                        </button>

                    </div>
                </div>

                <button type="submit" class="btn btn-success btn-full">
                    Đăng ký
                </button>

            </form>

            <p class="auth-link">
                Đã có tài khoản?
                <a href="<%= request.getContextPath() %>/login">
                    Đăng nhập
                </a>
            </p>

        </div>

    </div>

    <script src="<%= request.getContextPath() %>/js/script.js"></script>

</body>
</html>