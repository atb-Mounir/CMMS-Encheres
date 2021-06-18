<%@ include file="../fragments/head.jsp"%>
<%@page import="org.encheres.message.LecteurMessage"%>
<body>
	<div class="container-fluid">
		<%@ include file="../fragments/nav.jsp"%>
		
		<c:if test="${!empty listeCodesErreur}">
				<div class="alert alert-danger" role="alert">
					<strong>Erreur!</strong>
					<ul>
						<c:forEach var="code" items="${listeCodesErreur}">
							<li>${LecteurMessage.getMessageErreur(code)}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			
		<div id="contenu"
			class="row align-items-center justify-content-center"
			style="margin-top: 10%">


			<form action="${pageContext.request.contextPath}/Connexion" method="post">
				<div class="form-group row">
					<label for="pseudo" class="col-sm-5 col-form-label">Pseudo</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="pseudo"
							name="pseudo">
					</div>
				</div>
				<div class="form-group row">
					<label for="motDePasse" class="col-sm-5 col-form-label">Mot
						de passe</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" id="motDePasse"
							name="motDePasse">
					</div>
				</div>

				<div class="form-group row">

					<div id="bouton" style="display: inline-block;">
						<button type="submit" class="btn btn-primary">Connexion</button>
					</div>

					<div style="display: inline-block; margin-left: 30px">
						<input type="checkbox" id="seSouvenirDeMoi" name="seSouvenirDeMoi" checked> 
						<label for="seSouvenirDeMoi">Se souvenir de moi</label><br /> 
							<a href="#" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo" >Mot de passe oublie</a>
					</div>
				</div>
				<div>
	
					<button type="button"
						class="btn btn-outline-primary btn-lg col-sm-10"
						style="margin-top: 50px">
						<a href="${pageContext.request.contextPath}/MonProfil">Creer un compte</a>
					</button>
				
				</div>
			</form>

		</div>


		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Mot de passe oublie</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form method="post" action="#">
					<div class="modal-body">
						
							<div class="form-group">
								<label for="recipient-name" class="col-form-label">Adresse email :</label>
								<input type="email" class="form-control" name="email" id="recipient-name" placeholder="Entrez votre adresse email" required>
							</div>
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fermer</button>
						<button type="submit" class="btn btn-primary">Envoyer</button>
					</div>
					</form>
				</div>
			</div>
		</div>

		<%@ include file="../fragments/footer.jsp"%>
		<%@ include file="../fragments/script.jsp"%>
	</div>
</body>
</html>