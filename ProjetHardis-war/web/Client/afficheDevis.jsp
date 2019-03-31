<%-- 
    Document   : tabBord
    Created on : 13 mars 2019, 15:07:11
    Author     : 6170361
--%>

<%@page import="Entites.Facture"%>
<%@page import="Entites.UtilisateurHardis"%>
<%@page import="java.security.Signature"%>
<%@page import="Entites.HistoriqueDevis"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entites.Communication"%>
<%@page import="Entites.Service"%>
<%@page import="Entites.Devis"%>
<%@page import="Entites.Notification"%>
<%@page import="java.util.List"%>
<%@page import="Entites.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

 <jsp:useBean id="devis" scope="session" class = "Entites.Devis"> </jsp:useBean>
  <jsp:useBean id="listMessage" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeConsu" scope="session" class = "java.util.List"> </jsp:useBean>
      <jsp:useBean id="facture1" scope="request" class = "Entites.Facture"> </jsp:useBean>
       <jsp:useBean id="facture2" scope="request" class = "Entites.Facture"> </jsp:useBean>
  

     
  <title>AdminLTE 2 | Dashboard</title>
 <%@include  file = "meta.jsp" %>
 
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

   <%@include  file = "menu.jsp" %>
  <% Devis d = devis;%>
  
    <% List<Communication> liste = listMessage;
    Facture f = facture1;
       Facture f1 = facture2;
