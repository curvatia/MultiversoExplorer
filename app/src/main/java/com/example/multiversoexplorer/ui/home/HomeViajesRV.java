package com.example.multiversoexplorer.ui.home;

import java.io.Serializable;

public class HomeViajesRV implements Serializable {

    public String getContent() {
        return Content;
    }

    private String Content;
    private String precio;
    private String dias;
    private String Fotos;
    private String Viaje;
    private String Informacion;

    //CONSTRUCTOR 1
    public HomeViajesRV() {

    }

    //CONSTRUCTOR 2
    public HomeViajesRV(String viaje, String informacion, String content,String precio, String dias, String fotos) {
        this.precio = precio;
        this.dias = dias;
        this.Fotos = fotos;
        this.Viaje = viaje;
        this.Content = content;
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

    public String getPrecio() { return precio; }
    public String getDias() { return dias; }

    @Override
    public String toString() {
        return "Viaje:"+ this.Viaje+
                "\nDescripcion:"+ this.Informacion+
                "\nFoto"+ this.Fotos;
    }
}
