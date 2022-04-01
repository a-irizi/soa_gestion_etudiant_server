package com.anass.filiere;

import com.anass.filiere.exceptions.NonexistentEntityException;
import java.util.List;
import javax.jws.WebService;

@WebService(serviceName = "filiereService", portName = "filiereServicePort")
public class FiliereServiceImpl {

    private final FiliereDAO filiereDAO;

    public FiliereServiceImpl() {
        filiereDAO = new FiliereDAO();
    }

//    @Override
    public void addFiliere(Filiere filiere) {
        filiereDAO.add(filiere);
    }

//    @Override
    public void updateFiliere(Filiere filiere) throws NonexistentEntityException, Exception {
        filiereDAO.update(filiere);
    }

//    @Override
    public void deleteFiliere(Integer id) throws NonexistentEntityException {
        filiereDAO.delete(id);
    }

//    @Override
    public List<Filiere> getAllFiliere() {
        return filiereDAO.getMany();
    }

//    @Override
    public List<Filiere> getManyFiliere(int maxResults, int firstResult) {
        return filiereDAO.getMany(maxResults, firstResult);
    }

//    @Override
    public Filiere getFiliere(Integer id) {
        return filiereDAO.get(id);
    }

//    @Override
    public Long countFiliere() {
        return filiereDAO.count();
    }

}
