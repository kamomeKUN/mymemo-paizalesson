// JDBCで、データーベース呼び出し
// Servlet単独

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ExecuteSqlServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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
             ("DELETE FROM players WHERE id BETWEEN 12 AND 13")) {
            
            int number = statment.executeUpdate();   

        } catch (Exception e) {
            request.setAttribute("exception", "Execute exception:" + e.getMessage());
        }

        String forward = "/show";
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);
    }
}