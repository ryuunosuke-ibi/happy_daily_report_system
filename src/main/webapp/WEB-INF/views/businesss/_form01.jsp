<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<fmt:parseDate value="${business.businessDate}" pattern="yyyy-MM-dd" var="businessDay" type="date" />
<label for="${AttributeConst.BUS_DATE.getValue()}">商談日</label><br />
<input type="date" name="${AttributeConst.BUS_DATE.getValue()}" id="${AttributeConst.BUS_DATE.getValue()}" value="<fmt:formatDate value='${businessDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="${AttributeConst.BUS_EMPLOYEE.getValue()}">担当者</label><br />
<input type="text" name="${AttributeConst.BUS_EMPLOYEE.getValue()}" id="${AttributeConst.BUS_EMPLOYEE.getValue()}" value="${business.employee}" />
<br /><br />

<label for="${AttributeConst.BUS_CUSTOMER.getValue()}">顧客名</label><br />
<h4>顧客名は変更しないでください</h4>

<input type="text" name="${AttributeConst.BUS_CUSTOMER.getValue()}" id="${AttributeConst.BUS_CUSTOMER.getValue()}" value="${business.customer}" />
<br /><br />

<label for="${AttributeConst.BUS_TITLE.getValue()}">商談名称</label><br />
<input type="text" name="${AttributeConst.BUS_TITLE.getValue()}" id="${AttributeConst.BUS_TITLE.getValue()}" value="${business.title}" />
<br /><br />

<label for="${AttributeConst.BUS_CONTENT.getValue()}">内容</label><br />
<textarea  name="${AttributeConst.BUS_CONTENT.getValue()}" id="${AttributeConst.BUS_CONTENT.getValue()}" rows="10" cols="50">${business.content}</textarea>
<br /><br />
<input type="hidden" name="${AttributeConst.BUS_ID.getValue()}" value="${business.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>