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
      
    <p class="login-box-msg">Vous avez déjà votre code ?</p>

    
    <form action="servClient" method="get">
        <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Code contrat (6 chiffres)" name="codeC">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Code entreprise" name="mdp">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
           <input type ="hidden" name="action" value="lierE">
      <div class="row">

        <!-- /.col -->
        <div class="col-xs-12">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Valider</button>
        </div>
        <!-- /.col -->
      </div>
    </form>
    
    
    
    <br> <br>
    <br>
    <br>
     <p class="login-box-msg">Créer entreprise</p>
    
    <form action="servClient" method="get">
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Nom entreprise" name="nomE">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
        
         <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="Numéro siret" name="siret">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
        
     <div class="row">
      <div  class ="col-xs-4">
        <input type="text" class="form-control" placeholder="n° rue" name="nrRue">

      </div>
        
         <div  class ="col-xs-8">
        <input type="text" class="form-control" placeholder="nom rue" name="nomRue">
  
      </div>
     </div>
        <br>
           <div class="row">
               <div class ="col-xs-6">
        <input type="text" class="form-control" placeholder="Code Postal" name="cp">
     
      </div>
          <div  class ="col-xs-6">
        <input type="text" class="form-control" placeholder="Ville" name="ville">
    
      </div>
     
           </div> <br>
 
                   <input type ="hidden" name="action" value="creationE">
      <div class="row">
        
        <!-- /.col -->
        <div class="col-xs-12">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Valider</button>
        </div>
        <!-- /.col -->
      </div>
                   <br>
                   <p> Afin de valider cette entreprise, veuillez envoyer un mail avec un justificatif à l'adresse suivante : admin@hardis.fr</p>
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

