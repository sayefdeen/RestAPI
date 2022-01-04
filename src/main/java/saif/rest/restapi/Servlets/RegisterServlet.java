package saif.rest.restapi.Servlets;

import saif.rest.restapi.DAO.Students;
import saif.rest.restapi.Log.Logger;
import saif.rest.restapi.Services.StudentsService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try{
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }catch (Exception e){
            Logger.getLogger().addLog("Something Went Bad!! " + " " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        StudentsService ss = new StudentsService();

        try {
            Students student = new Students(email,password,true);
            if (ss.add(student)){
                req.setAttribute("success", "Please Login with your account");
                Logger.getLogger().addLog("New user created" + " " + student.getId());
            }else{
                req.setAttribute("errorMessage", "Already Used Email");
                Logger.getLogger().addLog("User Already exists" + " " + student.getName());
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Something went Bad!!!!");
            Logger.getLogger().addLog("Something Went Bad!! " + " " + e.getMessage());
        }finally {
            try{
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            }catch (Exception e){
                Logger.getLogger().addLog("Something Went Bad!! " + " " + e.getMessage());
            }

        }
    }
}