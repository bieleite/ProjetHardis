<%-- 
    Document   : tabBord
    Created on : 13 mars 2019, 15:07:11
    Author     : 6170361
--%>

<%@page import="Entites.ServiceStandard"%>
<%@page import="Entites.UtilisateurHardis"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entites.Facture"%>
<%@page import="Entites.Adresse"%>
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
   <jsp:useBean id="facture" scope="session" class = "Entites.Facture"> </jsp:useBean>
   <jsp:useBean id="servS" scope="request" class = "Entites.ServiceStandard"> </jsp:useBean>
   <jsp:useBean id="servNS" scope="request" class = "Entites.Service"> </jsp:useBean>
   <jsp:useBean id="listeConsu" scope="session" class = "java.util.List"> </jsp:useBean>
   <jsp:useBean id="listeLibC" scope="request" class = "java.util.List"> </jsp:useBean>
   <jsp:useBean id="PrixU" scope="request" class = "java.util.List"> </jsp:useBean>
      
     
  <title>AdminLTE 2 | Dashboard</title>
 <%@include  file = "meta.jsp" %>
 
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

   <%@include  file = "menu.jsp" %>
  <% 
  Devis d = devis;
  List<UtilisateurHardis> listeC = listeConsu;
  ServiceStandard s = servS;
  Service sns = servNS;
   List<String> listeLib = listeLibC;
    List<Float> PrixUnit = PrixU;
    Float tot = 0.0F;
    Facture f = facture;
    String dispo = (String)request.getAttribute("dispo");
  
  
  SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");%>
  
   <body>
        <!-- Content Wrapper. Contains page content -->
    <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
      <% if (f.getId()==null) {%>   <%  out.print("Devis"); %>
        <small> 
              
             <% out.print("DEV"+d.getId()); %></small> <% } else {%>
             
             <%  out.print("Facture"); %>
        <small> 
              
             <% out.print("FACT"+f.getId()); %></small> <% } %>
        
      </h1>
     
    </section>

   

    <!-- Main content -->
    <section class="invoice">
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 class="page-header">
           Hardis Group
        
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-4 invoice-col">
          De
          <address>
            <strong>Hardis Group <br>
                <%=d.getClient().getAgence().getNomAgence().toString()%>  </strong><br>
             <%=d.getClient().getAgence().getAdresse().toString()%> 
          </address>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
          A
          <address>
            <strong><%=d.getClient().getEntreprise().getNomEntreprise()%></strong><br>
            <% Adresse ad = d.getClient().getEntreprise().getAdresseFact();
                out.print(ad.getNumeroRue()+", "+ad.getNomRue());
            %>
                <br>
            <% 
                out.print(ad.getCodePostal()+" "+ad.getVille());
            %><br>        
          </address>
          Code contrat : <b> <% out.print(d.getClient().getEntreprise().getCodeContrat()); %> </b> 
        </div>
        <!-- /.col -->
       
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- Table row -->
      <div class="row">
           
        <div class="col-xs-12 table-responsive">
            <div >
            
          <b>
              
          <% if (f.getId()==null) {%>    Devis :  #<% out.print(d.getId()+"    en date du : "+dformat.format(d.getDateDevis()));%></b>
             <% } else{%> Facture :  #<% out.print(f.getId()+"    en date du : "+dformat.format(f.getDateFacture()));%></b><%}%>
              
          <br>
          <br>  
          <b> Offre : <%=d.getService().getOffre().getLibelle()%> </b>
          <br>
              <br>
          <b> Service : <%=d.getService().getNomService() %> </b>
          <br>
          <br>
        </div>
              
        
              <br>
              <br>
              <% 
                  if (listeC.size()==0 && dispo!=null && dispo.equals("ok")){ %>
                  
         <b>Il n'y a pas de consultants attribués, vous pouvez en choisir ou en attribuer par défaut. Que voulez-vous faire ? </b>
            <a href="servClient?action=choixConsultants&idDev=<%=d.getId()%>" class="btn btn-sm btn-info btn-flat pull-right">Choisir consultants</a>
              <a href="servClient?action=choixConsultantsDef&idDev=<%=d.getId()%>" class="btn btn-sm btn-info btn-flat pull-right">Défaut</a>
            
         <%   } else if (listeC.size()==0 && dispo!=null && dispo.equals("no")) 
{%>  
<p>Malheureusement il n'y a pas de consultants disponibles pour la date d'intervention choisie (<%=dformat.format(d.getDateIntervSouhaitee())%>), <br> veuillez
                                                        choisir une autre date</p> 
                                                          <form role="form">
                                        <div class="box-body">
                                            <div class="form-group">
                                                         <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="date" id="datepicker" name="date">
                </div>

                                                                        <input type ="hidden" name="action" value="choixConsultants">
                                          <input type ="hidden" name="idDev" value="<%=d.getId()%>">
                                                                    </div>
                                                                    <!-- /.box-body -->

                                                                    <div class="box-footer">
                                                                        <button type="submit" class="btn btn-primary">Valider</button>
                                                                    </div></div>
                                                                    </form>
                                                         <p>Si aucune date ne vous correspond, contactez Hardis au 00-00-00-00-00</p> 

<} else {%> 
         <table class="table table-striped">
            <thead>
            <tr>
              <th>Consultant</th>
              <th>Quantité</th>
              <th>Unité</th>
              <th>Prix unitaire HT</th>
              <th>TotalHT</th>           
            </tr>
            </thead>
            <tbody>
                 <%  
                     for (int i=0; i<listeC.size(); i++) { 
                 float nbJ=0;
                 %>
            <tr>
               
              <td><%=listeLib.get(i)%></td>
              <td><% if (listeLib.get(i).toString().equals("Junior"))
              {  if (s.getId()!=null){
                  out.print(s.getNbreJoursConsultantJ());
                  nbJ = s.getNbreJoursConsultantJ();
              }
              else if (sns.getId()!=null)
              {
                  out.print(d.getNbJoursPresta());
                  nbJ = d.getNbJoursPresta();
              }
              }
              
              else if (listeLib.get(i).toString().equals("Senior")){
                   if (s.getId()!=null){
                  out.print(s.getNbreJoursConsultantS());
                  nbJ = s.getNbreJoursConsultantS();
              }
              else if (sns.getId()!=null)
              {
                  out.print(d.getNbJoursPresta());
                  nbJ = d.getNbJoursPresta();
              }
               
              }
              else{  
                  if (s.getId()!=null){
                  out.print(s.getNbreJoursConsultantC());
                  nbJ = s.getNbreJoursConsultantC();
              }
              else if (sns.getId()!=null)
              {
                  out.print(d.getNbJoursPresta());
                  nbJ = d.getNbJoursPresta();
              }          
              
              }
              
              %></td>
              <td>J</td>
              <td><%=PrixUnit.get(i)%></td>
              <td><%
                      out.print(nbJ*PrixUnit.get(i));
              tot+=nbJ*PrixUnit.get(i);%></td>

              <% }%>
            </tr>  
            </tbody>
          </table>
            <br>
            <b> Conditions de réglement : </b> 50% à la commande, 50% à la fin de la prestation, paiement à reception de facture
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      
      
      <div class="row">
        <!-- accepted payments column -->
        <div class="col-xs-6">
         
        </div>
        <!-- /.col -->
        <div class="col-xs-6">
             <br>
          <p class="lead">Paiement</p>

          <div class="table-responsive">
            <table class="table">   
                <tr>
                <th>TOTAL HT</th>
                <td><%=tot%></td>
              </tr>
              <tr>
                <th>TVA (%)</th>
                <td><%=tot*0.2%></td>
              </tr>

              <tr>
                <th>Total TTC</th>
           <td><%=tot*1.2%></td>
              </tr>
              
               <tr>
                   <% if (d.getStatut().toString().equals("Presta_Terminee")) 
                       out.print("  <th>Total 1er paiement</th>");
                       else    out.print(" <th>Total 2eme paiement</th>");
                    %>
              
               <td><%=tot*1.2 / 2%></td>
              </tr>
            </table>
          </div>
        </div>
        <!-- /.col -->
      </div>
            
              
      <!-- /.row -->
<% String valide = (String)request.getAttribute("valide");
      if ((d.getStatut().toString().equals("Valide") ||d.getStatut().toString().equals("Presta_terminee")) && f.getId()!=null)
      { %>
      <!-- this row will not appear when printing -->
      <div class="row no-print">
        <div class="col-xs-10">
         <a href="servClient?action=payer&idDev=<%=d.getId()%>&mont=<%=tot%>" <button type="button" class="btn btn-success pull-right">Payer
          </button>
        </a>
        </div>
          <div class="col-xs-2">
               <a href="servClient?action=retour" <button type="button" class="btn btn-danger pull-right">Annuler
          </button>
        </a>
        </div>
      </div>
         <%} else if (d.getStatut().toString().equals("Envoye") || d.getStatut().toString().equals("Rep_en_Cours")){%>
        
        <div class="row no-print">
        <div class="col-xs-12">
            <% String ss = "";
                if (d.getTypeDevis().toString().equals("Standard")) ss="ss";
                else if (d.getTypeDevis().toString().equals("Non_Standard"))
                    ss="sns";
            %>
                <div class="row">
                    <div class="col-md-10"> </div>
                    <div class="col-md-1">
            <a href="servClient?action=consulteDevis&typeD=<%=ss%>&valide=1&idDev=<%=d.getId()%>"><button type="button" class="btn btn-success"> Valider
                </button></a></div>
                <div class="col-md-1">
           <a href="servClient?action=consulteDevis&valide=0&idDev=<%=d.getId()%>"><button type="button" class="btn btn-warning" style="margin-right: 5px;">
            Réfuser
          </button>
           </a></div>
                </div>
        </div>
           <%  if (valide!=null && valide.equals("0")){%>
            <form role="form">
             <input type ="text" name="motifRefus" placeholder="Motif refus">
                    <input type ="hidden" name="action" value="consulteDevis">
                      <input type ="hidden" name="idDev" value="<%=d.getId()%>">
                    
             <a><button type="submit" class="btn btn-primary pull-right" style="margin-right: 5px;">
                     
            <i class="fa fa-download"></i> Valider réfus
          </button>
           </a>
            </form>
       <%    }%>
      </div> 
      <%}
}%>
        
    </section>
    <!-- /.content -->
    <div class="clearfix"></div>
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

