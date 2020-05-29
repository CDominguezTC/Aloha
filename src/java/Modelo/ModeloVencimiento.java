/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ModeloVencimiento {

    Integer id;
    ModeloPersona Modelo_persona;
    ModeloEnumeracion Modelo_enumeracion_vencimiento;
    String fecha_vencimiento;
    String estado;

    public ModeloVencimiento(Integer id, ModeloPersona Modelo_persona, ModeloEnumeracion Modelo_enumeracion_vencimiento, String fecha_vencimiento, String estado) {
        this.id = id;
        this.Modelo_persona = Modelo_persona;
        this.Modelo_enumeracion_vencimiento = Modelo_enumeracion_vencimiento;
        this.fecha_vencimiento = fecha_vencimiento;
        this.estado = estado;
    }

    public ModeloVencimiento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModeloPersona getModelo_persona() {
        return Modelo_persona;
    }

    public void setModelo_persona(ModeloPersona Modelo_persona) {
        this.Modelo_persona = Modelo_persona;
    }

    public ModeloEnumeracion getModelo_enumeracion_vencimiento() {
        return Modelo_enumeracion_vencimiento;
    }

    public void setModelo_enumeracion_vencimiento(ModeloEnumeracion Modelo_enumeracion_vencimiento) {
        this.Modelo_enumeracion_vencimiento = Modelo_enumeracion_vencimiento;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

}
