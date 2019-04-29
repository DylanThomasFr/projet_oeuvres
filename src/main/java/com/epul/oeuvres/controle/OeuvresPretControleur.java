package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.ServiceOeuvrePret;
import com.epul.oeuvres.dao.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epul.oeuvres.dao.ServiceProprietaire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.epul.oeuvres.meserreurs.*;
import com.epul.oeuvres.metier.*;

@Controller
public class OeuvresPretControleur {

    @RequestMapping(value = "listerOeuvresPret.htm")
    public ModelAndView afficherLesOeuvresPret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage="";
        try {
            ServiceOeuvrePret unService = new ServiceOeuvrePret();
            request.setAttribute("mesOeuvres", unService.consulterListeOeuvresPretLibres());
            request.setAttribute("mesOeuvresR", unService.consulterListeOeuvresPretReservees());
            destinationPage = "vues/listerOeuvresPret";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        request.setAttribute("title", "Liste des oeuvres");
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierOeuvrePret.htm")
    public ModelAndView modifierOeuvresPret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage="";
        try {
            ServiceOeuvrePret unService = new ServiceOeuvrePret();
            ServiceProprietaire serviceP = new ServiceProprietaire();
            request.setAttribute("oeuvre", unService.oeuvrePretByID(Integer.parseInt(request.getParameter("id"))));
            request.setAttribute("mesProprietaires", serviceP.consulterListeProprietaires());
            destinationPage = "vues/listerOeuvresPret";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        request.setAttribute("title", "Modification d'une oeuvre de pret");
        return new ModelAndView("vues/formOeuvrePret");
    }

    @RequestMapping(value = "ajouterOeuvrePret.htm")
    public ModelAndView ajouterOeuvrePret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage="";
        try {
            ServiceProprietaire unService = new ServiceProprietaire();
            request.setAttribute("mesProprietaires", unService.consulterListeProprietaires());
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        request.setAttribute("title", "Ajout d'une oeuvre de pret");
        return new ModelAndView("vues/formOeuvrePret");
    }

    @RequestMapping(value = "changeOeuvrePret.htm")
    public ModelAndView changeOeuvrePret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage="";
        String id = request.getParameter("id");
        ServiceProprietaire unService = new ServiceProprietaire();
        ServiceOeuvrePret serviceP = new ServiceOeuvrePret();
        OeuvrepretEntity oeuvre = new OeuvrepretEntity();

        try {
            oeuvre.setTitreOeuvrepret(request.getParameter("txttitle"));
            oeuvre.setProprietaireByIdProprietaire(unService.proprietaireById(Integer.parseInt(request.getParameter("txtProprietaire"))));
            if(id==null){
                serviceP.insertOeuvrePret(oeuvre);
                request.setAttribute("ajout", true);
            }else{
                oeuvre.setIdOeuvrepret(Integer.parseInt(id));
                serviceP.updateOeuvrePret(oeuvre);
                request.setAttribute("modification", true);
            }
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return  afficherLesOeuvresPret(request, response);
    }

    @RequestMapping(value = "supprimerOeuvrePret.htm")
    public ModelAndView removeOeuvrePret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServiceOeuvrePret serviceP = new ServiceOeuvrePret();
        serviceP.removeOeuvrepret(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("suppression", true);

        return  afficherLesOeuvresPret(request, response);
    }

}
