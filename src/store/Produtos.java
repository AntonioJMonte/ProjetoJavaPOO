package store;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Produtos {
    private String nome;
    private double preco;
    private int quantidadeEmEstoque;
    private int quantidadeVendida;

    public Produtos(String nome, double preco, int quantidadeEmEstoque, int quantidadeVendida) {
        this.nome = nome;
        this.preco = preco;
        setQuantidadeEmEstoque(quantidadeEmEstoque);
        this.quantidadeVendida = quantidadeVendida;
    }

    //Criando getters para retornar os atributos privados
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    //Criando setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco (double preco) {
        if (preco >= 0) {
            try {
                File prods = new File("produtos.txt");
                Scanner reader = new Scanner(prods);

                StringBuffer str = new StringBuffer();
                String temp;
                while (reader.hasNextLine()) {
                    temp = reader.nextLine();
                    if (temp.contains(this.getNome()) && temp.contains(Double.toString(this.getPreco()))
                            && temp.contains(Integer.toString(this.getQuantidadeVendida())) &&
                            temp.contains(Integer.toString(this.getQuantidadeEmEstoque()))) {
                        str.append(this.getNome()).append(":").append(preco).append(":")
                                .append(this.getQuantidadeEmEstoque()).append(":").append(this.getQuantidadeVendida());
                    } else {
                        str.append(temp);
                    }
                    str.append("\n");
                }

                FileWriter fr = new FileWriter("produtos.txt");
                fr.write(str.toString());
                fr.close();
            } catch (Exception e) {
                // sla
            }
        }
        else {
            System.out.println("\u001B[31mEstoque insuficiente para realizar a venda\u001B[m");
        }

        this.preco = preco;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void registrarVenda (int quantidadeVendida) {
        if (quantidadeVendida <= quantidadeEmEstoque) {
            try {
                File prods = new File("produtos.txt");
                Scanner reader = new Scanner(prods);

                StringBuffer str = new StringBuffer();
                String temp;
                while (reader.hasNextLine()) {
                    temp = reader.nextLine();
                    if (temp.contains(this.getNome()) && temp.contains(Double.toString(this.getPreco()))
                            && temp.contains(Integer.toString(this.getQuantidadeVendida())) &&
                            temp.contains(Integer.toString(this.getQuantidadeEmEstoque()))) {
                        str.append(this.getNome()).append(":").append(this.getPreco())
                                .append(":").append(this.getQuantidadeEmEstoque() - quantidadeVendida).append(":")
                                .append(this.getQuantidadeVendida() + quantidadeVendida);
                    } else {
                        str.append(temp);
                    }
                    str.append("\n");
                }

                FileWriter fr = new FileWriter("produtos.txt");
                fr.write(str.toString());
                fr.close();
            } catch (Exception e) {
                // sla
            }
            this.quantidadeVendida += quantidadeVendida;
            this.quantidadeEmEstoque -= quantidadeVendida;
        }
        else {
            System.out.println("\u001B[31mEstoque insuficiente para realizar a venda\u001B[m");
        }
    }

    public void reposicao(int repor) {
        this.quantidadeEmEstoque += repor;
    }
}