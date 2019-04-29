<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="row justify-content-center mt-5">
    <c:if test="${oeuvre==null}">
        <h1> Ajout d'un pret </h1>
    </c:if>
    <c:if test="${oeuvre!=null}">
        <h1> Modification d'une pret </h1>
    </c:if>
</div>

<form method="post" action="changeReservationPret.htm" onsubmit="return teste()">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4 bg-light p-4 rounded border border-secondary">
            <div class="form-group">
                <label class="col-md-12 control-label">Titre de l'oeuvre : </label>
                <div class="col-md-8">
                    <INPUT type="text" name="txttitle" value="${titre}" id="title" class="form-control" min="0" disabled="disabled" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-12 control-label">Emprunteur : </label>
                <div class="col-md-8">
                    <select class="form-control" id="exampleFormControlSelect1" name="txtAdherent" size="1" required>
                        <c:forEach items="${adherents}" var="item">
                            <c:choose>
                                <c:when test="${pret!=null && pret.getAdherentByIdAdherent().getIdAdherent()==item.idAdherent}">
                                    <option value="${item.idAdherent}" selected>${item.prenomAdherent} ${item.nomAdherent}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.idAdherent}">${item.prenomAdherent} ${item.nomAdherent}</option>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group col-md-12 mt-5">
                <input type="hidden" name="id" value="${id}">
                <c:choose>
                    <c:when test="${pret==null}">
                        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                            Ajouter
                        </button>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="act" value="modifier">
                        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                            Modifier
                        </button>
                    </c:otherwise>
                </c:choose>
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
