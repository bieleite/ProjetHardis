<%-- 
    Document   : tabBord
    Created on : 13 mars 2019, 15:07:11
    Author     : 6170361
--%>

<%@page import="Entites.Offre_Profil_Util_CV"%>
<%@page import="Entites.UtilisateurHardis"%>
<%@page import="Entites.ServiceStandard"%>
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
        <jsp:useBean id="serviceS" scope="request" class = "Entites.ServiceStandard"> </jsp:useBean>
        <jsp:useBean id="listeC" scope="request" class = "java.util.List"> </jsp:useBean>
        <jsp:useBean id="listeJ" scope="request" class = "java.util.List"> </jsp:useBean>
        <jsp:useBean id="listeS" scope="request" class = "java.util.List"> </jsp:useBean>
        <jsp:useBean id="offreP" scope="request" class = "java.util.List"> </jsp:useBean>




            <title>AdminLTE 2 | Dashboard</title>
        <%@include  file = "meta.jsp" %>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <%@include  file = "menu.jsp" %>
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


                            <!-- left column -->
                            <div class="col-md-8">
                                <div class="box box-primary">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Choisir consultants pour son devis</h3>
                                    </div>

                                    <form role="form">
                                        <div class="box-body">
                                            <div class="form-group">
                                                <label>Offre & service</label>

                                                <% Devis d = devis;
                                                    out.print(d.getTypeDevis().toString());
                                                    ServiceStandard s = serviceS;

                                                    List<UtilisateurHardis> listeUC = listeC;
                                                    List<UtilisateurHardis> listeUJ = listeJ;
                                                    List<UtilisateurHardis> listeUS = listeS;
                                                    List<Offre_Profil_Util_CV> listeOPC = offreP;

                                                    if (listeUC.size() > 0) {
                                                %>

                                                <div class="box box-info">
                                                    <div class="box-header with-border">
                                                        <h3 class="box-title">Liste Consultants confirmés disponibles</h3>

                                                    </div>
                                                    <!-- /.box-header -->
                                                    <div class="box-body">
                                                        <div class="table-responsive">
                                                            <table class="table no-margin">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Nom</th>
                                                                        <th>Prénom</th>
                                                                        <th>CV</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%
                                                                        for (UtilisateurHardis u : listeUC) {
                                                                            String lienCV = "";
                                                                            for (Offre_Profil_Util_CV o : listeOPC) {
                                                                                if (u.equals(o.getUtilisateur())) {
                                                                                    lienCV = o.getLienCV();
                                                                                }
                                                                            }

                                                                    %> 
                                                                    <tr>


                                                                        <td><%=u.getNom()%></td>
                                                                        <td><%=u.getPrenom()%></td>
                                                                        <td><%=lienCV%></td>

                                                                    </tr> <% }
                                                                    } else if (listeUJ.size() > 0) {
                                                                    %>
                                                                <div class="box box-info">
                                                                    <div class="box-header with-border">
                                                                        <h3 class="box-title">Liste Consultants Junior disponibles</h3>

                                                                    </div>
                                                                    <!-- /.box-header -->
                                                                    <div class="box-body">
                                                                        <div class="table-responsive">
                                                                            <table class="table no-margin">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th>Nom</th>
                                                                                        <th>Prénom</th>
                                                                                        <th>CV</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <%
                                                                                        for (UtilisateurHardis u : listeUJ) {
                                                                                            String lienCV = "";
                                                                                            for (Offre_Profil_Util_CV o : listeOPC) {
                                                                                                if (u.equals(o.getUtilisateur())) {
                                                                                                    lienCV = o.getLienCV();
                                                                                                }
                                                                                            }

                                                                                    %> 
                                                                                    <tr>


                                                                                        <td><%=u.getNom()%></td>
                                                                                        <td><%=u.getPrenom()%></td>
                                                                                        <td><%=lienCV%></td>

                                                                                    </tr> <% }
                                                                                    } else if (listeUS.size() > 0) {
                                                                                    %>
                                                                                <div class="box box-info">
                                                                                    <div class="box-header with-border">
                                                                                        <h3 class="box-title">Liste Consultants confirmés disponibles</h3>

                                                                                    </div>
                                                                                    <!-- /.box-header -->
                                                                                    <div class="box-body">
                                                                                        <div class="table-responsive">
                                                                                            <table class="table no-margin">
                                                                                                <thead>
                                                                                                    <tr>
                                                                                                        <th>Nom</th>
                                                                                                        <th>Prénom</th>
                                                                                                        <th>CV</th>
                                                                                                    </tr>
                                                                                                </thead>
                                                                                                <tbody>
                                                                                                    <%
                                                                                                        for (UtilisateurHardis u : listeUS) {
                                                                                                            String lienCV = "";
                                                                                                            for (Offre_Profil_Util_CV o : listeOPC) {
                                                                                                                if (u.equals(o.getUtilisateur())) {
                                                                                                                    lienCV = o.getLienCV();
                                                                                                                }
                                                                                                            }

                                                                                                    %> 
                                                                                                    <tr>


                                                                                                        <td><%=u.getNom()%></td>
                                                                                                        <td><%=u.getPrenom()%></td>
                                                                                                        <td><%=lienCV%></td>

                                                                                                    </tr> <% }
                                                                                                        }
                                                                                                    %>


                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>
                                                                                        <!-- /.table-responsive -->
                                                                                    </div>



                                                                                    <!-- /.box-footer -->
                                                                                </div>

                                                                                <%}%>
                                                                                </select>
                                                                        </div>

                                                                        <div class="form-group">
                                                                            <label>Date:</label>

                                                                            <div class="input-group date">
                                                                                <div class="input-group-addon">
                                                                                    <i class="fa fa-calendar"></i>
                                                                                </div>
                                                                                <input type="date" id="datepicker" name="date">
                                                                            </div>
                                                                            <!-- /.input group -->
                                                                        </div>
                                                                        <!-- /.form group -->


                                                                        <div class="form-group">
                                                                            <label>Informations complémentaires</label>
                                                                            <textarea name="libre" class="form-control" rows="3" placeholder="Saisir..."></textarea>
                                                                        </div>
                                                                        <input type ="hidden" name="action" value="creerDevis">

                                                                    </div>
                                                                    <!-- /.box-body -->

                                                                    <div class="box-footer">
                                                                        <button type="submit" class="btn btn-primary">Valider</button>
                                                                    </div>
                                                                    </form>
                                                                </div>


                                                        </div>
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

