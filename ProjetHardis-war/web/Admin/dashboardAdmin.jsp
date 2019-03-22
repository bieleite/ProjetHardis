<%-- 
    Document   : AfficheOffres
    Created on : 12 mars 2019, 15:40:05
    Author     : 6170361
--%>

<%@page import="Entites.ContactMail"%>
<%@page import="Entites.UtilisateurHardis"%>
<%@page import="Entites.Entreprise"%>
<%@page import="java.text.SimpleDateFormat"%>
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
    <jsp:useBean id="listeEntreprise" scope="session" class = "java.util.List"> </jsp:useBean>    
    <jsp:useBean id="listeUtilisateurHardis" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeContactMail" scope="session" class = "java.util.List"> </jsp:useBean>
    <jsp:useBean id="listeUtilisateurHardisReponseContactMail" scope="session" class = "java.util.List"> </jsp:useBean>

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
        <small><% 
                String attribut = (String) request.getAttribute("message");
                out.println(attribut);
                %></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Tableau de bord</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3><%=nbreDevis%></h3>

              <p>Devis</p>
            </div>
            <div class="icon">
              <i class="ion ion-bag"></i>
            </div>
            <a href="servAdmin?action=listesDevis" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <% List<Client> lesClient=listeClient;
             int nclient = lesClient.size();%>
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner">
              <h3><%=nclient%><sup style="font-size: 20px"></sup></h3>

              <p>Client</p>
            </div>
            <div class="icon">
              <i class="ion ion-stats-bars"></i>
            </div>
            <a href="servAdmin?action=listesAfficherClient" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <% List<Entreprise> lesEntreprise=listeEntreprise;
             int nEntreprise = lesEntreprise.size();%>
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3><%=nEntreprise%></h3>

              <p>Entreprise </p>
            </div>
            <div class="icon">
              <i class="ion ion-person-add"></i>
            </div>
            <a href="servAdmin?action=AfficherEntreprise" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <% List<UtilisateurHardis> lesUtilisateurHardis=listeUtilisateurHardis;
             int nUtilisateurHardis = lesUtilisateurHardis.size();%>
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
              <h3><%=nUtilisateurHardis%></h3>

              <p>Utilisateur Hardis</p>
            </div>
            <div class="icon">
              <i class="ion ion-pie-graph"></i>
            </div>
            <a href="servAdmin?action=AfficherUtilisateur" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
      </div>
      <!-- /.row -->
      <!-- Main row -->
      <div class="row"> 
        <!-- Left col -->
        <section class="col-lg-7 connectedSortable">
          <!-- Custom tabs (Charts with tabs)-->
          <div class="nav-tabs-custom">
            <!-- Tabs within a box -->
            <div class="box-header">
                <h3 class="box-title"><i class="fa fa-inbox"></i><a href="servAdmin?action=listesDevis">Devis</a></h3>
            
            </div>
            <div class="tab-content no-padding">
              <!-- Morris chart - Sales -->
              
                  <div class="box">
                     <!-- /.box-body -->
                    
                        <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>Agence</th>
                  <th>Client</th>
                  <th>Entreprise</th>
                  <th>Service</th>
                  <th>Statut</th>
                  <th>Type Devis</th>
                  <th>Date Devis</th>
                </tr>
                </thead>
                 <% SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
                     for (Devis devis : listedevis){%>
                <tbody>
                <tr>
                    <td><%=devis.getAgence().getNomAgence() %> </td>
                    <td><%=devis.getClient().getNom() %> </td>
                     <td><%=devis.getClient().getEntreprise().getNomEntreprise() %></td>
                     <td><%=devis.getService().getNomService() %> </td>
                    
                     <td><%=devis.getStatut().name() %> </td>
                      <td><%=devis.getTypeDevis().name() %> </td>
                      <td><%=dformat.format(devis.getDateDevis()) %> </td>
                 
                  
                </tr>
                </tbody>
                <%}%>
                
              </table>
                     
                    <!-- /.box-body -->
                  </div>
                  
              
            </div>
          </div>
          <!-- /.nav-tabs-custom -->
          

          <!-- TO DO List -->
          <div class="box box-primary">
            <div class="box-header">
              <i class="ion ion-clipboard"></i>

              <h3 class="box-title">To Do List</h3>

              <div class="box-tools pull-right">
                <ul class="pagination pagination-sm inline">
                  <li><a href="#">&laquo;</a></li>
                  <li><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">&raquo;</a></li>
                </ul>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <!-- See dist/js/pages/dashboard.js to activate the todoList plugin -->
              <ul class="todo-list">
                <li>
                  <!-- drag handle -->
                  <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <!-- checkbox -->
                  <input type="checkbox" value="">
                  <!-- todo text -->
                  <span class="text">Design a nice theme</span>
                  <!-- Emphasis label -->
                  <small class="label label-danger"><i class="fa fa-clock-o"></i> 2 mins</small>
                  <!-- General tools such as edit or delete-->
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Make the theme responsive</span>
                  <small class="label label-info"><i class="fa fa-clock-o"></i> 4 hours</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-warning"><i class="fa fa-clock-o"></i> 1 day</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-success"><i class="fa fa-clock-o"></i> 3 days</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Check your messages and notifications</span>
                  <small class="label label-primary"><i class="fa fa-clock-o"></i> 1 week</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
                <li>
                      <span class="handle">
                        <i class="fa fa-ellipsis-v"></i>
                        <i class="fa fa-ellipsis-v"></i>
                      </span>
                  <input type="checkbox" value="">
                  <span class="text">Let theme shine like a star</span>
                  <small class="label label-default"><i class="fa fa-clock-o"></i> 1 month</small>
                  <div class="tools">
                    <i class="fa fa-edit"></i>
                    <i class="fa fa-trash-o"></i>
                  </div>
                </li>
              </ul>
            </div>
            <!-- /.box-body -->
            <div class="box-footer clearfix no-border">
              <button type="button" class="btn btn-default pull-right"><i class="fa fa-plus"></i> Add item</button>
            </div>
          </div>
          <!-- /.box -->

          

        </section>
        <!-- /.Left col -->
        <!-- right col (We are only adding the ID to make the widgets sortable)-->
        <section class="col-lg-5 connectedSortable">
            <% List<ContactMail> lesContactMail=listeContactMail;%>
          <!-- Chat box -->
          <div class="box box-success">
            <div class="box-header">
              <i class="fa fa-comments-o"></i>

              <h3 class="box-title">Contact Client</h3>

              
            </div>
            <div class="box-body chat" id="chat-box">
             <% for (ContactMail contactMail : lesContactMail){%>
                <!-- chat item -->
              <div class="item">
                <img src="https://www.hardis-group.com/sites/all/themes/hardis/logo.png" alt="user image" class="online">
                <p class="message">
                  <a href="#" class="name">
                    <small class="text-muted pull-right">
                        <div class="box-tools pull-right" >
                            <div class="btn-group" data-toggle="btn-toggle">
                                <a href="servAdmin?action=Menu&acao=repondre&idContactMail=<%=contactMail.getId().toString() %>" ><button type="button" class="btn btn-default btn-sm active"><i class="fa fa-square text-green"></i>Repondre</button></a>
                                <% if(contactMail.getUtilisateurHardis()!=null)   {%>
                                <button type="button" class="btn btn-default btn-sm"><i class="fa fa-square text-black "></i><%=contactMail.getUtilisateurHardis().getNom() %></button>
                                <%}%>
                                <% if(contactMail.getUtilisateurHardis()==null )     {%>
                                <a href="servAdmin?action=Menu&acao=affecter&idContactMail=<%=contactMail.getId().toString() %>" ><button type="button" class="btn btn-default btn-sm active"><i class="fa fa-square text-red"></i>Affecter</button></a>
                                <%}%>
                            </div>
                        </div>
                    </small>
                   <%=contactMail.getNom()  %> 
                  </a>
                   Sujet: <%=contactMail.getSujet()  %>
                 <%=contactMail.getMessage()  %>
                </p>
                
                <!-- /.attachment -->
              </div>
              <!-- /.item -->
              
             <%}%>
              <% String test = (String) request.getAttribute("acao");%>
              <% String idcontactmaill = (String) request.getAttribute("idContactMail");%>
            </div>
            <!-- /.chat -->
            <% if( test!=null&&test.equals("repondre") ){%>
            <form action="servAdmin" method="post" >
            
            <div class="box-footer">
              <div class="input-group">
                <input class="form-control" name="textContactMail" placeholder="Type message...">

                <div class="input-group-btn">
                <input type="hidden" name="action" value="ContactMail">
                <input type="hidden" name="idContactMail" value="<% out.print(idcontactmaill); %>">
                <input type="hidden" name="idutilisateur" value="<%=utilisateur.getId()%>">
                  <button type="submit" class="btn btn-success"><i class="fa fa-plus"></i></button>
                  
                </div>
              </div>
            </div>
          
            </form>
            <%}%>
            <% List<UtilisateurHardis> lesUHRCM=listeUtilisateurHardisReponseContactMail;%>
            <% if( !lesUHRCM.isEmpty() ){%>
            <form>
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4> Alert!</h4>
                    <select name="AgentAffecterContactMail" class="form-control">
                                
                               <% for (UtilisateurHardis age : lesUHRCM){%>
                            <option value="<%=age.getId() %>"> <%=age.getNom()%>  </option> 
                            <%}%>
                    </select>
                    <input type="hidden" name="idContactMail" value="<% out.print(idcontactmaill); %>">
                    <input type="hidden" name="action" value="AffecterContactMail">
                    <button type="submit" class="btn btn-success"><i class="fa fa-plus"></i></button>
                </div>
                
            </form>
            <%}%>
            </div>
          <!-- /.box (chat box) -->
            <!-- quick email widget -->
          <div class="box box-info">
            <div class="box-header">
              <i class="fa fa-envelope"></i>

              <h3 class="box-title">Communication</h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
                <button type="button" class="btn btn-info btn-sm" data-widget="remove" data-toggle="tooltip"
                        title="Remove">
                  <i class="fa fa-times"></i></button>
              </div>
              <!-- /. tools -->
            </div>
            <div class="box-body">
              <form action="servAdmin" method="post">
                <div class="form-group">
                  <input type="email" class="form-control" name="emailto" placeholder="Email to:">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="subject" placeholder="Subject">
                </div>
                <div>
                  <textarea class="textarea" placeholder="Message" name="textmail"
                            style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                </div>
                  <input type="hidden" name="action" value="ContactMail">
                  <div class="box-footer clearfix">
              <button type="submit" class="pull-right btn btn-default" id="sendEmail">Send
                <i class="fa fa-arrow-circle-right"></i></button>
            </div>
              </form>
            </div>
            
          </div>
          

          <!-- solid sales graph -->
          <div class="box box-solid bg-teal-gradient">
            <div class="box-header">
              <i class="fa fa-th"></i>

              <h3 class="box-title">Sales Graph</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn bg-teal btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn bg-teal btn-sm" data-widget="remove"><i class="fa fa-times"></i>
                </button>
              </div>
            </div>
            <div class="box-body border-radius-none">
              <div class="chart" id="line-chart" style="height: 250px;"></div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer no-border">
              <div class="row">
                <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                  <input type="text" class="knob" data-readonly="true" value="20" data-width="60" data-height="60"
                         data-fgColor="#39CCCC">

                  <div class="knob-label">Mail-Orders</div>
                </div>
                <!-- ./col -->
                <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                  <input type="text" class="knob" data-readonly="true" value="50" data-width="60" data-height="60"
                         data-fgColor="#39CCCC">

                  <div class="knob-label">Online</div>
                </div>
                <!-- ./col -->
                <div class="col-xs-4 text-center">
                  <input type="text" class="knob" data-readonly="true" value="30" data-width="60" data-height="60"
                         data-fgColor="#39CCCC">

                  <div class="knob-label">In-Store</div>
                </div>
                <!-- ./col -->
              </div>
              <!-- /.row -->
            </div>
            <!-- /.box-footer -->
          </div>
          <!-- /.box -->

          <!-- Calendar -->
          <div class="box box-solid bg-green-gradient">
            <div class="box-header">
              <i class="fa fa-calendar"></i>

              <h3 class="box-title">Calendar</h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
                <!-- button with a dropdown -->
                <div class="btn-group">
                  <button type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-bars"></i></button>
                  <ul class="dropdown-menu pull-right" role="menu">
                    <li><a href="#">Add new event</a></li>
                    <li><a href="#">Clear events</a></li>
                    <li class="divider"></li>
                    <li><a href="#">View calendar</a></li>
                  </ul>
                </div>
                <button type="button" class="btn btn-success btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-success btn-sm" data-widget="remove"><i class="fa fa-times"></i>
                </button>
              </div>
              <!-- /. tools -->
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <!--The calendar -->
              <div id="calendar" style="width: 100%"></div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer text-black">
              <div class="row">
                <div class="col-sm-6">
                  <!-- Progress bars -->
                  <div class="clearfix">
                    <span class="pull-left">Task #1</span>
                    <small class="pull-right">90%</small>
                  </div>
                  <div class="progress xs">
                    <div class="progress-bar progress-bar-green" style="width: 90%;"></div>
                  </div>

                  <div class="clearfix">
                    <span class="pull-left">Task #2</span>
                    <small class="pull-right">70%</small>
                  </div>
                  <div class="progress xs">
                    <div class="progress-bar progress-bar-green" style="width: 70%;"></div>
                  </div>
                </div>
                <!-- /.col -->
                <div class="col-sm-6">
                  <div class="clearfix">
                    <span class="pull-left">Task #3</span>
                    <small class="pull-right">60%</small>
                  </div>
                  <div class="progress xs">
                    <div class="progress-bar progress-bar-green" style="width: 60%;"></div>
                  </div>

                  <div class="clearfix">
                    <span class="pull-left">Task #4</span>
                    <small class="pull-right">40%</small>
                  </div>
                  <div class="progress xs">
                    <div class="progress-bar progress-bar-green" style="width: 40%;"></div>
                  </div>
                </div>
                <!-- /.col -->
              </div>
              <!-- /.row -->
            </div>
          </div>
          <!-- /.box -->

        </section>
        <!-- right col -->
      </div>
      <!-- /.row (main row) -->

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

