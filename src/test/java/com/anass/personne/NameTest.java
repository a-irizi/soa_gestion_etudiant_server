package com.anass.personne;

import com.anass.name.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Test for Name class")
public class NameTest {

    private Name cut;

    public NameTest() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @DisplayName("constructor with two String parameters should assigns values to fields successfully")
    public void ConstructorWithTwoStringParametersShouldAssignValuesToFieldsSuccessfully() {
        // Arrange
        String expectedFirstName = "Ali";
        String expectedLastName = "Allaoui";

        // Act
        cut = new Name("Ali", "Allaoui");

        // Assert
        assertAll("Test Constructor With two String arguments",
                () -> assertEquals(expectedFirstName, cut.getFirstName()),
                () -> assertEquals(expectedLastName, cut.getLastName()),
                () -> assertNull(cut.getMiddleName()));
    }

    @Test
    @DisplayName("constructor with three String parameters should assigns values to fields successfully")
    public void ConstructorWithThreeStringParametersShouldAssignValuesToFieldsSuccessfully() {
        // Arrange
        String expectedFirstName = "Ali";
        String expectedLastName = "Allaoui";
        String expectedMiddleName = "Allali";

        // Act
        cut = new Name("Ali", "Allaoui", "Allali");

        // Assert
        assertAll("Test Constructor With two String arguments",
                () -> assertEquals(expectedFirstName, cut.getFirstName()),
                () -> assertEquals(expectedLastName, cut.getLastName()),
                () -> assertEquals(expectedMiddleName, cut.getMiddleName()));
    }

    @Test
    @DisplayName("constructor should throw IllegalArgumentException when"
            + " assigning null to a parameter")
    public void ConstructorShouldThrowIllegalArgumentExceptionWhenAssigningNullToTheFirstName() {
        String nonNullString = "abc";
        assertAll("Test Constructors with null value",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(null, nonNullString);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(nonNullString, null);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(null, nonNullString, nonNullString);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(nonNullString, null, nonNullString);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(nonNullString, nonNullString, null);
                })
        );
    }

    @Test
    @DisplayName("constructor should throw IllegalArgumentException when"
            + " assigning String with non-alphabet characters to a parameter")
    public void ConstructorShouldThrowIllegalArgumentExceptionWhenAssigningStringWithNonAlphabetCharactersToParameter() {
        String onlyAlphabetCharacters = "abc";
        String containingNumber = "abc6";
        String containingSpecialCharacter = "/abc";
        assertAll("Test Constructors with String containing a number:",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(containingNumber, onlyAlphabetCharacters);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(onlyAlphabetCharacters, containingNumber);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(containingNumber, onlyAlphabetCharacters, onlyAlphabetCharacters);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(onlyAlphabetCharacters, containingNumber, onlyAlphabetCharacters);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(onlyAlphabetCharacters, onlyAlphabetCharacters, containingNumber);
                })
        );
        assertAll("Test Constructors with String containing a number:",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(containingSpecialCharacter, onlyAlphabetCharacters);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(onlyAlphabetCharacters, containingSpecialCharacter);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(containingSpecialCharacter, onlyAlphabetCharacters, onlyAlphabetCharacters);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(onlyAlphabetCharacters, containingSpecialCharacter, onlyAlphabetCharacters);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    cut = new Name(onlyAlphabetCharacters, onlyAlphabetCharacters, containingSpecialCharacter);
                })
        );
    }

    @Test
    @DisplayName("toString method should return the name in the formate"
            + " \"firstName lastName\" is middleName if null")
    public void toStringShouldReturnOnlyFirstNameAndLastNameIfMiddleNameIsNull() {
        // given
        String firstName = "Ali";
        String lastName = "Aloui";
        cut = new Name(firstName, lastName);

        // when
        String result = cut.toString();

        // then
        String expected = firstName + " " + lastName;
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("toString method should return the name in the formate"
            + " \"firstName middleName lastName\" if middleName is not null")
    public void toStringShouldReturnOnlyFirstNameAndMiddleNameAndLastNameIfMiddleNameIsNotNull() {
        // given
        String firstName = "Ali";
        String lastName = "Aloui";
        String middleName = "Allal";
        cut = new Name(firstName, lastName, middleName);

        // when
        String result = cut.toString();

        // then
        String expected = firstName + " " + middleName + " " + lastName;
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("equals should return false if objects do not match")
    public void equalsShould_ReturnFalse_IfObjectsDoNotMatch() {
        String firstName1 = "Ali";
        String lastName1 = "Alaoui";
        String middleName1 = "Allal";
        String firstName2 = "Joe";
        String lastName2 = "Smith";
        String middleName2 = "Carter";

        Name n1 = new Name(firstName1, lastName1);
        Name n2 = new Name(firstName1, lastName2);
        Name n3 = new Name(firstName2, lastName2);
        Name n4 = new Name(firstName2, lastName1);

        Name n11 = new Name(firstName1, middleName1, lastName1);
        Name n12 = new Name(firstName1, middleName2, lastName1);
        Name n21 = new Name(firstName1, middleName1, lastName2);
        Name n22 = new Name(firstName1, middleName2, lastName2);
        Name n31 = new Name(firstName2, middleName1, lastName2);
        Name n32 = new Name(firstName2, middleName2, lastName2);
        Name n41 = new Name(firstName2, middleName1, lastName1);
        Name n42 = new Name(firstName2, middleName2, lastName1);

        assertAll(
                "test for inequality with Name objects with no middle name",
                () -> assertNotEquals(n1, n2),
                () -> assertNotEquals(n1, n3),
                () -> assertNotEquals(n1, n4)
        );

        assertAll(
                "test for inequality with Name objects with a middle name",
                () -> assertNotEquals(n11, n22),
                () -> assertNotEquals(n12, n21),
                () -> assertNotEquals(n12, n22),
                () -> assertNotEquals(n11, n31),
                () -> assertNotEquals(n11, n32),
                () -> assertNotEquals(n12, n31),
                () -> assertNotEquals(n12, n32),
                () -> assertNotEquals(n11, n41),
                () -> assertNotEquals(n11, n42),
                () -> assertNotEquals(n12, n41),
                () -> assertNotEquals(n12, n42)
        );

    }

    @Test
    @DisplayName("equals should return true if objects do match")
    public void equalsShould_ReturnTrue_IfObjectsMatch() {
        String firstName = "Ali";
        String lastName = "Alaoui";
        String middleName = "Allal";

        Name noMiddleName1 = new Name(firstName, lastName);
        Name noMiddleName2 = new Name(firstName, lastName);

        Name withMiddleName1 = new Name(firstName, lastName, middleName);
        Name withMiddleName2 = new Name(firstName, lastName, middleName);

        assertEquals(noMiddleName1, noMiddleName1);
        assertEquals(noMiddleName1, noMiddleName2);
        assertEquals(withMiddleName1, withMiddleName2);
    }

}
