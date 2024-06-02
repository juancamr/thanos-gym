package DAO;

import java.util.ArrayList;
import java.util.List;
import model.Response;
import model.Cliente;

public class CRUDCliente extends BaseCrud {

    private static CRUDCliente crudCliente;

    public CRUDCliente() {
    }

    public static CRUDCliente getInstance() {
        if (crudCliente == null) {
            crudCliente = new CRUDCliente();
        }
        return crudCliente;
    }

    public Response<Cliente> create(Cliente cliente) {
        String con = "SELECT * FROM client WHERE email='<email>'";
        con = con.replace("<email>", cliente.getEmail());
        try {
            rs = st.executeQuery(con);
            if (!rs.next()) {
                String consulta = "INSERT INTO client(plan_id, dni, created_at, subscription_since, full_name, email, address, phone) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                try {
                    ps = connection.prepareStatement(consulta);
                    ps.setInt(1, cliente.getPlanId());
                    ps.setInt(2, cliente.getDni());
                    ps.setDate(3, java.sql.Date.valueOf(cliente.getCreated_At()));
                    ps.setDate(4, java.sql.Date.valueOf(cliente.getSubscription_since()));
                    ps.setString(5, cliente.getNombre());
                    ps.setString(6, cliente.getEmail());
                    ps.setString(7, cliente.getDireccion());
                    ps.setInt(8, cliente.getTelefono());
                    ps.executeUpdate();
                    ps.close();
                    return new Response(true, "Se creó el cliente con éxito");
                } catch (Exception e) {
                    System.out.println(e);
                    return new Response(false, "Algo salió mal al crear un nuevo cliente");
                }
            } else {
                return new Response<>(false, "El cliente con email " + cliente.getEmail() + " ya existe");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response(false, "Algo salió mal");
        }
    }

    public Response<Cliente> read(int dni) {
        String consulta = "SELECT * FROM client WHERE dni = ?";
        try {
            ps = connection.prepareStatement(consulta);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("client_id"),
                        rs.getInt("plan_id"),
                        rs.getInt("dni"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getDate("subscription_since").toLocalDate(),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getInt("phone")
                );
                return new Response(true, "Cliente encontrado", cliente);
            } else {
                return new Response(false, "Cliente no encontrado");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response(false, "Algo salio mal");
        }
    }

    public Response<List<Cliente>> readAll() {
        String consulta = "SELECT * FROM client";
        List<Cliente> clientes = new ArrayList<>();
        try {
            st = connection.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("client_id"),
                        rs.getInt("plan_id"),
                        rs.getInt("dni"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getDate("subscription_since").toLocalDate(),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getInt("phone")
                );
                clientes.add(cliente);
            }
            return new Response(true, "Clientes obtenidos", clientes);
        } catch (Exception e) {
            System.out.println(e);
            return new Response(false, "Algo salio mal");
        }
    }

    public Response<Cliente> update(Cliente cliente) {
        String consulta = "UPDATE client SET plan_id = ?, dni = ?, created_at = ?, subscription_since = ?, full_name = ?, email = ?, address = ?, phone = ? WHERE client_id = ?";
        try {
            ps = connection.prepareStatement(consulta);
            ps.setInt(1, cliente.getPlanId());
            ps.setInt(2, cliente.getDni());
            ps.setDate(3, java.sql.Date.valueOf(cliente.getCreated_At()));
            ps.setDate(4, java.sql.Date.valueOf(cliente.getSubscription_since()));
            ps.setString(5, cliente.getNombre());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getDireccion());
            ps.setInt(8, cliente.getTelefono());
            ps.setInt(9, cliente.getClienteId());
            ps.executeUpdate();
            ps.close();
            return new Response(true, "Cliente actualizado con éxito");
        } catch (Exception e) {
            System.out.println(e);
            return new Response(false, "Algo salio mal");
        }
    }

    public Response<Cliente> delete(int clienteId) {
        String consulta = "DELETE FROM client WHERE client_id = ?";
        try {
            ps = connection.prepareStatement(consulta);
            ps.setInt(1, clienteId);
            ps.executeUpdate();
            ps.close();
            return new Response(true, "Cliente eliminado con éxito");
        } catch (Exception e) {
            System.out.println(e);
            return new Response(false, "Algo salio mal");
        }
    }
}
