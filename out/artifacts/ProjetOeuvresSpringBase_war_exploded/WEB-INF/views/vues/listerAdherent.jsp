<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
	<div class="container mt-5 mb-5">
		<h1 class="text-center">Liste des adhérents</h1>
	</div>
	<div class="row justify-content-center mb-5">
		<div class="col-md-8">
			<a class="btn btn-outline-primary" href="index.htm" role="button">&#8592; Retour accueil</a>
			<a class="btn btn-outline-success ml-3" href="ajouterAdherent.htm" role="button">Ajouter un adhérent</a>
			<c:if test="${suppression}">
				<p class="text-success mt-3">Adhérent supprimé avec succès !</p>
			</c:if>
			<c:if test="${ajout}">
				<p class="text-success mt-3">Adhérent ajouté avec succès !</p>
			</c:if>
			<c:if test="${modification}">
				<p class="text-success mt-3">Modification réussie !</p>
			</c:if>
			<table id="tableAdherents" class="table table-hover mt-3">
				<thead >
					<tr class="text-center bg-primary text-white">
						<th scope="col">Numero</th>
						<th scope="col">Nom</th>
						<th scope="col">Prénom</th>
						<th scope="col" >Ville</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>

				<c:forEach items="${mesAdherents}" var="item">
					<tr class="text-center">
						<th scope="row">${item.idAdherent}</th>
						<td>${item.nomAdherent}</td>
						<td>${item.prenomAdherent}</td>
						<td>${item.villeAdherent}</td>
						<td><a class="btn btn-info" href="modifierAdherent.htm?id=${item.idAdherent}" role="button"><span
								class="glyphicon glyphicon-pencil"></span> Modifier</a>
							<a class="btn btn-danger" href="supprimerAdherent.htm?id=${item.idAdherent}" role="button" onclick="return confirm('Êtes-vous sur de vouloir supprimer cet adhérent ?')"><span
									class="glyphicon glyphicon-remove-circle"></span> Supprimer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

<%@include file="footer.jsp"%>
</body>

</html>