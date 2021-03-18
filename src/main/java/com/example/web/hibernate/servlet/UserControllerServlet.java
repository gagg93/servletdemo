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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                    theCommand="LOAD";
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
                case "RESEARCH":researchUser(req,resp);break;
                default:listUsers(req, resp);
            }
        } catch (Exception exception) {
            throw new ServletException(exception);
        }
    }

    private void researchUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new ArrayList<>();

        RequestDispatcher dispatcher= req.getRequestDispatcher("/user-list.jsp");


        switch (req.getParameter("researchField")){
            case "Username":{
                User user=userDao.getUserByUsername(req.getParameter("key"));
                users.add(user);
            }break;
            case "Nome":users=userDao.getUserByNome(req.getParameter("key"));break;
            case "Cognome":users=userDao.getUserByCognome(req.getParameter("key"));break;
            case "Data di nascita":users=userDao.getUserByData(req.getParameter("key"));break;
            default:users=userDao.getUsers();
        }
        req.setAttribute("USER_LIST",users);
        dispatcher.forward(req,resp);
    }

    private void researchUserByUsername(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void loadUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId=req.getParameter("userId");
        User user;
        if (userId==null){
            HttpSession session=req.getSession(true);
            user = userDao.getUserByUsername((String) session.getAttribute("username"));
        }else{
            user = userDao.getUser(userId);
        }
        req.setAttribute("THE_USER", user);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/update-user-form.jsp");
        dispatcher.forward(req,resp);
    }



    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat();
        User user=null;
        try {
            user = new User(req.getParameter("username"), req.getParameter("password"), req.getParameter("nome"), req.getParameter("cognome"), data.parse(req.getParameter("data")), false);
            user.setId(Integer.parseInt(req.getParameter("userId")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(user!=null&&user.getUsername().trim()!=null&&user.getNome().trim()!=null&&user.getCognome().trim()!=null&&user.getData_di_nascita()!=null) {
            userDao.updateUser(user);
        }else{
            RequestDispatcher dispatcher= req.getRequestDispatcher("/error-page.jsp");
            dispatcher.forward(req,resp);
        }
        listUsers(req,resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat();
        User user=new User(req.getParameter("username"),req.getParameter("password"), req.getParameter("nome"), req.getParameter("cognome"), data.parse(req.getParameter("data")), false);
        user.setId(Integer.parseInt(req.getParameter("userId")));
        userDao.deleteUser(user);
        listUsers(req,resp);

    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        User user=null;
        try {
            user = new User(req.getParameter("username"), req.getParameter("password"), req.getParameter("nome"), req.getParameter("cognome"), data.parse(req.getParameter("data")), false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(user!=null&&user.getUsername().trim()!=null&&user.getNome().trim()!=null&&user.getCognome().trim()!=null&&user.getData_di_nascita()!=null&&userDao.getUserByUsername(user.getUsername()) == null) {
                userDao.saveUser(user);
        }else{
            RequestDispatcher dispatcher= req.getRequestDispatcher("/error-page.jsp");
            dispatcher.forward(req,resp);
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
