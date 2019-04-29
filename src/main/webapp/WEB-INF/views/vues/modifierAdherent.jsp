<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="row justify-content-center mt-5">
    <h1> Modification d'un adhérent </h1>
</div>

<form method="post" action="updateAdherent.htm" onsubmit="return teste()">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4 bg-light p-4 rounded border border-secondary">
            <div class="form-group">
                <label class="col-md-6 control-label">Nom de l'adherent : </label>
                <div class="col-md-8">
                    <INPUT type="text" name="txtnom" value="${adherent.nomAdherent}" id="nom" class="form-control" min="0" required>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label">Prénom de l'adherent : </label>
                <div class="col-md-8">
                    <INPUT type="text" name="txtprenom" value="${adherent.prenomAdherent}" id="prenom" class="form-control" min="0" required>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-6 control-label">Ville de l'adherent : </label>
                <div class="col-md-8">
                    <INPUT type="text" name="txtville" value="${adherent.villeAdherent}" id="ville" class="form-control" min="0" required>
                </div>
            </div>

            <div class="form-group col-md-6">
                <input type="hidden" name="id" value="${adherent.idAdherent}" id="id">
                <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                    Modifier
                </button>
                <button type="button" class="btn btn-default btn-danger"
                        onclick="{
                            window.location = '../listerAdherent.htm';
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
