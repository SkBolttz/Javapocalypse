import java.util.ArrayList;
import java.util.List;

public class CadastroClientes {

    private List<Cliente> clientes = new ArrayList<>();

    public boolean incluirCliente(Cliente cliente) {
        if (!existeNome(cliente.getNome())) {
            clientes.add(cliente);
            return true;
        } else {
            throw new IllegalArgumentException("Cliente já existe com o nome: " + cliente.getNome());
        }
    }

    public boolean existeNome(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}
