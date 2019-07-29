package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class ConexaoPostgres {
	
	private static String driver;
	private static String usuario;
	private static String senha;
	private static String url ;
	private static String porta;
	private static String local;
	private static Connection c;
	private Statement statment;
	
	
	public ConexaoPostgres(String local, String porta,
		    String banco, String usuario, String senha){
		
		this.driver = new String ("org.postgresql.Driver");
		this.usuario = usuario;
		this.local = local;
		this.senha = senha;
		this.porta = porta;
		this.url = new String("jdbc:postgresql://"+ local +":" + porta +"/"+ banco);
		
	}
	
	 public void conectar() throws ClassNotFoundException, SQLException{
	  
	     Class.forName(this.driver);
	     this.c= DriverManager.getConnection(this.url, this.usuario, this.senha);
	     this.statment = this.c.createStatement();
	 }
	
	public void disconectar() throws SQLException {
		this.c.close();
	}
	
	public ResultSet buscar(String sql) throws SQLException {
		return this.statment.executeQuery(sql);
	}
	
	public int executarSql(String sql) throws SQLException {
		return this.statment.executeUpdate(sql);
	}

	

}
