package saif.rest.restapi.Servlets;

import saif.rest.restapi.DAO.Course;
import saif.rest.restapi.DAO.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/details.do")
public class CourseDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Students student = (Students) req.getSession().getAttribute("user");
        if(student == null){
            resp.setStatus(403);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }else{
            try {
                resp.setStatus(200);
                String courseId = req.getParameter("id");
                Course course = (Course) new saif.rest.restapi.Services.Course().get(courseId);
               req.setAttribute("courseDetails",course);
                req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req,resp);
            } catch (Exception e) {
                resp.setStatus(500);
                e.printStackTrace();
            }
        }
    }
}
