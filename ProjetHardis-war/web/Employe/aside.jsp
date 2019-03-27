<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="https://www.hardis-group.com/sites/all/themes/hardis/img/logo_footer.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><%=utilisateur.getNom() %>
          <a href="#"><i class="fa fa-circle text-success"></i>Visible</a></p>
        </div>
      </div>
      <!-- search form -->
<!--      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>-->
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">MENU</li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>Client</span>
            <span class="pull-right-container">
             
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="servEmployes?action=listesAfficherClient"><i class="fa  fa-search-plus"></i> Afficher Client</a></li>
            <!--<li><a href="servEmployes?action=listesCertifierClient"><i class="fa fa-check-circle"></i> Certifier Client</a></li>-->
            <!--<li><a href="servEmployes?action=ValiderClient"><i class="fa fa-circle-o"></i> Valider Client</a></li>-->
            <li><a href="servEmployes?action=AfficherEntreprise"><i class="fa  fa-search-plus"></i> Afficher Entreprise</a></li>
            <!--<li><a href="servEmployes?action=ValiderClient"><i class="fa fa-check-circle"></i> Valider Entreprise</a></li>-->
            <!--<li><a href="servEmployes?action=ValiderClient"><i class="fa fa-building-o"></i> Effacer Entreprise</a></li>-->
          </ul>
        </li>
        <li>
          <a href="servEmployes?action=listesDevis">
            <i class="fa fa-briefcase"></i> <span>Devis</span>
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
                <!--<li><a href="servEmployes?action=CreerAdresse"><i class="fa fa-plus"></i> Creer Adresse</a></li>-->
                <li><a href="servEmployes?action=AfficherAdresse"><i class="fa  fa-search-plus"></i> Afficher Adresse</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Agence
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa fa-plus"></i> Creer Agence</a></li>-->
                <li><a href="servEmployes?action=AfficherAgence"><i class="fa  fa-search-plus"></i> Afficher Agence</a></li>
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa  fa-remove"></i> Supprimer Agence</a></li>-->
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Ateliers
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <!--<li><a href="servEmployes?action=CreerAtelier"><i class="fa fa-plus"></i> Creer Ateliers</a></li>-->
                <li><a href="servEmployes?action=AfficherAtelier"><i class="fa  fa-search-plus"></i> Afficher Ateliers</a></li>
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa fa-edit "></i> Modifier Ateliers</a></li>-->
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa  fa-remove"></i> Supprimer Ateliers</a></li>-->
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Livrables
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <!--<li><a href="servEmployes?action=listesCreerLivrable"><i class="fa fa-plus"></i> Creer Livrable</a></li>-->
                <li><a href="servEmployes?action=AfficherLivrable"><i class="fa  fa-search-plus"></i> Afficher Livrable</a></li>
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa fa-edit "></i> Modifier Livrable</a></li>-->
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa  fa-remove"></i> Supprimer Livrable</a></li>-->
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Offre
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <!--<li><a href="servEmployes?action=CreerOffre"><i class="fa fa-plus"></i> Creer Offre</a></li>-->
                <li><a href="servEmployes?action=AfficherOffre"><i class="fa  fa-search-plus"></i> Afficher Offre</a></li>
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Ofrre</a></li>-->
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa  fa-remove"></i> Supprimer Offre</a></li>-->
              </ul>
            </li>            
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Service Standard
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <!--<li><a href="servEmployes?action=listesCreerServiceStandard"><i class="fa fa-plus"></i> Creer Service Standard</a></li>-->
                <li><a href="servEmployes?action=AfficherServiceStandard"><i class="fa  fa-search-plus"></i> Afficher Service Standard</a></li>
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Service Standard</a></li>-->
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa  fa-remove"></i> Supprimer Service Standard</a></li>-->
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Service
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <!--<li><a href="servEmployes?action=listesCreerService"><i class="fa fa-plus"></i> Creer Service</a></li>-->
                <li><a href="servEmployes?action=AfficherService"><i class="fa  fa-search-plus"></i> Afficher Service</a></li>
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Service</a></li>-->
                <!--<li><a href="servEmployes?action=CreerAgence"><i class="fa  fa-remove"></i> Supprimer Service</a></li>-->
              </ul>
            </li>
          </ul>
        </li>             
<!--        <li class="treeview">
          <a href="#">
            <i class="fa fa-users"></i>
            <span>Utilisateur</span>
            <span class="pull-right-container">
              
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="servEmployes?action=listesCreerUtiliateurHardis"><i class="fa fa-user-plus"></i> Creer Utilisateur</a></li>
            <li><a href="servEmployes?action=AfficherUtilisateur"><i class="fa  fa-search-plus"></i> Afficher Utilisateur</a></li>
            <li><a href="servEmployes?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Utilisateur</a></li>
            <li><a href="servEmployes?action=CreerAgence"><i class="fa  fa-remove"></i> Supprimer Utilisateur</a></li>
          </ul>
        </li>-->
        <li>
          <a href="servEmployes?action=calendar">
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