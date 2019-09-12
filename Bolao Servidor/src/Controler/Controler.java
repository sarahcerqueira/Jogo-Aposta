package Controler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.bolaodasortefacil.model.Aposta;
import com.example.bolaodasortefacil.model.Concursos;
import com.example.bolaodasortefacil.model.Jogador;

import Model.ConexaoPostgres;
import Model.BancoDados;


public class Controler {
	
	private BancoDados banco;
	private ArrayList<Concursos> concursosAtivos;
	
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
	
	public ResultSet getConcurso() throws SQLException {		
				
		return this.banco.getConcurso();
	}
	
	public ArrayList<Concursos> getConcursosAtivos() throws SQLException, ParseException{
		
		String datai, horai, dataf, horaf, id;
		ResultSet r = getConcurso();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yy");


		Date data1, data2, data;
		int hi, hf, mi, mf, h, m;
		
		this.concursosAtivos = new ArrayList<Concursos>();
		
				
		while(r.next()) {
			id = r.getString("id");
			datai = r.getString("datainicio");
			horai = r.getString("horainicio");
			dataf = r.getString("datafim");
			horaf = r.getString("horafim");
			
			data1 = new Date(format.parse(datai.replaceAll("-", "")).getTime());
			data2 = new Date(format.parse(dataf.replaceAll("-", "")).getTime());
			dataf = d.format(data2);
			
			//pegar Data e hora do sistema
			data = new Date();
			data = new Date(format.parse(format.format(data)).getTime()); //defini o horario como meia noite
			
			
			hi = Integer.parseInt(horai.substring(1,2));
			mi = Integer.parseInt(horai.substring(4,5));
			hf = Integer.parseInt(horaf.substring(1,2));
			mf = Integer.parseInt(horaf.substring(4,5));
			h = Integer.parseInt(hora.format(data).substring(0,1));
			m = Integer.parseInt(hora.format(data).substring(3,4));
			

			
			
			//Se está com data ativa
			if((data.equals(data1) || data.after(data1)) &&
					(data.before(data2) || data.equals(data2))) {
				
				//Se a data é a data incial
				if(data.equals(data1)){
					
					if(h > hi || ( h == hi && m>= mi )) {
						
						this.addConcursoAtivo(id, horaf.substring(1,6), dataf);
						
					} 
					
				} else if (data.equals(data2)){
					
					//5 minutos antes o concurso não está mais ativo
					
					if(h < hf || (h == hf && m < mf - 5) ) {
						this.addConcursoAtivo(id, horaf.substring(1,6), dataf);

						
					} 
					
				} else {
					this.addConcursoAtivo(id, horaf.substring(1,6), dataf);

				} 
				
			} 
			
			//System.out.print(i+"\n");

		}

		

	
		
		
		return this.concursosAtivos;
	}
	
	private void addConcursoAtivo(String id, String h, String d) {
		Concursos c = new Concursos(id, h, d);
		this.concursosAtivos.add(c);
		
	}
	
	public void apostar(Jogador j) throws NumberFormatException, SQLException{
		ArrayList<Aposta> lista = j.getAposta();
		
		for(Aposta a : lista) {
			int [] dezena = a.getDezenas();
		
			banco.cadastrarAposta(Integer.parseInt(a.getConcurso()), a.getTelefoneJogador(), a.getVendedor(), 
					a.getdata().toString(), a.getValor(), dezena[0],  dezena[1],  dezena[2], dezena[3],
					 dezena[4],  dezena[5],  dezena[6],  dezena[7],  dezena[8],  dezena[9]);
		}
	}
	

}
