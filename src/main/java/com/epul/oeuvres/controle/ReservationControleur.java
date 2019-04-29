package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.dao.ServiceAdherent;
import com.epul.oeuvres.dao.ServiceOeuvreVente;
import com.epul.oeuvres.dao.ServiceReservationVente;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ReservationEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;



@Controller
public class ReservationControleur {

    @RequestMapping(value = "reserverOeuvreVente.htm")
    public ModelAndView formReserverOeuvreVente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Service unService = new Service();
        ServiceOeuvreVente serviceV = new ServiceOeuvreVente();
        ServiceReservationVente serviceRV = new ServiceReservationVente();
        String idO = request.getParameter("idO");
        OeuvreventeEntity oeuvre;

        if(idO==null){
            oeuvre = serviceV.oeuvreVenteByID(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("action", "insererReservationVente.htm");
            request.setAttribute("title", "Reservation d'une oeuvre en vente");
        }else{
            oeuvre= serviceV.oeuvreVenteByID(Integer.parseInt(idO));
            ReservationEntity reservation = serviceRV.reservationVenteByID(Integer.parseInt(idO));
            request.setAttribute("action", "modifierReservationVente.htm");
            request.setAttribute("date", reservation.getDateReservation());
            request.setAttribute("statut", reservation.getStatut());
            request.setAttribute("title", "Modification d'une reservation d'une oeuvre en vente");
        }

        request.setAttribute("id", oeuvre.getIdOeuvrevente());
        request.setAttribute("titre", oeuvre.getTitreOeuvrevente());
        request.setAttribute("prix", oeuvre.getPrixOeuvrevente());
        request.setAttribute("users", unService.getUsers());

        return new ModelAndView("vues/formReservationVente");
    }

    @RequestMapping(value = "listerReservationsVentes.htm")
    public ModelAndView listerReservationsVentes(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServiceReservationVente unService = new ServiceReservationVente();
        request.setAttribute("reservations", unService.getReservationsVentes());
        request.setAttribute("title", "Liste des reservations");

        return new ModelAndView("vues/listeReservationsVente");
    }

    @RequestMapping(value = "supprimerReservationVente.htm")
    public ModelAndView supprimerReservationsVentes(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServiceReservationVente unService = new ServiceReservationVente();
        unService.removeReservationvente(Integer.parseInt(request.getParameter("idO")));

        return listerReservationsVentes(request, response);
    }

    @RequestMapping(value = "modifierReservationVente.htm")
    public ModelAndView modifierReservationsVentes(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServiceReservationVente unService = new ServiceReservationVente();
        ServiceAdherent serviceA = new ServiceAdherent();
        ServiceOeuvreVente serviceV = new ServiceOeuvreVente();
        ReservationEntity reservation = new ReservationEntity();
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtdate"));
        java.sql.Date dateF = new java.sql.Date(date.getTime());

        reservation.setIdOeuvrevente(Integer.parseInt(request.getParameter("id")));
        reservation.setStatut(request.getParameter("txtStatut"));
        reservation.setIdAdherent(Integer.parseInt(request.getParameter("txtAdherent")));
        reservation.setDateReservation(dateF);
        reservation.setOeuvreventeByIdOeuvrevente(serviceV.oeuvreVenteByID(Integer.parseInt(request.getParameter("id"))));
        reservation.setAdherentByIdAdherent(serviceA.adherentById(Integer.parseInt(request.getParameter("txtAdherent"))));
        unService.updateReservationOeuvreVente(reservation);

        return listerReservationsVentes(request, response);
    }

    @RequestMapping(value = "insererReservationVente.htm")
    public ModelAndView insererReservationVente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtdate"));
        java.sql.Date dateF = new java.sql.Date(date.getTime());
        StackTraceElement[]  reponse=null;
        try {
            ServiceReservationVente unService = new ServiceReservationVente();
            ServiceAdherent serviceA = new ServiceAdherent();
            ServiceOeuvreVente serviceV = new ServiceOeuvreVente();
            ReservationEntity reservation = new ReservationEntity();
            reservation.setOeuvreventeByIdOeuvrevente(serviceV.oeuvreVenteByID(Integer.parseInt(request.getParameter("id"))));
            reservation.setIdAdherent(Integer.parseInt(request.getParameter("txtAdherent")));
            reservation.setIdOeuvrevente(Integer.parseInt(request.getParameter("id")));
            reservation.setStatut("en attente");
            reservation.setAdherentByIdAdherent(serviceA.adherentById(Integer.parseInt(request.getParameter("txtAdherent"))));
            reservation.setDateReservation(dateF);

            reponse = unService.reservationOeuvreVente(reservation);
            request.setAttribute("ajout", true);
        } catch (MonException e) {
            reponse = e.getStackTrace();
        }

        request.setAttribute("reponse", reponse);
        return this.listerReservationsVentes(request, response);
    }

}
