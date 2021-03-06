<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.lang.String"%>

<%@ include file="../fragments/head.jsp"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>





<body>

	<div class="container ">
	<%@ include file="../fragments/nav.jsp"%>
	
<h1>Liste des encheres</h1>

<h2>Filtre</h2>

<form method="post" action="${pageContext.request.contextPath}/Accueil">
	<!-- barre de recherche -->
	<nav class="navbar navbar-light bg-light">
		<input class="form-control mr-sm-2" type="search"
			placeholder="Rechercher" aria-label="rechercher" name="motsCles">
	</nav>
	<!-- Liste des cat�gories -->
	<select name="categorie">
		<option>Selectionner une categorie</option>
		<c:forEach items="${categories}" var="categorie">
			<option>${categorie.libelleCategorie}</option>
		</c:forEach>
	</select>




<!-- Bloc pour afficher le menu achat ou vente si l'utilisateur est connecté -->
<c:if test="${etatConnexion!=null}">
<!-- Bloc achats -->
<div class="form-check form-check-inline">
	<input class="form-check-input" name="achatVente" type="radio" id="achats" value="achats" checked=checked onclick="achatFunction()">
	<label class="form-check-label" for="inlineCheckbox1">Achats</label>
	
	<div class="form-check">
		<input class="form-check-input" name="encheresOuverts" type="checkbox" id="ouvert"> 
		<label class="form-check-label" for="ouvert"> encheres ouvertes</label><br /> 
		<input class="form-check-input" name="mesEncheresEnCours" type="checkbox" id="enCours"> 
		<label class="form-check-label" for="enCours"> Mes encheres en cours</label><br />
		<input class="form-check-input" name="mesEncheresRemportes" type="checkbox" id="remporte"> 
		<label class="form-check-label" for="remporte"> Mes encheres remportees</label>
	</div>
</div>

<!-- Bloc ventes -->
<div class="form-check form-check-inline">
	<input class="form-check-input" name="achatVente" type="radio" id="ventes" value="ventes" onclick="venteFunction()"> 
	<label class="form-check-label" for="inlineCheckbox2">Mes ventes</label>



	<div class="form-check">
		<input class="form-check-input" name="mesVentesEnCours" type="checkbox" id="venteEnCours"> 
		<label class="form-check-label" for="venteEnCours"> Mes ventes en cours</label><br /> 
		<input class="form-check-input" name="mesVentesNonDebutees" type="checkbox" id="venteNonDebutees"> 
		<label class="form-check-label"	for="venteNonDebutees"> Ventes non debutees</label><br /> 
		<input class="form-check-input" name="mesVentesTerminees" type="checkbox" id="venteTerminee"> 
		<label class="form-check-label"	for="venteTerminee"> Ventes terminees</label>
	</div>
</div>
</c:if>
	<div>
		<input type="submit" class="readmore" value="Rechercher">
	</div>
