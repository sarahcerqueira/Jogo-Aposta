package Controler;

import java.sql.SQLException;
import Model.BancoDados;

public class ControlerJogador {
	
	private BancoDados banco;

	
	
	public int cadastrarJogador(String nome, String telefone) throws SQLException {
		return this.banco.cadastrarJogador(nome, telefone);
	}
	
	public boolean loginJogador(String telefone) throws SQLException {
		return this.banco.loginJogador(telefone);
	}
			
	public void getConcursos(int id) {}
	
	public void apostar() {}

}
