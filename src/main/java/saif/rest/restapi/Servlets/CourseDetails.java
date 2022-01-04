package saif.rest.restapi.Servlets;

import saif.rest.restapi.DAO.Course;
import saif.rest.restapi.DAO.Students;
import saif.rest.restapi.Log.Logger;
import saif.rest.restapi.Services.CourseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/details.do")
public class CourseDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        Students student = (Students) req.getSession().getAttribute("user");
        if(student == null){
            try{
                resp.setStatus(403);
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            }catch (Exception e){
                Logger.getLogger().addLog("Something Went Bad!! " + " " + e.getMessage());
            }
        }else{
            try {
                resp.setStatus(200);
                String courseId = req.getParameter("id");
                Course course = (Course) new CourseService().get(courseId);
               req.setAttribute("courseDetails",course);
                req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req,resp);
            } catch (Exception e) {
                resp.setStatus(500);
                Logger.getLogger().addLog("Something Went Bad!! " + " " + e.getMessage());
            }
        }
    }
}
