package DAO;

import modelo.Actividad;
import java.util.List;

public interface ActividadDAO {
    void insertar(Actividad actividad);
    Actividad obtenerPorId(int id);
    List<Actividad> obtenerTodas();
    List<Actividad> obtenerPorPaquete(int idPaquete);
    void actualizar(Actividad actividad);
    void eliminar(int id);
}

