<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content">
    <div class="container">

        <c:choose>
            <c:when test="${movieForm['new']}">
                <h1>Add Movie</h1>
            </c:when>
            <c:otherwise>
                <h1>Update Movie</h1>
            </c:otherwise>
        </c:choose>
        <br/>

        <spring:url value="/movies" var="movieActionUrl"/>

        <form:form class="form-horizontal" method="post" modelAttribute="movieForm" action="${movieActionUrl}">

            <form:hidden path="id"/>

            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="name" type="text" class="form-control " id="name" placeholder="Name"/>
                        <form:errors path="name" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="director">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Director</label>
                    <div class="col-sm-10">
                        <form:input path="director" class="form-control" id="director" placeholder="Director"/>
                        <form:errors path="director" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="year">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Year</label>
                    <div class="col-sm-10">
                        <form:input path="year" class="form-control" id="year" placeholder="year"/>
                        <form:errors path="year" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${movieForm['new']}">
                            <button type="submit" class="btn-lg btn-primary pull-right">Add</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
    </div>
</div>