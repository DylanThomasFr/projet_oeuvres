package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.UtilisateurEntity;
import com.epul.oeuvres.utilitaires.FonctionsUtiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;


@Controller
public class Authentification {

    ///
    /// Login
    ////


    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView pageLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("title", "Connexion");
        return new ModelAndView("vues/formLogin");
    }

    ///
    //
    //// Contrôle Login
    ///
    ////
    @RequestMapping(value = "controleLogin.htm")
    public ModelAndView controleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        {
            String login = request.getParameter("login");
            String pwd = request.getParameter("pwd");
            String message ="";
            try {

                Service unService = new Service();
                UtilisateurEntity unUtilisateur = unService.getUtilisateur(login);
                if (unUtilisateur != null) {
                    try {
                        String pwdmd5 = FonctionsUtiles.md5(pwd);
                        if (unUtilisateur.getMotPasse().equals(pwdmd5)) {
                            HttpSession session = request.getSession();
                            session.setAttribute("id", unUtilisateur.getNumUtil());
                            request.setAttribute("title", "Accueil");
                            destinationPage = "/index";
                        } else {
                            message = "mot de passe erroné";
                            request.setAttribute("message", message);
                            request.setAttribute("title", "Connexion");
                            destinationPage = "/vues/formLogin";
                        }
                    } catch (NoSuchAlgorithmException e) {
                        request.setAttribute("MesErreurs", e.getMessage());
                        destinationPage = "/vues/Erreur";
                    }
                } else {
                    message = "login erroné";
                    request.setAttribute("message", message);
                    request.setAttribute("title", "Connexion");
                    destinationPage = "/vues/formLogin";
                }
            } catch (MonException e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur";
            }
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "deconnect.htm")
    public ModelAndView deconnect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        request.setAttribute("title", "Accueil");
        session.setAttribute("id", null);
        return new ModelAndView("index");
    }
}
