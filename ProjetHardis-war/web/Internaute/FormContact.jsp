<%-- 
    Document   : FormContact
    Created on : 12 mars 2019, 17:12:44
    Author     : 6170361
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Internaute/formContact.css" rel="stylesheet" type="text/css">


<!DOCTYPE html>
<html lang="en">
<head>
	<title>Contact</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body>

   <% String attribut = (String) request.getAttribute("message");
                            String attributErr = (String) request.getAttribute("messageErreur");
                            if (attribut == null || attribut == "") {
                                if (attributErr != null) {
                                    out.println(attributErr);
                                } %>
	<div class="container-contact100">
		<div class="wrap-contact100">
                    
                  
                                
                                <form class="contact100-form validate-form" method ="get" action="servInternaute">
				<span class="contact100-form-title">
					Contactez-nous 
				</span>

				<label class="label-input100" for="first-name">Nom et prénom *</label>
				<div class="wrap-input100 rs1-wrap-input100 validate-input">
					<input id="first-name" class="input100" type="text" name="nom" placeholder="Nom">
			
				</div>
                               
				<div class="wrap-input100 rs2-wrap-input100 validate-input">
					<input class="input100" type="text" name="prenom" placeholder="Prenom">
				
				</div>

				<label class="label-input100" for="email">Email *</label>
				<div class="wrap-input100 validate-input">
					<input id="email" class="input100" type="text" name="email" placeholder="Eg. example@email.com">
		
				</div>

				<label class="label-input100" for="phone">Portable</label>
				<div class="wrap-input100">
					<input id="phone" class="input100" type="text" name="tel">
		
				</div>

				<label class="label-input100" for="message">Message *</label>
				<div class="wrap-input100 validate-input">
					<textarea id="message" class="input100" name="message" placeholder="Message"></textarea>
				
				</div>
                                
                                <div  style="padding : 10px">
                        <!--        <input type="checkbox" id="scales" name="rgpd" value="rgpd"> -->
                                <label for="rgpd">blabla RGPD</label>
                              </div>
                                
                                
                                <input type ="hidden" name="action" value="contacter">
                                 
				<div class="container-contact100-form-btn">
					<button class="contact100-form-btn" >
						Envoyer
					</button>
				</div>
			</form>
                                 <% } else {
                            out.println(attribut); %>
                        <%}%>

			<div class="contact100-more flex-col-c-m" style="background-image: url('images/bg-01.jpg');">
				<div class="flex-w size1 p-b-47">
					<div class="txt1 p-r-25">
						<span class="lnr lnr-map-marker"></span>
					</div>

					<div class="flex-col size2">
						<span class="txt1 p-b-20">
							Addresse
						</span>

						<span class="txt2">
							
						</span>
					</div>
				</div>

				<div class="dis-flex size1 p-b-47">
					<div class="txt1 p-r-25">
						<span class="lnr lnr-phone-handset"></span>
					</div>

					<div class="flex-col size2">
						<span class="txt1 p-b-20">
						Contact
						</span>

						<span class="txt3">
							
						</span>
					</div>
				</div>

				<div class="dis-flex size1 p-b-47">
					<div class="txt1 p-r-25">
						<span class="lnr lnr-envelope"></span>
					</div>

					<div class="flex-col size2">
						<span class="txt1 p-b-20">
							Email
						</span>

						<span class="txt3">
							contact@example.com
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<script>
		$(".selection-2").select2({
			minimumResultsForSearch: 20,
			dropdownParent: $('#dropDownSelect1')
		});
	</script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>

        

</body>
</html>

