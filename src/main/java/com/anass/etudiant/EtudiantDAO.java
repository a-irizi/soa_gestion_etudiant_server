package com.anass.etudiant;

import com.anass.etudiant.exceptions.NonexistentEntityException;
import com.anass.filiere.Filiere;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EtudiantDAO {

    private Configuration cfg;
    private SessionFactory sf;
    private Session session;

    public EtudiantDAO() {
        this(new Configuration().configure());
    }

    public EtudiantDAO(Configuration cfg) {
        this.cfg = cfg;
        this.sf = cfg.buildSessionFactory();
    }

    private Session getSession() {
        return this.sf.openSession();
    }

    public void add(Etudiant etudiant) {
        session = null;
        try {
            session = getSession();
            session.beginTransaction();

            Filiere filiere = etudiant.getFiliere();
            if (filiere != null) {
                filiere = session.get(filiere.getClass(), filiere.getId());
                etudiant.setFiliere(filiere);
            }
            session.save(etudiant);

            session.getTransaction().commit();
        } finally {
            if (sf != null) {
                sf.close();
            }
        }
    }

    public void update(Etudiant etudiant) throws NonexistentEntityException {
        session = null;
        try {
            session = getSession();
            session.beginTransaction();

            Etudiant persistentEtudiant = session.get(Etudiant.class, etudiant.getId());
            Filiere filiereOld = persistentEtudiant.getFiliere();
            Filiere filiereNew = etudiant.getFiliere();
            if (filiereNew != null) {
                filiereNew = session.get(filiereNew.getClass(), filiereNew.getId());
                etudiant.setFiliere(filiereNew);
            }
            session.merge(etudiant);

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public void delete(Integer id) {
        session = null;
        try {
            session = getSession();
            session.beginTransaction();

            Etudiant etudiant = session.get(Etudiant.class, id);
            Filiere filiere = etudiant.getFiliere();
            session.remove(etudiant);

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Etudiant get(Integer id) {
        session = getSession();
        Etudiant etudiant;
        try {
            etudiant = session.get(Etudiant.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return etudiant;
    }

    public List<Etudiant> getMany() {
        return EtudiantDAO.this.getMany(true, -1, -1);
    }

    public List<Etudiant> getMany(int firstResults, int maxResults) {
        return EtudiantDAO.this.getMany(false, firstResults, maxResults);
    }

    private List<Etudiant> getMany(boolean all, int firstResults, int maxResults) {
        session = null;
        try {
            session = getSession();
            Query query = session.createQuery("from Etudiant");
            if (!all) {
                query.setFirstResult(firstResults);
                query.setMaxResults(maxResults);
            }

            return query.getResultList();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Long getCount() {
        session = null;
        Long count = -1L;
        try {
            session = getSession();
            Query query = session.createQuery("select count(*) from Etudiant");
            count = (Long) query.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return count;
    }

}
