package com.sangamone.jaldishopping.utils;


import java.io.StringReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.sangamone.jaldishopping.controller.Response;
import com.sangamone.jaldishopping.controller.Row;



public class XmlParser {
	
	public static Response parse(String xml) {

		xml = "<Response>" + xml + "</Response>";

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Class[] bound = { Response.class, Row.class };
		marshaller.setClassesToBeBound(bound);
		Source source = new StreamSource(new StringReader(xml));

		Response xMLResponse = (Response) marshaller.unmarshal(source);

	   /*System.out.println("xmlresponse:::"+xMLResponse.row.get(0).getRequestOpeartor());
		*/
		
		return xMLResponse;

	}

}
