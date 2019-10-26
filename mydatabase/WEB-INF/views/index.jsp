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
        
        <%
        ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, 
        String>>)request.getAttribute("rows");
        %>
        <table border="1">
        <tr><th>id</th><th>name</th><th>level</th><th>jobName</th></tr>
        <%
        for (HashMap<String, String> columns : rows) {
        %>
          <tr>
            <td><%= columns.get("id") %></td>
            <td><%= columns.get("name") %></td>
            <td><%= columns.get("jobName") %></td>
            <td><%= columns.get("level") %></td>
            <!-- <td><%= columns.get("jobId") %></td> -->
          </tr>
        <% } %>
        </table>
    </body>
</html>
