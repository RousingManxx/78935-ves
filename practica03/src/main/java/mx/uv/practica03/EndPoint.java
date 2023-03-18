package mx.uv.practica03;
import java.util.ArrayList;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BuscarRequest;
import https.t4is_uv_mx.saludos.BuscarResponse;
import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.ModificarRequest;
import https.t4is_uv_mx.saludos.ModificarResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class EndPoint {
    ArrayList<String> list=new ArrayList<String>();//Creating arraylist
  
    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "SaludarRequest")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse response = new SaludarResponse();
        response.setRespuesta("Hola " + peticion.getNombre());
        
        list.add(peticion.getNombre());
        return response;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BuscarRequest")
    @ResponsePayload
    public BuscarResponse buscar(@RequestPayload BuscarRequest peticion) {
        BuscarResponse response = new BuscarResponse();
        if(list.size()==0){
            list.add("Alex");
        }
        response.setRespuesta("El saludo de la posicion "+peticion.getPosicion()+" con el nombre: "+list.get(peticion.getPosicion()));
        return response;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "EliminarRequest")
    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion) {
        EliminarResponse response = new EliminarResponse();
        response.setRespuesta("El saludo de la posicion: "+peticion.getPosicion()+" se ha eliminado"+" con el nombre: "+list.get(peticion.getPosicion()));
        list.remove(peticion.getPosicion());
        return response;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "ModificarRequest")
    @ResponsePayload
    public ModificarResponse Eliminar(@RequestPayload ModificarRequest peticion) {
        ModificarResponse response = new ModificarResponse();
        list.set(peticion.getPosicion(), peticion.getNombre());
        response.setRespuesta("El saludo de la posicion: "+peticion.getPosicion()+" se ha modificado"+" con el nombre: "+list.get(peticion.getPosicion()));
        
        return response;
    }
}
