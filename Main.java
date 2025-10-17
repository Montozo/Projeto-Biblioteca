import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Livro livro = new Livro("Penis","meu pau", LocalDate.now());

        System.out.println(livro.getStatusEmprestimo());
    }
}