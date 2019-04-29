<%--
  Created by IntelliJ IDEA.
  User: christian
  Date: 06/04/2018
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="vues/header.jsp" %>
<body>
<%@include file="vues/navigation.jsp"%>

<div class="row justify-content-center mt-5">
    <h1>Bienvenue à la médiathèque de POLYTECH Lyon !</h1>
</div>
<div class="row justify-content-center">
    <div class="col-md-8 bg-light p-4 rounded mt-5">
        <p>Vous pouvez utiliser ici l'application de gestion des réservations des différentes oeuvres dont nous disposons.</p>

        <p>Si vous êtes un <b>administrateur *</b>, vous avez la possibilité de :</p>
        <ul>
            <li>Consulter la liste des oeuvres en vente n'ayant pas été réservées et les oeuvres en prêt</li>
            <li>Ajouter un adhérent</li>
            <li>Ajouter une oeuvre en prêt ou à vendre</li>
            <li>Ajouter une réservation pour une offre à vendre</li>
            <li>Ajouter une réservation pour une offre en prêt</li>
            <li>Modifier les informations liées à une oeuvre</li>
            <li>Modifier les informations liées à une réservation</li>
            <li>Modifier le statut d'une réservation et d'une oeuvre à vendre</li>
        </ul>
        <p class="text-danger"><b>Toute modification est définitive et aucun rétour en arrière n'est possible !</b></p>
        <i>* Pour les tests administrateur utiliser : Merlot secret</i>
    </div>
</div>
<%@include file="vues/footer.jsp"%>
</body>
</ht