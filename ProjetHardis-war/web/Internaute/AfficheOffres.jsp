<%-- 
    Document   : AfficheOffres
    Created on : 12 mars 2019, 15:40:05
    Author     : 6170361
--%>

<%@page import="Entites.Service"%>
<%@page import="Entites.Offre"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="listeO" scope="request" class = "java.util.List"> </jsp:useBean>
        <title>JSP Page</title>
    </head>
    <body>
        <% List<Offre> liste = listeO;
        %>
        <h1>Liste des offres</h1>
        <% for (Offre o : liste)
        {
        %> <div class="col-md-2"> <p>  <%out.println(o.getLibelle());
            List<Service> listeS = o.getServices();
            out.println("<br>");
            for (Service s : listeS)
            {
                 out.println("Nom service : " + s.getNomService());
                out.println("<br>");
                out.println("Service : " + s.getTypeService().toString());
                out.println("<br>");
                 out.println("Description service : " + s.getDescriptionService());
                out.println("<br>");
                if (s.getTypeService().toString().equals("Standard"))
                {
                    out.println("Prix : " + s.getCoutService());
                    out.println("<br>");
                }
                else if (s.getTypeService().toString().equals("Non_Standard"))
                {
                    out.println("Prix : Sur devis");
                    out.println("<br>");
                }
                
                
            } %> </div> </p> <%
        }
%>
    </body>
</html>
