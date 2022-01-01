package saif.rest.restapi.Servlets;

import saif.rest.restapi.DAO.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout.do")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("allCourses");
        resp.setHeader("Cache-control","no-cache, no-store , must-revalidate");
        req.getSession().invalidate();
        resp.sendRedirect("login.do");
    }

}
