/*
 *
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;

import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.model.Plan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.juancamr.components.Typography;
import com.juancamr.route.RoutingUtils;
import com.uni.thanosgym.config.Theme;
import com.uni.thanosgym.dao.CRUDAsistencia;
import com.uni.thanosgym.dao.CRUDBoleta;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDContrato;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Asistencia;
import com.uni.thanosgym.model.Boleta;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.utils.FrameUtils;

/**
 *
 * @author juancamr
 */
public class ClientData extends javax.swing.JFrame {
    private Contrato contrato;
    private List<Asistencia> asistencias;
    private Client cliente;

    /**
     * Creates new form ClientData
     */
    public ClientData(Contrato contrato) {
        Response<Asistencia> resAsistencias = CRUDAsistencia.getInstance()
                .getAllByClientId(contrato.getCliente().getId());
        this.asistencias = resAsistencias.getDataList();
        this.cliente = contrato.getCliente();

        initComponents();
        this.contrato = contrato;
        Client client = contrato.getCliente();
        inputDni.setText(client.getDni());
        if (client.getFullName().contains(" ")) {
            String[] names = client.getFullName().split(" ");
            jlblNombre.setText(names[0] + " " + names[1]);
        } else {
            jlblNombre.setText(client.getFullName());
        }
        inputNombres.setText(client.getFullName());
        inputTelefono.setText(client.getPhone());
        inputDireccion.setText(client.getDireccion());
        inputCorreo.setText(client.getEmail());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jlblPlan.setText(contrato.getPlan().getName());
        jlblSubscriptionSince.setText("Desde: " + StringUtils.parseSpanishDate(contrato.getCreatedAt()));

        refreshEstado();
        jpnlAsistencias.setLayout(null);

        // events
        FrameUtils.addOnClickEvent(jbtnCongelar, this::congelarODescongelar);
        FrameUtils.addOnClickEvent(jbtnAsistencias, () -> {
            new ListaAsistencias(asistencias);
        });
        FrameUtils.addOnClickEvent(jbtnRenovar, this::renovar);
        FrameUtils.addOnClickEvent(jbtnContratos, this::mostrarContratos);
        FrameUtils.addOnClickEvent(jbtnBoletas, this::mostrarBoletas);
        FrameUtils.addOnClickEvent(jbtnMarcarAsistencia, this::marcarAsistencia);
        FrameUtils.addOnClickEvent(jbtnEditar, this::editar);

        paintAsistencias();
        FrameUtils.setupWindow(this);
    }

    private void editar() {
        inputNombres.setEnabled(true);
        inputTelefono.setEnabled(true);
        inputDireccion.setEnabled(true);
        inputCorreo.setEnabled(true);
        FrameUtils.removeAllEvents(jbtnEditar);
        jbtnEditar.setText("ACTUALIZAR");
        FrameUtils.addOnClickEvent(jbtnEditar, this::actualizar);
    }

    private void actualizar() {
        cliente.setFullName(inputNombres.getText());
        cliente.setPhone(inputTelefono.getText());
        cliente.setDireccion(inputDireccion.getText());
        cliente.setEmail(inputCorreo.getText());
        Response<Client> res = CRUDCliente.getInstance().update(cliente);
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return;
        }

        inputNombres.setEnabled(false);
        inputTelefono.setEnabled(false);
        inputDireccion.setEnabled(false);
        inputCorreo.setEnabled(false);

