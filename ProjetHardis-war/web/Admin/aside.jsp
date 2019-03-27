<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="https://www.hardis-group.com/sites/all/themes/hardis/img/logo_footer.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><%=utilisateur.getNom() %></p>
          <a href="#"><i class="fa fa-circle text-success"></i>Visible</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">MENU</li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Client</span>
            <span class="pull-right-container">
             
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="servAdmin?action=listesAfficherClient"><i class="fa fa-circle-o"></i> Afficher Client</a></li>
            <li><a href="servAdmin?action=listesCertifierClient"><i class="fa fa-circle-o"></i> Certifier Client</a></li>
            <li><a href="servAdmin?action=ValiderClient"><i class="fa fa-circle-o"></i> Valider Client</a></li>
            <li><a href="servAdmin?action=AfficherEntreprise"><i class="fa fa-circle-o"></i> Afficher Entreprise</a></li>
            <li><a href="servAdmin?action=ValiderClient"><i class="fa fa-circle-o"></i> Valider Entreprise</a></li>
            <li><a href="servAdmin?action=ValiderClient"><i class="fa fa-circle-o"></i> Effacer Entreprise</a></li>
          </ul>
        </li>
        <li>
          <a href="servAdmin?action=listesDevis">
            <i class="fa fa-files-o"></i> <span>Devis</span>
            <span class="pull-right-container">
              
            </span>
          </a>
        </li>
         
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Hardis</span>
            <span class="pull-right-container">
              
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Adresse
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=CreerAdresse"><i class="fa fa-circle-o"></i> Creer Adresse</a></li>
                <li><a href="servAdmin?action=AfficherAdresse"><i class="fa fa-circle-o"></i> Afficher Adresse</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Agence
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Creer Agence</a></li>
                <li><a href="servAdmin?action=AfficherAgence"><i class="fa fa-circle-o"></i> Afficher Agence</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Agence</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Ateliers
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=CreerAtelier"><i class="fa fa-circle-o"></i> Creer Ateliers</a></li>
                <li><a href="servAdmin?action=AfficherAtelier"><i class="fa fa-circle-o"></i> Afficher Ateliers</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Ateliers</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Ateliers</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Livrables
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=listesCreerLivrable"><i class="fa fa-circle-o"></i> Creer Livrable</a></li>
                <li><a href="servAdmin?action=AfficherLivrable"><i class="fa fa-circle-o"></i> Afficher Livrable</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Livrable</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Livrable</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Offre
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=CreerOffre"><i class="fa fa-circle-o"></i> Creer Offre</a></li>
                <li><a href="servAdmin?action=AfficherOffre"><i class="fa fa-circle-o"></i> Afficher Offre</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Ofrre</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Offre</a></li>
              </ul>
            </li>            
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Service Standard
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=listesCreerServiceStandard"><i class="fa fa-circle-o"></i> Creer Service Standard</a></li>
                <li><a href="servAdmin?action=AfficherServiceStandard"><i class="fa fa-circle-o"></i> Afficher Service Standard</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Service Standard</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Service Standard</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Service
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=listesCreerService"><i class="fa fa-circle-o"></i> Creer Service</a></li>
                <li><a href="servAdmin?action=AfficherService"><i class="fa fa-circle-o"></i> Afficher Service</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Service</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Service</a></li>
              </ul>
            </li>
          </ul>
        </li>             
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Utilisateur</span>
            <span class="pull-right-container">
              
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="servAdmin?action=listesCreerUtiliateurHardis"><i class="fa fa-circle-o"></i> Creer Utilisateur</a></li>
            <li><a href="servAdmin?action=AfficherUtilisateur"><i class="fa fa-circle-o"></i> Afficher Utilisateur</a></li>
            <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Utilisateur</a></li>
            <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Utilisateur</a></li>
          </ul>
        </li>
        <li>
          <a href="servAdmin?action=calendar">
            <i class="fa fa-calendar"></i> <span>Calendar</span>
            <span class="pull-right-container">
              
            </span>
          </a>
        </li>
<!--        <li>
          <a href="#">
            <i class="fa fa-envelope"></i> <span>Mailbox</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-yellow">12</small>
              <small class="label pull-right bg-green">16</small>
              <small class="label pull-right bg-red">5</small>
            </span>
          </a>
        </li>      -->
        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>