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

import model.User;

@Path("/User")
public class UserManagement {

	User userObj = new User();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)    
	public String readItems()
	{
	return userObj.readItems();
	}	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("userName") String itemCode,
	@FormParam("userEmail") String itemName,
	@FormParam("userPassword") String itemPrice,
	@FormParam("userPhoneNo") String itemDesc)
	{
	String output = userObj.insertItem(itemCode, itemName, itemPrice, itemDesc);
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
	String itemID = itemObject.get("userId").getAsString();
	String itemCode = itemObject.get("userName").getAsString();
	String itemName = itemObject.get("userEmail").getAsString();
	String itemPrice = itemObject.get("userPassword").getAsString();
	String itemDesc = itemObject.get("userPhoneNo").getAsString();
	String output = userObj.updateItem(itemID, itemCode, itemName, itemPrice, itemDesc);
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
	String userID = doc.select("userId").text();
	String output = userObj.deleteItem(userID);
	return output;
	}
	
}
