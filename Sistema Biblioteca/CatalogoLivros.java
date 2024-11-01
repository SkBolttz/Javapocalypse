package ProjetoBiblioteca;

import java.util.ArrayList;

public class CatalogoLivros {

    private ArrayList<Livro> listarLivros = new ArrayList<>();

    public ArrayList<Livro> getListarLivros() {
        return listarLivros;
    }

    public void adicionarLivro(Livro livro) {
        this.listarLivros.add(livro);
    }

    public void removerLivro(Livro livro) {
        this.listarLivros.remove(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : this.listarLivros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Livro encontrado: " + livro.getTitulo());
                return livro; // Retorna o livro encontrado
            }
        }
        System.out.println("Livro não encontrado");
        return null;
    }

    public Livro buscarLivroPorAutor(String autor) {
        for (Livro livro : this.listarLivros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                System.out.println("Livro encontrado: " + livro.getTitulo());
                return livro;
            }
        }
        System.out.println("Autor não encontrado");
        return null;
    }

    public Livro buscarLivroPorISBN(String isbn) {
        for (Livro livro : this.listarLivros) {
            if (livro.getIsnb().equals(isbn)) {
                System.out.println("Livro encontrado: " + livro.getTitulo());
                return livro;
            }
        }
        System.out.println("Livro não encontrado");
        return null;
    }

    public void listarLivros(){
        if(this.listarLivros.isEmpty()){
            System.out.println("Nenhum livro encontrado");
        }else{
            System.out.println("Listando...");
            for (Livro livro : this.listarLivros){
                System.out.println("Titulo: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", ISBN: " + livro.getIsnb());
            }
        }
    }
}
