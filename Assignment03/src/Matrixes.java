import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Matrixes {
    public static void main(String[] args) {
        String filePath = "MatrixInput.txt";
        String outputfile = "MatrixOutput.txt";
        String output = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Matrix mtx1, mtx2;
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] vals = sCurrentLine.split(",");
                mtx1 = new Matrix(Integer.valueOf(vals[0]), Integer.valueOf(vals[1]));
                mtx2 = new Matrix(Integer.valueOf(vals[2]), Integer.valueOf(vals[3]));
                Matrix addmatrix = mtx1.add(mtx2);
                Matrix multiplymatrix = mtx1.multiply(mtx2);
                output += "Matrix 1:\n" + mtx1 + "Matrix 2:\n" + mtx2;
                output += "Matrix 1 + Matrix 2:\n" + addmatrix;
                output += "Matrix 1 * Matrix 2:\n" + multiplymatrix+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        WriteToFile(output, outputfile);
    }

    public static void WriteToFile(String output, String filepath) {
        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

class Matrix {
    int[][] matrix;
    int rows, cols;

    public Matrix() {
        this.rows = 1;
        this.cols = 1;
        matrix = new int[rows][cols];
    }

    public Matrix(int rows, int cols) {
        Random rand = new Random();
        this.rows = rows;
        this.cols = cols;
        matrix = new int[this.rows][this.cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
        }
    }

    private void zeroTheMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public int getValue(int row, int col) {
        return matrix[row][col];
    }

    public void setValue(int row, int col, int value) {
        matrix[row][col] = value;
    }

    public Matrix add(Matrix matrix2add) {
        Matrix returnMatrix = null;
        boolean canAdd = (this.rows == matrix2add.rows && this.cols == matrix2add.cols);
        if (canAdd) {
            returnMatrix = new Matrix(rows, cols);
            returnMatrix.zeroTheMatrix();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    returnMatrix.matrix[i][j] = this.matrix[i][j] + matrix2add.matrix[i][j];
                }
            }
        }
        return returnMatrix;
    }

    public Matrix multiply(Matrix matrix2multiply) {
        boolean canMultiply = (this.cols == matrix2multiply.rows);
        Matrix returnMatrix = null;
        if (canMultiply) {
            returnMatrix = new Matrix(this.rows, matrix2multiply.cols);
            returnMatrix.zeroTheMatrix();
            for (int i = 0; i < this.rows; i++)
                for (int j = 0; j < matrix2multiply.cols; j++) {
                    for (int k = 0; k < this.cols; k++) {
                        returnMatrix.matrix[i][j] += this.matrix[i][k] * matrix2multiply.matrix[k][j];
                    }
                }

        }
        return returnMatrix;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output += this.matrix[i][j];
                if ((j - cols) != -1) {
                    output += ",";
                }
            }
            output += "\n";
        }

        return output;
    }
}