</ul><div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
           
           <!-- Messages: style can be found in dropdown.less-->
           <% List<Communication> lesCommunication=listeCommunication;
             int ncomunication = lesCommunication.size();%>
             
                  
           <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa  fa-comments-o"></i>
              <span class="label label-info"><%=ncomunication%></span>
            </a>
            <ul class="dropdown-menu">
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                    <% for(Communication co : lesCommunication){%>
                  <li>
                     <%=co.getUtilisateurHardis().getNom() %>
                  </li>
                  <li>
                     <%=co.getMessage() %>
                  </li>
                  <%}%>
                </ul>
              </li>
            </ul>
          </li>
           <!-- end message -->
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
          <!-- Tasks: style can be found in dropdown.less -->
         <% List<Devis> listedevis = listeDevis;
             int nbreDevis = listedevis.size();%>
          <li class="dropdown tasks-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger"><%=nbreDevis %></span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have <%=nbreDevis %> tasks</li>
            </ul>
            </li> 
                <!-- inner menu: contains the actual data -->
                
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
         
              <span class="hidden-xs">  <%= utilisateur.getNom() %>          
                  <i class="fa fa-circle text-success"></i> Online</a></span> 
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header"> 
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="servAdmin?action=formUtilisateur&idUtili=<%=utilisateur.getId().toString() %>" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="servAdmin?action=deconnexion" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          
        </ul>
      </div>
