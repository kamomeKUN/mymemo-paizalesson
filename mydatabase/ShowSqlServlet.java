// JDBCで、データ呼び出し
// Servlet-JSP間をArryaList<HashMap<String, String>>で渡す

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ShowSqlServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("message", "Hello World!");
        
        String url = "";
        String user = "";
        String password = "";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try(Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement
        ("SELECT * FROM players p LEFT OUTER JOIN jobs j ON p.jobId = j.id WHERE p.id >= 7");
        
        ResultSet results = statement.executeQuery()) {
          
          ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
          
          while (results.next()) {
            HashMap<String, String> columns = new HashMap<String, String>();
            
            String id = results.getString("id");
            columns.put("id", id);
            
            String name = results.getString("name");
            columns.put("name", name);
            
            String level = results.getString("level");
            columns.put("level", level);
            
            String jobId = results.getString("jobId");
            columns.put("jobId", jobId);
            
            String jobName = results.getString("jobName");
            columns.put("jobName", jobName);
            
            rows.add(columns);
          }
          
          request.setAttribute("rows", rows);
          
        } catch(Exception e) {
          request.setAttribute("message", "Exception:" + e.getMessage());
        }

        String view = "/WEB-INF/views/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
