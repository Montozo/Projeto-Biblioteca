public enum Arquivo {
    PATH_USUARIOS ("/run/media/kaiky/94a33637-813a-4528-bda2-961863b19fd0/Curso-Java/Projeto-Biblioteca/Listas/LIsta-Usuarios/usuarios.dat"), 
    PATH_LIVROS ("/run/media/kaiky/94a33637-813a-4528-bda2-961863b19fd0/Curso-Java/Projeto-Biblioteca/Listas/Lista-Livros/livros.dat");

    String path;

    private Arquivo(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

    @Override
    public String toString(){
        return path;
    }
}
