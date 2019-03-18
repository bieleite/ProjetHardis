<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.Offre"%>
<%@page import="Entites.Agence"%>
<%@page import="Entites.ProfilMetier"%>
<%@page import="Entites.Client"%>
<%@page import="Entites.Devis"%>
<%@page import="Entites.Utilisateur"%>
<%@page import="Entites.Notification"%>
<%@page import="Entites.Communication"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Hardis Group -¨Profil Administrateur</title>
  <!-- Tell the browser to be responsive to screen width -->
  <%@include  file = "meta.jsp" %>
    <jsp:useBean id="utilisateur" scope="session" class="Utilisateur"></jsp:useBean>
    <jsp:useBean id="listeCommunication" scope="session" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeNotif" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeClient" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listagence" scope="request" class = "java.util.List"> </jsp:useBean>
     <jsp:useBean id="listOffres" scope="request" class = "java.util.List"> </jsp:useBean>
   


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="servAdmin?action=Menu" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>H</b>G</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Hardis</b>GROUP</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <%@include  file = "head.jsp" %>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <%@include  file = "aside.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Tableau de bord
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Tableau de bord</li>
        <li class="active">Paramètres</li>
        <li class="active">Utilisateur</li>
        <li class="active">Creer Utilisateur</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      
      <!-- /.row -->
      <!-- Main row -->
      <div class="row">
        <!-- left column -->
   
          <!-- general form elements -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Quick Example</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form">
              <div class="box-body">
                <div class="form-group">
                  <label for="nomUtilisateur">Nom</label>
                  <input type="txt" name="nomUtilisateur" class="form-control" id="exampleInputEmail1" placeholder="Nom">
                </div>
                <div class="form-group">
                  <label for="prenomUtilisateur">Prenom</label>
                  <input type="txt" name="prenomUtilisateur" class="form-control" id="exampleInputEmail1" placeholder="Prenom">
                </div>
                <div class="form-group">
                  <label for="loginUtilisateur">Login</label>
                  <input type="txt" name="loginUtilisateur" class="form-control" id="exampleInputEmail1" placeholder="Login">
                </div>
                <div class="form-group">
                  <label for="mdpUtilisateur">Mot de Passe</label>
                  <input type="pass" name="mdpUtilisateur" class="form-control" id="exampleInputEmail1" placeholder="Mot de Passe">
                </div>
                
                <label for="PFUtilisateur">Profil Technique</label>
                <select class="form-control" name="PFUtilisateur">
                    <option value="Admin">Administrateur</option>
                    <option value="Gestion">Gestionnaire</option>
                    <option value="Visiteur">Visualisateur</option>
                </select>
                  <label for="PFUtilisateur">Profil Métier</label>
                   <label for="PFUtilisateur">Offre</label>
                <select class="form-control" name="offre">
                    <% List<Offre> listeO = listOffres;
                    for (Offre o :listeO ){%>
                    <option value="<%=o.getId()%>"><%=o.getLibelle()%></option>
                     <%}%>
                </select>
                   <label for="PFUtilisateur">Niveau</label>
                   
                   <select class="form-control" name="niveau">
                    <option value="Porteur">Porteur</option>
                    <option value="Referent">Référent</option>
                    <option value="Consultant">Consultant</option>
                </select>
                   
                    <select class="form-control" name="expertise">
                    <option value="Junior">Junior</option>
                    <option value="Senior">Senior</option>
                    <option value="Confirme">Confirmé</option>
                </select>
                   
                     <div class="form-group">
                      <label for="plafond">Montant délégation</label>
                  <input type="text" name="plafond" class="form-control" id="exampleInputEmail1" placeholder="plafond">
                </div>
                
                <label for="agence">Agence</label>
                <select class="form-control" name="agence">
                    <% List<Agence> lesAgences=listagence; %>
                     <%  for (Agence a : lesAgences){%>
                      <option value="<%=a.getId() %>" ><%=a.getNomAgence() %></option>
                      <%}%>
                </select>
                <input type="hidden" name="action" value="InsererUtilisateurHardis">
             <!--   <div class="form-group">
                  <label for="stadeEquipe">Select</label>
                  <select class="form-control" name="stadeEquipe">
                    <option>option 1</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                  </select>
                </div>
                <!-- checkbox 
                <div class="form-group">
                  <div class="checkbox">
                    <label>
                      <input type="checkbox">
                      Checkbox 1
                    </label>
                  </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">

                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
              </div>-->
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
              </div>
            </form>
          </div>
          <!-- /.box -->
        
        <!--/.col (left) -->
        <!-- right column -->
        
          <!-- /.box -->
        </div>
        <!--/.col (right) -->
      </div
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0.0
    </div>
    <strong>Copyright &copy; 2019 <a href="#"></a></strong> 
  </footer>

  <!-- Control Sidebar -->
   <%@include  file = "sidebar.jsp" %>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<%@include  file = "script.jsp" %>
</body>
</html>
