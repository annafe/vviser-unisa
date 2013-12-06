import it.unisa.vviser.storage.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class Test {
	

	public static void main(String[] args) throws SQLException {
		
		Connection conn;
		Statement st=null;
		ResultSet rs=null;
		String query;
		conn = DBConnectionPool.getConnection();
		query="select * from utenti";
		st=conn.createStatement();
		rs=st.executeQuery(query);
		while (rs.next())
		{
			System.out.println(rs.getString("username"));
		}

	}

}
