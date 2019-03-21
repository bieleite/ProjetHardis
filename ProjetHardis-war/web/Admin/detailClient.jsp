<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.Client"%>
<%@page import="Entites.Agence"%>
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
    <jsp:useBean id="client" scope="request" class = "Entites.Client"> </jsp:useBean>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@include  file = "header.jsp" %>
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
        <li class="active">Client</li>
        <li class="active">Afficher Client</li>
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
              <h3 class="box-title">Client</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form">
              <div class="box-body">
                <div class="form-group">
                  <label for="nomAgence">Id Client</label>
                  <input type="txt" name="idAgence" class="form-control" id="exampleInputEmail1" placeholder="<%=client.getId() %>"  disabled>
                  <label for="nomClient">Nom Client</label>
                  <input type="txt" name="nomAgence" class="form-control" id="exampleInputEmail1" placeholder="<%=client.getNom() %>" disabled>
                  <label for="prenomAgence">Prenom Client</label>
                  <input type="txt" name="prenomAgence" class="form-control" id="exampleInputEmail1" placeholder="<%=client.getPrenom() %>" disabled>
                  <label for="emailAgence">Email Client</label>
                  <input type="txt" name="emailAgence" class="form-control" id="exampleInputEmail1" placeholder="<%=client.getLogin() %>" disabled>
                  <label for="cpAgence">CP Client</label>
                  <input type="txt" name="cpAgence" class="form-control" id="exampleInputEmail1" placeholder="<%=client.getCodepostal() %>" disabled>
                  <label for="agenceClient">Agence Client</label>
                  <input type="txt" name="agenceClient" class="form-control" id="exampleInputEmail1" placeholder="<%=client.getAgence().getNomAgence() %>" disabled>
                  <label for="dtrgpdClient">Date RGPD Client</label>
                  <input type="txt" name="dtrgpdClient" class="form-control" id="exampleInputEmail1" placeholder="<%=client.getDateRGPD() %>" disabled>
                  <label for="visibleClient">Visible Client</label>
                  <% if (client.isVisible() ) {%>
                       <input type='txt' name='visibleClient' class='form-control' id='exampleInputEmail1' placeholder='Visible' disabled>
                       <%}%>
                 <% if (!client.isVisible() ){%>
                      <input type='txt' name='visibleClient' class='form-control' id='exampleInputEmail1' placeholder='Non Visible' disabled>
                  <%}%>
                  <label for="entrepriseClient">Entreprise Client</label>
                  <input type="txt" name="entrepriseClient" class="form-control" id="exampleInputEmail1" placeholder="<%=client.getEntreprise().getNomEntreprise() %>" disabled>
                  <label for="certifieClient">Certifie Client</label>
                  <% if (client.getCertifie() ) {%>
                       <input type='txt' name='certifieClient' class='form-control' id='exampleInputEmail1' placeholder='Certfiée' disabled>
                       <%}%>
                 <% if (!client.getCertifie() ){%>
                      <input type='txt' name='certifieClient' class='form-control' id='exampleInputEmail1' placeholder='Non Certfiée' disabled>
                  <%}%>
                  <input type="hidden" name="idAgence" value="<%=client.getId() %>">
                </div>
                
                <input type="hidden" name="action" value="ModifierClient">
             <!--   <div class="form-group">
                  <label>Select</label>
                  <select class="form-control">
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