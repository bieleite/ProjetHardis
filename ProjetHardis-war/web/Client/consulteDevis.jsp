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
   <jsp:useBean id="facture" scope="request" class = "Entites.Facture"> </jsp:useBean>
    <jsp:useBean id="servS" scope="request" class = "Entites.ServiceStandard"> </jsp:useBean>
      <jsp:useBean id="listeConsu" scope="request" class = "java.util.List"> </jsp:useBean>
     
  <title>AdminLTE 2 | Dashboard</title>
 <%@include  file = "meta.jsp" %>
 
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

   <%@include  file = "menu.jsp" %>
  <% 
  Devis d = devis;
  Facture f= facture;
  List<UtilisateurHardis> listeC = listeConsu;
  ServiceStandard s = servS;
  
  SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");%>
  
   <body>
        <!-- Content Wrapper. Contains page content -->
    <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
          <% if (f.getId()!=null) out.print("Facture"); else out.print("Devis"); %>
        <small> <% if (f.getId()!=null)  { %> 
           <% out.print("F"+f.getId()); } else  { %>
              
             <% out.print("DEV"+d.getId()); }%></small>
      </h1>
     
    </section>

   

    <!-- Main content -->
    <section class="invoice">
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 class="page-header">
            <i class="fa fa-globe"></i> Hardis Group
           <% if (f.getId()!=null) { %>  <small class="pull-right">Date: <%=dformat.format(f.getDateFacture())%></small> <%}%>
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-4 invoice-col">
          De
          <address>
            <strong>Hardis Group <%=d.getClient().getAgence().getNomAgence().toString()%>  </strong><br>
            blabla adresse
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
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col">
            
          <b><% if (f.getId()!=null) { %> 
              Facture #<% out.print("F"+f.getId()); } else  { %>
              
              Devis #<% out.print("DEV"+d.getId()); }%></b>
          
          <br>
          <br>       
          <b>Code contrat : </b> <% out.print(d.getClient().getEntreprise().getCodeContrat()); %>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped">
            <thead>
            <tr>
               <th>Offre</th>
              <th>Service</th>
               <th>Type service</th>
              <th>Description</th>
              <th>Date intervention</th>

            </tr>
            </thead>
            <tbody>
            <tr>
              <td><%=d.getService().getOffre().getLibelle() %></td>
              <td><%=d.getService().getNomService().toString() %></td>
              <td><%=d.getService().getTypeService().toString() %></td>
            
             <td><%=d.getService().getDescriptionService()%></td>
              <td><%=dformat.format(d.getDateIntervSouhaitee()) %></td>
  

            </tr>  
            </tbody>
          </table>
            
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
            <tr>
              <td>Confirmé</td>
              <td><%=s.getNbreJoursConsultantC()%></td>
              <td>J</td>
              <td>1</td>
              <td><%=d.getService().getDescriptionService()%></td>
              <td><% %></td>
              <td>  </td>
              <td></td>
            </tr>  
            </tbody>
          </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      
      <% String valide = (String)request.getAttribute("valide");
      if (valide!=null && valide.equals("1"))
      { %>
      <div class="row">
        <!-- accepted payments column -->
        <div class="col-xs-6">
          <p class="lead">Méthodes de paiement:</p>
          <img src="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/dist/img/credit/visa.png" alt="Visa">
          <img src="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/dist/img/credit/mastercard.png" alt="Mastercard">
          <img src="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/dist/img/credit/american-express.png" alt="American Express">
          <img src="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/dist/img/credit/paypal2.png" alt="Paypal">

          
        </div>
        <!-- /.col -->
        <div class="col-xs-6">
          <p class="lead">Premier paiement</p>

          <div class="table-responsive">
            <table class="table">
             
              <tr>
                <th>TVA (%)</th>
                <td></td>
              </tr>

              <tr>
                <th>Total:</th>
           <td><%=d.getMontantDevis()/2%></td>
              </tr>
            </table>
          </div>
        </div>
        <!-- /.col -->
      </div>
            
              
      <!-- /.row -->

      <!-- this row will not appear when printing -->
      <div class="row no-print">
        <div class="col-xs-12">
         <a href="servClient?action=payer&idDev=<%=d.getId()%>&idF=<%=f.getId()%>" <button type="button" class="btn btn-success pull-right"><i class="fa fa-credit-card"></i> Payer
          </button>
        </a>
        </div>
      </div>
         <%} else if (d.getStatut().toString().equals("Rep_en_Cours")){%>
        
        <div class="row no-print">
        <div class="col-xs-12">
            <a href="servClient?action=consulteDevis&valide=1&idDev=<%=d.getId()%>"><button type="button" class="btn btn-success pull-right"><i class="fa fa-credit-card"></i> Valider
                </button></a>
           <a href="servClient?action=consulteDevis&valide=0&idDev=<%=d.getId()%>"><button type="button" class="btn btn-primary pull-right" style="margin-right: 5px;">
            <i class="fa fa-download"></i> Réfuser
          </button>
           </a>
        </div>
      </div> 
      <%}%>
        
    </section>
    <!-- /.content -->
    <div class="clearfix"></div>
  </div>
   
   
   

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.4.0
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights
    reserved.
  </footer>


  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>

</div>
<!-- ./wrapper -->



  <%@include  file = "script.jsp" %>
  
 
</body>
</html>

