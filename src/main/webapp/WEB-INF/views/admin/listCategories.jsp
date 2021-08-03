<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:htmlPageStart title="Categorias" />

<div class="container">

    <h1>Categorias</h1>

    <a href="#" class="btn btn-primary" role="button">Nova categoria</a>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Código</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.title}</td>
                    <td>${category.code}</td>
                    <td>${category.disabled ? 'Inativa' : 'Ativa'}</td>
                    <td><a href="#">Subcategorias</a></td>
                    <td><a href="#" class="btn btn-default">Editar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<templates:htmlPageEnd />


