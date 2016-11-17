<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title" /></title>
</head>
<body>

    <section id="header">
        <tiles:insertAttribute name="header" />
    </section>
    <section id="body">
        <tiles:insertAttribute name="body" />
    </section>
    <section id="footer">
        <tiles:insertAttribute name="footer" />
    </section>

</body>
</html>
