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
public class ModeloAsocGrupoConsumo
{
    int id;
    ModeloGrupoConsumo modeloGrupoConsumo;
    ModeloHorarioConsumo modeloHorarioConsumo;    
    int costo;

    public ModeloAsocGrupoConsumo()
    {
    }

    public ModeloAsocGrupoConsumo(int id, ModeloGrupoConsumo modeloGrupoConsumo, ModeloHorarioConsumo modeloHorarioConsumo, int costo)
    {
        this.id = id;
        this.modeloGrupoConsumo = modeloGrupoConsumo;
        this.modeloHorarioConsumo = modeloHorarioConsumo;
        this.costo = costo;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public ModeloGrupoConsumo getModeloGrupoConsumo()
    {
        return modeloGrupoConsumo;
    }

    public void setModeloGrupoConsumo(ModeloGrupoConsumo modeloGrupoConsumo)
    {
        this.modeloGrupoConsumo = modeloGrupoConsumo;
    }

    public ModeloHorarioConsumo getModeloHorarioConsumo()
    {
        return modeloHorarioConsumo;
    }

    public void setModeloHorarioConsumo(ModeloHorarioConsumo modeloHorarioConsumo)
    {
        this.modeloHorarioConsumo = modeloHorarioConsumo;
    }

    public int getCosto()
    {
        return costo;
    }

    public void setCosto(int costo)
    {
        this.costo = costo;
    }
    
    
}
