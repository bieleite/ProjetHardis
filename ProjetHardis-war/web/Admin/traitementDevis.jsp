<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

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
    <jsp:useBean id="listeCommunication" scope="session" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeNotif" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeClient" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="devistraitement" scope="session" class = "Entites.Devis"> </jsp:useBean>
    <jsp:useBean id="listeCommunicationDevis" scope="request" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeHTVide" scope="request" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeConsultantOffre" scope="request" class= "java.util.List"></jsp:useBean>
    

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
      <div class="col-lg-8 connectedSortable">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Devis</h3>
              <div class="box-tools pull-right">
                    <div class="btn-group">
                        <% if(devistraitement.getStatut()==Statut.Incomplet )  {%>
                        <a href="servAdmin?action=RelancerDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn btn-default">Relancer Client</button></a>
                            
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.Rep_en_Cours && devistraitement.getTypeDevis()==TypeService.Non_Standard )  {%>
                        <a href="servAdmin?action=formDevis&faire=valider&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" >Valider Devis</button></a>
                            <a href="servAdmin?action=formDevis&faire=affecter&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" >Affecter Devis</button></a>
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.Transmettre_au_client )  {%>
                        <a href="servAdmin?action=affecterDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" disabled >Valider Devis</button></a>
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.Envoye )  {%>
                        <a href="servAdmin?action=affecterDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" disabled>Affecter Devis</button></a>
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.Valide )  {%>
                        <a href="servAdmin?action=affecterDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" disabled>Valider Devis</button></a>
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.Refuse )  {%>
                        <a href="servAdmin?action=affecterDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" disabled>Valider Devis</button></a>
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.En_nego )  {%>
                        <a href="servAdmin?action=affecterDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" disabled>Valider Devis</button></a>
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.Acompte_regle )  {%>
                        <a href="servAdmin?action=PrestationtermineeDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary">Prestation Terminnée</button></a>
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.Presta_terminee )  {%>
                        <a href="servAdmin?action=affecterDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" disabled>Prestation Terminnée</button></a>
                        <%}%>
                        
                        <% if(devistraitement.getStatut()==Statut.Modif_date )  {%>
                        <a href="servAdmin?action=affecterDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" disabled>Prestation Terminnée</button></a>
                        <%}%>
                        <% if(devistraitement.getStatut()==Statut.Total_regle )  {%>
                        <a href="servAdmin?action=affecterDevis&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" disabled>Prestation Terminnée</button></a>
                        <%}%>
                    </div>
                    
            </div>
                    <% List<UtilisateurHardis> lesConsultants=listeConsultantOffre;
                    if (!lesConsultants.isEmpty()){%>
              <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4> Consultant</h4>
                
                <form>
                    <select name="ConsultantAffecte" class="form-control">
                        
                       <% for (UtilisateurHardis consult : lesConsultants){%>
                    <option value="<%=consult.getId() %>"> <%=consult.getNom() %>  </option> 
                    <%}%>
                  </select>
                  <input type="hidden" name="iddev" value="<%=devistraitement.getId() %>">
                     <input type="hidden" name="action" value="AffecterDevis">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
              </div> <%}%>
              <% String faire = (String) request.getAttribute("faire");
                    if (faire!=null&&faire.equals("valider")){%>
              <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4> Nombre de Jour </h4>
                
                <form>
                    <label for="nombreJour">Nombre de Jours</label>
                  <input type="txt" name="nombreJour" class="form-control" id="exampleInputEmail1" placeholder=""  >
                  <input type="hidden" name="idDevis" value="<%=devistraitement.getId() %>">
                     <input type="hidden" name="action" value="affecterConsultantDevis">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
              </div> <%}%>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            
              <div class="box-body">
                  <form role="form">
                <div class="form-group">
                  <label for="idDevis">Id Devis</label>
                  <input type="txt" name="idDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getId() %>"  disabled>
                  <label for="tpDevis">Type Devis</label>
                  <input type='txt' name='tpDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=devistraitement.getTypeDevis().name() %>' disabled>
                  <label for="clientDevis">Client Devis</label>
                  <input type="txt" name="clientDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getClient().getNom() %>" disabled>
                 
                  
                  <% List<HistoriqueTraitement> listHistTrait = devistraitement.getHistoriqueTraitements();%>
                  
                  <label for="consultantDevis">Consultant Devis</label>
                    <%for (HistoriqueTraitement htde : listHistTrait){%> 
                    <input type='txt' name='validateurDevis' class='form-control' id='exampleInputEmail1' placeholder='
                  <% if (htde.getConsultant()!=null ) {%>
                       <%=htde.getConsultant().getNom() %>
                       <%}%>
                 <% if (htde.getConsultant()==null ){%>
                      Non Choisi
                  <%}%>' disabled><%}%>
                  <label for="validateurDevis">Validateur Devis</label>
                  <%for (HistoriqueTraitement htde : listHistTrait){%> 
                    <input type='txt' name='validateurDevis' class='form-control' id='exampleInputEmail1' placeholder='
                  <% if (htde.getValidateur()!=null ) {%>
                       <%=htde.getValidateur().getNom() %>
                       <%}%>
                 <% if (htde.getValidateur()==null ){%>
                      Non Choisi
                  <%}%>' disabled><%}%>
                  <label for="refLocalDevis">Referant Local Devis</label>
                 <% for (HistoriqueTraitement htde : listHistTrait){%> 
                  <input type='txt' name='refLocalDevis' class='form-control' id='exampleInputEmail1' placeholder='
                  <% if (htde.getRefLocal()!=null ) {%>
                       <%=htde.getRefLocal().getNom() %>
                       <%}%>
                 <% if (htde.getRefLocal()==null ){%>
                      Non Choisi
                  <%}%>
                  ' disabled>
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
                  <input type="txt" name="factDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getIndicateurFact().name() %>" disabled >
                  <label for="montDevis">Montant Devis</label>
                  <% if (devistraitement.getMontantDevis()>0 ) {%>
                       <input type='txt' name='montDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=devistraitement.getMontantDevis() %>' disabled>
                       <%}%>
                 <% if (devistraitement.getMontantDevis()==0 ){%>
                      <input type='txt' name='montDevis' class='form-control' id='exampleInputEmail1' placeholder='Non Rempli' disabled>
                  <%}%>                 
                  <label for="refusDevis">Motif Refus</label>
                  <textarea rows="3" name="refusDevis" class="form-control" id="exampleInputEmail1" placeholder="<%=devistraitement.getMotifRefus() %>" disabled ></textarea>
                  <label for="statutDevis">Statut</label>    
                  <input type='txt' name='montDevis' class='form-control' id='exampleInputEmail1' placeholder="<%=devistraitement.getStatut().name() %>" disabled ></textarea>
                
                  <label for="slDevis">Saisir Libre</label>
                  <textarea class="form-control" rows="3" name="slDevis"  id="exampleInputEmail1" placeholder="<%=devistraitement.getSaisieLibre() %>" ></textarea>
                     <input type="hidden" name="idcli" value="<%=devistraitement.getClient().getId() %>">
                  <input type="hidden" name="iddev" value="<%=devistraitement.getId() %>">
                  <input type="hidden" name="idage" value="<%=devistraitement.getAgence().getId() %>">
                  <input type="hidden" name="action" value="ModifierDevis">
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
      <div class="col-lg-4 connectedSortable">
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
<!------ test----->
<!------ test----->
<!------ test----->
        <div class="box box-warning direct-chat direct-chat-warning">
                <div class="box-header with-border">
                  <h3 class="box-title">Timeline</h3>
                    <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                    </button>
               
             
                  </div>

                </div>
                <!-- /.box-header -->
                <div class="box-body">
                  <!-- Conversations are loaded here -->
                  <ul class="timeline">
                      <% List<HistoriqueEtats> listHistEtat = devistraitement.getHistoriqueEtatss();%>
                      
                        <!-- timeline time label -->
                        <li class="time-label">
                            <span class="bg-red">
                               DEV<%=devistraitement.getId() %>
                            </span>
                        </li>
                        <!-- /.timeline-label -->
                <%for (HistoriqueEtats htetats : listHistEtat){%>
                        <!-- timeline item -->
                        <li>
                            <!-- timeline icon -->
                            <i class="fa fa-envelope bg-blue"></i>
                            <div class="timeline-item">
                                <span class="time"><i class="fa fa-clock-o"></i> <%=dformat.format(htetats.getDateMAJ()) %></span>

                                <h3 class="timeline-header"><%=htetats.getDevis().getClient().getNom() %> </h3>

                                <div class="timeline-body">
                                   <%=htetats.getStatut().name() %>
                                </div>

                                
                            </div>
                        </li>
                        <!-- END timeline item -->
<%}%>
                             

                    </ul>
                  <!-- /.direct-chat-pane -->
                </div>
              </div>
<!-- FACTURE LIST -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Factures</h3>
              <% List<Facture> lesFactures = devistraitement.getFactures();%>
            
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <!-- /.box-header -->
             
            <div class="box-body">
              <ul class="products-list product-list-in-box">
                  <% for (Facture fact : lesFactures){     %>
                <li class="item">
                  <div class="product-img">
                    <img src="https://www.popcompta.com/wp-content/uploads/2016/11/facture.png" alt="Product Image">
                  </div>
                  <div class="product-info">
                    <a href="javascript:void(0)" class="product-title"><%=dformat.format(fact.getDateFacture()) %>
                      <span class="label label-warning pull-right">€  <%=fact.getMontant() %></span></a>
                    <span class="product-description">
                        <% if (!fact.isPaye()){%>
                            <span class="label label-warning pull-right">Non Payé</span>
                        <%}%>
                        <% if (fact.isPaye()){%>
                            <span class="label label-success pull-right">Payé</span>
                        <%}%>
                        </span>
                  </div>
                </li>
                <%}%>
                <!-- /.item -->
                </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer text-center">
              <a href="javascript:void(0)" class="uppercase">View All Products</a>
            </div>
            <!-- /.box-footer -->
          </div>
          <!-- /.box -->
<!------ test----->
<!------ test----->
<!------ test----->
        
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
