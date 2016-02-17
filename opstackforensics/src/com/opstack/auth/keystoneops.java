package com.opstack.auth;
import java.io.*;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class keystoneops {
	public String rest_connector_get(String token,String url)
	{
		Client opsclient=Client.create();
		WebResource webResource = opsclient.resource(url);
		ClientResponse responsjson = webResource.header("Content-Type","application/json").header("X-Auth-Token",token).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		//String output = responsjson.getEntity(String.class);
		return responsjson.getEntity(String.class);
	}
//--------------------------json parser for users-----------------------------------	
	public String[] jsonparser_module(String jsonstring,String data) throws ParseException
	{
		List<String> ar = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject=(JSONObject) parser.parse(jsonstring);
		JSONArray arr = (JSONArray)jsonObject.get("users");
		 for(int i = 0; i < arr.size(); i++) {
			 JSONObject p = (JSONObject)arr.get(i);
			 String q= p.get(data).toString();
			 ar.add(q);
		     //System.out.println(q);
		    }		
		 String[] stringArray = ar.toArray(new String[ar.size()]);
		 return stringArray;
	}
//----------------------------------------------------------------------------------	
	public String get_identity(String authtoken,String authurl) throws ParseException
	{
		String newurl1=authurl.replace("tokens","users");
		String newurl=newurl1.replace("5000","35357");
		String jsonout;	
		jsonout=rest_connector_get(authtoken,newurl);
		//System.out.println(jsonout);
	    return jsonout;
	}
	public  String[] get_userdata(String jsonin,String data) throws ParseException
	{
		String[] stringArray=jsonparser_module(jsonin,data);
		return stringArray;
	}
}
