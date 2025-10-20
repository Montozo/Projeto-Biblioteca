
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Biblioteca {

    private static List<Livro> livroList;
    private static Map<String , Usuario> usuarioList;
    private final Scanner sc;

    public Biblioteca(List<Livro> livroList , Map<String , Usuario> usuarioList){
        Biblioteca.livroList = livroList;
        Biblioteca.usuarioList = usuarioList;
        sc = new Scanner(System.in);
        login();
        

    }

    private void login() {
        System.out.println("Bem vindo a biblioteca virtual!\nInsira seu nome e email para acessar!");
        Usuario UsuarioSelecionado = usuarioList.get(sc.nextLine());
        if(UsuarioSelecionado.getClass().getName().equalsIgnoreCase("UsuarioCliente")){
            
        }
    }
    

}
