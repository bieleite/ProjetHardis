/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;

import Entites.Agence;
import Entites.Client;
import Entites.Communication;
import Entites.Devis;
import Entites.Entreprise;
import Entites.Facture;
import Entites.Interlocuteur;
import Entites.Notification;
import Entites.Offre_Profil_Util_CV;
import Entites.Service;
import Entites.ServiceStandard;
import Entites.UtilisateurHardis;
import Entites.testFTP;
import Entites.testPDF;
import Session.ClientSessionLocal;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 */

/**
 *
 * @author 6170361
 */
@WebServlet(name = "servClient", urlPatterns = {"/servClient"})
public class servClient extends HttpServlet {

    @EJB
    private ClientSessionLocal clientSession;

    Client clientT = null;

    String jspClient = null;
    HttpSession sess = null;

    protected Client connexion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //         
        //  testFTP test = new testFTP();
        //  test.downloadFTP();

        sess = request.getSession(true);
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");

        Client c = null;
        String message = "";
        String messageErreur = "";

        if (mdp.isEmpty() || email.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

            c = clientSession.authentificationClient(email, mdp);

            if (c == null) {
                messageErreur = "Erreur, identifiants erronnés";
                jspClient = "/Internaute/login.jsp";
            } else {
                List<Notification> listeNotif = clientSession.getNotifsClient(c.getId());
                List<Devis> listeDevis = clientSession.afficherDevisClient(c.getId());
                List<Devis> listeDevisAn =  clientSession.recupContratsParAn(2019, c.getId());
                if (listeNotif == null) {
                    listeNotif = new ArrayList<>();
                }
                if (listeDevis == null) {
                    listeDevis = new ArrayList<>();
                }
                jspClient = "/Client/tabBord.jsp";

                sess.setAttribute("listeNotif", listeNotif);
                sess.setAttribute("listeDevis", listeDevis);
                sess.setAttribute("listeDevisAn", listeDevisAn);
                sess.setAttribute("client", c);

                clientT = c;

            }
        }
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);
        return c;
    }

    protected void majInfos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Notification> listeNotif = clientSession.getNotifsClient(clientT.getId());
        List<Devis> listeDevis = clientSession.afficherDevisClient(clientT.getId());
        if (listeNotif == null) {
            listeNotif = new ArrayList<>();
        }
        if (listeDevis == null) {
            listeDevis = new ArrayList<>();
        }
        jspClient = "/Client/tabBord.jsp";

        sess.setAttribute("listeNotif", listeNotif);
        sess.setAttribute("listeDevis", listeDevis);
        sess.setAttribute("client", clientT);

    }

    protected void creation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sess = request.getSession(true);
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String mdpC = request.getParameter("mdpC");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cp = request.getParameter("cp");
        String questS = request.getParameter("qs");
        String repS = request.getParameter("rs");

        boolean cpo = false;

        String message = "";
        String messageErreur = "";

        if (mdp.isEmpty() || email.isEmpty() || mdpC.isEmpty() || nom.isEmpty() || prenom.isEmpty() || cp.isEmpty() || questS.isEmpty() || repS.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

            try {
                int codepos = (Integer.parseInt(cp));
                cpo = true;
            } catch (NumberFormatException ex) {
                messageErreur = "Erreur, le code postal doit être une valuer numérique";
            }
            if (cpo) {
                Client c = clientSession.rechercheCliParLogin(email);
                if (c == null) {
                    if (mdp.equals(mdpC)) {
                        Client cli = clientSession.creerClient(nom, prenom, email, mdp, questS, repS, cp);
                        jspClient = "/Internaute/entreprise.jsp";
                        clientT = cli;
                    } else {
                        messageErreur = "Erreur, les mots de passe sont différents";
                        jspClient = "/Internaute/signup.jsp";
                    }
                } else {
                    messageErreur = "Erreur, email existant";
                    jspClient = "/Internaute/signup.jsp";
                }
            }
        }
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);

    }

    protected void creationInter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String fonction = request.getParameter("fonction");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String tel = request.getParameter("tel");

        String message = "";
        String messageErreur = "";

        if (tel.isEmpty() || email.isEmpty() || nom.isEmpty() || prenom.isEmpty() || fonction.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

            clientSession.creerInter(nom, prenom, email, fonction, tel, clientT.getEntreprise().getId());

        }

        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);

    }

    protected void creationDevis(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sess = request.getSession(true);
        String libre = request.getParameter("libre");
        String date = request.getParameter("date");
        String idS = request.getParameter("idS");

        String message = "";
        String messageErreur = "";

        if (libre.isEmpty() || date.isEmpty() || idS.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

            if (java.sql.Date.valueOf(date).before(new java.util.Date())) {
                messageErreur = "Erreur, la date doit être supérieure à aujourd'hui";
            } else {
                Service s = clientSession.rechercheServiceParId(Long.valueOf(idS));
                Date d = java.sql.Date.valueOf(date);
                clientSession.demandeDevis(new Date(), d, libre, Long.valueOf(idS), s.getTypeService().toString(), clientT.getId());
                List<Notification> listeNotif = clientSession.getNotifsClient(clientT.getId());
                List<Devis> listeDevis = clientSession.afficherDevisClient(clientT.getId());
                if (listeNotif == null) {
                    listeNotif = new ArrayList<>();
                }
                if (listeDevis == null) {
                    listeDevis = new ArrayList<>();
                }
                jspClient = "/Client/tabBord.jsp";

                sess.setAttribute("listeNotif", listeNotif);
                sess.setAttribute("listeDevis", listeDevis);
                sess.setAttribute("client", clientT);
            }

        }
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);

    }

    protected void creationEntreprise(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sess = request.getSession(true);

        String nomE = request.getParameter("nomE");
        String siret = request.getParameter("siret");
        String ville = request.getParameter("ville");
        String cp = request.getParameter("cp");
        String nrRue = request.getParameter("nrRue");
        String nomRue = request.getParameter("nomRue");

        String message = "";
        boolean nrRu = false;
        boolean cpo = false;

        String messageErreur = "";

        if (nomE.isEmpty() || siret.isEmpty() || ville.isEmpty() || nrRue.isEmpty() || nomRue.isEmpty() || cp.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {
            try {
                int r = (Integer.parseInt(siret));
                nrRu = true;
            } catch (NumberFormatException ex) {
                messageErreur = "Erreur, le numéro rue doit être une valuer numérique";
            }

            try {
                int codepos = (Integer.parseInt(cp));
                cpo = true;
            } catch (NumberFormatException ex) {
                messageErreur = "Erreur, le numéro rue doit être une valuer numérique";
            }

            if (cpo && nrRu) {

                Entreprise e = clientSession.rechercheEntrepriseParSiret(siret);

                if (e == null) {
                    Entreprise entreprise = clientSession.creerEntreprise(nomE, siret, Integer.valueOf(nrRue), nomRue, ville, cp);
                    clientSession.majEntreprise(clientT.getId(), entreprise.getId());
                    clientSession.creerNotif(clientT.getId(), "Veuillez certifier votre entreprise");
                    jspClient = "/Internaute/login.jsp";
                } else {
                    messageErreur = "Erreur, siret existant, veuillez réesayer ou contactez l'admin";
                    jspClient = "/Internaute/entreprise.jsp";
                }

            }
        }
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        sess = request.getSession(true);

        String message = "";

        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher Rd;

        String act = request.getParameter("action");

        if (act.equals("retour")) {

            jspClient = "/Client/tabBord.jsp";

        }

        if (act.equals("connexion")) {

            Client c = connexion(request, response);

        }
        if (act.equals("deconnexion")) {
            Client c = (Client) sess.getAttribute("client");
            clientSession.deconnexion(c.getId());
            sess.setAttribute("client", null);
            jspClient = "/PageAccueil.jsp";
        }
        if (act.equals("creation")) {

            creation(request, response);
        }
        if (act.equals("lierE")) {

            String mdp = request.getParameter("mdp");
            String codeC = request.getParameter("codeC");

            Entreprise e = clientSession.rechercheEntrepriseParCodeEtMdp(codeC, mdp);

            if (e == null) {

            } else {

                clientSession.majEntreprise(clientT.getId(), e.getId());
                clientSession.certifierClient(clientT.getId());

                Agence ag = e.getAgence();
                clientSession.majAgenceCli(ag.getId(),clientT.getId() );
                jspClient = "/Internaute/login.jsp";
            }
        }

        if (act.equals("creationE")) {
            creationEntreprise(request, response);
        }

        if (act.equals("appelDevis")) {
            jspClient = "/Client/demandeDevis.jsp";
            List<Service> listeS = clientSession.recupServices();
            if (listeS == null) {
                listeS = new ArrayList<>();
            }
            sess.setAttribute("listeS", listeS);
        }

        if (act.equals("creerDevis")) {
            creationDevis(request, response);

        }
        if (act.equals("afficheDevis")) {

            String idD = request.getParameter("idDev");

            String lienDevis = clientSession.rechercheDocDevis(Long.valueOf(idD));

            String mess = request.getParameter("message");
            if (mess != null) {
                clientSession.creerComm(mess, Long.valueOf(idD));
            }

            if (idD != "") {
                long id = Long.valueOf(idD);
                Devis d = clientSession.recupDevis(id);

                List<Communication> listeC = clientSession.rechercheCommDev(Long.valueOf(idD));
                if (listeC == null) {
                    listeC = new ArrayList<>();
                }
                 
                List<UtilisateurHardis> listeConsu = clientSession.rechercheCParDevis(id);
                jspClient = "/Client/afficheDevis.jsp";

                request.setAttribute("lienD", lienDevis);
                 request.setAttribute("listeConsu", listeConsu);
                sess.setAttribute("devis", d);
                sess.setAttribute("listMessage", listeC);

            }
            List<Devis> listeDevis = clientSession.afficherDevisClient(clientT.getId());
            sess.setAttribute("listeDevis", listeDevis);

        }
        if (act.equals("consulteDevis")) {

            jspClient = "/Client/consulteDevis.jsp";
            String idD = request.getParameter("idDev");
            String fact = request.getParameter("fact");
            List<String> listeLibC =  new ArrayList<String>();
            List<Float> PrixU =  new ArrayList<Float>();
            
            
            
            long id = 0;
            Devis d = null;
            if (idD != "") {
                id = Long.valueOf(idD);
                d = clientSession.recupDevis(id);
                 Service s = d.getService();
                
                ServiceStandard ss = clientSession.rechercheSS(s.getId());
                sess.setAttribute("devis", d);


                
                List<UtilisateurHardis> listeConsu = clientSession.rechercheCParDevis(id);
                
                for (UtilisateurHardis u : listeConsu)
                {
                    float prixUni = clientSession.recherchePrixOffreC(u, ss.getOffre());
                    PrixU.add(prixUni);
                    String lib = clientSession.rechercheLibConsultOffre(u, ss.getOffre());
                    listeLibC.add(lib);
                    
                }
                
                request.setAttribute("listeConsu", listeConsu);
                request.setAttribute("PrixU", PrixU);
                  request.setAttribute("servS", ss);
                request.setAttribute("listeLibC", listeLibC);
            }
            
            String valide = request.getParameter("valide");
            if (valide != null && valide.equals("1")) {
                clientSession.accepterDevis(clientT.getId(), Long.valueOf(idD));
                Facture f = clientSession.creerFacture(id);
                request.setAttribute("facture", f);
                request.setAttribute("valide", "1");
            }

            if (d.getStatut().toString().equals("Valide") && fact != null) {
                request.setAttribute("valide", "1");
                List<Facture> f = clientSession.rechercheFactParDevis(Long.valueOf(idD));
                request.setAttribute("facture", f.get(0));

            }

            List<Devis> listeDevis = clientSession.afficherDevisClient(clientT.getId());
            sess.setAttribute("listeDevis", listeDevis);

        } else if (act.equals("payer")) {
            String idD = request.getParameter("idDev");
            String idF = request.getParameter("idF");
            if (idD != "" && idF != "") {
                long id = Long.valueOf(idD);
                Devis d = clientSession.recupDevis(id);

                clientSession.payerFacture(Long.valueOf(idF));
                jspClient = "/Client/tabBord.jsp";
                List<Devis> listeDevis = clientSession.afficherDevisClient(clientT.getId());
                sess.setAttribute("listeDevis", listeDevis);

            }
        } 
         else if (act.equals("choixConsultantsDef")) {
              String idD = request.getParameter("idDev");     
                if (idD != "") {
                    boolean b = true;
                 long id = Long.valueOf(idD);
                 Devis d = clientSession.recupDevis(id);
                 Date dateInter = d.getDateIntervSouhaitee();
                 
                 Service s = d.getService();
                
                ServiceStandard ss = clientSession.rechercheSS(s.getId());
                
                 List<Offre_Profil_Util_CV> listeO = clientSession.rechercheOPUCParU(clientT.getId(), d.getService().getOffre().getId());

                //rechercheCDisponibles(String typeC, Date date, long idS, String typeS, long idCli)
                List<UtilisateurHardis> listeCCDispo = clientSession.rechercheCDisponibles("Confirme", dateInter, s.getId(), "Standard", clientT.getId());
                List<UtilisateurHardis> listeCSDispo = clientSession.rechercheCDisponibles("Senior", dateInter, s.getId(), "Standard", clientT.getId());
                List<UtilisateurHardis> listeCJDispo = clientSession.rechercheCDisponibles("Junior", dateInter, s.getId(), "Standard", clientT.getId());

                float nbJ = ss.getNbreJoursConsultantJ();
                float nbS = ss.getNbreJoursConsultantS();
                float nbC = ss.getNbreJoursConsultantC();
                
                
                String[] listeC = new String[1];
                String[] listeJ = new String[1];
                String[] listeS = new String[1];
                
                
                if (nbJ!=0 && listeCJDispo.size()!=0){
                   listeJ[0]=listeCJDispo.get(0).getId().toString();
                   clientSession.choixConsultants(Long.valueOf(idD), null, listeJ, null);
                }
                if (nbS!=0 && listeCSDispo.size()!=0)
                {
                   listeS[0]=listeCSDispo.get(0).getId().toString();
                   clientSession.choixConsultants(Long.valueOf(idD), null, null, listeS);
                }
      
                if (nbC!=0 && listeCCDispo.size()==0)
                {
                     listeC[0]=listeCCDispo.get(0).getId().toString();
                   clientSession.choixConsultants(Long.valueOf(idD), listeC, null, null);
                }
                
                }
                
         }
        
        else if (act.equals("choixConsultants")) {
            String idD = request.getParameter("idDev");
             jspClient = "/Client/choixConsultants.jsp";
            if (idD != "") {
                 long id = Long.valueOf(idD);
                 Devis d = clientSession.recupDevis(id);
                 Date dateInter = d.getDateIntervSouhaitee();
                 
                
                 
                   
                
                String date = request.getParameter("date"); 
                
                if (date!=null && date!="")
                {
                    dateInter = java.sql.Date.valueOf(date);

                }
                boolean b  = true;
              

               
                Service s = d.getService();
                
                ServiceStandard ss = clientSession.rechercheSS(s.getId());

                sess.setAttribute("devis", d);
                
                 String[] listeC = request.getParameterValues("listCC");
                  String[] listeJ = request.getParameterValues("listCJ");
                  String[] listeS = request.getParameterValues("listCS");
             boolean b1 = true;
                  if (listeC!=null ||listeJ!=null   ||listeS!=null)
                  {
                       clientSession.choixConsultants(Long.valueOf(idD), listeC, listeJ, listeS);
                       float nbJ = ss.getNbreJoursConsultantJ();
                float nbS = ss.getNbreJoursConsultantS();
                float nbC = ss.getNbreJoursConsultantC();
                if (listeC!=null  && listeC.length==0 &&  nbC>0) b1 = false;
                if (listeJ!=null && listeJ.length==0 &&  nbJ>0) b1 = false;
                if (listeS!=null &&listeS.length==0 &&  nbS>0) b1 = false;
                
                      if (b1) {
                        request.setAttribute("valide", "ok");
                      }
                  }

               

                List<Offre_Profil_Util_CV> listeO = clientSession.rechercheOPUCParU(clientT.getId(), d.getService().getOffre().getId());

                //rechercheCDisponibles(String typeC, Date date, long idS, String typeS, long idCli)
                List<UtilisateurHardis> listeCCDispo = clientSession.rechercheCDisponibles("Confirme", dateInter, s.getId(), "Standard", clientT.getId());
                List<UtilisateurHardis> listeCSDispo = clientSession.rechercheCDisponibles("Senior", dateInter, s.getId(), "Standard", clientT.getId());
                List<UtilisateurHardis> listeCJDispo = clientSession.rechercheCDisponibles("Junior", dateInter, s.getId(), "Standard", clientT.getId());

                float nbJ = ss.getNbreJoursConsultantJ();
                float nbS = ss.getNbreJoursConsultantS();
                float nbC = ss.getNbreJoursConsultantC();
                
                if (nbJ!=0 && listeCJDispo.size()==0)
                    b = false;
                if (nbS!=0 && listeCSDispo.size()==0)
                    b = false;
                if (nbC!=0 && listeCCDispo.size()==0)
                    b = false;
                
                if (!b) request.setAttribute("valide", "n");
                
                request.setAttribute("serviceS", s);
                request.setAttribute("listeC", listeCCDispo);
                request.setAttribute("listeS", listeCSDispo);
                request.setAttribute("listeJ", listeCJDispo);
                request.setAttribute("offreP", listeO);
               

            }
        }
        
       
        
        else if (act.equals("majEnt")) {
            boolean b = false;
            String code = request.getParameter("code");
            String mdp = request.getParameter("mdp");

            if (code == null || mdp == null || code.isEmpty() || mdp.isEmpty()) {

            } else {
                b = clientSession.lierEntreprise(clientT.getId(), code, mdp);
            }

            if (b) {

            }
            List<Interlocuteur> liste = null;
            if (clientT.getEntreprise() != null) {
                liste = clientSession.recupInter(clientT.getEntreprise().getId());
            }

            request.setAttribute("listeInt", liste);
            jspClient = "/Client/majEntreprise.jsp";

        } else if (act.equals("ajoutInter")) {

            creationInter(request, response);
            jspClient = "/Client/majEntreprise.jsp";
            List<Interlocuteur> liste = clientSession.recupInter(clientT.getEntreprise().getId());
            request.setAttribute("listeInt", liste);
    
          }
        
       
        
        else if (act.equals("forgot")) {

            jspClient = "/Client/forgot.jsp";
            String repS = request.getParameter("repS");
            String mdp = request.getParameter("mdp");
            String mdp1 = request.getParameter("mdp1");

            if (mdp == null || mdp.isEmpty()) {

            } else {
                if (mdp.equals(mdp1)) {
                    clientSession.modifMDP(clientT.getId(), mdp);
                    jspClient = "/Internaute/login.jsp";

                }
            }

            if (repS == null || repS.isEmpty()) {

            } else {
                if (clientSession.verifRepS(clientT.getId(), repS)) {
                    request.setAttribute("valide", "valide");
                    request.setAttribute("client", clientT);
                }
            }

            String email = request.getParameter("email");
            if (email == null || email.isEmpty()) {

            } else {
                Client c = clientSession.rechercheCliParLogin(email);
                clientT = c;
                if (c != null) {
                    request.setAttribute("client", c);
                } else {
                    request.setAttribute("messageErreur", "Erreur, email non trouvé");
                }
            }

        }

        Rd = getServletContext().getRequestDispatcher(jspClient);
        Rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
