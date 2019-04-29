<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">

        <a class="navbar-brand" href="index.htm">Média'tech</a>
        <ul class="nav navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="index.htm"> Accueil</a>
            </li>
            <c:if test="${sessionScope.id == null }">
                <li class="dropdown">
                    <a class="nav-link"  href="login.htm">
                            Se connecter
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.id > 0  }">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="#">
                        <span class="glyphicon glyphicon-user"></span>
                            Adhérents
                            <span class="caret"></span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="listerAdherent.htm"> Lister les adhérents</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="ajouterAdherent.htm">Ajout Adhérent</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="#">
                        <span class="glyphicon glyphicon-user"></span>
                        Oeuvres
                        <span class="caret"></span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="listerOeuvres.htm">Liste des oeuvres en vente</a>
                        <a class="dropdown-item" href="listerOeuvresPret.htm">Liste des oeuvres en prêt</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="ajouterOeuvreVente.htm">Ajout d'une oeuvre à vendre</a>
                        <a class="dropdown-item" href="ajouterOeuvrePret.htm">Ajout d'une oeuvre de prêt</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" href="#">
                        <span class="glyphicon glyphicon-user"></span>
                            Réservations
                        <span class="caret"></span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="listerReservationsVentes.htm">Liste des réservations d'oeuvres vente</a>
                        <a class="dropdown-item" href="listerReservationsPret.htm">Liste des réservations d'oeuvres prêt</a>
                    </div>
                </li>
                <li >
                    <a class="nav-link" href="deconnect.htm">Deconnexion</a>
                </li>
            </c:if>

        </ul>
    </nav>


