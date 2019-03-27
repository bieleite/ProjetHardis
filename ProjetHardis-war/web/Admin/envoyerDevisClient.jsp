<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.Document"%>
<%@page import="Entites.Facture"%>
<%@page import="Entites.UtilisateurHardis"%>
<%@page import="Entites.TypeService"%>
<%@page import="Entites.Statut"%>
<%@page import="Entites.HistoriqueEtats"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entites.HistoriqueTraitement"%>
<%@page import="Entites.Service"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entites.Agence"%>
<%@page import="Entites.Adresse"%>
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
    <jsp:useBean id="devistraitement" scope="request" class = "Entites.Devis"> </jsp:useBean> 
    <jsp:useBean id="listeCommunication" scope="session" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeNotif" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeClient" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeDocument" scope="request" class= "java.util.List"></jsp:useBean>
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
        <li><a href="servAdmin?action=Menu"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="servAdmin?action=Menu"> Tableau de Bord</a></li>
        <li><a href="servAdmin?action=listesDevis"> Devis</a></li>
        <li><a href="#"> Afficher Devis</a></li>
      </ol>
    </section>
<% SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");%>
    <!-- Main content -->
    
    <section class="content">
      <!-- Small boxes (Stat box) -->
       
      <!-- /.row -->
      <!--Form Recherche-->
      <!-- Main row -->
      <div class="row">
        <!-- left column -->
        <!-- Div Class Box -->
         
            <% List<Document> lesDocument = listeDocument; %>
        <!--/.col (right) -->
      </div>
      <!-- /.row (main row) -->
      <div class="col-md-9">
          
          <form role="form">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Envoyer Devis</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div class="form-group">
                  <p>To:<input name="emailto" class="form-control" placeholder="<%=devistraitement.getClient().getLogin().toString() %>" value="<%=devistraitement.getClient().getLogin().toString()%>"></p>
              </div>
              <div class="form-group">
             <%     String date = (String) request.getAttribute("date");%>
                  <p>Subject: <input name="subject" class="form-control" placeholder="Proposition du devis DEV:<%=devistraitement.getId().toString() %> valable:<%=date%>" value="Proposition du devis DEV:<%=devistraitement.getId().toString() %> valable:<%=date%>"></p>
              </div>
              <div class="form-group">
                  <textarea name="textmail" id="compose-textarea" class="form-control" style="height: 300px" placeholder="Bonjour <%=devistraitement.getClient().getNom() %>, &#10;Ci-joint votre proposition li&eacute;e au devis DEV:<%=devistraitement.getId().toString() %> valable jusqu'au :<%=date%>.&#10;Cordialement, &#10;<%=utilisateur.getNom() %> " value="Bonjour <%=devistraitement.getClient().getNom() %>, &#10;Ci-joint votre proposition li&eacute;e au devis DEV:<%=devistraitement.getId().toString() %> valable jusqu'au :<%=date%>.&#10;Cordialement, &#10;<%=utilisateur.getNom() %> "></textarea>
                   
              </div>
              <%     String docenvoye = (String) request.getAttribute("docenvoye");%>
              <div class="form-group">                
                <p> Attaché<i class="fa fa-paperclip"></i></p>           
                <p class="help-block"> <i class="fa fa-paperclip"></i><%=docenvoye%></p>
              </div>
              <%     String textemail = (String) request.getAttribute("textmail");%>
              <input type="hidden" name="textemail" value="<%=textemail %>">
              <input type="hidden" name="iddev" value="<%=devistraitement.getId() %>">
                  <input type="hidden" name="action" value="EnvoyerDevisMail">
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
            
                  
                <button type="submit" class="btn btn-primary"><i class="fa fa-envelope-o"></i> Envoyer</button>
              

            </div>
             </form>
            <!-- /.box-footer -->
          
         
          <!-- /. box -->
        </div>
      
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
