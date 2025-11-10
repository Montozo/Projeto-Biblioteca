import java.io.IOException;

//usuario com maior nivel de privilegio, gerenciando cadastros de livros e usuarios
public class UsuarioGerente extends Usuario{
    
    public UsuarioGerente(String nome , String email){
        
        super(nome, email);
        
    }
    
    //Sobrescriçao das opçoes padroes de usuario para as especificas da subclasse
    @Override
    protected int menu(){
        System.out.println("MENU PRINCIPAL");
        System.out.println("Você desejaria:");
        System.out.println("1- Lista de livros");//Oferecer opçoes de ordenaçao e busca
        System.out.println("2- Buscar Livro");
        System.out.println("3- Cadastrar novo livro");
        System.out.println("4- Cadastrar novo usuario");
        System.out.println("0 - Salvar e Sair");
        return Integer.parseInt(Biblioteca.sc.nextLine());
    }

    @Override
    public void runOpçoes(int selecao) {
        switch(selecao){
            case 0 -> {
                try  {
                    Biblioteca.salvarListas();
                } catch (IOException e){
                e.printStackTrace();
                
            }
        }
            case 1 -> Biblioteca.getLivrosList().forEach(System.out::println);

            case 2 ->{ 
                try  {Biblioteca
                    .getLivrosPorTitulo(Biblioteca.sc.nextLine())
                    .stream()
                    .forEach(System.out::println);
                
                } catch (LivroIndisponivelException e){
                    e.printStackTrace();
                }
            }

            case 3 -> Biblioteca.addLivro();

            case 4 -> {
                try {
                    Biblioteca.addUsuario();
                } catch (UsuarioInvalidoException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
