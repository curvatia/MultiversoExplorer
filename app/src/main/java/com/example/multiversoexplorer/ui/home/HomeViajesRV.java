package com.example.multiversoexplorer.ui.home;

import java.io.Serializable;

public class HomeViajesRV implements Serializable {

    private String Fotos, Viaje, Informacion;

    //CONSTRUCTOR 1
    public HomeViajesRV() {

    }

    //CONSTRUCTOR 2
    public HomeViajesRV(String viaje, String informacion, String fotos) {
        this.Fotos = fotos;
        this.Viaje = viaje;
        this.Informacion = informacion;
    }

    //GETTER & SETTER
    public String getFotos() {
        return Fotos;
    }
    public void setFotos(String fotos) {
        Fotos = fotos;
    }

    public String getViaje() {
        return Viaje;
    }
    public void setViaje(String viaje) {
        Viaje = viaje;
    }

    public String getInformacion() {
        return Informacion;
    }
    public void setInformacion(String informacion) {
        Informacion = informacion;
    }


    @Override
    public String toString() {
        return "Viaje:"+ this.Viaje+
                "\nDescripcion:"+ this.Informacion+
                "\nFoto"+ this.Fotos;
    }
}
