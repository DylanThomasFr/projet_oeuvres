package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.PretEntity;

import javax.persistence.EntityTransaction;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ServicePret extends EntityService{

    /**
     * Retourne les réservations d'oeuvres en pret
     * @return
     */
    public List<PretEntity> getReservationsPret(){
        List<PretEntity> mesReservations= null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();

            mesReservations = (List<PretEntity>)
                    entitymanager.createQuery(
                            "SELECT a " +
                                    "FROM PretEntity a").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesReservations;
    }

    /**
     * Insert une nouvelle réservation d'oeuvre en pret
     * @param uneReservation
     * @return
     * @throws MonException
     */
    public StackTraceElement[] reservationOeuvrePret(PretEntity uneReservation) throws MonException {
        String requete = "INSERT INTO pret(id_oeuvrepret, id_adherent, date_reservation, date_rendu) " +
                "VALUES("+uneReservation.getIdOeuvrepret()+","+uneReservation.getIdAdherent()+",'"+uneReservation.getDateReservation()+"',NULL)";
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createNativeQuery(requete).executeUpdate();
            entitymanager.flush();
            transac.commit();
            entitymanager.close();
            return null;
        }
        catch (RuntimeException e)
        {
            return e.getStackTrace();
        } catch (Exception e) {
            return e.getStackTrace();
        }
    }

    /**
     * Supprime un prêt
     * @param idO
     * @param idA
     * @param dateR
     * @throws MonException
     */
    public void removePret(Integer idO, Integer idA, Date dateR) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("delete from PretEntity where idOeuvrepret="+idO+" and idAdherent="+idA+" and dateRendu="+dateR).executeUpdate();
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

    public PretEntity getPretById(Integer idO){
        PretEntity pret = new PretEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            pret = (PretEntity) entitymanager.createQuery("SELECT a FROM PretEntity a WHERE a.idOeuvrepret="+idO+" and dateRendu=NULL").getSingleResult();
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pret;
    }

    /**
     * Met à jour un prêt
     * @param idO
     */
    public void updateReservationOeuvreVente(Integer idO){
        PretEntity pret = this.getPretById(idO);
        java.sql.Date dateF = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("" +
                    "UPDATE PretEntity " +
                    "SET dateRendu='" +dateF+
                    "' WHERE idOeuvrepret="+pret.getIdOeuvrepret()).executeUpdate();
            transac.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            new MonException("Erreur de modification", e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
