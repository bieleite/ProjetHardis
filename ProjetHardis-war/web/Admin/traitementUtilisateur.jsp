<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.Offre_Profil_Util_CV"%>
<%@page import="Entites.Offre"%>
<%@page import="Entites.UtilisateurHardis"%>
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
    <jsp:useBean id="listeUtilisateurHardis" scope="request" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="utili" scope="request" class = "Entites.UtilisateurHardis"> </jsp:useBean>
    <jsp:useBean id="listeOffress" scope="request" class = "java.util.List"> </jsp:useBean>
    

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
        <li class="active">Adresse</li>
        <li class="active">Paramètres</li>
        <li class="active">Creer Adresse</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <% List<Devis> lesDeevis=listeDevis; %> 
      <!-- /.row -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="mail" name="champ" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="action" value="RechercherUtilisateur" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- Main row -->
      <div class="row">
        <!-- left column -->
        <div class="box">
            <div class="box-header">
              <h3 class="box-title">Utilisateur Hardis</h3>
            </div>
             <% List<UtilisateurHardis> lesUtilisateurHardis=listeUtilisateurHardis; %>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>Nom Utilisateur</th>
                  <th>Prenom Utilisateur </th>
                  <th>Mail Utilisateur </th>
                  <th>Agence</th>
                  <th>Profil Technique</th>
                  <th>Statut</th>
                </tr>
                </thead>
                <% for (UtilisateurHardis a : lesUtilisateurHardis){%>
                <tbody>
                <tr>
                    <td><a href="servAdmin?action=formUtilisateur&idUtili=<%=a.getId().toString() %>" name="idUtili" value="<%=a.getId().toString() %>"><%=a.getNom() %></a></td>
                  <td><%=a.getPrenom() %></td>
                  <td><%=a.getLogin() %></td>
                  <td><%=a.getAgence().getNomAgence() %></td>
                  <td><%=a.getProfilTechique().toString() %></td>
                  <td><%=a.getStatut().toString() %></td>
                </tr>
                </tbody>
                <%}%>
                <tfoot>
                <tr>
                 <th>Nom Utilisateur</th>
                  <th>Prenom Utilisateur </th>
                  <th>Mail Utilisateur </th>
                  <th>Agence</th>
                  <th>Profil Technique</th>
                  <th>Statut</th>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
         
          
        <!--/.col (right) -->
      </div>
      <!-- /.row (main row) -->
      <div class="col-lg-8 connectedSortable">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Utilisateur</h3>
              <div class="btn-group">
                        
                        <a href="servAdmin?action=SupprimerUtilisateur&idUtilisateur=<%=utili.getId().toString() %>" name="idUtilisateur" value="<%=utili.getId().toString() %>">
                            <button type="submit" class="btn btn-default" title="Effacer Utilisateur"><i class="fa  fa-remove"></i></button></a>
                       
                    </div>
              
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            
              <div class="box-body">
                  <form role="form">
                <div class="form-group">
                    <div class="row"> <div class="col-md-3">
                  <label for="idDevis">Id Utilisateur</label>
                  <input type="txt" name="idDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=utili.getId() %>" disabled >
                        </div>
                        <div class="col-md-3">
                        <label for="tpDevis">Nom Utilisateur</label>
                  <input type='txt' name='tpDevis' class='form-control' id='exampleInputEmail1' placeholder="<%=utili.getNom() %>" disabled >
                  
                  </div>
                  <div class="col-md-3">
                      <label for="clientDevis">Prenom Utilisateur</label>
                  <input type="txt" name="clientDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=utili.getPrenom() %>" disabled>
                 </div>
                        <div class="col-md-3">
                  
                  <label for="agenceDevis">Mail Utilisateur</label>
                  <input type="txt" name="agenceDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=utili.getLogin() %>" disabled>
                 </div>
                    </div>
                  
                  <label for="agenceDevis">Profil Technique</label>
                  <select  name="techUtili" class="form-control">
                    <option><%=utili.getProfilTechique() %></option>
                    <option value="Visiteur" >Visiteur</option>
                    <option  value="Gestion">Gestion</option>
                    <option value="Admin">Admin</option>
                  </select> 
                  <label for="metUtili">Profil Metier</label><a href="servAdmin?action=formUtilisateur&acao=modif&idUtili=<%=utili.getId().toString() %>" ><button type="button" class="btn btn-default btn-sm active"><i class="fa fa-edit"></i></button></a>
                  
                  <%    List<Offre> lesOffress = listeOffress;
                        List<Offre_Profil_Util_CV> lesOPCV = utili.getOffre_Profil_Utils();%>
                     
                    <table class="table table-bordered">
                        <tr>
                            <th >Offre</th>
                            <th>Niveau Expertise</th>
                            <th>Niveau Habilitation</th>
                            <th>Plafond</th>
                            <th>Prix Unitaire</th>
                            <th>Lien CV</th>
                        </tr>
                       <% for (Offre_Profil_Util_CV opcv : lesOPCV){%>
                        <tr>
                            <td><%=opcv.getOffre().getLibelle() %></td>
                             <td><%=opcv.getProfil().getNiveauExpertise() %></td>
                             <td><%=opcv.getProfil().getNiveauHabilitation() %></td>
                             <td><%=opcv.getProfil().getPlafond() %></td>
                             <td><%=opcv.getPrixUnit() %></td>
                             <td><%=opcv.getLienCV() %></td>
                        </tr>
                        <%}%>
                        
                        <% String acao = (String) request.getAttribute("acao");%>
                        <% if( acao!=null&&acao.equals("modif") ){%>
                        
                        <tr>
                          <td>
                              <select  name="offreProf" class="form-control">
                                  <option value="vide" ></option>
                              <%    for (Offre off : lesOffress){%>    <option value="<%=off.getId() %>" >
                              <span class="label label-primary"><%=off.getLibelle() %></span></option>
                              <%}%> 
                              </select>
                          </td>
                          <td>
                            <select  name="NivEx" class="form-control">
                                <option value="vide" ></option>
                                <option value="Junior" >Junior</option>
                                <option  value="Confirme">Confirme</option>
                                <option value="Senior">Senior</option>  
                            </select>
                          </td>
                          <td>   
                              <select  name="NivHab" class="form-control">
                                  <option value="vide" ></option>
                                <option value="Consultant" >Consultant</option>
                                <option  value="Referent">Referent</option>
                                <option value="Porteur">Porteur</option>  
                            </select>
                          </td>
                          <td><input type="txt" name="plafondPFCV" class="form-control" id="exampleInputEmail1" placeholder="0" >  </td>
                          <td><input type="txt" name="prixPFCV" class="form-control" id="exampleInputEmail1" placeholder="0" >  </td>
                          <td><input type="txt" name="cvPFCV" class="form-control" id="exampleInputEmail1" placeholder="lien" >  </td>
                        </tr>         <%}%>                    
                    </table>
                  
                  <input type="hidden" name="idutili" value="<%=utili.getId() %>">
                  <input type="hidden" name="action" value="ModifierProfilMetier">
                </div>
                
                
             

              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Valider</button>
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
