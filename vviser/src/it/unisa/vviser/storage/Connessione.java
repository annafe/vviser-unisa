package it.unisa.vviser.storage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.beans.*;

public class Connessione {


protected String DRIVER = "com.mysql.jdbc.Driver";
protected String url = "jdbc:mysql://localhost/vviser";
protected String user = "root";
protected String psw = "";

/**
DOCUMENTAZIONE:
- 
- La classe serve a creare la connessione al DB
- La classe restituisce l'oggetto connecition
@author Eugenio Gigante
@version 1.0
*/

public Connection connetti(){

Connection con=null;

try
{                   
Class.forName(DRIVER);                 
con = DriverManager.getConnection(url,user,psw);
}
catch(SQLException e)
{
System.out.println(e.getMessage());
}
catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

return con;

}

/**
DOCUMENTAZIONE:
- Libera le risorse allocate
- Il metodo viene richiamato dandogli in ingresso la Connection, lo Statment e il ResultSet
@param con è l'oggetto Connection al DB
@param st è l'oggetto Stantement
@param rs è l'oggetto ResultSet
@return COSA RESTITUISCE
@throws Ex QUANDO VIENE LANCIATA?
*/

public void liberaRisorse(Connection con, Statement st, ResultSet rs){

try
{           
rs.close();           
st.close();
con.close();
}

catch(SQLException e)
{
System.out.println(e.getMessage());
}       

}



}

