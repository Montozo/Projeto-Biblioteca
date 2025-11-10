import java.io.Serializable;

//Usuarios que podem acessar a biblioteca
public abstract class Usuario implements Serializable{

    private final String nome;
    private final String email;
    
    

    public Usuario(String nome , String email){
        this.nome = nome;
        this.email = email;
        }

    //Interface mostrada ao usuario no sistem, retornando um int selecionado pelo usuario
    protected int menu(){
        System.out.println("MENU PRINCIPAL");
        System.out.println("Você desejaria:");
        System.out.println("1- Lista de livros");//Oferecer opçoes de ordenaçao e busca
        System.out.println("2- Buscar Livro");
        System.out.println("3- Pegar Livro emprestado");
        System.out.println("4- Devolver Livro");
        System.out.println("0 - Salvar e Sair");
        return Integer.parseInt(Biblioteca.sc.nextLine());
    }

    //Recebe um int de menu e executa o metodo runOpçoes especifico do usuario parametizado
    public void getFuncoesUsuario(Usuario usuario){
        int selecao;
        do { 
            selecao = this.menu();
            if (selecao > 4 || selecao < 0){
                System.out.println("digite uma opçao valida!");
                continue;
            }
            usuario.runOpçoes(selecao);
        } while (selecao != 0);
               
    }

    //Lida com as opçoes e resolve os metodos baseado na selecao do menu
    protected abstract void runOpçoes(int selecao);

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

  
}