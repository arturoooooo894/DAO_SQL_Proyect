package DAO;

import modelo.Reserva;
import java.util.List;

public interface ReservaDAO {
    void insertar(Reserva reserva);
    Reserva obtenerPorId(int id);
    List<Reserva> obtenerTodas();
    List<Reserva> obtenerPorCliente(int idCliente);
    void actualizar(Reserva reserva);
    void eliminar(int id);
}
