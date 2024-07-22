/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.routes;

import java.util.ArrayList;
import java.util.Date;
import com.juancamr.route.Route;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTextField;

import com.uni.thanosgym.controllers.VentaController;
import com.uni.thanosgym.dao.CRUDBoleta;
import com.uni.thanosgym.dao.CRUDDetalleBoleta;
import com.uni.thanosgym.dao.CRUDDetalleProducto;
import com.uni.thanosgym.dao.CRUDProducto;
import com.uni.thanosgym.model.Boleta;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.DetalleBoleta;
import com.uni.thanosgym.model.DetalleProducto;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.utils.Utils;
import com.uni.thanosgym.view.dialogs.IngresosProducto;
import com.uni.thanosgym.view.dialogs.ListaBoletas;

/**
 *
 * @author juancamr
 */
@Route("main:venta")
public class PanelVenta extends javax.swing.JPanel {

    private Client clienteSeleccionado;
    private Producto productoSeleccionado;
    private List<DetalleProducto> detallesDeProductoSeleccionado = new ArrayList<>();
    private int cantidadSolicitada;
    private double precioApropiado;

    private List<DetalleBoleta> detalles = new ArrayList<>();
    private List<DetallesItem> listaDetallesDeProductoGlobal = new ArrayList<>();

    /**
     * Creates new form PanelVenta
     */
    public PanelVenta() {
        initComponents();
        jlblFechaActual.setText(StringUtils.parseSpanishDate(new Date()));
        jlblRUC.setText(Utils.RUC);
        jlblTotal.setText("S/ 0.00");
        jlblNumeroBoleta.setText(StringUtils.parseIdBoleta(CRUDBoleta.getInstance().getUltimoNumeroBoleta() + 1));

        FrameUtils.addOnClickEvent(jbtnAgregarProducto, this::agregarProducto);
        FrameUtils.addOnClickEvent(jbtnTodasLasVentas, this::mostrarVentas);
        FrameUtils.addOnClickEvent(jbtnSetCLiente, this::setCliente);
        FrameUtils.addOnClickEvent(jbtnSetProducto, this::setProducto);
        FrameUtils.addOnClickEvent(jbtnClear, this::eliminarProductos);
        FrameUtils.addOnClickEvent(jbtnGenerarVenta, this::generarVenta);
        FrameUtils.addOnClickEvent(jbtnBorrarUno, this::borrarUno);
    }

    private void borrarUno() {
        int row = jtblBoleta.getSelectedRow();
        if (row == -1) {
            Messages.show("Seleccione una fila");
            return;
        }
        ((javax.swing.table.DefaultTableModel) jtblBoleta.getModel()).removeRow(row);
        detalles.remove(row);
        listaDetallesDeProductoGlobal.remove(row);
    }

    private void agregarProducto() {
        if (productoSeleccionado == null) {
            Messages.show("Debe seleccionar un producto");
            return;
        }
        if (cantidadSolicitada <= 0) {
            Messages.show("La cantidad debe ser mayor a 0");
            return;
        }
        DetalleBoleta detalle = new DetalleBoleta.Builder()
                .setCantidad(cantidadSolicitada)
                .setProducto(productoSeleccionado)
                .setPrecio(precioApropiado)
                .build();

        // clear inputs
        JTextField[] inputs = new JTextField[] { jtxtCantidadProducto, jtxtNombreProducto, jtxtPrecioProducto,
                jtxtCodigoProducto };
        FrameUtils.clearInputs(inputs);

        detalles.add(detalle);
        List<Integer> ids = detallesDeProductoSeleccionado.stream().map(DetalleProducto::getId)
                .collect(Collectors.toList());
        DetallesItem datelleItem = new DetallesItem(ids, cantidadSolicitada, productoSeleccionado);
        listaDetallesDeProductoGlobal.add(datelleItem);

        String[] row = new String[] { String.valueOf(detalle.getCantidad()), detalle.getProducto().getNombre(),
                String.valueOf(detalle.getPrecio()), String.valueOf(detalle.getTotal()) };

        ((javax.swing.table.DefaultTableModel) jtblBoleta.getModel()).addRow(row);
        updateMontoTotal();

        productoSeleccionado = null;
        detallesDeProductoSeleccionado.clear();
        cantidadSolicitada = 0;
        precioApropiado = 0;
    }

    private void mostrarVentas() {
        Response<Boleta> response = CRUDBoleta.getInstance().getAllBoletas();
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        new ListaBoletas(response.getDataList());
    }

