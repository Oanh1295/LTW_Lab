package servlet;

import dao.StudentDAO;
import model.Student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
            return;
        }

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteStudent(request, response);
            return;
        }

        request.getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
        ) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
            return;
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateStudent(request, response);
        } else {
            insertStudent(request, response);
        }
    }

    private void insertStudent(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String hoTen = request.getParameter("hoTen");
        String lop = request.getParameter("lop");
        String mssv = request.getParameter("mssv");
        String email = request.getParameter("email");

        if (isBlank(hoTen)
                || isBlank(lop)
                || isBlank(mssv)
                || isBlank(email)) {

            request.setAttribute(
                    "error",
                    "Vui lòng nhập đầy đủ thông tin sinh viên."
            );

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }

        hoTen = hoTen.trim();
        lop = lop.trim();
        mssv = mssv.trim();
        email = email.trim();

        if (studentDAO.isMssvExists(mssv)) {
            request.setAttribute(
                    "error",
                    "MSSV đã tồn tại."
            );

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }

        if (studentDAO.isEmailExists(email)) {
            request.setAttribute(
                    "error",
                    "Email sinh viên đã tồn tại."
            );

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }

        Student student = new Student(
                0,
                hoTen,
                lop,
                mssv,
                email
        );

        boolean success = studentDAO.insertStudent(student);

        if (success) {
            request.setAttribute(
                    "message",
                    "add-success"
            );
        } else {
            request.setAttribute(
                    "error",
                    "Thêm sinh viên thất bại."
            );
        }

        request.getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    private void updateStudent(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String idValue = request.getParameter("id");
        String hoTen = request.getParameter("hoTen");
        String lop = request.getParameter("lop");
        String mssv = request.getParameter("mssv");
        String email = request.getParameter("email");

        if (isBlank(idValue)
                || isBlank(hoTen)
                || isBlank(lop)
                || isBlank(mssv)
                || isBlank(email)) {

            request.setAttribute(
                    "error",
                    "Vui lòng nhập đầy đủ thông tin sinh viên."
            );

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idValue);

            Student student = new Student(
                    id,
                    hoTen.trim(),
                    lop.trim(),
                    mssv.trim(),
                    email.trim()
            );

            boolean success =
                    studentDAO.updateStudent(student);

            if (success) {
                request.setAttribute(
                        "message",
                        "update-success"
                );
            } else {
                request.setAttribute(
                        "error",
                        "Cập nhật sinh viên thất bại."
                );
            }

        } catch (NumberFormatException e) {
            request.setAttribute(
                    "error",
                    "ID sinh viên không hợp lệ."
            );
        }

        request.getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    private void deleteStudent(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String idValue = request.getParameter("id");

        if (isBlank(idValue)) {
            request.setAttribute(
                    "error",
                    "ID sinh viên không hợp lệ."
            );

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idValue);

            boolean success =
                    studentDAO.deleteStudent(id);

            if (success) {
                request.setAttribute(
                        "message",
                        "delete-success"
                );
            } else {
                request.setAttribute(
                        "error",
                        "Xóa sinh viên thất bại."
                );
            }

        } catch (NumberFormatException e) {
            request.setAttribute(
                    "error",
                    "ID sinh viên không hợp lệ."
            );
        }

        request.getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}