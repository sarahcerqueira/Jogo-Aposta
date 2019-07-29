package Controler;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

import Model.TipoUsuario;

public class Cliente implements Runnable{
	
	private Socket cliente;
	private int maquina;		
	private int idUsuario;			//Quando um cliente se conecta é necessário saber o usuário(adm ou vendedor);
	private TipoUsuario tipo;		//O tipo de usuario vai definir se o cliente é um ADM, um jogador ou vendedor.
	private Scanner carta;
	private PrintWriter lapis;
	private Administrador adm;
	
	public Cliente(Socket c) {
		this.cliente = c;
		this.adm = new Administrador("127.0.0.1",
				"5432", "jogo", "postgres", "admin");

	}

	@Override
	public void run() {
		/*
		
		try {
			this.carta = new Scanner(this.cliente.getInputStream());
			
		} catch (IOException e) {
			System.out.println("Não foi possível ler do cliente!");
			e.printStackTrace();
			return;
		}
		
		try {
			lapis = new PrintWriter(this.cliente.getOutputStream());
		} catch (IOException e) {
			System.out.println("Não foi possível escrever para cliente!");
			e.printStackTrace();
			return;
		}
	
		
		int op = Integer.parseInt(this.lerDoCliente()); //recebe solicitação do cliente.
		
		if(op == 1){
			try {
				this.login();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.lapis.flush();
		
		if(lapis.)
		
		this.escreverParaCliente("ok");
		this.lapis.flush();
		
		System.out.println("Escrevi pro cliente");
		
		try {
			this.fecharConexao();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
		try {
			ObjectOutputStream ler = new ObjectOutputStream(this.cliente.getOutputStream());
			ler.flush();
			ler.writeObject("oi cliente");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		
	}
	
	private void fecharConexao() throws IOException {
		this.cliente.close();
	}
	
	private boolean temProxLinha() {
		return this.carta.hasNextLine();
	}
	
	private String lerDoCliente() {
		if(this.temProxLinha()) {
			return this.carta.nextLine();
			}
		
		return null;
	}
	
	private void escreverParaCliente(String mensagem) {
		this.lapis.println(mensagem);
	}
	
	
	private void login() throws SQLException {
		System.out.println("Solicitacao de login!");
		
		String username = this.lerDoCliente();
		System.out.println("Usuario "+ username);

		String senha = this.lerDoCliente();
		System.out.println("Senha "+ senha);

		boolean s = this.adm.login(senha, username);
		if(s) {
			this.escreverParaCliente("ok");
		}else {
			this.escreverParaCliente("erro");

		}
		
		
		
	}

}
