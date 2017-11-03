package com.jaldishopping.vo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



import android.content.Context;
import android.util.Log;

public class PayeeUtility 
{
	private static ArrayList<PTypeVO> payeeDetails;

		public static void init(Context context)
		{
			getPayeeDetails(context);
		}
		
		public static ArrayList<PTypeVO> getPayeeDetails(Context context)
		{
			if (payeeDetails == null)
			{
				payeeDetails = new ArrayList<PTypeVO>();
			}
			
			
			try {
				Document doc = parseXML(readFromFile("JaldiShoppingCartXML.xml",context));
				NodeList node = doc.getElementsByTagName("Item");
				
				for (int i = 0; i<node.getLength(); i++)
				{
					
					PTypeVO ptVO = new PTypeVO();
					ArrayList<PNameVO> payeeDetail = new ArrayList<PNameVO>();
					
					Element e = (Element) node.item(i);
					
					Element bankNameE = (Element) e.getElementsByTagName("ItemName").item(0);

					String bankNameS = bankNameE.getLastChild().getNodeValue();

					ptVO.setItemName(bankNameS);

					NodeList node2 = e.getElementsByTagName("Function");

					
					for (int j=0; j<node2.getLength(); j++)
					{	
						PNameVO pVO = new PNameVO();
						Log.d("TestTag", "Value of length"+node2.getLength());
						Element payeeElement = (Element)node2.item(j);

						Element itemIdeEl = (Element) e.getElementsByTagName("Size").item(0);
						Element itemTypeEl = (Element) e.getElementsByTagName("BrandName").item(0);
						Element priceEl = (Element) e.getElementsByTagName("Price").item(0);
						Element offerEl = (Element) e.getElementsByTagName("Offer").item(0);
						Element locationEl = (Element) e.getElementsByTagName("Location").item(0);

						pVO.setQuantity(itemIdeEl.getLastChild().getNodeValue());
						pVO.setItemType(itemTypeEl.getLastChild().getNodeValue());
						pVO.setPrice(priceEl.getLastChild().getNodeValue());
						pVO.setOffer(offerEl.getLastChild().getNodeValue());
						pVO.setLocation(locationEl.getLastChild().getNodeValue());
						
						payeeDetail.add(pVO);
					}
					

					
					ptVO.setPayeeDetails(payeeDetail);
					payeeDetails.add(ptVO);
					
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return payeeDetails;
			
		}

		public static Document parseXML(InputSource source) throws Exception 
		{
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			Document doc = null;
			
			try {
				db = dbf.newDocumentBuilder();
				doc = db.parse(source);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			}
			
			return doc;
		}// End of parseXML

		public static InputSource readFromFile(String fileName, Context context) 
		{
			InputSource inputSource = null;
			InputStream fIn = null;
			
			try {
				fIn = context.getResources().getAssets().open(fileName);
				inputSource = new InputSource(fIn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return inputSource;
		}// End of ReadFromFile

}// End PayeeUtility
