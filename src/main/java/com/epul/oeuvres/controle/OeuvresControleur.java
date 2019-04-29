package com.epul.oeuvres.controle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epul.oeuvres.dao.ServiceOeuvreVente;
import com.epul.oeuvres.dao.ServiceProprietaire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.*;
import com.epul.oeuvres.metier.*;


@Controller
public class OeuvresControleur {

    @RequestMapping(value = "listerOeuvres.htm")
    public ModelAndView afficherLesOeuvres(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage="";
        try {
            // HttpSession session = request.getSession();
            ServiceOeuvreVente unService = new ServiceOeuvreVente();
            request.setAttribute("mesOeuvres", unService.consulterListeOeuvresVenteLibres());
            request.setAttribute("mesOeuvresR", unService.consulterListeOeuvresVenteReservees());
            destinationPage = "vues/listerOeuvres";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        request.setAttribute("title", "Liste des oeuvres");
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "ajouterOeuvreVente.htm")
    public ModelAndView ajouterOeuvreVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage="";
        try {
            ServiceProprietaire unService = new ServiceProprietaire();
            request.setAttribute("mesProprietaires", unService.consulterListeProprietaires());
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        request.setAttribute("title", "Ajout d'une oeuvre a vendre");
        return new ModelAndView("vues/ajoutOeuvreVente");
    }

    @RequestMapping(value = "insererOeuvreVente.htm")
    public ModelAndView insertOeuvreVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage="";
        try {
            ServiceProprietaire unService = new ServiceProprietaire();
            ServiceOeuvreVente serviceV = new ServiceOeuvreVente();
            OeuvreventeEntity oeuvre = new OeuvreventeEntity();
            oeuvre.setTitreOeuvrevente(request.getParameter("txttitle"));
            oeuvre.setPrixOeuvrevente(Double.parseDouble(request.getParameter("txtprice")));
            oeuvre.setEtatOeuvrevente("R");
            oeuvre.setProprietaireByIdProprietaire(unService.proprietaireById(Integer.parseInt(request.getParameter("txtProprietaire"))));
            serviceV.insertOeuvreVente(oeuvre);
            request.setAttribute("ajout", true);
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return this.afficherLesOeuvres(request, response);
    }


    @RequestMapping(value = "supprimerOeuvrevente.htm")
    public ModelAndView supprimerOeuvrevente(HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {

        ServiceOeuvreVente unService = new ServiceOeuvreVente();
        try {
            unService.removeOeuvrevente(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("suppression", true);
        } catch (Exception e) {
            String destinationPage = "vues/Erreur";
            request.setAttribute("suppression", false);
        }

        return this.afficherLesOeuvres(request, response);
    }



    @RequestMapping(value = "modifierOeuvreVente.htm")
    public ModelAndView modifierOeuvreVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage="";
        try {
            ServiceProprietaire unService = new ServiceProprietaire();
            ServiceOeuvreVente serviceV = new ServiceOeuvreVente();
            request.setAttribute("mesProprietaires", unService.consulterListeProprietaires());
            int id = Integer.parseInt(request.getParameter("id"));
            OeuvreventeEntity oeuvre = serviceV.oeuvreVenteByID(id);
            request.setAttribute("oeuvre", oeuvre);
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        request.setAttribute("title", "Modification d'une oeuvre de vente");
        return new ModelAndView("vues/modifierOeuvreVente");
    }

    @RequestMapping(value = "updateOeuvreVente.htm")
    public ModelAndView updateOeuvreVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServiceProprietaire unService = new ServiceProprietaire();
        ServiceOeuvreVente serviceV = new ServiceOeuvreVente();
        try {
            OeuvreventeEntity oeuvre = new OeuvreventeEntity();
            oeuvre.setTitreOeuvrevente(request.getParameter("txttitle"));
            oeuvre.setProprietaireByIdProprietaire(unService.proprietaireById(Integer.parseInt(request.getParameter("txtProprietaire"))));
            oeuvre.setPrixOeuvrevente(Double.parseDouble(request.getParameter("txtprice")));
            oeuvre.setEtatOeuvrevente("R");
            oeuvre.setIdOeuvrevente(Integer.parseInt(request.getParameter("id")));
            serviceV.updateOeuvreVente(oeuvre);
        } catch (Exception e) {
            request.setAttribute("modification", false);
            return this.afficherLesOeuvres(request, response);
        }

        request.setAttribute("modification", true);
        return this.afficherLesOeuvres(request, response);
    }

}
