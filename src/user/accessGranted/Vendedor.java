
package user.accessGranted;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import CRUD.CRUD;
import user.accessGranted.Usuario;
import store.Loja;
import store.Produtos;
import java.util.Scanner;

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
        if (produto.isEmpty()) {
            return null;
//            System.out.println("Não há produtos disponíveis");
        } else {
//            System.out.println("Acessando os produtos: ");
//            System.out.println("<----------------------------->");
//            for (Produtos produtos : produto) {
//                System.out.println("Nome: " +  produtos.getNome() + ", Preço: " + produtos.getPreco()
//                        + ", Quantidade em estoque: " + produtos.getQuantidadeEmEstoque());
//            }
//            System.out.println("<----------------------------->");
            return produto;
        }
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
