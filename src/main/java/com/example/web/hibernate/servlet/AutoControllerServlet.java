package com.example.web.hibernate.servlet;

import com.example.web.hibernate.Dao.AutoDao;
import com.example.web.hibernate.entity.Auto;
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

@WebServlet("/AutoControllerServlet")
public class AutoControllerServlet extends HttpServlet {
    AutoDao autoDao= new AutoDao();

    public AutoControllerServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String theCommand=req.getParameter("command");
            HttpSession session=req.getSession(false);
            if(session!= null){
                Boolean admin=(Boolean) session.getAttribute("admin");
                if(!admin&&theCommand!="LIST"){
                    theCommand="LIST";
                }
            }
            if (theCommand==null){
                theCommand="LIST";
            }
            switch (theCommand){
                case "LIST":listAutos(req,resp);break;
                case "ADD":addAuto(req,resp);break;
                case "LOAD":loadAuto(req,resp);break;
                case "UPDATE":updateAuto(req,resp);break;
                case "DELETE":deleteAuto(req,resp);break;
                default:listAutos(req, resp);
            }
        } catch (Exception exception) {
            throw new ServletException(exception);
        }
    }

    private void loadAuto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String autoId=req.getParameter("autoId");
        Auto auto= autoDao.getAuto(autoId);
        req.setAttribute("THE_AUTO", auto);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/update-auto-form.jsp");
        dispatcher.forward(req,resp);
    }



    private void updateAuto(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Auto auto=new Auto(req.getParameter("casaCostruttrice"),req.getParameter("modello"), Integer.parseInt(req.getParameter("annoImmatricolazione")), req.getParameter("targa"), req.getParameter("tipologia"));
        auto.setId(Integer.parseInt(req.getParameter("autoId")));
        autoDao.updateAuto(auto);
        listAutos(req,resp);
    }

    private void deleteAuto(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Auto auto=new Auto(req.getParameter("casaCostruttrice"),req.getParameter("modello"), Integer.parseInt(req.getParameter("annoImmatricolazione")), req.getParameter("targa"), req.getParameter("tipologia"));
        auto.setId(Integer.parseInt(req.getParameter("autoId")));
        autoDao.deleteAuto(auto);
        listAutos(req,resp);

    }

    private void addAuto(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Auto auto=new Auto(req.getParameter("casaCostruttrice"),req.getParameter("modello"), Integer.parseInt(req.getParameter("annoImmatricolazione")), req.getParameter("targa"), req.getParameter("tipologia"));

        Boolean unique=(autoDao.getAutoByTarga(auto.getTarga())==null);
        if(unique){
            autoDao.saveAuto(auto);
        }
        listAutos(req,resp);
    }



    private void listAutos(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Auto> autos=autoDao.getAutos();
        req.setAttribute("AUTO_LIST",autos);
        req.setAttribute("ADMIN",(Boolean)req.getSession(false).getAttribute("admin"));
        RequestDispatcher dispatcher= req.getRequestDispatcher("/auto-list.jsp");
        dispatcher.forward(req,resp);
    }

}