%>
   <body>
        <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Tableau de bord
       
      </h1>
     
    </section>

    <!-- Main content -->
    <section class="content">
        <% if (d.getStatut().toString().equals("Presta_terminee") || d.getStatut().toString().equals("Valide")) {%>
        <div class="row">
            <div class="col-md-12">
                            <div class="box-body">
    
              <div class="callout callout-info">
              
<% String ss = "";
String message = "";
                if (d.getTypeDevis().toString().equals("Standard")) ss="ss";
                else if (d.getTypeDevis().toString().equals("Non_Standard"))
                    ss="sns";
          if (d.getStatut().toString().equals("Presta_terminee")) message = "Votre prestation a été finalisée, vous avez 30 jours pour payer votre facture.";
          else if (d.getStatut().toString().equals("Valide")) message = "Le devis a été validé";
%>
           
                  <p><%=message%>
                  <h4>Cliquez <a href="servClient?action=consulteDevis&idDev=<%=d.getId()%>&typeD=<%=ss%>">ici</a> pour payer votre facture</h4></p>
              </div>
            
            </div>
            </div>
        </div> <%}%>
      <!-- Main row -->
      <div class="row">
          
   <div class="col-md-6">

                    <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Détail devis</h3>

             
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div class="table-responsive">
                <table class="table no-margin">
                  <thead>
                  <tr>
                    <th>ID</th>
                    <th>Offre</th>
                    <th>Service</th>
                    <th>Statut</th>
                     <th>Date intervention</th>

                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                                              
                   <td><% 
                       
                       SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
                   String devislien = (String)request.getAttribute("lienD");
                   List<UtilisateurHardis> listeC = listeConsu;
                   
                       out.print("DEV"+d.getId());
                       %></td>
                   <td><%=d.getService().getOffre().getLibelle()%></td>
                   <td><%=d.getService().getNomService()%></td>
                   <td><% 
                      if (d.getStatut().toString().equals("Valide"))
                      
                           out.print("<span class=\"label label-danger\">Validé, en attente de paiement</span>");
                      
                      else if (d.getStatut().toString().equals("Refuse"))
                      
                           out.print("<span class=\"label label-warning\">Refusé</span>");
                     
                      else if (d.getStatut().toString().equals("Acompte_regle"))
                       
                           out.print("<span class=\"label label-success\">Acompte reglé</span>");
                        else if (d.getStatut().toString().equals("Total_regle"))
                       
                           out.print("<span class=\"label label-success\">Total reglé</span>");
                                              
                       else if (d.getStatut().toString().equals("Rep_en_Cours") && d.getTypeDevis().toString().equals("Standard"))
                           out.print("<span class=\"label label-info\">En attente de validation</span>");
 
                       else if (d.getStatut().toString().equals("Rep_en_Cours")&& d.getTypeDevis().toString().equals("Non_Standard"))
                           out.print("<span class=\"label label-info\">Traitement demande</span>");

                       
                       
                   
                       
                      
                  %></td>
                   
                   <td><%=dformat.format(d.getDateIntervSouhaitee())%></span></td>

                   
                 
                   

                  
                  
                  </tr>

                  </tbody>
                </table>
              </div>
              <!-- /.table-responsive -->
            </div>
                  <% if (d.getTypeDevis().toString().equals("Standard") && (d.getStatut().toString().equals("Rep_en_Cours") || d.getStatut().toString().equals("Valide")) ||
                         d.getTypeDevis().toString().equals("Non_Standard") && d.getStatut().toString().equals("Envoye") || d.getStatut().toString().equals("Valide")) {
                             
                           %>
                   <div row>
                          <div class="box-footer clearfix">
                              <div class="col-md-10">
                                  <a href="servClient?action=choixDate&idDev=<%=d.getId()%>" class="btn btn-sm btn-info btn-flat pull-right">Changer date </a>
                             
                              </div>
                             
            
                          
                          </div>
                   </div>
 <% String change = (String)request.getAttribute("change");
                              if (change!=null && change.equals("1"))
                              { 
if (d.getStatut().toString().equals("Valide")) out.print("Attention, vous avez déjà validé votre devis. En choissisant une autre date le montant du devis pourrait changer !"); %>
                                              <form role="form">
              <div class="box-body">
                                     
             <div class="form-group">
                <label>Date:</label>

                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="date" id="datepicker" name="date">
                </div>
                <!-- /.input group -->
              </div>
              <!-- /.form group -->
                      
                   <input type ="hidden" name="action" value="choixDate">
                    <input type ="hidden" name="idDev" value="<%=d.getId()%>">
                     <input type ="hidden" name="typeD" value="<%=d.getTypeDevis().toString()%>">
   
              </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Valider</button>
              </div>
            </form>
                             <% }%> <% }
                   %>
            <!-- /.box-body -->
           
                    </div>
            <!-- /.box-footer -->

          </div>    
            
            <div class="col-md-2">
          <% List<Facture> lesFactures = d.getFactures();%>
            
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Documents</h3>
      <div class="box-tools pull-right">
            
                
              </div>
            </div>
            <!-- /.box-header -->
             
            <div class="box-body">
              <ul class="products-list product-list-in-box">
                  
                   <li class="item">
                  <div class="product-img">
                    <img src="https://www.popcompta.com/wp-content/uploads/2016/11/facture.png" alt="Product Image">
                  </div>
                  <div class="product-info">
                 
                        <a href="<%=d.getService().getConditionsContract()%>" class="product-title">
                            CONDITIONS GENERALES
                 </a>
                              
                    <span class="product-description">
                       
                        </span>
                  </div>
                </li>
                  
                <li class="item">
                  <div class="product-img">
                    <img src="https://www.popcompta.com/wp-content/uploads/2016/11/facture.png" alt="Product Image">
                  </div>
                  <div class="product-info">
                        <% if (d.getTypeDevis().toString().equals("Standard")){
                   %>
                        <a href="servClient?action=consulteDevis&typeD=ss&idDev=<%out.print(d.getId());%>" class="product-title">
                            DEVIS
                 </a>
                        <%} else if (d.getTypeDevis().toString().equals("Non_Standard")){
                   %>
                   <%if  (d.getStatut().toString().equals("Rep_en_Cours")) {%>
                     
                             DEVIS en attente
                   <%} else {%>
                        <a href="servClient?action=consulteDevis&typeD=sns&idDev=<%out.print(d.getId());%>" class="product-title">
                             DEVIS
                 </a>    <%}}   %>      
                    <span class="product-description">
                       
                        </span>
                  </div>
                </li>
        
                <!-- /.item -->
                
                    <% if (d.getStatut().toString().equals("Acompte_regle") || d.getStatut().toString().equals("Total_regle") ) {
                  %>
                  <% if (f.getId()!=null){%>
                <li class="item">
                  <div class="product-img">
                    <img src="https://www.popcompta.com/wp-content/uploads/2016/11/facture.png" alt="Product Image">
                  </div>
                  <div class="product-info">
                    <a href="<%=f.getLienFact()%>" class="product-title">FACTURE 1
                      <span class="label label-warning pull-right">€  <%=f.getMontant() %></span></a>
                    <span class="product-description">
                       
                        <span class="label label-warning pull-right"><%=dformat.format(f.getDateFacture())%></span>
                    
                        </span> 
                  </div>
                </li><%}%>
                <% if (f1.getId()!=null){%>
                   <li class="item">
                  <div class="product-img">
                    <img src="https://www.popcompta.com/wp-content/uploads/2016/11/facture.png" alt="Product Image">
                  </div>
                  <div class="product-info">
                    <a href="<%=f1.getLienFact()%>" class="product-title">FACTURE 2
                      <span class="label label-warning pull-right">€  <%=f1.getMontant() %></span></a>
                    <span class="product-description">
                       
                        <span class="label label-warning pull-right"><%=dformat.format(f1.getDateFacture())%></span>
                    
                        </span>
                  </div>
                </li>
                 <%}%>
                <%
                }%>
                <!-- /.item -->

                <!-- /.item -->
                </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer text-center">
              
            </div>
            <!-- /.box-footer -->
          </div>
                </div>
           
                <div class="col-md-4">
                     <div class="row">
              <!-- DIRECT CHAT -->
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
                      
                      <% if (liste.size()==0){
                      
                      } else { 
                      for (Communication c : liste)  {
   if(c.getTypeQR().equals("R")) { %>
                    <!-- Message. Default to the left -->
                    <div class="direct-chat-msg left">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left"><%=c.getUtilisateurHardis().getNom()%> <%=c.getUtilisateurHardis().getPrenom()%></span>
                        <span class="direct-chat-timestamp pull-right"><%=c.getDateHeure()%> </span>
                      </div>
                      <!-- /.direct-chat-info -->
                     
                      <!-- /.direct-chat-img -->
                      <div class="direct-chat-text">
                        <%=c.getMessage()%>
                      </div>
                      <!-- /.direct-chat-text -->
                    </div>
                    <!-- /.direct-chat-msg -->
                    <%} else if (c.getTypeQR().equals("Q"))
{%>
 <!-- Message. Default to the left -->
                    <div class="direct-chat-msg right">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-timestamp pull-right"><%=c.getDateHeure()%> </span>
                      </div>
                      <!-- /.direct-chat-info -->
                     
                      <!-- /.direct-chat-img -->
                      <div class="direct-chat-text">
                        <%=c.getMessage()%>
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
                <!-- /.box-body -->
                <div class="box-footer">
                  <form action="servClient" method="get">
                    <div class="input-group">
                      <input type="text" name="message" placeholder="Type Message ..." class="form-control">
                                        <input type ="hidden" name="action" value="afficheDevis">
                                        <input type ="hidden" name="idDev" value="<%=d.getId()%>">
                      <span class="input-group-btn">
                         <button type="submit" class="btn btn-primary">Envoyer</button>
                          </span>
                    </div>
                  </form>
                </div>
                <!-- /.box-footer-->
              </div>
              <!--/.direct-chat -->
            </div>
            <!-- /.col -->
   
   
        </div>


    </section>
   
   
   
   
  </div>
   
   
   
   
   
   



  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>

</div>
<!-- ./wrapper -->



  <%@include  file = "script.jsp" %>
  
 
</body>
</html>

