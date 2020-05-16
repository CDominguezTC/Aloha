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
public class ModeloAsociacion_grupo_consumo_horario_consumo {

    Integer id;
    ModeloGrupo_consumo Modelo_grupo_consumo;
    ModeloHorario_consumo Modelo_horario_consumo;
    Integer costo_consumo;
    String estado;

    public ModeloAsociacion_grupo_consumo_horario_consumo() {
    }

    public ModeloAsociacion_grupo_consumo_horario_consumo(Integer id, ModeloGrupo_consumo Modelo_grupo_consumo, ModeloHorario_consumo Modelo_horario_consumo, Integer costo_consumo, String estado) {
        this.id = id;
        this.Modelo_grupo_consumo = Modelo_grupo_consumo;
        this.Modelo_horario_consumo = Modelo_horario_consumo;
        this.costo_consumo = costo_consumo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCosto_consumo() {
        return costo_consumo;
    }

    public void setCosto_consumo(Integer costo_consumo) {
        this.costo_consumo = costo_consumo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
