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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/PrenotazioneControllerServlet")
public class PrenotazioneControllerServlet extends HttpServlet {
    PrenotazioneDao prenotazioneDao=new PrenotazioneDao();
    UserDao userDao=new UserDao();
    AutoDao autoDao=new AutoDao();



    public PrenotazioneControllerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            String theCommand=req.getParameter("command");
            if (theCommand==null){
                theCommand="LIST";
            }
            switch (theCommand){
                case "LIST":listPrenotazioni(req,resp);break;
                case "ADD":addPrenotazione(req,resp);break;
                case "LOAD":loadPrenotazione(req,resp);break;
                case "LOADADD":loadAddPrenotazione(req,resp);break;
                case "UPDATE":addPrenotazione(req,resp);break;
                case "DELETE":deletePrenotazione(req,resp);break;
                case "USERLIST":loadUserPrenotazioni(req,resp);break;
                case "APPROVE": approvePrenotazione(req,resp);break;
                case "DISAPPROVE":disapprovePrenotazione(req,resp);break;
                case "RESEARCH":researchPrenotazioni(req,resp);break;
                case "ERROR":{
                    RequestDispatcher dispatcher= req.getRequestDispatcher("/error-page.jsp");
                    dispatcher.forward(req,resp);
                }break;
                default:listPrenotazioni(req, resp);
            }
        } catch (Exception exception) {
            throw new ServletException(exception);
        }
    }

    private void researchPrenotazioni(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Prenotazione> prenotazioni;
        List<PrenotazioneRefactored> prenotazioneRefactoreds = new ArrayList<>();
        HttpSession session=req.getSession();
        prenotazioni=prenotazioneDao.getPrenotazioniByField(req.getParameter("key"),req.getParameter("researchField"));
        if(!prenotazioni.isEmpty()) {
            for (Prenotazione var :
                    prenotazioni) {
                String name=var.getUser().getUsername();
                String name1= (String) session.getAttribute("username");
                Boolean cond=(Boolean)session.getAttribute("admin");
                if ( name.equals( name1) || cond) {
                    PrenotazioneRefactored prenotazioneRefactored = new PrenotazioneRefactored(var);
                    prenotazioneRefactoreds.add(prenotazioneRefactored);
                }
            }

        }
        req.setAttribute("PRENOTAZIONI_LIST",prenotazioneRefactoreds);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/prenotazioni-list.jsp");
        dispatcher.forward(req,resp);
    }



    private void disapprovePrenotazione(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String prenotazioneId=req.getParameter("prenotazioneId");
        Prenotazione prenotazione=prenotazioneDao.getPrenotazione(prenotazioneId);
        prenotazione.setApprovata(false);
        prenotazioneDao.savePrenotazione(prenotazione);
        resp.sendRedirect("/PrenotazioneControllerServlet");
    }

    private void approvePrenotazione(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String prenotazioneId=req.getParameter("prenotazioneId");
        Prenotazione prenotazione=prenotazioneDao.getPrenotazione(prenotazioneId);
        prenotazione.setApprovata(true);
        prenotazioneDao.savePrenotazione(prenotazione);
        resp.sendRedirect("/PrenotazioneControllerServlet");
    }

    private void loadUserPrenotazioni(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Prenotazione> prenotazioni=prenotazioneDao.getPrenotazioniByUserId(Integer.valueOf(req.getParameter("userId")));
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


    private void loadAddPrenotazione(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        HttpSession session=req.getSession();
        req.setAttribute("username",session.getAttribute("username"));
        String autoId= req.getParameter("autoId");
        Auto auto=autoDao.getAuto(autoId);
        SimpleDateFormat str = new SimpleDateFormat("dd-MM-yyyy");
        String data=str.format(new Date());
        Date dt = str.parse(data);
        Prenotazione prenotazione=new Prenotazione(dt,dt,auto,userDao.getUserByUsername((String) session.getAttribute("username")));
        req.setAttribute("targa", auto.getTarga());
        PrenotazioneRefactored prenotazioneRefactored=new PrenotazioneRefactored(prenotazione);
        req.setAttribute("THE_PRENOTAZIONE", prenotazioneRefactored);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/update-prenotazione-form.jsp");
        dispatcher.forward(req,resp);
        return;
    }

    private void loadPrenotazione(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prenotazioneId=req.getParameter("prenotazioneId");
        Prenotazione prenotazione= prenotazioneDao.getPrenotazione(prenotazioneId);
        Date now=new Date(System.currentTimeMillis()+172800000);
        Date begin=prenotazione.getDataDiInizio();
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        if (now.after(begin)){
            RequestDispatcher dispatcher=req.getRequestDispatcher("/error-page.jsp");
            dispatcher.forward(req,resp);
        }
        PrenotazioneRefactored prenotazioneRefactored=new PrenotazioneRefactored(prenotazione);
        req.setAttribute("THE_PRENOTAZIONE", prenotazioneRefactored);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/update-prenotazione-form.jsp");
        dispatcher.forward(req,resp);
    }





    private void deletePrenotazione(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Prenotazione prenotazione= prenotazioneDao.getPrenotazione(req.getParameter("prenotazioneId"));
        Date now=new Date(System.currentTimeMillis()+172800000);
        Date begin=prenotazione.getDataDiInizio();
        if (now.after(begin)){
            RequestDispatcher dispatcher=req.getRequestDispatcher("/error-page.jsp");
            dispatcher.forward(req,resp);
        }
        prenotazioneDao.deletePrenotazione(prenotazione);
        listPrenotazioni(req,resp);

    }

    private void addPrenotazione(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Auto auto=autoDao.getAutoByTarga(req.getParameter("targa"));
        User user=userDao.getUserByUsername(req.getParameter("username"));
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        String temp=req.getParameter("dataDiInizio");
        Prenotazione prenotazione=null;
        try {
            prenotazione=new Prenotazione(data.parse(temp),data.parse(req.getParameter("dataDiFine")), auto, user);
            prenotazione.setApprovata(false);
            if(req.getParameter("prenotazioneId")!=null){
                prenotazione.setId(Integer.parseInt(req.getParameter("prenotazioneId")));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(prenotazione!=null&&prenotazione.getDataDiFine()!=null&&prenotazione.getDataDiInizio()!=null&&prenotazione.getAuto()!=null&&prenotazione.getUser()!=null) {
            Date now=new Date(System.currentTimeMillis()+172800000);
            Date begin=prenotazione.getDataDiInizio();
            List<Prenotazione> prenotazioniAuto=prenotazioneDao.getPrenotazioniByTarga(prenotazione.getAuto());
            Boolean flag=false;
            for (Prenotazione var :
                    prenotazioniAuto) {
                if (((prenotazione.getDataDiInizio().before(var.getDataDiFine()) && prenotazione.getDataDiInizio().after(var.getDataDiInizio()))
                        || (prenotazione.getDataDiFine().before(var.getDataDiFine()) && prenotazione.getDataDiFine().after(var.getDataDiInizio()))
                        || (prenotazione.getDataDiFine().equals(var.getDataDiFine()))
                        || (prenotazione.getDataDiFine().equals(var.getDataDiInizio()))
                        || (prenotazione.getDataDiInizio().equals(var.getDataDiFine()))
                        || (prenotazione.getDataDiInizio().equals(var.getDataDiInizio()))
                        || ((prenotazione.getDataDiInizio().before(var.getDataDiInizio()))&& (prenotazione.getDataDiFine().after(var.getDataDiFine()))))
                        && (prenotazione.getId()!= var.getId())) {
                    flag=true;
                }
            }
            if (now.after(begin)|| begin.after(prenotazione.getDataDiFine()) || flag){
                RequestDispatcher dispatcher=req.getRequestDispatcher("/error-page.jsp");
                dispatcher.forward(req,resp);
                return;
            }

            prenotazioneDao.savePrenotazione(prenotazione);
        }else{
            RequestDispatcher dispatcher= req.getRequestDispatcher("/error-page.jsp");
            dispatcher.forward(req,resp);
        }

        listPrenotazioni(req,resp);
    }



    private void listPrenotazioni(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Prenotazione> prenotazioni=new ArrayList<>();
        List<PrenotazioneRefactored> prenotazioneRefactoreds = new ArrayList<PrenotazioneRefactored>();
        HttpSession session=req.getSession();
        if((Boolean)session.getAttribute("admin")){
            prenotazioni=prenotazioneDao.getPrenotazioni();
        }else{
            int userId=userDao.getUserByUsername((String) session.getAttribute("username")).getId();
            List<Prenotazione> prenotazioniss= prenotazioneDao.getPrenotazioniByUserId(userId);
            if(prenotazioniss!=null){
                prenotazioni=prenotazioniss;
            }
        }
        if(!prenotazioni.isEmpty()) {
            for (Prenotazione var :
                    prenotazioni) {
                PrenotazioneRefactored prenotazioneRefactored = new PrenotazioneRefactored(var);
                prenotazioneRefactoreds.add(prenotazioneRefactored);
            }
        }
        req.setAttribute("PRENOTAZIONI_LIST",prenotazioneRefactoreds);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/prenotazioni-list.jsp");
        dispatcher.forward(req,resp);
    }

}
