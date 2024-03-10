<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String code = (String) request.getAttribute("gen-hash");
    String hash = (String) request.getAttribute("gen-code");
%>
<h1>Інверсія управління у веб-проєкті</h1>
<p>
    Передача управління життєвим циклом (CRUD) об'єктів до спеціального
    модуля. Цей модуль потребує окремого встановлення.
    Серед варіантів: Spring, Guice
</p>
<p>
    Управління життєвим циклом об'єктів на пряму пов'язане з
    втручанням у життєвий цикл веб-проєкту. Для правильного
    налаштування необхідно знати його етапи.
</p>
<ul>
    <li>
        Створення контексту - запуск проєкту, після чого його
        частина знаходиться у постійній роботі
    </li>
    <li>
        Ланцюг фільтрів (~Middleware) - проходження запиту через
        об'єкти-обробники, кожен з яких може або продовжити ланцюг,
        або зупинити його.
    </li>
    <li>
        Створення сервлетів та формування HTML з JSP
    </li>
</ul>
<p>
    Встановлюємо залежності: guice та guice-servlet.
    Редагуємо файл-налаштування серверу web.xml.
    Створюємо конфігуратор та модулі до нього.
    Підписуємо конфігуратор до слухання контексту.
    Налаштовуємо модуль маршрутизації.
    З усіх сервлетів знімаємо анотацію "WebServlet" і
    додаємо анотацію Singleton
</p>
<p>code:
    <code>
        <%=code%>
    </code>
</p>
<p>hash:
    <code>
        <%=hash%>
    </code>
</p>