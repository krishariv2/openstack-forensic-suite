package com.opstack.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

//import javax.ws.rs.core.MediaType;
//
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
/**
 * Servlet implementation class loginaction
 */
@WebServlet("/loginaction")
public class loginaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String authurl= request.getParameter("authurl");
		String username = request.getParameter("user");
        String password = request.getParameter("password");
        System.out.println("authurl:"+authurl);
        //System.out.println("username:"+username);
        //System.out.println("password:"+password);
        PrintWriter out = response.getWriter();

 //-------------------------Creating web client for api calls--------------------------------------------
        Client client = Client.create();
 //       "http://127.0.0.1:5000/v2.0/tokens"
        		WebResource webResource = client.resource(authurl);
  //      		String input = "{\"auth\":{\"passwordCredentials\":{\"username\":\""+username+"\",\"password\":\""+password+"\"}}}";
        		String input = "{\"auth\":{\"tenantName\":\"admin\",\"passwordCredentials\":{\"username\":\""+username+"\",\"password\":\""+password+"\"}}}";
        		ClientResponse response1 = webResource.header("Content-Type","application/json").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,input);
                
        		if (response1.getStatus() != 200) {
//        		   throw new RuntimeException("Failed : HTTP error code : "
//        			+ response1.getStatus());
        			String message = "Login Failed try again!";
        			response.sendRedirect("index.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
        		   return;
        		}
//        
       		String output = response1.getEntity(String.class);
//        //-------------------Process jason response-----------------------------------------------		
        		JSONParser parser = new JSONParser();
        		JSONObject jsonObject = null;
				try {
					jsonObject = (JSONObject) parser.parse(output);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		JSONObject access     = (JSONObject) jsonObject.get("access");
        		JSONObject token = (JSONObject) access.get("token");
        		String     token_id = (String) token.get("id").toString(); 
        		System.out.println(token_id);
        		System.out.println("Output from Server .... \n");
        		System.out.println(output);
        		//out.print(output);
        //----------------------Set session----------------------------------------
        		HttpSession session=request.getSession();  
        		session.setAttribute("ID", token_id);
        		session.setAttribute("Name", username);
        		session.setAttribute("cloudurl", authurl);
        		response.sendRedirect("home.jsp");
 //------------------------------------------------------------------------------       
        out.flush();
	}

}