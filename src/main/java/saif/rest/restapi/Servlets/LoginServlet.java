package saif.rest.restapi.Servlets;

import saif.rest.restapi.DAO.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        saif.rest.restapi.Services.Students ss = new saif.rest.restapi.Services.Students();

        try {
            Students student =ss.getUser(email);
            if(student != null){
                if(ss.checkPass(password,student.getPassword())){
                    resp.setStatus(200);
                    req.setAttribute("user", student);
                    req.getSession().setAttribute("user", student);
                    req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req,resp);
                }else{
                    resp.setStatus(401);
                    req.setAttribute("errorMessage", "Invalid Credentials!! Check your email or password");
                    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
                }
            }else{
                resp.setStatus(401);
                req.setAttribute("errorMessage", "Invalid Credentials!! Check your email or password");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            resp.setStatus(500);
            req.setAttribute("errorMessage", "Something went Bad!! Please try again");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }

    }
}
