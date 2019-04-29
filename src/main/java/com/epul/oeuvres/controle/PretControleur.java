package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.ServiceOeuvrePret;
import com.epul.oeuvres.dao.ServicePret;
import com.epul.oeuvres.dao.ServiceAdherent;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.PretEntity;
import com.epul.oeuvres.metier.ReservationEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Controller
public class PretControleur {

    ServicePret serviceP = new ServicePret();
    ServiceAdherent unService = new ServiceAdherent();
    ServiceOeuvrePret serviceOP = new ServiceOeuvrePret();

    @RequestMapping(value = "listerReservationsPret.htm")
    public ModelAndView listerReservationPret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("reservations", serviceP.getReservationsPret());

        return new ModelAndView("vues/listerReservationsPret");
    }

    @RequestMapping(value = "formReservationPret.htm")
    public ModelAndView formReservationPret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute("titre", request.getParameter("titre"));
        request.setAttribute("adherents", unService.consulterListeAdherents());
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("mindate", new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        request.setAttribute("title", "Reservation d'une oeuvre en vente");
        return new ModelAndView("vues/formPret");


    }

    @RequestMapping(value = "changeReservationPret.htm")
    public ModelAndView changeReservationPret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String act = request.getParameter("act");

        if(act==null){
            PretEntity pret= new PretEntity();
            pret.setIdOeuvrepret(Integer.parseInt(request.getParameter("id")));
            pret.setDateReservation(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            pret.setIdAdherent(Integer.parseInt(request.getParameter("txtAdherent")));
            pret.setAdherentByIdAdherent(unService.adherentById(Integer.parseInt(request.getParameter("txtAdherent"))));
            pret.setOeuvrepretByIdOeuvrepret(serviceOP.oeuvrePretByID(Integer.parseInt(request.getParameter("id"))));
            pret.setDateRendu(null);

            request.setAttribute("title", "Reservation d'une oeuvre en vente");
            serviceP.reservationOeuvrePret(pret);
            request.setAttribute("ajout", true);
            return this.listerReservationPret(request, response);
        }else{
            serviceP.updateReservationOeuvreVente(Integer.parseInt(request.getParameter("idR")));
            request.setAttribute("modification", true);
            return this.listerReservationPret(request, response);
        }
    }

    @RequestMapping(value = "supprimerPret.htm")
    public ModelAndView supprimerPret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idO = Integer.parseInt(request.getParameter("idO"));
        int idA = Integer.parseInt(request.getParameter("adherent"));
        String dateR = request.getParameter("dateR");
        java.sql.Date dateF=null;

        if(dateR!=null){
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateR);
            dateF = new java.sql.Date(date.getTime());
        }
        serviceP.removePret(idO, idA, dateF);

        request.setAttribute("suppression", true);
        request.setAttribute("title", "Liste des prets");
        return this.listerReservationPret(request, response);
    }
}
