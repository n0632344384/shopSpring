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

<div id="tableProd">
	<%-- information message when product removed without errors --%>
	<c:if test="${!empty sessionScope.delProductDone}">
		<div id="delProductDone">
			<c:out value="${sessionScope.delProductDone}" />
			<c:remove var="delProductDone" scope="session" />
		</div>
	</c:if>

	<%-- information message when product updated without errors --%>
	<c:if test="${!empty sessionScope.updateProductDone}">
		<div id="updateProductDone">
			<c:out value="${sessionScope.updateProductDone}" />
			<c:remove var="updateProductDone" scope="session" />
		</div>
	</c:if>
	<br>

	<%-- table of products --%>
	<table id="tableProducts">
		<c:forEach items="${products}" var="product">
			<tr>
				<td>
					<%-- form for updating and removing products --%>
					<form action="<c:url value="/mandator?mandator=updateProduct"/>"
						method="post">
						<fmt:message key="productId" bundle="${lang}" />
						<br> <input type="text" name="productId"
							value="${product.id}" readonly /> <br>
						<fmt:message key="productName" bundle="${lang}" />
						*<br>
						<textarea name="productName" cols="80" rows="1"
							style="resize: none">${product.name}</textarea>
						<br>
						<fmt:message key="productDescription" bundle="${lang}" />
						*<br>
						<textarea name="productDescription" cols="80" rows="5"
							style="resize: none">${product.description}</textarea>
						<br>
						<fmt:message key="productPrice" bundle="${lang}" />
						*<br> <input type="number" name="productPrice"
							value="${product.price}" /> <br>
						<fmt:message key="productImage" bundle="${lang}" />
						*<br> <input type="text" name="productImage"
							value="${product.image}" /> <br>
						<fmt:message key="productCategory" bundle="${lang}" />
						*<br> <select name="productCategory">
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}"
									${product.category_id eq category.id ? 'selected' : ''}>
									<c:out value="${category.name}" /></option>
							</c:forEach>
						</select><br> <input type="submit" name="update"
							value=<fmt:message key="update" bundle="${lang}" />><br>
						<input type="submit" name="delete"
							value=<fmt:message key="delete" bundle="${lang}" />>
					</form> <%-- action="<c:url value="/mandator?mandator=updateProduct"/>"	method="post" --%>
				</td>
			</tr>
		</c:forEach>
		<%-- items="${products}" var="product" --%>
	</table>
</div>