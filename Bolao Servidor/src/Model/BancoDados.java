package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoDados {
	
	private ConexaoPostgres bancoDados;
	
	public BancoDados(String local, String porta,
		    String banco, String usuario, String senha) {
		
		this.bancoDados = new ConexaoPostgres(local, porta,
			    banco, usuario, senha);
		
	}
	
	
	public void cadastrarUsuarios(int id, String nome, String usuario, String senha, boolean adm) {	
	}
	
	
	public void conectarBancoDados() throws ClassNotFoundException, SQLException {
		this.bancoDados.conectar();
	}
	
	public void disconectarBancoDados() throws SQLException {
		this.bancoDados.disconectar();
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
	
	
	public boolean login(String username, String senha) throws SQLException {
		ResultSet r = this.bancoDados.buscar("SELECT * FROM usuario WHERE usuario.username='"+username+"'");
		
		if(r.next()) {
			if(senha.equals(r.getString("senha"))) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
	

}
