/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anass.etudiant;

import com.anass.etudiant.exceptions.NonexistentEntityException;
import java.util.List;
import javax.jws.WebService;

@WebService(serviceName = "etudiantService", portName = "etudiantServicePort")
public class EtudiantServiceImpl {

    private final EtudiantDAO etudiantDAO;

    public EtudiantServiceImpl() {
        etudiantDAO = new EtudiantDAO();
    }

//    @Override
    public void addEtudiant(Etudiant etudiant) {
        etudiantDAO.add(etudiant);
    }

//    @Override
    public void updateEtudiant(Etudiant etudiant) throws NonexistentEntityException {
        etudiantDAO.update(etudiant);
    }

//    @Override
    public void deleteEtudiant(Integer id) {
        etudiantDAO.delete(id);
    }

//    @Override
    public Etudiant getEtudiant(Integer id) {
        return etudiantDAO.get(id);
    }

//    @Override
    public List<Etudiant> getAllEtudiant() {
        return etudiantDAO.getMany();
    }

//    @Override
    public List<Etudiant> getManyEtudiant(int firstResults, int maxResults) {
        return etudiantDAO.getMany(firstResults, maxResults);
    }

//    @Override
    public Long countEtudiant() {
        return etudiantDAO.getCount();
    }

}
