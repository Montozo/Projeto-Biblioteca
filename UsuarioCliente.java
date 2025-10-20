import java.util.ArrayList;
import java.util.List;

public abstract class UsuarioCliente extends Usuario{

    private List<Livro> livrosEmprestados;

    public UsuarioCliente(String nome, String email) {
        super(nome, email);
        this.livrosEmprestados = new ArrayList<>();
    }

    

    public List<Livro> getLivrosPegos(){
        return livrosEmprestados;
    }

    

}
