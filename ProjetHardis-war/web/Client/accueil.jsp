<%-- 
    Document   : newjsptest
    Created on : 14 mars 2019, 13:44:40
    Author     : 6170361
--%>

<%@page import="Entites.Devis"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean>       
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

    <!-- Main content -->
    <section class="content">
     
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
                    <th>Status</th>
                  </tr>
                  </thead>
                  <tbody>
                       <% List<Devis> listeD = listeDevis; 
                   for (Devis d : listeD)  {%>   
                   
                  <tr>
                      
                  
                   <td><a href="servClient?action=afficheDevis&idDev=<%=d.getId()%>"><% out.print("DEV"+d.getId());%></a></td>
                    <td><%=d.getService().getOffre().getLibelle()%></td>
                     <td><%=d.getService().getNomService()%></td>
                    <td><%=d.getStatut()%></span></td>
                               
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
        
        
          <!-- 
            <div class="col-md-4">

              <div class="box box-warning direct-chat direct-chat-warning">
                <div class="box-header with-border">
                  <h3 class="box-title">Direct Chat</h3>

                  <div class="box-tools pull-right">
                    <span data-toggle="tooltip" title="3 New Messages" class="badge bg-yellow">3</span>
                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                    </button>
                    <button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="Contacts"
                            data-widget="chat-pane-toggle">
                      <i class="fa fa-comments"></i></button>
                    <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                    </button>
                  </div>
                </div>

          
          
                <div class="box-body">

                  <div class="direct-chat-messages">

                    <div class="direct-chat-msg">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-left">Alexander Pierce</span>
                        <span class="direct-chat-timestamp pull-right">23 Jan 2:00 pm</span>
                      </div>

                      <img class="direct-chat-img" src="dist/img/user1-128x128.jpg" alt="message user image">

                      <div class="direct-chat-text">
                        Is this template really for free? That's unbelievable!
                      </div>

                    </div>


                    <div class="direct-chat-msg right">
                      <div class="direct-chat-info clearfix">
                        <span class="direct-chat-name pull-right">Sarah Bullock</span>
                        <span class="direct-chat-timestamp pull-left">23 Jan 2:05 pm</span>
                      </div>

                      <img class="direct-chat-img" src="dist/img/user3-128x128.jpg" alt="message user image">

                      <div class="direct-chat-text">
                        You better believe it!
                      </div>

                    </div>



                  </div>


                  </div>

                </div>

                <div class="box-footer">
                  <form action="#" method="post">
                    <div class="input-group">
                      <input type="text" name="message" placeholder="Type Message ..." class="form-control">
                      <span class="input-group-btn">
                            <button type="button" class="btn btn-warning btn-flat">Send</button>
                          </span>
                    </div>
                  </form>
                </div>

              </div>
              <!--/.direct-chat -->







         

     
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
    </body>
</html>