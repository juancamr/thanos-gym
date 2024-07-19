package com.uni.thanosgym.model;

import java.util.Date;

public class Reporte {
    private Date fecha;
    private double monto;

    public Reporte(Date fecha, double monto) {
        this.fecha = fecha;
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }
}
