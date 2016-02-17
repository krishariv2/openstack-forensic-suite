<%@page import="com.opstack.auth.*"  %>
<%

   if(session.getAttribute("ID")==null)
   {
	   out.print("you dont have permission to view this page");
	   return;
   }
   else
   {  
	   String token=(String)session.getAttribute("ID");
       String authurl=(String)session.getAttribute("cloudurl");
	   keystoneops ops=new keystoneops();
	   String userinfo=ops.get_identity(token,authurl);
	   String[] usernames=ops.get_userdata(userinfo,"name");
	   String[] userids=ops.get_userdata(userinfo,"id");
	      
%>
 <%@include file="header.jsp" %> 
  <div class="left-menu">
    <ul class="main-menu-bar">
      <li><a href="index.html">Projects</a></li>
      <li><a href="instance.html">Instances</a></li>
      <li><a href="userlog.html">Logs</a></li>
      <li><a href="Apilogs.html">Api logs</a></li>
      <li><a href="listimages.html">images</a></li>
      <li><a href="volume.html">volume Informations</a></li>
    </ul>
  </div>
	<div class="right-container">
      <div class="table-container">
        <div class="user-info">
           <p><b>List of Projects</b></p>
        </div>
        <table class="userdatas" width="900" border="0">
          <tr>
            <th class="tblheader"><p>Users</p></th>
            <th class="tblheader"><p>User ID</p></th>
            <th class="tblheader"><p>Projects</p></th>
            <th class="tblheader"><p>Instances</p></th>
          </tr>
    <%
     for(int i=0;i<usernames.length;i++)
     {
    %>      
          <tr>
            <td><div><p><a href="#"><% out.println(usernames[i]);%></a></p></div></td>
            <td><div><p><% out.println(userids[i]);%></p></div></td>
            <td><div class="user-icon"><p>user1</p></div></td>
            <td><div><p><a href="#">Ubuntu1</a></p></div></td>
          </tr>
    <%
     }
    %>
        </table>
      </div>
  </div>
  
 <%@include file="footer.jsp" %>  
 <% } %>
 