package saif.rest.restapi.Servlets;


import saif.rest.restapi.DAO.Students;
import saif.rest.restapi.Log.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/logout.do")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try{
            Students student = (Students) req.getSession().getAttribute("user");
            Logger.getLogger().addLog("User has logout " + " " + student.getId());
            req.getSession().removeAttribute("user");
            req.getSession().removeAttribute("allCourses");
            resp.setHeader("Cache-control","no-cache, no-store , must-revalidate");
            req.getSession().invalidate();
            resp.sendRedirect("login.do");
        }catch (Exception e){
            Logger.getLogger().addLog("Something Went Bad!! " + " " + e.getMessage());
        }

    }

}
