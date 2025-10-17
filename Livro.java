
import java.time.LocalDate;

public class Livro {

    private final String titulo;
    private final String autor;
    private final LocalDate dataLancamento;
    private boolean emprestado = false;
    private String statusEmprestimo;

    public Livro(String titulo , String autor, LocalDate dataLancamento){
        this.titulo = titulo;
        this.autor = autor;
        this.dataLancamento = dataLancamento;
        this.statusEmprestimo = (!emprestado) ? "Disponivel" : "indisponivel";
    }

    public String getTitulo(){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    public LocalDate getData(){
        return dataLancamento;
    }

    public boolean isEmprestado(){
        return emprestado;
    }

    public String getStatusEmprestimo(){
        return statusEmprestimo;
    }
}
