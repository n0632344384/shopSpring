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

<div id="sidebarRight">
	<div id="setLang">
		<%-- set current language interface --%>
		<form action="<c:url value="/setLang"/>" method="post">
			<input type="hidden" name="curUrl"
				value=${requestScope['javax.servlet.forward.servlet_path']} /> <input
				type="hidden" name="curUrlParam"
				value=${requestScope['javax.servlet.forward.query_string']} />
			<fmt:message key="shopLang" bundle="${lang}" />
			<br> <select size="1" name="selectedLang">
				<option value="ru"
					${((empty sessionScope.curShopLang) and (pageContext.request.locale.language eq 'ru')) 
						or sessionScope.curShopLang eq 'ru' ? 'selected' : ''}><fmt:message
						key="shopLangRu" bundle="${lang}" /></option>
				<option value="en"
					${((empty sessionScope.curShopLang) and (pageContext.request.locale.language eq 'en')) 
						or sessionScope.curShopLang eq 'en'  ? 'selected' : ''}><fmt:message
						key="shopLangEn" bundle="${lang}" /></option>
			</select><br> <input type="submit" name="setLang"
				value=<fmt:message key="update" bundle="${lang}" />>
		</form>
	</div>

	<c:choose>
		<%-- when user logged in --%>
		<c:when test="${!empty sessionScope.login}">
			<%-- print user info --%>
			<div id="userInfo">
				<fmt:message key="login" bundle="${lang}" />
				:
				<c:out value=" ${sessionScope.firstname}" />
				<br>
				<fmt:message key="role" bundle="${lang}" />
				:
				<c:out value=" ${sessionScope.role}" />
				<br>
				<form action="<c:url value=""/>" method="post">
					<input type="hidden" name="paginationCurPage" value="${param.page}">
					<input type="submit" name="logout"
						value=<fmt:message key="logout" bundle="${lang}" />>
				</form>
			</div>

			<%-- print basket if not empty --%>
			<c:if test="${!empty sessionScope.basket}">
				<div id="basket">
					<fmt:message key="shopBasket" bundle="${lang}" />
					<table id="pagination">
						<tr>
							<td><fmt:message key="productId" bundle="${lang}" /></td>
							<td><fmt:message key="productName" bundle="${lang}" /></td>
							<td></td>
							<td></td>
						</tr>

						<c:forEach items="${basket}" var="basket">
							<tr>
								<td><c:out value="${basket.productId}"></c:out></td>
								<td><c:out value="${basket.productName}"></c:out></td>
								<td><c:out value="${basket.productQuantity}"></c:out></td>

								<c:if test="${not empty param.page}">
									<c:set var="paginCurPage" value=";page=${param.page}"></c:set>
								</c:if>

								<td><a
									href="/shop/delFromBasket?productId=<c:out value="${basket.productId}"></c:out><c:out value="${paginCurPage}"></c:out>">X</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</c:when>
		<%-- test="${!empty sessionScope.login}" --%>

		<%-- rest cases for (when user logged in) --%>
		<c:otherwise>
			<div id="login">
				<%-- error massage when trying to enter to the shop --%>
				<c:if test="${!empty sessionScope.error}">
					<div id="loginError">
						<c:out value="${sessionScope.error}" />
						<c:remove var="error" scope="session" />
					</div>
				</c:if>
				<br>

				<%-- form to enter users to shop --%>
				<form action="<c:url value=""/>" method="post">
					<input type="hidden" name="paginationCurPage" value="${param.page}">
					<fmt:message key="login" bundle="${lang}" />
					<br> <input type="text" name="login" /><br>
					<fmt:message key="loginpass" bundle="${lang}" />
					<br> <input type="password" name="pass" /><br> <input
						type="submit" name="entrance"
						value=<fmt:message key="entrance" bundle="${lang}" />>
				</form>

				<%-- form to register users in shop --%>
				<form action="<c:url value="/register"/>" method="post">
					<input type="submit"
						value=<fmt:message key="registration" bundle="${lang}" />>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
</div>