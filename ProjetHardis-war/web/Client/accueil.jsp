<%-- 
    Document   : newjsptest
    Created on : 14 mars 2019, 13:44:40
    Author     : 6170361
--%>

<%@page import="java.util.Date"%>
<%@page import="Entites.Devis"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
            <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean> 
               <jsp:useBean id="listeDevisAn" scope="session" class = "java.util.List"> </jsp:useBean> 
            
    </head>
    <body>
        <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Tableau de bord
       
      </h1>
     
    </section>
<%List<Devis> listeD = listeDevis; 
List<Devis> listeDAn = listeDevisAn;

int v = Integer.valueOf(session.getAttribute("nbD").toString());
int QSR = Integer.valueOf(session.getAttribute("nbQSR").toString());
float mont = Float.valueOf(session.getAttribute("mont").toString());
float delM = Float.valueOf(session.getAttribute("delM").toString());
int DNP = Integer.valueOf(session.getAttribute("nbDevisNP").toString());

%>
    <!-- Main content -->
    <section class="content">
     
              <!-- Small boxes (Stat box) -->
      <div class="row">
        <div class="col-lg-2 col-xs-2">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3><%=listeDAn.size()%></h3>

              <p>Devis</p>
            </div>
           

          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-2 col-xs-2">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner">
              <h3><%=v%></h3>

              <p>Devis en cours</p>
            </div>
            
          </div>
        </div>
              
                <div class="col-lg-2 col-xs-2">
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
              <h3><%=DNP%></h3>
              <p>Facture(s) non payée(s)</p>
            </div>
            
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-2 col-xs-2">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner">
              <h3><%=mont%></h3>

              <p>CA facture 2019</p>
            </div>
           
          </div>
        </div>
        <!-- ./col -->
      <div class="col-lg-2 col-xs-2">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3><%=(int)delM/60/24%></h3>

              <p>Délai moyen (jours)</p>
            </div>
           
          </div>
        </div>
              
                <div class="col-lg-2 col-xs-2">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3><%=QSR%></h3>

              <p>Questions sans réponse</p>
            </div>
           
          </div>
        </div>
        <!-- ./col -->
      </div>
              
      <!-- Main row -->
      <div class="row">

          
          
        <!-- Left col -->
        <div class="col-md-12">

           <!-- TABLE: LATEST ORDERS -->
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Historique de vos devis</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
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
                  </tr>
                  </thead>
                  <tbody>
                       <% listeD = listeDevis; 
                   for (Devis d : listeD)  {%>   
                   
                  <tr>
                      
                  
                   <td><a href="servClient?action=afficheDevis&idDev=<%=d.getId()%>"><% out.print("DEV"+d.getId());%></a></td>
                    <td><%=d.getService().getOffre().getLibelle()%></td>
                     <td><%=d.getService().getNomService()%></td>
                     
                    <td><% 
                      if (d.getStatut().toString().equals("Valide"))
                      
                           out.print("<span class=\"label label-danger\">Validé, en attente de paiement</span>");
                      
                       if (d.getStatut().toString().equals("Presta_terminee"))
                      
                           out.print("<span class=\"label label-danger\">Prestation terminée, en attente de paiement</span>");
                      
                      else if (d.getStatut().toString().equals("Refuse"))
                      
                           out.print("<span class=\"label label-warning\">Refusé</span>");
                        else if (d.getStatut().toString().equals("Total_regle"))
                       
                           out.print("<span class=\"label label-success\">Total reglé</span>");
                     
                      else if (d.getStatut().toString().equals("Acompte_regle"))
                       
                           out.print("<span class=\"label label-success\">Acompte reglé</span>");
                                              
                      else if (d.getStatut().toString().equals("Rep_en_Cours") && d.getTypeDevis().toString().equals("Standard"))
                           out.print("<span class=\"label label-info\">En attente de validation</span>");
 
                      else if (d.getStatut().toString().equals("Transmettre_au_client"))
                           out.print("<span class=\"label label-info\">Traitement demande</span>");
                      
                       else if (d.getStatut().toString().equals("Rep_en_Cours")&& d.getTypeDevis().toString().equals("Non_Standard"))
                           out.print("<span class=\"label label-info\">Traitement demande</span>");


                       else if (d.getStatut().toString().equals("Envoye"))
                       
                           out.print("<span class=\"label label-info\">En attent de validation</span>");
                   
                       
                      
                  %></td>
                               
                  </tr>
    <%}%>  
                  </tbody>
                </table>
              </div>
              <!-- /.table-responsive -->
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix">
              <a href="servClient?action=appelDevis" class="btn btn-sm btn-info btn-flat pull-left">Demande devis</a>
            </div>
            <!-- /.box-footer -->
          </div>
        </div>







         

     
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
                  
  <!-- /.content-wrapper -->
    </body>
</html>

