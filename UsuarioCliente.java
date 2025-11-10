import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//usuario do tipo padrao com menos privilegios, podendo fazer vizualizaçao e emprestimos de livros
public class UsuarioCliente extends Usuario{

    private List<Livro> livrosEmprestados;

    public UsuarioCliente(String nome, String email) {
        super(nome, email);
        this.livrosEmprestados = new ArrayList<>();
    }

    //SobEscreve runopçoes, executando metodos relacionados ao nivel de privilegio do cliente
    @Override
    protected void runOpçoes(int selecao){
        switch(selecao){
            case 0 -> {try  {Biblioteca.salvarListas();
            } catch (IOException e){
                e.printStackTrace();}
                }
            case 1 -> Biblioteca.getSortedLivrosList().forEach(System.out::println);

            case 2 -> { 
            try  {Biblioteca
                .getLivrosPorTitulo(Biblioteca.sc.nextLine())
                .stream()
                .forEach(System.out::println);} 
            catch (LivroIndisponivelException e){
            e.printStackTrace();}}

            case 3 -> {
                try {
                    solicitarEmprestimo();
                } catch (LivroIndisponivelException e) {
                    e.printStackTrace();
            }}

            case 4-> devolverLivro();
            
        }

    }
    
    
    //Busca e verifica a disponibilidade dos livros buscados, adicionando para a lista de livros emprestados deste usuario e mudando sua disponibilidade
    public void solicitarEmprestimo() throws LivroIndisponivelException{
        while (true) {
        System.out.println("Digite o título do livro que gostaria de pegar emprestado:");
        String tituloInserido = Biblioteca.sc.nextLine();

        AtomicInteger i = new AtomicInteger(0);
        
        Biblioteca.getLivrosPorTitulo(tituloInserido)
                  .forEach(l -> System.out.println(i.getAndIncrement() + "- " + l));

        System.out.println("Agora digite a opção desejada:");
        try {        
            livrosEmprestados.add(Biblioteca.realizarEmprestimo(tituloInserido, Integer.parseInt(Biblioteca.sc.nextLine())));
            break; // sai do loop se deu certo
        } catch (LivroIndisponivelException ex) {
            System.out.println("Livro indisponível! Escolha outro livro.\n");
            }
        }       
    }

    //Devole livros na lista de emprestados, mudando sua disponibilidade e removendo da lista
    private void devolverLivro() {
        System.out.println("Escolha o livro que deseja devolver: ");
        AtomicInteger i = new AtomicInteger();
        livrosEmprestados.forEach(l -> System.out.println(i.getAndIncrement() + l.toString()));
        String s = Biblioteca.sc.nextLine();
        Livro livroSelecionado = livrosEmprestados.get(Integer.parseInt(s));
        livrosEmprestados.remove(Integer.parseInt(s));
        livroSelecionado.emprestaLivro(false);
    }
    public List<Livro> getLivrosPegos(){
        return livrosEmprestados;
    }

    

}
