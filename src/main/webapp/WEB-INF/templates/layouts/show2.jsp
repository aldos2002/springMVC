<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:url value="/movies/add" var="urlAddMovie" />
<div id="content">

    <div class="container">
        <c:if test="${not empty msg}">
            <div>
                <strong>${msg}</strong>
            </div>
        </c:if>

        <h1>Movies</h1>

        <table border="1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Director</th>
                <th>Year</th>
                <th>Action</th>
            </tr>
            </thead>

            <c:forEach var="movie" items="${movies}">
                <tr>
                    <td>${movie.id}</td>
                    <td>${movie.name}</td>
                    <td>${movie.director}</td>
                    <td>${movie.year}</td>
                    <td>
                        <spring:url value="/movies/${movie.id}" var="movieUrl" />
                        <spring:url value="/movies/${movie.id}/delete" var="deleteUrl" />
                        <spring:url value="/movies/${movie.id}/update" var="updateUrl" />

                        <button  onclick="location.href='${movieUrl}'">View</button>
                        <button  onclick="location.href='${updateUrl}'">Update</button>
                        <button  onclick="location.href='${deleteUrl}'">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <li class="active"><a href="${urlAddMovie}">Add Movie</a></li>
    </div>
</div>