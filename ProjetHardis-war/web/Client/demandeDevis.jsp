<%-- 
    Document   : tabBord
    Created on : 13 mars 2019, 15:07:11
    Author     : 6170361
--%>

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

 <jsp:useBean id="listeS" scope="request" class = "java.util.List"> </jsp:useBean>
  <jsp:useBean id="listeSS" scope="request" class = "java.util.List"> </jsp:useBean>
     
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
            
            <%  if(client.getEntreprise()==null){  %>
            <h3 class="box-title">Vous ne pouvez pas créer de devis car vous n'êtes pas rattaché à une entreprise</h3>
            <p>Vous pouvez mettre à jour cela en cliquant <a href="servClient?action=majEnt">ici</a></p>
            <%} else {%>
    <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Demander devis</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
             <% List<Service> listeOS = listeS; 
                         List<ServiceStandard> listeSSt = listeSS;
                     %>
            <form role="form">
              <div class="box-body">
                       <div class="form-group">
                  <label>Offre & service</label>
                  <select class="form-control" name="idS">
                      <optgroup label="Services standard">
                      <%
                      for (ServiceStandard s : listeSSt)
                      {%>
                      
                      <option  value="<%=s.getId()%>"><%out.print(s.getOffre().getLibelle()+" : "+s.getNomService());%></option>
                     
                   <%}%>
                     </optgroup>
                     
                     <optgroup label="Services non standard">
                      <%
                      for (Service s : listeOS)
                      {
%>         
                      <option  value="<%=s.getId()%>"><%out.print(s.getOffre().getLibelle()+" : "+s.getNomService());%></option>
                        
                     
                   <%}%>
                     </optgroup>
                     
                     
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
   
   
   <%}%>
   
   
   

  


  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>

</div>
<!-- ./wrapper -->



  <%@include  file = "script.jsp" %>
  
 
</body>
</html>

