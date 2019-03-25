<%-- 
    Document   : menu
    Created on : 14 mars 2019, 14:00:29
    Author     : 6170361
--%>

<%@page import="Entites.Client"%>
<%@page import="java.util.List"%>
<%@page import="Entites.Notification"%>
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
            
             <% List<Notification> listeN = listeNotif;
             int nbreN = listeN.size();%>
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success"><%=nbreN%></span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">Vous avez <%=nbreN%> notifications</li>
              <li>
                
                  
                <ul class="menu">
                    
                      <% for (Notification n : listeN){%>
                  <li>
                   
                <p>  - <%=n.getMessage()%></p>
                
                     
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
                  <a href="servClient?action=majEnt" class="btn btn-default btn-flat">Profile</a>
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
     
    </section>
    <!-- /.sidebar -->
  </aside>
</html>
