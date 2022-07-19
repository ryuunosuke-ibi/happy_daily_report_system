<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>id : ${customer.id} の顧客情報 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>顧客番号</th>
                    <td><c:out value="${customer.code}" /></td>
                </tr>
                <tr>
                    <th>顧客名</th>
                    <td><c:out value="${customer.name}" /></td>
                </tr>
                <tr>
                    <th>電話番号</th>
                    <td><c:out value="${customer.phone_number}" /></td>
                </tr>
                <tr>
                    <th>メールアドレス</th>
                    <td><c:out value="${customer.mail_adress}" /></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${customer.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${customer.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="<c:url value='?action=${actCus}&command=${commEdit}&id=${customer.id}' />">この顧客情報を編集する</a>
        </p>

        <p>
            <a href="<c:url value='?action=${actCus}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>