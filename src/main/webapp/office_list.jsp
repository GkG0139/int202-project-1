<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Office List</title>

    <jsp:include page="assets/bootstrap_css.html"/>
</head>
<body>
<jsp:include page="components/navbar.html"/>

<div class="container" style="margin-top: 100px;">
    <h1>Office List ::</h1>
    <div class="d-flex flex-row justify-content-between">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb my-auto">
                <li class="breadcrumb-item active" aria-current="page">Office
                    List
                </li>
            </ol>
        </nav>
        <a class="btn btn-primary"
           href="${pageContext.request.contextPath}/add-office" role="button">
            <i class="bi bi-plus-circle"></i> Add Office</a>
    </div>
    <hr>
    <div class="row">
        <c:forEach var="office" items="${offices}">
            <div class="col-3 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${office.city}, ${office.country}</h5>
                        <p class="card-text">Tel: ${office.phone}</p>
                        <a type="button" class="btn btn-outline-primary"
                           href="${pageContext.request.contextPath}/office?code=${office.officeCode}"
                           role="button">
                            <i class="bi bi-eye-fill"></i> View
                        </a>
                        <c:choose>
                            <c:when test="${not empty office.employeeList && office.employeeList.size() > 0}">
                            <span class="d-inline-block"
                                  tabindex="0"
                                  data-bs-toggle="tooltip"
                                  title="Unable to delete the office with assigned employees.">
                                <button type="button"
                                        class="btn btn-outline-danger" disabled>
                                        <i class="bi bi-trash-fill"></i> Delete
                                </button>
                            </span>
                            </c:when>
                            <c:otherwise>
                                <a type="button"
                                   class="btn btn-outline-danger"
                                   href="${pageContext.request.contextPath}/delete-office?code=${office.officeCode}"
                                   role="button">
                                    <i class="bi bi-trash-fill"></i> Delete
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<c:choose>
    <c:when test="${param.status == 'ERROR'}">
        <jsp:include page="components/toast.jsp">
            <jsp:param name="status" value="error"/>
            <jsp:param name="message" value="${param.message}"/>
        </jsp:include>
    </c:when>
    <c:when test="${param.status == 'SUCCESS'}">
        <jsp:include page="components/toast.jsp">
            <jsp:param name="status" value="success"/>
            <jsp:param name="message" value="${param.message}"/>
        </jsp:include>
    </c:when>
</c:choose>

<jsp:include page="assets/bootstrap_js.html"/>
</body>
</html>
