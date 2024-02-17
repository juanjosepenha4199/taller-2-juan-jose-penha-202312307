package uniandes.dpoo.estructuras.logica;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
;
;/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista( )
    {
         Map<String, String> mapaOrdenado = new TreeMap<>(mapaCadenas);

        List<String> listaOrdenada = new ArrayList<>(mapaOrdenado.values());

        return listaOrdenada;
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
    	Map<String, String> mapaOrdenado = new TreeMap<>((str1, str2) -> str2.compareTo(str1));
        mapaOrdenado.putAll(mapaCadenas);

        List<String> listaLlavesOrdenadas = new ArrayList<>(mapaOrdenado.keySet());

        return listaLlavesOrdenadas;
    }
    

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( )
    {
    	if (mapaCadenas.isEmpty()) {
            return null;
        }
    	TreeMap<String, String> mapaOrdenado = new TreeMap<>(mapaCadenas);
        return mapaOrdenado.firstKey();
        
        
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
    	if (mapaCadenas != null && !mapaCadenas.isEmpty()) {
           
            TreeMap<String, String> mapaOrdenado = new TreeMap<>((str1, str2) ->
                    mapaCadenas.get(str2).compareTo(mapaCadenas.get(str1)));
            
            mapaOrdenado.putAll(mapaCadenas);

            return mapaOrdenado.firstKey();
        }

        return null;
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
    	 if (mapaCadenas != null && !mapaCadenas.isEmpty()) {

             Collection<String> llaves = mapaCadenas.keySet();

            
             Collection<String> llavesEnMayusculas = new ArrayList<>();

             for (String llave : llaves) {
                 llavesEnMayusculas.add(llave.toUpperCase());
             }

             return llavesEnMayusculas;}
		return new ArrayList<>();
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
    	 if (mapaCadenas != null && !mapaCadenas.isEmpty()) {
             // Utilizar un conjunto (Set) para almacenar los valores únicos
             Set<String> valoresUnicos = new HashSet<>(mapaCadenas.values());

             // Retornar la cantidad de valores únicos en el conjunto
             return valoresUnicos.size();
         }

         // Retornar 0 si el mapa está vacío o no está inicializado
         return 0;
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	 if (!mapaCadenas.containsValue(cadena)) {
             String cadenaInvertida = new StringBuilder(cadena).reverse().toString();
             mapaCadenas.put(cadenaInvertida, cadena);
         }
    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	if (mapaCadenas != null && mapaCadenas.containsKey(llave)) {
            mapaCadenas.remove(llave);}
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
    	if (mapaCadenas != null) {

            Iterator<Map.Entry<String, String>> iterator = mapaCadenas.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (valor.equals(entry.getValue())) {
                    iterator.remove();
                    break;  
                }
            }
        }
    }


    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	if (mapaCadenas != null) {
            mapaCadenas.clear();

            for (Object objeto : objetos) {
                String cadena = Objects.toString(objeto, null);
                mapaCadenas.put(cadena, cadena);
            }
        }
    }
    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	if (mapaCadenas != null) {
            Map<String, String> nuevoMapa = new HashMap<>();
            for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
                String llaveEnMayusculas = entry.getKey().toUpperCase();
                nuevoMapa.put(llaveEnMayusculas, entry.getValue());
            }
            mapaCadenas = nuevoMapa;
        }
    }
    

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
    	if (mapaCadenas != null) {
           
            for (String elemento : otroArreglo) {
                if (!mapaCadenas.containsValue(elemento)) {
                    return false;  
                }
            }
            return true; 
        }
        return false;  
    }
    }


