<%@ include file="../fragments/head.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="../fragments/nav.jsp"%>

		<h1 style="text-align:center">Acces interdit</h1>
		

		<h2 style="text-align:center; margin-top:100px;">L'acces a cette fonctionnalite necessite d'etre connecte</h2>

		
		
		<div style="text-align:center;margin-top:100px">
		<a class="btn btn-outline-primary btn-lg col-sm-3" href="${pageContext.request.contextPath}/Accueil" role="button" style="display:inline-block;">
		Revenir a la page d'accueil</a>
		
		<a class="btn btn-primary btn-lg col-sm-3" href="${pageContext.request.contextPath}/MonProfil" role="button" style="display:inline-block;">
		Creer un compte</a>

		</div>

		<%@ include file="../fragments/footer.jsp"%>
		<%@ include file="../fragments/script.jsp"%>
	</div>
</body>
</html>

