<%-- 
    Document   : menu
    Created on : 14 mars 2019, 14:00:29
    Author     : 6170361
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <jsp:useBean id="client" scope="session" class = "Entites.Client"> </jsp:useBean>
    <jsp:useBean id="listeNotif" scope="session" class = "java.util.List"> </jsp:useBean>
        <title>JSP Page</title>
    </head>
  <header class="main-header">

    <!-- Logo -->
    <a href="servClient?action=retour" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->

      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg">Hardis Group
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->

                </ul>
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>


          <% List<Notification> listeN = listeNotif;
             int nbreN = listeN.size();%>
                  
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning"><%=nbreN%></span>
            </a>
            <ul class="dropdown-menu">
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                    <% for (Notification n : listeN){%>
                  <li>
                     <%=n.getMessage()%>
                  </li>
                  <%}%>
                </ul>
              </li>
            </ul>
          </li>


          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
         
              <span class="hidden-xs">  <% Client cli = client; 
             out.println(cli.getNom()+" "+cli.getPrenom()); 
%>  <i class="fa fa-circle text-success"></i> Online</a></span> 
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
       

                
              </li>

              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="servClient?action=deconnexion" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
        
        </ul>
      </div>

    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->


      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">


        <li>
          <a href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/pages/mailbox/mailbox.html">
            <i class="fa fa-envelope"></i> <span>Messages</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-yellow">12</small>

            </span>
          </a>
        </li>

      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
</html>
