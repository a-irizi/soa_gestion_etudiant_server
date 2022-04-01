package com.anass.filiere;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "filieres")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement(name = "filiere")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Filiere implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private Integer id;
    @Column(nullable = false, unique = true)
    @XmlElement(name = "name")
    private String nom;

    public Filiere() {
    }

    public Filiere(String nom) {
        this.setId(id);
        this.setNom(nom);
    }

    public Filiere(Integer id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws IllegalArgumentException {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException(
                    "null et la chaine de caractere vide et interdit.");
        }

        if (nom.matches("^[0-9]+$")) {
            throw new IllegalArgumentException(
                    "Le nom ne doit pas contenure seulement les chifres");
        }

        if (!nom.matches("^\\d*[a-zA-Z][a-zA-Z\\d]*$")) {
            throw new IllegalArgumentException(
                    "Le nom doit contenir les lettres et peut aussi contenir "
                    + "les chiffres");
        }

        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.nom);
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
        final Filiere other = (Filiere) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }


    @Override
    public String toString() {
        return getNom();
    }
}
