<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<templates:htmlPageStart title="Nova Categoria" />

<div class="container">
    <h1>Nova Categoria</h1>

    <form:form modelAttribute="categoryForm" method="post" action="/admin/categories" >

        <div class="form-group">
            <label for="title">Nome</label>
            <form:input type="text" class="form-control" id="title" path="title" required="true"
                        placeholder="Digite aqui o nome da categoria" />
            <form:errors path="title" cssClass="text-danger" />
        </div>

        <div class="form-group">
            <label for="code">Código</label>
            <form:input type="text" class="form-control" id="code" path="code" required="true"
                        placeholder="Por exemplo: desenvolvimento, movile (não use letras maiúsculas, acentos ou caracteres especiais)" />
            <form:errors path="code" cssClass="text-danger" />
        </div>

        <div class="form-group">
            <form:checkbox id="disabled" path="disabled" />
            <label for="disabled">
                <strong>Categoria inativa?</strong>
            </label>
            <span class="help-block">Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações etc.</span>
        </div>

        <div class="form-group">
            <label for="code">Ordem da categoria</label>
            <form:input type="number" min="1" step="1" class="form-control" id="order" path="order"
                        placeholder="Por exemplo: categoria de order 1 aparece antes da categoria de ordem 2" />
            <form:errors path="order" cssClass="text-danger" />
        </div>

        <div class="form-group">
            <label for="studyGuide">Guias de estudo</label>
            <form:textarea rows="8" class="form-control" id="studyGuide" path="studyGuide"
                        placeholder="Um texto apontando para formações para ajudar pessoas perdidas" />
            <form:errors path="studyGuide" cssClass="text-danger" />
        </div>

        <div class="form-group">
            <label for="iconPath">Caminho do ícone</label>
            <form:input type="text" class="form-control" id="iconPath" path="iconPath"
                        placeholder="Por exemplo: /images/categorias/programacao.svg" />
            <form:errors path="iconPath" cssClass="text-danger" />
        </div>

        <div class="form-group">
            <label for="colorHexCode">Cor</label>
            <form:input type="text" class="form-control" id="colorHexCode" path="colorHexCode"
                        placeholder="Por exemplo: #fcc14a" />
            <form:errors path="colorHexCode" cssClass="text-danger" />
        </div>

        <div class="form-group">
            <label for="description">Descrição</label>
            <form:input type="text" class="form-control" id="description" path="description"
                        placeholder="Por exemplo: iOS, Android, PhoneGap etc." />
            <form:errors path="description" cssClass="text-danger" />
        </div>

        <button type="submit" class="btn btn-success">Enviar</button>
        <a class="btn btn-default" href="/admin/categories">Voltar</a>

    </form:form>

</div>

<templates:htmlPageEnd />


