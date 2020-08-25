/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Time;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloTurno_tiempo {

    Integer id;
    String codigo;
    String descripcion;
    String tipo_turno;
    String hora_inicio;
    String hora_fin;
    String teorico;
    String tolerancia_despues_entrada;
    String tolerancia_antes_salir;
    String tiempo_break;
    String limite_turno;
    String genera_extras_entrada;
    String tiempo_minimo_entrada;
    String tiempo_maximo_entrada;
    String genera_extras_salida;
    String tiempo_minimo_salida;
    String tiempo_maximo_salida;
    String redondeo_entrada;
    String sentido_entrada;
    String redondeo_salida;
    String sentido_salida;
    String descanso;
    String sentido_descanso;
    String conceptos;
    String sentido_concepto;
    String hora_inicio_diurno;
    String hora_inicio_nocturno;
    String turno_noche;
    String hora_inicio_break;
    String hora_fin_break;
    String descuenta_break;
    String turno_extra;
    String estado;

    public ModeloTurno_tiempo() {
    }

    public ModeloTurno_tiempo(Integer id, String codigo, String descripcion, String tipo_turno, String hora_inicio, String hora_fin, String teorico, String tolerancia_despues_entrada, String tolerancia_antes_salir, String tiempo_break, String limite_turno, String genera_extras_entrada, String tiempo_minimo_entrada, String tiempo_maximo_entrada, String genera_extras_salida, String tiempo_minimo_salida, String tiempo_maximo_salida, String redondeo_entrada, String sentido_entrada, String redondeo_salida, String sentido_salida, String descanso, String sentido_descanso, String conceptos, String sentido_concepto, String hora_inicio_diurno, String hora_inicio_nocturno, String turno_noche, String hora_inicio_break, String hora_fin_break, String descuenta_break, String turno_extra, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo_turno = tipo_turno;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.teorico = teorico;
        this.tolerancia_despues_entrada = tolerancia_despues_entrada;
        this.tolerancia_antes_salir = tolerancia_antes_salir;
        this.tiempo_break = tiempo_break;
        this.limite_turno = limite_turno;
        this.genera_extras_entrada = genera_extras_entrada;
        this.tiempo_minimo_entrada = tiempo_minimo_entrada;
        this.tiempo_maximo_entrada = tiempo_maximo_entrada;
        this.genera_extras_salida = genera_extras_salida;
        this.tiempo_minimo_salida = tiempo_minimo_salida;
        this.tiempo_maximo_salida = tiempo_maximo_salida;
        this.redondeo_entrada = redondeo_entrada;
        this.sentido_entrada = sentido_entrada;
        this.redondeo_salida = redondeo_salida;
        this.sentido_salida = sentido_salida;
        this.descanso = descanso;
        this.sentido_descanso = sentido_descanso;
        this.conceptos = conceptos;
        this.sentido_concepto = sentido_concepto;
        this.hora_inicio_diurno = hora_inicio_diurno;
        this.hora_inicio_nocturno = hora_inicio_nocturno;
        this.turno_noche = turno_noche;
        this.hora_inicio_break = hora_inicio_break;
        this.hora_fin_break = hora_fin_break;
        this.descuenta_break = descuenta_break;
        this.turno_extra = turno_extra;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_turno() {
        return tipo_turno;
    }

    public void setTipo_turno(String tipo_turno) {
        this.tipo_turno = tipo_turno;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getTeorico() {
        return teorico;
    }

    public void setTeorico(String teorico) {
        this.teorico = teorico;
    }

    public String getTolerancia_despues_entrada() {
        return tolerancia_despues_entrada;
    }

    public void setTolerancia_despues_entrada(String tolerancia_despues_entrada) {
        this.tolerancia_despues_entrada = tolerancia_despues_entrada;
    }

    public String getTolerancia_antes_salir() {
        return tolerancia_antes_salir;
    }

    public void setTolerancia_antes_salir(String tolerancia_antes_salir) {
        this.tolerancia_antes_salir = tolerancia_antes_salir;
    }

    public String getTiempo_break() {
        return tiempo_break;
    }

    public void setTiempo_break(String tiempo_break) {
        this.tiempo_break = tiempo_break;
    }

    public String getLimite_turno() {
        return limite_turno;
    }

    public void setLimite_turno(String limite_turno) {
        this.limite_turno = limite_turno;
    }

    public String getGenera_extras_entrada() {
        return genera_extras_entrada;
    }

    public void setGenera_extras_entrada(String genera_extras_entrada) {
        this.genera_extras_entrada = genera_extras_entrada;
    }

    public String getTiempo_minimo_entrada() {
        return tiempo_minimo_entrada;
    }

    public void setTiempo_minimo_entrada(String tiempo_minimo_entrada) {
        this.tiempo_minimo_entrada = tiempo_minimo_entrada;
    }

    public String getTiempo_maximo_entrada() {
        return tiempo_maximo_entrada;
    }

    public void setTiempo_maximo_entrada(String tiempo_maximo_entrada) {
        this.tiempo_maximo_entrada = tiempo_maximo_entrada;
    }

    public String getGenera_extras_salida() {
        return genera_extras_salida;
    }

    public void setGenera_extras_salida(String genera_extras_salida) {
        this.genera_extras_salida = genera_extras_salida;
    }

    public String getTiempo_minimo_salida() {
        return tiempo_minimo_salida;
    }

    public void setTiempo_minimo_salida(String tiempo_minimo_salida) {
        this.tiempo_minimo_salida = tiempo_minimo_salida;
    }

    public String getTiempo_maximo_salida() {
        return tiempo_maximo_salida;
    }

    public void setTiempo_maximo_salida(String tiempo_maximo_salida) {
        this.tiempo_maximo_salida = tiempo_maximo_salida;
    }

    public String getRedondeo_entrada() {
        return redondeo_entrada;
    }

    public void setRedondeo_entrada(String redondeo_entrada) {
        this.redondeo_entrada = redondeo_entrada;
    }

    public String getSentido_entrada() {
        return sentido_entrada;
    }

    public void setSentido_entrada(String sentido_entrada) {
        this.sentido_entrada = sentido_entrada;
    }

    public String getRedondeo_salida() {
        return redondeo_salida;
    }

    public void setRedondeo_salida(String redondeo_salida) {
        this.redondeo_salida = redondeo_salida;
    }

    public String getSentido_salida() {
        return sentido_salida;
    }

    public void setSentido_salida(String sentido_salida) {
        this.sentido_salida = sentido_salida;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }

    public String getSentido_descanso() {
        return sentido_descanso;
    }

    public void setSentido_descanso(String sentido_descanso) {
        this.sentido_descanso = sentido_descanso;
    }

    public String getConceptos() {
        return conceptos;
    }

    public void setConceptos(String conceptos) {
        this.conceptos = conceptos;
    }

    public String getSentido_concepto() {
        return sentido_concepto;
    }

    public void setSentido_concepto(String sentido_concepto) {
        this.sentido_concepto = sentido_concepto;
    }

    public String getHora_inicio_diurno() {
        return hora_inicio_diurno;
    }

    public void setHora_inicio_diurno(String hora_inicio_diurno) {
        this.hora_inicio_diurno = hora_inicio_diurno;
    }

    public String getHora_inicio_nocturno() {
        return hora_inicio_nocturno;
    }

    public void setHora_inicio_nocturno(String hora_inicio_nocturno) {
        this.hora_inicio_nocturno = hora_inicio_nocturno;
    }

    public String getTurno_noche() {
        return turno_noche;
    }

    public void setTurno_noche(String turno_noche) {
        this.turno_noche = turno_noche;
    }

    public String getHora_inicio_break() {
        return hora_inicio_break;
    }

    public void setHora_inicio_break(String hora_inicio_break) {
        this.hora_inicio_break = hora_inicio_break;
    }

    public String getHora_fin_break() {
        return hora_fin_break;
    }

    public void setHora_fin_break(String hora_fin_break) {
        this.hora_fin_break = hora_fin_break;
    }

    public String getDescuenta_break() {
        return descuenta_break;
    }

    public void setDescuenta_break(String descuenta_break) {
        this.descuenta_break = descuenta_break;
    }

    public String getTurno_extra() {
        return turno_extra;
    }

    public void setTurno_extra(String turno_extra) {
        this.turno_extra = turno_extra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }   
}
