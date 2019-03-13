<%-- 
    Document   : FormLog
    Created on : 13 mars 2019, 10:32:50
    Author     : 6170361
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Internaute/login.css" rel="stylesheet" type="text/css">

<!DOCTYPE HTML>
<html lang="zxx">

<head>
	<title></title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>

</head>

<body>
	<div class="main-bg">
		<!-- title -->
		<h1></h1>
		<!-- //title -->
		<div class="sub-main-w3">
			<div class="image-style">

			</div>
			<!-- vertical tabs -->
			<div class="vertical-tab">
				<div id="section1" class="section-w3ls">
					<input type="radio" name="sections" id="option1" checked>
					<label for="option1" class="icon-left-w3pvt"><span class="fa fa-user-circle" aria-hidden="true"></span>Connexion</label>
					<article>
						<form action="servClient" method="get">                                                  
							<h3 class="legend">Connexion</h3>
							<div class="input">
								<span class="fa fa-envelope-o" aria-hidden="true"></span>
								<input type="email" placeholder="Email" name="email" required />
							</div>
							<div class="input">
								<span class="fa fa-key" aria-hidden="true"></span>
								<input type="password" placeholder="Mot de passe" name="mdp" required />
							</div>
                                                          <input type ="hidden" name="action" value="connexion">
							<button type="submit" class="btn submit">Connexion</button>
					
						</form>
					</article>
				</div>
				<div id="section2" class="section-w3ls">
					<input type="radio" name="sections" id="option2">
					<label for="option2" class="icon-left-w3pvt"><span class="fa fa-pencil-square" aria-hidden="true"></span>Créer compte</label>
					<article>
						<form action="servClient" method="get">
							<h3 class="legend">Créer compte</h3>
							<div class="input">
								<span class="fa fa-user-o" aria-hidden="true"></span>
								<input type="text" placeholder="Nom" name="nom" required />
							</div>
                                                        <div class="input">
								<span class="fa fa-user-o" aria-hidden="true"></span>
								<input type="text" placeholder="Prénom" name="prenom" required />
							</div>
                                                        <div class="input">
								<span class="fa fa-user-o" aria-hidden="true"></span>
								<input type="text" placeholder="Email" name="email" required />
							</div>
							
                                                         <div class="input">
								<span class="fa fa-key" aria-hidden="true"></span>
								<input type="text" placeholder="Code Postal" name="cp" required />
							</div>
                                                        <div class="input">
								<span class="fa fa-key" aria-hidden="true"></span>
								<input type="password" placeholder="Mot de passe" name="mdp" required />
							</div>
                                                        
							<div class="input">
								<span class="fa fa-key" aria-hidden="true"></span>
								<input type="password" placeholder="Confirmer mot de passe" name="mdpC" required />
                                                                
							</div>
                                                         <div class="input">
								<span class="fa fa-key" aria-hidden="true"></span>
								<input type="text" placeholder="Question secrète" name="qs" required />
							</div>
                                                        
                                                         <div class="input">
								<span class="fa fa-key" aria-hidden="true"></span>
								<input type="text" placeholder="Réponse secrète" name="rs" required />
							</div>
                                                        
                                                         <input type ="hidden" name="action" value="creation">
							<button type="submit" class="btn submit">S'enregistrer</button>
						</form>
					</article>
				</div>
                                 
				<div id="section3" class="section-w3ls">
					<input type="radio" name="sections" id="option3">
					<label for="option3" class="icon-left-w3pvt"><span class="fa fa-lock" aria-hidden="true"></span>Mot de passe oublié?</label>
					<article>
						<form action="#" method="post">
							<h3 class="legend last"></h3>
							
							<div class="input">
								<span class="fa fa-envelope-o" aria-hidden="true"></span>
								<input type="email" placeholder="Email" name="email" required />
							</div>
							<button type="submit" class="btn submit last-btn">Reset</button>
						</form>
					</article>
				</div>
			</div>
			<!-- //vertical tabs -->
			<div class="clear"></div>
		</div>
		
		
	</div>

</body>

</html>
