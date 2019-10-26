// JDBCで、データーベース呼び出し
// Servlet単独

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SelectSqlServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Java - paiza</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<p>Hello World!</p>");

        String url = "";
        String user = "";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statment = connection.prepareStatement
             ("SELECT name, level FROM players")) {
               
            out.println("<p>接続成功！</p>");
            ResultSet results = statment.executeQuery();
            while(results.next()) {
              String name = results.getString("name");
              int level = results.getInt("level");
              out.println("<p>" + name + ", " + level + "</p>");
            }

        } catch (Exception e) {
            out.println("Exception" + e.getMessage());
        }

        out.println("</body>");
        out.println("</html>");
    }
}