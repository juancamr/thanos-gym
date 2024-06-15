import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.utils.TablaUtils;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class ControladorWindowClients {

    private JTable tableClients;
    private JTextField jtxtNameBuscar;
    private DefaultTableModel tableModel;

    public ControladorWindowClients(JTable tableClients, JTextField jtxtNameBuscar) {
        this.tableClients = tableClients;
        this.jtxtNameBuscar = jtxtNameBuscar;
        this.tableModel = new DefaultTableModel();

        tableClients.setModel(tableModel);

        TablaUtils.formatoTablaTodosLosProductos(tableClients); // Ajusta este método según tus necesidades

        cargarTodosLosClientes();

        jtxtNameBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarClientes();
            }
        });
    }

    private void cargarTodosLosClientes() {
        // Aquí deberías obtener todos los clientes de tu base de datos o servicio
        // Supongamos que tienes una lista de clientes llamada listaClientes

        // Limpiar tabla antes de cargar nuevos datos
        tableModel.setRowCount(0);

        // Llenar la tabla con los datos de listaClientes
        for (Cliente cliente : listaClientes) {
            tableModel.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    // Agregar más columnas según los atributos del cliente que quieras mostrar
            });
        }
    }

    private void filtrarClientes() {
        // Obtener el texto del campo de búsqueda
        String filtro = jtxtNameBuscar.getText().trim().toLowerCase();

        // Limpiar tabla antes de aplicar el filtro
        tableModel.setRowCount(0);

        // Aplicar el filtro sobre la lista de clientes y agregar los resultados al modelo de la tabla
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre().toLowerCase().contains(filtro) ||
                    cliente.getApellido().toLowerCase().contains(filtro)) {
                tableModel.addRow(new Object[]{
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        // Agregar más columnas según los atributos del cliente que quieras mostrar
                });
            }
        }
    }

    // Otros métodos adicionales según necesidades

}
