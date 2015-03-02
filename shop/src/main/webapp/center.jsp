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

<div id="center">
	<c:choose>
		<%-- check if user tried to open not existed page, 
		or accidentally fell to not existed page  --%>
		<c:when test="${!empty sessionScope.error404}">
			<h1>${sessionScope.error404}</h1>
			<c:remove var="error404" scope="session" />
		</c:when>

		<c:otherwise>
			<%-- print products information --%>
			<c:forEach items="${products}" var="product">
				<div class="products">
					<div class="productLeft">
						<div class="productImage">
							<c:set var="curImageUrl" scope="session"
								value="/img/products/categories/${product.category_id}/${product.image}.jpg" />
							<img src="<c:url value='${curImageUrl}'/>" width="100"
								height="100" />
						</div>

						<div class="productPrice">
							<b><c:out value="${product.price}"></c:out></b>
						</div>

						<%-- add to basket products just for logged in users --%>
						<c:if test="${!empty sessionScope.login}">
							<form action="<c:url value="/addToBasket"/>" method="post">
								<input type="hidden" name="productId" value="${product.id}">
								<input type="hidden" name="productName" value="${product.name}">
								<input type="hidden" name="paginationCurPage"
									value="${param.page}"> <select size="1"
									name="productQuantity">
									<option value="0"><fmt:message key="choose"
											bundle="${lang}" /></option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select> <input type="submit"
									value=<fmt:message key="add" bundle="${lang}" />>
							</form>
						</c:if>
					</div>
					<%-- class="productLeft" --%>

					<div class="productName">
						<c:out value="${product.name}"></c:out>
					</div>

					<div class="productDescription">
						<c:out value="${product.description}"></c:out>
					</div>
				</div>
				<%-- class="products" --%>
			</c:forEach>
			<%-- items="${products}" var="product" --%>

			<%-- pagination for printed products --%>
			<div id="productsPagination">
				<%-- displaying page numbers. The when condition does not display a link for the current page --%>
				<table id="pagination">
					<tr>
						<%-- displaying previous link except first page --%>
						<c:if test="${currentPage != 1}">
							<td><a href="?page=${currentPage - 1}">&lt;</a></td>
						</c:if>

						<c:forEach begin="1" end="${numOfPages}" var="i">
							<c:choose>
								<c:when test="${currentPage eq i}">
									<td>${i}</td>
								</c:when>
								<c:otherwise>
									<td><a href="?page=${i}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<%-- displaying next link except last page --%>
						<c:if test="${currentPage lt numOfPages}">
							<td><a href="?page=${currentPage + 1}">&gt;</a></td>
						</c:if>
					</tr>
				</table>
			</div>
			<br>
			<br>
		</c:otherwise>
	</c:choose>
</div>