<%--
  Created by IntelliJ IDEA.
  User: 10236
  Date: 2019/8/25
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc.jsp"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <pg:pager url="${param['url']}"
              maxIndexPages="5"
              export="currentPageNumber=pageNumber"
              items="${param['count']}"
              maxPageItems="${param['pagesize']}">
        <tr>
            <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong>${vo.count}</strong> 条记录，当前第<strong>${vo.currentPage}</strong> 页，共 <strong>${vo.pageCount}</strong> 页</span></div></td>
            <td width="67%" align=right vAlign="center" noWrap>
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
            </td>
        </tr>
    </pg:pager>
</table>
