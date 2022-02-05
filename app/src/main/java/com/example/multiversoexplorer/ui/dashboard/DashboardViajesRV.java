package com.example.multiversoexplorer.ui.dashboard;

import java.io.Serializable;

public class DashboardViajesRV  implements Serializable {
    private String picture;
    private String destino;
    private String fechaIda, fechaVuelta;

    public DashboardViajesRV(String picture, String destino, String fechaIda, String fechaVuelta) {
        this.picture = picture;
        this.destino = destino;
        this.fechaIda = fechaIda;
        this.fechaVuelta = fechaVuelta;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(String fechaIda) {
        this.fechaIda = fechaIda;
    }

    public String getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(String fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }


}
