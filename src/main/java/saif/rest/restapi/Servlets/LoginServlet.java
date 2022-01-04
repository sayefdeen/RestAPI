package saif.rest.restapi.Servlets;

import saif.rest.restapi.DAO.Students;
import saif.rest.restapi.Log.Logger;
import saif.rest.restapi.Services.StudentsService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try{
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }catch (Exception e){
            Logger.getLogger().addLog("Something Went Bad!! " + " " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        StudentsService ss = new StudentsService();

        try {
            Students student =ss.getUser(email);
            if(student != null){
                if(ss.checkPass(password,student.getPassword())){
                    resp.setStatus(200);
                    req.setAttribute("user", student);
                    req.getSession().setAttribute("user", student);
                    Logger.getLogger().addLog("User has logIn " + " " + student.getId());
                    req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req,resp);
                }else{
                    resp.setStatus(401);
                    req.setAttribute("errorMessage", "Invalid Credentials!! Check your email or password");
                    Logger.getLogger().addLog("Invalid Credentials" + " " + student.getId());
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
            Logger.getLogger().addLog("Something went Bad!!" + " " + e.getMessage());
            try{
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            }catch (Exception message){
                Logger.getLogger().addLog("Something Went Bad!! " + " " + message.getMessage());
            }
        }

    }
}
