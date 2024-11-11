package Básicas.accessGranted;

public abstract class Usuario {
    protected String nome;
    protected String email;

    public Usuario (String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    protected abstract void criarConta();
    protected abstract void acessarSistema();

    public String getNome () {
        return nome;
    }

    public String getEmail (){
        return email;
    }

    public void setNomeUsuario (String nome) {
        this.nome = nome;
    }

    public void setEmail (String email) {
        this.email = email;
    }
}