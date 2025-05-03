package DAO;

import modelo.PaqueteTuristico;
import java.util.List;

public interface PaqueteTuristicoDAO {
    void insertar(PaqueteTuristico paquete);
    PaqueteTuristico obtenerPorId(int id);
    List<PaqueteTuristico> obtenerTodos();
    void actualizar(PaqueteTuristico paquete);
    void eliminar(int id);
}

