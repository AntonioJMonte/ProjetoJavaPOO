package Dados.CRUD;

import java.util.List;

import Básicas.accessGranted.Gerente;
import Básicas.accessGranted.Vendedor;
import Básicas.client.Cliente;
import Dados.Produtos;

public class CRUD {
    public List<Produtos> produtos;
    public List<Gerente> gerentes;
    public List<Vendedor> vendedores;
    public List<Cliente> clientes;

    public CRUD(List<Produtos> p, List<Cliente> c, List<Vendedor> v, List<Gerente> g) {
        this.produtos = p;
        this.gerentes = g;
        this.vendedores = v;
        this.clientes = c;
    }
}