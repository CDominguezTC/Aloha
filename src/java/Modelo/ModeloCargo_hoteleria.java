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
public class ModeloCargo_hoteleria {

    Integer id;
    String tipo_cargo;
    Integer valor_cargo;
    String estado;

    public ModeloCargo_hoteleria() {
    }

    public ModeloCargo_hoteleria(Integer id, String tipo_cargo, Integer valor_cargo, String estado) {
        this.id = id;
        this.tipo_cargo = tipo_cargo;
        this.valor_cargo = valor_cargo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_cargo() {
        return tipo_cargo;
    }

    public void setTipo_cargo(String tipo_cargo) {
        this.tipo_cargo = tipo_cargo;
    }

    public Integer getValor_cargo() {
        return valor_cargo;
    }

    public void setValor_cargo(Integer valor_cargo) {
        this.valor_cargo = valor_cargo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
