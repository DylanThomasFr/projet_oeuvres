package com.epul.oeuvres.dao;

import com.epul.oeuvres.controle.OeuvresControleur;
import com.epul.oeuvres.meserreurs.MonException;
import java.util.*;
import javax.persistence.*;
import com.epul.oeuvres.metier.*;


import javax.persistence.EntityTransaction;

public class ServiceProprietaire extends EntityService{

    /**
     * Retourne la liste des propriétaires
     * @return
     * @throws MonException
     */
    public List<ProprietaireEntity> consulterListeProprietaires() throws MonException {
        List<ProprietaireEntity> mesProprietaires = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesProprietaires = (List<ProprietaireEntity>)
                    entitymanager.createQuery(
                            "SELECT a FROM ProprietaireEntity a " +
                                    "ORDER BY a.idProprietaire").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesProprietaires;
    }

    /**
     * Retourne un propriétaire par son id
     * @param numero
     * @return
     * @throws MonException
     */
    public ProprietaireEntity proprietaireById(int numero) throws MonException {
        List<ProprietaireEntity> proprietaires = null;
        ProprietaireEntity proprietaire = new ProprietaireEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            proprietaires = (List<ProprietaireEntity>)entitymanager.createQuery("SELECT a FROM ProprietaireEntity a WHERE a.idProprietaire="+numero).getResultList();
            proprietaire = proprietaires.get(0);
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proprietaire;
    }

}
