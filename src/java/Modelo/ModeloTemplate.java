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
public class ModeloTemplate {
    Integer id;
    ModeloPersona Modelo_persona;
    String tipo_plantilla;
    String numero_plantilla;
    String plantilla;
    String estado;

    public ModeloTemplate() {
    }

    public ModeloTemplate(Integer id, ModeloPersona Modelo_persona, String tipo_plantilla, String numero_plantilla, String plantilla, String estado) {
        this.id = id;
        this.Modelo_persona = Modelo_persona;
        this.tipo_plantilla = tipo_plantilla;
        this.numero_plantilla = numero_plantilla;
        this.plantilla = plantilla;
        this.estado = estado;
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

    public String getTipo_plantilla() {
        return tipo_plantilla;
    }

    public void setTipo_plantilla(String tipo_plantilla) {
        this.tipo_plantilla = tipo_plantilla;
    }

    public String getNumero_plantilla() {
        return numero_plantilla;
    }

    public void setNumero_plantilla(String numero_plantilla) {
        this.numero_plantilla = numero_plantilla;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
