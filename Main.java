
public class Main {
    public static void main(String[] args) {
        
        Biblioteca biblioteca = new Biblioteca();

        //biblioteca.getLivrosList().forEach(s -> s.emprestaLivro(false));

        try {
            biblioteca.login();
        } catch (UsuarioInvalidoException e) {
        }

        Biblioteca.getFuncoesUsuario();

    }
    }
