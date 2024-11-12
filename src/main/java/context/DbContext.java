package context;
import java.sql.*;

public class DbContext {
    
    private static final String serverName = "localhost";
    private static final String dbName = "ShopDB";
    private static final String portNumber = "1433";  
    private static final String userID = "sa";
    private static final String password = "sa";
    
    public static Connection getConnection()
    {
        Connection conn=null;
        try{ 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            
            conn = DriverManager.getConnection(url,userID,password);       
        }catch(Exception ex){
            System.out.println("Error:" + ex.toString());
        }
        return conn;
    }
    
    public static void main(String[] args)
    {
        System.out.println("Ket Qua ket noi:" + DbContext.getConnection());
    }
}
    