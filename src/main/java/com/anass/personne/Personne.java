package com.anass.personne;

import com.anass.name.Name;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Dependent
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public abstract class Personne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private Integer id;
    @Column(nullable = false)
    @XmlElement(name = "name")
    private Name personneName;
    @Column(nullable = false)
    @XmlElement(name = "dateOfBirth")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateDeNaissance;

    public Personne() {
    }

    public Personne(String nom, String prenom,
            LocalDate dateDeNaissance) throws IllegalArgumentException {
        this.personneName = new Name(prenom, nom);
        this.setDateDeNaissance(dateDeNaissance);
    }

    public Personne(String nom, String prenom, String deuxiemeNom,
            LocalDate dateDeNaissance) throws IllegalArgumentException {
        this.personneName = new Name(prenom, nom, deuxiemeNom);
        this.setDateDeNaissance(dateDeNaissance);
    }

    public Personne(Name personneName, LocalDate dateDeNaissance)
            throws IllegalArgumentException {
        this.personneName = personneName;
        this.setDateDeNaissance(dateDeNaissance);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance)
            throws IllegalArgumentException {
        if (dateDeNaissance == null) {
            throw new IllegalArgumentException(
                    "dateDeNaissance ne doit pas être null");
        }

        LocalDate now = LocalDate.now();
        if (now.compareTo(dateDeNaissance) == -1
                || now.compareTo(dateDeNaissance) == 0) {
            throw new IllegalArgumentException(
                    "dateDeNaissance doit être avant la date d'aujourd'hui");
        }

        Integer years;
        years = Math.abs(Period.between(now, dateDeNaissance).getYears());
        if (years < 18) {
            throw new IllegalArgumentException(
                    "l'age doit doit être plus grand ou égale à 18 ans.\n"
                    + "Valeur actuelle " + years + " ans (" + dateDeNaissance + ")");
        }

        this.dateDeNaissance = dateDeNaissance;
    }

    public Name getPersonneName() {
        return personneName;
    }

    public void setPersonneName(Name personneName)
            throws IllegalArgumentException {
        if (personneName == null) {
            throw new IllegalArgumentException("Le nom du personne ne doit pas"
                    + " être null");
        }
        this.personneName = personneName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.personneName);
        hash = 37 * hash + Objects.hashCode(this.dateDeNaissance);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Personne other = (Personne) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.personneName, other.personneName)) {
            return false;
        }
        return Objects.equals(this.dateDeNaissance, other.dateDeNaissance);
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", personneName=" + personneName + ", dateDeNaissance=" + dateDeNaissance + '}';
    }

}
