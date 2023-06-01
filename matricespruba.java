package matricesprubea;

import java.io.*;

public class matricespruba {

    public static void main(String[] args) {
        String matrix1File = "matrix1.txt";
        String matrix2File = "matrix2.txt";
        String resultFile = "result.txt";

        // Lee las matrices desde los archivos
        int[][] matrix1 = readMatrixFromFile(matrix1File);
        int[][] matrix2 = readMatrixFromFile(matrix2File);

        // Multiplica las matrices
        int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);

        // Guarda el resultado en un archivo
        writeMatrixToFile(resultMatrix, resultFile);

        System.out.println("La multiplicación de matrices se ha completado y el resultado se ha guardado en " + resultFile);
    }

    public static int[][] readMatrixFromFile(String fileName) {
        int[][] matrix = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int rowCount = 0;
            int colCount = 0;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Salta las líneas vacías
                }
                String[] elements = line.split("\\s+");
                if (matrix == null) {
                    rowCount = elements.length;
                    matrix = new int[rowCount][];
                }
                matrix[colCount] = new int[rowCount];
                for (int i = 0; i < rowCount; i++) {
                    String element = elements[i];
                    if (!element.isEmpty()) {
                        matrix[colCount][i] = Integer.parseInt(element);
                    } else {
                        // Manejar el caso en el que el elemento esté vacío
                        // Por ejemplo, puedes asignar un valor predeterminado o mostrar un mensaje de error
                        // matrix[colCount][i] = 0; // Asignar un valor predeterminado de 0
                        // System.err.println("Elemento vacío encontrado en la matriz"); // Mensaje de error
                    }
                }
                colCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public static void writeMatrixToFile(int[][] matrix, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(matrix[i][j] + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;

        int[][] resultMatrix = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                int sum = 0;
                for (int k = 0; k < cols1; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }

        return resultMatrix;
    }
}
