<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="row justify-content-center mt-5">
    <h1> Modification d'une oeuvre de vente </h1>
</div>

<form method="post" action="updateOeuvreVente.htm" onsubmit="return teste()">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4 bg-light p-4 rounded border border-secondary">
            <div class="form-group">
                <label class="col-md-6 control-label">Titre de l'oeuvre : </label>
                <div class="col-md-8">
                    <INPUT type="text" name="txttitle" value="${oeuvre.titreOeuvrevente}" id="title" class="form-control" min="0" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label">Prix de l'oeuvre : </label>
                <div class="col-md-8">
                    <INPUT type="number" name="txtprice" value="${oeuvre.prixOeuvrevente}" id="price" class="form-control" min="0" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label">Propriétaire de l'oeuvre : </label>
                <div class="col-md-8">
                    <select class="form-control" id="exampleFormControlSelect1" name="txtProprietaire" size="1" required>
                        <c:forEach items="${mesProprietaires}" var="item">
                            <c:choose>
                                <c:when test="${item.idProprietaire == oeuvre.proprietaireByIdProprietaire.idProprietaire}">
                                    <option value="${item.idProprietaire}" selected>${item.prenomProprietaire} ${item.nomProprietaire}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.idProprietaire}">${item.prenomProprietaire} ${item.nomProprietaire}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group col-md-6 mt-5">
                <input type="hidden" name="id" value="${oeuvre.idOeuvrevente}">
                <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                    Modifier
                </button>
                <button type="button" class="btn btn-default btn-danger"
                        onclick="{
                            window.location = '../listerOeuvres.htm';
                        }">
                    <span class="glyphicon glyphicon-remove"></span> Annuler
                </button>
            </div>
        </div>
    </div>
</form>
<%@include file="footer.jsp"%>
</body>

</html>
