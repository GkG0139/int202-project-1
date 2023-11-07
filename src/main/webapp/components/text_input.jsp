<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label for="${param.id}" class="form-label">${param.label}</label>
<input class="form-control"
       type="${empty param.type ? 'text' : param.type}"
       name="${param.name}"
       id="${param.id}"
${param.required == 'true' ? 'required' : ''}>
<c:if test="${param.required  == 'true'}">
    <div id="${param.id}Required" class="form-text">
        *Required
    </div>
</c:if>
