<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title" /></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/site.css"/>"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/index.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>

    <script src="/resources/js/bootstrap.min.js"></script>
</head>
<body>

    <div class="wrapper">
        <section id="header">
            <tiles:insertAttribute name="header" />
        </section>
        <section id="body">
            <tiles:insertAttribute name="body" />
        </section>
        <section id="footer">
            <tiles:insertAttribute name="footer" />
        </section>
    </div>
</body>
</html>
