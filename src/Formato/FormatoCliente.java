package Formato;

import DAO.*;
import controller.plan.ControladorAgregarPlan;
import java.time.LocalDate;
import javax.swing.JComboBox;
import view.client.AddCliente;
import model.Cliente;
import model.Plan;
import model.Response;
import thanosgym.Main;

public class FormatoCliente {

    public static void cargarComboPlanes(JComboBox combo) { //*****
        CRUDPlan crudp = new CRUDPlan();
        crudp.actualizarListaPlanes();
        for (int i = 0; i < ControladorAgregarPlan.listaPlanes.size(); i++) {
            combo.addItem(ControladorAgregarPlan.listaPlanes.get(i).getName());
        }
    }

    public static void isFocusable(AddCliente vista, boolean flag) {
        vista.jtxtNombreCliente.setFocusable(flag);
        vista.jtxtDireccionCliente.setFocusable(flag);
        vista.jtxtTelefonoCliente.setFocusable(flag);
    }

    public static void leerClienteBusqueda(AddCliente vista, Cliente cli) {
        cli.setDni(Integer.parseInt(vista.jtxtDniCliente.getText()));
        cli.setNombre(vista.jtxtNombreCliente.getText());
        cli.setDireccion(vista.jtxtDireccionCliente.getText());
        cli.setTelefono(Integer.parseInt(vista.jtxtTelefonoClienteAdd.getText()));
    }

    public static Cliente leerClienteRegistro(AddCliente vista) {
        Cliente cli = new Cliente();
        CRUDPlan crudp = new CRUDPlan();
        String selectedPlanName = vista.jcbxPlanRegistro.getSelectedItem().toString();
        Response<Plan> response = crudp.read(selectedPlanName);
        Plan selectedPlan = response.getData();
        int idplan = selectedPlan.getPlanId();
        cli.setPlanId(idplan);
        cli.setDni(Integer.parseInt(vista.jtxtDniClienteAgregar.getText()));
        cli.setCreated_At(LocalDate.now()); //same?
        cli.setSubscription_since(LocalDate.now()); //same?
        cli.setNombre(vista.jtxtNombreClienteAgregar.getText());
        cli.setEmail(vista.jtxtDireccionCorreoAdd.getText());
        cli.setDireccion(vista.jtxtDireccionClienteAdd.getText());
        cli.setTelefono(Integer.parseInt(vista.jtxtTelefonoClienteAdd.getText()));
        return cli;
    }

    public static void limpiarEntradasRegistro(AddCliente vista) {
        vista.jtxtNombreClienteAgregar.setText("");
        vista.jtxtDireccionClienteAdd.setText("");
        vista.jtxtTelefonoClienteAdd.setText("");
        vista.jtxtDniClienteAgregar.requestFocus();
    }

    public static void limpiarEntradasBusqueda(AddCliente vista) {
        vista.jtxtDniCliente.setText("");
        vista.jtxtNombreCliente.setText("");
        vista.jtxtDireccionCliente.setText("");
        vista.jtxtTelefonoCliente.setText("");
        vista.jtxtDniCliente.requestFocus();
    }
}
