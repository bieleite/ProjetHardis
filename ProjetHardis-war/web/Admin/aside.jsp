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
              <span class="label label-primary pull-right">4</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Valider Client</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Effacer Client</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Certifier Client</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Hardis</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Agence
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Creer Agence</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Afficher Agence</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Agence</a></li>
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
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Afficher Ateliers</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Ateliers</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Ateliers</a></li>
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
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Afficher Utilisateur</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Utilisateur</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Utilisateur</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Utilisateur
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=listesCreerUtiliateurHardis"><i class="fa fa-circle-o"></i> Creer Utilisateur</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Afficher Utilisateur</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Utilisateur</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Utilisateur</a></li>
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
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Afficher Service Standard</a></li>
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
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Afficher Service</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Modifier Service</a></li>
                <li><a href="servAdmin?action=CreerAgence"><i class="fa fa-circle-o"></i> Supprimer Service</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-pie-chart"></i>
            <span>Entreprise</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> ChartJS</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Morris</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Flot</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Inline charts</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-laptop"></i>
            <span>Services</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> General</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Icons</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Buttons</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Sliders</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Timeline</a></li>
            <li><a href="servAdim?action=ValiderClient"><i class="fa fa-circle-o"></i> Modals</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-edit"></i> <span>Param�tres</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
            <ul class="treeview-menu">
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Adresse
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="servAdmin?action=CreerAdresse"><i class="fa fa-circle-o"></i> Creer Adresse</a></li>
                <li class="treeview">
                  <a href="#"><i class="fa fa-circle-o"></i> Level Two
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a href="servAdim?action=CreerAdresse"><i class="fa fa-circle-o"></i> Level Three</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-table"></i> <span>Tables</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/tables/simple.html"><i class="fa fa-circle-o"></i> Simple tables</a></li>
            <li><a href="pages/tables/data.html"><i class="fa fa-circle-o"></i> Data tables</a></li>
          </ul>
        </li>
        <li>
          <a href="pages/calendar.html">
            <i class="fa fa-calendar"></i> <span>Calendar</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-red">3</small>
              <small class="label pull-right bg-blue">17</small>
            </span>
          </a>
        </li>
        <li>
          <a href="pages/mailbox/mailbox.html">
            <i class="fa fa-envelope"></i> <span>Mailbox</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-yellow">12</small>
              <small class="label pull-right bg-green">16</small>
              <small class="label pull-right bg-red">5</small>
            </span>
          </a>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-folder"></i> <span>Examples</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/examples/invoice.html"><i class="fa fa-circle-o"></i> Invoice</a></li>
            <li><a href="pages/examples/profile.html"><i class="fa fa-circle-o"></i> Profile</a></li>
            <li><a href="pages/examples/login.html"><i class="fa fa-circle-o"></i> Login</a></li>
            <li><a href="pages/examples/register.html"><i class="fa fa-circle-o"></i> Register</a></li>
            <li><a href="pages/examples/lockscreen.html"><i class="fa fa-circle-o"></i> Lockscreen</a></li>
            <li><a href="pages/examples/404.html"><i class="fa fa-circle-o"></i> 404 Error</a></li>
            <li><a href="pages/examples/500.html"><i class="fa fa-circle-o"></i> 500 Error</a></li>
            <li><a href="pages/examples/blank.html"><i class="fa fa-circle-o"></i> Blank Page</a></li>
            <li><a href="pages/examples/pace.html"><i class="fa fa-circle-o"></i> Pace Page</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-share"></i> <span>Multilevel</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
            <li class="treeview">
              <a href="#"><i class="fa fa-circle-o"></i> Level One
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
                <li class="treeview">
                  <a href="#"><i class="fa fa-circle-o"></i> Level Two
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>