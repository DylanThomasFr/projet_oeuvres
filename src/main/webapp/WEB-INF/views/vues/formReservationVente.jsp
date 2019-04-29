<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="row justify-content-center mt-5">
    <h1> Réservation d'une oeuvre en vente </h1>
</div>

<form method="post" action="${action}" onsubmit="return teste()">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4 bg-light p-4 rounded border border-secondary">
            <div class="form-group">
                <label class="col-md-6 control-label">Titre de l'oeuvre : </label>
                <div class="col-md-8">
                    <INPUT type="text" name="txttitle" value="${titre}" id="title" class="form-control" min="0" disabled>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label">Prix de l'oeuvre : </label>
                <div class="col-md-8">
                    <INPUT type="number" name="txtprice" value="${prix}" id="price" class="form-control" min="0" disabled>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label">Date de réservation : </label>
                <div class="col-md-8">
                    <INPUT type="date" name="txtdate" value="${date}" id="date" class="form-control" min="0" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label">Adhérent : </label>
                <div class="col-md-8">
                    <select class="form-control" id="exampleFormControlSelect1" name="txtAdherent" size="1" required>
                        <c:forEach items="${users}" var="item">
                            <option value="${item.idAdherent}">${item.prenomAdherent} ${item.nomAdherent}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <c:if test="${statut!=null}">
                <div class="form-group">
                    <label class="col-md-6 control-label">Statut : </label>
                    <div class="col-md-8">
                        <select class="form-control" id="txtStatut" name="txtStatut" size="1" required>
                            <c:if test="${statut=='en attente'}" >
                                <option value="en attente" selected>En attente</option>
                                <option value="confirmee" >Confirmée</option>
                            </c:if>
                            <c:if test="${statut=='confirmee'}" >
                                <option value="en attente" >En attente</option>
                                <option value="confirmee" selected>Confirmée</option>
                            </c:if>
                        </select>
                    </div>
                </div>
            </c:if>


            <div class="form-group col-md-12 mt-5">
                <input type="hidden" value="${id}${idO}" name="id">
                <c:if test="${statut==null}">
                    <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                        Ajouter
                    </button>
                </c:if>
                <c:if test="${statut!=null}">
                    <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                        Modifier
                    </button>
                </c:if>
                <button type="button" class="btn btn-default btn-danger"
                        onclick="{
                            window.location = '../listerReservationsVentes.htm';
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
