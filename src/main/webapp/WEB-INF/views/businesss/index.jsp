<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actBus" value="${ForwardConst.ACT_BUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>商談　一覧</h2>
        <table id="business_list">
            <tbody>
                <tr>

                    <th class="business_name">氏名</th>

                    <th class="business_date">日付</th>
                    <th class="business_title">タイトル</th>
                    <th class="business_action">操作</th>
                </tr>
                <c:forEach var="business" items="${businesss}" varStatus="status">
                    <fmt:parseDate value="${business.businessDate}" pattern="yyyy-MM-dd" var="businessDay" type="date" />

                    <tr class="row${status.count % 2}">

                        <td class="business_name"><c:out value="${business.employee.name}" /></td>

                        <td class="business_date"><fmt:formatDate value='${businessDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="business_title">${business.title}</td>
                        <td class="business_action"><a href="<c:url value='?action=${actBus}&command=${commShow}&id=${business.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${businesss_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((businesss_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actBus}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actBus}&command=${commNew}' />">商談の登録</a></p>

    </c:param>
</c:import>