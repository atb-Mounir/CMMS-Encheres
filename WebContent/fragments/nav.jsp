<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-white mb-1">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/Accueil">
			<img src="./img/LogoSMCM2.png">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- Si l'utilisateur est connect? -->

		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav ml-auto">
				<c:if test="${etatConnexion!=null}">
					<a class="nav-item nav-link"
						href="${pageContext.request.contextPath}/Accueil">Encheres <span
						class="sr-only">(current)</span></a>
					<a class="nav-item nav-link"
						href="${pageContext.request.contextPath}/NouvelleVente">Vendre
						un article</a>
					<a class="nav-item nav-link"
						href="${pageContext.request.contextPath}/VisualiserProfil">Mon
						profil</a>



					<c:if test="${cookieUtilisateurPseudo!=null}">
						<div class="dropdown">
							<button class="btn btn-outline-info dropdown-toggle"
								type="button" id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Se
								deconnecter</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="nav-item nav-link dropdown-item"
									href="${pageContext.request.contextPath}/Deconnexion">Deconnexion</a>
								<a class="nav-item nav-link dropdown-item"
									href="${pageContext.request.contextPath}/DeconnexionDefinitive">Deconnexion
									definitive</a>
							</div>
						</div>
					</c:if>

					<c:if test="${cookieUtilisateurPseudo==null}">
						<a class="nav-item nav-link"
							href="${pageContext.request.contextPath}/Deconnexion">Deconnexion</a>
					</c:if>

				</c:if>
				<!-- Si l'utilisateur n'est pas connect? -->
				<c:if test="${etatConnexion==null}">
					<div id="menuInscription">
						<a class="nav-item nav-link"
							href="${pageContext.request.contextPath}/Connexion">S'inscrire
							- Se connecter</a>
					</div>
				</c:if>
			</div>


		</div>
	</nav>
</header>