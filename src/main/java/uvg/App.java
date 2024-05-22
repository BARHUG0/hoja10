package uvg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        FileManager fM = new FileManager();
        String result = fM.readTXTFile("guategrafo.txt");
        ArrayList<WeightedVertex> wArr = Tokenizer.getVertexsFromString(result);
        WeightedDigraph wDigrap = new WeightedDigraph(wArr);

        System.out.println("Bienvenido al Centro de Respuesta de COVID-19");
        boolean program = true;
        Scanner scanner = new Scanner(System.in);
        while(program){
            System.out.println("¿Qué dato de emergencia requiere atender?");
            System.out.println("1. Calcular la ruta más corta entre dos ciudades \n2. Indicar la ciudad en el centro del grafo\n3. Advertir sobre una interrupción de tráfico entre dos ciudades\n4. Establecer un nuevo valor de distancia entre dos ciudades\n5. Mostrar la matriz del grafo ponderado\n6. Mostrar la matriz generada por el Algoritmo de Floyd\n7. Salir");
            String op = scanner.nextLine();
            String startingNode = "";
            String endNode = "";
            switch (op) {
                case "1":
                    startingNode = getStartingNode(wDigrap, scanner);
                    endNode = getEndingNode(wDigrap, scanner);
                    System.out.println("La distancia más corta entre " + startingNode + " y " + endNode + " es de " + wDigrap.getFloydPath(startingNode, endNode));
                    break;

                case "2":
                    System.out.println("La ciudad ubicada en el centro del grafo es " + wDigrap.getGraphCenter());
                    break;
            
                case "3":
                    startingNode = getStartingNode(wDigrap, scanner);
                    endNode = getEndingNode(wDigrap, scanner);
                    wDigrap.removeWeightedVertex(startingNode, endNode);
                    System.out.println("Se ha actualizado la información del grafo");
                    System.out.println("La nueva matriz de distancias es: ");
                    showFloydMatrix(wDigrap);
                    System.out.println("El nuevo centro es " + wDigrap.getGraphCenter());

                    break;

                case "4":
                    startingNode = getStartingNode(wDigrap, scanner);
                    endNode = getEndingNode(wDigrap,scanner);
                    System.out.println("¿Cuál es la nueva distancia entre estas ciudades?");
                    String newDistance = scanner.nextLine();
                    wDigrap.addWeightedVertex(startingNode, endNode, Float.parseFloat(newDistance));
                    System.out.println("Se ha actualizado la información del grafo");
                    System.out.println("La nueva matriz de distancias es: ");
                    showFloydMatrix(wDigrap);
                    System.out.println("El nuevo centro es " + wDigrap.getGraphCenter());
                    break;

                case "5":
                    showWeightMatrix(wDigrap);
                    break;
                
                case "6":
                    showFloydMatrix(wDigrap);
                    break;

                case "7":
                    program = false;
                    scanner.close();
                    break;

                default:
                    break;
            }
        }

        
    }

    public static String getStartingNode(WeightedDigraph wDigrap, Scanner scan){
        Set<String> nodes = wDigrap.getNodes();
        List<String> nodesList = new ArrayList<String>(nodes);
        
        int i = 0;
        for(String node : nodes){
            System.out.println(i + ". " + node );
            i++;
        }

        System.out.println("Ingrese el número de la ciudad inicial");
        String index = scan.nextLine();
        return nodesList.get(Integer.parseInt(index));
    }

    public static String getEndingNode(WeightedDigraph wDigrap, Scanner scan){
        Set<String> nodes = wDigrap.getNodes();
        List<String> nodesList = new ArrayList<String>(nodes);
        
        int i = 0;
        for(String node : nodes){
            System.out.println(i + ". " + node );
            i++;
        }

        System.out.println("Ingrese el número de la ciudad final");
        String index = scan.nextLine();
        return nodesList.get(Integer.parseInt(index));


    }

    public static void showFloydMatrix(WeightedDigraph wDigrap){
        Float[][] matrix = wDigrap.getFloydMatrix();

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(matrix[i][j] == Float.POSITIVE_INFINITY){
                    System.out.print("|   inf   ");
                }else{
                    System.out.print("|   " + matrix[i][j] + "   ");
                }
            }
            System.out.println("");
        }
    }

    public static void showWeightMatrix(WeightedDigraph wDigrap){
        Float[][] matrix = wDigrap.getWeigthMatrix();



        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(matrix[i][j] == Float.POSITIVE_INFINITY){
                    System.out.print("|   inf   ");
                }else{
                    System.out.print("|   " + matrix[i][j] + "   ");
                }
            }
            System.out.println("");
        }
    }

}
