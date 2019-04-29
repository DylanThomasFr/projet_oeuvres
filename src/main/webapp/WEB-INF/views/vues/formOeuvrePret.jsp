<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="row justify-content-center mt-5">
    <c:if test="${oeuvre==null}">
        <h1> Ajout d'une oeuvre en pret </h1>
    </c:if>
    <c:if test="${oeuvre!=null}">
        <h1> Modification d'une oeuvre en pret </h1>
    </c:if>
</div>

<form method="post" action="changeOeuvrePret.htm" onsubmit="return teste()">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4 bg-light p-4 rounded border border-secondary">
            <div class="form-group">
                <label class="col-md-12 control-label">Titre de l'oeuvre : </label>
                <div class="col-md-8">
                    <INPUT type="text" name="txttitle" value="${oeuvre.getTitreOeuvrepret()}" id="title" class="form-control" min="0" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-12 control-label">Propriétaire de l'oeuvre : </label>
                <div class="col-md-8">
                    <select class="form-control" id="exampleFormControlSelect1" name="txtProprietaire" size="1" required>
                        <c:forEach items="${mesProprietaires}" var="item">
                            <c:choose>
                                <c:when test="${oeuvre!=null && oeuvre.getProprietaireByIdProprietaire().getIdProprietaire()==item.idProprietaire}">
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

            <div class="form-group col-md-12 mt-5">
                <c:choose>
                    <c:when test="${oeuvre==null}">
                        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                            Ajouter
                        </button>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="id" value="${oeuvre.getIdOeuvrepret()}">
                        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                            Modifier
                        </button>
                    </c:otherwise>
                </c:choose>
                <button type="button" class="btn btn-default btn-danger"
                        onclick="{
                            window.location = '../listerOeuvresPret.htm';
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
