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
public class ModeloAutoriza_consumo {

    Integer id;
    String tipo_persona;
    ModeloPersona Modelo_persona_que_autoriza;
    ModeloPersona Modelo_persona_autorizada;
    ModeloCentro_costo Modelo_centro_costo;
    String fecha_autorizada;
    ModeloHorario_consumo Modelo_horario_consumo;
    String motivo;
    Integer cantidad_autorizada;
    Integer cantidad_consumida;
    String fecha_registro;
    ModeloUsuario Modelo_usuario;
    String estado;

    public ModeloAutoriza_consumo() {
    }

    public ModeloAutoriza_consumo(Integer id, String tipo_persona, ModeloPersona Modelo_persona_que_autoriza, ModeloPersona Modelo_persona_autorizada, ModeloCentro_costo Modelo_centro_costo, String fecha_autorizada, ModeloHorario_consumo Modelo_horario_consumo, String motivo, Integer cantidad_autorizada, Integer cantidad_consumida, String fecha_registro, ModeloUsuario Modelo_usuario, String estado) {
        this.id = id;
        this.tipo_persona = tipo_persona;
        this.Modelo_persona_que_autoriza = Modelo_persona_que_autoriza;
        this.Modelo_persona_autorizada = Modelo_persona_autorizada;
        this.Modelo_centro_costo = Modelo_centro_costo;
        this.fecha_autorizada = fecha_autorizada;
        this.Modelo_horario_consumo = Modelo_horario_consumo;
        this.motivo = motivo;
        this.cantidad_autorizada = cantidad_autorizada;
        this.cantidad_consumida = cantidad_consumida;
        this.fecha_registro = fecha_registro;
        this.Modelo_usuario = Modelo_usuario;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public ModeloPersona getModelo_persona_que_autoriza() {
        return Modelo_persona_que_autoriza;
    }

    public void setModelo_persona_que_autoriza(ModeloPersona Modelo_persona_que_autoriza) {
        this.Modelo_persona_que_autoriza = Modelo_persona_que_autoriza;
    }

    public ModeloPersona getModelo_persona_autorizada() {
        return Modelo_persona_autorizada;
    }

    public void setModelo_persona_autorizada(ModeloPersona Modelo_persona_autorizada) {
        this.Modelo_persona_autorizada = Modelo_persona_autorizada;
    }

    public ModeloCentro_costo getModelo_centro_costo() {
        return Modelo_centro_costo;
    }

    public void setModelo_centro_costo(ModeloCentro_costo Modelo_centro_costo) {
        this.Modelo_centro_costo = Modelo_centro_costo;
    }

    public String getFecha_autorizada() {
        return fecha_autorizada;
    }

    public void setFecha_autorizada(String fecha_autorizada) {
        this.fecha_autorizada = fecha_autorizada;
    }

    public ModeloHorario_consumo getModelo_horario_consumo() {
        return Modelo_horario_consumo;
    }

    public void setModelo_horario_consumo(ModeloHorario_consumo Modelo_horario_consumo) {
        this.Modelo_horario_consumo = Modelo_horario_consumo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getCantidad_autorizada() {
        return cantidad_autorizada;
    }

    public void setCantidad_autorizada(Integer cantidad_autorizada) {
        this.cantidad_autorizada = cantidad_autorizada;
    }

    public Integer getCantidad_consumida() {
        return cantidad_consumida;
    }

    public void setCantidad_consumida(Integer cantidad_consumida) {
        this.cantidad_consumida = cantidad_consumida;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public ModeloUsuario getModelo_usuario() {
        return Modelo_usuario;
    }

    public void setModelo_usuario(ModeloUsuario Modelo_usuario) {
        this.Modelo_usuario = Modelo_usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
