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
public class ModeloRegistro_tiempo {
    
    private int id;
    private String codigo;
    private String fecha;
    private String campo1;
    private String campo2;
    private String campo3;
    private String campo4;
    private String dispositivo;
    private String estado;

    public ModeloRegistro_tiempo() {
    }

    public ModeloRegistro_tiempo(int id, String codigo, String fecha, String campo1, String campo2, String campo3, String campo4, String dispositivo, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.campo1 = campo1;
        this.campo2 = campo2;
        this.campo3 = campo3;
        this.campo4 = campo4;
        this.dispositivo = dispositivo;
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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the campo1
     */
    public String getCampo1() {
        return campo1;
    }

    /**
     * @param campo1 the campo1 to set
     */
    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    /**
     * @return the campo2
     */
    public String getCampo2() {
        return campo2;
    }

    /**
     * @param campo2 the campo2 to set
     */
    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    /**
     * @return the campo3
     */
    public String getCampo3() {
        return campo3;
    }

    /**
     * @param campo3 the campo3 to set
     */
    public void setCampo3(String campo3) {
        this.campo3 = campo3;
    }

    /**
     * @return the campo4
     */
    public String getCampo4() {
        return campo4;
    }

    /**
     * @param campo4 the campo4 to set
     */
    public void setCampo4(String campo4) {
        this.campo4 = campo4;
    }

    /**
     * @return the dispositivo
     */
    public String getDispositivo() {
        return dispositivo;
    }

    /**
     * @param dispositivo the dispositivo to set
     */
    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
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
