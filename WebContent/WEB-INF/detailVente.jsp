<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="org.encheres.message.LecteurMessage"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.lang.String"%>

<%@ include file="../fragments/head.jsp"%>


<%@ include file="../fragments/nav.jsp"%>

<body>
	<div class="site-section">
		<form
			action="${pageContext.request.contextPath}/DetailVente?no_article=${article.noArticle }"
			method="post">
			<div class="container">
			
			<!-- SECTION LISTE ERREUR -->
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
			
				<div class="row mb-4 align-items-center">
					<div class="col-md-6" data-aos="fade-up">

						<h2>DETAIL VENTE ${article.categorie.libelleCategorie}</h2>
						<jsp:useBean id="today" class="java.util.Date" />
						<fmt:setLocale value="fr_fr" />
						<fmt:parseDate var="dateFinEncheres"
							value="${article.dateFinEncheres}" pattern="yyyy-MM-dd" />
						<c:if
							test="${article.enchere.noAcheteur.noUtilisateur==newUtilisateur.noUtilisateur}">
							<h4>Vous avez remporte l'enchere !</h4>
							<br />
						</c:if>


						<div class="sticky-content">

							<!-- NOM DE L ARTICLE -->
							<label for="no_article"></label> <input type="hidden"
								id="no_article" name="no_article" value="${article.noArticle }">

							<h2 class="h2 ">${article.nomArticle}</h2>
							${article.categorie.libelleCategorie }
							<!-- DESCRIPTION -->
							<div class="mb-5">
								<p class="mb-0">
									<span class="text-muted">${article.description}</span>
								</p>
							</div>

						</div>


					</div>
				</div>

				<div class="site-section pb-0">
					<div class="container">
						<div class="row align-items-stretch">
							<div class="col-md-8" data-aos="fade-up">
								<c:if test="${article.categorie.noCategorie==1}">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/img/informatique.jpg"
										style="height: 350px">
								</c:if>
								<c:if test="${article.categorie.noCategorie==2}">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/img/ameublement.jpg"
										style="height: 350px">
								</c:if>
								<c:if test="${article.categorie.noCategorie==3}">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/img/vetement.jpg"
										style="height: 350px">
								</c:if>
								<c:if test="${article.categorie.noCategorie==4}">
									<img class="img-fluid"
										src="${pageContext.request.contextPath}/img/sportsEtLoisirs.jpg"
										style="height: 350px">
								</c:if>
							</div>
							<div class="col-md-3 ml-auto" data-aos="fade-up"
								data-aos-delay="100">

								<!-- PRIX -->
								<div class="mb-5">
									<p class="mb-0">
										<span class="text-muted">Meilleur enchere</span>
									</p>
									<!-- MEILLEUR OFFRE CONDITION 1 SI IL N Y A PAS D OFFRE EN COUR -->
									<p class="ml-2">
										<c:if test="${article.enchere.montantEnchere==0}">
											<td>Soyez le premier a encherir !</td>
										</c:if>
										<c:if test="${article.enchere.montantEnchere!=0}">
											<td>${article.enchere.montantEnchere} pts par
												<a href="${pageContext.request.contextPath}/VisualiserVendeur?no_vendeur=${article.enchere.noAcheteur.noUtilisateur}"
								 class="shadow badge badge-info w-25 h-10 bg-#008e97">${article.enchere.noAcheteur.pseudo}</a></td>
										</c:if>
										<input type="hidden" name="meilleureOffre"
											value="${article.enchere.montantEnchere}">
									</p>
									<!-- MISE A PRIX -->
									<p class="mb-0">
										<span class="text-muted">Mise a prix</span>
									</p>
									<p class="ml-2">${article.miseAPrix}</p>
									<input type="hidden" name="miseAPrix"
											value="${article.miseAPrix}">
									<!-- FIN DE L ENCHERE -->
									<p class="mb-0">
										<span class="text-muted">Fin de l'enchere</span>
									</p>
									<fmt:parseDate value="${article.dateFinEncheres}"
										pattern="yyyy-MM-dd" var="DateFinParsee" type="date" />
									<fmt:formatDate value="${DateFinParsee}" var="newDateFinParsee"
										type="date" pattern="dd-MM-yyyy" />
									<p class="ml-2">${newDateFinParsee }</p>
									</p>
									<!-- RETRAIT -->
									<p class="mb-0">
										<span class="text-muted">Retrait</span>
									</p>
									<p class="ml-2">
										${article.retrait.rueRetrait}<br />
										${article.retrait.cpRetrait} ${article.retrait.villeRetrait}
									</p>
									<!-- VENDEUR -->
									<p class="mb-0">
										<span class="text-muted">Vendeur</span>
									</p>
									<p class="ml-2"><a href="${pageContext.request.contextPath}/VisualiserVendeur?no_vendeur=${article.utilisateur.noUtilisateur}"
								 class="shadow badge badge-warning w-25 h-10 bg-#008e97">${article.utilisateur.pseudo}</a></p>

									<p class="mr-auto">
										<img src="./img/logoPhone.png" class="rounded mr-2" alt="...">${article.utilisateur.telephone}</p>
								</div>
							</div>
						</div>
						<!-- SECTION RENCHERIR -->
						<h4 class="h4 mb-3">MA PROPOSITION</h4>
						<div class="input-group mb-3 ">
							<!-- Section formulaire pour rencherir qui renvoi en Post vers la servlet DetailVente	-->
							<input class="w-10" type="number" min="${meilleurOffre}"
								max="${credit} " name="montant_enchere" required maxlength="4"
								value="${enchere.montant_enchere }" class="form-control"
								placeholder="Encherir ici" aria-label="Recipient's username"
								aria-describedby="basic-addon2">
							<div class="input-group-append">
								<span class="input-group-text">Credit disponible :
									${newUtilisateur.credit}</span>
								<!-- bouton permettant de valider la surench???re-->
								<c:if
									test="${newUtilisateur.credit<article.enchere.montantEnchere }">
									<button disabled tabindex="0" type="submit"
										class="btn btn-lg btn-danger" role="button"
										data-toggle="popover" data-trigger="focus"
										title="Dismissible popover"
										data-content="And here's some amazing content. It's very engaging. Right?">Encherir</button>
								</c:if>
								<c:if
									test="${newUtilisateur.credit>article.enchere.montantEnchere }">
									<button tabindex="0" type="submit"
										class="btn btn-lg btn-danger" role="button"
										data-toggle="popover" data-trigger="focus"
										title="Encherir"
										data-content="And here's some amazing content. It's very engaging. Right?">Encherir</button>
								</c:if>
							</div>
						</div>

						
						<!-- BOUTON BACK -->
						<P class="text-center" colspan="2">
							<a class="readmore" href="<%=request.getContextPath() %>/Accueil"
								role="button">Back</a>
						</P>
					</div>
		</form>
	</div>

	</div>

	<script type="text/javascript" charset="UTF-8">
	encherirFunction();

	function venteFunction() {
		document.getElementById("ouvert").disabled = true;
	}
	</script>

	<%@ include file="../fragments/footer.jsp"%>
	<%@ include file="../fragments/script.jsp"%>
</body>
</html>