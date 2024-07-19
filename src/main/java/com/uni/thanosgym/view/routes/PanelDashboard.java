/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.routes;

import com.juancamr.route.Route;
import java.util.List;
import java.util.stream.Collectors;

import com.uni.thanosgym.config.Theme;
import com.uni.thanosgym.dao.CRUDAsistencia;
import com.uni.thanosgym.dao.CRUDBoleta;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDContrato;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Boleta;
import com.uni.thanosgym.model.ReporteMensual;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.dialogs.ClientData;
import com.uni.thanosgym.view.dialogs.BoletaData;

import io.quickchart.QuickChart;

/**
 *
 * @author juancamr
 */
@Route("main:dashboard")
public class PanelDashboard extends javax.swing.JPanel {

    private List<Contrato> contratos;
    private List<Boleta> boletas;

    /**
     * Creates new form PanelDashboard
     */
    public PanelDashboard() {
        initComponents();

        // inicializar grafico
        QuickChart chart = new QuickChart();
        chart.setWidth(320);
        chart.setHeight(240);
        String[] months = new String[] { "<firstMonth>", "<secondMonth>", "<thirdMonth>", "<fourthMonth>" };
        String[] mounts = new String[] { "<firstMount>", "<secondMount>", "<thirdMount>", "<fourthMount>" };
        Response<ReporteMensual> resReporte = CRUDBoleta.getInstance().getReporteLast4Months();
        List<ReporteMensual> reportes = resReporte.getDataList();
        double maximo = resReporte.getDataList().stream().mapToDouble(ReporteMensual::getTotal).max().orElse(0) + 200;
        String config = """
                {
                          type: 'line',
                          data: {
                            labels: ['<firstMonth>', '<secondMonth>', '<thirdMonth>', '<fourthMonth>'],
                            datasets: [
                              {
                                label: 'Soles',
                                data: [<firstMount>, <secondMount>, <thirdMount>, <fourthMount>],
                              }
                            ],
                          },
                          options: {
                            scales: {
                              yAxes: [{
                                ticks: {
                                  min: 0,
                                  max: <maximo>,
                                  callback: (val) => {
                                    return 'S/' + val.toLocaleString();
                                  },
                                }
                              }]
                            }
                          },
                        }
                                                                        """;
        config = config.replace("<maximo>", String.valueOf(maximo));
        for (int i = 0; i < months.length; i++) {
            config = config.replace(months[i], String.valueOf(reportes.get(i).getMes()));
            config = config.replace(mounts[i], String.valueOf(reportes.get(i).getTotal()));
        }
        chart.setConfig(config);
        String grafico = chart.getUrl();
        FrameUtils.renderImageFromWeb(grafico, lblGrafico);

        // clientes suscritos hoy
        jlblClientesSuscritos.setForeground(Theme.colors.orange);
        int clientesSuscritos = CRUDCliente.getInstance().obtenerClientesSuscritosHoy();
        jlblClientesSuscritos.setText(String.valueOf(clientesSuscritos));

        // ganancias
        panelGanancias.setBackground(Theme.colors.blue);
        lblGanancias.setForeground(Theme.colors.primary);
        double montoMesActual = reportes.get(reportes.size() - 1).getTotal();
        double montoMesAnterior = reportes.get(reportes.size() - 2).getTotal();
        double porcentajeGanancia = 0;
        if (montoMesActual == 0 || montoMesAnterior == 0) {
            porcentajeGanancia = 0;
        } else {
            porcentajeGanancia = (montoMesActual * 100 / montoMesAnterior) - 100;
        }
        lblGanancias.setText(String.format("%% %.2f", porcentajeGanancia));
        ;

        // clientes suscritos todo el tiempo
        lblClientesTodoElTiempo.setForeground(Theme.colors.purple);
        int clientesTodoElTiempo = CRUDCliente.getInstance().obtenerClientesSuscritosTodoElTiempo();
        lblClientesTodoElTiempo.setText(String.valueOf(clientesTodoElTiempo));

        // asistencias
        int asistencias = CRUDAsistencia.getInstance().obtenerCantidadAsistenciasDeHoy();
        lblAsistencias.setText(String.valueOf(asistencias));

        fillTableClientes();
        fillTableVentas();
    }

