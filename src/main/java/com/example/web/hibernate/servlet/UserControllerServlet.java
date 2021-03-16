package com.example.web.hibernate.servlet;

import com.example.web.hibernate.Dao.UserDao;
import com.example.web.hibernate.entity.User;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet  {
    UserDao userDao=new UserDao();



    public UserControllerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String theCommand=req.getParameter("command");
            HttpSession session=req.getSession(false);
            if(session!= null){
                Boolean admin=(Boolean) session.getAttribute("admin");
                if(!admin){
                    RequestDispatcher dispatcher=req.getRequestDispatcher("/user-form.jsp");
                    dispatcher.forward(req,resp);
                }
            }
            if (theCommand==null){
                theCommand="LIST";
            }
            switch (theCommand){
                case "LIST":listUsers(req,resp);break;
                case "ADD":addUser(req,resp);break;
                case "LOAD":loadUser(req,resp);break;
                case "UPDATE":updateUser(req,resp);break;
                case "DELETE":deleteUser(req,resp);break;
                default:listUsers(req, resp);
            }
        } catch (Exception exception) {
            throw new ServletException(exception);
        }
    }

    private void loadUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId=req.getParameter("userId");
        User user= userDao.getUser(userId);
        req.setAttribute("THE_USER", user);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/update-user-form.jsp");
        dispatcher.forward(req,resp);
    }



    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user=new User(req.getParameter("username"),req.getParameter("password"), req.getParameter("nome"), req.getParameter("cognome"), req.getParameter("data"), false);
        user.setId(Integer.parseInt(req.getParameter("userId")));
        userDao.updateUser(user);
        listUsers(req,resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user=new User(req.getParameter("username"),req.getParameter("password"), req.getParameter("nome"), req.getParameter("cognome"), req.getParameter("data"), false);
        user.setId(Integer.parseInt(req.getParameter("userId")));
        userDao.deleteUser(user);
        listUsers(req,resp);

    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user=new User(req.getParameter("username"),req.getParameter("password"), req.getParameter("nome"), req.getParameter("cognome"), req.getParameter("data"), false);
        Boolean unique=(userDao.getUserByUsername(user.getUsername())==null);
        if(unique){
            userDao.saveUser(user);
        }
        resp.sendRedirect("/UserControllerServlet");
    }



    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<User> users=userDao.getUsers();
        req.setAttribute("USER_LIST",users);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/user-list.jsp");

        dispatcher.forward(req,resp);
    }

}
