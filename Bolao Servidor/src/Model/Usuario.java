package Model;

public class Usuario {
	
	 private int id;
	 private String nome;
	 private String usuario;
	    private String senha;
	    private boolean adm;

	    public Usuario(int id, String nome, String usuario, String senha, boolean adm){
	        this.id = id;
	        this.nome = nome;
	        this.usuario = usuario;
	        this.senha = senha;
	        this.adm = adm;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
	    }

	    public String getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(String usuario) {
	        this.usuario = usuario;
	    }

	    public boolean isAdm() {
	        return adm;
	    }



}
