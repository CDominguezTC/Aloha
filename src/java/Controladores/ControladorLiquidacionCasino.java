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
 * Esta clase permite realizar la liquidacion de los consumos realizados por el
 * casino, esta clase contiene los sigientes metodos Select, DowloadFile
 *
 * @author: Carlos A Dominguez D
 * @version: 07/05/2020
 */
public class ControladorLiquidacionCasino {

    /**
     * Permite selecionar los valores de la tabal deacuerdo a unos parametros
     *
     * @author: Carlos A Dominguez D
     * @param request
     * @param response
     * @param GenerarLiquidacionCasino
     * @version: 07/05/2020
     */
    public void Select(String GenerarLiquidacion, HttpServletRequest request, HttpServletResponse response) {
        String SQLReporte;
        switch (GenerarLiquidacion) {
            case "GenerarLiquidacionCasino":
                SQLReporte = "SELECT p.identificacion AS \"IDENTIFICACION \", CONCAT(p.nombres, \" \", p.apellidos) AS "
                        + "\"PERSONA \", e.nombre AS \"EMPRESA \", cc.codigoInterno AS \"CODIGO CC \", cc.nombre AS \"NOMBRE CC \","
                        + " gc.nombre AS \"GRUPO CONSUMO \", h.nombre AS \"CONSUMO \", c.Fechaconsumo AS \"FECHA CONSUMO \", \" \" AS "
                        + "\"VALOR CONSUMO \", \" \" AS \"% DESCUENTO \", \" \" AS \"SUB TOTAL \", \" \" AS \"% IMPUESTO \", \" \" AS "
                        + "\"VALOR IMPUESTO \", co.costo AS \"VALOR T CONSUMO \" FROM consumo c join persona p on (c.personaId = p.id) "
                        + "join empresa e on (e.id = p.Id_EmpresaTrabaja) join centrocosto cc on (c.centrodecostoId = cc.id) "
                        + "join grupoconsumo gc on (c.grupoconsumoId = gc.id) join horarioconsumo h on (c.horarioconsumoId = h.id) "
                        + "join asocgrupohorario co on (co.horarioconsumoid = h.id AND co.grupoconsumoid = gc.id) "
                        + "WHERE c.Fechaconsumo >= '" + request.getParameter("FechaIni") + "' AND c.Fechaconsumo <= '" + request.getParameter("FechaFin") + " 23:59:59' order by c.Fechaconsumo";
                try {
                    String UrlArchivo = "C:\\Zred\\AlohaFiles\\LIQUIDACION_CASINO.xls";//request.getParameter("PlantillaUrl");                
                    String newQuery = SQLReporte;
                    //ControladorExcel controladorExcel = new ControladorExcel();
                    GenerarExcel generarExcel = new GenerarExcel();
                    String archivo = generarExcel.GenerarExcel(UrlArchivo, newQuery);
                    downloadFile(response, archivo);

                } //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
                catch (Exception ex) {
                    //Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "GenerarLiquidacionHoteleria":
                SQLReporte = "SELECT h.`id`, "
                        
                        + "p.`identificacion` AS No_Cedula, "
                        + "p.`id` AS IDPersona, CONCAT(p.`nombres`,\" \","                        
                        + "p.`apellidos`) AS Nombre, "
                        + "h.`cargo_persona` AS Cargo, "
                        + "h.`id_centro_costo`, "
                        + "c.`nombre` AS Nombre_Centro_Costo, "
                        + "h.`id_cargo_hoteleria`, "
                        + "g.`tipo_cargo` AS Nombre_Cargo_Hoteleria, "
                        + "h.`cargo_hoteleria_valor` AS Valor_Cargo, "
                        + "h.`fecha_consumo` AS Fecha_Cargo, "
                        + "h.`dia_consumo` AS Dia_Cargo "
                        + "FROM `consumo_hoteleria` h "
                        + "JOIN `persona` p ON (h.`id_persona` = p.`id`) "
                        + "JOIN `centro_costo` c ON (h.`id_centro_costo` = c.`id`) "
                        + "JOIN `cargo_hoteleria` g ON (h.`id_cargo_hoteleria` = g.`id`) "
                        + "WHERE h.fecha_consumo >= '" + request.getParameter("FechaIni") + "' "
                        + "AND h.fecha_consumo <= '" + request.getParameter("FechaFin") + " 23:59:59' "
                        + "AND h.`estado` = 'S' "
                        + "ORDER BY h.fecha_consumo;";
                try {
                    //String UrlArchivo = "C:\\Zred\\AlohaFiles\\LIQUIDACION_CASINO.xls";//request.getParameter("PlantillaUrl");                
                    String UrlArchivo = "Aloha\\AlohaFiles\\LIQUIDACION_HOTELERIA.xls";//request.getParameter("PlantillaUrl");                
                    String newQuery = SQLReporte;
                    //ControladorExcel controladorExcel = new ControladorExcel();
                    GenerarExcel generarExcel = new GenerarExcel();
                    String archivo = generarExcel.GenerarExcel(UrlArchivo, newQuery);
                    downloadFile(response, archivo);

                } //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
                catch (Exception ex) {
                    //Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }
    }

    /**
     * Permite descargar los archivos seleccionados a Excel
     *
     * @author: Carlos A Dominguez D
     * @param filePath
     * @param response
     * @version: 07/05/2020
     */
    protected void downloadFile(HttpServletResponse response, String filePath)
            throws ServletException, IOException {

        File fileToDownload = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(fileToDownload);

        ServletOutputStream out = response.getOutputStream();
        String mimeType = new MimetypesFileTypeMap().getContentType(filePath);

        response.setContentType(mimeType);
        response.setContentLength(fileInputStream.available());
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + fileToDownload.getName() + "\"");

        int c;
        while ((c = fileInputStream.read()) != -1) {
            out.write(c);
        }
        out.flush();
        out.close();
        fileInputStream.close();
    }
}
