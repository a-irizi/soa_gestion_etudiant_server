package com.anass.etudiant;

import com.anass.filiere.Filiere;
import com.anass.name.Name;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EtudiantTest {

    private Etudiant cut;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("getCne should return the cne")
    public void getCneShould_Return_Cne() {
        // given
        String fname = "Ali";
        String mname = "Allal";
        String lname = "Alaoui";
        Name etudinatName = new Name(fname, lname, mname);

        LocalDate dob = LocalDate.parse("2000-01-01");

        Integer filiereId = 111;
        String filiereName = "DSBD";
        Filiere filiere = new Filiere(filiereId, filiereName);

        String cne = "R123456789";

        cut = new Etudiant(cne, filiere, etudinatName, dob);

        // when
        String result = cut.getCne();

        // then
        assertEquals(cne, result);
    }

    @Test
    @DisplayName("setCne chould set the cne")
    public void setCneShould_Set_Cne() {
        // given
        cut = new Etudiant();
        String cne = "R234567890";

        // when
        cut.setCne(cne);
        String result = cut.getCne();

        // then
        assertEquals(cne, result);
    }

    @Test
    @DisplayName("setCne should set cne in all capital letters")
    public void setCneShould_set_Cne_InAllCapitalLetters() {
        // given
        String cne = "r123456789";
        cut = new Etudiant();

        // when
        cut.setCne(cne);
        String result = cut.getCne();

        // then
        assertEquals(cne.toUpperCase(), result);

    }

    @Test
    @DisplayName("setCne Should throw IllegalArgumentException if cne is null")
    public void setCneShould_Throw_IllegalArgumentException_If_CneIsNull() {
        // given
        cut = new Etudiant();

        // when
        String cne = null;
        // then
        assertThrows(IllegalArgumentException.class, () -> cut.setCne(cne));
    }

    @Test
    @DisplayName("setCne Should throw IllegalArgumentException if cne is empty")
    public void setCneShould_Throw_IllegalArgumentException_If_CneIsEmpty() {
        // given
        cut = new Etudiant();

        // when
        String cne = "";
        // then
        assertThrows(IllegalArgumentException.class, () -> cut.setCne(cne));
    }

    @Test
    @DisplayName("setCne should throw IllegalArgumentException if cne does not "
            + "start with r or R followed by 9 numbers")
    public void setCneShould_Throw_IllegalArgumentException_If_CneDoesNotStartWithRFollowedBy9Numbers() {
        // given
        cut = new Etudiant();

        // when
        String a = "1R23456789";
        String b = "R12345678";
        String c = "R1234c6789";
        String d = "0123456789";
        String e = "D123456789";

        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> cut.setCne(a)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> cut.setCne(b)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> cut.setCne(c)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> cut.setCne(d)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> cut.setCne(e))
        );

    }

    @Test
    @DisplayName("setCne Should not throw exception when cne is of the correct "
            + "format")
    public void setCneShouldNot_ThrowException_When_CneIsOfTheCorrectFormat() {
        // given
        cut = new Etudiant();

        // when
        String cne = "R123456789";

        // then
        assertDoesNotThrow(() -> cut.setCne(cne));
    }

    @Test
    @DisplayName("getFiliere should return filiere")
    public void getFiliereShould_Return_Filiere() {
        // given
        String fname = "Ali";
        String mname = "Allal";
        String lname = "Alaoui";
        Name etudinatName = new Name(fname, lname, mname);

        LocalDate dob = LocalDate.parse("2000-01-01");

        Integer filiereId = 111;
        String filiereName = "DSBD";
        Filiere filiere = new Filiere(filiereId, filiereName);

        String cne = "R123456789";

        cut = new Etudiant(cne, filiere, etudinatName, dob);

        // when
        Filiere result;
        result = cut.getFiliere();

        // then
        assertEquals(filiere, result);
    }

    @Test
    @DisplayName("setFiliere should set filiere")
    public void setFiliereShould_Set_Filiere() {
        // given
        cut = new Etudiant();

        Integer filiereId = 111;
        String filiereName = "DSBD";
        Filiere filiere = new Filiere(filiereId, filiereName);

        // when
        cut.setFiliere(filiere);
        Filiere result;
        result = cut.getFiliere();

        // then
        assertEquals(filiere, result);
    }

    @Test
    @DisplayName("setFiliere should Throw IllegalArgumentException if filiere "
            + "is null")
    public void setFiliereShould_Throw_IllegalArgumentException_If_FiliereIsNull() {
        // given
        cut = new Etudiant();

        // when
        Filiere filiere = null;

        // then
        assertThrows(IllegalArgumentException.class,
                () -> cut.setFiliere(filiere));
    }

    @Test
    @DisplayName("setFiliere should not throw exception if filiere is not null")
    public void setFiliereShouldNot_throwException_If_FiliereIsNotNull() {
        // given
        cut = new Etudiant();

        // when
        Integer filiereId = 111;
        String filiereName = "DSBD";
        Filiere filiere = new Filiere(filiereId, filiereName);

        // then
        assertDoesNotThrow(() -> cut.setFiliere(filiere));
    }


}
