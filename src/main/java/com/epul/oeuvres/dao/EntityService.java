package com.epul.oeuvres.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Valentin on 06/04/2016.
 */
public abstract class EntityService {

    protected EntityManager entitymanager;
    protected EntityManagerFactory emf;

    /**
     * Démarre une transaction
     * @return
     * @throws Exception
     */
    public EntityTransaction startTransaction() throws Exception
    {
        emf = Persistence.createEntityManagerFactory("oeuvresjpa");
        entitymanager = emf.createEntityManager();

        return entitymanager.getTransaction();
    }

}