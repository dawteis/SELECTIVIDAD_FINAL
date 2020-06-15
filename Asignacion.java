
package select_final;

import java.io.*;

/**
 *
 * @author C
 */
public class Asignacion {
       // atributos
    int dni;
    int carrera;

    // metodo constructor
    public Asignacion (int dni, int carrera) {
        this.dni = dni;
        this.carrera = carrera;
    }

    //método para inicializar el array de asignaciones
    public static void inicializar(Asignacion  aaa []) {
        for (int i = 0; i < aaa.length; i++) {
            aaa [i] = new Asignacion (99999999, 999);

        }
    }
    
    public static void ordenar(Asignacion aaa[]) {
        /* ordena el array por el atributo dni */

        boolean cambio = true;
        Asignacion aux;

        while (cambio) {
            cambio = false;

            for (int i = 0; i < aaa.length - 1; i++) {

                if (aaa[i].dni > aaa[i + 1].dni) {
                    /* intercambio */
                    aux = aaa[i];
                    aaa[i] = aaa[i + 1];
                    aaa[i + 1] = aux;
                    cambio = true;

                }
            }
        }

    } // fin de ordenar 
    
    public static void mostrar(Asignacion aaa[]) {
        /* muestra el array asignaciones */

        for (int i = 0; i < aaa.length; i++) {

            System.out.println( aaa[i].dni + " . . . " + aaa[i].carrera);

        }

    }  // fin de mostrar
    
    //método escribir  datos - a un fichero
    public static int escribirDatos( Asignacion aaa[]) {
        // devuelve el numero de datos escritos
        
        //String nombreFichero = "c:/ficherosjava/ejemplo429.txt";
         String ficherosalida = "c:/ficherosjava/asignaciones.txt";
        String cr = System.lineSeparator();
        String linea;
       
        try {
        
        FileWriter ficherito = new FileWriter(ficherosalida); // crea nuevo
            
           // ficherito.write("PRUEBA DE ESCRITURA DE TEXTO" + cr);  
    
        
          
          /* recorremos el array y escribirmos en el fichero linea a linea */
            for ( int  i=0; i < aaa.length; i++) {
            
                
                
                linea= aaa[i].dni +" "+aaa[i].carrera;
                  
                        ficherito.write (linea + cr);     
                
/*            datosSalida.writeInt (aaa [i].dni);
            
            datosSalida.writeUTF ( " " );
            datosSalida.writeInt (aaa [i].carrera);
  */          
                        }
             ficherito.close();
           } catch (IOException exc) {
            System.out.println("Imposible abrir fichero de salida");
            return (-1);
        }
        
        System.out.println(" cerramos el fichero  ... " );
    
       /* try {
            
             ficherito.close();
          // osw.close();
           // datosSalida.close();
        } catch (IOException exc) {
            System.out.println("Error cerrando fichero de salida");
            return (-1);
        }
*/
        System.out.println();
        
       return (1);
    } //fin del método escribirDatos

    
    
} // fin de la clase asignacion
