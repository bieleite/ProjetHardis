<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.ServiceStandard"%>
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
    <jsp:useBean id="listeServiceStandard" scope="request" class = "java.util.List"> </jsp:useBean>


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
        <li class="active">Adresse</li>
        <li class="active">Creer Adresse</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <% List<ServiceStandard> lesServiceStandard=listeServiceStandard; %> 
      <!-- /.row -->
      <!-- Main row -->
      <div class="row">
        <!-- left column -->
        <div class="box">
            <div class="box-header">
              <h3 class="box-title">Service Standard</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Type Service</th>
                  <th>Nom Service </th>
                  <th>Description Service</th>
                  <th>Conditions Contrat</th>
                  <th>Coût Service</th>
                  <th>Nombre d'Heures Entretien</th>
                  <th>Nombre d'Heures Support Telephone</th>
                  <th>Nombre Jours Consultant Junior</th>
                  <th>Nombre Jours Consultant Confirmé</th>
                  <th>Nombre Jours Consultant Senior</th>
                  <th>Lieu d'Intervention</th>
                  <th>Facture Frais</th>
                </tr>
                </thead>
                <% for (ServiceStandard a : lesServiceStandard){%>
                <tbody>
                <tr>
                    <td><%=a.getTypeService().toString() %></td>
                    <td><%=a.getNomService() %></td>
                    <td><%=a.getDescriptionService() %></td>
                    <td><%=a.getConditionsContract() %></td>
                    <td><%=a.getCoutService() %></td>
                    <td><%=a.getNbreHeuresEntretien() %></td>
                    <td><%=a.getNbreHeuresSupportTel() %></td>
                    <td><%=a.getNbreJoursConsultantJ() %></td>
                    <td><%=a.getNbreJoursConsultantC() %></td>
                    <td><%=a.getNbreJoursConsultantS() %></td>
                    <td><%=a.getLieuInterv().toString() %></td>
                    <td><%=a.getFactFrais().toString() %></td>
                </tr>
                </tbody>
                <%}%>
                <tfoot>
                <tr>
                   <th>Type Service</th>
                  <th>Nom Service </th>
                  <th>Description Service</th>
                  <th>Conditions Contrat</th>
                  <th>Coût Service</th>
                  <th>Nombre d'Heures Entretien</th>
                  <th>Nombre d'Heures Support Telephone</th>
                  <th>Nombre Jours Consultant Junior</th>
                  <th>Nombre Jours Consultant Confirmé</th>
                  <th>Nombre Jours Consultant Senior</th>
                  <th>Lieu d'Intervention</th>
                  <th>Facture Frais</th>
                </tr>
                </tfoot>
              </table>
            </div>
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
