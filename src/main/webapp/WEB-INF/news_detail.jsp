<%@ page import="step.learning.entity.News" %>
<%@ page import="java.util.Objects" %>
<%@ page import="step.learning.entity.User" %>
<%@ page import="step.learning.entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.OffsetTime" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.ZoneId" %>
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
    SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat oldFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
    java.util.Date startOfDay = dateFormat.parse( dateFormat.format(new java.util.Date()) );

%>

<div class="row">
    <div class="col">
        <h1 data-editable="true" data-parameter="title"><%= title %>
        </h1>

        <% if (news != null) { %>
        <i><%=news.getCreateDt()%>
        </i><br/>
        <% if (canUpdate) { %>
        <div data-editable="true" data-parameter="spoiler"><%=news.getSpoiler()%>
        </div>
        <button class="btn-floating btn-large waves-effect waves-light indigo right"
                style="position: fixed; right: 5px; top: 15vh;"
                onclick="newsEditClick()"><i class="material-icons">edit</i></button>

        <% } %>
        <img alt="image" style="float: left; max-height: 25vh"
             src="<%=contextPath%>/upload/news/<%=news.getImageUrl()%>"/>
        <p data-editable="true" data-parameter="text" data-news-edit-id="<%=news.getId()%>"><%=news.getText()%>
        </p>
        <% } %>

<%-- Блок відображення коментарів --%>
<% for( Comment comment : comments ) { %>
    <p data-comment-id="<%=comment.getId().toString()%>">
        <%= comment.getCreateDt().before(startOfDay)
                ? oldFormat.format(comment.getCreateDt())
                : todayFormat.format(comment.getCreateDt()) %>
        <%=comment.getUser() == null ? "--" : comment.getUser().getName()%>
        <%=comment.getText()%>

    </p>
<% } %>

        <% if (user != null) { %><%-- Блок додавання коментаря --%>
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


        <%
            System.out.println(contextPath);
            List<News> otherNews = (List<News>) request.getAttribute("other_news");
        %>
    </div>
</div>
<h1>Недавні Новини</h1>

<% for (News n : otherNews) { %>
<div class="col s12 m7" style="opacity: <%= n.getDeleteDt() == null ? 1 : .5 %>">
    <div class="card horizontal">
        <div class="card-image" style="flex: 1; place-self: center;">
            <img src="<%=contextPath%>/upload/news/<%=n.getImageUrl()%>" alt="img"
                 style="max-height: 25vh; margin: auto"/>
        </div>
        <div class="card-stacked" style="flex: 3">
            <div class="card-content">
                <h5><%=n.getTitle()%>
                </h5>
                <p><%=dateFormat.format(n.getCreateDt())%>
                </p>
                <small>
                    <%=n.getSpoiler()%>
                </small>
            </div>
            <div class="card-action">
                <% if (Objects.equals(request.getAttribute("can-delete"), true)) {
                    if (n.getDeleteDt() == null) { %>
                <a href="#" class="right" data-news-id="<%=n.getId()%>"><i
                        class="material-icons prefix">delete_forever</i></a>
                <% } else { %>
                <a href="#" class="right" data-news-restore-id="<%=n.getId()%>"><i class="material-icons prefix">restore_from_trash</i></a>
                <% }
                } %>
                <a href="<%=contextPath%>/news/<%=n.getId()%>">читати детальніше...</a>
            </div>
        </div>
    </div>
</div>
<% } %>

Д.З. Прикласти посилання на репозиторій або архів підсумкового проєкту
(не забувати про скріншоти роботи проєкту)
Встановити AndroidStudio (https://developer.android.com/studio)