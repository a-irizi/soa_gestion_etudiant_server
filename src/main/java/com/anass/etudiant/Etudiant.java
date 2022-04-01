package com.anass.etudiant;

import com.anass.filiere.Filiere;
import com.anass.name.Name;
import com.anass.personne.Personne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "etudiants")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement(name = "etudiant")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Etudiant extends Personne implements Serializable {

    @Column(nullable = false, unique = true)
    private String cne;

    @ManyToOne(optional = false, fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Filiere filiere;

    public Etudiant(String cne, Filiere filiere, String nom, String prenom,
            LocalDate dateDeNaissance) throws IllegalArgumentException {
        super(nom, prenom, dateDeNaissance);
        this.setCne(cne);
        this.setFiliere(filiere);
    }

    public Etudiant(String cne, Filiere filiere, String nom, String prenom,
            String deuxiemeNom, LocalDate dateDeNaissance)
            throws IllegalArgumentException {
        super(nom, prenom, deuxiemeNom, dateDeNaissance);
        this.setCne(cne);
        this.setFiliere(filiere);
    }

    public Etudiant(String cne, Filiere filiere, Name personneName,
            LocalDate dateDeNaissance) throws IllegalArgumentException {
        super(personneName, dateDeNaissance);
        this.setCne(cne);
        this.setFiliere(filiere);
    }

    public Etudiant() {
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) throws IllegalArgumentException {
        if (cne == null || cne.isEmpty()) {
            throw new IllegalArgumentException(
                    "Le champ cne de la classe Etudiant n'accepte ni null"
                    + " ni une chaine de caractere vide.");
        }
        if (!cne.matches("(R|r)[0-9]{9}")) {
            throw new IllegalArgumentException("La chaine de caratere " + cne
                    + " a une format invalide pour le champ cne de la classe "
                    + "Etudiant.\nCe champ doit commencer par R est suivie par "
                    + "9 chiffres.");
        }
        this.cne = cne.toUpperCase();
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        if (filiere == null) {
            throw new IllegalArgumentException("filiere ne doit pas être null");
        }
        this.filiere = filiere;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.cne);
        hash = 83 * hash + Objects.hashCode(this.filiere);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Etudiant other = (Etudiant) obj;
        if (!Objects.equals(this.cne, other.cne)) {
            return false;
        }
        return Objects.equals(this.filiere, other.filiere);
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nEtudiant{" + super.toString() + "cne=" + cne
                + ", filiere=" + filiere + '}';
    }
}
