<%--
  Created by IntelliJ IDEA.
  User: 10236
  Date: 2019/8/25
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<pg:pager url="/pagTaglib/page.jsp" maxIndexPages="5" export="currentPageNumber=pageNumber"
          items="1000" maxPageItems="10">
    <pg:first>
        <a href="${pageUrl}">首页</a>
    </pg:first>
    <pg:prev>
        <a href="${pageUrl}">上页</a>
    </pg:prev>
    <pg:pages>
        <c:if test="${pageNumber eq currentPageNumber}">
            ${pageNumber}
        </c:if>
        <c:if test="${not (pageNumber eq currentPageNumber)}">
            <a href="${pageUrl}">${pageNumber}</a>
        </c:if>
    </pg:pages>
    <pg:next>
        <a href="${pageUrl}">下页</a>
    </pg:next>
    <pg:last>
        <a href="${pageUrl}">末页</a>
    </pg:last>
</pg:pager>
</body>
</html>
