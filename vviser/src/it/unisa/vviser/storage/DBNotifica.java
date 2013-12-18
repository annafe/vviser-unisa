package it.unisa.vviser.storage;

import it.unisa.vviser.entity.Notifica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBNotifica {
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public DBNotifica(){}
	
	// METODI DI AGGIUNTA
	
	public boolean addNotifica(Notifica e) throws SQLException{
		conn = DBConnectionPool.getConnection();
		try{
			String queryParam = "INSERT INTO "+DBNames.TABLE_NOTIFICA+
					" ("+DBNames.ATTR_NOTIFICA_TIPO+","+DBNames.ATTR_NOTIFICA_MESSAGGIO+","+
					DBNames.ATTR_NOTIFICA_MITTENTE+","+DBNames.ATTR_NOTIFICA_DESTINATARIO+")"+
					" VALUES ("+e.getTipo()+","+e.getMessaggio()+","+e.getMittente()+","+
					e.getDestinatario()+");";
			
			pstm = conn.prepareStatement(queryParam);
			
			ResultSet toR = pstm.executeQuery();
			if (toR!=null)
				return true;
			else return false;
		}finally{
			pstm.close();
            DBConnectionPool.releaseConnection(conn);
		}
		
	}	
	
}
