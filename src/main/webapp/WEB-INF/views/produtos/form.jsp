<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books of Java, Android, iPhone, Ruby, PHP and much more</title>
</head>
<body>
	<form:form  action="${s:mvcUrl('PC#save').build()}" 
				method="post"
				commandName="product">
		<div>
			<label>Titulo</label>
			<form:input path="title"/>
			<form:errors path="title"/>
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea path="description" rows="10" cols="20"/> 	
			<form:errors path="description"/>
		</div>
		<div>
			<label>Páginas</label>	
			<form:input path="pages"/>
			<form:errors path="pages"/>
		</div>
		<div>
			<label>Launch date</label>	
			<form:input path="lauchDate"/>
			<form:errors path="lauchDate"/>
		</div>
		<c:forEach items="${types}" var="priceType" varStatus="status">
		<div>
			<label>${priceType}</label>	
			<form:input path="prices[${status.index}].value"/>
			<form:hidden path="prices[${status.index}].type" value="${priceType}"/>
		</div>
		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form:form>
</body>
</html>