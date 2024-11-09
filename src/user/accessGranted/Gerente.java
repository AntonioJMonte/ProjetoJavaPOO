
package user.accessGranted;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.Loja;
import store.Produtos;
import java.util.Scanner;

import CRUD.CRUD;

public class Gerente extends Usuario {

    private List<Vendedor> vendedores;

    public Gerente(String nome, String email) {
        super(nome, email);
        this.vendedores = new ArrayList<>(); // tem que mudar
    }

    @Override
    protected void acessarSistema() {
        
    }

    @Override
    protected void criarConta () {

    }

    public void criarVendedor(String nomeVendedor, CRUD crud) {

        String emailVendedor = (nomeVendedor.toLowerCase().replace(' ', Character.MIN_VALUE)) + "@laranjinha.com";
        System.out.println(emailVendedor);

        Vendedor novoVendedor = new Vendedor(nomeVendedor, emailVendedor);
        vendedores.add(novoVendedor);

        try {
            FileWriter fw = new FileWriter("contasVendedores.txt", true);
            fw.write('\n' + nomeVendedor + ":" + emailVendedor);
            fw.close();
        } catch (IOException e) {
            // pensa aí patrão, sei oq fzr aq nn
        }

        crud.vendedores.add(novoVendedor);
//        System.out.println("Novo vendedor criado: " + nomeVendedor + " - Email: " + emailVendedor);
    }


    public void deletarVendedor(Vendedor vendedorRemovido, CRUD crud) {
//        System.out.println("O vendedor " + vendedor.nome + " foi removido.");
        // Aqui poderia remover o vendedor de uma lista de vendedores.

        vendedores.remove(vendedorRemovido);

        try {
            File client = new File("contasVendedores.txt");
            Scanner reader = new Scanner(client);
            StringBuffer str = new StringBuffer();
            while (reader.hasNextLine()) {
                String temp = reader.nextLine();
                if (!temp.contains(vendedorRemovido.getNome()) && !temp.contains(vendedorRemovido.getEmail())) {
                    str.append(temp);
                    str.append("\n");
                }
            }

            FileWriter fw = new FileWriter("contasVendedores.txt", false);
            fw.write(String.valueOf(str));
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover vendedor!");
        }
    }

    public double calcularLucros(Loja loja) {
        double lucroTotal = 0;
        List<Produtos> produtos = loja.getProdutos();
        for (Produtos produto : produtos) {
            double lucroProduto = produto.getQuantidadeVendida() * produto.getPreco();
            lucroTotal += lucroProduto;      
        }
        return lucroTotal;
    }
}
