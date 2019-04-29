<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="container mt-5 mb-5">

    <h1 class="text-center">Liste des oeuvres en pret</h1>
</div>
<div class="row justify-content-center mb-5">
    <div class="col-md-8">
        <a class="btn btn-outline-primary" href="index.htm" role="button">&#8592; Retour accueil</a>
        <a class="btn btn-outline-success ml-3" href="ajouterOeuvrePret.htm" role="button">Ajouter une oeuvre à emprunter</a>
        <c:if test="${suppression}">
            <p class="text-success mt-3">Oeuvre supprimée avec succès !</p>
        </c:if>
        <c:if test="${ajout}">
            <p class="text-success mt-3">Oeuvre ajoutée avec succès !</p>
        </c:if>
        <c:if test="${modification}">
            <p class="text-success mt-3">Modification réussie !</p>
        </c:if>
        <table id="tableOeuvres" class="table table-hover mt-3">
            <thead >
            <tr class="text-center bg-primary text-white">
                <th scope="col">Titre</th>
                <th scope="col">Propriétaire</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <c:forEach items="${mesOeuvres}" var="item">
                <tr class="text-center">
                    <th scope="row">${item.titreOeuvrepret}</th>
                    <td>${item.proprietaireByIdProprietaire.getPrenomProprietaire()} ${item.proprietaireByIdProprietaire.getNomProprietaire()}</td>
                    <td><a class="btn btn-success" href="formReservationPret.htm?id=${item.idOeuvrepret}&titre=${item.titreOeuvrepret}" role="button">Réserver</a>
                        <a class="btn btn-info" href="modifierOeuvrePret.htm?id=${item.idOeuvrepret}" role="button">Modifier</a>
                        <a class="btn btn-danger" href="supprimerOeuvrePret.htm?id=${item.idOeuvrepret}" role="button"
                           onclick="return confirm('Êtes-vous sur de vouloir supprimer cette oeuvre ?')">
                            Supprimer
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach items="${mesOeuvresR}" var="item">
                <tr class="text-center">
                    <th scope="row">${item.titreOeuvrepret}</th>
                    <td>${item.proprietaireByIdProprietaire.getPrenomProprietaire()} ${item.proprietaireByIdProprietaire.getNomProprietaire()}</td>
                    <td><a class="btn btn-light" role="button" disabled>Réserver</a>
                        <a class="btn btn-info" href="modifierOeuvrePret.htm?id=${item.idOeuvrepret}" role="button">Modifier</a>
                        <a class="btn btn-danger" href="supprimerOeuvrePret.htm?id=${item.idOeuvrepret}"
                           role="button" onclick="return confirm('En supprimant cette oeuvre, vous supprimerez la réservation associée. Êtes-vous sur de votre choix ?')">
                             Supprimer
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>

</html>
