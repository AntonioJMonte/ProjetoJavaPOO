package Dados;

import java.util.ArrayList;
import java.util.List;

public class Loja {

    private List<Produtos> produtos;

    public Loja (List<Produtos> produto) {
        this.produtos = produto;
    }

    public List <Produtos> getProdutos() {
        return produtos;
    }

    public void adicionarProdutos (Produtos produto) {
        produtos.add(produto);
    }

    public Produtos getProdutoPorNome (String nomeDoProduto) {
        for (Produtos produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeDoProduto)) {
                return produto;
            }
        }
        return null;
    }
}