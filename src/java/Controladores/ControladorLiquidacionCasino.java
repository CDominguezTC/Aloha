/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Tools.GenerarExcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorLiquidacionCasino
{

    public void Select (String GenerarLiquidacionCasino, HttpServletRequest request, HttpServletResponse response)
    {
        switch (GenerarLiquidacionCasino)
        {
            case "GenerarLiquidacionCasino":
                String SQLReporte = "SELECT p.identificacion AS \"IDENTIFICACION \", CONCAT(p.nombres, \" \", p.apellidos) AS "
                        + "\"PERSONA \", e.nombre AS \"EMPRESA \", cc.codigoInterno AS \"CODIGO CC \", cc.nombre AS \"NOMBRE CC \","
                        + " gc.nombre AS \"GRUPO CONSUMO \", h.nombre AS \"CONSUMO \", c.Fechaconsumo AS \"FECHA CONSUMO \", \" \" AS "
                        + "\"VALOR CONSUMO \", \" \" AS \"% DESCUENTO \", \" \" AS \"SUB TOTAL \", \" \" AS \"% IMPUESTO \", \" \" AS "
                        + "\"VALOR IMPUESTO \", co.costo AS \"VALOR T CONSUMO \" FROM consumo c join persona p on (c.personaId = p.id) "
                        + "join empresa e on (e.id = p.Id_EmpresaTrabaja) join centrocosto cc on (c.centrodecostoId = cc.id) "
                        + "join grupoconsumo gc on (c.grupoconsumoId = gc.id) join horarioconsumo h on (c.horarioconsumoId = h.id) "
                        + "join asocgrupohorario co on (co.horarioconsumoid = h.id AND co.grupoconsumoid = gc.id) "
                        + "WHERE c.Fechaconsumo >= '" + request.getParameter ("FechaIni") + "' AND c.Fechaconsumo <= '" + request.getParameter ("FechaFin") + " 23:59:59' order by c.Fechaconsumo";
                try
                {
                    String UrlArchivo = "C:\\Zred\\AlohaFiles\\LIQUIDACION_CASINO.xls";//request.getParameter("PlantillaUrl");                
                    String newQuery = SQLReporte;
                    //ControladorExcel controladorExcel = new ControladorExcel();
                    GenerarExcel generarExcel = new GenerarExcel ();
                    String archivo = generarExcel.GenerarExcel (UrlArchivo, newQuery);
                    downloadFile (response, archivo);

                } //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
                catch (Exception ex)
                {
                    //Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }
    }

    protected void downloadFile (HttpServletResponse response, String filePath)
            throws ServletException, IOException
    {

        File fileToDownload = new File (filePath);
        FileInputStream fileInputStream = new FileInputStream (fileToDownload);

        ServletOutputStream out = response.getOutputStream ();
        String mimeType = new MimetypesFileTypeMap ().getContentType (filePath);

        response.setContentType (mimeType);
        response.setContentLength (fileInputStream.available ());
        response.setHeader ("Content-Disposition", "attachment; filename=\""
                + fileToDownload.getName () + "\"");

        int c;
        while ((c = fileInputStream.read ()) != -1)
        {
            out.write (c);
        }
        out.flush ();
        out.close ();
        fileInputStream.close ();
    }
}
