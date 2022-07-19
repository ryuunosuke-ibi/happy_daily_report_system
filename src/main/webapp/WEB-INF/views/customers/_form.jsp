<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="${AttributeConst.CUS_CODE.getValue()}">顧客番号</label><br />
<input type="text" name="${AttributeConst.CUS_CODE.getValue()}" id="${AttributeConst.CUS_CODE.getValue()}" value="${customer.code}" />
<br /><br />

<label for="${AttributeConst.CUS_NAME.getValue()}">顧客名</label><br />
<input type="text" name="${AttributeConst.CUS_NAME.getValue()}" id="${AttributeConst.CUS_NAME.getValue()}" value="${customer.name}" />
<br /><br />

<label for="${AttributeConst.CUS_PHONE_NUMBER.getValue()}">電話番号</label><br />
<input type="text" name="${AttributeConst.CUS_PHONE_NUMBER.getValue()}" id="${AttributeConst.CUS_PHONE_NUMBER.getValue()}" value="${customer.phone_number}" />
<br /><br />

<label for="${AttributeConst.CUS_MAIL_ADRESS.getValue()}">メールアドレス</label><br />
<input type="text" name="${AttributeConst.CUS_MAIL_ADRESS.getValue()}" id="${AttributeConst.CUS_MAIL_ADRESS.getValue()}" value="${customer.mail_adress}" />
<br /><br />


<br /><br />
<input type="hidden" name="${AttributeConst.CUS_ID.getValue()}" value="${customer.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>