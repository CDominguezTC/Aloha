/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloImagen;
import Modelo.ModeloPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorImagen {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBdMysql conexion = new ConexionBdMysql();

    String user;

    public String Insert(String[] Huellas, String Foto, String firma, ModeloPersona modelo, String opcion) {
        LinkedList<ModeloImagen> listamodeloImagenes = new LinkedList<>();
        listamodeloImagenes = Generar_lista_imagenes(Huellas, Foto, firma, modelo);
        if (!listamodeloImagenes.isEmpty()) {
            if ("Insert".equals(opcion)) {
                resultado = Insert(listamodeloImagenes);
            }
            if ("Update".equals(opcion)) {
                resultado = Update(listamodeloImagenes);
            }
        }

        return resultado;
    }

    /**
     * Se genera el listado de imaghenes que se desea almacena en la base de
     * datos
     *
     * @author: Carlos Arturo Dominguez Diaz
     * @param Firma
     * @param Foto
     * @param Huellas
     * @param modeloPersona
     * @return
     * @version: 20/05/2020
     */
    private LinkedList<ModeloImagen> Generar_lista_imagenes(String[] Huellas, String Foto, String Firma, ModeloPersona modeloPersona) {
        LinkedList<ModeloImagen> listaModeloImagenes = new LinkedList<>();

        //PROCESAMOS LAS HUELLAS 
        for (String huellas : Huellas) {
            if (!"".equals(huellas)) {
                ModeloImagen modeloImagenHuella = new ModeloImagen();
                modeloImagenHuella.setModelo_persona(modeloPersona);
                modeloImagenHuella.setTipo_imagen("Huella");
                String[] parteshuella = huellas.split(",");
                modeloImagenHuella.setImagen(parteshuella[0] + "," + parteshuella[1]);
                modeloImagenHuella.setNumero_imagen(Integer.parseInt(parteshuella[2]));
                modeloImagenHuella.setEstado("S");
                listaModeloImagenes.add(modeloImagenHuella);
            }
        }
        //PROCESAMOS LA FOTO 
        if (!"".equals(Foto)) {
            ModeloImagen modeloImagenFoto = new ModeloImagen();
            modeloImagenFoto.setModelo_persona(modeloPersona);
            modeloImagenFoto.setTipo_imagen("Foto");
            modeloImagenFoto.setImagen(Foto);
            modeloImagenFoto.setNumero_imagen(20);
            modeloImagenFoto.setEstado("S");
            listaModeloImagenes.add(modeloImagenFoto);
        }
        //PROCESAMOS LA FIRMA
        if (!"".equals(Firma)) {
            ModeloImagen modeloImagenFirma = new ModeloImagen();
            modeloImagenFirma.setModelo_persona(modeloPersona);
            modeloImagenFirma.setTipo_imagen("Firma");
            modeloImagenFirma.setImagen(Firma);
            modeloImagenFirma.setNumero_imagen(30);
            modeloImagenFirma.setEstado("S");
            listaModeloImagenes.add(modeloImagenFirma);
        }
        return listaModeloImagenes;
    }

    public LinkedList<ModeloImagen> getListaModelo(Integer id) {
        ControladorPersona controladorPersona = new ControladorPersona();
        LinkedList<ModeloImagen> listaModeloImagens = new LinkedList<ModeloImagen>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`id`,"
                    + "`id_persona`,"
                    + "`tipo_imagen`,"
                    + "`numero_imagen`,"
                    + "`imagen`,"
                    + "`estado` "
                    + "FROM `imagen` "
                    + "WHERE `id_persona` = ? ;");
            SQL.setInt(1, id);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloImagen modeloImagen = new ModeloImagen();
                modeloImagen.setId(res.getInt("id"));
                modeloImagen.setModelo_persona(controladorPersona.getModelo(id));
                modeloImagen.setTipo_imagen(res.getString("tipo_imagen"));
                modeloImagen.setNumero_imagen(res.getInt("numero_imagen"));
                modeloImagen.setImagen(res.getString("imagen"));
                modeloImagen.setEstado(res.getString("estado"));
                listaModeloImagens.add(modeloImagen);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listaModeloImagens;
    }

    private String Insert(LinkedList<ModeloImagen> listamodeloImagenes) {
        try {
            con = conexion.abrirConexion();
            for (ModeloImagen modeloImagen : listamodeloImagenes) {
                try {
                    SQL = con.prepareStatement("INSERT INTO `imagen`("
                            + "`id_persona`,"
                            + "`tipo_imagen`,"
                            + "`numero_imagen`,"
                            + "`imagen`,"
                            + "`estado`) "
                            + "VALUE (?,?,?,?,?);", SQL.RETURN_GENERATED_KEYS);
                    SQL.setInt(1, modeloImagen.getModelo_persona().getId());
                    SQL.setString(2, modeloImagen.getTipo_imagen());
                    SQL.setInt(3, modeloImagen.getNumero_imagen());
                    SQL.setString(4, modeloImagen.getImagen());
                    SQL.setString(5, modeloImagen.getEstado());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                    }
                } catch (SQLException e) {
                    System.out.println("Controladores.ControladorImagen.Insert() " + e);
                    resultado = "-2";
                }
            }
            SQL.close();
            con.close();
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Controladores.ControladorImagen.Insert() " + ex);
        }
        return resultado;
    }

    private String Update(LinkedList<ModeloImagen> listamodeloImagenes) {
        resultado = Delete(listamodeloImagenes);
        if ("1".equals(resultado)) {
            resultado = Insert(listamodeloImagenes);

        }
        return resultado;
    }

    private String Delete(LinkedList<ModeloImagen> listamodeloImagenes) {
        try {
            con = conexion.abrirConexion();
            for (ModeloImagen modeloImagen : listamodeloImagenes) {
                try {
                    SQL = con.prepareStatement("DELETE FROM `imagen` "
                            + "WHERE `id_persona` = ? ;");
                    SQL.setInt(1, modeloImagen.getModelo_persona().getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                    }
                    resultado = "1";
                } catch (SQLException e) {
                    System.out.println("Controladores.ControladorImagen.Delete() " + e);
                    resultado = "-2";
                }
            }
            SQL.close();
            con.close();
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Controladores.ControladorImagen.Insert() " + ex);
        }
        return resultado;
    }
}
