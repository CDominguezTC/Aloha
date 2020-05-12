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
    ModeloGrupoTurnos IdModelo_Grupo_Turnos;
    ModeloTurnos IdModelo_Turnos;
    String dia_Semana;

    public ModeloGrupoTurnos_Turnos() {
    }

    public ModeloGrupoTurnos_Turnos(int id, ModeloGrupoTurnos IdModelo_Grupo_Turnos, ModeloTurnos IdModeloTurnos, String dia_Semana) {
        this.id = id;
        this.IdModelo_Grupo_Turnos = IdModelo_Grupo_Turnos;
        this.IdModelo_Turnos = IdModeloTurnos;
        this.dia_Semana = dia_Semana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModeloGrupoTurnos getIdModelo_Grupo_Turnos() {
        return IdModelo_Grupo_Turnos;
    }

    public void setIdModelo_Grupo_Turnos(ModeloGrupoTurnos IdModelo_Grupo_Turnos) {
        this.IdModelo_Grupo_Turnos = IdModelo_Grupo_Turnos;
    }

    public ModeloTurnos getIdModelo_Turnos() {
        return IdModelo_Turnos;
    }

    public void setIdModelo_Turnos(ModeloTurnos IdModeloTurnos) {
        this.IdModelo_Turnos = IdModeloTurnos;
    }

    public String getDia_Semana() {
        return dia_Semana;
    }

    public void setDia_Semana(String dia_Semana) {
        this.dia_Semana = dia_Semana;
    }
    
    
}
