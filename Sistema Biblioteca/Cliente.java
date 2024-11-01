package ProjetoBiblioteca;

public class Cliente {

    private String nome;
    private int id;
    private String endereco;
    private int telefone;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void atualizarInformacoes(String nome, String endereco, int telefone) {

        boolean atualizado = false;

        if (this.nome != null || this.nome.trim().isEmpty()) {
            this.setNome(nome);
            System.out.println("Nome atualizado com sucesso");
            atualizado = true;
        }

        if (this.endereco != null || this.endereco.trim().isEmpty()) {
            this.setEndereco(endereco);
            System.out.println("Endereço atualizado com sucesso");
            atualizado = true;
        }

        if (this.telefone <= 0) {
            this.setTelefone(telefone);
            System.out.println("Telefone atualizado com sucesso");
            atualizado = true;
        }

        if (!atualizado) {
            System.out.println("Já estavam atualizadas!");
        } else {
            System.out.println("Informações atualizadas com sucesso");
        }
    }
}
