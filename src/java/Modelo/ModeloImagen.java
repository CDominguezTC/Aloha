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
public class ModeloImagen {

    Integer id;
    ModeloPersona Modelo_persona;
    ModeloVehiculo Modelo_vehiculo;
    String tipo_imagen;
    Integer numero_imagen;
    String imagen;
    String estado;

    public ModeloImagen() {
    }

    public ModeloImagen(Integer id, ModeloPersona Modelo_persona, ModeloVehiculo Modelo_vehiculo, String tipo_imagen, Integer numero_imagen, String imagen, String estado) {
        this.id = id;
        this.Modelo_persona = Modelo_persona;
        this.Modelo_vehiculo = Modelo_vehiculo;
        this.tipo_imagen = tipo_imagen;
        this.numero_imagen = numero_imagen;
        this.imagen = imagen;
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

    public ModeloVehiculo getModelo_vehiculo() {
        return Modelo_vehiculo;
    }

    public void setModelo_vehiculo(ModeloVehiculo Modelo_vehiculo) {
        this.Modelo_vehiculo = Modelo_vehiculo;
    }

    public String getTipo_imagen() {
        return tipo_imagen;
    }

    public void setTipo_imagen(String tipo_imagen) {
        this.tipo_imagen = tipo_imagen;
    }

    public Integer getNumero_imagen() {
        return numero_imagen;
    }

    public void setNumero_imagen(Integer numero_imagen) {
        this.numero_imagen = numero_imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
