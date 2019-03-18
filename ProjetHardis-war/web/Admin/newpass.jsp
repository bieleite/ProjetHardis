<%-- 
    Document   : Login
    Created on : 13 mars 2019, 16:16:18
    Author     : 6171217
--%>

<%@page import="Entites.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Log in</title>
<%@include  file = "meta.jsp" %>
<jsp:useBean id="utilisateur" scope="session" class="Utilisateur"></jsp:useBean>

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
<div class="login-box"> 
    <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
                <h4><i class="icon fa fa-info"></i> 
                    <% String attribut = (String) request.getAttribute("message");
                out.println(attribut); %>
                </h4>
    </div>

  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Connexion <%=utilisateur.getNom()%></p>

    <form action="servAdmin" method="get">
      <div class="form-group has-feedback">
        <input type="login" class="form-control" placeholder="Mot de pass" name="pass">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="row">
   
        <!-- /.col -->
        <div class="col-xs-12">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Submit</button>
        </div>
        <!-- /.col -->
      </div>
              <input type ="hidden" name="action" value="ModifierPass">
    </form>


  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<%@include  file = "script.jsp" %>
</body>
</html>
