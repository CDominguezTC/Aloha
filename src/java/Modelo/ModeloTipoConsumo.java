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
public class ModeloTipoConsumo 
{
    int Id;
    String Nombre;
    int Cantidad;

    public ModeloTipoConsumo()
    {
    }
    
    public ModeloTipoConsumo(int Id, String Nombre, int Cantidad)
    {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
    }

    public int getId()
    {
        return Id;
    }

    public void setId(int Id)
    {
        this.Id = Id;
    }

    public String getNombre()
    {
        return Nombre;
    }

    public void setNombre(String Nombre)
    {
        this.Nombre = Nombre;
    }

    public int getCantidad()
    {
        return Cantidad;
    }

    public void setCantidad(int Cantidad)
    {
        this.Cantidad = Cantidad;
    }
    
    
}
