<%@ page import="step.learning.entity.News" %>
<%@ page import="java.util.Objects" %>
<%@ page import="step.learning.entity.User" %>
<%@ page import="step.learning.entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String contextPath = request.getContextPath() ;
    User user = (User) request.getAttribute("auth-user");
    News news = (News) request.getAttribute("news_detail");
    Comment[] comments = (Comment[]) request.getAttribute("news_comments") ;
    String title = news == null
        ? "Запитаної новини не існує"
        : news.getTitle() ;

    boolean canUpdate = // (boolean) request.getAttribute("can-update"); -- (boolean)null -> Exception
            Objects.equals(true, request.getAttribute("can-update"));
%>
<h1 data-editable="true" data-parameter="title"><%= title %></h1>

<% if(news != null) { %>
<i><%=news.getCreateDt()%></i><br/>
<% if(canUpdate) { %>
    <div data-editable="true" data-parameter="spoiler"><%=news.getSpoiler()%></div>
    <button class="btn-floating btn-large waves-effect waves-light indigo right"
        style="position: fixed; right: 5px; top: 15vh;"
        onclick="newsEditClick()"><i class="material-icons">edit</i></button>

<% } %>
<img alt="image" style="float: left; max-height: 25vh"
     src="<%=contextPath%>/upload/news/<%=news.getImageUrl()%>"/>
<p data-editable="true" data-parameter="text" data-news-edit-id="<%=news.getId()%>"><%=news.getText()%></p>
<% } %>

<%-- Блок відображення коментарів --%>
<% for( Comment comment : comments ) { %>
    <p><%=comment.getText()%></p>
<% } %>

<% if(user != null) { %><%-- Блок додавання коментаря --%>
<div class="row">
    <div class="input-field col s6">
        <input placeholder="Текст коментаря" id="news-comment-text" type="text">
        <label for="news-comment-text">Коментар</label>
    </div>
</div>
<div class="row">
    <button id="news-comment-button" class="btn indigo">Опублікувати коментар</button>
</div>
<input type="hidden" id="news-comment-user-id" value="<%=user.getId()%>">
<% } %>