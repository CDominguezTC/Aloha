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
public class ModeloConsumo_hoteleria {

    Integer id;
    ModeloPersona Modelo_persona;
    String cargo_persona;
    ModeloCentro_costo Modelo_centro_costo;
    ModeloGrupo_consumo Modelo_grupo_consumo;
    ModeloHorario_consumo Modelo_horario_consumo;
    ModeloCargo_hoteleria Modelo_cargo_hoteleria;
    String cargo_hoteleria_valor;
    String fecha_consumo;
    String dia_consumo;
    String estado;

    public ModeloConsumo_hoteleria() {
    }

    public ModeloConsumo_hoteleria(Integer id, ModeloPersona Modelo_persona, String cargo_persona, ModeloCentro_costo Modelo_centro_costo, ModeloGrupo_consumo Modelo_grupo_consumo, ModeloHorario_consumo Modelo_horario_consumo, ModeloCargo_hoteleria Modelo_cargo_hoteleria, String cargo_hoteleria_valor, String fecha_consumo, String dia_consumo, String estado) {
        this.id = id;
        this.Modelo_persona = Modelo_persona;
        this.cargo_persona = cargo_persona;
        this.Modelo_centro_costo = Modelo_centro_costo;
        this.Modelo_grupo_consumo = Modelo_grupo_consumo;
        this.Modelo_horario_consumo = Modelo_horario_consumo;
        this.Modelo_cargo_hoteleria = Modelo_cargo_hoteleria;
        this.cargo_hoteleria_valor = cargo_hoteleria_valor;
        this.fecha_consumo = fecha_consumo;
        this.dia_consumo = dia_consumo;
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

    public String getCargo_persona() {
        return cargo_persona;
    }

    public void setCargo_persona(String cargo_persona) {
        this.cargo_persona = cargo_persona;
    }

    public ModeloCentro_costo getModelo_centro_costo() {
        return Modelo_centro_costo;
    }

    public void setModelo_centro_costo(ModeloCentro_costo Modelo_centro_costo) {
        this.Modelo_centro_costo = Modelo_centro_costo;
    }

    public ModeloGrupo_consumo getModelo_grupo_consumo() {
        return Modelo_grupo_consumo;
    }

    public void setModelo_grupo_consumo(ModeloGrupo_consumo Modelo_grupo_consumo) {
        this.Modelo_grupo_consumo = Modelo_grupo_consumo;
    }

    public ModeloHorario_consumo getModelo_horario_consumo() {
        return Modelo_horario_consumo;
    }

    public void setModelo_horario_consumo(ModeloHorario_consumo Modelo_horario_consumo) {
        this.Modelo_horario_consumo = Modelo_horario_consumo;
    }

    public ModeloCargo_hoteleria getModelo_cargo_hoteleria() {
        return Modelo_cargo_hoteleria;
    }

    public void setModelo_cargo_hoteleria(ModeloCargo_hoteleria Modelo_cargo_hoteleria) {
        this.Modelo_cargo_hoteleria = Modelo_cargo_hoteleria;
    }

    public String getCargo_hoteleria_valor() {
        return cargo_hoteleria_valor;
    }

    public void setCargo_hoteleria_valor(String cargo_hoteleria_valor) {
        this.cargo_hoteleria_valor = cargo_hoteleria_valor;
    }

    public String getFecha_consumo() {
        return fecha_consumo;
    }

    public void setFecha_consumo(String fecha_consumo) {
        this.fecha_consumo = fecha_consumo;
    }

    public String getDia_consumo() {
        return dia_consumo;
    }

    public void setDia_consumo(String dia_consumo) {
        this.dia_consumo = dia_consumo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
