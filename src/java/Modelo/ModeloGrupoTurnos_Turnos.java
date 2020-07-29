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
public class ModeloGrupoTurnos_Turnos {

    int id;    
    String dia_Semana;
    String estado;
    ModeloGrupo_horario IdModelo_Grupo_Turnos;
    ModeloTurno_tiempo IdModelo_Turnos;

    public ModeloGrupoTurnos_Turnos() {
    }

    public ModeloGrupoTurnos_Turnos(int id, String dia_Semana, String estado, ModeloGrupo_horario IdModelo_Grupo_Turnos, ModeloTurno_tiempo IdModelo_Turnos) {
        this.id = id;
        this.dia_Semana = dia_Semana;
        this.estado = estado;
        this.IdModelo_Grupo_Turnos = IdModelo_Grupo_Turnos;
        this.IdModelo_Turnos = IdModelo_Turnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia_Semana() {
        return dia_Semana;
    }

    public void setDia_Semana(String dia_Semana) {
        this.dia_Semana = dia_Semana;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ModeloGrupo_horario getIdModelo_Grupo_Turnos() {
        return IdModelo_Grupo_Turnos;
    }

    public void setIdModelo_Grupo_Turnos(ModeloGrupo_horario IdModelo_Grupo_Turnos) {
        this.IdModelo_Grupo_Turnos = IdModelo_Grupo_Turnos;
    }

    public ModeloTurno_tiempo getIdModelo_Turnos() {
        return IdModelo_Turnos;
    }

    public void setIdModelo_Turnos(ModeloTurno_tiempo IdModelo_Turnos) {
        this.IdModelo_Turnos = IdModelo_Turnos;
    }        
}
