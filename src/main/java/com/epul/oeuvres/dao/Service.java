package com.epul.oeuvres.dao;

import com.epul.oeuvres.controle.OeuvresControleur;
import com.epul.oeuvres.meserreurs.MonException;
import java.util.*;
import javax.persistence.*;
import com.epul.oeuvres.metier.*;


import javax.persistence.EntityTransaction;

public class Service extends EntityService{

	public UtilisateurEntity getUtilisateur( String login) throws MonException
	{
		UtilisateurEntity unUtilisateur=null;
	try {
		EntityTransaction transac = startTransaction();
		transac.begin();

		Query query = entitymanager.createNamedQuery("UtilisateurEntity.rechercheNom");
		query.setParameter("name", login);
		unUtilisateur = (UtilisateurEntity) query.getSingleResult();
		if (unUtilisateur == null) {
			new MonException("Utilisateur Inconnu", "Erreur ");
	}
		entitymanager.close();

	}
		catch(RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e){
		new MonException("Erreur de lecture", e.getMessage());
	}
		return unUtilisateur;
	}

    public List<AdherentEntity> getUsers(){
        List<AdherentEntity> mesAdherents= null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();

            mesAdherents = (List<AdherentEntity>)
                    entitymanager.createQuery(
                            "SELECT a " +
                                    "FROM AdherentEntity a").getResultList();
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
}
