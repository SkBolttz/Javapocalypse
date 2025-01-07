public class Cliente {

    private int id;
    private String nome;
    private String endereco;
    private String email;
    private String senha;

    public Cliente() {
    }

    public Cliente(int id, String nome, String endereco, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String toString() {
        return "Nome: " + getNome() + "\n"
                + "Endereço: " + getEndereco() + "\n"
                + "Email: " + getEmail();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cliente other = (Cliente) obj;
        return this.getNome().equals(other.getNome());
    }

    public int hashCode() {
        return getNome().toLowerCase().hashCode();
    }
}
