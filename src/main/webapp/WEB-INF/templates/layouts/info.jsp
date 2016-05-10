<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content">
    <div class="container">

        <c:if test="${not empty msg}">
            <div>
                <strong>${msg}</strong>
            </div>
        </c:if>

        <h1>Movie Detail</h1>
        <br/>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>${movie.id}</td>
                </tr>

                <tr>
                    <td>Name</td>
                    <td>${movie.name}</td>
                </tr>

                <tr>
                    <td>Director</td>
                    <td>${movie.director}</td>
                </tr>

                <tr>
                    <td>Year</td>
                    <td>${movie.year}</td>
                </tr>
            </table>
    </div>
</div>