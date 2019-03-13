/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Action;
import Entites.Agence;
import Entites.Client;
import Entites.Devis;
import Entites.DevisNonStandard;
import Entites.Document;
import Entites.Entreprise;
import Entites.Facturation;
import Entites.Facture;
import Entites.HistoriqueDevis;
import Entites.Offre_Profil_Util_CV;
import Entites.ProfilMetier;
import Entites.Service;
import Entites.ServiceStandard;
import Entites.Statut;
import Entites.TypeService;
import Entites.TypeUtilisateur;
import Entites.UtilisateurHardis;
import Facades.AgenceFacadeLocal;
import Facades.ClientFacadeLocal;
import Facades.CommunicationFacadeLocal;
import Facades.DevisFacadeLocal;
import Facades.DevisNonStandardFacadeLocal;
import Facades.DocumentFacadeLocal;
import Facades.EntrepriseFacadeLocal;
import Facades.FactureFacadeLocal;
import Facades.HistoriqueDevisFacadeLocal;
import Facades.HistoriqueEtatsFacadeLocal;
import Facades.HistoriqueTraitementFacadeLocal;
import Facades.LogsFacadeLocal;
import Facades.Offre_Profil_Util_CVFacadeLocal;
import Facades.ServiceFacadeLocal;
import Facades.ServiceStandardFacadeLocal;
import Facades.UtilisateurFacadeLocal;
import Facades.UtilisateurHardisFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gabrielleite
 */
@Stateless
public class ClientSession implements ClientSessionLocal {

    @EJB
    private ServiceStandardFacadeLocal serviceStandardFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private FactureFacadeLocal factureFacade;

    @EJB
    private CommunicationFacadeLocal communicationFacade;

    @EJB
    private LogsFacadeLocal logsFacade;

    @EJB
    private AgenceFacadeLocal agenceFacade;

    @EJB
    private UtilisateurHardisFacadeLocal utilisateurHardisFacade;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    @EJB
    private Offre_Profil_Util_CVFacadeLocal offre_Profil_Util_CVFacade;

    @EJB
    private HistoriqueTraitementFacadeLocal historiqueTraitementFacade;

    @EJB
    private HistoriqueEtatsFacadeLocal historiqueEtatsFacade;

    @EJB
    private DocumentFacadeLocal documentFacade;

    @EJB
    private HistoriqueDevisFacadeLocal historiqueDevisFacade;

    @EJB
    private DevisNonStandardFacadeLocal devisNonStandardFacade;

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private ClientFacadeLocal clientFacade;
    
    
    
    
    
    
    @Override
    public Client authentificationClient(String login, String mdp) {  
       
         return clientFacade.authentificationClient(login,mdp);   
    }

    @Override
    public void majEntreprise(long idCli, long idEnt) {
        Client cli = clientFacade.rechercheClient(idCli);
        Entreprise ent = entrepriseFacade.rechercheEntrepriseParId(idEnt);
        clientFacade.majEntrepriseClient(cli, ent);
    }

