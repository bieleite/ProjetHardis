<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.Atelier"%>
<%@page import="Entites.Livrable"%>
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
    <jsp:useBean id="listoffre" scope="request" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listatelier" scope="request" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listlivrable" scope="request" class = "java.util.List"> </jsp:useBean>
   


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
        <li class="active">Service Standard</li>
        <li class="active">Creer Service Standard</li>
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
                  <label for="nomService">Nom Service</label>
                  <input type="txt" name="nomService" class="form-control" id="exampleInputEmail1" placeholder="Nom Service">
                </div>
                <div class="form-group">
                  <label for="descriptionService">Description Service</label>
                  <input type="txt" name="descriptionService" class="form-control" id="exampleInputEmail1" placeholder="Description Service">
                </div>
                <div class="form-group">
                  <label for="descPrestaService">Description Prestation Service</label>
                  <input type="txt" name="descPrestaService" class="form-control" id="exampleInputEmail1" placeholder="Description Prestation Service">
                </div>
                <div class="form-group">
                  <label for="nbHSService">Nombre d'Heures du Service</label>
                  <input type="txt" name="nbHSService" class="form-control" id="exampleInputEmail1" placeholder="Nombre d'Heures du Service">
                </div>
                <div class="form-group">
                  <label for="nbJJService">Nombre d'Heures Consultant Junior</label>
                  <input type="txt" name="nbJJService" class="form-control" id="exampleInputEmail1" placeholder="Nombre d'Heures Consultant Junior">
                </div>
                <div class="form-group">
                  <label for="nbJCService">Nombre d'Heures Consultant Confirmé</label>
                  <input type="txt" name="nbJCService" class="form-control" id="exampleInputEmail1" placeholder="Nombre d'Heures Consultant Confirmé">
                </div>
                <div class="form-group">
                  <label for="nbJSService">Nombre d'Heures Consultant Senior</label>
                  <input type="txt" name="nbJSService" class="form-control" id="exampleInputEmail1" placeholder="Nombre d'Heures Consultant Senior">
                </div>
                <% List<Livrable> lesLivrables=listlivrable; %>              
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Livrable</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Nom Livrable</th>
                  <th>Check</th>
                </tr>
				<% for (Livrable l : lesLivrables){%>
                <tr>
                  <td><%=l.getNomLivrable() %></td>
                  <td><input type="checkbox" name="listlivrable" value="<%=l.getId()%>"></td>
                </tr><%}%>
                
              </table>
            </div>
            <!-- /.box-body -->
          </div>
                 
                      <% List<Atelier> lesAteliers=listatelier; %>              
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title">Ateliers</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table class="table table-bordered">
                <tr>
                  <th>Nom Atelier</th>
                  <th>Check</th>
                </tr>
				<% for (Atelier a : lesAteliers){%>
                <tr>
                  <td><%=a.getNomAtelier() %></td>
                  <td><input type="checkbox" name="listatelier" value="<%=a.getId()%>"></td>
                </tr><%}%>
                
              </table>
            </div>
            <!-- /.box-body -->
          </div>   
                <div class="form-group">
                  <label for="nbHAService">Nombre d'Heures d'Atelier</label>
                  <input type="txt" name="nbHAService" class="form-control" id="exampleInputEmail1" placeholder="Nombre d'Heures d'Atelier">
                </div>
                  
                <label for="lieuInterv">Lieu d'intervention</label>
                <select class="form-control" name="lieuInterv">
                    <option value="Agence_Hardis">Agence Hardis</option>
                    <option value="Mixte">Mixte</option>
                    <option value="Site_Client">Site Client</option>
                </select>
                <label for="offre">Offre</label>
                <select class="form-control" name="offre">
                    <% List<Offre> lesOffres=listoffre; %>
                     <%  for (Offre o : lesOffres){%>
                      <option value="<%=o.getId() %>" ><%=o.getLibelle() %></option>
                      <%}%>
                </select>
                <div class="form-group">
                  <label for="cout">Coût</label>
                  <input type="txt" name="cout" class="form-control" id="exampleInputEmail1" placeholder="Coût">
                </div>
                <label for="facturation">Facturation des Frais</label>
                <select class="form-control" name="facturation">
                    <option value="Oui">Oui</option>
                    <option value="Non">Non</option>
                </select>
                <div class="form-group">
                  <label for="listeCond">Liste de conditions</label>
                  <input type="text" name="listeCond" class="form-control" id="exampleInputEmail1" placeholder="Liste de conditions">
                </div>
                <div class="form-group">
                  <label for="delai">Delai</label>
                  <input type="text" name="delai" class="form-control" id="exampleInputEmail1" placeholder="Delai">
                </div>
<!--                <label for="typeS">Type de Service</label>
                <select class="form-control" name="typeS">
                    <option value="Standard">Standard</option>
                    <option value="Non_Standard">Non Standard</option>
                </select>-->
                
                <input type="hidden" name="action" value="InsererServiceStandard">
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
