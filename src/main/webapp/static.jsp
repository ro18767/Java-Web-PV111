<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String contextPath = request.getContextPath() ;
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=contextPath%>/css/site.css"/>
</head>
<body>
<h1>Сайт працює у статичному режимі</h1>
<h2>Щось з БД...</h2>
<img src="<%=contextPath%>/img/Java_Logo.png" alt="img"/>

<script src="<%=contextPath%>/js/site.js"></script>
</body>
</html>
