<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.Offre_Profil_Util_CV"%>
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
    <jsp:useBean id="listeCommunication" scope="session" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeNotif" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeClient" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="devistraitement" scope="session" class = "Entites.Devis"> </jsp:useBean>
    <jsp:useBean id="listeCommunicationDevis" scope="request" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeHTVide" scope="request" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeConsultantOffre" scope="request" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeLibC" scope="request" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="PrixU" scope="request" class= "java.util.List"></jsp:useBean>
     <jsp:useBean id="nombreJour" scope="request" class= "Float"></jsp:useBean>
    

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
      <% List<Devis> lesDeevis=listeDevis; %> 
      <!-- /.row -->
      <!--Form Recherche-->
      <!-- Main row -->
      <div class="row">
        <!-- left column -->
        <!-- Div Class Box -->
         
          
        <!--/.col (right) -->
      </div>
      <!-- /.row (main row) -->
      <div class="col-lg-12 connectedSortable">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Devis</h3>
              
                    <% List<UtilisateurHardis> lesConsultants=listeConsultantOffre; %>
                    
              
              
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            
              <div class="box-body">
                  <form role="form">
                <div class="form-group">
                  <label for="idDevis">Id Devis</label>
                  <input type="txt" name="idDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getId() %>"  disabled>
                  
                 
                  <%  if (!lesConsultants.isEmpty()){%>
                  <table class="table table-hover">
                      
                <tr>
                  <th>ID</th>
                  <th>Nom</th>
                  <th>Agence</th>
                  <th>Niveau</th>
                  <th>Prix Heure</th>
                </tr>
                <%  List<String> lesExpertise = listeLibC ; 
                List<Float> lesPrixU = PrixU ;
                    for (int i=0; i<lesConsultants.size(); i++){%>
                  <tr> <% UtilisateurHardis ht = lesConsultants.get(i);%>
                  <td><%=ht.getId() %></td>
                  <td><%=ht.getNom() %></td>
                  <td><%=ht.getAgence().getNomAgence() %></td>
                                
                  <td><span class="label label-success"><%=lesExpertise.get(i) %></span></td>
                  <td><%=lesPrixU.get(i)  %></td>
                  <td><input type="checkbox" name="idConsultant" value="<%=ht.getId() %>"></td>
                </tr>      <%}%>          
              </table><%}%>
              
                  
                  
                <input type="hidden" name="idcli" value="<%=devistraitement.getClient().getId() %>">
                  <input type="hidden" name="iddev" value="<%=devistraitement.getId() %>">
                  <input type="hidden" name="numJour" value="<%=nombreJour %>">
                  <input type="hidden" name="idage" value="<%=devistraitement.getAgence().getId() %>">
                 
                  
                  <input type="hidden" name="action" value="AffecterConsultantAUnDevis">
                </div>
                
                
             

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
