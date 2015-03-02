<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- setting locale --%>
<c:if test="${empty sessionScope.curShopLang}">
	<fmt:setLocale value="ru" />
	<fmt:setBundle basename="config" var="lang" />
</c:if>
<c:if test="${!empty sessionScope.curShopLang}">
	<fmt:setLocale value="${sessionScope.curShopLang}" />
	<fmt:setBundle basename="config" var="lang" />
</c:if>

<div id="sidebarLeft">
	<%-- menu mandator --%>
	<c:if test="${sessionScope.role == 2}">
		<div id="menuMandator">
			<a href="/shop/mandator?mandator=addProduct"><fmt:message
					key="addproduct" bundle="${lang}" /></a><br> <a
				href="/shop/mandator?mandator=updateProduct"><fmt:message
					key="updateproduct" bundle="${lang}" /></a>
		</div>
	</c:if>

	<%-- main menu --%>
	<div id="menu">
		<a href="/shop"><fmt:message key="menuhome" bundle="${lang}" /></a>
	</div>
</div>