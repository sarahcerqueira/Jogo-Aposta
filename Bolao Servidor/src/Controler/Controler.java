package Controler;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.ConexaoPostgres;
import Model.BancoDados;

public class Controler {
	
	private BancoDados banco;
	
	public Controler(String local, String porta,
		    String banco, String usuario, String senha) throws ClassNotFoundException, SQLException{
		
		this.banco = new BancoDados(local, porta,
		    banco, usuario, senha);
		
		this.conectarBancoDados();
		
	}
	
	public void conectarBancoDados() throws ClassNotFoundException, SQLException {
		this.banco.conectarBancoDados();
	}
	
	public int cadastrarUsuario(String nome, String usuario, String senha,
			boolean adm) throws SQLException {
		return this.banco.cadastrarUsuario(nome, usuario, senha, adm);
	}
	
	public int removerUsuario(int id) throws SQLException {
		return this.banco.removerUsuario(id);
	}
	
	
	public int removerJogador(String telefone) throws SQLException {
		return this.removerJogador(telefone);
	}
	
	public String login( String username, String senha) throws SQLException {
		return this.banco.login(username, senha);
	}
	

	public int cadastrarJogador(String nome, String telefone) throws SQLException {
		return this.banco.cadastrarJogador(nome, telefone);
	}
	
	public boolean loginJogador(String telefone) throws SQLException {
		return this.banco.loginJogador(telefone);
	}
	
	public int cadastrarConcurso(int id, String datai, String horai, String dataf, String horaf ) throws SQLException {
		return this.banco.cadastrarConcurso(id, datai, horai, dataf, horaf);
	}

	public void getVendedor() {}
	
	public void getUsuario() {}
		
	

}
