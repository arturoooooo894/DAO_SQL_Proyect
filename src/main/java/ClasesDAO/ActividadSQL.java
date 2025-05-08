package ClasesDAO;

import conexion.conexion;
import DAO.ActividadDAO;
import modelo.Actividad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActividadSQL implements ActividadDAO {

    @Override
    public void insertar(Actividad actividad) {
        String sql = "INSERT INTO actividad (id_paquete, nombre, descripcion, costo_adicional) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexion.getConnection()) {
            conn.setAutoCommit(false); // Desactiva autocommit

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, actividad.getIdPaquete());
                stmt.setString(2, actividad.getNombre());
                stmt.setString(3, actividad.getDescripcion());
                stmt.setDouble(4, actividad.getCostoAdicional());

                stmt.executeUpdate();
                conn.commit(); // Si todo va bien, confirma los cambios
                System.out.println("Actividad insertada correctamente.");

            } catch (SQLException e) {
                conn.rollback(); // Rollback si falla algo en la ejecución
                System.out.println("Error al insertar la actividad: " + e.getMessage());
                System.out.println("Transacción revertida.");
            } finally {
                conn.setAutoCommit(true); // Restaurar autocommit para no afectar otras operaciones
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Errores en la conexión o en el rollback
        }
    }






    @Override
    public Actividad obtenerPorId(int id) {
        String sql = "SELECT * FROM Actividad WHERE id = ?";
        Actividad actividad = null;

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                actividad = new Actividad(
                        rs.getInt("id"),
                        rs.getInt("id_paquete"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("costo_adicional")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actividad;
    }

    @Override
    public List<Actividad> obtenerTodas() {
        String sql = "SELECT * FROM Actividad";
        List<Actividad> actividades = new ArrayList<>();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Actividad actividad = new Actividad(
                        rs.getInt("id"),
                        rs.getInt("id_paquete"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("costo_adicional")
                );
                actividades.add(actividad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actividades;
    }

    @Override
    public List<Actividad> obtenerPorPaquete(int idPaquete) {
        String sql = "SELECT * FROM Actividad WHERE id_paquete = ?";
        List<Actividad> actividades = new ArrayList<>();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaquete);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Actividad actividad = new Actividad(
                        rs.getInt("id"),
                        rs.getInt("id_paquete"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("costo_adicional")
                );
                actividades.add(actividad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actividades;
    }

    @Override
    public void actualizar(Actividad actividad) {
        String sql = "UPDATE Actividad SET id_paquete = ?, nombre = ?, descripcion = ?, costo_adicional = ? WHERE id = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, actividad.getIdPaquete());
            stmt.setString(2, actividad.getNombre());
            stmt.setString(3, actividad.getDescripcion());
            stmt.setDouble(4, actividad.getCostoAdicional());
            stmt.setInt(5, actividad.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Actividad WHERE id = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


