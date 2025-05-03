package ClasesDAO;

import conexion.conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasAvanzadasSQL {

    // 1. Obtener actividades por paquete
    public List<String> obtenerActividadesPorPaquete(int idPaquete) {
        List<String> actividades = new ArrayList<>();
        String sql = "SELECT nombre, descripcion, costo_adicional FROM Actividad WHERE id_paquete = ?";

        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idPaquete);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String actividad = String.format("Nombre: %s | Descripción: %s | Costo adicional: %.2f",
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("costo_adicional"));
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actividades;
    }

    // 2. Obtener reservas por cliente
    public List<String> obtenerReservasPorCliente(int idCliente) {
        List<String> reservas = new ArrayList<>();
        String sql = """
                SELECT r.id, p.nombre AS paquete, r.fecha_reserva, r.estado
                FROM Reserva r
                JOIN PaqueteTuristico p ON r.id_paquete = p.id
                WHERE r.id_cliente = ?""";

        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String reserva = String.format("Reserva #%d | Paquete: %s | Fecha: %s | Estado: %s",
                        rs.getInt("id"),
                        rs.getString("paquete"),
                        rs.getDate("fecha_reserva"),
                        rs.getString("estado"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    // 3. Ranking de destinos más reservados
    public List<String> rankingDestinosMasReservados() {
        List<String> ranking = new ArrayList<>();
        String sql = """
                SELECT p.destino, COUNT(*) AS total_reservas
                FROM Reserva r
                JOIN PaqueteTuristico p ON r.id_paquete = p.id
                GROUP BY p.destino
                ORDER BY total_reservas DESC""";

        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String destino = String.format("Destino: %s | Total reservas: %d",
                        rs.getString("destino"),
                        rs.getInt("total_reservas"));
                ranking.add(destino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ranking;
    }

    // 4. Clientes con más de una reserva
    public List<String> clientesConMasDeUnaReserva() {
        List<String> clientes = new ArrayList<>();
        String sql = """
                SELECT c.nombre_completo, COUNT(*) AS cantidad
                FROM Cliente c
                JOIN Reserva r ON c.id = r.id_cliente
                GROUP BY c.id, c.nombre_completo
                HAVING COUNT(*) > 1""";

        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String cliente = String.format("Cliente: %s | Reservas: %d",
                        rs.getString("nombre_completo"),
                        rs.getInt("cantidad"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}


