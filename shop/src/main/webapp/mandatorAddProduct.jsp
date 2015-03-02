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

<div id="addProduct">
	<%-- form for adding products to shop --%>
	<form action="<c:url value="/mandator"/>" method="post">
		<input type="hidden" name="addProduct" value="addProduct" />

		<%-- error massage when trying to add product to shop --%>
		<c:if test="${!empty sessionScope.error}">
			<div id="addProductError">
				<c:out value="${sessionScope.error}" />
				<c:remove var="error" scope="session" />
			</div>
		</c:if>

		<%-- information message when adding product to shop executed without errors --%>
		<c:if test="${!empty sessionScope.addProductDone}">
			<div id="addProductDone">
				<c:out value="${sessionScope.addProductDone}" />
				<c:remove var="addProductDone" scope="session" />
			</div>
		</c:if>

		<br> <br> <b><fmt:message key="addNewProduct"
				bundle="${lang}" /></b><br> <br>
		<fmt:message key="productName" bundle="${lang}" />
		*<br> <input type="text" name="name" /><br>
		<fmt:message key="productDescription" bundle="${lang}" />
		*<br>
		<textarea name="description" cols="40" rows="5"></textarea>
		<br>
		<fmt:message key="productPrice" bundle="${lang}" />
		*<br> <input type="number" name="price" /><br>
		<fmt:message key="productImage" bundle="${lang}" />
		*<br> <input type="text" name="image" /><br>
		<fmt:message key="productCategory" bundle="${lang}" />
		*<br> <select name="category">
			<c:forEach items="${categories}" var="category">
				<option value="${category.id}"><c:out
						value="${category.name}" /></option>
			</c:forEach>
		</select><br> <br> <input type="submit" name="addProduct"
			value=<fmt:message key="add" bundle="${lang}" />>
	</form>
	<%-- action="<c:url value="/mandator"/>" method="post" --%>
</div>