package select_final;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * resuleve el problema de asignar plazas en las carreras según la nota de la
 * selectividad
 *
 * @author C
 */
public class Select_final {

    public static void main(String[] args) {

        // el objeto Carrera tiene código y nplazas
        // el array acarreras se carga a partir de los datos de un fichero
        int ncarreras;

        Carrera acarreras[];

        acarreras = new Carrera[60];
        
        /* para ordenar las asignaciones  se necesita un array */
        Asignacion aasignaciones [];
        aasignaciones = new Asignacion [200];
        
        Asignacion.inicializar (aasignaciones);
        
        int celda=0;  /* para movernos por el array de asignaciones */
        

        //Inicializar acarreras
        Carrera.inicializar(acarreras);
        // se carga leyendo los datos de un fichero
        ncarreras = Carrera.leerDatos(acarreras);

        System.out.println("leídos >> " + ncarreras + "registros");

        /* ahora leemos el fichero de estudiantes que contiene nota dni op1 op2 op3 */
        /* se lee un estudiante y se procesa ; no se guarda en ningún array */
        FileInputStream fichero;
        BufferedReader buffer;
        InputStreamReader isr;

        String linea;
        int dni, op1, op2, op3;
        float nota;

        try {
            fichero = new FileInputStream("C:\\ficherosjava\\festudiantes00.txt");
            isr = new InputStreamReader(fichero, "UTF8");
            buffer = new BufferedReader(isr);

            //Mientras haya una línea en el fichero, el programa la lee  
            while ((linea = buffer.readLine()) != null) {

                if (linea.length() != 0) { // filtra líneas en blanco
                    linea = linea.trim(); // elimina los blancos por delante
                    String datos[] = linea.split(" "); // separa los datos 

                    //Las variables son de tipo int - hay que convertir Strings to int
                    nota = Float.parseFloat(datos[0]);
                    dni = Integer.parseInt(datos[1]);
                    op1 = Integer.parseInt(datos[2]);
                    op2 = Integer.parseInt(datos[3]);
                    op3 = Integer.parseInt(datos[4]);

                    System.out.println("> " + nota + " " + dni + " " + op1 + " " + op2 + " " + op3);
                    
                    /* una vez leídos los datos de un estudiante se intenta asignarle una de sus opciones, por orden */
                    int asign;//codigo de carrera asignado al candidato
                    int r;  /* resultado de la llamada al método intentar */

                    r = Carrera.intentar(op1, acarreras);  // se intenta con la opcion1 
                    if (r != 0) {
                        asign = op1;

                    } else { // se intenta con la opcion2
                        r = Carrera.intentar(op2, acarreras);
                        if (r != 0) {
                            asign = op2;
                        } else {
                            r = Carrera.intentar(op3, acarreras);
                            if (r != 0) {
                                asign = op3;
                            } else {
                                /* si no se puede con ninguna se le asigna un -1 */
                                asign = -1;
                            }
                        }

                    }

            //Impresión de los resultados de la asignación
            System.out.println("El estudiante con el DNI " + dni  + " queda "

                + "matriculado en la carrera con el código: " + asign);
            
                
                
            /* para mostrar los resultados ordenados por dni hay que meterlos en un array */    
            /* carga de datos de asignación en un array tasignaciones[] 
            */
            
            aasignaciones[celda].dni= dni;
            aasignaciones[celda].carrera=asign;
            celda = celda+1; //incrementamos el valor        
            }  /* fin del if anidado de intentar asignar */
             
            }
            
            
            buffer.close();
            /* cerramos el fichero */
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /*  ahora ordenamos el array aasignaciones y lo mostramos */
        for (int i=0; i<aasignaciones.length; i++)
        {
            
            System.out.println("DNI " + aasignaciones [i].dni + " . . . "

                +  aasignaciones [i].carrera);
            
        }
         Asignacion.mostrar (aasignaciones);
        Asignacion.ordenar (aasignaciones);
        
        System.out.println(" ************************************* ");
        
        System.out.println(" ************************************* ");
        
        Asignacion.mostrar (aasignaciones);
        
        /* ahora se graba el array aasignaciones en un fichero */
        
        Asignacion.escribirDatos(aasignaciones);
        
                 
        
    } // del main

}
