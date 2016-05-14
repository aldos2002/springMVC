<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
    <spring:url value="/movies" var="urlHome"/>
    <li class="active"><a href="${urlHome}">Home</a></li>
</div>

<div id="footer">
    <img src="/springMVC/resources/footer.jpg" alt="footer" style="width:500px;height:100px;">
</div>