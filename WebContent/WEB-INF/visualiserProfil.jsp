<%@ include file="../fragments/head.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="../fragments/nav.jsp"%>

		<div class="container ">
			<div class="row ">
				<div class="col align-self-start"></div>
				<div class="col align-self-center">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Pseudo</th>
								<th scope="col">${utilisateur.pseudo}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Nom:</td>
								<td>${utilisateur.nom}</td>
							</tr>
							<tr>
								<td>Prenom:</td>
								<td>${utilisateur.prenom}</td>
							</tr>
							<tr>
								<td>Email:</td>
								<td>${utilisateur.email}</td>
							</tr>
							<tr>
								<td>Telephone:</td>
								<td>${utilisateur.telephone}</td>
							</tr>
							<tr>
								<td>Rue:</td>
								<td>${utilisateur.rueUtilisateur}</td>
							</tr>
							<tr>
								<td>Code postal:</td>
								<td>${utilisateur.cpUtilisateur}</td>
							</tr>
							<tr>
								<td>Ville:</td>
								<td>${utilisateur.villeUtilisateur}</td>
							</tr>
							<c:if
								test="${utilisateur.noUtilisateur==newUtilisateur.noUtilisateur}">
								<tr>
									<td>Credit:</td>
									<td>${utilisateur.credit}</td>
								</tr>
								<tr>

									<td class="text-center" colspan="2"><a
										class="readmore"
										href="<%=request.getContextPath()%>/MonProfil" role="button">Modifier</a>
									</td>
							</c:if>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col align-self-end"></div>
			</div>
		</div>

		<%@ include file="../fragments/footer.jsp"%>
		<%@ include file="../fragments/script.jsp"%>
	</div>
</body>
</html>