    private void generarVenta() {
        if (clienteSeleccionado == null) {
            Messages.show("Debe establecer un cliente");
            return;
        }
        if (detalles.isEmpty()) {
            Messages.show("Debe agregar al menos un producto");
            return;
        }

        double total = detalles.stream().mapToDouble(DetalleBoleta::getPrecio).sum();
        Boleta boleta = new Boleta.Builder()
                .setCliente(clienteSeleccionado)
                .setAdmin(UserPreferences.getData())
                .setTotal(total)
                .build();

        jbtnGenerarVenta.setEnabled(false);
        Utils.mostrarPantallaDeCarga(null, () -> {
            Response<Boleta> response = CRUDBoleta.getInstance().create(boleta);
            if (!response.isSuccess()) {
                Messages.show(response.getMessage());
                jbtnGenerarVenta.setEnabled(true);
                return;
            }
            boleta.setId(response.getId());

            for (DetalleBoleta detalle : detalles) {
                detalle.setIdBoleta(boleta.getId());
                CRUDDetalleBoleta.getInstance().create(detalle);
            }
            boleta.setDetallesBoleta(detalles);

            String message = "Se generó la venta correctamente\n\nPor favor, busque lo siguiente en el almacén:\n";
            for (DetallesItem detalleItem : listaDetallesDeProductoGlobal) {
                Response<Integer> resDescuento = CRUDDetalleProducto.getInstance()
                        .descontar(detalleItem.getListaIds(), detalleItem.getCantidad());
                message += String.format("\nCódigos de ingresos del producto %s a retirar:\n",
                        detalleItem.getProducto().getNombre());
                for (int i = 0; i < resDescuento.getDataList().size(); i++) {
                    message += String.format("Código: %d\t Stock a retirar: %d", detalleItem.getListaIds().get(i), resDescuento.getDataList().get(i));
                }
            }
            Messages.show(message);

            VentaController.enviarCorreo(boleta);

            // clear
            detalles.clear();
            ((javax.swing.table.DefaultTableModel) jtblBoleta.getModel()).setRowCount(0);
            clienteSeleccionado = null;
            jtxtNombreCliente.setText("");
            jtxtDireccionCiente.setText("");
            jlblDniCliente.setText("");
            jtxtDniCliente.setText("");

            jlblTotal.setText("S/ 0.00");
            listaDetallesDeProductoGlobal.clear();
            updateMontoTotal();
            jbtnGenerarVenta.setEnabled(true);
        });
    }

    private void setProducto() {
        String codigo = jtxtCodigoProducto.getText();
        String stock = jtxtCantidadProducto.getText();
        if (codigo.isEmpty() || stock.isEmpty()) {
            Messages.show("Debe completar el codigo y la cantidad");
            return;
        }

        boolean existe = detalles.stream().anyMatch(detalle -> detalle.getProducto().getCodigo().equals(codigo));
        if (existe) {
            Messages.show(
                    "Estas intentando agregar un producto agregado previamente, por favor, eliminalo y vuelve a intentarlo");
            return;
        }

        if (!StringUtils.isInteger(stock)) {
            Messages.show("La cantidad debe ser un número");
            return;
        }
        cantidadSolicitada = Integer.parseInt(stock);
        if (cantidadSolicitada <= 0) {
            Messages.show("La cantidad debe ser mayor a 0");
            return;
        }

        Response<Producto> resProd = CRUDProducto.getInstance().getByCodigo(codigo);
        if (!resProd.isSuccess()) {
            Messages.show(resProd.getMessage());
            return;
        }
        productoSeleccionado = resProd.getData();

        Response<DetalleProducto> resDet = CRUDDetalleProducto.getInstance()
                .obtenerProductosParaVenta(productoSeleccionado.getId(), cantidadSolicitada);
        if (!resDet.isSuccess()) {
            Messages.show(resDet.getMessage());
            return;
        }
        detallesDeProductoSeleccionado = resDet.getDataList();
        precioApropiado = detallesDeProductoSeleccionado.stream().mapToDouble(DetalleProducto::getPrecio).max()
                .orElse(0);

        jtxtNombreProducto.setText(productoSeleccionado.getNombre());
        jtxtPrecioProducto.setText(String.valueOf(precioApropiado));
    }

    private void updateMontoTotal() {
        double total = 0;
        for (DetalleBoleta detalle : detalles) {
            total += detalle.getPrecio() * detalle.getCantidad();
        }
        jlblTotal.setText(String.format("S/ %.2f", total));
    }

