
import java.time.LocalDate;
import java.util.Objects;
import java.io.Serializable;
//Classe representando os livros da biblioteca virtual
public class Livro implements Serializable , Comparable{

    private final String titulo;
    private final String autor;
    private final LocalDate dataLancamento;
    private boolean emprestado = false;
    private String statusEmprestimo = (!emprestado) ? "indisponivel" : "disponivel";

    public Livro(String titulo , String autor, LocalDate dataLancamento){
        this.titulo = titulo;
        this.autor = autor;
        this.dataLancamento = dataLancamento;
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

    //retorna true caso o livro esteja emprestado e false caso nao esteja emprestado
    public boolean isEmprestado(){
        return emprestado;
    }

    //Muda o status de diponibilidade do livro e sua variavel
    public void emprestaLivro(boolean emprestado){
        this.emprestado = emprestado;
        if(!emprestado){
            statusEmprestimo = "disponivel";
        } else {
            statusEmprestimo = "indisponivel";
        }
    }

   
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Livro objLivro = (Livro) obj;
        return this.titulo.equalsIgnoreCase(objLivro.getTitulo()) && this.getData().equals(objLivro.getData()) && this.autor.equalsIgnoreCase(objLivro.getAutor());
    }

    @Override
    public int hashCode(){
        return Objects.hash(titulo);
    }

    public String getStatusEmprestimo(){
        return statusEmprestimo;
    }

    @Override
    public int compareTo(Object arg0) {
        Livro outro = (Livro) arg0;
        return this.titulo.compareToIgnoreCase(outro.titulo);
    }

    @Override
    public String toString(){
        return titulo + " (" + statusEmprestimo + ")";
    }
}