    private void fillTableVentas() {
        try {
            Response<Boleta> resBoletas = CRUDBoleta.getInstance().obtenerUltimasTresBoletas();
            boletas = resBoletas.getDataList();
            List<String[]> datos = boletas.stream().map((boleta) -> {
                return new String[] {
                        boleta.getCliente().getFullName(),
                        String.valueOf(boleta.getTotal()),
                };
            }).collect(Collectors.toList());
            datos.forEach((dato) -> {
                ((javax.swing.table.DefaultTableModel) jtblVentas.getModel()).addRow(dato);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTableClientes() {
        Response<Contrato> resContratos = CRUDContrato.getInstance().obtenerUltimosTresContratos();
        contratos = resContratos.getDataList();
        List<String[]> datos = resContratos.getDataList().stream().map((contrato) -> {
            return new String[] {
                    contrato.getCliente().getFullName(),
                    contrato.getPlan().getName(),
            };
        }).collect(Collectors.toList());
        datos.forEach((dato) -> {
            ((javax.swing.table.DefaultTableModel) jtblCliente.getModel()).addRow(dato);
        });
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        typography1 = new com.juancamr.components.Typography();
        typography2 = new com.juancamr.components.Typography();
        contadorNotificaciones = new com.juancamr.components.Typography();
        jPanel2 = new javax.swing.JPanel();
        typography3 = new com.juancamr.components.Typography();
        lblGrafico = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        typography5 = new com.juancamr.components.Typography();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblCliente = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        typography4 = new com.juancamr.components.Typography();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblVentas = new javax.swing.JTable();
        panelGanancias = new javax.swing.JPanel();
        typography8 = new com.juancamr.components.Typography();
        typography12 = new com.juancamr.components.Typography();
        lblGanancias = new com.juancamr.components.Typography();
        jPanel6 = new javax.swing.JPanel();
        typography6 = new com.juancamr.components.Typography();
        typography13 = new com.juancamr.components.Typography();
        jlblClientesSuscritos = new com.juancamr.components.Typography();
        jPanel7 = new javax.swing.JPanel();
        typography9 = new com.juancamr.components.Typography();
        typography10 = new com.juancamr.components.Typography();
        lblAsistencias = new com.juancamr.components.Typography();
        jPanel8 = new javax.swing.JPanel();
        typography7 = new com.juancamr.components.Typography();
        typography11 = new com.juancamr.components.Typography();
        lblClientesTodoElTiempo = new com.juancamr.components.Typography();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Dashboard");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        typography2.setText("Notificaciones");
        jPanel1.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, -1, -1));

        contadorNotificaciones.setText("(0)");
        jPanel1.add(contadorNotificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography3.setText("Ingresos totales");
        typography3.setType(com.juancamr.components.Typography.Type.HEADING3);
        jPanel2.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lblGrafico.setText("grafico");
        jPanel2.add(lblGrafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 320, 240));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 365, 318));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography5.setText("Últimos clientes");
        typography5.setType(com.juancamr.components.Typography.Type.HEADING3);
        jPanel3.add(typography5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jtblCliente.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Nombres completos", "Membresía"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtblCliente);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 320, 100));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 365, 180));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography4.setText("Últimas ventas");
        typography4.setType(com.juancamr.components.Typography.Type.HEADING3);
        jPanel4.add(typography4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jtblVentas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Cliente", "Total"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblVentas);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 320, 100));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, 365, 180));

        panelGanancias.setBackground(new java.awt.Color(0, 0, 0));
        panelGanancias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography8.setForeground(new java.awt.Color(255, 255, 255));
        typography8.setText("Ganancias");
        panelGanancias.add(typography8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        typography12.setForeground(new java.awt.Color(255, 255, 255));
        typography12.setText("Este último mes");
        typography12.setType(com.juancamr.components.Typography.Type.SMALL);
        panelGanancias.add(typography12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        lblGanancias.setForeground(new java.awt.Color(255, 255, 51));
        lblGanancias.setText("40.0%");
        lblGanancias.setType(com.juancamr.components.Typography.Type.HEADING1);
        panelGanancias.add(lblGanancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanel1.add(panelGanancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 172, 150));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography6.setText("Clientes suscritos");
        jPanel6.add(typography6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        typography13.setText("Este último mes");
        typography13.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel6.add(typography13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jlblClientesSuscritos.setText("136");
        jlblClientesSuscritos.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel6.add(jlblClientesSuscritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 172, 150));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography9.setText("Asistencias");
        jPanel7.add(typography9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        typography10.setText("Clientes el día de hoy");
        typography10.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel7.add(typography10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        lblAsistencias.setText("304");
        lblAsistencias.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel7.add(lblAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 172, 150));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography7.setText("Clientes");
        jPanel8.add(typography7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        typography11.setText("Todo el tiempo");
        typography11.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel8.add(typography11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        lblClientesTodoElTiempo.setText("12.43K");
        lblClientesTodoElTiempo.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel8.add(lblClientesTodoElTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 172, 150));

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

    private void jtblClienteMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jtblClienteMouseClicked
        int rowIndex = jtblCliente.getSelectedRow();
        if (rowIndex != -1) {
            new ClientData(contratos.get(rowIndex));
        }
    }// GEN-LAST:event_jtblClienteMouseClicked

    private void jtblVentasMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jtblVentasMouseClicked
        int rowIndex = jtblVentas.getSelectedRow();
        if (rowIndex != -1) {
            new BoletaData(boletas.get(rowIndex));
        }
    }// GEN-LAST:event_jtblVentasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.juancamr.components.Typography contadorNotificaciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.juancamr.components.Typography jlblClientesSuscritos;
    private javax.swing.JTable jtblCliente;
    private javax.swing.JTable jtblVentas;
    private com.juancamr.components.Typography lblAsistencias;
    private com.juancamr.components.Typography lblClientesTodoElTiempo;
    private com.juancamr.components.Typography lblGanancias;
    private javax.swing.JLabel lblGrafico;
    private javax.swing.JPanel panelGanancias;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography10;
    private com.juancamr.components.Typography typography11;
    private com.juancamr.components.Typography typography12;
    private com.juancamr.components.Typography typography13;
    private com.juancamr.components.Typography typography2;
    private com.juancamr.components.Typography typography3;
    private com.juancamr.components.Typography typography4;
    private com.juancamr.components.Typography typography5;
    private com.juancamr.components.Typography typography6;
    private com.juancamr.components.Typography typography7;
    private com.juancamr.components.Typography typography8;
    private com.juancamr.components.Typography typography9;
    // End of variables declaration//GEN-END:variables
}