</form>
<!-- SECTION LISTE DES ARTICLE EN VENTES -->
		<div class="site-section site-portfolio">
			<div class="container">
				<div id="portfolio-grid" class="row no-gutter" data-aos="fade-up"
					data-aos-delay="200">
					<c:forEach items="${listeAccueil}" var="article">
					
					<div class="  item photography col-sm-6 col-md-4 col-lg-4 mb-4">
						<!--LIEN SUR IMAGE   ///CONDITION 1/// / Si connexion positive et l'utilisateur EST le vendeur MODIFIER VENTE -->
								<c:if test="${(etatConnexion!=null) && (article.utilisateur.noUtilisateur==newUtilisateur.noUtilisateur)}">
						<a
							href="<%=request.getContextPath() %>/DetailVente?no_article=${article.noArticle}"
							class="item-wrap fancybox"> 
							
								<div class="work-info">
									<h5 class="card-title" style="text-transform: uppercase;">${article.nomArticle}</h5>
									<p class="card-text">
										Prix :
										<c:choose>
											<c:when test="${article.enchere.montantEnchere != 0}">
									           ${article.enchere.montantEnchere} 
									         </c:when>

											<c:otherwise>
									           ${article.miseAPrix}
									         </c:otherwise>
										</c:choose>
										points
									</p>
									<fmt:parseDate value="${article.dateDebutEncheres}" pattern="yyyy-MM-dd" var="DateDebutParsee" type="date"/>
								<fmt:formatDate value="${DateDebutParsee}" var="newDateDebutParsee" type="date" pattern="dd-MM-yyyy" />
								<p class="card-text">Debut d'enchere : ${newDateDebutParsee} </p>
								<fmt:parseDate value="${article.dateFinEncheres}" pattern="yyyy-MM-dd" var="DateFinParsee" type="date"/>
								<fmt:formatDate value="${DateFinParsee}" var="newDateFinParsee" type="date" pattern="dd-MM-yyyy" />
								<p class="card-text">Fin de l'enchere : ${newDateFinParsee}</p>
								
								</div>
								
							<c:if test="${article.categorie.noCategorie==1}">
							<img class="img-fluid"
							src="${pageContext.request.contextPath}/img/informatique.jpg" style="height:350px">
							</c:if>
							<c:if test="${article.categorie.noCategorie==2}">
							<img class="img-fluid"
							src="${pageContext.request.contextPath}/img/ameublement.jpg" style="height:350px">
							</c:if>
							<c:if test="${article.categorie.noCategorie==3}">
							<img class="img-fluid"
							src="${pageContext.request.contextPath}/img/vetement.jpg" style="height:350px">
							</c:if>
							<c:if test="${article.categorie.noCategorie==4}">
							<img class="img-fluid"
							src="${pageContext.request.contextPath}/img/sportsEtLoisirs.jpg" style="height:350px">
							</c:if>
						</a>
						</c:if>
						
						<!--LIEN SUR IMAGE   ///CONDITION 2/// / Si connexion positive et l'utilisateur N EST PAS le vendeur VOIR ARTICLE -->
								<c:if test="${(etatConnexion!=null) && (article.utilisateur.noUtilisateur!=newUtilisateur.noUtilisateur)}">
						<a
							href="<%=request.getContextPath() %>/DetailVente?no_article=${article.noArticle}"
							class="item-wrap fancybox"> 
							
								<div class="work-info">
									<h5 class="card-title" style="text-transform: uppercase;">${article.nomArticle}</h5>
									<p class="card-text">
										Prix :
										<c:choose>
											<c:when test="${article.enchere.montantEnchere != 0}">
									           ${article.enchere.montantEnchere} 
									         </c:when>

											<c:otherwise>
									           ${article.miseAPrix}
									         </c:otherwise>
										</c:choose>
										points
									</p>
									<fmt:parseDate value="${article.dateDebutEncheres}" pattern="yyyy-MM-dd" var="DateDebutParsee" type="date"/>
								<fmt:formatDate value="${DateDebutParsee}" var="newDateDebutParsee" type="date" pattern="dd-MM-yyyy" />
								<p class="card-text">Debut d'enchere : ${newDateDebutParsee} </p>
								<fmt:parseDate value="${article.dateFinEncheres}" pattern="yyyy-MM-dd" var="DateFinParsee" type="date"/>
								<fmt:formatDate value="${DateFinParsee}" var="newDateFinParsee" type="date" pattern="dd-MM-yyyy" />
								<p class="card-text">Fin de l'enchere : ${newDateFinParsee}</p>
								
								</div>
							 <c:if test="${article.categorie.noCategorie==1}">
							<img class="img-fluid"
							src="${pageContext.request.contextPath}/img/informatique.jpg" style="height:350px">
							</c:if>
							<c:if test="${article.categorie.noCategorie==2}">
							<img class="img-fluid"
							src="${pageContext.request.contextPath}/img/ameublement.jpg" style="height:350px">
							</c:if>
							<c:if test="${article.categorie.noCategorie==3}">
							<img class="img-fluid"
							src="${pageContext.request.contextPath}/img/vetement.jpg" style="height:350px">
							</c:if>
							<c:if test="${article.categorie.noCategorie==4}">
							<img class="img-fluid"
							src="${pageContext.request.contextPath}/img/sportsEtLoisirs.jpg" style="height:350px">
							</c:if>
						</a>
						</c:if>
				<!--LIEN SUR IMAGE   //CONDITION 3/// SI LA CONNEXION EST NULL ACCES REFUSE-->
							<c:if test="${etatConnexion==null}">
								<a href="<%=request.getContextPath() %>/AccesRefuse"
									class="item-wrap fancybox">

									<div class="work-info">
										<h5 class="card-title" style="text-transform: uppercase;">${article.nomArticle}</h5>
										<p class="card-text">
											Prix :
											<c:choose>
												<c:when test="${article.enchere.montantEnchere != 0}">
									           ${article.enchere.montantEnchere} 
									         </c:when>

												<c:otherwise>
									           ${article.miseAPrix}
									         </c:otherwise>
											</c:choose>
											points
										</p>
										<fmt:parseDate value="${article.dateDebutEncheres}"
											pattern="yyyy-MM-dd" var="DateDebutParsee" type="date" />
										<fmt:formatDate value="${DateDebutParsee}"
											var="newDateDebutParsee" type="date" pattern="dd-MM-yyyy" />
										<p class="card-text">Debut d'enchere :
											${newDateDebutParsee}</p>
										<fmt:parseDate value="${article.dateFinEncheres}"
											pattern="yyyy-MM-dd" var="DateFinParsee" type="date" />
										<fmt:formatDate value="${DateFinParsee}"
											var="newDateFinParsee" type="date" pattern="dd-MM-yyyy" />
										<p class="card-text">Fin de l'enchere :
											${newDateFinParsee}</p>

									</div> <c:if test="${article.categorie.noCategorie==1}">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/img/informatique.jpg"
											style="height: 350px">
									</c:if> <c:if test="${article.categorie.noCategorie==2}">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/img/ameublement.jpg"
											style="height: 350px">
									</c:if> <c:if test="${article.categorie.noCategorie==3}">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/img/vetement.jpg"
											style="height: 350px">
									</c:if> <c:if test="${article.categorie.noCategorie==4}">
										<img class="img-fluid"
											src="${pageContext.request.contextPath}/img/sportsEtLoisirs.jpg"
											style="height: 350px">
									</c:if>
								</a>
							</c:if>
							<!-- 						AFFICHER LE VENDEUR //CONDITION 1// SI l'Utilisateur est le vendeur // "VOTRE ARTICLE" est le point jaune -->
							<jsp:useBean id="today" class="java.util.Date" />
							<fmt:setLocale value="fr_fr" />
							<fmt:parseDate var="dateFinEncheres"
								value="${article.dateFinEncheres}" pattern="yyyy-MM-dd" />
							<fmt:parseDate var="dateFinEncheres"
								value="${article.dateDebutEncheres}" pattern="yyyy-MM-dd" />

							<c:if test="${(dateFinEncheres.time gt today.time) && (dateFinEncheres.time gt today.time) && (etatConnexion!=null) && (article.utilisateur.noUtilisateur==newUtilisateur.noUtilisateur)}">
								<a href="<%=request.getContextPath() %>/NouvelleVente?no_article=${article.noArticle}"
								 class="shadow badge badge-warning w-25 h-10">Modifier</a>
								</c:if> 
								
