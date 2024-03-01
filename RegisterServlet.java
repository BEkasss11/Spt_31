package servlets;

import Classes.DBManagerTasks;
import Classes.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Task2Web/register.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repPassword = req.getParameter("repPassword");
        String fullName = req.getParameter("fullName");

        if (!password.equals(repPassword)) {
            resp.sendRedirect("/register?passwordFail");
            return;
        }

        Users newUser = new Users();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setFullName(fullName);

        if (DBManagerTasks.addUser(newUser)) {
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/register?registrationFail");
        }
    }
}
