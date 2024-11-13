package Interface;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Básicas.accessGranted.Gerente;
import Básicas.accessGranted.Vendedor;
import Básicas.client.Cliente;
import Dados.Produtos;
import Dados.CRUD.CRUD;
import Interface.InterfaceVisual;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        // testar criação, leitura e escrita em um arquivo separado para os vendedores, gerente e clientes do sistema
        try {
            File contasCliente = new File("contasClientes.txt");
            if (contasCliente.createNewFile()) {
                System.out.println("\u001B[32m" + "Banco de clientes criado" + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[34m" + "Banco de clientes acessado" + "\u001B[0m");
            }

            File contasVendedores = new File("contasVendedores.txt");
            if (contasVendedores.createNewFile()) {
                System.out.println("\u001B[32m" + "Banco de vendedores criado" + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[34m" + "Banco de vendedores acessado" + "\u001B[0m");
            }
            File contasGerentes = new File("contasGerentes.txt");
            if (contasGerentes.createNewFile()) {
                System.out.println("\u001B[32m" + "Banco de gerentes criado" + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[34m" + "Banco de gerentes acessado" + "\u001B[0m");
            }
            File prods = new File("produtos.txt");
            if (prods.createNewFile()) {
                System.out.println("\u001B[32m" + "Banco de produtos criado" + "\u001B[0m");
            }
            else {
                System.out.println("\u001B[34m" + "Banco de produtos acessado" + "\u001B[0m");
            }
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "Erro no acesso/criação do banco de contas. Finalizando programa..." + "\u001B[0m");
        }

        Scanner scam = new Scanner(System.in);
        int valor = 0;
        boolean continuar = false;

        List<Cliente> cl = new ArrayList<Cliente>();
        List<Vendedor> ve = new ArrayList<Vendedor>();
        List<Gerente> ge = new ArrayList<Gerente>();
        List<Produtos> pr = new ArrayList<Produtos>();

        try {
            File client = new File("contasClientes.txt");
            File vend = new File("contasVendedores.txt");
            File ger = new File("contasGerentes.txt");
            File prod = new File("produtos.txt");
            Scanner reader = new Scanner(client);
            while (reader.hasNextLine()) {
                String[] tempStr = reader.nextLine().split(":");
                Cliente temp = new Cliente(tempStr[0], tempStr[1]);
                cl.add(temp);
            }
            reader = new Scanner(vend);
            while (reader.hasNextLine()) {
                String[] tempStr = reader.nextLine().split(":");
                Vendedor temp = new Vendedor(tempStr[0], tempStr[1]);
                ve.add(temp);
            }
            reader = new Scanner(ger);
            while (reader.hasNextLine()) {
                String[] tempStr = reader.nextLine().split(":");
                Gerente temp = new Gerente(tempStr[0], tempStr[1]);
                ge.add(temp);
            }
            reader = new Scanner(prod);
            while (reader.hasNextLine()) {
                String[] tempStr = reader.nextLine().split(":", 4);
                Produtos temp = new Produtos(tempStr[0], Double.parseDouble(tempStr[1]),
                        Integer.parseInt(tempStr[2]), Integer.parseInt(tempStr[3])); // nome:preço:estoque:vendidos
                pr.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println();
        }

        //Loja loja = new Loja(new ArrayList<>());
        CRUD everything = new CRUD(pr, cl, ve, ge);
        InterfaceVisual interfaceVisual = new InterfaceVisual();

        boolean prosseguir = true;
        
        while (prosseguir) {
            interfaceVisual.menuPrincipal();
           /*System.out.println("<----------------------------->");
            System.out.println("1 - Login como cliente");
            System.out.println("2 - Login como vendedor");
            System.out.println("3 - Login como gerente");
            System.out.println("4 - Finalizar programa");
            System.out.println("<----------------------------->"); */ 

            while (valor < 1 || valor > 4) {
                try {
                    valor = scam.nextInt();
                    scam.nextLine();  // Consumir o restante da linha após nextInt()
                } catch (InputMismatchException e) {
                    System.out.println("Erro!!! Valor digitado de tipo inválido. Finalizando...");
                    System.exit(-20);
                }
            }

            switch (valor) {
                // Login como cliente
                case 1:
                    String nomeDoCliente;
                    String emailCliente;
                    int validar = 0;

                    interfaceVisual.menuCliente();
                    validar = scam.nextInt();
                    scam.nextLine();  // Consumir o restante da linha após nextInt()
                
                    
                    /*System.out.println("1 - Login com conta existente; ");
                    System.out.println("2 - Criar conta; ");
                    System.out.println("Digite sua escolha: ");
                    validar = scam.nextInt();
                    scam.nextLine();*/

                    //Login com conta existente
                    if (validar == 1) {
                        if (everything.clientes.isEmpty()) {
                            System.out.println("Não existem contas cadastradas no sistema. Por gentileza, crie uma nova conta.");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {}
                            break;
                        }
                        // Inserir os dados de login do cliente
                        interfaceVisual.nomeUsuario();
                        nomeDoCliente = scam.nextLine();
                        interfaceVisual.emailUsuario();
                        emailCliente = scam.nextLine();

                        Cliente temp = new Cliente(nomeDoCliente, emailCliente);
                        int ind = -1;
                        for (int i = 0; i < everything.clientes.size(); i++) {
                            if (Objects.equals(everything.clientes.get(i).getNome(), temp.getNome()) && Objects.equals(everything.clientes.get(i).getEmail(), temp.getEmail())) {
                                ind = i;
                            }
                        }

                        Cliente cliente;

                        // Criar o objeto cliente com os dados fornecidos
                        if (ind >= 0) {
                            cliente = everything.clientes.get(ind);
                            System.out.println("Login feito com sucesso como Cliente!");
                        }
                        else {
                            System.out.println("Elemento não encontrado.");
                            break;
                        }

                        continuar = true;
                        while (continuar) {
                            interfaceVisual.opcoesCliente();
                            valor = scam.nextInt();
                            scam.nextLine();  // Consumir o restante da linha após nextInt()

                            if (valor == 1) {
                                // Acessar o catálogo de produtos
                                try {
                                    List <Produtos> produto = cliente.accessProducts(everything);
                                    System.out.println("Acessando os produtos: ");
                                    System.out.println("<----------------------------->");
                                    for (Produtos produtos : produto) {
                                        System.out.println("Nome: " +  produtos.getNome() + ", Preço: " + produtos.getPreco()
                                        + ", Quantidade em estoque: " + produtos.getQuantidadeEmEstoque());
                                    }
                                    System.out.println("<----------------------------->");  
                                }
                                catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Voltanto para o menu principal...");
                                }
                            }
                            else if (valor == 2) {
                                // Comprar produto
                                interfaceVisual.nomeDoProduto();
                                String nomeProduto = scam.nextLine();
                                interfaceVisual.quantidadeProduto();
                                int quantidade = scam.nextInt();
                                scam.nextLine();  // Consumir o restante da linha

                                Produtos produtoParaComprar = null;

                                try {
                                    // Buscar produto na loja e realizar a compra
                                    for (int i = 0; i < everything.produtos.size(); i++) {
                                        Produtos tempProd = everything.produtos.get(i);
                                        if (tempProd.getNome().equals(nomeProduto)) {
                                            produtoParaComprar = tempProd;
                                            break;
                                        }
                                    }
                                    if (produtoParaComprar != null) {
                                        cliente.purchaseOption(produtoParaComprar, quantidade);
                                        double total = quantidade * produtoParaComprar.getPreco();
                                        System.out.printf("Comprando %d unidades de %s por R$ %.2f\n", quantidade, produtoParaComprar.getNome(), total);
                                        produtoParaComprar.registrarVenda(quantidade);
                                    }
                                    else {
                                        System.out.println("Produto não encontrado!");
                                    }
                                }
                                catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            else if (valor == 3) {
                                // Deletar conta
                                if (cliente.deleteAccount(cliente, everything)) {
                                    System.out.println("Conta deletada com sucesso!");
                                }
                                else {
                                    System.out.println("Erro ao remover a conta!");
                                }
                                break;
                            }
                            else if (valor == 4) {
                                continuar = false;
                                System.out.println("Voltando para o menu principal...");
                            }
                        }
                    }
                    else if (validar == 2) {
                        interfaceVisual.nomeCadastro();
                        nomeDoCliente = scam.nextLine();
                        interfaceVisual.emailCadastro();
                        emailCliente = scam.nextLine();

                        Cliente newCliente = Cliente.createProfile(nomeDoCliente, emailCliente);
                        everything.clientes.add(newCliente);

                        try {
                            FileWriter fw = new FileWriter("contasClientes.txt", true);
                            fw.write(nomeDoCliente + ":" + emailCliente);
                            fw.close();
                        } catch (IOException e) {
                            System.out.println("Erro ao guardar cliente. Encerrando...");
                            System.exit(101);
                        }

                        System.out.println("Seja bem-vindo " + nomeDoCliente + "! Agora escolha uma das opções a seguir: ");
                        continuar = true;
                        Cliente cliente = new Cliente(nomeDoCliente, emailCliente);
                        continuar = true;
                        while (continuar) {
                            interfaceVisual.opcoesCliente();
                            valor = scam.nextInt();
                            scam.nextLine();  // Consumir o restante da linha após nextInt()

                            if (valor == 1) {
                                // Acessar o catálogo de produtos
                                try {
                                    List <Produtos> produto = cliente.accessProducts(everything);
                                    System.out.println("Acessando os produtos: ");
                                    System.out.println("<----------------------------->");
                                    for (Produtos produtos : produto) {
                                        System.out.println("Nome: " +  produtos.getNome() + ", Preço: " + produtos.getPreco()
                                        + ", Quantidade em estoque: " + produtos.getQuantidadeEmEstoque());
                                    }
                                    System.out.println("<----------------------------->");  
                                }
                                catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                    System.out.println("Voltanto para o menu principal...");
                                }
                            }
                            else if (valor == 2) {
                                // Comprar produto
                                interfaceVisual.nomeDoProduto();
                                String nomeProduto = scam.nextLine();
                                interfaceVisual.quantidadeProduto();
                                int quantidade = scam.nextInt();
                                scam.nextLine();  // Consumir o restante da linha

                                Produtos produtoParaComprar = null;

                                try {
                                    // Buscar produto na loja e realizar a compra
                                    for (int i = 0; i < everything.produtos.size(); i++) {
                                        Produtos tempProd = everything.produtos.get(i);
                                        if (tempProd.getNome().equals(nomeProduto)) {
                                            produtoParaComprar = tempProd;
                                            break;
                                        }
                                    }
                                    if (produtoParaComprar != null) {
                                        cliente.purchaseOption(produtoParaComprar, quantidade);
                                        double total = quantidade * produtoParaComprar.getPreco();
                                        System.out.printf("Comprando %d unidades de %s por R$ %.2f\n", quantidade, produtoParaComprar.getNome(), total);
                                        produtoParaComprar.registrarVenda(quantidade);
                                    }
                                    else {
                                        System.out.println("Produto não encontrado!");
                                    }
                                }
                                catch (RuntimeException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            else if (valor == 3) {
                                // Deletar conta
                                if (cliente.deleteAccount(cliente, everything)) {
                                    System.out.println("Conta deletada com sucesso!");
                                }
                                else {
                                    System.out.println("Erro ao remover a conta!");
                                }
                                break;
                            }
                            else if (valor == 4) {
                                continuar = false;
                                System.out.println("Voltando para o menu principal...");
                            }
                        }
                    }
                    valor = 0;
                    break;

                //Login como vendedor
                case 2:
                    String nomeDoVendedor;
                    String emailVendedor;

                    interfaceVisual.nomeUsuario();
                    nomeDoVendedor = scam.nextLine();
                    interfaceVisual.emailUsuario();
                    emailVendedor = scam.nextLine();

                    Vendedor temp = new Vendedor(nomeDoVendedor, emailVendedor);
                    int ind = -1;
                    for (int i = 0; i < everything.vendedores.size(); i++) {
                        if (Objects.equals(everything.vendedores.get(i).getNome(), temp.getNome()) && Objects.equals(everything.vendedores.get(i).getEmail(), temp.getEmail())) {
                            ind = i;
                        }
                    }

                    Vendedor vendedor;

                    if (ind >= 0) {
                        vendedor = everything.vendedores.get(ind);
                        System.out.println("Login feito com sucesso como Vendedor!");
                    }
                    else {
                        System.out.println("Vendedor não encontrado.");
                        valor = 0;
                        break;
                    }

                    continuar = true;
                    while (continuar) {
                        interfaceVisual.opcoesVendedor();
                        valor = scam.nextInt();
                        scam.nextLine();  // Consumir o restante da linha após nextInt()

                        if (valor == 1) {
                            // Adicionando um novo produto
                            interfaceVisual.nomeDoProduto();
                            String nomeProduto = scam.nextLine();
                            interfaceVisual.precoDoProduto();
                            double precoProduto = scam.nextDouble();
                            interfaceVisual.quantidadeProduto();
                            int quantidadeEmEstoque = scam.nextInt();
                            scam.nextLine();  // Consumir o restante da linha

                            Produtos novoProduto = new Produtos(nomeProduto, precoProduto, quantidadeEmEstoque, 0);
                            int indProd = -1;
                            for (int i = 0; i < everything.produtos.size(); i++) {
                                if (Objects.equals(everything.produtos.get(i).getNome(), novoProduto.getNome())
                                        && Objects.equals(Double.toString(everything.produtos.get(i).getPreco()), Double.toString(novoProduto.getPreco()))
                                        && Objects.equals(Integer.toString(everything.produtos.get(i).getQuantidadeEmEstoque()), Integer.toString(novoProduto.getQuantidadeEmEstoque()))
                                        && Objects.equals(Integer.toString(everything.produtos.get(i).getQuantidadeVendida()), Integer.toString(novoProduto.getQuantidadeVendida()))) {
                                    indProd = i;
                                }
                            }

                            if (indProd < 0) {
                                vendedor.addProducts(everything, novoProduto);  // Método para adicionar o produto na loja
                                System.out.println("O produto " + nomeProduto + " foi adicionado com sucesso à loja!");
                            }
                            else {
                                System.out.println("Produto já existente na loja!");
                                break;
                            }
                        }
                        else if (valor == 2) {
                            // Ver produtos da loja
                            List<Produtos> tempProds = vendedor.accessProducts(everything);
                            try {
                                System.out.println("Acessando os produtos: ");
                                System.out.println("<----------------------------->");
                                for (Produtos produtos : tempProds) {
                                    System.out.println("Nome: " +  produtos.getNome() + ", Preço: " + produtos.getPreco()
                                            + ", Quantidade em estoque: " + produtos.getQuantidadeEmEstoque());
                                }
                                System.out.println("<----------------------------->");
                            }
                            catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Voltanto para o menu principal...");
                            }
                        }
                        else if (valor == 3) {
                            System.out.print("Digite o produto a ter o valor trocado: ");
                            String nomeProd = scam.nextLine();

                            Produtos prodAlt = null;
                            for (int i = 0; i < everything.produtos.size(); i++) {
                                if (Objects.equals(everything.produtos.get(i).getNome(), nomeProd)) {
                                    prodAlt = everything.produtos.get(i);
                                }
                            }

                            if (prodAlt != null) {
                                System.out.print("Digite o novo valor: ");
                                Double novoPreco = scam.nextDouble();

                                if (vendedor.changePrice(prodAlt, novoPreco)) {
                                    System.out.println("PREÇO TROCADO");
                                }
                                else {
                                    System.out.println("PREÇO INVÁLIDO");
                                }
                            } else {
                                System.out.println("PRODUTO NÃO EXISTENTE");
                            }
                        }
                        else if (valor == 4) {
                            System.out.print("Digite o produto a ser reposto: ");
                            String nomeProd = scam.nextLine();

                            Produtos prodAlt = null;
                            for (int i = 0; i < everything.produtos.size(); i++) {
                                if (Objects.equals(everything.produtos.get(i).getNome(), nomeProd)) {
                                    prodAlt = everything.produtos.get(i);
                                }
                            }

                            if (prodAlt != null) {
                                System.out.print("Digite a quantidade: ");
                                int qtd = scam.nextInt();

                                if (vendedor.stockReplenishment(prodAlt, qtd)) {
                                    System.out.println("QUANTIDADE ADICIONADA");
                                }
                                else {
                                    System.out.println("QUANTIDADE INVÁLIDA");
                                }
                            } else {
                                System.out.println("PRODUTO NÃO EXISTENTE");
                            }
                        }
                        else if (valor == 5) {
                            System.out.println(vendedor.calculateEarnings(everything));
                        }
                        else if (valor == 6) {
                            continuar = false;
                            System.out.println("Voltando para o menu principal...");
                        }
                    }
                    valor = 0;
                    break;

                // Login como gerente
                case 3:    
                    
                    String nomeDoGerente;
                    String emailGerente;

                    System.out.println("Digite seu nome: ");
                    nomeDoGerente = scam.nextLine();
                    System.out.println("Digite seu email: ");
                    emailGerente = scam.nextLine();

                    Gerente tempGer = new Gerente(nomeDoGerente, emailGerente);
                    int indGer = -1;
                    for (int i = 0; i < everything.gerentes.size(); i++) {
                        if (Objects.equals(everything.gerentes.get(i).getNome(), tempGer.getNome()) && Objects.equals(everything.gerentes.get(i).getEmail(), tempGer.getEmail())) {
                            indGer = i;
                        }
                    }

                    Gerente gerente;

                    if (indGer >= 0) {
                        gerente = everything.gerentes.get(indGer);
                        System.out.println("Login como Gerente feito com sucesso!");
                    }
                    else {
                        System.out.println("Gerente não encontrado.");
                        valor = 0;
                        break;
                    }

                    continuar = true;
                    while (continuar) {
                        System.out.println("<----------------------------->");
                        System.out.println("1 - Contratar vendedor");
                        System.out.println("2 - Demitir vendedor");
                        System.out.println("3 - Calcular lucros");
                        System.out.println("4 - Sair");
                        System.out.println("<----------------------------->");
                        System.out.print("Digite a sua opção: ");
                        valor = scam.nextInt();
                        scam.nextLine();  // Consumir o restante da linha após nextInt()

                        if (valor == 1) {
                            System.out.println("Digite o nome do vendedor: ");
                            String nomeVendedor = scam.nextLine();

                            gerente.criarVendedor(nomeVendedor, everything);
                        }
                        else if (valor == 2) {
                            //Lógica para deletar conta
                            System.out.println("Selecione o vendedor a ser removido:");
                            int tempInd = 1;
                            for (Vendedor tempV : everything.vendedores) {
                                System.out.print("(" + Integer.toString(tempInd) + ") " + tempV.getNome() + " - ");
                                tempInd++;
                            }
                            System.out.println();

                            //gerente.deletarVendedor();
                        }
                        else if (valor == 3) {

                            System.out.println("\n--- Calculando Lucros ---");
                            System.out.println("Lucro total da loja em reais: R$ " + gerente.calcularLucros(everything));
                        }
                        else if (valor == 4) {
                            continuar = false;
                            System.out.println("Voltando para o menu principal...");
                        }
                    }
                    valor = 0;
                    break;
                case 4:
                    System.out.println("Finalizando o programa...");
                    prosseguir = false;

                    scam.close();  // Fechar o scanner ao final
                    break;
                default:
                    break;
            }
        }
    }
}