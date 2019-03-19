<%-- 
    Document   : tabBord
    Created on : 13 mars 2019, 15:07:11
    Author     : 6170361
--%>

<%@page import="java.text.SimpleDateFormat"%>
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

                                    <form role="form" method="get" action="servClient">
                                        <div class="box-body">
                                            <div class="form-group">
                                              

                                                <% Devis d = devis;
                                                  
                                                    ServiceStandard s = serviceS;

                                                    List<UtilisateurHardis> listeUC = listeC;
                                                    List<UtilisateurHardis> listeUJ = listeJ;
                                                    List<UtilisateurHardis> listeUS = listeS;
                                                    List<Offre_Profil_Util_CV> listeOPC = offreP;
                                                    String valide = (String)request.getAttribute("valide");
                                                     SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
                                                    if (valide!=null && valide.equals("n")) //pas de consultants dispos pour cette date
                                                        
                                                    {
                                                        %>
                                                        
                                                        <p>Malheureusement il n'y a pas de consultants disponibles pour la date d'intervention choisie (<%=dformat.format(d.getDateIntervSouhaitee())%>), <br> veuillez
                                                        choisir une autre date</p> 
                                                        
                                                         <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="date" id="datepicker" name="date">
                </div>
                                                         <p>Si aucune date ne vous correspond, contactez Hardis au 00-00-00-00-00</p> 
                                                    
                                                   <% }
                                                       

                                                    else  {
                                                        if (listeUC.size() > 0) {
                                                %>

                                                <div class="box box-info">
                                                    <div class="box-header with-border">
                                                        <h3 class="box-title">Liste Consultants confirmés disponibles (choisir 1)</h3>

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
                                                                             if (lienCV.equals(""))
                                                                                                            lienCV = u.getLienCVDefaut();

                                                                    %> 
                                                                    <tr>


                                                                        <td><%=u.getNom()%></td>
                                                                        <td><%=u.getPrenom()%></td>
                                                                        <td><%=lienCV%></td>
                                                                         <td><input type="checkbox" name="listCS" value="<%=u.getId()%>"></td>

                                                                    </tr> <% }
                                                                    } if (listeUJ.size() > 0) {
                                                                    %>
                                                                <div class="box box-info">
                                                                    <div class="box-header with-border">
                                                                        <h3 class="box-title">Liste Consultants Junior disponibles (choisir 1)</h3>

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
                                                                                             if (lienCV.equals(""))
                                                                                                            lienCV = u.getLienCVDefaut();

                                                                                    %> 
                                                                                    <tr>


                                                                                        <td><%=u.getNom()%></td>
                                                                                        <td><%=u.getPrenom()%></td>
                                                                                        <td><%=lienCV%></td>
                                                                                         <td><input type="checkbox" name="listCJ" value="<%=u.getId()%>"></td>

                                                                                    </tr> <% }
                                                                                    }  if (listeUS.size() > 0) {
                                                                                    %>
                                                                                <div class="box box-info">
                                                                                    <div class="box-header with-border">
                                                                                        <h3 class="box-title">Liste Consultants confirmés disponibles (choisir 1)</h3>

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
                                                                                                          <th>Votre choix</th>
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
                                                                                                            if (lienCV.equals(""))
                                                                                                            lienCV = u.getLienCVDefaut();

                                                                                                    %> 
                                                                                                    <tr>


                                                                                                        <td><%=u.getNom()%></td>
                                                                                                        <td><%=u.getPrenom()%></td>
                                                                                                        <td><a href="<%=lienCV%>">Lien CV</a></td>
                                                                                                         <td><input type="checkbox" name="listCC" value="<%=u.getId()%>"></td>

                                                                                                    </tr> <% }
                                                                                                        }
}
                                                                                                    %>


                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>
                                                                                        <!-- /.table-responsive -->
                                                                                    </div>



                                                                                    <!-- /.box-footer -->
                                                                                </div>

                                                     
                                                                                </select>
                                                                        </div>

                                                                     

                                                                        <input type ="hidden" name="action" value="choixConsultants">
                                          <input type ="hidden" name="idDev" value="<%=d.getId()%>">
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

