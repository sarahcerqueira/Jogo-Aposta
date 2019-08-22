package Controler;

import java.io.IOException;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;

import Model.ConexaoPostgres;
import Model.Servidor;

public class Main {
	

	public static void main(String[] args)  {
		
		// TODO Auto-generated method stub
		
		Controler adm;
		try {
			adm = new Controler("127.0.0.1",
						"5432", "jogo", "postgres", "admin");
			
			int i = 0;
			
			//i = adm.cadastrarUsuario("Matheus", "matheus", "1234", true);
			//i = adm.removerUsuario(1);
			//i = adm.cadastrarJogador("Matinho", "9999999999999");
			//i= adm.removerJogador("9999999999999");
			//System.out.println(adm.login("1234", "esa"));
			i = adm.cadastrarConcurso(2, "2019-08-21", "17:00:00", "2019-08-24",  "17:00:00");
			System.out.print(i+"\n");

			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
			
	
			
			
	
		
		//**************************************************************************************
		
		/*
		
		//Declaração de váriaveis
		int porta = 5010;		
		Servidor s = null;
		
		
		//Tenta iniciar o servidor, se o servidor não for iniciado o programa é encerrado.
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
				
				System.out.println("Cliente conectado");
				//Trata conexão do cliente com o servidor
				Cliente cliente = new Cliente(c);
				
				Thread t = new Thread(cliente);
				
				t.start();
				
				
			} catch (IOException e) {
				System.out.println("Conexão com cliente perdida");
				e.printStackTrace();
			}
			
		}
			
		
		*/
		
		
		
	
	}

}
