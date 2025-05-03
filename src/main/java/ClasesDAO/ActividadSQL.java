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
        String sql = "INSERT INTO Actividad (idPaquete, nombre, descripcion, costoAdicional) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, actividad.getIdPaquete());
            stmt.setString(2, actividad.getNombre());
            stmt.setString(3, actividad.getDescripcion());
            stmt.setDouble(4, actividad.getCostoAdicional());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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
                        rs.getInt("idPaquete"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("costoAdicional")
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
                        rs.getInt("idPaquete"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("costoAdicional")
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
        String sql = "SELECT * FROM Actividad WHERE idPaquete = ?";
        List<Actividad> actividades = new ArrayList<>();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaquete);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Actividad actividad = new Actividad(
                        rs.getInt("id"),
                        rs.getInt("idPaquete"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("costoAdicional")
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
        String sql = "UPDATE Actividad SET idPaquete = ?, nombre = ?, descripcion = ?, costoAdicional = ? WHERE id = ?";

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


