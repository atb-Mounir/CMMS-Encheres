
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.encheres.bo.Categorie"%>
<%@page import="org.encheres.bo.Article"%>
<%@page import="org.encheres.bo.Enchere"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="java.time.LocalDate"%>

<%@ include file="../fragments/head.jsp"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<body>
	<div class="container">
		<%@ include file="../fragments/nav.jsp"%>

			<div class="container" >
				<h1 style="text-align: center">Nouvelle vente</h1>

					<form action="${pageContext.request.contextPath}/NouvelleVente"
						method="post" class="border border-light p-5">
						<!--NOM DE l'ARTICLE -->
						<div class="form-group row justify-content-center">
							<div class="col-xs-6 col-sm-3">
								<label for="article" class="col-sm col-form-label" style="">Article :</label>
							</div>
							<div class="col-xs-6 col-sm-5">
								<input type="text" name="nomArticle" class="form-control" id="article" placeholder="Nom de l'article" required value="${article.nomArticle}">
							</div>
						</div>
						<!--DESCRIPTION DE l'ARTICLE -->
						<div class="form-group row justify-content-center">
							<div class="col-xs-6 justify-content-center col-sm-3">
								<label for="description" class="col-sm col-form-label" style="">Description :</label>
							</div>
								<c:if test="${retraitRue!=null}">
								<div class="col-xs-6 col-sm-5">
									<textarea type="text-area" name="description" rows="6"  class="form-control" id="description" placeholder="Description de l'article" style=""></textarea>
								</div>
								</c:if>
								<c:if test="${article!=null}">
								<div class="col-xs-6 col-sm-5">
									<textarea type="text-area" name="description" rows="6"  class="form-control" id="description" required value="${article.getDescription()}">${article.getDescription()}</textarea>
								</div>
								</c:if>
						</div>
						<!--CHOIX DES CATEGORIES -->
						<div class="form-group row justify-content-center">
							<div class="col-xs-6  col-sm-3">
								<label for="categorie" class="col-sm col-form-label" style="" >Categories :</label>
							</div>
							<div class="col-xs-6 col-sm-5">
								<select name="categorie" id="categorie" class="form-control">
									<c:forEach items="${categories}" var="categorie">
									<option value="${ categorie.noCategorie }">${categorie.libelleCategorie}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<!--UPLOAD FILE-->
						<div class="form-group row justify-content-center">
							<div class="col-xs-6 justify-content-center col-sm-3">
								<label for="upload" class="col-sm col-form-label" style="">Photo de l'article :</label>
							</div>
							<div class="col-xs-6 col-sm-5">
								<input type="file" name="upload" class="form-control-file" id="upload">
							</div>
						</div>
						<!--MISE A PRIX -->
						<div class="form-group row justify-content-center">
							<c:if test="${retraitRue!=null}">
								<div class="col-xs-6 col-sm-3">
									<label for="miseAprix" class="col-sm col-form-label" style="">Mise a prix :</label>
								</div>
								<div class="col-xs-6 col-sm-5">
									<input type="number" name="miseAPrix" class="btn btn-outline-*" min="0" max="2000" step="10" id="miseAprix" value="${article.miseAPrix}" required style="border:1px solid darkgrey;">
								</div>
							</c:if>
							<c:if test="${article!=null}">
								<div class="col-xs-6 col-sm-3">
									<label for="miseAprix" class="col-sm col-form-label" style="">Mise a prix :</label>
								</div>
								<div class="col-xs-6 col-sm-5">
									<input type="number" name="miseAPrix" class="btn btn-outline-*" min="0" max="2000" step="10" id="miseAprix" value="${article.miseAPrix}" required style="border:1px solid darkgrey;">
								</div>
							</c:if>
						</div>
						<!--DEBUT DE l'ENCHERE -->
						<div class="form-group row justify-content-center">
							<c:if test="${retraitRue!=null}">
								<div class="col-xs-6 col-sm-3">
									<label for="debutEnchere" class="col-sm col-form-label" style="">Debut de l'enchere :</label>
								</div>
								<div class="col-xs-6 col-sm-5">
									<input type="date" name="dateDebutEnchere" class="btn btn-outline-*" id="debutEnchere" required value="" style="border:1px solid darkgrey;">
								</div>
							</c:if>
							<c:if test="${article!=null}">
								<div class="col-xs-6 col-sm-3">
									<label for="debutEnchere" class="col-sm col-form-label" style="">Debut de l'enchere :</label>
								</div>
								<div class="col-xs-6 col-sm-5">
									<input type="date" name="dateDebutEnchere" class="btn btn-outline-*" id="debutEnchere" required value="${article.dateDebutEncheres}" style="border:1px solid darkgrey;">
								</div>
							</c:if>
						</div>
						<!--FIN DE l'ENCHERE -->
						<div class="form-group row justify-content-center">
							<c:if test="${retraitRue!=null}">
								<div class="col-xs-6 col-sm-3">
									<label for="finEnchere" class="col-sm col-form-label" style="">Fin de l'enchere :</label>
								</div>
								<div class="col-xs-6 col-sm-5">
									<input type="date" name="dateFinEnchere" class="btn btn-outline-*" required id="finEnchere" style="border:1px solid darkgrey;">
								</div>
							</c:if>
							<c:if test="${article!=null}">
								<div class="col-xs-6 col-sm-3">
									<label for="finEnchere" class="col-sm col-form-label" style="">Fin de l'enchere :</label>
								</div>
								<div class="col-xs-6 col-sm-5">
									<input type="date" name="dateFinEnchere" class="btn btn-outline-*" id="finEnchere" min="2020-01-30" required value="${article.dateFinEncheres}" style="border:1px solid darkgrey;">
								</div>
							</c:if>
						</div>
						<!--ADRESSE DU RETRAIT -->
						<c:if test="${retraitRue!=null}">
							<div class="form-group row justify-content-center">
								<div class="col-xs-8 col-sm-8">
									<fieldset for="Retrait" class="col-sm " style="border:1px solid darkgrey; border-radius: 5px">
										<legend style="text-align: center; font-weight:bold">Retrait</legend>
											<label  class="col-sm">Rue :</label>
											<input name="retraitRue" class="col-sm" type="text" value="${retraitRue}" required>
											<label class="col-auto" style="margin-top: 10px">Code Postal :</label>
											<input name="retraitCP" class="col-sm" type="text" value="${retraitCP}" required>
											<label class="col-sm" style="margin-top: 10px">Ville :</label>
											<input name="retraitVille" class="col-sm" type="text" value="${retraitVille}" style="margin-bottom: 20px" required>
									</fieldset>
								</div>
							</div>
						</c:if>
						<c:if test="${article!=null}">
							<div class="form-group row justify-content-center">
								<div class="col-xs-8 col-sm-8">
									<fieldset for="Retrait" class="col-sm " style="border:1px solid darkgrey; border-radius: 5px">
										<legend style="text-align: center; font-weight:bold">Retrait</legend>
											<label  class="col-sm">Rue :</label>
											<input name="retraitRue" class="col-sm" type="text" value="${article.retrait.rueRetrait}" required>
											<label class="col-auto" style="margin-top: 10px">Code Postal :</label>
											<input name="retraitCP" class="col-sm" type="text" value="${article.retrait.cpRetrait}" required>
											<label class="col-sm" style="margin-top: 10px">Ville :</label>
											<input name="retraitVille" class="col-sm" type="text" value="${article.retrait.villeRetrait}" style="margin-bottom: 20px" required>
									</fieldset>
								</div>
							</div>
						</c:if>
						<!--BOUTONS SUBMIT DU FORM -->
							
							<div class="row justify-content-center">
							<div class="col-8 justify-content-center">
								<c:if test="${retraitRue!=null}">	
									<input type="submit" name="enregistrer" value="Enregistrer"
										class="btn btn-outline-info" style="float: left">
									<input type="submit" name="annuler" value="Annuler"
										class="btn btn-outline-info" style="margin-left: 1em; ">
								</c:if>
								<c:if test="${retraitRue==null}">
								<input type="submit" name="annulerVente" value="Annuler Vente"
									class="btn btn-warning" style="float: right;">
								</c:if>
							</div>
							</div>
							
							
							
							
							
							
							
<!-- 							<div class="item photography col-sm-6  mb-4 ml-5"> -->
<!-- 								<input type="submit" name="enregistrer" value="Enregistrer"> -->
<!-- 								<input type="submit" name="annuler" value="Annuler" -->
<!-- 									class="readmore" style="margin-left: 1em; "> -->
<!-- 								<input type="submit" name="annulerVente" value="Annuler Vente" -->
<!-- 										class="readmore" id="annulerVente"   style="float: right;"> -->
<!-- 							</div> -->
					</form>
			</div>
			
			
		<%@ include file="../fragments/footer.jsp"%>
		<%@ include file="../fragments/script.jsp"%>
	</div>
</body>
</html>
