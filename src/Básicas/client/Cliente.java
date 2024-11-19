package Básicas.client;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import Básicas.accessGranted.Usuario;
import Dados.Produtos;
import Dados.CRUD.CRUD;

public class Cliente extends Usuario {

    public Cliente (String nome, String email) {
        super(nome, email);
    }

    @Override
    protected void acessarSistema() {
    }

    @Override
    public void criarConta () {
    }

    public List<Produtos> accessProducts(CRUD crud) {
        if (crud.produtos.isEmpty()) {
            throw new RuntimeException("Não há produtos disponíveis...Voltanto para o menu principal...");
        }
        return crud.produtos;
    }


    public void purchaseOption (Produtos produto, int unit) {
        if (produto.getQuantidadeEmEstoque() < unit) {
            throw new RuntimeException("Não há unidades suficientes no estoque");
        }
    }

    public boolean deleteAccount (Usuario infoUser, CRUD crud) {
        boolean res = crud.clientes.remove(infoUser);
        if (res) {
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

                return res;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }

    }

    public static Cliente createProfile (String nome, String email) {
        return new Cliente(nome, email);
    }
}