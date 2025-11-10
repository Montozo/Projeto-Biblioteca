
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/*A Biblioteca gerencia as listas de livros e usuarios, 
como tambem partes do processo de interaçao com o usuario*/
public class Biblioteca {
    
    private static List<Livro> livroList;
    public static Map<String , Usuario> usuarioList;
    private static Usuario usuarioLogado;
    public static Scanner sc;

    //Objeto criado a partir das listas salvas no arquivo
    public Biblioteca(){
        loadListas();
        sc = new Scanner(System.in);

    }
    //Objeto tipo biblioteca sendo criada a partir de listas de usuario e livros por parametros prévios
    public Biblioteca(List<Livro> livroList , Map<String , Usuario> usuarioList){
        Biblioteca.livroList = livroList;
        Biblioteca.usuarioList = usuarioList;
        sc = new Scanner(System.in);
    }

    /*Pede login do usuario, realizando uma busca e selecionando para usuarioLogado, 
    o resto das operaçoes sao feitas a partir desse usuario selecionado.
    Joga um UsuarioInvalidoException caso o usuario nao exista nas listas*/
    public void login() throws UsuarioInvalidoException{
        System.out.println("Bem vindo a biblioteca virtual!\nInsira seu email para acessar!");
        String emailInserido = sc.nextLine();

        if(usuarioList.containsKey(emailInserido)){
            this.usuarioLogado = usuarioList.get(emailInserido);
        } else {
            throw new UsuarioInvalidoException("Usuario não cadastrado!");
        }
    }

    //Caso um usuario esteje logado, suas funçoes sao executadas.
    public static void getFuncoesUsuario(){
        if (usuarioLogado == null) {
            System.out.println("Nenhum usuario logado");
        } else {
            usuarioLogado.getFuncoesUsuario(usuarioLogado);
        }
    }

    //adiciona um livro a lista
    public static void addLivro(){
        System.out.println("Digite o titulo do livro");
        String titulo = sc.nextLine();
        System.out.println("Data de lançamento:(Primeiro ano -> mes -> dia)");
        LocalDate data = LocalDate.of(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
        System.out.println("Autor:");
        String autor = sc.nextLine();

        Livro livro = new Livro(titulo, autor, data);

        if (livroList.contains(livro)){
            System.out.println("Livro ja existente");
        } else {
            livroList.add(new Livro(titulo, autor, data));
            System.out.print("Livro adicionado com sucesso!");
        }
    }
    /*Adiciona um Usuario ao map de usuarios, jogando um UsuarioInvalidoException caso haja 
    duplicidade de usuarios ou invalidade de cargo/* */
    public static void addUsuario() throws UsuarioInvalidoException{
        System.out.println("Digite o email: ");
        String email = sc.nextLine();
        if(usuarioList.containsKey(email)){
            throw new UsuarioInvalidoException("Usuario com esse email ja existe");
        }
        System.out.println("Nome de usuario: ");
        String nome = sc.nextLine();
        System.out.println("agora digite o tipo de usuario (cliente, gerente)");
        String tipoUsuario = sc.nextLine();
        if(tipoUsuario.equalsIgnoreCase("gerente")){
            usuarioList.put(email, new UsuarioGerente(nome, email));
            System.out.println("usuario adicionado com sucesso!");
        } else if(tipoUsuario.equalsIgnoreCase("cliente")){
            System.out.println("usuario adicionado com sucesso!");
            usuarioList.put(email, new UsuarioCliente(nome, email));
        } else{
            throw new UsuarioInvalidoException("Usuario ivalido");
        }
    }


    //retorna uma lista com apenas livros que tenham o titutlo inserido
    public static List<Livro> getLivrosPorTitulo(String titulo) throws LivroIndisponivelException{
        List<Livro> listaPorTitulo = livroList.stream()
                                    .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                                    .collect(Collectors.toList());
        if (listaPorTitulo.get(0) == null){
            throw new LivroIndisponivelException("Livro inexistente");
        } else {
            return listaPorTitulo;
        }
    }
    //Verifica a disponibilidade do livro listado e caso disponivel retorna o livro e muda seu status
    public static Livro realizarEmprestimo(String tituloInserido, int i) throws LivroIndisponivelException{
        Livro livroSelecionado = Biblioteca.getLivrosPorTitulo(tituloInserido).get(i);

        if (livroSelecionado.isEmprestado()) {
            throw new LivroIndisponivelException(livroSelecionado.getStatusEmprestimo());
        } else {
            livroSelecionado.emprestaLivro(true);
        }

        return livroSelecionado;
    }
     public static List<Livro> getLivrosList(){
        return livroList;
     }
    public static List<Livro> getSortedLivrosList(){
        
        do{
        System.out.println("Gostaria de listar por: ");
        System.out.println("1- Titulo ");
        System.out.println("2- Ano");
        System.out.println("3- Autor");
        
        switch (Integer.parseInt(sc.nextLine())){
            case 1 -> {
                return livroList.stream()
                .sorted()
                .collect(Collectors.toList());
            }
            
            case 2 -> {
                return livroList.stream()
                .sorted(Comparator.comparing(Livro::getData))
                .collect(Collectors.toList());
            }
            case 3 -> {
                return livroList.stream()
                .sorted(Comparator.comparing(Livro::getAutor))
                .collect(Collectors.toList());
            } default ->{
                System.out.println("digite um valor valido");
            }
        }
        } while(true);
    }

    //Carrega as listas salvas nos arquivos, e adiciona para as listas da classe
    public static void loadListas(){
        
        try {
            ObjectInputStream oISLivros = new ObjectInputStream(new FileInputStream(Arquivo.PATH_LIVROS.getPath()));
            ObjectInputStream oISUsuarios = new ObjectInputStream(new FileInputStream(Arquivo.PATH_USUARIOS.getPath()));
            List<Livro> listaLivroSaved = (List<Livro>) oISLivros.readObject();
            Map<String , Usuario> listaUsuarioSaved = (Map<String , Usuario>) oISUsuarios.readObject();

            livroList = listaLivroSaved;
            usuarioList = listaUsuarioSaved;
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    //Salva as listas no arquivo
    public static void salvarListas() throws FileNotFoundException, IOException {
        ObjectOutputStream oOSLivros = new ObjectOutputStream(new FileOutputStream(Arquivo.PATH_LIVROS.getPath()));
        ObjectOutputStream oOSUsuarios = new ObjectOutputStream(new FileOutputStream(Arquivo.PATH_USUARIOS.getPath()));

        oOSLivros.writeObject(livroList);
        oOSUsuarios.writeObject(usuarioList);

        oOSLivros.close();
        oOSUsuarios.close();
    }
}   

