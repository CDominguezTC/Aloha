/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Julian A Aristizabal
 */
public class ModeloAuditoria {

    private int id;
    private String operacion;
    private String tabla;
    private String fecha;
    private ModeloUsuario usuario;
    private int registro_modificado;
    private String observacion;

    public ModeloAuditoria() {
    }

    public ModeloAuditoria(int id, String operacion, String tabla, String fecha, ModeloUsuario usuario, int registro_modificado, String observacion) {
        this.id = id;
        this.operacion = operacion;
        this.tabla = tabla;
        this.fecha = fecha;
        this.usuario = usuario;
        this.registro_modificado = registro_modificado;
        this.observacion = observacion;
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
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the tabla
     */
    public String getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the usuario
     */
    public ModeloUsuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(ModeloUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the registro_modificado
     */
    public int getRegistro_modificado() {
        return registro_modificado;
    }

    /**
     * @param registro_modificado the registro_modificado to set
     */
    public void setRegistro_modificado(int registro_modificado) {
        this.registro_modificado = registro_modificado;
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

}
