<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actBus" value="${ForwardConst.ACT_BUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>商談 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>担当者</th>
                    <td><c:out value="${business.employee}" /></td>
                </tr>
                 <tr>
                    <th>顧客名</th>
                    <td><c:out value="${business.customer}" /></td>
                </tr>
                <tr>
                    <th>商談日</th>
                    <fmt:parseDate value="${business.businessDate}" pattern="yyyy-MM-dd" var="businessDay" type="date" />
                    <td><fmt:formatDate value='${businessDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>商談名称</th>
                    <td><pre><c:out value="${business.title}" /></pre></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${business.content}" /></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${business.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${business.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>
            <p>
                <a href="<c:url value='?action=${actBus}&command=${commEdt}&id=${business.id}' />">この商談を編集する</a>
            </p>

        <p>
            <a href="<c:url value='?action=${actBus}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>