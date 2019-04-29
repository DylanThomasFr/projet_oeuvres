<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="container mt-5">
    <h1 class="text-center">Authentification</h1>
</div>
<form method="post" action="controleLogin.htm">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4 bg-light p-4 rounded border border-secondary">
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-3 control-label">Identifiant : </label>
                    <div class="col-md-8">
                        <input type="text" name="login" class="form-control" placeholder="Votre identifiant" required autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Mot de passe : </label>
                    <div class="col-md-8">
                        <input type="password" name="pwd" ng-model="pwd" class="form-control" placeholder="Votre mot de passe" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-8">
                        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-log-in"></span> Valider</button>
                    </div>
                </div>
                <c:if test="${not empty message}">
                    <div class="alert alert-danger fade in">
                         <div class="col-md-3 ">
                            <strong> Connexion !</strong> <c:out value="${message}" />
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</form>

<%@include file="footer.jsp"%>
</body>
</html>
