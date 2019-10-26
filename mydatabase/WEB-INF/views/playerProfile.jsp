<!-- ServletからArryaListでデータ渡し -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Java - paiza</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>
        <h1><%= request.getAttribute("message") %></h1>

        <p>名前：<%= request.getAttribute("name") %></p>
        <p>職業：<%= request.getAttribute("jobName") %></p>
        <p>レベル：<%= request.getAttribute("level") %></p>
        <p></p>
        <p><a href="playerlist">戻る</a></p>
    </body>
</html>