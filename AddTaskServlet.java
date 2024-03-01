package servlets;

import Classes.DBManagerTasks;
import Classes.Tasks;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/addTask")
public class AddTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("desc");
        String deadline  = req.getParameter("deadline");

        Tasks tasks = new Tasks();
        tasks.setName(name);
        tasks.setDescription(description);
        tasks.setDeadlineDate(deadline);

        DBManagerTasks.addTask(tasks);
        resp.sendRedirect("/tasks");
    }
}
