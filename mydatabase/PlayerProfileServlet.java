// RPG Playerリスト
// Servlet-JSP間をArryaListで渡す

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class PlayerProfileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        int playerId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("message", "Hello player " + playerId);

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
             ("SELECT * FROM players LEFT JOIN jobs ON jobs.id = players.jobId WHERE players.id = ?")) {
               
            statment.setInt(1, playerId);
            ResultSet results = statment.executeQuery();

            while (results.next()) {

                String id = results.getString("id");
                request.setAttribute("id", id);

                String name = results.getString("name");
                request.setAttribute("name", name);

                String level = results.getString("level");
                request.setAttribute("level", level);

                String jobId = results.getString("jobId");
                request.setAttribute("jobId", jobId);

                String jobName = results.getString("jobName");
                request.setAttribute("jobName", jobName);

            }

        } catch (Exception e) {
            request.setAttribute("message", "Exception:" + e.getMessage());
        }

        String view = "/WEB-INF/views/playerProfile.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
