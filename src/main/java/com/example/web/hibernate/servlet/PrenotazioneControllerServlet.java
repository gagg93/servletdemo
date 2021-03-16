package com.example.web.hibernate.servlet;


import com.example.web.hibernate.Dao.AutoDao;
import com.example.web.hibernate.Dao.PrenotazioneDao;
import com.example.web.hibernate.Dao.UserDao;
import com.example.web.hibernate.entity.Auto;
import com.example.web.hibernate.entity.Prenotazione;
import com.example.web.hibernate.entity.User;
import com.example.web.hibernate.refactored.PrenotazioneRefactored;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/PrenotazioneControllerServlet")
public class PrenotazioneControllerServlet extends HttpServlet {
    PrenotazioneDao prenotazioneDao=new PrenotazioneDao();
    UserDao userDao=new UserDao();
    AutoDao autoDao=new AutoDao();



    public PrenotazioneControllerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String theCommand=req.getParameter("command");
            HttpSession session=req.getSession(false);
            if (theCommand==null){
                theCommand="LIST";
            }
            switch (theCommand){
                case "LIST":listPrenotazioni(req,resp);break;
                case "ADD":addPrenotazione(req,resp);break;
                case "LOAD":loadPrenotazione(req,resp);break;
                case "LOADADD":loadAddPrenotazione(req,resp);break;
                case "UPDATE":updatePrenotazione(req,resp);break;
                case "DELETE":deletePrenotazione(req,resp);break;
                case "USERLIST":loadUserPrenotazioni(req,resp);break;
                case "APPROVE": approvePrenotazione(req,resp);break;
                case "DISAPPROVE":disapprovePrenotazione(req,resp);break;
                default:listPrenotazioni(req, resp);
            }
        } catch (Exception exception) {
            throw new ServletException(exception);
        }
    }

    private void disapprovePrenotazione(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String prenotazioneId=req.getParameter("prenotazioneId");
        Prenotazione prenotazione=prenotazioneDao.getPrenotazione(prenotazioneId);
        prenotazione.setApprovata(false);
        prenotazioneDao.updatePrenotazione(prenotazione);
        resp.sendRedirect("/PrenotazioneControllerServlet");
    }

    private void approvePrenotazione(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String prenotazioneId=req.getParameter("prenotazioneId");
        Prenotazione prenotazione=prenotazioneDao.getPrenotazione(prenotazioneId);
        prenotazione.setApprovata(true);
        prenotazioneDao.updatePrenotazione(prenotazione);
        resp.sendRedirect("/PrenotazioneControllerServlet");
    }

    private void loadUserPrenotazioni(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Prenotazione> prenotazioni=prenotazioneDao.getPrenotazioni();
        List<PrenotazioneRefactored> prenotazioneRefactoreds = new ArrayList<PrenotazioneRefactored>();
        for (Prenotazione var :
                prenotazioni) {
            PrenotazioneRefactored prenotazioneRefactored=new PrenotazioneRefactored(var);
            int i=Integer.valueOf(req.getParameter("userId"));
            if(var.getUser().getId()==i){
                prenotazioneRefactoreds.add(prenotazioneRefactored);
            }
        }
        req.setAttribute("PRENOTAZIONI_LIST",prenotazioneRefactoreds);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/prenotazioni-list.jsp");
        dispatcher.forward(req,resp);
    }


    private void loadAddPrenotazione(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        req.setAttribute("username",session.getAttribute("username"));
        String autoId= req.getParameter("autoId");
        Auto auto=autoDao.getAuto(autoId);
        req.setAttribute("targa", auto.getTarga());
        RequestDispatcher dispatcher=req.getRequestDispatcher("/prenotazione-form.jsp");
        dispatcher.forward(req,resp);
        return;
    }

    private void loadPrenotazione(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prenotazioneId=req.getParameter("prenotazioneId");
        Prenotazione prenotazione= prenotazioneDao.getPrenotazione(prenotazioneId);
        PrenotazioneRefactored prenotazioneRefactored=new PrenotazioneRefactored(prenotazione);
        req.setAttribute("THE_PRENOTAZIONE", prenotazioneRefactored);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/update-prenotazione-form.jsp");
        dispatcher.forward(req,resp);
    }



    private void updatePrenotazione(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Auto auto = autoDao.getAutoByTarga(req.getParameter("targa"));
        User user = userDao.getUserByUsername(req.getParameter("username"));
        Prenotazione prenotazione = new Prenotazione(req.getParameter("data_di_inizio"), req.getParameter("data_di_fine"), auto, user);
        prenotazione.setApprovata(false);
        prenotazione.setId(Integer.parseInt(req.getParameter("prenotazioneId")));
        prenotazioneDao.updatePrenotazione(prenotazione);
        listPrenotazioni(req, resp);
    }

    private void deletePrenotazione(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Prenotazione prenotazione= prenotazioneDao.getPrenotazione(req.getParameter("prenotazioneId"));
        prenotazioneDao.deletePrenotazione(prenotazione);
        listPrenotazioni(req,resp);

    }

    private void addPrenotazione(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Auto auto=autoDao.getAutoByTarga(req.getParameter("targa"));
        User user=userDao.getUserByUsername(req.getParameter("username"));
        Prenotazione prenotazione=new Prenotazione(req.getParameter("data_di_inizio"),req.getParameter("data_di_fine"), auto, user);
        prenotazione.setApprovata(false);
        prenotazioneDao.savePrenotazione(prenotazione);
        /*List<Prenotazione> prenotazioni;
        prenotazioni=prenotazioneDao.getPrenotazioneByUserId(user.getId());
        user.setPrenotazioni(prenotazioni);*/
        listPrenotazioni(req,resp);
    }



    private void listPrenotazioni(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Prenotazione> prenotazioni=prenotazioneDao.getPrenotazioni();
        List<PrenotazioneRefactored> prenotazioneRefactoreds = new ArrayList<PrenotazioneRefactored>();
        for (Prenotazione var :
                prenotazioni) {
            PrenotazioneRefactored prenotazioneRefactored=new PrenotazioneRefactored(var);
            prenotazioneRefactoreds.add(prenotazioneRefactored);
        }
        req.setAttribute("PRENOTAZIONI_LIST",prenotazioneRefactoreds);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/prenotazioni-list.jsp");
        dispatcher.forward(req,resp);
    }

}
