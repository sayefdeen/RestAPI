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


@WebServlet(urlPatterns = "/courses.do")
public class Courses extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Students student = (Students) req.getSession().getAttribute("user");
        if(student == null){
            resp.setStatus(403);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }else{
            try {
                resp.setStatus(200);
                ArrayList<Course> courseArrayList = new saif.rest.restapi.Services.Course().getCourses(student.getId());
                req.setAttribute("user", student);
                req.setAttribute("allCourses",courseArrayList);
                req.getSession().setAttribute("allCourses",courseArrayList);
                req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
