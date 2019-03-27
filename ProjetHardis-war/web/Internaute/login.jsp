<%-- 
    Document   : login
    Created on : 13 mars 2019, 15:45:14
    Author     : 6170361
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Log in</title>
<%@include  file = "meta.jsp" %>


</head>
<body class="hold-transition login-page">
<div class="login-box">
 
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Connexion</p>
    <p style="color:red"> 
        <% 
            String message = (String)request.getAttribute("messageErreur");
    if (message!="" && message!=null)
    {
      out.println(message);
}%> </p>
    
  
  
  
    <form action="servClient" method="get">
      <div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="Email" name="email">
        <span class=""></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Password" name="mdp">
        <span class=""></span>
      </div>
      <div class="row">
   
        <!-- /.col -->
        <div class="col-xs-12">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
        </div>
        <!-- /.col -->
      </div>
              <input type ="hidden" name="action" value="connexion">
    </form>

    <a href="servClient?action=forgot">Mot de passe oublié ?</a><br>
    <a href="servInternaute?action=register" class="text-center">S'enregistrer</a>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<%@include  file = "script.jsp" %>
</body>
</html>
