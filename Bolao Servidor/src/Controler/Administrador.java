package Controler;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.ConexaoPostgres;
import Model.BancoDados;

public class Administrador {
	
	private BancoDados banco;
	
	public Administrador(String local, String porta,
		    String banco, String usuario, String senha){
		
		this.banco = new BancoDados(local, porta,
		    banco, usuario, senha);
		
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
	
	public int cadastrarJogador(String nome, String telefone) throws SQLException {
		return this.banco.cadastrarJogador(nome, telefone);
	}
	
	public int removerJogador(String telefone) throws SQLException {
		return this.removerJogador(telefone);
	}
	
	public boolean login(String senha, String username) throws SQLException {
		return this.banco.login(username, senha);
	}
	
	
	
	

}
