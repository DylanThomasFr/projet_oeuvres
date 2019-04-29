package com.epul.oeuvres.dao;

import com.epul.oeuvres.controle.OeuvresControleur;
import com.epul.oeuvres.meserreurs.MonException;
import java.util.*;
import javax.persistence.*;
import com.epul.oeuvres.metier.*;


import javax.persistence.EntityTransaction;

public class ServiceOeuvreVente extends EntityService{

    /**
     * Insert une oeuvre en vente
     * @param uneOeuvre
     * @return
     * @throws MonException
     */
    public String insertOeuvreVente(OeuvreventeEntity uneOeuvre) throws MonException {
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
     * Supprime une oeuvre en vente
     * @param id
     * @throws MonException
     */
    public void removeOeuvrevente(Integer id) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("delete from ReservationEntity where idOeuvrevente="+id).executeUpdate();
            entitymanager.createQuery("delete from OeuvreventeEntity where idOeuvrevente="+id).executeUpdate();
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

    /**
     * Retourne les oeuvre non-réservées
     * @return
     * @throws MonException
     */
    public List<OeuvreventeEntity> consulterListeOeuvresVenteLibres() throws MonException {
        List<OeuvreventeEntity> mesOeuvres= null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesOeuvres = (List<OeuvreventeEntity>)
                    entitymanager.createQuery(
                            "SELECT DISTINCT a " +
                                    "FROM OeuvreventeEntity a " +
                                    "WHERE idOeuvrevente " +
                                    "NOT IN (SELECT b.idOeuvrevente from ReservationEntity b)" +
                                    "ORDER BY a.idOeuvrevente").getResultList();
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
     * Retourne les oeuvres  ayant été réservées
     * @return
     * @throws MonException
     */
    public List<OeuvreventeEntity> consulterListeOeuvresVenteReservees() throws MonException {
        List<OeuvreventeEntity> mesOeuvres= null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesOeuvres = (List<OeuvreventeEntity>)
                    entitymanager.createQuery(
                            "SELECT DISTINCT a " +
                                    "FROM OeuvreventeEntity a " +
                                    "WHERE idOeuvrevente " +
                                    "IN (SELECT b.idOeuvrevente from ReservationEntity b ORDER BY b.idOeuvrevente)" +
                                    "ORDER BY a.idOeuvrevente").getResultList();
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
     * Retourne une oeuvre par son id
     * @param numero
     * @return
     * @throws MonException
     */
    public OeuvreventeEntity oeuvreVenteByID(int numero) throws MonException {
        List<OeuvreventeEntity> oeuvres = null;
        OeuvreventeEntity oeuvre = new OeuvreventeEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            oeuvres = (List<OeuvreventeEntity>)entitymanager.createQuery("SELECT a FROM OeuvreventeEntity a WHERE a.idOeuvrevente="+numero).getResultList();
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
     * Met à jour une oeuvre en vente
     * @param oeuvre
     */
    public void updateOeuvreVente(OeuvreventeEntity oeuvre){
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

}
