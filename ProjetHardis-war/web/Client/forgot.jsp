<%-- 
    Document   : login
    Created on : 13 mars 2019, 15:45:14
    Author     : 6170361
--%>

<%@page import="Entites.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <jsp:useBean id="client" scope="request" class = "Entites.Client"> </jsp:useBean>
  <title>AdminLTE 2 | Log in</title>
<%@include  file = "meta.jsp" %>


</head>
<body class="hold-transition login-page">
<div class="login-box">
 
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Connexion</p>
    <p style="color:red"> 
        <% Client cli = client;
         String message = (String)request.getAttribute("messageErreur");
    if (message!="" && message!=null)
    {
      out.println(message);
}
        String valide = (String)request.getAttribute("valide");
            %> </p>
    <form action="servClient" method="get">
          <% if (cli.getId()==null){%>
      <div class="form-group has-feedback">
        
        <input type="email" class="form-control" placeholder="Email" name="email">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>  
      <%} else if (cli.getId()!=0 && valide==null){%>
      <div class="form-group has-feedback">
          <p> <%out.print(cli.getQuestionSecrete()); %></p>
        
          <input type="text" class="form-control" placeholder="" name="repS" >
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <%} else if (valide!=null && valide!="") {%>
       <div class="form-group has-feedback">      
          <input type="password" class="form-control" placeholder="Nouveau mdp" name="mdp" >
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
        <div class="form-group has-feedback">      
          <input type="password" class="form-control" placeholder="Nouveau mdp confirm" name="mdp1" >
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div> <%}%>
       
      <div class="row">
   
        <!-- /.col -->
        <div class="col-xs-12">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Valider</button>
        </div>
        <!-- /.col -->
      </div>
              <input type ="hidden" name="action" value="forgot">
    </form>

   
  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<%@include  file = "script.jsp" %>
</body>
</html>
