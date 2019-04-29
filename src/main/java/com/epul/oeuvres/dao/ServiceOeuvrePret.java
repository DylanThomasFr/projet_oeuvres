package com.epul.oeuvres.dao;

import com.epul.oeuvres.controle.OeuvresControleur;
import com.epul.oeuvres.meserreurs.MonException;
import java.util.*;
import javax.persistence.*;
import com.epul.oeuvres.metier.*;


import javax.persistence.EntityTransaction;

public class ServiceOeuvrePret extends EntityService{

    /**
     * Liste des oeuvres en prêt non-réservées
     * @return
     * @throws MonException
     */
    public List<OeuvrepretEntity> consulterListeOeuvresPretLibres() throws MonException {
        List<OeuvrepretEntity> mesOeuvres= null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesOeuvres = (List<OeuvrepretEntity>)
                    entitymanager.createQuery(
                            "SELECT DISTINCT a " +
                                    "FROM OeuvrepretEntity a " +
                                    "WHERE idOeuvrepret " +
                                    "NOT IN (SELECT b.idOeuvrepret from PretEntity b ORDER BY b.idOeuvrepret)" +
                                    "ORDER BY a.idOeuvrepret").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesOeuvres;
    }

    /**
     * Liste des oeuvres en prêt réservées
     * @return
     * @throws MonException
     */
    public List<OeuvrepretEntity> consulterListeOeuvresPretReservees() throws MonException {
        List<OeuvrepretEntity> mesOeuvres= null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesOeuvres = (List<OeuvrepretEntity>)
                    entitymanager.createQuery(
                            "SELECT DISTINCT a " +
                                    "FROM OeuvrepretEntity a " +
                                    "WHERE idOeuvrepret " +
                                    "IN (SELECT b.idOeuvrepret from PretEntity b WHERE b.dateRendu=null ORDER BY b.idOeuvrepret)" +
                                    "ORDER BY a.idOeuvrepret").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesOeuvres;
    }

    /**
     * Insertion d'une oeuvre en prêt
     * @param uneOeuvre
     * @return
     * @throws MonException
     */
    public String insertOeuvrePret(OeuvrepretEntity uneOeuvre) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(uneOeuvre);
            entitymanager.flush();
            transac.commit();
            entitymanager.close();
            return "ok";
        }
        catch (RuntimeException e)
        {
            return e.getLocalizedMessage();
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    /**
     * Récupère une oeuvre en prêt avec son id
     * @param numero
     * @return
     * @throws MonException
     */
    public OeuvrepretEntity oeuvrePretByID(int numero) throws MonException {
        List<OeuvrepretEntity> oeuvres = null;
        OeuvrepretEntity oeuvre = new OeuvrepretEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            oeuvres = (List<OeuvrepretEntity>)entitymanager.createQuery("SELECT a FROM OeuvrepretEntity a WHERE a.idOeuvrepret="+numero).getResultList();
            oeuvre = oeuvres.get(0);
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvre;
    }

    /**
     * Mise à jour d'une oeuvre en prêt
     * @param oeuvre
     */
    public void updateOeuvrePret(OeuvrepretEntity oeuvre){
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.merge(oeuvre);
            transac.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();;
            new MonException("Erreur de modification", e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Suppression d'une oeuvre en prêt et des réservations associées
     * @param id
     * @throws MonException
     */
    public void removeOeuvrepret(Integer id) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("delete from OeuvrepretEntity where idOeuvrepret="+id).executeUpdate();
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
