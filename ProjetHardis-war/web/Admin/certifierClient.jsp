<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.Agence"%>
<%@page import="Entites.UtilisateurHardis"%>
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
    <jsp:useBean id="listeCommunication" scope="session" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeNotif" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeClient" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listClient" scope="request" class = "java.util.List"> </jsp:useBean>
        <jsp:useBean id="listAgence" scope="request" class = "java.util.List"> </jsp:useBean>



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
        <li class="active">Client</li>
        <li class="active">Certifier Client</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <% List<Client> lesClients=listClient;
      List<Agence> listeA = listAgence;%> 
      <!-- /.row -->
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="mail" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="action" value="RechercherClient" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- Main row -->
      <div class="row">
        <!-- left column -->
        <div class="box">
            <div class="box-header">
              <h3 class="box-title">Clients</h3>
            </div>
            <!-- /.box-header -->
            <form role="form">
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Nom Client</th>
                  <th>Prenom Client </th>
                  <th>Agence Client</th>
                  <th>Mail Client</th>
                  <th>Entreprise Client</th>
                  <th>Certfier Client</th>
                </tr>
                </thead>
                <% for (Client a : lesClients){%>
                <tbody>
                <tr>
                  <td><%=a.getNom() %></td>
                  <td><%=a.getPrenom() %></td>
              
                  <td><select  name="agence">
                      <% for (Agence ag : listeA) {%>
                    <option value="<%=ag.getId()%>"><%=ag.getNomAgence()%></option>
                   <%}%>
                      </select></td>
                  <td><%=a.getLogin() %></td>
                  <td><%=a.getEntreprise().getNomEntreprise() %></td>
                  <td><input type="checkbox" name="idClient" value="<%=a.getId() %>"></td>
                </tr>
                </tbody>
                <%}%>
                <tfoot>
                <tr>
                  <th>Nom Client</th>
                  <th>Prenom Client </th>
                  <th>Agence Client</th>
                  <th>Mail Client</th>
                  <th>Entreprise Client</th>
                  <th>Certfier Client</th>
                </tr>
                </tfoot>
              </table>
                <input type="hidden" name="action" value="CertifierClient">
                <button type="submit" class="btn btn-primary">Certifier</button>
            </div>
                 </form>
            <!-- /.box-body -->
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
