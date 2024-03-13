<%@ page import="step.learning.entity.News" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    News news = (News) request.getAttribute("news_detail");
    String title = news == null
        ? "Запитаної новини не існує"
        : news.getTitle() ;
%>
<h1><%= title %></h1>
Д.З. На сторінці з новиною (детальний перегляд) додати блок
з переліком трьох останніх новин. Клік по них також переводить
до їх детального перегляду. Переконатись, що поточна новина,
яка переглядається на сторінці, не входить до блоку переліку
(три новини окрім даної).