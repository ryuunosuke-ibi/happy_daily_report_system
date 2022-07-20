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
<label for="${AttributeConst.BUS_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.BUS_DATE.getValue()}" id="${AttributeConst.BUS_DATE.getValue()}" value="<fmt:formatDate value='${businessDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label>氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label>顧客名</label><br />
<c:out value="${sessionScope.login_customer.name}" />
<input type="text" name="${AttributeConst.CUS_NAME.getValue()}" id="${AttributeConst.CUS_NAME.getValue()}" value="${customer.name}" />
<br /><br />

<label for="${AttributeConst.REP_TITLE.getValue()}">商談名称</label><br />
<input type="text" name="${AttributeConst.BUS_TITLE.getValue()}" id="${AttributeConst.BUS_TITLE.getValue()}" value="${business.title}" />
<br /><br />

<label for="${AttributeConst.BUS_CONTENT.getValue()}">内容</label><br />
<textarea  name="${AttributeConst.BUS_CONTENT.getValue()}" id="${AttributeConst.BUS_CONTENT.getValue()}" rows="10" cols="50">${business.content}</textarea>
<br /><br />
<input type="hidden" name="${AttributeConst.BUS_ID.getValue()}" value="${business.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>