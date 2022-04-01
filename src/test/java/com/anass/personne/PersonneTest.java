package com.anass.personne;

import com.anass.name.Name;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Test for Personne Class")
public class PersonneTest {

    private PersonneImpl cut;

    class PersonneImpl extends Personne {

        public PersonneImpl(String nom, String prenom, LocalDate dateDeNaissance) throws IllegalArgumentException {
            super(nom, prenom, dateDeNaissance);
        }

        public PersonneImpl(String nom, String prenom, String deuxiemeNom, LocalDate dateDeNaissance) throws IllegalArgumentException {
            super(nom, prenom, deuxiemeNom, dateDeNaissance);
        }

        public PersonneImpl(Name personneName, LocalDate dateDeNaissance) {
            super(personneName, dateDeNaissance);
        }

    }

    @Test
    @DisplayName("getDateDeNaissance should return date of birth")
    public void getDateDeNaissanceShouldReturnDateOfBirth() {
        // given
        String firstName = "Ali";
        String lastName = "Alaoui";
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(firstName, lastName, dob);

        // when
        LocalDate result = cut.getDateDeNaissance();

        // then
        assertEquals(dob, result);
    }

    @Test
    @DisplayName("setDateDeNaissance should change the date of birth")
    public void setDateDeNaissanceShouldChangeTheDateOfBirth() {
        // given
        String firstName = "Ali";
        String lastName = "Alaoui";
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(firstName, lastName, dob);

        // when
        LocalDate expected = LocalDate.parse("2000-01-02");
        cut.setDateDeNaissance(expected);
        LocalDate result = cut.getDateDeNaissance();

        // then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("setDateDeNaissance should throw IllegalArgumentException for"
            + "null dateDeNaissance parameter")
    public void setDateDeNaissanceShould_ThrowIllegalArgumentException_ForNullDateDeNaissanceParameter() {
        // given
        String firstName = "Ali";
        String lastName = "Alaoui";
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(firstName, lastName, dob);
        LocalDate nullLocalDate = null;


        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> cut.setDateDeNaissance(nullLocalDate));
    }

    @Test
    @DisplayName("setDateDeNaissance should throw IllegalArgumentException for"
            + "date after or the same as today's date")
    public void setDateDeNaissanceShould_ThrowIllegalArgumentException_ForDateAfterOrSameAsToday() {
        // given
        String firstName = "Ali";
        String lastName = "Alaoui";
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(firstName, lastName, dob);
        LocalDate now = LocalDate.now();
        LocalDate afterNow = now.plusDays(1L);

        // when
        // then
        assertAll("Test setDateDeNaissance for date equal to of bigger than today's date",
                () -> assertThrows(IllegalArgumentException.class, () -> cut.setDateDeNaissance(now)),
                () -> assertThrows(IllegalArgumentException.class, () -> cut.setDateDeNaissance(afterNow)));
    }

    @Test
    @DisplayName("setDateDeNaissance should throw IllegalArgumentException for"
            + "age less than 18 years")
    public void setDateDeNaissanceShould_Throw_IllegalArgumentException_For_AgeLessThan18() {
        // given
        String firstName = "Ali";
        String lastName = "Alaoui";
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(firstName, lastName, dob);
        LocalDate lessThan18YearsFromNow;
        lessThan18YearsFromNow = LocalDate.now().minusYears(18L).plusDays(1L);

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> cut.setDateDeNaissance(lessThan18YearsFromNow));
    }

    @Test
    @DisplayName("setDateDeNaissance should not throw IllegalArgumentException"
            + " for age equal to 18 years")
    public void setDateDeNaissanceShould_NotThrow_IllegalArgumentException_For_AgeEqualTo18() {
        // given
        String firstName = "Ali";
        String lastName = "Alaoui";
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(firstName, lastName, dob);
        LocalDate exactly18YearsFromNow;
        exactly18YearsFromNow = LocalDate.now().minusYears(18L);

        // when
        // then
        assertDoesNotThrow(() -> cut.setDateDeNaissance(exactly18YearsFromNow));
    }

    @Test
    @DisplayName("setDateDeNaissance should not throw IllegalArgumentException"
            + " for age greater than 18 years")
    public void setDateDeNaissanceShould_NotThrow_IllegalArgumentException_For_GreaterThan18() {
        // given
        String firstName = "Ali";
        String lastName = "Alaoui";
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(firstName, lastName, dob);
        LocalDate moreThan18YearsFromNow;
        moreThan18YearsFromNow = LocalDate.now().minusYears(18L).minusYears(1L);

        // when
        // then
        assertDoesNotThrow(() -> cut.setDateDeNaissance(moreThan18YearsFromNow));
    }

    @Test
    @DisplayName("getPersonneName should return the name assigned to the person")
    public void getPersonneNameShould_Return_NameOfPerson() {
        // given
        String firstName = "Ali";
        String lastName = "Alaoui";
        String middleName = "Allal";
        Name expectedName = new Name(firstName, lastName, middleName);
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(expectedName, dob);

        // when
        Name result;
        result = cut.getPersonneName();

        // then
        assertEquals(expectedName, result);
    }

    @Test
    @DisplayName("setPersonneName should change the name assigned to the person")
    public void setPersonneNameShould_Change_NameOfPerson() {
        // given
        String firstName1 = "Ali";
        String lastName1 = "Alaoui";
        String middleName1 = "Allal";
        Name oldName = new Name(firstName1, lastName1, middleName1);
        LocalDate dob = LocalDate.parse("2000-01-01");
        cut = new PersonneImpl(oldName, dob);

        String firstName2 = "Joe";
        String lastName2 = "Smith";
        String middleName2 = "Carter";

        Name newName = new Name(firstName2, lastName2, middleName2);

        // when
        cut.setPersonneName(newName);
        Name result = cut.getPersonneName();

        // then
        assertEquals(newName, result);
    }


}
