<%-- 
    Document   : tabBord
    Created on : 13 mars 2019, 15:07:11
    Author     : 6170361
--%>

<%@page import="Entites.Interlocuteur"%>
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
        
 <jsp:useBean id="listeInt" scope="request" class = "java.util.List"> </jsp:useBean>


        <title>AdminLTE 2 | Dashboard</title>
        <%@include  file = "meta.jsp" %>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <%@include  file = "menu.jsp" %>
            <body>
                <% List<Interlocuteur> listeI = listeInt; %>
                <!-- Content Wrapper. Contains page content -->
                <div class="content-wrapper">
                    <!-- Content Header (Page header) -->
                    <section class="content-header">


                    </section>

                    <!-- Main content -->
                    <section class="content">

                        <!-- Main row -->
                        <div class="row">

                            <% if (client.getEntreprise() == null) { %>
                            <!-- left column -->
                            <div class="col-md-6">
                                <div class="box box-primary">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Mettre à jour son entreprise</h3>
                                    </div>
                                   
                                    <form role="form">
                                        <div class="box-body">

                                            <div class="form-group">
                                                <label>Code contrat : </label>
                                                <input type="text" class="form-control" name="code">
                                            </div>
                                           
                                            <div class="form-group">
                                                <label>Mot de passe : </label>
                                                <input type="password"  class="form-control" name="mdp">
                                            </div>
                                            
                                            <input type ="hidden" name="action" value="majEnt">

                                        </div>
    

                                        <div class="box-footer">
                                            <button type="submit" class="btn btn-primary">Valider</button>
                                        </div>
                                    </form>
                                </div>


                            </div>
                            <% } else {%>


                            <!-- left column -->
                            <div class="col-md-6">
                                <div class="box box-primary">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Mettre à jour information entreprise</h3>
                                    </div>
                               
                                    <form role="form">
                                        
                                        <div class="box-body">

                                            <div class="form-group">
                                                <label>Nom </label>             
                                                <input type="text" class="form-control" value="<%=client.getEntreprise().getNomEntreprise()%>" disabled >            
                                            </div>

                                            <div class="form-group">
                                                <label>Siret </label>             
                                                <input type="text" class="form-control" value="<%=client.getEntreprise().getNumeroEntreprise()%>" disabled >            
                                            </div>
                                            
                                            <div class="form-group">
                                                <label>Agence de rattachement Hardis </label>             
                                                <input type="text" class="form-control" value="<%=client.getAgence().getNomAgence()%>" disabled >            
                                            </div>  
                                            
                                            <div class="form-group">
                                                <label>Adresse </label>

                                                <div class="row">
                                                    <div  class ="col-xs-4">
                                                        <input type="text" class="form-control" placeholder="n° rue" name="nrRue" value ="<%=client.getEntreprise().getAdresseFact().getNumeroRue()%>">

                                                    </div>

                                                    <div  class ="col-xs-8">
                                                        <input type="text" class="form-control" placeholder="nom rue" name="nomRue" value ="<%=client.getEntreprise().getAdresseFact().getNomRue()%>">

                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class ="col-xs-6">
                                                        <input type="text" class="form-control" placeholder="Code Postal" name="cp" value ="<%=client.getEntreprise().getAdresseFact().getCodePostal()%>">

                                                    </div>
                                                    <div  class ="col-xs-6">
                                                        <input type="text" class="form-control" placeholder="Ville" name="ville" value ="<%=client.getEntreprise().getAdresseFact().getVille()%>">

                                                    </div>
                                                </div>
                                                
                                                <input type ="hidden" name="action" value="majEnt">

                                            </div>
                                        </div>
                                            <!-- /.box-body -->

                                            <div class="box-footer">
                                                <button type="submit" class="btn btn-primary">Valider</button>
                                            </div>
                                    </form>
                                </div>


                            </div>
                                                        
                           <div class="col-md-6">
                               
                                <div class="box box-primary">
                                    <div class="box-header with-border">
                                    <% if (listeI.size()==0) out.print("Vous n'avez pas d'interlocuteurs declarés pour cette entreprise");
                                    else {
                                        out.print("Liste interlocuteurs");
                                        for (Interlocuteur i : listeI)
                                        {
                                            out.print(i.getNomInterlocuteur());
                                        }
                                       
                                    } %>
                                    </div>
                                    
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Ajouter interlocuteur</h3>
                                    </div>
                                   
                                   
                                    <form role="form" action="servClient" method="get">
                                        <div class="box-body">

                                            <div class="form-group">
                                                <label>Nom </label>
                                                <input type="text" class="form-control" name="nom">
                                            </div>
                                           
                                             <div class="form-group">
                                                <label>Prénom </label>
                                                <input type="text" class="form-control" name="prenom">
                                            </div>
                                            
                                              <div class="form-group">
                                                <label>Fonction </label>
                                                <input type="text" class="form-control" name="fonction">
                                            </div>
                                            
                                              <div class="form-group">
                                                <label>Email </label>
                                                <input type="email" class="form-control" name="email">
                                            </div>
                                              <div class="form-group">
                                                <label>Tél </label>
                                                <input type="text" class="form-control" name="tel">
                                            </div>
                                            
                                            <input type ="hidden" name="action" value="ajoutInter">

                                        </div>
    

                                        <div class="box-footer">
                                            <button type="submit" class="btn btn-primary">Ajouter</button>
                                        </div>
                                    </form>
                                </div>


                            </div>


                        </div>
                        <% }%>
                        
                </section>




        </div>








        <!-- /.control-sidebar -->
        <!-- Add the sidebar's background. This div must be placed
             immediately after the control sidebar -->
        <div class="control-sidebar-bg"></div>

    </div>
    <!-- ./wrapper -->



    <%@include  file = "script.jsp" %>


</body>
</html>