    @Override
    public void demandeDevis(Date dateDevis, Date dateInterv, String infosC, long  idServ, String typeServ, long idCli) {
         Service serv = null;
         ServiceStandard servSt = null;
        if (typeServ.equals("Standard"))
        {
              serv = serviceFacade.rechercheServiceParId(idServ);
        }
        else if (typeServ.equals("Non_Standard"))
        {
              servSt = serviceStandardFacade.rechercheServiceSParId(idServ);
        }
        Client cli = clientFacade.rechercheClient(idCli);
             
        if(cli.getEntreprise()!=null){
         
        if (servSt!=null) //service standard
        {      
           Devis d =  devisFacade.creerDevis(TypeService.Standard, serv, dateDevis, dateInterv, Facturation.Auto, 0, "", infosC, Statut.Rep_en_Cours, cli, cli.getEntreprise().getAgence());
                    
           historiqueEtatsFacade.creerHistoriqueEtats(new Date(), Statut.Rep_en_Cours, d);
          
           List<Document> listeDocs = new ArrayList<>();
          
           listeDocs.add(documentFacade.creerDocument("conditions contrat",serv.getConditionsContract(), null));
           
           HistoriqueDevis hd = historiqueDevisFacade.creerHistoriqueDevis(d, null, null, 1, null, listeDocs);
         
           logsFacade.creerLog(Action.Create, new Date(), "création devis avec id : "+d.getId(), cli);
        }
        else if (serv!=null)
        {
           Devis d  =  devisFacade.creerDevis(TypeService.Non_Standard, serv, dateDevis, dateInterv, Facturation.Manuel, 0, "", infosC, Statut.Rep_en_Cours, cli, cli.getEntreprise().getAgence());
           
           historiqueEtatsFacade.creerHistoriqueEtats(new Date(), Statut.Rep_en_Cours, d);
           
           List<UtilisateurHardis>  listeU =  utilisateurHardisFacade.rechercheUtilisateurHParAgence(cli.getEntreprise().getAgence());
          
           UtilisateurHardis ref = null;
           
           for (UtilisateurHardis u : listeU )
           {
               List<Offre_Profil_Util_CV> o = offre_Profil_Util_CVFacade.rechercheOPUCParUtilisateur(u);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   ProfilMetier pm = compteur.getProfil();
                   if (pm.getNiveauHabilitation().toString().equals("Referent"))
                       ref = u;
               }
           }       
            historiqueTraitementFacade.creerHistoriqueTraitement(new Date(), null, TypeUtilisateur.r, d, null, ref, null);
            logsFacade.creerLog(Action.Create, new Date(), "création devis avec id : "+d.getId(), cli);
        }
    }
    }
    
    
    @Override
    public void accepterDevis(long idCli, long idDevis) {
        Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
        devisFacade.accepterRefuserDevis(d, "a");
        historiqueEtatsFacade.creerHistoriqueEtats(new Date(), Statut.Valide, d);
        logsFacade.creerLog(Action.Update, new Date(), "maj devis avec id : "+d.getId(), cli);
    }
   

    @Override
    public void refuserDevis(long idCli, long idDevis) {
       Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
          devisFacade.accepterRefuserDevis(d, "r");
         historiqueEtatsFacade.creerHistoriqueEtats(new Date(), Statut.Refuse, d);
          logsFacade.creerLog(Action.Update, new Date(), "maj devis avec id : "+d.getId(), cli);
    }

    
    
    

    @Override
    public void poserQuestion(long idCli, long idDevis, String text) {
          Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
       communicationFacade.creerCommunication(new Date(), text, d, null, "Q", 0);
       logsFacade.creerLog(Action.Create, new Date(), "creation communication pour devis id : "+d.getId(), cli);  
    }

    @Override
    public void payerFacture(long idF) {
        Facture f = factureFacade.rechercheFactParId(idF);
        factureFacade.payerFacture(f);
        logsFacade.creerLog(Action.Create, new Date(), "creation facture pour devis id : "+f.getDevis().getId(), f.getDevis().getClient()); 
    }

    @Override
    public void modifDateInterv(Date date, long idCli, long idDevis) {
           Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
        devisFacade.modifDateInterv(d, date);
       logsFacade.creerLog(Action.Update, new Date(), "maj date interv pour devis id : "+d.getId(), cli);      
    }

    @Override
    public List<Devis> afficherDevisClient(long idCli) {
        Client cli = clientFacade.rechercheClient(idCli);
       return  devisFacade.afficherDevisClient(cli);
    }

    @Override
    public List<Devis> afficherDevisStatut(long idCli, String statut) {
        Client cli = clientFacade.rechercheClient(idCli);
        Statut st = null;
        switch (statut) {
            case "Incomplet" : {
                st = Statut.Incomplet;
                break;
            }
            case "Réponse en cours" : {
                 st = Statut.Rep_en_Cours;
                break;
            }
             case "Envoyé" : {
                 st = Statut.Envoye;
                break;
            }
              case "Validé" : {
                 st = Statut.Valide;
                break;
            }
               case "Refusé" : {
                 st = Statut.Refuse;
                break;
            }
                case "En négociation" : {
                 st = Statut.En_nego;
                break;
            }
                  case "Acompte réglé" : {
                 st = Statut.Acompte_regle;
                break;
            }
              case "Prestation terminée" : {
                 st = Statut.Presta_terminee;
                break;
            }
        }
       return devisFacade.afficherDevisStatut(cli, st);
    }

    @Override
    public Client rechercheCliParLogin(String log) {
        return clientFacade.rechercheClientParLogin(log);
    }

    //creerClient(String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP, Entreprise entreprise, Agence agence, String cp
    @Override
    public void creerClient(String nom, String prenom, String login, String mdp, String cp, String qs, String rs) {
        clientFacade.creerClient(nom, prenom, login, mdp, qs, rs, 1, new Date(), null, null, cp);
    }
    
    


    
    
    
    
    
    
    
    
    
    
    

    
    
   
}
