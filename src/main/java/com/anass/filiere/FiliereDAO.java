/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anass.filiere;

import com.anass.filiere.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author anass
 */
public class FiliereDAO {

    private Configuration cfg;
    private SessionFactory sf;
    private Session session;

    public FiliereDAO() {
        this(new Configuration().configure());
    }

    public FiliereDAO(Configuration cfg) {
        this.cfg = cfg;
    }
    private Session getSession() {
        return this.sf.openSession();
    }

    public void add(Filiere filiere) {
        session = null;
        try {
            session = getSession();
            session.beginTransaction();
            session.save(filiere);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(Filiere filiere) throws NonexistentEntityException, Exception {
        session = null;
        try {
            session = getSession();
            session.getTransaction().begin();
            filiere = (Filiere) session.merge(filiere);
            session.getTransaction().commit();

        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = filiere.getId();
                if (get(id) == null) {
                    throw new NonexistentEntityException("The filiere with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Integer id) throws NonexistentEntityException {
        session = null;
        try {
            session = getSession();
            session.getTransaction().begin();
            Filiere filiere;
            try {
                filiere = session.getReference(Filiere.class, id);
                filiere.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The filiere with id " + id + " no longer exists.", enfe);
            }
            session.remove(filiere);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Filiere> getMany() {
        return FiliereDAO.this.getMany(true, -1, -1);
    }

    public List<Filiere> getMany(int maxResults, int firstResult) {
        return FiliereDAO.this.getMany(false, maxResults, firstResult);
    }

    private List<Filiere> getMany(boolean all, int maxResults, int firstResult) {
        session = null;
        try {
            session = getSession();
            Query query = session.createQuery("from Etudiant");
            if (!all) {
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
            }

            return query.getResultList();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Filiere get(Integer id) {
        session = getSession();
        try {
            return session.find(Filiere.class, id);
        } finally {
            session.close();
        }
    }

    public Long count() {
        session = getSession();
        Long count = -1L;
        try {
            session = getSession();
            Query query = session.createQuery("select count(*) from Filiere");
            count = (Long) query.uniqueResult();
            return count;
        } finally {
            session.close();
        }
    }

}
