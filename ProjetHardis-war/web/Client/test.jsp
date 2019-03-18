<%-- 
    Document   : test
    Created on : 18 mars 2019, 22:22:28
    Author     : anastasia.salari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
      
            <%    String server = "cpanel.freehosting.com";
       String user = "lucialei";
       String pass = "rj3fTOw378";
        String remoteFile = "/public_ftp/conditions.pdf";
  String lien ="ftp://"+user+":"+pass+"@"+server+remoteFile;  %>
  
<%=lien%>
 
  <a href="<%=lien%>">ici</a>
  

  
  <iframe src="lien"></iframe>  
    </body>
</html>
