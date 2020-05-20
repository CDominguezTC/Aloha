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

    public String Insert(String[] Huellas, String Foto, String firma, ModeloPersona modelo) {
        LinkedList<ModeloImagen> listamodeloImagenes = new LinkedList<ModeloImagen>();
        listamodeloImagenes = Generar_lista_imagenes(Huellas, Foto, firma, modelo);
        for (ModeloImagen modeloImagen : listamodeloImagenes) {
            if (modeloImagen.getId() == null) {
                resultado = Insert(modeloImagen);
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
                modeloImagenHuella.setImagen(parteshuella[0] + parteshuella[1]);
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

    private String Insert(ModeloImagen modeloImagen) {
        try {
            con = conexion.abrirConexion();
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
//                    ControladorAuditoria auditoria = new ControladorAuditoria();
//                    try (ResultSet generatedKeys = SQL.getGeneratedKeys()) {
//                        if (generatedKeys.next()) {
//                            int i = (int) generatedKeys.getLong(1);
//                            auditoria.Insert("insertar", "usuario", user, i, "Se inserto el registro.");
//                        }
                    resultado = "1";
                    SQL.close();
                    con.close();
//                    }
                }
            } catch (SQLException e) {
                System.out.println("Controladores.ControladorImagen.Insert() " + e);
                resultado = "-2";
                SQL.close();
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Controladores.ControladorImagen.Insert() " + e);
            resultado = "-3";
        }
        return resultado;
    }
}
