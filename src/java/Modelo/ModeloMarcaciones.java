/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloMarcaciones {

    private int id;
    private int id_persona;
    private String fecha_marcacion;
    //String hora_marcacion;
    private String estado_marcacion;
    private String nombre_dispositivo;
    private String observacion;
    private String observacion_personal;
    private String estado;

    public ModeloMarcaciones() {
    }

    public ModeloMarcaciones(int id, int id_persona, String fecha_marcacion, String estado_marcacion, String nombre_dispositivo, String observacion, String observacion_personal, String estado) {
        this.id = id;
        this.id_persona = id_persona;
        this.fecha_marcacion = fecha_marcacion;
        this.estado_marcacion = estado_marcacion;
        this.nombre_dispositivo = nombre_dispositivo;
        this.observacion = observacion;
        this.observacion_personal = observacion_personal;
        this.estado = estado;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_persona
     */
    public int getId_persona() {
        return id_persona;
    }

    /**
     * @param id_persona the id_persona to set
     */
    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    /**
     * @return the fecha_marcacion
     */
    public String getFecha_marcacion() {
        return fecha_marcacion;
    }

    /**
     * @param fecha_marcacion the fecha_marcacion to set
     */
    public void setFecha_marcacion(String fecha_marcacion) {
        this.fecha_marcacion = fecha_marcacion;
    }

    /**
     * @return the estado_marcacion
     */
    public String getEstado_marcacion() {
        return estado_marcacion;
    }

    /**
     * @param estado_marcacion the estado_marcacion to set
     */
    public void setEstado_marcacion(String estado_marcacion) {
        this.estado_marcacion = estado_marcacion;
    }

    /**
     * @return the nombre_dispositivo
     */
    public String getNombre_dispositivo() {
        return nombre_dispositivo;
    }

    /**
     * @param nombre_dispositivo the nombre_dispositivo to set
     */
    public void setNombre_dispositivo(String nombre_dispositivo) {
        this.nombre_dispositivo = nombre_dispositivo;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the observacion_personal
     */
    public String getObservacion_personal() {
        return observacion_personal;
    }

    /**
     * @param observacion_personal the observacion_personal to set
     */
    public void setObservacion_personal(String observacion_personal) {
        this.observacion_personal = observacion_personal;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }



}
