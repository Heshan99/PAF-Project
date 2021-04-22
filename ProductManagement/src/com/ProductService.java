package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Product;

@Path("/Products")

public class ProductService {
	
	 Product prodObj = new Product();
	 
	 @GET
	 @Path("/")
	 @Produces(MediaType.TEXT_HTML)
	 public String readItems()
	  {
	  return prodObj.readItems();
	  }
	 
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertItem(@FormParam("productCode") String itemCode,
	 @FormParam("productName") String itemName,
	 @FormParam("productPrice") String itemPrice,
	 @FormParam("productDesc") String itemDesc)
	 {
	 String output = prodObj.insertItem(itemCode, itemName, itemPrice, itemDesc);
	 return output;
	 }
	 
	 
	 
	 @PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateItem(String itemData)
	 {
	 //Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	 //Read the values from the JSON object
	 String itemID = itemObject.get("productId").getAsString();
	 String itemCode = itemObject.get("productCode").getAsString();
	 String itemName = itemObject.get("productName").getAsString();
	 String itemPrice = itemObject.get("productPrice").getAsString();
	 String itemDesc = itemObject.get("productDesc").getAsString();
	 String output = prodObj.updateItem(itemID, itemCode, itemName, itemPrice, itemDesc);
	 return output;
	 }
	 
	 
	 
	 @DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteItem(String itemData)
	 {
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	 //Read the value from the element <itemID>
	 String itemID = doc.select("productId").text();
	 String output = prodObj.deleteItem(itemID);
	 return output;
	 }
	 
	 
	 
	 
	 

}
