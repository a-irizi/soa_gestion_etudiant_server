package com.anass.filiere;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test for Filiere class")
public class FiliereTest {
    Filiere cut;
    public FiliereTest() {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    @DisplayName("getId should return the id of Filiere object")
    public void getIdShould_Return_IdOfFiliereObject() {
        //given
        Integer id = 101;
        String name = "text";
        cut = new Filiere(id, name);

        // when
        Integer result = cut.getId();

        // then
        assertEquals(id, result);
    }

    @Test
    @DisplayName("setId should set the id of Filiere object")
    public void setIdShould_Set_IdOfFiliereObject() {
        //given
        Integer id = 101;
        String name = "text";
        cut = new Filiere(id, name);

        // when
        Integer newId = 102;
        cut.setId(newId);

        // then
        assertEquals(newId, cut.getId());

    }

    @Test
    @DisplayName("getNom should return the name of Filiere object")
    public void getNomShould_Return_NameOfFiliereObject() {
        //given
        Integer id = 101;
        String name = "text";
        cut = new Filiere(id, name);

        // when
        String result = cut.getNom();

        // then
        assertEquals(name, result);
    }

    @Test
    @DisplayName("setNom should set the name of Filiere object")
    public void setNomShould_Set_NameOfFiliereObject() {
        //given
        Integer id = 101;
        String name = "text1";
        cut = new Filiere(id, name);

        // when
        String newName = "text2";
        cut.setNom(newName);

        // then
        assertEquals(newName, cut.getNom());
    }

    @Test
    @DisplayName("setNom should throw IllegalArgumentException for null"
            + " arguments")
    public void setNomShould_ThrowIllegalArgumentException_ForNullArgument() {
        //given
        Integer id = 101;
        String name = "text1";
        cut = new Filiere(id, name);

        // when
        String newName = null;
        //then
        assertThrows(IllegalArgumentException.class,
                () -> cut.setNom(newName));
    }

    @Test
    @DisplayName("setNom should throw IllegalArgumentException for"
            + " argument containing only numbers")
    public void setNomShould_ThrowIllegalArgumentException_ForArgumentContainingOnlyNumbers() {
        // given
        Integer id = 101;
        String name = "text1";
        cut = new Filiere(id, name);

        // when
        String newName = "1";
        // then
        assertThrows(IllegalArgumentException.class,
                () -> cut.setNom(newName));
    }

    @Test
    @DisplayName("setNom should throw IllegalArgumentException for"
            + " argument containing characters other than letters and numbers")
    public void setNomShould_ThrowIllegalArgumentException_ForArgumentContainingCharactersOtherThanLettersAndNumbers() {
        // given
        cut = new Filiere();

        // when
        String newName = "a1_";

        // then
        assertThrows(IllegalArgumentException.class,
                () -> cut.setNom(newName));
    }

    @Test
    @DisplayName("setNom should not throw any exception if the argument "
            + "contains only letters and numbers but not only numbers")
    public void setNomShouldNot_Throw_AnyException_IfArgumetContainsOnlyLettersAndNumbersButNotOnlyNumbers() {
        // given
        cut = new Filiere();

        // when
        String name1 = "a";
        String name2 = "1a";
        String name3 = "a1";

        assertAll("Test for setNom with correct arguments",
                () -> assertDoesNotThrow(() -> cut.setNom(name1)),
                () -> assertDoesNotThrow(() -> cut.setNom(name2)),
                () -> assertDoesNotThrow(() -> cut.setNom(name3)));
    }

    @Test
    @DisplayName("two Filiere objects with the same name and id should be equal")
    public void twoFiliereObjectsWith_TheSameNameAndId_Should_beEqual() {
        // given
        Integer id = 101;
        String name = "text";

        // when
        Filiere f1 = new Filiere(id, name);
        Filiere f2 = new Filiere(id, name);

        // then
        assertEquals(f1, f2);
    }

    @Test
    @DisplayName("two Filiere objects with the different Ids should not be "
            + "equal")
    public void twoFiliereObjectsWith_DifferentId_ShouldNot_beEqual() {
        // given
        Integer id1 = 101;
        Integer id2 = 102;
        String name = "text";

        // when
        Filiere f1 = new Filiere(id1, name);
        Filiere f2 = new Filiere(id2, name);

        // then
        assertNotEquals(f1, f2);
    }

    @Test
    @DisplayName("toString should return the member nom of the Filiere object")
    public void toStringShould_Return_MemberNom_OfFiliereObject() {
        // when
        Integer id = 101;
        String name = "text";
        cut = new Filiere(id, name);

        // given
        String result = cut.toString();

        // then
        assertEquals(name, result);
    }

}
