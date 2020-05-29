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
public class ModeloAsociacion_grupo_vencimientio {

    Integer id;
    ModeloEnumeracion Modelo_enumeracion_grupo;
    ModeloEnumeracion Modelo_enumeracion_vencimiento;
    String estado;

    public ModeloAsociacion_grupo_vencimientio(Integer id, ModeloEnumeracion Modelo_enumeracion_grupo, ModeloEnumeracion Modelo_enumeracion_vencimiento, String estado) {
        this.id = id;
        this.Modelo_enumeracion_grupo = Modelo_enumeracion_grupo;
        this.Modelo_enumeracion_vencimiento = Modelo_enumeracion_vencimiento;
        this.estado = estado;
    }

    public ModeloAsociacion_grupo_vencimientio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModeloEnumeracion getModelo_enumeracion_grupo() {
        return Modelo_enumeracion_grupo;
    }

    public void setModelo_enumeracion_grupo(ModeloEnumeracion Modelo_enumeracion_grupo) {
        this.Modelo_enumeracion_grupo = Modelo_enumeracion_grupo;
    }

    public ModeloEnumeracion getModelo_enumeracion_vencimiento() {
        return Modelo_enumeracion_vencimiento;
    }

    public void setModelo_enumeracion_vencimiento(ModeloEnumeracion Modelo_enumeracion_vencimiento) {
        this.Modelo_enumeracion_vencimiento = Modelo_enumeracion_vencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
