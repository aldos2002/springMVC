<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/save" var="uploadAction"/>
<div id="content">
    <c:if test="${not empty msg}">
        <div>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <h1>Terms and Conditions Upload</h1>

    <form:form method="post" action="${uploadAction}"
              enctype="multipart/form-data">
        <p>Select pdf file to upload.</p>

        <table id="fileTable">
            <tr>
                <td><input name="uploadFile" type="file"/></td>
            </tr>
        </table>
        <br/><input type="submit" value="Upload"/>
    </form:form>
</div>