<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Office ${office.city}, ${office.country}</title>

    <jsp:include page="assets/bootstrap_css.html"/>
</head>
<body>
<jsp:include page="components/navbar.html"/>

<div class="container" style="margin-top: 100px;">
    <h1>Office ${office.city}, ${office.country} ::</h1>
    <div class="d-flex flex-row justify-content-between">
        <nav class="my-auto" aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/office-list">Office
                    List</a></li>
                <li class="breadcrumb-item active"
                    aria-current="page">Office ${office.city}, ${office.country}</li>
            </ol>
        </nav>
        <a class="btn btn-outline-primary"
           href="${pageContext.request.contextPath}/edit-office?code=${office.officeCode}" role="button">
            <i class="bi bi-pen"></i> Edit Office</a>
    </div>
    <hr>

    <div class="row">
        <div class="col-2 fw-bold">Tel:</div>
        <div class="col-10">${office.phone}</div>

        <div class="col-2 fw-bold">Address Line 1:</div>
        <div class="col-10">${office.addressLine1}</div>

        <div class="col-2 fw-bold">Address Line 2:</div>
        <div class="col-10">${office.addressLine2}</div>

        <div class="col-2 fw-bold">City:</div>
        <div class="col-10">${office.city}</div>

        <div class="col-2 fw-bold">State:</div>
        <div class="col-10">${office.state}</div>

        <div class="col-2 fw-bold">Country:</div>
        <div class="col-10">${office.country}</div>

        <div class="col-2 fw-bold">Postal Code:</div>
        <div class="col-10">${office.postalCode}</div>

        <div class="col-2 fw-bold">Territory:</div>
        <div class="col-10">${office.territory}</div>
    </div>

    <h4 class="mt-5">Employee List ::</h4>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">No.</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${office.employeeList}">
            <tr>
                <th scope="row">${employee.employeeNumber}</th>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="assets/bootstrap_js.html"/>
</body>
</html>
