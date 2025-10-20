

public abstract class Usuario {

    private final String nome;
    private final String email;
    
    

    public Usuario(String nome , String email){
        this.nome = nome;
        this.email = email;
    }

    public static void menu(){
        
    }

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

  
}