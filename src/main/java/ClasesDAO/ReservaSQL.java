package ClasesDAO;

import conexion.conexion;
import DAO.ReservaDAO;
import modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaSQL implements ReservaDAO {

    @Override
    public void insertar(Reserva reserva) {
        String sql = "INSERT INTO Reserva (idCliente, idPaquete, fechaReserva, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdCliente());
            stmt.setInt(2, reserva.getIdPaquete());
            stmt.setDate(3, Date.valueOf(reserva.getFechaReserva()));
            stmt.setString(4, reserva.getEstado());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reserva obtenerPorId(int id) {
        String sql = "SELECT * FROM Reserva WHERE id = ?";
        Reserva reserva = null;

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getInt("idPaquete"),
                        rs.getDate("fechaReserva").toLocalDate(),
                        rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reserva;
    }

    @Override
    public List<Reserva> obtenerTodas() {
        String sql = "SELECT * FROM Reserva";
        List<Reserva> reservas = new ArrayList<>();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getInt("idPaquete"),
                        rs.getDate("fechaReserva").toLocalDate(),
                        rs.getString("estado")
                );
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    @Override
    public List<Reserva> obtenerPorCliente(int idCliente) {
        String sql = "SELECT * FROM Reserva WHERE idCliente = ?";
        List<Reserva> reservas = new ArrayList<>();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getInt("idPaquete"),
                        rs.getDate("fechaReserva").toLocalDate(),
                        rs.getString("estado")
                );
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    @Override
    public void actualizar(Reserva reserva) {
        String sql = "UPDATE Reserva SET idCliente = ?, idPaquete = ?, fechaReserva = ?, estado = ? WHERE id = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdCliente());
            stmt.setInt(2, reserva.getIdPaquete());
            stmt.setDate(3, Date.valueOf(reserva.getFechaReserva()));
            stmt.setString(4, reserva.getEstado());
            stmt.setInt(5, reserva.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Reserva WHERE id = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


