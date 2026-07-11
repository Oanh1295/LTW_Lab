package servlet;

import dao.UserDAO;
import model.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.getRequestDispatcher("/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword =
                request.getParameter("confirmPassword");

        if (isBlank(fullname)
                || isBlank(username)
                || isBlank(email)
                || isBlank(password)
                || isBlank(confirmPassword)) {

            request.setAttribute(
                    "error",
                    "Vui lòng nhập đầy đủ thông tin."
            );

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
            return;
        }

        fullname = fullname.trim();
        username = username.trim();
        email = email.trim();

        if (!password.equals(confirmPassword)) {
            request.setAttribute(
                    "error",
                    "Mật khẩu xác nhận không khớp."
            );

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
            return;
        }

        if (password.length() < 6) {
            request.setAttribute(
                    "error",
                    "Mật khẩu phải có ít nhất 6 ký tự."
            );

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
            return;
        }

        if (userDAO.isUsernameExists(username)) {
            request.setAttribute(
                    "error",
                    "Tên đăng nhập đã tồn tại."
            );

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
            return;
        }

        if (userDAO.isEmailExists(email)) {
            request.setAttribute(
                    "error",
                    "Email đã được sử dụng."
            );

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
            return;
        }

        User user = new User(
                username,
                password,
                email,
                fullname
        );

        boolean success = userDAO.register(user);

        if (success) {
            request.setAttribute(
                    "registerSuccess",
                    true
            );

            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute(
                    "error",
                    "Đăng ký thất bại. Vui lòng thử lại."
            );

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}