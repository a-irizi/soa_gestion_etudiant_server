package com.anass.name;

import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Dependent
@Embeddable
@XmlRootElement(name = "name")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Name implements Serializable {

    @Column(name = "first_name", nullable = false)
    @XmlElement(name = "firstName")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @XmlElement(name = "lastName")
    private String lastName;
    @Column(name = "middle_name", nullable = true)
    @XmlElement(name = "middleName")
    private String middleName;

    public Name() {
    }

    public Name(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.middleName = null;
    }

    public Name(String firstName, String lastName, String middleName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setMiddleName(middleName);
    }

    private void checkName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(
                    "null et la chaine de caractere vide et interdit.");
        }

        if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException(
                    "le chaine de character \"" + name + "\" contient des character "
                    + "non valid\n"
                    + "Seulement les alphabet sont autorisés");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        checkName(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        checkName(lastName);
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        checkName(middleName);
        this.middleName = middleName;
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
        final Name other = (Name) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return Objects.equals(this.middleName, other.middleName);
    }

    @Override
    public String toString() {
        if (middleName == null) {
            return firstName + " " + lastName;
        }
        return firstName + " " + middleName + " " + lastName;
    }

}
