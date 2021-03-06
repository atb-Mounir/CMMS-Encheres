<%@ include file="../fragments/head.jsp"%>
<%@page import="org.encheres.message.LecteurMessage"%>
<style>
.col {margin-bottom:50px}
</style>

<body>
	<div class="site-section" >
<% 
// 	Variables � r�cup�rer dans servlet
	int credit=0;
%>


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

		<h1 style="text-align:center">Mon profil</h1>


<form action="${pageContext.request.contextPath}/MonProfil" method="post" 
class="text-center border border-light p-5">

    <div class="form-row mb-4">
        <div class="col">
            <label for="pseudo" class="col-sm-4 col-form-label">Pseudo:</label>
            <input type="text" id="pseudo" name="pseudo" maxlength="30" required value="${utilisateur.pseudo}">
        </div>
        <div class="col">
            <label for="nom" class="col-sm-4 col-form-label">Nom:</label>
            <input type="text" id="nom" name="nom" maxlength="30" required value="${utilisateur.nom}">
        </div>
    </div>

	<div class="form-row mb-4">
        <div class="col">
            <label for="prenom" class="col-sm-4 col-form-label">Prenom:</label>
            <input type="text" id="prenom" name="prenom" maxlength="30" required value="${utilisateur.prenom}">
        </div>
        <div class="col">
            <label for="email" class="col-sm-4 col-form-label">Email:</label>
            <input type="email" id="email" name="email" required value="${utilisateur.email}">
        </div>
    </div>
    
    <div class="form-row mb-4">
        <div class="col">
            <label for="telephone" class="col-sm-4 col-form-label">Telephone:</label>
            <input type="tel" id="telephone" name="telephone" value="${utilisateur.telephone}">
        </div>
        <div class="col">
            <label for="rue" class="col-sm-4 col-form-label">Rue:</label>
            <input type="text" id="rue" name="rue" required maxlength="30" value="${utilisateur.rueUtilisateur}">
        </div>
    </div>
    
    <div class="form-row mb-4">
        <div class="col">
            <label for="cp" class="col-sm-4 col-form-label">Code postal:</label>
            <input type="number" id="cp" name="cp" pattern=".{5,5}"  required value="${utilisateur.cpUtilisateur}">
        </div>
        <div class="col">
            <label for="ville" class="col-sm-4 col-form-label">Ville:</label>
            <input type="text" id="ville" name="ville" required value="${utilisateur.villeUtilisateur}">
        </div>
    </div>
    
    <!-- Mode D�connect� -->
    
    <c:if test="${etatConnexion==null}">	
	    <div class="form-row justify-content-md-center">
	        <div class="col">
	            <label for="motDePasse" class="col-sm-4 col-form-label">Mot de passe:</label>
	            <input type="password" id="motDePasse" name="motDePasseCreation" required>
	        </div>
	        <div class="col">
	            <label for="motDePasseConfirm" class="col-sm-4 col-form-label">Confirmer:</label>
	            <input type="password" id="motDePasseConfirm" name="motDePasseCreationConfirm" required>
	        </div>
	    </div>
	    
	    <div class="form-row">
	        <div class="col">
	        	<input type="submit"class="readmore"
					style="margin-left:35%;" value="Creer" name="creer">
	        </div>
	        <div class="col">
	           <div class="col">
	            <a class="readmore" href="${pageContext.request.contextPath}/Accueil" role="button" style="margin-left:35%;">Annuler</a>
	        </div>
	        </div>
	    </div>
    </c:if>
    
    <!-- Mode Connect� -->
    
    <c:if test="${etatConnexion!=null}">
	    <div class="form-row justify-content-md-center">
	        <div class="col">
	            <label for="motDePasse" class="col-sm-4 col-form-label">Mot de passe actuel:</label>
	            <input type="password" id="motDePasse" name="motDePasseActuel" required>
	        </div>
	        <div class="col"></div>
	    </div>
	    
	    <div class="form-row justify-content-md-center">
	        <div class="col">
	            <label for="motDePasse" class="col-sm-4 col-form-label">Nouveau mot de passe:</label>
	            <input type="password" id="motDePasse" name="motDePasseNouveau">
	        </div>
	        <div class="col">
	            <label for="motDePasse" class="col-sm-4 col-form-label">Confirmation:</label>
	            <input type="password" id="motDePasse" name="motDePasseNouveauConfirmation">
	        </div>
	    </div>
	    
	    <div class="form-row justify-content-md-center">
	        <div class="col">
	            <label for="credit" class="col-sm-4 col-form-label">Credit:</label>
	            ${utilisateur.credit}
	        </div>
	        <div class="col"></div>
	    </div>
	    
	    <div class="form-row">
	        <div class="col">
	        	<input type="submit" class="readmore"
					style="margin-left:35%;" name="enregistrer" value="Enregistrer">
	        </div>
	        <div class="col">
	           <div class="col">
	            <input type="submit"class="readmore"
					style="margin-left:35%;" name="supprimer" value="Supprimer">
	        </div>
	        </div>
    </div>
    </c:if>
    
</form>
<!-- Default form register -->

<%@ include file="../fragments/footer.jsp"%>
<%@ include file="../fragments/script.jsp"%>
</div>
</body>
</html>