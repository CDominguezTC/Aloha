/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Herramienta.Herramienta;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Carlos A Dominguez D
 */
@Path("Insert_Dato_Cedula")
public class InsertDatoCedulaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InsertDatoCedulaResource
     */
    public InsertDatoCedulaResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.InsertDatoCedulaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("NumeroCedula") String NumeroCedula, 
            @HeaderParam("Token") String Token) {
        Herramienta herraminetas = new Herramienta();
        if (Token != null) {
            if (Token == null ? herraminetas.getSHA256("800211327") == null : Token.equals(herraminetas.getSHA256("800211327")) ) {
                return "Comnexion Aceptada " + NumeroCedula;
            }
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of InsertDatoCedulaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
