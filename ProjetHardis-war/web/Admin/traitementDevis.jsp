<%-- 
    Document   : creerAdresse
    Created on : 15 mars 2019, 11:25:44
    Author     : 6171217
--%>

<%@page import="Entites.EchangeTel"%>
<%@page import="Entites.Interlocuteur"%>
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
    <jsp:useBean id="listeCommunication" scope="session" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeNotif" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeClient" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="devistraitement" scope="session" class = "Entites.Devis"> </jsp:useBean>
    <jsp:useBean id="listeCommunicationDevis" scope="request" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeHTVide" scope="request" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeConsultantOffre" scope="session" class= "java.util.List"></jsp:useBean>
    <jsp:useBean id="listeDocument" scope="session" class= "java.util.List"></jsp:useBean>
     <jsp:useBean id="listInterlocuteur" scope="request" class= "java.util.List"></jsp:useBean>
     <jsp:useBean id="listEchangeTel" scope="request" class= "java.util.List"></jsp:useBean>
    

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
      <% List<Interlocuteur> lesInter=listInterlocuteur; %>
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
      <div class="col-lg-8 connectedSortable">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Devis</h3>
              <div class="box-tools pull-right">
                    <div class="btn-group">
                        
                        <% if (listeConsultantOffre.contains(utilisateur)&&!listeDocument.isEmpty()){ %>
                        <a href="servAdmin?action=formDevis&faire=envoyer&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="submit" class="btn btn-default" title="Envoyer le Devis"><i class="fa fa-send"></i></button></a><%}%>
                        <a href="servAdmin?action=formDevis&faire=tele&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="submit" class="btn btn-default" title="Echange Telephonique"><i class="fa fa-phone"></i></button></a>
                            <a href="servAdmin?action=formDevis&faire=document&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="submit" class="btn btn-default" title="Ajouter Documents"><i class="fa fa-plus"></i></button></a>
                        <a href="servAdmin?action=formDevis&faire=modifier&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="submit" class="btn btn-default" title="Modifier"><i class="fa fa-edit"></i></button></a>
                        
                        
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
                        <% if(devistraitement.getStatut()==Statut.Valide && devistraitement.getTypeDevis()==TypeService.Non_Standard)  {%>
                        <a href="servAdmin?action=formDevis&faire=facture&idDevis=<%=devistraitement.getId().toString() %>" name="idDevis" value="<%=devistraitement.getId().toString() %>">
                            <button type="button" class="btn  btn-primary" >Creer Facture Devis</button></a>
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
                    String faire = (String) request.getAttribute("faire");
                    if (faire!=null&&faire.equals("affecter")){%>
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
                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>
              </div> <%}%>
              <% 
                    if (faire!=null&&faire.equals("valider")){%>
              <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4> Nombre de Jour </h4>
                
                <form>
                    <label for="nombreJour">Nombre de Jours</label>
                  <input type="txt" name="nombreJour" class="form-control" id="exampleInputEmail1" placeholder=""  >
                  <input type="hidden" name="idDevis" value="<%=devistraitement.getId() %>">
                     <input type="hidden" name="action" value="affecterConsultantDevis">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>
              </div> <%}%>
              
               <% if (faire!=null&&faire.equals("facture")){ %>
              <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4> Facture Devis </h4>
                
                <form>
                    <label for="nombreJour">Montant Facture</label>
                    <p>€ <%=devistraitement.getMontantDevis() %></p>
                  <input type="txt" name="MontantDevis" class="form-control" id="exampleInputEmail1" placeholder=""  >
                  <input type="hidden" name="idDevis" value="<%=devistraitement.getId() %>">
                     <input type="hidden" name="action" value="Creer1ereFactureDevis">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>
              </div> <%}%>
              <% if (faire!=null&&faire.equals("document")){ %>
              <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4> Document Devis </h4>
                
                <form>
                    <label for="nombreJour">Ajouter Document</label>
                  <input type="txt" name="DescriptionDocument" class="form-control" id="exampleInputEmail1" placeholder="Description Document"  >
                  <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile" name="DocumentDevis">
                </div>
                  <input type="hidden" name="idDevis" value="<%=devistraitement.getId() %>">
                     <input type="hidden" name="action" value="AjouterDocumentAUnDevis">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>
              </div> <%}%>
              
                <%  if (faire!=null&&faire.equals("envoyer")){ %>
              <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4> Envoyer le Devis </h4>
                
                <form>
                    <select name="docenvoye" class="form-control">
                        
                       <% for (Document docs : lesDocument){%>
                        <option value="<%=docs.getId() %>"> <%=docs.getDescriptif() %>  </option> 
                    <%}%>
                    </select>
                    <input type="hidden" name="idclient" value="<%=devistraitement.getClient().getId() %>">
                    <input type="hidden" name="iddev" value="<%=devistraitement.getId() %>">
                    <input type="hidden" name="action" value="EnvoyerDevis">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>
              </div> <%}%>
               <%  if (faire!=null&&faire.equals("tele")){ %>
              <div class="alert alert-info alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <h4> Echange Telephonique </h4>
                
                <form>
                    <select name="interlocuteur" class="form-control">
                        <option value=" client"> <%=devistraitement.getClient().getNom() %>  </option> 
                       <% for (Interlocuteur inter : lesInter){%>
                        <option value="<%=inter.getId() %>"> <%=inter.getNomInterlocuteur() %>  </option> 
                    <%}%>
                    </select>
                    <textarea class="form-control" rows="3" name="textEchange"  id="exampleInputEmail1" placeholder="" ></textarea>
                    <input type="hidden" name="idclient" value="<%=devistraitement.getClient().getId() %>">
                    <input type="hidden" name="iddev" value="<%=devistraitement.getId() %>">
                    <input type="hidden" name="action" value="EchangeTel">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </form>
              </div> <%}%>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            
              <div class="box-body">
                  <form role="form">
                <div class="form-group">
                   
                    <div class="row">
                        <div class="col-md-4">
                  <label for="idDevis">Id : <%=devistraitement.getId() %></label>
          </div>
                  <div class="col-md-4">
                  <label for="tpDevis">Type Devis : <%=devistraitement.getTypeDevis().toString()%></label>
                  </div>
                  <div class="col-md-4">
                  <label for="clientDevis">Client : <%=devistraitement.getClient().getNom() %></label>
                   </div>
                    </div>
                   <br>
                   
                            <div class="row"> <div class="col-md-4">
                  <label for="agenceDevis">Agence : <%=devistraitement.getAgence().getNomAgence() %></label>
                      </div>
                      <div class="col-md-4">
                  <label for="serviceDevis">Service : <%=devistraitement.getService().getNomService()%></label>
                      </div>
                      <div class="col-md-4">
                  <label for="dtDevis">Date Devis : <%=dformat.format(devistraitement.getDateDevis()) %></label>
                      </div>
                  </div>
                      <br>
                  <% List<HistoriqueTraitement> listHistTrait = devistraitement.getHistoriqueTraitements();%>
                  <div class="row">
                      <div class="col-md-4">
                  <label for="consultantDevis">Consultant Devis </label>
                    <%for (HistoriqueTraitement htde : listHistTrait){%> 
                    <input type='txt' name='validateurDevis' class='form-control' id='exampleInputEmail1' placeholder='
                  <% if (htde.getConsultant()!=null ) {%>
                       <%=htde.getConsultant().getNom() %>
                       <%}%>
                 <% if (htde.getConsultant()==null ){%>
                      Non Choisi
                  <%}%>' disabled><%}%>
                      </div><div class="col-md-4">
                  <label for="validateurDevis">Validateur Devis</label>
                  <%for (HistoriqueTraitement htde : listHistTrait){%> 
                    <input type='txt' name='validateurDevis' class='form-control' id='exampleInputEmail1' placeholder='
                  <% if (htde.getValidateur()!=null ) {%>
                       <%=htde.getValidateur().getNom() %>
                       <%}%>
                 <% if (htde.getValidateur()==null ){%>
                      Non Choisi
                  <%}%>' disabled><%}%>
                    </div><div class="col-md-4">
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
                   </div>
                  </div>
         
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
                       <input type='txt' name='montDevis' class='form-control' id='exampleInputEmail1' placeholder='<%=devistraitement.getMontantDevis() %>' 
                              <%  if (faire!=null&&faire.equals("modifier") ){%>
                              disabled
                              <%}%>
                              <%  if (faire!=null&&faire.equals("modifier")){%><%}%>
                              >
                       <%}%>
                 <% if (devistraitement.getMontantDevis()==0 ){%>
                      <input type='txt' name='montDevis' class='form-control' id='exampleInputEmail1' placeholder='Non Rempli' 
                             <%  if (faire!=null&&!faire.equals("modifier")){%>
                              disabled
                              <%}%>
                             <%  if (faire!=null&&faire.equals("modifier")){%> <%}%>
                             >
                     
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
                
                
             
                <%  if (faire!=null&&faire.equals("modifier")){%>
              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Valider</button>
              </div>
              <%}%>
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
<% List<Facture> lesFactures = devistraitement.getFactures();%>
<% if (!lesFactures.isEmpty()){%>
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Factures</h3>
              
            
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                
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
              
            </div>
            <!-- /.box-footer -->
          </div><%}%>
          <!-- /.box -->
          <% if (!listeDocument.isEmpty()){%>
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Documents</h3>
            
            
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                
              </div>
            </div>
            <!-- /.box-header -->
             
            <div class="box-body">
              <ul class="products-list product-list-in-box">
                  <% for (Document doc : lesDocument){     %>
                <li class="item">
                  <div class="product-img">
                    <img src="https://www.popcompta.com/wp-content/uploads/2016/11/facture.png" alt="Product Image">
                  </div>
                  <div class="product-info">
                    <a href="javascript:void(0)" class="product-title"><%=doc.getTypeDoc().toString() %>
                      <span class="label label-warning pull-right">  <%=doc.getLienDoc() %></span></a>
                    <span class="product-description"><%=doc.getDescriptif() %> </span>
                  </div>
                </li>
                <%}%>
                <!-- /.item -->
                </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer text-center">
              
            </div>
            <!-- /.box-footer -->
          </div>
                <%}%>
                <% List<EchangeTel> lesEchange=listEchangeTel; %> 
                <% if (!listEchangeTel.isEmpty()){%>
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Echange telephonique</h3>
            
            
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                
              </div>
            </div>
            <!-- /.box-header -->
             
            <div class="box-body">
              <ul class="products-list product-list-in-box">
                  <% for (EchangeTel echanget : lesEchange){     %>
                <li class="item">
                  <div class="product-img">
                    <img src="data:image/png;base64,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" alt="Product Image">
                  </div>
                  <div class="product-info">
                    <a href="javascript:void(0)" class="product-title">DEV:<%=echanget.getDevis().getId().toString() %>
                      </a>
                    <span class="product-description"><%=echanget.getTexte() %> </span>
                  </div>
                </li>
                <%}%>
                <!-- /.item -->
                </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer text-center">
              
            </div>
            <!-- /.box-footer -->
          </div>
                <%}%>
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
