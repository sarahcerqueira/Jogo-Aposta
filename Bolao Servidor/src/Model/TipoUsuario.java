package Model;

public enum TipoUsuario {
	
	ADMINISTRADOR(1), VENDEDOR(2), JOGADOR(3);
	
	public int tipoUsuario;
	
	TipoUsuario(int t) {
		
		this.tipoUsuario = t;
	}
	
	

}
