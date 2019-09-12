package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoDados {
	
	private ConexaoPostgres bancoDados;
	private int idDezena;
	
	public BancoDados(String local, String porta,
		    String banco, String usuario, String senha) {
		
		this.bancoDados = new ConexaoPostgres(local, porta,
			    banco, usuario, senha);
		
	}
	
	
	public void conectarBancoDados() throws ClassNotFoundException, SQLException {
		this.bancoDados.conectar();
	}
	
	public void disconectarBancoDados() throws SQLException {
		this.bancoDados.disconectar();
	}
	
	public int cadastrarConcurso(int id, String datai, String horai, String dataf, String horaf ) throws SQLException {
		
		return this.bancoDados.executarSql("INSERT INTO concurso(id, datainicio, horainicio, datafim, horafim) VALUES( "+id +",'"+
				java.sql.Date.valueOf(datai)+"','{"+ horai+"}','"+ java.sql.Date.valueOf(dataf)+"','{"+ java.sql.Time.valueOf(horaf)+"}')");
		
	}
	
	public int cadastrarUsuario(String nome, String usuario, String senha, boolean adm) throws SQLException{
	
		return this.bancoDados.executarSql("INSERT INTO usuario(nome, username, senha, admin) VALUES( '"+nome+"','"+
			 usuario+"','"+ senha+"',"+adm+")");
		
	} 
	
	public int removerUsuario(int id) throws SQLException {
		return this.bancoDados.executarSql("DELETE FROM usuario WHERE usuario.id = " + id );
	}
	
	
	public int cadastrarJogador(String nome, String telefone) throws SQLException {
		return this.bancoDados.executarSql("INSERT INTO jogador VALUES( '"+telefone+"','"+ nome+"')");
	}
	
	
	public int removerJogador(String telefone) throws SQLException {
		return this.bancoDados.executarSql("DELETE FROM jogador WHERE jogador.telefone = '" + telefone + "'" );
	}
	
	
	public String login(String username, String senha) throws SQLException {
		ResultSet r = this.bancoDados.buscar("SELECT * FROM usuario WHERE usuario.username= '"+username+"'");
		
		if(r.next()) {
			if(senha.equals(r.getString("senha"))) {
				return r.getString("admin");
			}
			
		}
		
		return "erro";
	}
	
	
	public boolean loginJogador(String telefone) throws SQLException {
		
		ResultSet r = this.bancoDados.buscar("SELECT telefone FROM jogador WHERE jogador.telefone = '"+telefone+"'");
		
		if(r.next()) {
			return true;
		}
		
		return false;
	}
	
	
	public ResultSet getVendedor() throws SQLException {
		
		return this.bancoDados.buscar("SELECT id, nome FROM usuario WHERE usuario.admin = false");
		
	}
	
	public ResultSet getConcurso() throws SQLException {
		return this.bancoDados.buscar("SELECT * FROM concurso ORDER BY datafim ASC");

	}
	
	
	public void cadastrarAposta(int concurso, String jogador, String usuario, String data, float preco, int d1,
			int d2, int d3, int d4, int d5, int d6, int d7, int d8, int d9, int d10) throws SQLException {
		
		
		this.bancoDados.executarSql("INSERT INTO aposta(concurso, jogador, usuario, data, preco, d1, d2, d3,"
				+ "d4, d5, d6, d7, d8, d9, d10)VALUES( "
		+concurso+",'"+ jogador+ "','" +usuario+"','"+ data+"',"+ preco +","+d1+","
		+ d2 +","+ d3 +","+ d4+","+ d5 +"," +d6 +"," + d7 + "," + d8 
		+"," + d9 +"," + d10 +")");

		
	}
	
	
	

}
