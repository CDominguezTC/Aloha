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
public class ModeloVehiculo {

    Integer id;
    String placa_vehiculo;
    String color_vehiculo;
    String marca_vehiculo;
    String tipo_vehiculo;
    ModeloPersona Modelo_persona_responsable;
    String estado;

    public ModeloVehiculo(Integer id, String placa_vehiculo, String color_vehiculo, String marca_vehiculo, String tipo_vehiculo, ModeloPersona Modelo_persona_responsable, String estado) {
        this.id = id;
        this.placa_vehiculo = placa_vehiculo;
        this.color_vehiculo = color_vehiculo;
        this.marca_vehiculo = marca_vehiculo;
        this.tipo_vehiculo = tipo_vehiculo;
        this.Modelo_persona_responsable = Modelo_persona_responsable;
        this.estado = estado;
    }

    public ModeloVehiculo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getColor_vehiculo() {
        return color_vehiculo;
    }

    public void setColor_vehiculo(String color_vehiculo) {
        this.color_vehiculo = color_vehiculo;
    }

    public String getMarca_vehiculo() {
        return marca_vehiculo;
    }

    public void setMarca_vehiculo(String marca_vehiculo) {
        this.marca_vehiculo = marca_vehiculo;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public ModeloPersona getModelo_persona_responsable() {
        return Modelo_persona_responsable;
    }

    public void setModelo_persona_responsable(ModeloPersona Modelo_persona_responsable) {
        this.Modelo_persona_responsable = Modelo_persona_responsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getStirng() {
        return placa_vehiculo;
    }
    
    

}
