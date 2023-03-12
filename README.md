# Como desplegar la practica02 con el archivo dockerfile
Para desplegar la práctica 02 con el archivo Dockerfile, se deben seguir los siguientes pasos en la terminal:

1. Clonar el repositorio de Github en la carpeta deseada:

`git clone https://github.com/RousingManxx/78935-ves.git`
 
2. Mover el archivo Dockerfile a la carpeta raíz:

`mv /root/78935-ves/practica02/Dockerfile /root`

3. Construir la imagen de Docker con la etiqueta despliegue usando el siguiente comando:

`docker build -t despliegue /root`

4. Ejecutar el contenedor de Docker con la imagen creada y mapear el puerto 80 del contenedor al puerto 5000 del host usando el siguiente comando:

`docker run --rm -it -p 80:5000 despliegue`
