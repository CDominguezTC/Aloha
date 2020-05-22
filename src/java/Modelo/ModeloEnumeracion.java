/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ModeloEnumeracion {

    Integer id;
    ModeloEnumeracion Modelo_enumeracion;
    String campo;
    String estado;

    public ModeloEnumeracion(Integer id, ModeloEnumeracion Modelo_enumeracion, String campo, String estado) {
        this.id = id;
        this.Modelo_enumeracion = Modelo_enumeracion;
        this.campo = campo;
        this.estado = estado;
    }

    public ModeloEnumeracion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModeloEnumeracion getModelo_enumeracion() {
        return Modelo_enumeracion;
    }

    public void setModelo_enumeracion(ModeloEnumeracion Modelo_enumeracion) {
        this.Modelo_enumeracion = Modelo_enumeracion;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

}
