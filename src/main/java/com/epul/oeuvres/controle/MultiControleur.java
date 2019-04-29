package com.epul.oeuvres.controle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epul.oeuvres.dao.ServiceAdherent;
import com.epul.oeuvres.utilitaires.FonctionsUtiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.*;
import com.epul.oeuvres.metier.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

///
/// Les méthode du contrôleur répondent à des sollicitations
/// des pages JSP

@Controller
public class MultiControleur {

	@RequestMapping(value = "listerAdherent.htm")
	public ModelAndView afficherLesStages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage;
		try {
			ServiceAdherent unService = new ServiceAdherent();
			request.setAttribute("mesAdherents", unService.consulterListeAdherents());
			destinationPage = "vues/listerAdherent";
		} catch (MonException e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "Erreur";

		}
        request.setAttribute("title", "Liste des adhérents");
		return new ModelAndView(destinationPage);
	}



	@RequestMapping(value = "insererAdherent.htm")
	public ModelAndView insererAdherent(HttpServletRequest request,
										HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			AdherentEntity unAdherent = new AdherentEntity();
			unAdherent.setNomAdherent(request.getParameter("txtnom"));
			unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
			unAdherent.setVilleAdherent(request.getParameter("txtville"));
			ServiceAdherent unService = new ServiceAdherent();
			unService.insertAdherent(unAdherent);
			request.setAttribute("ajout", true);
		} catch (Exception e) {
			request.setAttribute("ajout", false);
		}
        request.setAttribute("title", "Liste des adhérents");
		return this.afficherLesStages(request, response);
	}

	@RequestMapping(value = "ajouterAdherent.htm")
	public ModelAndView ajouterAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "";
		try {
			destinationPage = "vues/ajouterAdherent";
            request.setAttribute("title", "Ajouter un adhérent");
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());
			destinationPage = "vues/Erreur";

		}

		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "supprimerAdherent.htm")
	public ModelAndView supprimerAdherent(HttpServletRequest request,
										HttpServletResponse response) throws Exception {

		ServiceAdherent unService = new ServiceAdherent();
		try {
			unService.removeAdherent(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("suppression", true);
		} catch (Exception e) {
			String destinationPage = "vues/Erreur";
			request.setAttribute("suppression", false);
		}
        request.setAttribute("title", "Liste des adhérents");
		return this.afficherLesStages(request, response);
	}

	@RequestMapping(value = "modifierAdherent.htm")
	public ModelAndView modifierAdherent(HttpServletRequest request,
										  HttpServletResponse response) throws Exception {

		ServiceAdherent unService = new ServiceAdherent();
		try {
			AdherentEntity adherent = unService.adherentById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("adherent", adherent);
		} catch (Exception e) {
            request.setAttribute("title", "Liste des adhérents");
			return new ModelAndView("vues/listerAdherent");
		}
        request.setAttribute("title", "Modifier l'adhérent");
		return new ModelAndView("vues/modifierAdherent");
	}

    @RequestMapping(value = "updateAdherent.htm")
    public ModelAndView updateAdherent(HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        ServiceAdherent unService = new ServiceAdherent();
        try {
            AdherentEntity unAdherent = new AdherentEntity();
            unAdherent.setNomAdherent(request.getParameter("txtnom"));
            unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
            unAdherent.setVilleAdherent(request.getParameter("txtville"));
            unAdherent.setIdAdherent(Integer.parseInt(request.getParameter("id")));
            unService.updateAdherent(unAdherent);
        } catch (Exception e) {
            request.setAttribute("modification", false);
            request.setAttribute("title", "Liste des adhérents");
            return this.afficherLesStages(request, response);
        }

        request.setAttribute("modification", true);
        request.setAttribute("title", "Liste des adhérents");
        return this.afficherLesStages(request, response);
    }





	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "index.htm", method = RequestMethod.GET)
	public ModelAndView Afficheindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("title", "Accueil");
		return new ModelAndView("index");
	}

	// /
		// / Affichage de la page d'accueil
		// /
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public ModelAndView Afficheindex2(HttpServletRequest request, HttpServletResponse response) throws Exception {
            request.setAttribute("title", "Accueil");
			return new ModelAndView("index");
		}

	// /
	// / Affichage de la page d'accueil
	// /
	@RequestMapping(value = "erreur.htm", method = RequestMethod.GET)
	public ModelAndView AfficheErreur(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("vues/Erreur");
	}

}
