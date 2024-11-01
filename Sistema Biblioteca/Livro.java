package ProjetoBiblioteca;

public class Livro {

    private String titulo;
    private String autor;
    private String isnb;
    private boolean disponivel;

    

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsnb(String isnb) {
        this.isnb = isnb;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsnb() {
        return isnb;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void getDetalhes() {
        System.out.println("Título: " + this.titulo);
        System.out.println("Autor: " + this.autor);
        System.out.println("ISBN: " + this.isnb);
        System.out.println("Disponibilidade: " + (this.disponivel ? "Disponível" : "Indisponível"));
    }

    public void alterarDisponibilidade() {
        this.disponivel = !this.disponivel;
    }
}