package com.example.web.hibernate.servlet;

import com.example.web.hibernate.Dao.AutoDao;
import com.example.web.hibernate.entity.Auto;
import com.example.web.hibernate.entity.User;
import com.example.web.hibernate.Dao.UserDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
    UserDao userDao=new UserDao();
    AutoDao autoDao=new AutoDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();


        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        User user=userDao.getUserByUsername(userName);
        HttpSession session=request.getSession(true);

        if(user!=null&&password.equals(user.getPassword())){
            //out.print("Welcome, "+userName);
            session.setAttribute("username",userName);

            if(user.isAdmin()){
                session.setAttribute("admin",true);
                request.setAttribute("theCommand", "LIST");
                response.sendRedirect("/UserControllerServlet");
                //request.getRequestDispatcher("user-list.jsp").include(request, response);
            }
            else{

                session.setAttribute("admin",false);
                request.getRequestDispatcher("user-form.jsp").include(request, response);
            }
        }
        else{
            out.print("Sorry, username or password error!");
            request.getRequestDispatcher("login-form.jsp").include(request, response);
        }
        out.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        userDao.saveUser(new User("admin","admin"," "," "," ",true));
        userDao.saveUser(new User("gagg","admin"," "," "," ",false));
        autoDao.saveAuto(new Auto("honda","civic",0,"da907ve","berlina"));

    }
}
