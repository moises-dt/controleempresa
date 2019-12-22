package connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionFactory {

    /**
     * Criar uma nova instancia do DataBaseConnectionFactory
     */
    public DataBaseConnectionFactory() {
    }
//  SQL Server
//  private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";            
//  private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=caixa";  
//  private static final String LOGIN = "sa";
//  private static final String PASSWORD = "12345678";

// PostgreSQL
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/controleempresa";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "123456";

//    Sybase 9
//    private static final String JDBC_DRIVER = "com.sybase.jdbc3.jdbc.SybDriver";
//    private static final String DATABASE_URL = "jdbc:sybase:Tds:192.168.0.103:6000"; //Areal
//    private static final String DATABASE_URL = "jdbc:sybase:Tds:192.168.100.250:6005"; //Sapucaia
//    private static final String DATABASE_URL = "jdbc:sybase:Tds:localhost:6001"; //Locahost
//    private static final String LOGIN = "externo";
//    private static final String PASSWORD = "externo";
    
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DATABASE_URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

//    public static void main(String[] args) throws Exception {
//        try {
//            Connection c = getConnection();
//            DatabaseMetaData meta = c.getMetaData();
//            System.out.println("Banco usado : " + meta.getDatabaseProductName());
//            System.out.println("Versão : " + meta.getDatabaseProductVersion());
//            System.out.println("Driver : " + meta.getDriverName() + " - "
//                    + meta.getDriverVersion());
//        } catch (SQLException sQLException) {
//            System.out.println(sQLException.getMessage());
//        }
//    }
}
