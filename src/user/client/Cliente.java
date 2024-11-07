package user.client;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import user.accessGranted.Usuario;
import store.Loja;
import store.Produtos;

import CRUD.CRUD;

public class Cliente extends Usuario {

    public Cliente (String nome, String email) {
        super(nome, email);
    }

    @Override
    protected void acessarSistema() {
    }

    @Override
    public void criarConta () {
        /*Scanner scam = new Scanner(System.in);

        System.out.println("<--- Criando conta do cliente --->");

        System.out.println("Digite seu nome: ");
        String nome = scam.nextLine();
        setNomeUsuario(nome);

        System.out.println("Digite seu email: ");
        String email = scam.nextLine();
        setEmail(email);

        System.out.println("Conta criada com sucesso! Seja bem-vindo " + nome);*/

    }

    public List<Produtos> accessProducts(CRUD crud) {
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


    public void purchaseOption (Produtos produto, int unit) {
        if (produto.getQuantidadeEmEstoque() >= unit) {
            double total = unit * produto.getPreco();
            System.out.printf("Comprando %d unidades de %s por R$ %.2f\n", unit, produto.getNome(), total);
            produto.registrarVenda(unit);
        }
        else {
            System.out.println("Não há unidades suficientes no estoque");
        }
    }

    public void deleteAccount (Usuario infoUser, CRUD crud) {
        boolean res = crud.clientes.remove(infoUser);
        if (res) {
            System.out.println("Conta removida com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }

        try {
            File client = new File("contasClientes.txt");
            Scanner reader = new Scanner(client);
            StringBuffer str = new StringBuffer();
            while (reader.hasNextLine()) {
                String temp = reader.nextLine();
                if (!temp.contains(infoUser.getNome()) && !temp.contains(infoUser.getEmail())) {
                    str.append(temp);
                    str.append("\n");
                }
            }

            FileWriter fw = new FileWriter("contasClientes.txt", false);
            fw.write(String.valueOf(str));
            fw.close();
        } catch (Exception e) {
            System.out.println("Erro em alguma coisa aq");
        }
    }

    public static Cliente createProfile (String nome, String email) {
        return new Cliente(nome, email);
    }
}