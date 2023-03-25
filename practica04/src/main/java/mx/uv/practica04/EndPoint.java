package mx.uv.practica04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
import https.t4is_uv_mx.saludos.BuscarSaludoRequest;
import https.t4is_uv_mx.saludos.BuscarSaludoResponse;
import https.t4is_uv_mx.saludos.DeleteSaludoRequest;
import https.t4is_uv_mx.saludos.DeleteSaludoResponse;
import https.t4is_uv_mx.saludos.EditSaludoRequest;
import https.t4is_uv_mx.saludos.EditSaludoResponse;
import https.t4is_uv_mx.saludos.MostrarSaludosResponse;



@Endpoint
public class EndPoint {
    @Autowired
    private ISaludador iSaludador;



    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos",localPart = "SaludarRequest")

    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola "+ peticion.getNombre());

        Saludador saludador = new Saludador();
        saludador.setNombre((peticion.getNombre()));
        iSaludador.save(saludador);
        return respuesta;
    }
    
    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BuscarSaludoRequest")
    @ResponsePayload
    public BuscarSaludoResponse Buscar(@RequestPayload BuscarSaludoRequest peticion) {
        BuscarSaludoResponse respuesta = new BuscarSaludoResponse();

        Saludador saludador = new Saludador();
        saludador = iSaludador.findById(peticion.getId()).get();

        respuesta.setRespuesta("El saludo de la posicion "+peticion.getId()+" con el nombre: "+saludador.getNombre());
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "DeleteSaludoRequest")
    @ResponsePayload
    public DeleteSaludoResponse Eliminar(@RequestPayload DeleteSaludoRequest peticion) {
        DeleteSaludoResponse respuesta = new DeleteSaludoResponse();

        Saludador saludador = new Saludador();
        saludador = iSaludador.findById(peticion.getId()).get();

        iSaludador.deleteById(peticion.getId());

        respuesta.setRespuesta("El saludo de la posicion: "+peticion.getId()+" se ha eliminado"+" con el nombre: "+saludador.getNombre());
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "EditSaludoRequest")
    @ResponsePayload
    public EditSaludoResponse Modificar(@RequestPayload EditSaludoRequest peticion) {
        EditSaludoResponse respuesta = new EditSaludoResponse();

        Saludador saludador = new Saludador();
        saludador = iSaludador.findById(peticion.getId()).get();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);

        respuesta.setRespuesta("El saludo de la posicion: "+peticion.getId()+" se ha modificado"+" con el nombre: "+saludador.getNombre());
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "MostrarSaludosRequest")
    @ResponsePayload
    public MostrarSaludosResponse Mostrar(){
        MostrarSaludosResponse respuesta = new MostrarSaludosResponse();
        // Recorrer lista
        Iterable<Saludador> lista = iSaludador.findAll();
        for (Saludador x : lista) {
            respuesta.getRespuesta().add("El nombre es:  "+x.getNombre()+" || con el id:  "+x.getId());
        }
        return respuesta;
    }
    
}
