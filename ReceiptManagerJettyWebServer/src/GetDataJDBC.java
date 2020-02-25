

/**
 * Created by simonkent on 24/01/2017.
 *
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 A simple Servlet that demonstrates how a servlet works.
 */
public class GetDataJDBC extends HttpServlet
{
    private static final Properties properties = new Properties();

    private static String IP = "localhost:3306";
    private static String DB = "recieptmanager";
    private static String USERNAME = "root";
    private static String PASSWORD = "Guitar8.";

    

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        httpServletResponse.setContentType("text/plain");
        PrintWriter out = httpServletResponse.getWriter();
        //out.println(httpServletRequest.getPathInfo());
        out.println(pullDataFromDatabase());
        out.close();
    }

    public String pullDataFromDatabase() {

        String returnString = "";
            try {
                //Statement stmt;
                ResultSet rs;

                //Register the JDBC driver for MySQL
                //Class.forName("com.mysql.cj.jdbc.Driver");
                Class.forName("com.mysql.jdbc.Driver");
                
                String url = "jdbc:mysql://" + IP + "/" + DB +"?useSSL=false";
                Logger.getLogger(this.getClass().getName()).info("Connecting to url");

                Connection con = DriverManager.getConnection(url, USERNAME, PASSWORD);
                Statement select = con.createStatement();
                String tableName = "ForeignRec";
                // Execute a query
                rs = select.executeQuery("SELECT * FROM " + tableName);

                while (rs.next()) {
                    String name = rs.getString("Translation");
                    returnString += name;
                }
            } catch (ClassNotFoundException e) {
                returnString = "Something went wrong!" + e;
            } catch (SQLException e) {
                returnString = "Something went wrong with database: ";
                returnString += e.getLocalizedMessage();
            }

        return returnString;
    }

}

