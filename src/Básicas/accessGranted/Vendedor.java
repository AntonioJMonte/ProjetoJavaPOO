
package Básicas.accessGranted;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Básicas.accessGranted.Usuario;
import Dados.Produtos;
import Dados.CRUD.CRUD;

public class Vendedor extends Usuario {
    public Vendedor (String nome, String email) {
        super(nome, email);
    }

    @Override
    public void acessarSistema () {
    }

    @Override
    protected void criarConta () {
    }

    public List<Produtos> accessProducts (CRUD crud) {
        List<Produtos> produto = crud.produtos;
        if (crud.produtos.isEmpty()) {
            throw new RuntimeException("Não há produtos disponíveis...Voltanto para o menu principal...");
        }
        return crud.produtos;
    }

    public boolean changePrice (Produtos produto, double novoPreco) {
        if (novoPreco < 0) return false;
        produto.setPreco(novoPreco);
        return true;
//        System.out.println("O preço do " + produto.getNome() + " foi atualizado para: R$ " + novoPreco) ;
    }

    public boolean stockReplenishment(Produtos produto, int replace) {
        if (replace < 0) return false;
//        System.out.println("Quantidade de produtos vendidos: " + produto.getQuantidadeVendida());
        produto.reposicao(replace);
        return true;
//        System.out.println("Quantida em estoque após a reposição: " + produto.getQuantidadeEmEstoque());
    }

    public double calculateEarnings(CRUD crud) {
        double total = 0.0;
        for (Produtos produto : crud.produtos) {
            total += (produto.getPreco() * produto.getQuantidadeVendida()) * 0.05;
        }
        return total;
    }

    public void addProducts(CRUD crud, Produtos produto) {
        crud.produtos.add(produto);

        try {
            FileWriter fw = new FileWriter("produtos.txt", true);
            fw.write('\n' + produto.getNome() + ":" + produto.getPreco() + ":" + produto.getQuantidadeEmEstoque() + ":" + produto.getQuantidadeVendida());
            fw.close();
        } catch (IOException e) {
            // pensa aí patrão, sei oq fzr aq nn
        }
    }
}
