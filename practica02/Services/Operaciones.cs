using System;
using WSDL.mensajes;

// Hacer uso de colecciones

using System.Collections.Generic;
using System.Linq;

namespace WSDL.operaciones{
    public class Operaciones : Mensajes{
        // Crear la coleccion
        List<string> coleccion = new List<string>();

        public string Saludar (string nombre){

            string msj="Hola "+nombre;
            // Agregar nombre a la coleccion
            coleccion.Add(nombre);

            return msj;
        }
        public string Mostrar (int id){
            return "El nombre es: "+coleccion[id];
        }
    }
}