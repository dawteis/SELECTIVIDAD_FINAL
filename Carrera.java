
package select_final;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author C
 */
public class Carrera {

    // atributos
    int codigo;
    int nplazas;

    // metodo constructor
    public Carrera(int codigo, int nplazas) {
        this.codigo = codigo;
        this.nplazas = nplazas;
    }

    //método para inicializar el array de carreras
    public static void inicializar(Carrera carreras[]) {
        for (int i = 0; i < carreras.length; i++) {
            carreras[i] = new Carrera(-1, -1);

        }
    }

    //método cargar datos - desde un fichero
    public static int leerDatos(Carrera aaa[]) {
        // devuelve el numero de datos leidos
        FileInputStream fichero;
        BufferedReader buffer;
        InputStreamReader isr;

        int celda = 0; // para movernos por las celdas del array
        String linea, scodigo, snplazas;
        int codigo, nplazas;

        try {
            fichero = new FileInputStream("C:\\ficherosjava\\fcarreras.txt");
            isr = new InputStreamReader(fichero, "UTF8");
            buffer = new BufferedReader(isr);

            //leemos las líneas del fichero y vamos poniendo los datos en el array 
            while ((linea = buffer.readLine()) != null) {
                /* se lee toda una linea y luego separamos los datos */

                if (linea.length() != 0) { // filtra líneas en blanco

                    linea = linea.trim();  // elimina los blancos por delante

                    String datos[] = linea.split(" "); //el separador de datos es el caracter espacio

                    /*como lo que se lee son strings hay que convertir los valores a int */
                    codigo = Integer.parseInt(datos[0]);
                    nplazas = Integer.parseInt(datos[1]);

                    //Cargamos los datos en cada celda del array carreras                
                    aaa[celda].codigo = codigo;
                    aaa[celda].nplazas = nplazas;

                    celda++;
                }
            }
            buffer.close();
            /* cerramos el fichero */

        } catch (IOException ex) {

            ex.printStackTrace();
        }
        return (celda);
    } //fin del método leerDatos
    
    public static int intentar (int codigo, Carrera carreras[]) {

        /* El método comprueba si hay plazas disponibles relacionadas a un 
           código. Si hay decrementa el número de plazas en 1. Y devuelve la 
           posición (asignado=c) */

        int asignado = 0;

        for (int c=0; c<carreras.length; c++) {

            if ((carreras[c].codigo == codigo) && (carreras[c].nplazas > 0)) {

                carreras[c].nplazas = carreras[c].nplazas - 1;
                asignado = c;
            }
        }

        return asignado;
    } 

} // fin de la clase Carrera
