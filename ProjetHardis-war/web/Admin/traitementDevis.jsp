<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

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
      <% List<Devis> lesDeevis=listeDevis; %> 
      <!-- /.row -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="champ" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="action" value="RechercherDevis" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- Main row -->
      <div class="row">
        <!-- left column -->
        <div class="box">
            <div class="box-header">
              <h3 class="box-title">Devis</h3>

            </div>
            
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                <th>ID</th>
                  <th>Agence</th>
                  <th>Client</th>
                  <th>Entreprise</th>
                  <th>Service</th>
                  <th>Type Service</th>
                  <th>Statut</th>
                  <th>Type Devis</th>
                  <th>Date Devis</th>
                </tr>
                </thead>
                 <% SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
                     for (Devis devis : lesDeevis){%>
                <tbody>
                <tr>
                    <td><a href="servAdmin?action=formDevis&idDevis=<%=devis.getId().toString() %>" name="idDevis" value="<%=devis.getId().toString() %>">DEV<%=devis.getId()%></a></td>
                    <td><%=devis.getAgence().getNomAgence() %> </td>
                    <td><%=devis.getClient().getNom() %> </td>
                    <td><%=devis.getClient().getEntreprise().getNomEntreprise() %></td>
                    <td><%=devis.getService().getNomService() %> </td>
                    <td><%=devis.getService().getTypeService().name() %></td>
                    <td><%=devis.getStatut().name() %> </td>
                    <td><%=devis.getTypeDevis() %> </td>
                    <td><%=dformat.format(devis.getDateDevis()) %> </td>
                 
                  
                </tr>
                </tbody>
                <%}%>
                
              </table>
            </div>
            <!-- /.box-body -->
          </div>
         
          
        <!--/.col (right) -->
      </div>
      <!-- /.row (main row) -->
      <div class="col-xs-8">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Devis</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form">
              <div class="box-body">
                <div class="form-group">
                  <label for="idDevis">Id Devis</label>
                  <input type="txt" name="idDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getId() %>"  disabled>
                  <label for="tpDevis">Type Devis</label>
                  <input type='txt' name='tpDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=devistraitement.getTypeDevis().name() %>' disabled>
                  <label for="clientDevis">Client Devis</label>
                  <input type="txt" name="clientDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getClient().getNom() %>" disabled>
                  <label for="consultantDevis">Consultant Devis</label>
                  <% List<HistoriqueTraitement> listHistTrait = devistraitement.getHistoriqueTraitements();
                  for (HistoriqueTraitement htde : listHistTrait){%>
                  <% if (htde.getConsultant()!=null ) {%>
                       <input type='txt' name='consultantDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=htde.getConsultant().getNom() %>' disabled>
                       <%}%>
                 <% if (htde.getConsultant()==null ){%>
                      <input type='txt' name='consultantDevis' class='form-control' id='exampleInputEmail1' placeholder='Non Choisi' disabled>
                  <%}%>
                  <label for="validateurDevis">Validateur Devis</label>
                  <% if (htde.getValidateur()!=null ) {%>
                       <input type='txt' name='validateurDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=htde.getValidateur().getNom() %>' disabled>
                       <%}%>
                 <% if (htde.getValidateur()==null ){%>
                      <input type='txt' name='validateurDevis' class='form-control' id='exampleInputEmail1' placeholder='Non Choisi' disabled>
                  <%}%>
                  <label for="refLocalDevis">Referant Local Devis</label>
                  <% if (htde.getRefLocal()!=null ) {%>
                       <input type='txt' name='refLocalDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=htde.getRefLocal().getNom() %>' disabled>
                       <%}%>
                 <% if (htde.getRefLocal()==null ){%>
                      <input type='txt' name='refLocalDevis' class='form-control' id='exampleInputEmail1' placeholder='Non Choisi' disabled>
                  <%}%>
                  <%}%>
                  <label for="agenceDevis">Agence Devis</label>
                  <input type="txt" name="agenceDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getAgence().getNomAgence() %>" disabled>
                  <label for="serviceDevis">Service Devis</label>
                  <input type="txt" name="serviceDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getClient().getEntreprise().getNomEntreprise() %>" disabled>
                  <label for="dtDevis">Date Devis</label>
                  <input type="txt" name="dtDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=dformat.format(devistraitement.getDateDevis()) %>" disabled>
                  <label for="dtDebPrestation">Date Debut Prestation</label>
                  <% if (devistraitement.getDateDebutPresta()!=null ) {%>
                       <input type='txt' name='dtDebPrestation' class='form-control' id='exampleInputEmail1' placeholder='<%=dformat.format(devistraitement.getDateDebutPresta())%>' disabled>
                       <%}%>
                 <% if (devistraitement.getDateDebutPresta()==null ){%>
                      <input type='txt' name='dtDebPrestation' class='form-control' id='exampleInputEmail1' placeholder='Non Choisi' disabled>
                  <%}%>
                  <label for="dtFinPrestation">Date Fin Prestation</label>
                  <% if (devistraitement.getDateFinPresta()!=null ) {%>
                       <input type='txt' name='dtFinPrestation' class='form-control' id='exampleInputEmail1' placeholder='<%=dformat.format(devistraitement.getDateFinPresta())%>' disabled>
                       <%}%>
                 <% if (devistraitement.getDateFinPresta()==null ){%>
                      <input type='txt' name='dtFinPrestation' class='form-control' id='exampleInputEmail1' placeholder='Non Choisi' disabled>
                  <%}%>
                  <label for="dtIntSou">Date D'Intervention Souhaitée Client</label>
                  <% if (devistraitement.getDateIntervSouhaitee()!=null ) {%>
                       <input type='txt' name='dtIntSou' class='form-control' id='exampleInputEmail1' placeholder='<%=dformat.format(devistraitement.getDateIntervSouhaitee())%>' disabled>
                       <%}%>
                 <% if (devistraitement.getDateIntervSouhaitee()==null ){%>
                      <input type='txt' name='dtIntSou' class='form-control' id='exampleInputEmail1' placeholder='Non Choisi' disabled>
                  <%}%>
                  <label for="factDevis">Facturation</label>
                  <input type="txt" name="factDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getIndicateurFact().name() %>" disabled>
                  <label for="montDevis">Montant Devis</label>
                  <% if (devistraitement.getMontantDevis()>0 ) {%>
                       <input type='txt' name='montDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=devistraitement.getMontantDevis() %>' disabled>
                       <%}%>
                 <% if (devistraitement.getMontantDevis()==0 ){%>
                      <input type='txt' name='montDevis' class='form-control' id='exampleInputEmail1' placeholder='Non Rempli' disabled>
                  <%}%>                 
                  <label for="refusDevis">Motif Refus</label>
                  <input type="txt" name="refusDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getMotifRefus() %>" disabled>
                  <label for="statutDevis">Statut</label>
                  <input type='txt' name='statutDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=devistraitement.getStatut().name() %>' disabled>                      
                  <label for="slDevis">Saisir Libre</label>
                  <input type="txt" name="slDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getSaisieLibre() %>" disabled>
                     
                  <input type="hidden" name="idAgence" value="<%=devistraitement.getId() %>">
                </div>
                
                <input type="hidden" name="action" value="ModifierDevis">
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
            
          </div>
                </form>
          <!-- /.box -->
        
        <!--/.col (left) -->
        <!-- right column -->
        
          <!-- /.box -->
        </div>
      </div>
      <div class="col-xs-4">
          <div class="box box-warning direct-chat direct-chat-warning">
                <div class="box-header with-border">
                  <h3 class="box-title">Communication</h3>
 <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                    </button>
               
             
                  </div>

                </div>
                <!-- /.box-header -->
                <div class="box-body">
                  <!-- Conversations are loaded here -->
                  <div class="direct-chat-messages">
                      <% List<Communication> lesCom=listeCommunicationDevis; %>
                      <% if (lesCom.size()==0){
                      
                      } else { 
                      for (Communication comuni : lesCom)  {
   if(comuni.getTypeQR().equals("R")) { %>
                    <!-- Message. Default to the left -->
                    <div class="direct-chat-msg left">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left"><%=comuni.getUtilisateurHardis().getNom()%> <%=comuni.getUtilisateurHardis().getPrenom()%></span>
                        <span class="direct-chat-timestamp pull-right"><%=comuni.getDateHeure()%> </span>
                      </div>
                      <!-- /.direct-chat-info -->
                     
                      <!-- /.direct-chat-img -->
                      <div class="direct-chat-text">
                        <%=comuni.getMessage()%>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->
                    <%} else if (comuni.getTypeQR().equals("Q"))
{%>
 <!-- Message. Default to the left -->
                    <div class="direct-chat-msg right">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-timestamp pull-right"><%=comuni.getDateHeure()%> </span>
                      </div>
                      <!-- /.direct-chat-info -->
                     
                      <!-- /.direct-chat-img -->
                      <div class="direct-chat-text">
                        <%=comuni.getMessage()%>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->

<%} }}%>


           
                    <!-- /.contatcts-list -->
                  </div>
                  <!-- /.direct-chat-pane -->
                </div>
              </div>
        <div class="box-footer">
                  <form action="servAdmin" method="get">
                    <div class="input-group">
                      <input type="text" name="message" placeholder="Type Message ..." class="form-control">
                                       
                                        <input type="hidden" name="action" value="messageDevis">
                                        <input type ="hidden" name="idDev" value="<%=devistraitement.getId()%>">
                      <span class="input-group-btn">
                         <button type="submit" class="btn btn-primary">Envoyer</button>
                          </span>
                    </div>
                  </form>
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
