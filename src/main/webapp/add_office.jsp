<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add New Office</title>
    <jsp:include page="assets/bootstrap_css.html"/>
</head>
<body>
<jsp:include page="components/navbar.html"/>

<div class="container" style="margin-top: 100px;">
    <h1>Office List ::</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a
                    href="${pageContext.request.contextPath}/office-list">Office
                List</a></li>
            <li class="breadcrumb-item active"
                aria-current="page">Add New Office
            </li>
        </ol>
    </nav>
    <hr>
    <form class="row"
          action="${pageContext.request.contextPath}/add-office"
          method="post">
        <div class="col-3 mb-2">
            <jsp:include page="components/text_input.jsp">
                <jsp:param name="name" value="addressLine1"/>
                <jsp:param name="id" value="addressLine1"/>
                <jsp:param name="label" value="Address Line 1"/>
                <jsp:param name="required" value="true"/>
            </jsp:include>
        </div>
        <div class="col-3 mb-2">
            <jsp:include page="components/text_input.jsp">
                <jsp:param name="name" value="addressLine2"/>
                <jsp:param name="id" value="addressLine2"/>
                <jsp:param name="label" value="Address Line 2"/>
            </jsp:include>
        </div>
        <div class="col-3 mb-2">
            <jsp:include page="components/text_input.jsp">
                <jsp:param name="name" value="city"/>
                <jsp:param name="id" value="city"/>
                <jsp:param name="label" value="City"/>
                <jsp:param name="required" value="true"/>
            </jsp:include>
        </div>
        <div class="col-3 mb-2">
            <jsp:include page="components/text_input.jsp">
                <jsp:param name="name" value="state"/>
                <jsp:param name="id" value="state"/>
                <jsp:param name="label" value="State"/>
            </jsp:include>
        </div>
        <div class="col-3 mb-2">
            <jsp:include page="components/text_input.jsp">
                <jsp:param name="name" value="country"/>
                <jsp:param name="id" value="country"/>
                <jsp:param name="label" value="Country"/>
                <jsp:param name="required" value="true"/>
            </jsp:include>
        </div>
        <div class="col-3 mb-2">
            <jsp:include page="components/text_input.jsp">
                <jsp:param name="name" value="postalCode"/>
                <jsp:param name="id" value="postalCode"/>
                <jsp:param name="label" value="Postal Code"/>
                <jsp:param name="required" value="true"/>
            </jsp:include>
        </div>
        <div class="col-3 mb-2">
            <jsp:include page="components/text_input.jsp">
                <jsp:param name="name" value="territory"/>
                <jsp:param name="id" value="territory"/>
                <jsp:param name="label" value="Territory"/>
                <jsp:param name="required" value="true"/>
            </jsp:include>
        </div>
        <div class="col-3 mb-2">
            <jsp:include page="components/text_input.jsp">
                <jsp:param name="name" value="phone"/>
                <jsp:param name="id" value="phone"/>
                <jsp:param name="label" value="Phone"/>
                <jsp:param name="required" value="true"/>
            </jsp:include>
        </div>
        <div class="col-12 mt-2 text-end">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>

<c:if test="${param.error != null}">
    <jsp:include page="components/toast.jsp">
        <jsp:param name="toast_status" value="error"/>
        <jsp:param name="toast_message" value="${param.success}"/>
    </jsp:include>
</c:if>

<jsp:include page="assets/bootstrap_js.html"/>
</body>
</html>
