package com.cnsbd.aptitudo.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class XMLUtils {

    public static <T> Object parse(Class<T> className, String xmlString) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(className);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            // RobiSmsResData robiSmsResData = (RobiSmsResData) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
            return jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