<!-- 							AFFICHER LE VENDEUR //CONDITION 2// SI l'Utilisateur n'est PAS le vendeur // "VOIR VENDEUR" est le point bleu -->
							<c:if test="${(etatConnexion!=null)}">
								<a href="${pageContext.request.contextPath}/VisualiserVendeur?no_vendeur=${article.utilisateur.noUtilisateur}"
								 class="shadow badge badge-info w-25 h-10 bg-#008e97">${article.utilisateur.pseudo}</a>
								</c:if>
<!-- 						Bouton vendeur  si la connexion est positive: voir le vendeur sinon acces refus� -->
							<c:if test="${etatConnexion==null}">
 								<a href="${pageContext.request.contextPath}/AccesRefuse" 
								 class="shadow badge badge-secondary w-25 h-10 bg-#008e97">${article.utilisateur.pseudo}</a>
 								</c:if> 
 					</div>
					</c:forEach>
					</div>
				</div>
			</div>
		<script type="text/javascript" charset="UTF-8">
			achatFunction();

			function venteFunction() {
				document.getElementById("ouvert").disabled = true;
				document.getElementById("enCours").disabled = true;
				document.getElementById("remporte").disabled = true;
				document.getElementById("venteEnCours").disabled = false;
				document.getElementById("venteNonDebutees").disabled = false;
				document.getElementById("venteTerminee").disabled = false;
				document.getElementById("ouvert").checked = false;
				document.getElementById("enCours").checked = false;
				document.getElementById("remporte").checked = false;
			}

			function achatFunction() {
				document.getElementById("ouvert").disabled = false;
				document.getElementById("enCours").disabled = false;
				document.getElementById("remporte").disabled = false;
				document.getElementById("venteEnCours").disabled = true;
				document.getElementById("venteNonDebutees").disabled = true;
				document.getElementById("venteTerminee").disabled = true;
				document.getElementById("venteEnCours").checked = false;
				document.getElementById("venteNonDebutees").checked = false;
				document.getElementById("venteTerminee").checked = false;
			}
		</script>
 <!-- Vendor JS Files -->
  
<%@ include file="../fragments/footer.jsp"%>
<%@ include file="../fragments/script.jsp"%>

</body>
</html>