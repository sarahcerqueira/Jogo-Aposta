package Controler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import Model.TipoUsuario;

public class Cliente implements Runnable{
	
	private Socket cliente;
	private int maquina;		
	private int idUsuario;			//Quando um cliente se conecta é necessário saber o usuário(adm ou vendedor);
	private TipoUsuario tipo;		//O tipo de usuario vai definir se o cliente é um ADM, um jogador ou vendedor.
	private  ObjectInputStream  ler;
	private ObjectOutputStream escrever;
	private Controler adm;
	
	public Cliente(Socket c) {
		this.cliente = c;
		
		try {
			this.adm = new Controler("127.0.0.1",
					"5432", "jogo", "postgres", "admin");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		
		try {
			escrever = new ObjectOutputStream(this.cliente.getOutputStream());
			ler = new ObjectInputStream(this.cliente.getInputStream());
			
			String op = (String) this.lerDoCliente();

			if(op.equals("login")){
				this.login();
				
			} else if(op.equals("loginJogador")) {
				this.loginJogador();
				
			} else if(op.equals("cadastroJogador")) {
				this.cadastrarJogador();
			}
		
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	
	private void fecharConexao() throws IOException {
		this.escrever.close();
		this.ler.close();
		this.cliente.close();
	}
	
	
	private Object lerDoCliente() throws ClassNotFoundException, IOException {
		return ler.readObject();
	}
	
	private void escreverParaCliente(Object o) throws IOException {
		this.escrever.flush();
		this.escrever.writeObject(o);
	}
	
	
	private void login() {
		String senha, username;
		
		try {
			senha = (String) this.lerDoCliente();
			username = (String) this.lerDoCliente();
			
			System.out.println(username + " "+ senha);
			
			String check = adm.login(username, senha);
			System.out.println(check);
			

			if(!check.equals("erro")) {
				
				if(check.equals("t")) {
					this.escreverParaCliente("adm");

				} else {
					
					this.escreverParaCliente("vend");
				
				}
				System.out.println("Login ok");

			}else {
				this.escreverParaCliente(check);
				System.out.println("Erro no login");

			}
			
			this.fecharConexao();


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void loginJogador(){
		try {
			String telefone = (String) this.lerDoCliente();
			
			if(this.adm.loginJogador(telefone)) {
				this.escreverParaCliente("true");
			} else {
				this.escreverParaCliente("cadastrar");				
			}
			
			this.fecharConexao();
			
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cadastrarJogador() {
		
		String telefone;
		
		try {
			
			telefone = (String) this.lerDoCliente();
			String nome = (String) this.lerDoCliente();
			int i = this.adm.cadastrarJogador(nome, telefone);
			
			if(i==1) {
				this.escreverParaCliente("ok");
			} else {
				this.escreverParaCliente("erro");
			}
			
			this.fecharConexao();

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
