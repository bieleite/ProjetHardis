<%-- 
    Document   : signup
    Created on : 13 mars 2019, 15:51:31
    Author     : 6170361
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Registration Page</title>
<%@include  file = "meta.jsp" %>

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition register-page">
<div class="register-box">


  <div class="register-box-body">
    <p class="login-box-msg">Création compte</p>

    <form action="servClient" method="get">
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Nom" name="nom">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
         <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Prénom" name="prenom">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="Email" name="email">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
          <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Code postal" name="cp">
        <span class="glyphicon glyphicon-globe form-control-feedback"></span>
      </div>
          <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Question secrète" name="qs">
        <span class="glyphicon glyphicon-question-sign form-control-feedback"></span>
      </div>
        
          <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Réponse secrète" name="rs">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
        
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Mot de passe" name="mdp">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Confirmer mot de passe" name="mdpC">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
                   <input type ="hidden" name="action" value="creation">
      <div class="row">
        <div class="col-xs-12" style="padding-left:10%">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"> I agree to the <a href="#">terms</a>
            </label>
          </div>
        </div>
      </div>
        <!-- /.col -->
        <div class="row">
        <div class="col-xs-12">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Valider</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 3 -->
<script src="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
</script>
</body>
</html>

