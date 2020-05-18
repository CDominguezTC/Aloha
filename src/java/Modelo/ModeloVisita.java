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
public class ModeloVisita {

    Integer id;
    ModeloPersona Modelo_persona_visitante;
    ModeloEmpresa Modelo_empresa_visitante;
    ModeloPersona Modelo_persona_visitada;
    ModeloArea Modelo_area_visitada;
    String tipo_visita;
    String numero_tarjeta;
    Date fecha_hora_entrada;
    Date fecha_hora_salida;
    String estado_visita;
    ModeloVehiculo Modelo_vehiculo;
    String observacion;
    ModeloUsuarios Modelo_usuario_ingreso;
    ModeloUsuarios Modelo_usuario_salida;

    public ModeloVisita(Integer id, ModeloPersona Modelo_persona_visitante, ModeloEmpresa Modelo_empresa_visitante, ModeloPersona Modelo_persona_visitada, ModeloArea Modelo_area_visitada, String tipo_visita, String numero_tarjeta, Date fecha_hora_entrada, Date fecha_hora_salida, String estado_visita, ModeloVehiculo Modelo_vehiculo, String observacion, ModeloUsuarios Modelo_usuario_ingreso, ModeloUsuarios Modelo_usuario_salida) {
        this.id = id;
        this.Modelo_persona_visitante = Modelo_persona_visitante;
        this.Modelo_empresa_visitante = Modelo_empresa_visitante;
        this.Modelo_persona_visitada = Modelo_persona_visitada;
        this.Modelo_area_visitada = Modelo_area_visitada;
        this.tipo_visita = tipo_visita;
        this.numero_tarjeta = numero_tarjeta;
        this.fecha_hora_entrada = fecha_hora_entrada;
        this.fecha_hora_salida = fecha_hora_salida;
        this.estado_visita = estado_visita;
        this.Modelo_vehiculo = Modelo_vehiculo;
        this.observacion = observacion;
        this.Modelo_usuario_ingreso = Modelo_usuario_ingreso;
        this.Modelo_usuario_salida = Modelo_usuario_salida;
    }

    public ModeloVisita() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModeloPersona getModelo_persona_visitante() {
        return Modelo_persona_visitante;
    }

    public void setModelo_persona_visitante(ModeloPersona Modelo_persona_visitante) {
        this.Modelo_persona_visitante = Modelo_persona_visitante;
    }

    public ModeloEmpresa getModelo_empresa_visitante() {
        return Modelo_empresa_visitante;
    }

    public void setModelo_empresa_visitante(ModeloEmpresa Modelo_empresa_visitante) {
        this.Modelo_empresa_visitante = Modelo_empresa_visitante;
    }

    public ModeloPersona getModelo_persona_visitada() {
        return Modelo_persona_visitada;
    }

    public void setModelo_persona_visitada(ModeloPersona Modelo_persona_visitada) {
        this.Modelo_persona_visitada = Modelo_persona_visitada;
    }

    public ModeloArea getModelo_area_visitada() {
        return Modelo_area_visitada;
    }

    public void setModelo_area_visitada(ModeloArea Modelo_area_visitada) {
        this.Modelo_area_visitada = Modelo_area_visitada;
    }

    public String getTipo_visita() {
        return tipo_visita;
    }

    public void setTipo_visita(String tipo_visita) {
        this.tipo_visita = tipo_visita;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public Date getFecha_hora_entrada() {
        return fecha_hora_entrada;
    }

    public void setFecha_hora_entrada(Date fecha_hora_entrada) {
        this.fecha_hora_entrada = fecha_hora_entrada;
    }

    public Date getFecha_hora_salida() {
        return fecha_hora_salida;
    }

    public void setFecha_hora_salida(Date fecha_hora_salida) {
        this.fecha_hora_salida = fecha_hora_salida;
    }

    public String getEstado_visita() {
        return estado_visita;
    }

    public void setEstado_visita(String estado_visita) {
        this.estado_visita = estado_visita;
    }

    public ModeloVehiculo getModelo_vehiculo() {
        return Modelo_vehiculo;
    }

    public void setModelo_vehiculo(ModeloVehiculo Modelo_vehiculo) {
        this.Modelo_vehiculo = Modelo_vehiculo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ModeloUsuarios getModelo_usuario_ingreso() {
        return Modelo_usuario_ingreso;
    }

    public void setModelo_usuario_ingreso(ModeloUsuarios Modelo_usuario_ingreso) {
        this.Modelo_usuario_ingreso = Modelo_usuario_ingreso;
    }

    public ModeloUsuarios getModelo_usuario_salida() {
        return Modelo_usuario_salida;
    }

    public void setModelo_usuario_salida(ModeloUsuarios Modelo_usuario_salida) {
        this.Modelo_usuario_salida = Modelo_usuario_salida;
    }

    
    
    
}
