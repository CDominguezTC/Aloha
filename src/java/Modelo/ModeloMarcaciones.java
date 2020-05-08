/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloMarcaciones {

    int id;
    int id_empleado;
    Date fecha_marcacion;
    String hora_marcacion;
    String estado_marcacion;
    String nombre_dispositivo;
    String observacion;
    String observacionPersonal;

    public ModeloMarcaciones() {
    }

    public ModeloMarcaciones(int id, int id_empleado, Date fecha_marcacion, String hora_marcacion, String estado_marcacion, String nombre_dispositivo, String observacion, String observacionPersonal) {
        this.id = id;
        this.id_empleado = id_empleado;
        this.fecha_marcacion = fecha_marcacion;
        this.hora_marcacion = hora_marcacion;
        this.estado_marcacion = estado_marcacion;
        this.nombre_dispositivo = nombre_dispositivo;
        this.observacion = observacion;
        this.observacionPersonal = observacionPersonal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Date getFecha_marcacion() {
        return fecha_marcacion;
    }

    public void setFecha_marcacion(Date fecha_marcacion) {
        this.fecha_marcacion = fecha_marcacion;
    }

    public String getHora_marcacion() {
        return hora_marcacion;
    }

    public void setHora_marcacion(String hora_marcacion) {
        this.hora_marcacion = hora_marcacion;
    }

    public String getEstado_marcacion() {
        return estado_marcacion;
    }

    public void setEstado_marcacion(String estado_marcacion) {
        this.estado_marcacion = estado_marcacion;
    }

    public String getNombre_dispositivo() {
        return nombre_dispositivo;
    }

    public void setNombre_dispositivo(String nombre_dispositivo) {
        this.nombre_dispositivo = nombre_dispositivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionPersonal() {
        return observacionPersonal;
    }

    public void setObservacionPersonal(String observacionPersonal) {
        this.observacionPersonal = observacionPersonal;
    }

}
