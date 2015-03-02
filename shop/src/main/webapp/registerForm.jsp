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
	<%-- deny register for users who already logged in --%>
	<c:if test="${empty sessionScope.login}">
		<div id="register">
			<c:choose>
				<%-- information message when registration executed without errors --%>
				<c:when test="${!empty sessionScope.registrationDone}">
					<div id="registrationDone">
						<c:out value="${sessionScope.registrationDone}" />
						<c:remove var="registrationDone" scope="session" />
					</div>
				</c:when>

				<c:otherwise>
					<%-- form for registering users in shop --%>
					<form action="<c:url value="/register"/>" method="post">
						<input type="hidden" name="register" value="register" />

						<%-- error massage when trying to register --%>
						<c:if test="${!empty sessionScope.error}">
							<div id="registerError">
								<c:out value="${sessionScope.error}" />
								<c:remove var="error" scope="session" />
							</div>
						</c:if>

						<br> <br>
						<div id="registerHeader">
							<b><fmt:message key="registration" bundle="${lang}" /></b>
						</div>

						<div id="registerLeft">
							<fmt:message key="login" bundle="${lang}" />
							*<br> <input type="text" name="login" /><br>
							<fmt:message key="loginpass" bundle="${lang}" />
							*<br> <input type="password" name="pass1" /><br>
							<fmt:message key="loginpass" bundle="${lang}" />
							*<br> <input type="password" name="pass2" /><br>
							<fmt:message key="fname" bundle="${lang}" />
							*<br> <input type="text" name="fname" /><br>
							<fmt:message key="lname" bundle="${lang}" />
							*<br> <input type="text" name="lname" /><br>
							<fmt:message key="email" bundle="${lang}" />
							*<br> <input type="text" name="email" /><br>
							<fmt:message key="account" bundle="${lang}" />
							*<br> <input type="text" name="account" />
						</div>

						<div id="registerRight">
							<fmt:message key="age" bundle="${lang}" />
							*<br> <select name="age">
								<option value="18" selected>18</option>
								<c:forEach var="i" begin="19" end="60" step="1">
									<option value="${i}"><c:out value="${i}" /></option>
								</c:forEach>
							</select><br>
							<fmt:message key="gender" bundle="${lang}" />
							*<br> <input type="radio" name="gender" tabindex=""
								value="true" checked="checked">
							<fmt:message key="genderM" bundle="${lang}" />
							<input type="radio" name="gender" tabindex="" value="false">
							<fmt:message key="genderF" bundle="${lang}" />
							<br>
							<fmt:message key="zip" bundle="${lang}" />
							*<br> <input type="text" name="zip" /><br>
							<fmt:message key="state" bundle="${lang}" />
							*<br> <input type="text" name="state" /><br>
							<fmt:message key="city" bundle="${lang}" />
							*<br> <input type="text" name="city" /><br>
							<fmt:message key="street" bundle="${lang}" />
							*<br> <input type="text" name="street" /><br>
							<fmt:message key="phone" bundle="${lang}" />
							*<br> <input type="text" name="phone" />
						</div>

						<div id="registerSubmit">
							<input type="submit" name="register"
								value=<fmt:message key="create" bundle="${lang}" />>
						</div>
					</form>
					<%-- action="<c:url value="/register"/>" method="post" --%>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<%-- test="${empty sessionScope.login} --%>
</div>