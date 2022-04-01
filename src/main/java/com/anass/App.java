package com.anass;

import com.anass.etudiant.Etudiant;
import com.anass.filiere.Filiere;
import java.io.File;
import java.time.LocalDate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class App {

    public static void main(String[] args) throws JAXBException {
        LocalDate age = LocalDate.now().minusYears(18);
        System.out.println(age);
        Etudiant e = new Etudiant("R123456789", new Filiere(102, "DSBD"), "IRIZI", "Anass", age);
        e.setId(101);
        System.out.println("-- serialisation de etudiant");
        serialiser(e);
        System.out.println("-- déserialisation de etudaint");
        deserialiser("employee.xml");

    }

    private static void deserialiser(String employeexml) throws JAXBException {
        File XmlFile = new File(employeexml);
        JAXBContext jAXBContext;
        jAXBContext = JAXBContext.newInstance(Etudiant.class);
        Unmarshaller jaxbUnmarshaller = jAXBContext.createUnmarshaller();
        Etudiant e = (Etudiant) jaxbUnmarshaller.unmarshal(XmlFile);
        System.out.println("Affichage de l'objet créé ");
        System.out.println(e);
    }

    private static void serialiser(Etudiant e) throws JAXBException {
        JAXBContext jAXBContext = JAXBContext.newInstance(Etudiant.class);
        Marshaller jaxbMarshaller = jAXBContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.marshal(e, new File("employee.xml"));
    }
}
