package com.epul.oeuvres.dao;

import com.epul.oeuvres.controle.OeuvresControleur;
import com.epul.oeuvres.meserreurs.MonException;
import java.util.*;
import javax.persistence.*;
import com.epul.oeuvres.metier.*;


import javax.persistence.EntityTransaction;

public class ServiceAdherent extends EntityService{

    /**
     * Insertion d'un adherent
     * @param unAdherent
     * @throws MonException
     */
    public void insertAdherent(AdherentEntity unAdherent) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(unAdherent);
            transac.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de d'ajout d'un adherent", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mise à jour d'un adhérent
     * @param unAdherent
     */
    public void updateAdherent(AdherentEntity unAdherent){
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("" +
                    "UPDATE AdherentEntity " +
                    "SET nomAdherent='"+unAdherent.getNomAdherent()+
                    "', prenomAdherent='"+unAdherent.getPrenomAdherent()+
                    "', villeAdherent='"+unAdherent.getVilleAdherent()+
                    "' WHERE idAdherent="+unAdherent.getIdAdherent()).executeUpdate();
            transac.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de modification", e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Liste les adhérents
     * @return
     * @throws MonException
     */
    public List<AdherentEntity> consulterListeAdherents() throws MonException {
        List<AdherentEntity> mesAdherents = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesAdherents = (List<AdherentEntity>)
                    entitymanager.createQuery(
                            "SELECT a FROM AdherentEntity a " +
                                    "ORDER BY a.nomAdherent").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesAdherents;
    }

    /**
     * Récupère un adhérent avec son id
     * @param numero
     * @return
     * @throws MonException
     */
    public AdherentEntity adherentById(int numero) throws MonException {
        List<AdherentEntity> adherents = null;
        AdherentEntity adherent = new AdherentEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            adherents = (List<AdherentEntity>)entitymanager.createQuery("SELECT a FROM AdherentEntity a WHERE a.idAdherent="+numero).getResultList();
            adherent = adherents.get(0);
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adherent;
    }

    /**
     * Supprime un adherent
     * @param id
     * @throws MonException
     */
    public void removeAdherent(Integer id) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("delete from ReservationEntity where idAdherent="+id).executeUpdate();
            entitymanager.createQuery("delete from AdherentEntity where idAdherent="+id).executeUpdate();
            transac.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de suppression", e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
