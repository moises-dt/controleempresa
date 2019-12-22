package connection;

/*
 * DataBaseConnection.java
 *
 * Created on 30 de Novembro de 2007, 15:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



/**
 *
 * @author Administrator
 */
import exception.ApplicationException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DataBaseConnection{
    
    /** Creates a new instance of DataBaseConnection */
    public abstract Connection getConnection() throws SQLException;

    protected void close(Connection con) throws ApplicationException {
		this.close(con, null, null);
    }

	protected void close(Connection con, Statement stmt) throws ApplicationException {
		this.close(con, stmt, null);
	}

	protected void close(Statement stmt, ResultSet rs) throws ApplicationException {
		this.close(null, stmt, rs);
	}

	protected void close(Connection con, Statement stmt, ResultSet rs)
			throws ApplicationException {
		try {

			if (rs != null) {
				rs.close();
			}

		} catch (SQLException e) {
			throw new ApplicationException("problema ao fechar ResultSet: ["
					+ e.getMessage() + "].", e);
		}

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new ApplicationException("problema ao fechar Statement: ["
					+ e.getMessage() + "].", e);
		}

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new ApplicationException("problema ao fechar Connection: ["
					+ e.getMessage() + "].", e);
		}
	}
    
    
}