    private void eliminarProductos() {
        if (Messages.confirm("¿Está seguro de que desea eliminar todos los productos agregados?", "Confirmación")) {
            detalles.clear();
            listaDetallesDeProductoGlobal.clear();
            ((javax.swing.table.DefaultTableModel) jtblBoleta.getModel()).setRowCount(0);
        }
    }

    private void setCliente() {
        Client cliente = VentaController.setCliente(jtxtDniCliente.getText());
        if (cliente == null) {
            Messages.show("El cliente no existe");
            return;
        }
        jtxtNombreCliente.setText(cliente.getFullName());
        jtxtDireccionCiente.setText(cliente.getDireccion());
        jlblDniCliente.setText(cliente.getDni());
        clienteSeleccionado = cliente;
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typography11 = new com.juancamr.components.Typography();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        typography1 = new com.juancamr.components.Typography();
        typography2 = new com.juancamr.components.Typography();
        jtxtDniCliente = new com.juancamr.components.InputComponent();
        jbtnSetCLiente = new com.juancamr.components.ButtonComponent();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblBoleta = new javax.swing.JTable();
        typography3 = new com.juancamr.components.Typography();
        typography4 = new com.juancamr.components.Typography();
        typography5 = new com.juancamr.components.Typography();
        typography6 = new com.juancamr.components.Typography();
        jbtnGenerarVenta = new com.juancamr.components.ButtonComponent();
        jlblTotal = new com.juancamr.components.Typography();
        jlblFechaActual = new com.juancamr.components.Typography();
        jtxtNombreCliente = new com.juancamr.components.Typography();
        jtxtDireccionCiente = new com.juancamr.components.Typography();
        jlblDniCliente = new com.juancamr.components.Typography();
        jPanel2 = new javax.swing.JPanel();
        jlblNumeroBoleta = new com.juancamr.components.Typography();
        jlblRUC = new com.juancamr.components.Typography();
        typography15 = new com.juancamr.components.Typography();
        jPanel3 = new javax.swing.JPanel();
        typography13 = new com.juancamr.components.Typography();
        jtxtNombreProducto = new com.juancamr.components.InputComponent();
        typography14 = new com.juancamr.components.Typography();
        jtxtPrecioProducto = new com.juancamr.components.InputComponent();
        jtxtCantidadProducto = new com.juancamr.components.InputComponent();
        typography16 = new com.juancamr.components.Typography();
        typography17 = new com.juancamr.components.Typography();
        jbtnSetProducto = new com.juancamr.components.ButtonComponent();
        typography18 = new com.juancamr.components.Typography();
        jtxtCodigoProducto = new com.juancamr.components.InputComponent();
        jbtnAgregarProducto = new com.juancamr.components.ButtonComponent();
        jbtnTodasLasVentas = new com.juancamr.components.ButtonComponent();
        jbtnClear = new com.juancamr.components.ButtonComponent();
        jbtnBorrarUno = new com.juancamr.components.ButtonComponent();

        typography11.setText("DNI del cliente");
        typography11.setType(com.juancamr.components.Typography.Type.SMALL);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Generar venta");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        typography2.setText("DNI del cliente");
        typography2.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));
        jPanel1.add(jtxtDniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 310, -1));

        jbtnSetCLiente.setText("ESTABLECER");
        jbtnSetCLiente.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnSetCLiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        jtblBoleta.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Cantidad", "Descripción", "Precio Unid.", "Total"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtblBoleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblBoletaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblBoleta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 530, 410));

        typography3.setText("Fecha");
        typography3.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        typography4.setText("Cliente");
        typography4.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(typography4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        typography5.setText("Dirección");
        typography5.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(typography5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        typography6.setText("DNI");
        typography6.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(typography6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jbtnGenerarVenta.setText("GENERAR");
        jbtnGenerarVenta.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel1.add(jbtnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 620, 220, 40));

        jlblTotal.setText("S/ 1200.0");
        jlblTotal.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel1.add(jlblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 580, -1, -1));

        jlblFechaActual.setText("Fecha actual");
        jlblFechaActual.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jlblFechaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 120, 15));

        jtxtNombreCliente.setText("Nombre del cliente");
        jtxtNombreCliente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jtxtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, 15));

        jtxtDireccionCiente.setText("Direccion del cliente");
        jtxtDireccionCiente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jtxtDireccionCiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 240, 15));

        jlblDniCliente.setText("DNI del cliente");
        jlblDniCliente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jlblDniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 120, 15));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblNumeroBoleta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblNumeroBoleta.setText("typography13");
        jlblNumeroBoleta.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblNumeroBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 130, -1));

        jlblRUC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblRUC.setText("typography13");
        jlblRUC.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, -1));

        typography15.setForeground(new java.awt.Color(255, 255, 255));
        typography15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typography15.setText("BOLETA DE VENTA");
        typography15.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(typography15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, -1));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 170, Short.MAX_VALUE));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 20, Short.MAX_VALUE));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 26, 170, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 170, 70));

        typography13.setText("Nombre");
        typography13.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography13, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, -1, -1));

        jtxtNombreProducto.setEnabled(false);
        jPanel1.add(jtxtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 220, -1));

        typography14.setText("Precio");
        typography14.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, -1, -1));

        jtxtPrecioProducto.setEnabled(false);
        jtxtPrecioProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPrecioProductoActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 100, -1));
        jPanel1.add(jtxtCantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 320, 100, -1));

        typography16.setText("Cantidad");
        typography16.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography16, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, -1, -1));

        typography17.setText("Producto");
        typography17.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel1.add(typography17, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, -1, -1));

        jbtnSetProducto.setText("SET");
        jbtnSetProducto.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnSetProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 220, -1, -1));

        typography18.setText("Código");
        typography18.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography18, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));
        jPanel1.add(jtxtCodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 130, -1));

        jbtnAgregarProducto.setText("AGREGAR");
        jbtnAgregarProducto.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 370, -1, -1));

        jbtnTodasLasVentas.setText("TODAS LAS VENTAS");
        jbtnTodasLasVentas.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnTodasLasVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, -1, -1));

        jbtnClear.setText("Borrar Todo");
        jbtnClear.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, 220, -1));

        jbtnBorrarUno.setText("Borrar un elemento");
        jbtnBorrarUno.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnBorrarUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 470, 220, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 840,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
    }// </editor-fold>//GEN-END:initComponents

    private void jtblBoletaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jtblBoletaMouseClicked
        int row = jtblBoleta.getSelectedRow();
        if (row == -1)
            return;
        // mostrar todos los ingresos de este producto
        Producto producto = detalles.get(row).getProducto();
        new IngresosProducto(producto);
    }// GEN-LAST:event_jtblBoletaMouseClicked

    private void jtxtPrecioProductoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtxtPrecioProductoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jtxtPrecioProductoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private com.juancamr.components.ButtonComponent jbtnAgregarProducto;
    private com.juancamr.components.ButtonComponent jbtnBorrarUno;
    private com.juancamr.components.ButtonComponent jbtnClear;
    private com.juancamr.components.ButtonComponent jbtnGenerarVenta;
    private com.juancamr.components.ButtonComponent jbtnSetCLiente;
    private com.juancamr.components.ButtonComponent jbtnSetProducto;
    private com.juancamr.components.ButtonComponent jbtnTodasLasVentas;
    private com.juancamr.components.Typography jlblDniCliente;
    private com.juancamr.components.Typography jlblFechaActual;
    private com.juancamr.components.Typography jlblNumeroBoleta;
    private com.juancamr.components.Typography jlblRUC;
    private com.juancamr.components.Typography jlblTotal;
    private javax.swing.JTable jtblBoleta;
    private com.juancamr.components.InputComponent jtxtCantidadProducto;
    private com.juancamr.components.InputComponent jtxtCodigoProducto;
    private com.juancamr.components.Typography jtxtDireccionCiente;
    private com.juancamr.components.InputComponent jtxtDniCliente;
    private com.juancamr.components.Typography jtxtNombreCliente;
    private com.juancamr.components.InputComponent jtxtNombreProducto;
    private com.juancamr.components.InputComponent jtxtPrecioProducto;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography11;
    private com.juancamr.components.Typography typography13;
    private com.juancamr.components.Typography typography14;
    private com.juancamr.components.Typography typography15;
    private com.juancamr.components.Typography typography16;
    private com.juancamr.components.Typography typography17;
    private com.juancamr.components.Typography typography18;
    private com.juancamr.components.Typography typography2;
    private com.juancamr.components.Typography typography3;
    private com.juancamr.components.Typography typography4;
    private com.juancamr.components.Typography typography5;
    private com.juancamr.components.Typography typography6;
    // End of variables declaration//GEN-END:variables

    private class DetallesItem {
        private List<Integer> lista;
        private int cantidad;
        private Producto producto;

        public DetallesItem(List<Integer> lista, int cantidad, Producto producto) {
            this.lista = lista;
            this.cantidad = cantidad;
            this.producto = producto;
        }

        public List<Integer> getListaIds() {
            return lista;
        }

        public int getCantidad() {
            return cantidad;
        }

        public Producto getProducto() {
            return producto;
        }
    }

}
