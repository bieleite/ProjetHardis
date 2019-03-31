<%-- 
    Document   : PageAccueil
    Created on : 12 mars 2019, 16:15:33
    Author     : 6170361
--%>

<%@page import="Entites.ServiceStandard"%>
<%@page import="Entites.Service"%>
<%@page import="java.util.List"%>
<%@page import="Entites.Offre"%>
<%@page import="Entites.Offre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html class="no-js" lang="fr" dir="ltr" prefix="og: http://ogp.me/ns# article: http://ogp.me/ns/article# book: http://ogp.me/ns/book# profile: http://ogp.me/ns/profile# video: http://ogp.me/ns/video# product: http://ogp.me/ns/product# content: http://purl.org/rss/1.0/modules/content/ dc: http://purl.org/dc/terms/ foaf: http://xmlns.com/foaf/0.1/ rdfs: http://www.w3.org/2000/01/rdf-schema# sioc: http://rdfs.org/sioc/ns# sioct: http://rdfs.org/sioc/types# skos: http://www.w3.org/2004/02/skos/core# xsd: http://www.w3.org/2001/XMLSchema#" xmlns:fb="http://www.facebook.com/2008/fbml" xmlns:og="http://opengraphprotocol.org/schema/"> <!--<![endif]-->
    <head profile="http://www.w3.org/1999/xhtml/vocab">
        <jsp:useBean id="listeO" scope="session" class = "java.util.List"> </jsp:useBean>
        <jsp:useBean id="listeSS" scope="session" class = "java.util.List"> </jsp:useBean>
        <jsp:useBean id="listeSN" scope="session" class = "java.util.List"> </jsp:useBean>
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <link rel="shortcut icon" href="https://www.hardis-group.com/sites/all/themes/hardis/favicon.ico" type="image/vnd.microsoft.icon" />
            <link href="https://www.hardis-group.com/" rel="alternate" hreflang="fr" />
            <link href="https://www.hardis-group.com/en" rel="alternate" hreflang="en" />
            <meta name="description" content="Hardis Group, SSII, ESN Grenoble, Lyon, Paris, Nantes, Lille, Bordeaux" />

            <meta itemprop="name" content="Page d&#039;accueil" />
            <title>Hardis Group, SSII, ESN Grenoble, Lyon, Paris, Nantes, Lille, Bordeaux</title>
            <link rel="stylesheet" href="https://www.hardis-group.com/sites/all/themes/hardis/bootstrap/css/bootstrap.min.css" type="text/css" media="all" />
            <link rel="stylesheet" href="style3.css" type="text/css" media="all" />

            <link rel="stylesheet" type="text/css" href="https://cloud.typography.com/6730332/646866/css/fonts.css" />
            <link type="text/css" rel="stylesheet" href="https://www.hardis-group.com/sites/default/files/css/css_lQaZfjVpwP_oGNqdtWCSpJT1EMqXdMiU84ekLLxQnc4.css" media="all" />
            <link type="text/css" rel="stylesheet" href="https://www.hardis-group.com/sites/default/files/css/css_-s5yoERteuGjJDrLxz9Kggu6sThyaBYUz8x_6tN7g4I.css" media="all" />
            <link type="text/css" rel="stylesheet" href="https://www.hardis-group.com/sites/default/files/css/css_qepgyu6QkN9zYkafiZkEknEamt9cVISmDbaw_LfrHqc.css" media="all" />
            <style>
                #sliding-popup.sliding-popup-bottom,#sliding-popup.sliding-popup-bottom .eu-cookie-withdraw-banner,.eu-cookie-withdraw-tab{background:#006699;}#sliding-popup.sliding-popup-bottom.eu-cookie-withdraw-wrapper{background:transparent}#sliding-popup .popup-content #popup-text h1,#sliding-popup .popup-content #popup-text h2,#sliding-popup .popup-content #popup-text h3,#sliding-popup .popup-content #popup-text p,.eu-cookie-compliance-more-button,.eu-cookie-compliance-secondary-button,.eu-cookie-withdraw-tab{color:#ffffff !important;}.eu-cookie-withdraw-tab{border-color:#ffffff;}
            </style>
            <link type="text/css" rel="stylesheet" href="https://www.hardis-group.com/sites/default/files/css/css_tsQCAaPInHxSwwdCNcCugkJA1ggpAoTf41hnZ9DRIRs.css" media="all" />
            <!-- HTML5 element support for IE6-8 -->
            <!--[if lt IE 9]>
              <script type="text/javascript" src="/sites/all/themes/hardis/js/vendor/html5shiv.js"></script>
              <script type="text/javascript" src="/sites/all/themes/hardis/js/vendor/respond.min.js"></script>
            <![endif]-->

            <script src="https://www.hardis-group.com/sites/all/modules/contrib/jquery_update/replace/jquery/1.9/jquery.min.js?v=1.9.1"></script>
            <script src="https://www.hardis-group.com/misc/jquery.once.js?v=1.2"></script>
            <script src="https://www.hardis-group.com/misc/drupal.js?po00he"></script>
            <script src="https://www.hardis-group.com/sites/all/modules/contrib/eu_cookie_compliance/js/jquery.cookie-1.4.1.min.js?v=1.4.1"></script>
            <script src="https://www.hardis-group.com/sites/all/modules/contrib/admin_menu/admin_devel/admin_devel.js?po00he"></script>
            <script src="https://www.hardis-group.com/sites/default/files/languages/fr_FaiFeVRWUEVGyfZBwY33pq9wzTsechy2-Je1J_kKd9s.js?po00he"></script>
            <script src="https://www.hardis-group.com/sites/all/modules/contrib/google_analytics/googleanalytics.js?po00he"></script>
            <script>(function (i, s, o, g, r, a, m) {
                    i["GoogleAnalyticsObject"] = r;
                    i[r] = i[r] || function () {
                        (i[r].q = i[r].q || []).push(arguments)
                    }, i[r].l = 1 * new Date();
                    a = s.createElement(o), m = s.getElementsByTagName(o)[0];
                    a.async = 1;
                    a.src = g;
                    m.parentNode.insertBefore(a, m)
                })(window, document, "script", "//www.google-analytics.com/analytics.js", "ga");
                ga("create", "UA-8808399-3", {"cookieDomain": "auto"});
                ga("set", "anonymizeIp", true);
                ga("send", "pageview");</script>
            <script src="https://www.hardis-group.com/sites/all/modules/custom/nodule/nodule_slideshow/js/nodule_slideshow.js?po00he"></script>
            <script src="https://www.hardis-group.com/sites/all/themes/hardis/js/vendor/jquery.equalheight.min.js?po00he"></script>
            <script src="https://www.hardis-group.com/sites/all/themes/hardis/js/vendor/jquery.touchSwipe.min.js?po00he"></script>
            <script src="https://www.hardis-group.com/sites/all/themes/hardis/js/main.js?po00he"></script>
            <script>jQuery.extend(Drupal.settings, {"basePath": "\/", "pathPrefix": "", "ajaxPageState": {"theme": "hardis", "theme_token": "pWWsP8Z9g75ZUEb9Vg_xU0_qCKBs6HtXlj1rviTFNV0", "js": {"0": 1, "1": 1, "sites\/all\/themes\/bootstrap\/js\/bootstrap.js": 1, "sites\/all\/modules\/contrib\/eu_cookie_compliance\/js\/eu_cookie_compliance.js": 1, "sites\/all\/modules\/contrib\/jquery_update\/replace\/jquery\/1.9\/jquery.min.js": 1, "misc\/jquery.once.js": 1, "misc\/drupal.js": 1, "sites\/all\/modules\/contrib\/eu_cookie_compliance\/js\/jquery.cookie-1.4.1.min.js": 1, "sites\/all\/modules\/contrib\/admin_menu\/admin_devel\/admin_devel.js": 1, "public:\/\/languages\/fr_FaiFeVRWUEVGyfZBwY33pq9wzTsechy2-Je1J_kKd9s.js": 1, "sites\/all\/modules\/contrib\/google_analytics\/googleanalytics.js": 1, "2": 1, "sites\/all\/modules\/custom\/nodule\/nodule_slideshow\/js\/nodule_slideshow.js": 1, "sites\/all\/themes\/hardis\/js\/vendor\/jquery.equalheight.min.js": 1, "sites\/all\/themes\/hardis\/js\/vendor\/jquery.touchSwipe.min.js": 1, "sites\/all\/themes\/hardis\/js\/main.js": 1}, "css": {"modules\/system\/system.base.css": 1, "sites\/all\/modules\/contrib\/date\/date_api\/date.css": 1, "sites\/all\/modules\/contrib\/date\/date_popup\/themes\/datepicker.1.7.css": 1, "modules\/field\/theme\/field.css": 1, "modules\/node\/node.css": 1, "sites\/all\/modules\/contrib\/views\/css\/views.css": 1, "sites\/all\/modules\/contrib\/ckeditor\/css\/ckeditor.css": 1, "sites\/all\/modules\/contrib\/ctools\/css\/ctools.css": 1, "modules\/locale\/locale.css": 1, "sites\/all\/modules\/contrib\/eu_cookie_compliance\/css\/eu_cookie_compliance.css": 1, "0": 1, "sites\/all\/themes\/hardis\/css\/styles.css": 1}}, "nodule_slideshow": {"delay": "4000"}, "eu_cookie_compliance": {"popup_enabled": 1, "popup_agreed_enabled": 0, "popup_hide_agreed": 0, "popup_clicking_confirmation": 1, "popup_scrolling_confirmation": 0, "popup_html_info": "\u003Cdiv\u003E\n  \u003Cdiv class =\u0022popup-content info\u0022\u003E\n    \u003Cdiv id=\u0022popup-text\u0022\u003E\n      \u003C!DOCTYPE html PUBLIC \u0022-\/\/W3C\/\/DTD HTML 4.0 Transitional\/\/EN\u0022 \u0022http:\/\/www.w3.org\/TR\/REC-html40\/loose.dtd\u0022\u003E\n\u003Chtml\u003E\u003Cbody\u003E\u003Cp\u003EEn poursuivant votre navigation sur ce site, vous acceptez l\u0027utilisation de Cookies permettant de r\u0026eacute;aliser des statistiques de visites et de publier des liens sur les r\u0026eacute;seaux sociaux.\u003C\/p\u003E\u003C\/body\u003E\u003C\/html\u003E\n              \u003Cbutton type=\u0022button\u0022 class=\u0022find-more-button eu-cookie-compliance-more-button\u0022\u003EEn savoir plus\u003C\/button\u003E\n          \u003C\/div\u003E\n    \u003Cdiv id=\u0022popup-buttons\u0022\u003E\n      \u003Cbutton type=\u0022button\u0022 class=\u0022agree-button eu-cookie-compliance-default-button\u0022\u003EOK\u003C\/button\u003E\n          \u003C\/div\u003E\n  \u003C\/div\u003E\n\u003C\/div\u003E", "use_mobile_message": false, "mobile_popup_html_info": "\u003Cdiv\u003E\n  \u003Cdiv class =\u0022popup-content info\u0022\u003E\n    \u003Cdiv id=\u0022popup-text\u0022\u003E\n                    \u003Cbutton type=\u0022button\u0022 class=\u0022find-more-button eu-cookie-compliance-more-button\u0022\u003EEn savoir plus\u003C\/button\u003E\n          \u003C\/div\u003E\n    \u003Cdiv id=\u0022popup-buttons\u0022\u003E\n      \u003Cbutton type=\u0022button\u0022 class=\u0022agree-button eu-cookie-compliance-default-button\u0022\u003EOK\u003C\/button\u003E\n          \u003C\/div\u003E\n  \u003C\/div\u003E\n\u003C\/div\u003E\n", "mobile_breakpoint": "768", "popup_html_agreed": "\u003Cdiv\u003E\n  \u003Cdiv class =\u0022popup-content agreed\u0022\u003E\n    \u003Cdiv id=\u0022popup-text\u0022\u003E\n      \u003C!DOCTYPE html PUBLIC \u0022-\/\/W3C\/\/DTD HTML 4.0 Transitional\/\/EN\u0022 \u0022http:\/\/www.w3.org\/TR\/REC-html40\/loose.dtd\u0022\u003E\n\u003Chtml\u003E\u003Cbody\u003E\u003Ch2\u003EThank you for accepting cookies\u003C\/h2\u003E\u003Cp\u003EYou can now hide this message or find out more about cookies.\u003C\/p\u003E\u003C\/body\u003E\u003C\/html\u003E\n    \u003C\/div\u003E\n    \u003Cdiv id=\u0022popup-buttons\u0022\u003E\n      \u003Cbutton type=\u0022button\u0022 class=\u0022hide-popup-button eu-cookie-compliance-hide-button\u0022\u003EMasquer\u003C\/button\u003E\n              \u003Cbutton type=\u0022button\u0022 class=\u0022find-more-button eu-cookie-compliance-more-button-thank-you\u0022 \u003EPlus d\u0027infos\u003C\/button\u003E\n          \u003C\/div\u003E\n  \u003C\/div\u003E\n\u003C\/div\u003E", "popup_use_bare_css": false, "popup_height": "auto", "popup_width": "100%", "popup_delay": 1000, "popup_link": "\/respect-des-donnees-personnelles", "popup_link_new_window": 1, "popup_position": null, "popup_language": "fr", "store_consent": true, "better_support_for_screen_readers": 0, "reload_page": 0, "domain": "www.hardis-group.com", "popup_eu_only_js": 0, "cookie_lifetime": "100", "cookie_session": false, "disagree_do_not_show_popup": 0, "method": "default", "whitelisted_cookies": "", "withdraw_markup": "\u003Cbutton type=\u0022button\u0022 class=\u0022eu-cookie-withdraw-tab\u0022\u003E\u003C\/button\u003E\n\u003Cdiv class=\u0022eu-cookie-withdraw-banner\u0022\u003E\n  \u003Cdiv class=\u0022popup-content info\u0022\u003E\n    \u003Cdiv id=\u0022popup-text\u0022\u003E\n          \u003C\/div\u003E\n    \u003Cdiv id=\u0022popup-buttons\u0022\u003E\n      \u003Cbutton type=\u0022button\u0022 class=\u0022eu-cookie-withdraw-button\u0022\u003E\u003C\/button\u003E\n    \u003C\/div\u003E\n  \u003C\/div\u003E\n\u003C\/div\u003E\n", "withdraw_enabled": false}, "googleanalytics": {"trackOutbound": 1, "trackMailto": 1, "trackDownload": 1, "trackDownloadExtensions": "7z|aac|arc|arj|asf|asx|avi|bin|csv|doc(x|m)?|dot(x|m)?|exe|flv|gif|gz|gzip|hqx|jar|jpe?g|js|mp(2|3|4|e?g)|mov(ie)?|msi|msp|pdf|phps|png|ppt(x|m)?|pot(x|m)?|pps(x|m)?|ppam|sld(x|m)?|thmx|qtm?|ra(m|r)?|sea|sit|tar|tgz|torrent|txt|wav|wma|wmv|wpd|xls(x|m|b)?|xlt(x|m)|xlam|xml|z|zip"}, "urlIsAjaxTrusted": {"\/": true}, "bootstrap": {"anchorsFix": 0, "anchorsSmoothScrolling": 1, "formHasError": 1, "popoverEnabled": 1, "popoverOptions": {"animation": 1, "html": 1, "placement": "right", "selector": "", "trigger": "click", "triggerAutoclose": 1, "title": "", "content": "", "delay": 0, "container": "body"}, "tooltipEnabled": 1, "tooltipOptions": {"animation": 1, "html": 1, "placement": "auto left", "selector": "", "trigger": "hover focus", "delay": 0, "container": "body"}}});</script>
        </head>
        <body class="html front not-logged-in no-sidebars page-home i18n-fr" >
            <div id="skip-link">
                <a href="#menu" tabindex="1" accesskey="0" class="element-invisible element-focusable">Aller à la navigation</a>
                <a href="#main-content" tabindex="2" accesskey="1" class="element-invisible element-focusable">Aller au contenu</a>
            </div>
            <a name="top"></a>
            <header id="navbar" role="banner" class="navbar navbar-fixed-top navbar-default">
                <div class="line1 hidden-xs">
                    <div class="container">

                        <div class=" col-md-8 bg-social">
                        </div>

                        <div class="col-md-2 text-right">
                            <ul class="nav nav-pills glyphicon-user" ><li><a href="servInternaute?action=connexion">Espace client</a> </li></ul>

                        </div>

                        <div class="col-md-2 text-right">
                            <ul class="nav nav-pills glyphicon-user" ><li><a href="servAdmin">Espace Hardis</a> </li></ul>

                        </div>



                        <!--</form>--></div>          </div>
            </div>
            <div class="line2">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-sm-2">
                            <div class="navbar-header">
                                <div class="row">
                                    <a class="logo navbar-btn pull-left col-xs-5 col-sm-12" href="http://localhost:8080/ProjetHardis-war/servInternaute" title="Accueil">
                                        <img src="https://www.hardis-group.com/sites/all/themes/hardis/logo.png" alt="Accueil" class="img-responsive" />
                                    </a>

                                    <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
                                    <div class="col-xs-6">
                                        <button type="button" class="navbar-toggle pull-right" data-toggle="collapse" data-target=".navbar-collapse">
                                            <span class="sr-only">Toggle navigation</span>
                                            <span class="icon-bar"></span>
                                            <span class="icon-bar"></span>
                                            <span class="icon-bar"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-10">
                            <div class="navbar-collapse collapse">
                                <nav class="yamm" role="navigation">
                                    <div class="close-wrapper"><button type="button" class="close" data-toggle="collapse" data-target=".navbar-collapse"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></div>
                                    <ul class="menu nav navbar-nav">
                                        <li class="first expanded dropdown yamm-fw level_1 big-menu most mlid-665">
                                            <a href="servInternaute?action=afficheOffres" title="" data-target="#" class="dropdown-toggle nolink" data-toggle="dropdown">Nos offres </a>
                                        </li>





                                        <li class="expanded dropdown yamm-fw level_1 no-big-menu mlid-666"><a title="" data-target="#" class="dropdown-toggle nolink" data-toggle="dropdown">Votre secteur </a>
                                        </li>
                                        <li class="expanded dropdown yamm-fw level_1 no-big-menu mlid-664"><a title="" data-target="#" class="dropdown-toggle nolink" data-toggle="dropdown">Innovation et transformation </a>
                                        </li>
                                        <li class="expanded dropdown yamm-fw level_1 big-menu most mlid-667"><a href ="" title="" data-target="#" class="dropdown-toggle nolink" data-toggle="dropdown">Société</a>
                                        </li>


                                        <li class="first expanded dropdown yamm-fw level_1 big-menu most mlid-665">
                                            <a href ="servInternaute?action=afficheFormContact" title="" data-target="#" class="dropdown-toggle nolink" data-toggle="dropdown">Nous contacter</a>
                                    </ul>                          
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <div class="main-container">
            <div class="white">
                <div class="container">


                <% String val = (String) request.getAttribute("valeur");
                    List<Offre> listeOf = listeO;
                    List<ServiceStandard> listeSSt = listeSS;
                    List<Service> listeSNS = listeSN;

                   if (val != null && val != "" && val.equals("offres")) { %>
                <section class="section-padding" id="service-area">
                    <div class="container">
                        <div class="row">
                            <% for (Offre o : listeOf) {%>
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="feature-box wow fadeInUp" data-wow-delay="0.2s">

                                    <h4><%=o.getLibelle()%></h4>

                                    <a href="servInternaute?action=afficheService&idO=<%=o.getId()%>" class="read-more">En savoir +</a>
                                </div>
                            </div>
                            <%}%>



                        </div>
                    </div>
                </section>

                <% } else if (val != null && val != "" && val.equals("services")) {%>

                <section class="section-padding" id="service-area">
                    <div class="container">
                        <h3><%=listeSNS.get(0).getOffre().getLibelle()%></h3>
                        <div class="row">
                            <% for (Service o : listeSNS) {%>
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="feature-box wow fadeInUp" data-wow-delay="0.2s">

                                    <h4><%=o.getNomService()%></h4>

                                    <p><%=o.getDescriptionService()%> </p>
                                </div>
                            </div>
                            <%}%>

                            <% for (ServiceStandard o : listeSSt) {%>
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="feature-box wow fadeInUp" data-wow-delay="0.2s">

                                    <h4><%=o.getNomService()%></h4>

                                    <p><%=o.getDescriptionService()%> </p>
                                </div>
                            </div>
                            <%}%>



                        </div>
                    </div>
                </section>

                <%} else if (val != null && val != "" && val.equals("contacter")) {%>

                <form role="form">

                    <div>        <div class="form-item webform-component webform-component-textfield webform-component--company form-group form-item form-item-submitted-company form-type-textfield form-group">
                            <label class="control-label" for="edit-submitted-company">Société <span class="form-required" title="Ce champ est obligatoire.">*</span></label>
                            <input required="required" class="form-control form-text required" type="text" id="edit-submitted-company" name="entreprise" value="" size="60" maxlength="128" /></div>

                        <div class="form-item webform-component webform-component-textfield webform-component--last-name form-group form-item form-item-submitted-last-name form-type-textfield form-group"> 
                            <label class="control-label" for="edit-submitted-last-name">Nom <span class="form-required" title="Ce champ est obligatoire.">*</span></label>
                            <input required="required" class="form-control form-text required" type="text" id="edit-submitted-last-name" name="nom" value="" size="60" maxlength="128" /></div>

                        <div class="form-item webform-component webform-component-textfield webform-component--first-name form-group form-item form-item-submitted-first-name form-type-textfield form-group"> 
                            <label class="control-label" for="edit-submitted-first-name">Prénom <span class="form-required" title="Ce champ est obligatoire.">*</span></label>
                            <input class="form-control form-text" type="text" id="edit-submitted-title" name="prenom" value="" size="60" maxlength="128" /></div>

                        <div class="form-item webform-component webform-component-email webform-component--email form-group form-item form-item-submitted-email form-type-webform-email form-group"> 
                            <label class="control-label" for="edit-submitted-email">Email <span class="form-required" title="Ce champ est obligatoire.">*</span></label>
                            <input required="required" class="email form-control form-text form-email required" type="email" id="edit-submitted-email" name="email" size="60" /></div>

                        <div class="form-item webform-component webform-component-textfield webform-component--phone form-group form-item form-item-submitted-phone form-type-textfield form-group"> 
                            <label class="control-label" for="edit-submitted-phone">Téléphone</label>
                            <input class="form-control form-text" type="text" id="edit-submitted-phone" name="tel" value="" size="60" maxlength="128" /></div>

                        <div class="form-item webform-component webform-component-select webform-component--country-code form-group form-item form-item-submitted-country-code form-type-select form-group"> 
                            <label class="control-label" for="edit-submitted-description">Description de votre besoin</label>
                            <div class="form-textarea-wrapper"><textarea class="form-control form-textarea" id="edit-submitted-description" name="besoin" cols="60" rows="3"></textarea></div></div>



                        <div class="form-item webform-component webform-component-checkboxes webform-component--mention-legale form-group form-item form-item-submitted-mention-legale form-type-checkboxes form-group">

                            <div id="edit-submitted-mention-legale" class="form-checkboxes">
                                <div class="form-item form-item-submitted-mention-legale-1 form-type-checkbox checkbox"> <label class="control-label" for="edit-submitted-mention-legale-1"><input required="required" type="checkbox" id="edit-submitted-mention-legale-1" name="submitted[mention_legale][1]" value="1" class="form-checkbox" />J'accepte que mes données à caractère  personnel soient collectées et traitées selon les conditions décrites à la page <a href="/respect-des-donnees-personnelles">"respect des données personnelles"</a></label>
                                </div></div> <label class="control-label element-invisible" for="edit-submitted-mention-legale">Mention légale <span class="form-required" title="Ce champ est obligatoire.">*</span></label>
                        </div><br>
                        <input type="hidden" name="action" value ="contacter" />

                        <button type="submit" >Envoyer</button></div>
                </form>


                <%} else {
                %>

                <header role="banner" id="page-header">
                    <div id="slideshow" class="">
                        <div id="carousel" class="carousel slide" data-ride="carousel" data-interval="">

                            <!-- Carousel Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carousel" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel" data-slide-to="1" class=""></li>
                                <li data-target="#carousel" data-slide-to="2" class=""></li>
                                <li data-target="#carousel" data-slide-to="3" class=""></li>
                                <li data-target="#carousel" data-slide-to="4" class=""></li>
                            </ol>

                            <!-- Carousel items -->
                            <div class="carousel-inner">
                                <div class="item active">
                                    <a href="node/3631" target="_self">
                                        <img class="" alt="" title="Hardis Group certifié Great Place to Work" typeof="foaf:Image" src="https://www.hardis-group.com/sites/default/files/styles/slideshow_carousel/public/slideshow/cover-hardis-group-certifie-great-place-to-work.jpg?itok=P3c7y-GG" />          </a>
                                </div>
                                <div class="item">
                                    <a href="node/3616" target="_self">
                                        <img class="" alt="" title="Reflex WMS est référencé dans le Gartner Europe" typeof="foaf:Image" src="https://www.hardis-group.com/sites/default/files/styles/slideshow_carousel/public/slideshow/ban-gartnermq-slideshow-fr.jpg?itok=mBmbuzZi" />          </a>
                                </div>
                                <div class="item">
                                    <a href="https://www.customer-platform.com" target="_self">
                                        <img class="" alt="" title="Intégrateur Salesforce Hardis Group" typeof="foaf:Image" src="https://www.hardis-group.com/sites/default/files/styles/slideshow_carousel/public/slideshow/cover_integrateur-salesforce-hardis-group.jpg?itok=DqBVBRrv" />          </a>
                                </div>
                                <div class="item">
                                    <a href="node/2537" target="_self">
                                        <img class="" alt="" title="EYESEE, drone inventory by Hardis Group" typeof="foaf:Image" src="https://www.hardis-group.com/sites/default/files/styles/slideshow_carousel/public/slideshow/cover_eyesse_2017_fr.png?itok=f_wjtZ45" />          </a>
                                </div>
                                <div class="item">
                                    <a href="node/85" target="_self">
                                        <img class="" alt="" title="Accélerer la transformation de nos clients" typeof="foaf:Image" src="https://www.hardis-group.com/sites/default/files/styles/slideshow_carousel/public/slideshow/cover_accompagnement_transfo_fr.png?itok=jPuVIYXd" />          </a>
                                </div>
                            </div>

                            <!-- Carousel nav -->
                            <a class="left carousel-control" href="#carousel" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                    <div class="clearfix hidden-xs"></div>      </header> <!-- /#page-header -->

                <div class="row">


                    <section class="col-sm-12">
                        <a id="main-content"></a>
                        <section class="col-sm-12 twitter">

                        </section>


                    </section>

                    <section id="block-block-6" class="block block-block col-sm-4 digitalisation clearfix">


                        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">
                        <html><body><div class="bg">
                                    <h2>Digitalisation des Services</h2>

                                    <p>Accompagner les hommes et transformer votre SI pour insuffler une culture services et acc&eacute;l&eacute;rer la digitalisation des services.<br><a href="/innovation/creer-de-la-valeur-business-par-la-digitalisation-des-services">en savoir plus</a></p>
                                </div></body></html>

                    </section> <!-- /.block -->

                    <section id="block-block-8" class="block block-block col-sm-4 vivre-hardis clearfix">


                        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">
                        <html><body><div class="bg">
                                    <h2 style="background-image: url('https://www.hardis-group.com/sites/default/files/hardis_block_image/hardis_group_rejoignez_nous_0.png')">Vivre&nbsp;Hardis</h2>

                                    <p>Une entreprise &agrave; taille humaine, une ambiance conviviale et une culture de proximit&eacute; qui vise &agrave; faire grandir ses collaborateurs.<br><a href="/nous-rejoindre/postuler/espace-emploi-ssii-esn-societe-services-informatiques-grenoble-paris-lyon-lille-nantes">en savoir plus</a></p>
                                </div></body></html>

                    </section> <!-- /.block -->
                    <section id="block-block-9" class="block block-block col-sm-4 supply-chain clearfix">


                        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">
                        <html><body><div class="bg">
                                    <h2 style="background-image: url('https://www.hardis-group.com/sites/default/files/hardis_block_image/reflex_logistics_solutions.png')">Performance Supply Chain</h2>

                                    <p>Mettre en place une logistique efficace, fiable et agile au changement pour r&eacute;pondre aux enjeux de l'omnicanal.<br><a href="/nos-activites/reflex-suite-logicielle-modulaire-pour-la-gestion-logistique">en savoir plus</a></p>
                                </div></body></html>

                    </section> <!-- /.block -->


                </div>
                <%}%>

            </div>
        </div>

    </div>

    <footer class="footer">
        <div class="line1">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3 hidden-xs">
                        <a href="http://localhost:8080/ProjetHardis-war/servInternaute"><img src="https://www.hardis-group.com/sites/all/themes/hardis/img/logo_footer.jpg" class="img-responsive"></a>
                    </div>
                    <div class="col-sm-9 col-xs-12">
                        <form id="newsletter-form" method="GET" name="" action="/newsletter" role="form" class="form-inline">
                            <div class="form-group">
                                <div class="col-sm-8 hidden-xs"><p>Restez informé de l'actualité d'Hardis Group, inscrivez-vous à notre newsletter</p></div>
                                <div class="input-group col-sm-4 col-xs-12">
                                    <input type="email" class="form-control" id="email" name="email" placeholder="Votre email">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn btn-default">OK</button>
                                    </span>
                                </div>
                            </div>
                        </form><section id="block-menu-menu-footer" class="block block-menu clearfix">


                            <ul class="nav col-sm-3 col-xs-4" role="tablist"><li class="first leaf level_1"><a href="/actualites">Actualités</a></li>
                                <li class="leaf level_1"><a href="https://extranet.hardis.fr" title="">Extranet clients</a></li>
                                <li class="leaf level_1"><a href="http://www.move2digital.fr" title="">Blogs</a></li>
                                <li class="leaf level_1"><a href="/contact" title="">Contact</a></li>
                            </ul><ul class="nav col-sm-3 col-xs-4" role="tablist"><li class="leaf level_1"><a href="/espace-presse">Espace presse</a></li>
                                <li class="leaf level_1"><a href="/temoignages-clients">Témoignages clients</a></li>
                                <li class="leaf level_1"><a href="/avis-dexperts">Avis d&#039;experts</a></li>
                                <li class="leaf level_1"><a href="https://www.youtube.com/user/GroupeHardis/feed" title="">Vidéos</a></li>
                            </ul><ul class="nav col-sm-3 col-xs-4" role="tablist"><li class="leaf level_1"><a href="/nos-references">Nos références</a></li>
                                <li class="last leaf level_1"><a href="/nous-rejoindre/postuler/espace-emploi-ssii-esn-societe-services-informatiques-grenoble-paris-lyon-lille-nantes" title="">Nos offres d&#039;emploi</a></li>
                            </ul>
                        </section> <!-- /.block -->

                        <div class="visible-xs">
                            <select name="langs" onchange="javascript:document.location = this.value;"><option value="/" selected="selected">Français / FR</option><option value="/en">English / EN</option></select>          </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="line2">
            <div class="container">
                <div class="row">
                    <section id="block-menu-menu-footer-bottom" class="block block-menu clearfix">


                        <ul class="nav nav-pills" role="tablist"><li class="first leaf level_1"><a href="/mentions-legales">Mentions légales</a></li>
                            <li class="leaf level_1"><a href="/respect-des-donnees-personnelles">Respect des données personnelles</a></li>
                            <li class="leaf level_1"><a href="/plan-du-site" title="">Plan du Site</a></li>
                            <li class="last leaf level_1"><a href="/contact" title="">Contact</a></li>
                        </ul>
                    </section> <!-- /.block -->
                </div>
            </div>
        </div>
    </footer>    <script>function euCookieComplianceLoadScripts() {}</script>
    <script>var eu_cookie_compliance_cookie_name = "";</script>
    <script src="https://www.hardis-group.com/sites/all/themes/bootstrap/js/bootstrap.js?po00he"></script>
    <script src="https://www.hardis-group.com/sites/all/modules/contrib/eu_cookie_compliance/js/eu_cookie_compliance.js?po00he"></script>
    <script type="text/javascript" src="https://www.hardis-group.com//sites/all/themes/hardis/js/vendor/placeholders.jquery.min.js"></script>
    <script type="text/javascript" src="https://www.hardis-group.com//sites/all/themes/hardis/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://www.hardis-group.com//sites/all/themes/hardis/js/vendor/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-566152932c45c389" async="async"></script>
</body>
</html>
