import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoLogins {

    public void escritaLogins(String login, String senha, String caminho) {
        try {
            File file = new File(caminho);
            boolean isNewFile = !file.exists();
            FileWriter writer = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(writer);
            if (isNewFile) {
                printWriter.println("Login, Senha");
            }
            printWriter.println(login + ", " + senha);
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean leituraLogins(String login, String senha, String caminho) throws IOException {
        boolean loginEncontrado = false;
        try (Scanner scanner = new Scanner(new File(caminho))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(", ");

                if (dados[0].equals(login) && dados[1].equals(senha)) {
                    return true;
                }
            }
        }
        if(!loginEncontrado){
        }
        return loginEncontrado;
    }

    public List<String[]> carregarLogins(String caminho) throws IOException {
        List<String[]> logins = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(", ");
            logins.add(dados);
        }
        br.close();
        return logins;
    }
}
