package ClasesDAO;

import conexion.conexion;
import DAO.ClienteDAO;
import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteSQL implements ClienteDAO {

    @Override
    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombreCompleto, email, telefono) VALUES (?, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        Cliente cliente = null;

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombreCompleto"),
                        rs.getString("email"),
                        rs.getString("telefono")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        String sql = "SELECT * FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombreCompleto"),
                        rs.getString("email"),
                        rs.getString("telefono")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombreCompleto = ?, email = ?, telefono = ? WHERE id = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            stmt.setInt(4, cliente.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Cliente WHERE id = ?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



