package Controler;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;

import Model.ConexaoPostgres;

public class Main {
	

	public static void main(String[] args)  {
		
		// TODO Auto-generated method stub
		/*
			Administrador adm = new Administrador("127.0.0.1",
					"5432", "jogo", "postgres", "admin");
			
			int i = 0;
			
			try {
				
				adm.conectarBancoDados();
				System.out.println("Banco de dados conectado com sucesso!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				//i = adm.cadastrarUsuario("Cesa", "cesa", "1234", true);
				//i = adm.removerUsuario(1);
				//i = adm.cadastrarJogador("Matinho", "9999999999999");
				//i= adm.removerJogador("9999999999999");
				System.out.println(adm.login("1234", "esa"));

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.print(i+"\n");} */
			
	
		
		//**************************************************************************************
		
		
		
		//Declara��o de v�riaveis
		int porta = 1010;		
		Servidor s = null;
		
		
		//Tenta iniciar o servidor, se o servidor n�o for iniciado o programa � encerrado.
		try {
			s = new Servidor(porta);
			System.out.println("Servicor iniciado!");
			
		} catch (IOException e) {
			System.out.println("Erro ao inciar o servidor!");
			e.printStackTrace();
			System.exit(0);
		}
		
		
		//Aceita clientes e cria uma thread para eles.
		while(true) {
			
			try {
				Socket c = s.aceitarCliente();
				
				//Trata conex�o do cliente com o servidor
				Cliente cliente = new Cliente(c);
				
				Thread t = new Thread(cliente);
				
				t.start();
				
				
			} catch (IOException e) {
				System.out.println("Conex�o com cliente perdida");
				e.printStackTrace();
			}
			
		}
			
		
		
		/*
		Scanner scanner = new Scanner(cliente.getInputStream());
			
		while (scanner.hasNextLine()) {
			    System.out.println(scanner.nextLine());
			}
		
		scanner.close();
		s.fecharPorta();
		cliente.close(); */
		
		
		
		
		// TODO Auto-generated method stub
	/*	
		Administrador adm = new Administrador("127.0.0.1",
				"5432", "jogo", "postgres", "admin");
		
		int i = 0;
		
		try {
			
			adm.conectarBancoDados();
			System.out.println("Banco de dados conectado com sucesso!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			//i = adm.cadastrarUsuario("Jhonny", "ladrao", "1234", true);
			//i = adm.removerUsuario(1);
			//i = adm.cadastrarJogador("Matinho", "9999999999999");
			i= adm.removerJogador("9999999999999");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.print(i+"\n"); */
		
		
	
	}

}
