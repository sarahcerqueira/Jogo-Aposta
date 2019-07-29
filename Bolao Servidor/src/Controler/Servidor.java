package Controler;

import java.io.IOException;
import java.net.*;

public class Servidor {
	
	private ServerSocket servidor;
	
	
	public Servidor(int porta) throws IOException {
		this.servidor = new ServerSocket(porta);
	}
	
	
	public Socket aceitarCliente() throws IOException {
		return this.servidor.accept();
	}
	
	public void fecharPorta() throws IOException {
		this.servidor.close();
	}
	
	

}
