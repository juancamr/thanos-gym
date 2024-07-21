/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.routes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.juancamr.route.Route;
import com.juancamr.route.RoutingUtils;
import com.uni.thanosgym.controllers.ProductoController;
import com.uni.thanosgym.dao.CRUDProducto;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.dialogs.AgregarProducto;
import com.uni.thanosgym.view.dialogs.IngresosProducto;

/**
 *
 * @author juancamr
 */
@Route("main:producto*")
public class PanelProducto extends javax.swing.JPanel {
    List<Producto> productos;

    /**
     * Creates new form PanelProducto
     */
    public PanelProducto() {
        initComponents();
        this.productos = CRUDProducto.getInstance().getAll().getDataList();
        fillTable(productos);
        FrameUtils.addHandleChangeEvent(jtxtNombre, this::handleChange);
        FrameUtils.addOnClickEvent(jbtnCrearProducto, () -> {
            Map<String, Object> params = RoutingUtils.openDialog(new AgregarProducto());
            if (ProductoController.crearProducto(params)) {
                fillTable(productos);
            }
        });
    }

    public void handleChange() {
        if (jtxtNombre.getText().isEmpty()) {
            fillTable(productos);
            return;
        }
        List<Producto> datos = productos.stream()
                .filter(producto -> {
                    String nombre = producto.getNombre().toLowerCase();
                    String codigo = producto.getCodigo().toLowerCase();
                    String input = jtxtNombre.getText().toLowerCase();
                    return nombre.contains(input) || codigo.contains(input);
                }).toList();
        fillTable(datos);
    }

    private void fillTable(List<Producto> localProductos) {
        List<String[]> datos = localProductos.stream()
                .map(producto -> new String[] { String.valueOf(producto.getCodigo()), producto.getNombre() })
                .collect(Collectors.toList());
        ((javax.swing.table.DefaultTableModel) jtblProducto.getModel()).setRowCount(0);
        for (String[] dato : datos) {
            ((javax.swing.table.DefaultTableModel) jtblProducto.getModel()).addRow(dato);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        typography1 = new com.juancamr.components.Typography();
        jtxtNombre = new com.juancamr.components.InputComponent();
        typography2 = new com.juancamr.components.Typography();
        jbtnCrearProducto = new com.juancamr.components.ButtonComponent();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblProducto = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Productos");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel2.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));
        jPanel2.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 280, -1));

        typography2.setText("Nombre del producto");
        typography2.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel2.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jbtnCrearProducto.setText("CREAR");
        jbtnCrearProducto.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jbtnCrearProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnCrearProductoMouseClicked(evt);
            }
        });
        jPanel2.add(jbtnCrearProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 130, -1));

        jtblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblProducto);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 750, 470));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 690));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtblProductoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jtblProductoMouseClicked
        // GEN-FIRST:event_jtblProductoMouseClicked
        int fila = jtblProducto.getSelectedRow();
        if (fila != -1) {
            Producto producto = productos.get(fila);
            new IngresosProducto(producto);
        }

    }// GEN-LAST:event_jtblProductoMouseClicked

    private void jbtnCrearProductoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnCrearProductoMouseClicked
    }// GEN-LAST:event_jbtnCrearProductoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.juancamr.components.ButtonComponent jbtnCrearProducto;
    private javax.swing.JTable jtblProducto;
    private com.juancamr.components.InputComponent jtxtNombre;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography2;
    // End of variables declaration//GEN-END:variables
}
