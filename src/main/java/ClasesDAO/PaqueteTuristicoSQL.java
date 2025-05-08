package ClasesDAO;

import conexion.conexion;
import DAO.PaqueteTuristicoDAO;
import modelo.PaqueteTuristico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaqueteTuristicoSQL implements PaqueteTuristicoDAO {

    @Override
    public void insertar(PaqueteTuristico paquete) {
        String sql = "INSERT INTO paquete_turistico (nombre, destino, precio, duracion_dias) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paquete.getNombre());
            stmt.setString(2, paquete.getDestino());
            stmt.setDouble(3, paquete.getPrecio());
            stmt.setInt(4, paquete.getDuracionDias());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PaqueteTuristico obtenerPorId(int id) {
        String sql = "SELECT * FROM paquete_turistico WHERE id = ?";
        PaqueteTuristico paquete = null;

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paquete = new PaqueteTuristico(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("destino"),
                        rs.getDouble("precio"),
                        rs.getInt("duracion_dias")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paquete;
    }

    @Override
    public List<PaqueteTuristico> obtenerTodos() {
        String sql = "SELECT * FROM paquete_turistico";
        List<PaqueteTuristico> paquetes = new ArrayList<>();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PaqueteTuristico paquete = new PaqueteTuristico(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("destino"),
                        rs.getDouble("precio"),
                        rs.getInt("duracion_dias")
                );
                paquetes.add(paquete);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paquetes;
    }

    @Override
    public void actualizar(PaqueteTuristico paquete) {
        String sql = "UPDATE paquete_turistico SET nombre = ?, destino = ?, precio = ?, duracion_dias = ? WHERE id = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paquete.getNombre());
            stmt.setString(2, paquete.getDestino());
            stmt.setDouble(3, paquete.getPrecio());
            stmt.setInt(4, paquete.getDuracionDias());
            stmt.setInt(5, paquete.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM paquete_turistico WHERE id = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


