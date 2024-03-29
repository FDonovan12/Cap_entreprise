<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sort-filter d-flex">
    ${label}
    <div class="ms-1 me-2 row ms-3">
        <c:set var="sortAsc" scope="request" value="sort=${sortable},asc"/>
        <a href="${jspUtils.getUrlFrom(currentUrl, currentQuery, sortAsc)}" data-my-sortable="${sortAsc}" class="list-group-item active">
            <i class="fa-solid fa-sort-up"></i>
        </a>
        <c:set var="sortDesc" scope="request" value="sort=${sortable},desc"/>
        <a href="${jspUtils.getUrlFrom(currentUrl, currentQuery, sortDesc)}" data-my-sortable="${sortDesc}" class="list-group-item active">
            <i class="fa-solid fa-sort-down"></i>
        </a>
    </div>
</div>