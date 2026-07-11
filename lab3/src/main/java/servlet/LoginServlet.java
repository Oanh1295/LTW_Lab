package servlet;

import dao.UserDAO;
import model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("account") != null) {
            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }
        request.getRequestDispatcher("login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isBlank(username) || isBlank(password)) {
            request.setAttribute(
                    "error",
                    "Vui lòng nhập tên đăng nhập và mật khẩu."
            );

            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
            return;
        }

        username = username.trim();

        User user = userDAO.login(username, password);

        if (user != null) {
            HttpSession session = request.getSession();

            session.setAttribute("account", user);
            session.setMaxInactiveInterval(30 * 60);

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute(
                    "error",
                    "Tên đăng nhập hoặc mật khẩu không đúng."
            );

            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}