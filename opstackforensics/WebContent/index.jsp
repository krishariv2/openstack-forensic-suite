 <%@include file="header.jsp" %>
	
      <div class="form-container loginpage">
      
        <form action="loginaction" method="post">
          <table style="border:none!important">
            <tr style="border:none!important">
              <td style="border:none!important"><p>Auth URL:</p></td>
              <td style="border:none!important"><input type="text" name="authurl" class="auth"></td>
            </tr>
           <tr style="border:none!important">
              <td style="border:none!important"><p>User Name:</p></td>
              <td style="border:none!important"><input type="text" name="user" class="user"></td>
            </tr>
           <tr style="border:none!important">
              <td style="border:none!important"><p>Password:</p></td>
              <td style="border:none!important"><input type="password" name="password" class="pass"></td>
          </tr>
          <tr>
              <td style="border:none!important">&nbsp;</td>
              <td style="border:none!important"><input type="submit" value="Login" name="subject" class="sub"></td>
          </tr>
          </table>
        </form>
        <div class="form-error-message"><p style="color:red">${param.message}</p></div>
      </div>
 <%@include file="footer.jsp" %>  