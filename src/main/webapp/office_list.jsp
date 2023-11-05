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
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">Office List</li>
        </ol>
    </nav>
    <hr>
    <div class="row">
        <c:forEach var="office" items="${offices}">
            <div class="col-3 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${office.city}, ${office.country}</h5>
                        <p class="card-text">Tel: ${office.phone}</p>
                        <button type="button" class="btn btn-outline-primary"
                                onclick="window.location='${pageContext.request.contextPath}/office?code=${office.officeCode}'">
                            <i class="bi bi-eye-fill"></i> View
                        </button>
                        <button type="button" class="btn btn-outline-danger">
                            <i class="bi bi-trash-fill"></i> Delete
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="assets/bootstrap_js.html"/>
</body>
</html>
