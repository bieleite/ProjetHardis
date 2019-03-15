<%-- 
    Document   : tabBord
    Created on : 13 mars 2019, 15:07:11
    Author     : 6170361
--%>

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

     
  <title>AdminLTE 2 | Dashboard</title>
 <%@include  file = "meta.jsp" %>
 
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

   <%@include  file = "menu.jsp" %>
  <% Devis d = devis;%>
  
    <% List<Communication> liste = listMessage;
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
     
      <!-- Main row -->
      <div class="row">
          
   <div class="col-md-8">

                    <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Détail devis</h3>

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
                    <th>Status</th>
                     <th>Date intervention</th>
                    <th>Conditions</th>
                     <th>Devis</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                                              
                   <td><% 
                       out.print("DEV"+d.getId());
                       %></td>
                   <td><%=d.getService().getOffre().getLibelle()%></td>
                   <td><%=d.getService().getNomService()%></td>
                   <td><%=d.getStatut()%></span></td>
                    <td><%=d.getDateIntervSouhaitee()%></span></td>
                   <td><%=d.getService().getConditionsContract()%></span></td>
                   <td><%=d.getHistoriqueDeviss().get(0).getDocuments().get(0).getLienDoc()%></span></td>
                  
                  </tr>

                  </tbody>
                </table>
              </div>
              <!-- /.table-responsive -->
            </div>
                   <div row>
                          <div class="box-footer clearfix">
                              <div class="col-md-10">
              <a href="servClient?action=appelDevis" class="btn btn-sm btn-info btn-flat pull-right">Accepte devis</a>
                              </div>
               <div class="col-md-2">
              <a href="servClient?action=appelDevis" class="btn btn-sm btn-danger btn-flat pull-right">Refuse devis</a>
               </div></div>
                   </div>
            <!-- /.box-body -->
           
                    </div>
            <!-- /.box-footer -->

          </div>      
            <div class="row">
                <div class="col-md-4">
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
                                        <input type ="hidden" name="action" value="poseQuestDev">
                                        <input type ="hidden" name="idD" value="<%=d.getId()%>">
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
