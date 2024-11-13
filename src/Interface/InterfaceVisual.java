package Interface;

import java.util.List;
import java.util.Scanner;

public class InterfaceVisual {
    
    Scanner scam = new Scanner(System.in);
    int validar;
    int valor;

    public void menuPrincipal () {
        System.out.println("<----------------------------->");
        System.out.println("1 - Login como Cliente");
        System.out.println("2 - Login como Vendedor");
        System.out.println("3 - Login como Gerente");
        System.out.println("4 - Sair");
        System.out.println("<----------------------------->");
        System.out.print("Digite a sua opção: ");
    }

    public void menuCliente () {
        System.out.println("1 - Login com conta existente; ");
        System.out.println("2 - Criar conta; ");
        System.out.println("Digite sua escolha: ");
    }

    public void opcoesCliente () {
        System.out.println("<----------------------------->");
        System.out.println("1 - Acessar catálogo de produtos");
        System.out.println("2 - Comprar produto");
        System.out.println("3 - Deletar conta");
        System.out.println("4 - Sair");
        System.out.println("<----------------------------->");
        System.out.print("Digite a sua opção: "); 
    }

    public void opcoesVendedor () {
        System.out.println("<----------------------------->");
        System.out.println("1 - Adicionar produto");
        System.out.println("2 - Ver produtos da loja");
        System.out.println("3 - Alterar valor de um produto");
        System.out.println("4 - Repor estoque");
        System.out.println("5 - Calcular ganhos");
        System.out.println("6 - Sair");
        System.out.println("<----------------------------->");
        System.out.print("Digite a sua opção: ");
    }

    public void opcoesGerente () {
        System.out.println("<----------------------------->");
        System.out.println("1 - Contratar vendedor");
        System.out.println("2 - Demitir vendedor");
        System.out.println("3 - Calcular lucros");
        System.out.println("4 - Sair");
        System.out.println("<----------------------------->");
        System.out.print("Digite a sua opção: ");
    }

    public void nomeUsuario () {
        System.out.println("Digite seu nome: ");
    }
    public void nomeCadastro () {
        System.out.println("Digite o nome de usuário a ser cadastrado: ");
    }
    public void nomeDoContratado () {
        System.out.println("Digite o nome do vendedor que será contratado: ");
    }

    public void emailUsuario () {
        System.out.println("Digite seu email: ");
    }
    public void emailCadastro () {
        System.out.println("Digite o email a ser cadastrado ");
    }

    public void nomeDoProduto () {
        System.out.println("Digite o nome do produto: ");
    }

    public void precoDoProduto () {
        System.out.println("Digite o preço do produto: ");
    }
    public void novoPrecoProduto () {
        System.out.println("Digite o novo valor do produto: ");
    }

    public void quantidadeDeCompra () {
        System.out.println("Digite a quantidade que deseja comprar: ");
    }
    public void quantidadeRepor () {
        System.out.println("Digite a quantidade que deseja repor: ");
    }
    public void quantidadeAdd () {
        System.out.println("Digite a quantidade que você deseja adicionar a loja: ");
    }

    
    public void msgErroTipo () {
        System.out.println("Erro!!! Valor digitado de tipo inválido. Finalizando...");
    }

    public void msgErroLoginCliente () {
        System.out.println("Elemento não encontrado.");
    }
    
    public void msgSemClientes () {
        System.out.println("Não existem contas cadastradas no sistema. Por gentileza, crie uma nova conta.");
    }

    public void msgSucessoLogin () {
        System.out.println("Login feito com sucesso!");
    }

    public void msgSucessoDel () {
        System.out.println("Conta deletada com sucesso!");
    }
}