        FrameUtils.removeAllEvents(jbtnEditar);
        jbtnEditar.setText("EDITAR");
        FrameUtils.addOnClickEvent(jbtnEditar, this::editar);
    }

    private void marcarAsistencia() {
        if (contrato.isFrozen()) {
            Messages.show("No puedes marcar asistencias en contratos congelados");
            return;
        }
        if (!asistencias.isEmpty()) {
            boolean iguales = StringUtils.parseDate(asistencias.get(0).getIngreso())
                    .equals(StringUtils.parseDate(new Date()));
            if (iguales) {
                Messages.show("Ya has marcado esta asistencia");
                return;
            }
        }
        Asistencia asistencia = new Asistencia.Builder()
                .setCliente(cliente)
                .setIngreso(DateUtils.addDays(new Date(), 1))
                .build();
        Response<Asistencia> res = CRUDAsistencia.getInstance().create(asistencia);
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return;
        }
        Messages.show("Se ha marcado la asistencia");
        asistencias.add(asistencia);
        paintAsistencias();
        revalidate();
        repaint();
    }

    private void paintAsistencias() {
        jpnlAsistencias.removeAll();
        double width = jpnlAsistencias.getSize().getWidth();
        double height = jpnlAsistencias.getSize().getHeight();
        int margin = 15;

        List<Asistencia> past14Asistencias = CRUDAsistencia.getInstance().getUltimasCatorceAsistencias(cliente.getId())
                .getDataList();
        past14Asistencias = past14Asistencias.reversed();

        List<Date> past14Dates = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            past14Dates.add(DateUtils.dateMinus(new Date(), i));
        }
        past14Dates = past14Dates.reversed();

        int tamanioCuadradoX = (int) width / 7 - margin * 2;
        int tamanioCuadradoY = (int) height / 2 - margin;

        int y = margin;
        int start = 0;
        for (int i = 0; i < 14; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            Typography fecha = new Typography();
            fecha.setType(Typography.Type.BODY);
            fecha.setText(StringUtils.parseToDayAndMonth(past14Dates.get(i)));
            if (past14Asistencias.get(i).getIngreso() == null) {
                panel.setBackground(Theme.colors.grayCenizo);
            } else {
                panel.setBackground(Theme.colors.green);
            }
            if (i == 7) {
                y += tamanioCuadradoY + 10;
            }
            if (i < 7) {
                panel.setBounds(margin + i * (tamanioCuadradoX + margin), y, tamanioCuadradoX, tamanioCuadradoY);
            } else {
                panel.setBounds(margin + start * (tamanioCuadradoX + margin), y, tamanioCuadradoX, tamanioCuadradoY);
                start++;
            }
            panel.add(fecha);

            jpnlAsistencias.add(panel);
        }
    }

    private void renovar() {
        if (contrato.isFrozen()) {
            Messages.show("No puedes renovar un contrato conocido");
            return;
        }
        if (!contrato.getEstado().equals("Vencido")) {
            Messages.show("No puedes renovar un contrato en estado activo");
            return;
        }
        Map<String, Object> params = RoutingUtils.openDialog(this, new RenovarDialog());
        if (params == null) {
            return;
        }
        int planId = (int) params.get("plan_item");
        String codigoTransaccion = (String) params.get("codigo");
        if (StringUtils.isInteger(codigoTransaccion)) {
            Messages.show("El código de transacción debe ser un número");
            return;
        }
        if (codigoTransaccion.isEmpty()) {
            Messages.show("Ingrese un código de transacción");
            return;
        }
        Response<Plan> resPlan = CRUDPlan.getInstance().getById(planId);
        if (!resPlan.isSuccess()) {
            Messages.show(resPlan.getMessage());
            return;
        }
        Contrato contratoRenovado = new Contrato.Builder()
                .setCliente(cliente)
                .setPlan(resPlan.getData())
                .setAdmin(UserPreferences.getData())
                .setTransactionCode(codigoTransaccion)
                .setSubscriptionUntil(DateUtils.addDays(new Date(), resPlan.getData().getDurationDays()))
                .build();
        Response<Contrato> resContrato = CRUDContrato.getInstance().create(contratoRenovado);

        if (!resContrato.isSuccess()) {
            Messages.show(resContrato.getMessage());
            return;
        }

    }

    private void refreshEstado() {
        if (contrato.isFrozen()) {
            jbtnCongelar.setText("DESCONGELAR");
        } else {
            jbtnCongelar.setText("CONGELAR");
        }

        if (contrato.getEstado().equals("Vencido")) {
            jbtnCongelar.setVisible(false);
        }

        String estado = contrato.getEstado();
        jlblEstado.setText(estado);
        Color backgroundEstado = estado.equals("Congelado") ? Color.BLUE
                : estado.equals("Vencido") ? Color.RED : Theme.colors.green;

        jpnlEstado.setBackground(backgroundEstado);
        revalidate();
        repaint();
    }

    private void congelarODescongelar() {
        if (!contrato.isFrozen() && contrato.getFreezeCount() > 3) {
            Messages.show("No puedes congelar más de 3 veces");
            return;
        }
        if (!contrato.isFrozen()) {
            Map<String, Object> params = RoutingUtils.openDialog(this, new Congelar());
            if (params == null) {
                return;
            }
            String dias = (String) params.get("dias");
            if (!StringUtils.isInteger(dias)) {
                Messages.show("El campo de días debe ser un número");
                return;
            }
            int cantidadDias = Integer.parseInt(dias);
            if (cantidadDias <= 0 || cantidadDias > 15) {
                Messages.show("El número de días debe ser mayor que 0 y menor que 15");
                return;
            }
            boolean confirmacion = Messages.confirm(
                    "¿Estás seguro que quieres congelar el contrato por " + cantidadDias + " días?", "Descongelar");
            if (!confirmacion) {
                return;
            }
            contrato.setIsFrozen(true);
            contrato.setSubscriptionUntil(DateUtils.addDays(contrato.getSubscriptionUntil(), cantidadDias));
            contrato.setLastFreezeDate(new Date());
            contrato.increaseFreezeCount();
        } else {
            boolean confirmacion = Messages.confirm("¿Estás seguro que quieres descongelar el contrato hoy?",
                    "Descongelar");
            if (!confirmacion) {
                return;
            }
            int diffDays = DateUtils.getDaysBetween(new Date(), contrato.getFreezeUntil());
            contrato.setSubscriptionUntil(DateUtils.dateMinus(contrato.getSubscriptionUntil(), diffDays));
            contrato.setIsFrozen(false);
        }

        Response<Contrato> res = CRUDContrato.getInstance().congelarODescongelar(contrato);
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return;
        }
        refreshEstado();
    }

    private void mostrarContratos() {
        Response<Contrato> res = CRUDContrato.getInstance().getByClienteId(contrato.getCliente().getId());
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return;
        }
        new ListaContratos(res.getDataList());
    }

    private void mostrarBoletas() {
        Response<Boleta> res = CRUDBoleta.getInstance().getBoletasByIdCliente(contrato.getCliente().getId());
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return;
        }
        new ListaBoletas(res.getDataList());
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        inputDni = new com.juancamr.components.InputComponent();
        typography2 = new com.juancamr.components.Typography();
        typography3 = new com.juancamr.components.Typography();
        inputNombres = new com.juancamr.components.InputComponent();
        typography4 = new com.juancamr.components.Typography();
        inputDireccion = new com.juancamr.components.InputComponent();
        typography6 = new com.juancamr.components.Typography();
        jlblNombre = new com.juancamr.components.Typography();
        inputTelefono = new com.juancamr.components.InputComponent();
        jPanel2 = new javax.swing.JPanel();
        jlblPlan = new com.juancamr.components.Typography();
        jlblSubscriptionSince = new com.juancamr.components.Typography();
        jpnlAsistencias = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        typography1 = new com.juancamr.components.Typography();
        jbtnEditar = new com.juancamr.components.ButtonComponent();
        jbtnCongelar = new com.juancamr.components.ButtonComponent();
        jbtnRenovar = new com.juancamr.components.ButtonComponent();
        jbtnBoletas = new com.juancamr.components.ButtonComponent();
        jbtnContratos = new com.juancamr.components.ButtonComponent();
        jbtnAsistencias = new com.juancamr.components.ButtonComponent();
        typography8 = new com.juancamr.components.Typography();
        typography9 = new com.juancamr.components.Typography();
        inputCorreo = new com.juancamr.components.InputComponent();
        jpnlEstado = new javax.swing.JPanel();
        jlblEstado = new com.juancamr.components.Typography();
        jbtnMarcarAsistencia = new com.juancamr.components.ButtonComponent();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputDni.setBackground(new java.awt.Color(250, 250, 250));
        inputDni.setEnabled(false);
        jPanel1.add(inputDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 240, -1));

        typography2.setText("DNI");
        typography2.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel1.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        typography3.setText("Nombres completos");
        typography3.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel1.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        inputNombres.setBackground(new java.awt.Color(250, 250, 250));
        inputNombres.setEnabled(false);
        jPanel1.add(inputNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 240, -1));

        typography4.setText("Teléfono");
        typography4.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel1.add(typography4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        inputDireccion.setBackground(new java.awt.Color(250, 250, 250));
        inputDireccion.setEnabled(false);
        jPanel1.add(inputDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 240, -1));

        typography6.setText("Dirección");
        typography6.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel1.add(typography6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, -1));

        jlblNombre.setText("typography7");
        jlblNombre.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel1.add(jlblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        inputTelefono.setBackground(new java.awt.Color(250, 250, 250));
        inputTelefono.setEnabled(false);
        jPanel1.add(inputTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 240, -1));

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblPlan.setText("typography5");
        jlblPlan.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel2.add(jlblPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jlblSubscriptionSince.setText("typography5");
        jlblSubscriptionSince.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel2.add(jlblSubscriptionSince, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 330, 110));

        jpnlAsistencias.setBackground(new java.awt.Color(250, 250, 250));

        javax.swing.GroupLayout jpnlAsistenciasLayout = new javax.swing.GroupLayout(jpnlAsistencias);
        jpnlAsistencias.setLayout(jpnlAsistenciasLayout);
        jpnlAsistenciasLayout.setHorizontalGroup(
                jpnlAsistenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 730, Short.MAX_VALUE));
        jpnlAsistenciasLayout.setVerticalGroup(
                jpnlAsistenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 120, Short.MAX_VALUE));

        jPanel1.add(jpnlAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 730, 120));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("ACCIONES");
        typography1.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel4.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jbtnEditar.setText("EDITAR");
        jbtnEditar.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel4.add(jbtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 130, -1));

        jbtnCongelar.setText("CONGELAR");
        jbtnCongelar.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel4.add(jbtnCongelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, -1));

        jbtnRenovar.setText("RENOVAR");
        jbtnRenovar.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel4.add(jbtnRenovar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 130, -1));

        jbtnBoletas.setText("BOLETAS");
        jbtnBoletas.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel4.add(jbtnBoletas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 130, -1));

        jbtnContratos.setText("CONTRATOS");
        jbtnContratos.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel4.add(jbtnContratos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 130, -1));

        jbtnAsistencias.setText("ASISTENCIAS");
        jbtnAsistencias.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel4.add(jbtnAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 130, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 170, 450));

        typography8.setText("DATOS DEL CLIENTE");
        typography8.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel1.add(typography8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        typography9.setText("Correo");
        typography9.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel1.add(typography9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, -1));

        inputCorreo.setBackground(new java.awt.Color(250, 250, 250));
        inputCorreo.setEnabled(false);
        jPanel1.add(inputCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 240, -1));

        jpnlEstado.setBackground(new java.awt.Color(0, 255, 51));
        jpnlEstado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblEstado.setForeground(new java.awt.Color(255, 255, 255));
        jlblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblEstado.setText("ACTIVA");
        jlblEstado.setType(com.juancamr.components.Typography.Type.HEADING2);
        jpnlEstado.add(jlblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 170, 40));

        jPanel1.add(jpnlEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, 170, 60));

        jbtnMarcarAsistencia.setText("MARCAR ASISTENCIA");
        jbtnMarcarAsistencia.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnMarcarAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, 170, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.juancamr.components.InputComponent inputCorreo;
    private com.juancamr.components.InputComponent inputDireccion;
    private com.juancamr.components.InputComponent inputDni;
    private com.juancamr.components.InputComponent inputNombres;
    private com.juancamr.components.InputComponent inputTelefono;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.juancamr.components.ButtonComponent jbtnAsistencias;
    private com.juancamr.components.ButtonComponent jbtnBoletas;
    private com.juancamr.components.ButtonComponent jbtnCongelar;
    private com.juancamr.components.ButtonComponent jbtnContratos;
    private com.juancamr.components.ButtonComponent jbtnEditar;
    private com.juancamr.components.ButtonComponent jbtnMarcarAsistencia;
    private com.juancamr.components.ButtonComponent jbtnRenovar;
    private com.juancamr.components.Typography jlblEstado;
    private com.juancamr.components.Typography jlblNombre;
    private com.juancamr.components.Typography jlblPlan;
    private com.juancamr.components.Typography jlblSubscriptionSince;
    private javax.swing.JPanel jpnlAsistencias;
    private javax.swing.JPanel jpnlEstado;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography2;
    private com.juancamr.components.Typography typography3;
    private com.juancamr.components.Typography typography4;
    private com.juancamr.components.Typography typography6;
    private com.juancamr.components.Typography typography8;
    private com.juancamr.components.Typography typography9;
    // End of variables declaration//GEN-END:variables
}
