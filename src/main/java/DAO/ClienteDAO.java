package DAO;

import modelo.Cliente;

import java.util.List;

public interface ClienteDAO {
    void insertar(Cliente cliente);
    Cliente obtenerPorId(int id);
    List<Cliente> obtenerTodos();
    void actualizar(Cliente cliente);
    void eliminar(int id);
}

