package ProjetoBiblioteca;

import java.util.ArrayList;

public class GerenciamentoDeClientes {

    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void adicionarCliente(Cliente cliente) {
        this.listaClientes.add(cliente);
    }

    public boolean buscarClientePorNome(String nome) {

        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Nome encontrado: " + cliente.getNome());
                return true;
            }
        }
        System.out.println("Nome nao encontrado");
        return false;
    }

    public void buscarClientePorID(int id) {
        boolean encontrado = false;
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == (id)) {
                System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("ID nao encontrado");
        }
    }

    public boolean atualizarCliente(int id, String novoNome, String novoEndereco, int novoTelefone) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == (id)) {
                cliente.atualizarInformacoes(novoNome, novoEndereco, novoTelefone);
                System.out.println("Informações do cliente " + cliente.getNome() + " atualizadas com sucesso");
                return true;
            }
        }
        System.out.println("Nome nao encontrado");
        return false;
    }

    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente na Lista!");
        } else {
            for (Cliente cliente : listaClientes) {
                System.out.println("Cliente: " + cliente.getNome() + ", ID: " + cliente.getId());
            }
        }
    }
}
