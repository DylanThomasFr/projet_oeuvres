package com.epul.oeuvres.dao;

import com.epul.oeuvres.controle.OeuvresControleur;
import com.epul.oeuvres.meserreurs.MonException;
import java.util.*;
import javax.persistence.*;
import com.epul.oeuvres.metier.*;


import javax.persistence.EntityTransaction;

public class ServiceReservationVente extends EntityService{

    /**
     * Insert une nouvelle réservation d'oeuvre en vente
     * @param uneReservation
     * @return
     * @throws MonException
     */
    public StackTraceElement[] reservationOeuvreVente(ReservationEntity uneReservation) throws MonException {
        String requete = "INSERT INTO reservation(id_oeuvrevente, id_adherent, date_reservation, statut) " +
                "VALUES("+uneReservation.getIdOeuvrevente()+","+uneReservation.getIdAdherent()+",'"+uneReservation.getDateReservation()+"','"+uneReservation.getStatut()+"')";
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
     * Supprime une réservation d'oeuvre en vente
     * @param id
     * @throws MonException
     */
    public void removeReservationvente(Integer id) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("delete from ReservationEntity where idOeuvrevente="+id).executeUpdate();
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
     * Retourne une réservation grâce à son ID
     * @param numero
     * @return
     * @throws MonException
     */
    public ReservationEntity reservationVenteByID(int numero) throws MonException {
        List<ReservationEntity> reservations = null;
        ReservationEntity reservation = new ReservationEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            reservations = (List<ReservationEntity>)entitymanager.createQuery("SELECT a FROM ReservationEntity a WHERE a.idOeuvrevente="+numero).getResultList();
            reservation = reservations.get(0);
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }

    /**
     * Met à jour une reservation
     * @param reservation
     */
    public void updateReservationOeuvreVente(ReservationEntity reservation){
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.createQuery("" +
                    "UPDATE ReservationEntity " +
                    "SET idOeuvrevente='"+reservation.getIdOeuvrevente()+
                    "', idAdherent='"+reservation.getIdAdherent()+
                    "', statut='"+reservation.getStatut()+
                    "', dateReservation='"+reservation.getDateReservation()+
                    "' WHERE idOeuvrevente="+reservation.getIdOeuvrevente()).executeUpdate();
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
     * Retourne les réservations d'oeuvres en vente
     * @return
     */
    public List<ReservationEntity> getReservationsVentes(){
        List<ReservationEntity> mesReservations= null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();

            mesReservations = (List<ReservationEntity>)
                    entitymanager.createQuery(
                            "SELECT a " +
                                    "FROM ReservationEntity a").getResultList();
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
}
