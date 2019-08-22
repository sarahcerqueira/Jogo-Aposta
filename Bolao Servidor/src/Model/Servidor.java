package Model;

import java.io.IOException;
import java.net.*;

/** Esta classe é responsável por fazer o servidor abrir uma porta para receber clientes, e aceita-los.
 * 
 */
public class Servidor {
	
	
	private ServerSocket servidor;
	
	
	/** Inicia servirdor.
	 * 
	 * @param porta pela qual o cliente vai se conectar ao servidor.
	 * @throws IOException erro ao abrir o servidor.
	 */
	public Servidor(int porta) throws IOException {
		this.servidor = new ServerSocket(porta);
	}
	
	/** Aceita clientes.
	 * 
	 * @return retornar um socket do cliente aceito.
	 * @throws IOException
	 * 
	 */
	public Socket aceitarCliente() throws IOException {
		return this.servidor.accept();
	}
	
	/** Fecha a porta de conexão com o servidor.
	 * 
	 * @throws IOException
	 */
	public void fecharPorta() throws IOException {
		this.servidor.close();
	}
	
	

}
