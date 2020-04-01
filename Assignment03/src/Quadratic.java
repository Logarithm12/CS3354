import java.lang.Math;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Quadratic {
    public static void main(String args[]) {
        String filePath = "input.txt";
        String output = "";
        String outputfilePath = "output.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            QuadraticEquation qEq;
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] vals = sCurrentLine.split(",");
                qEq = new QuadraticEquation(Double.valueOf(vals[0]), Double.valueOf(vals[1]), Double.valueOf(vals[2]));
                output += testQuadratic(qEq);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        WriteToFile(output, outputfilePath);

    }

    public static String testQuadratic(QuadraticEquation testQ) {
        Complex[] roots = testQ.findRoots();
        String output = testQ.toString() + "\n" + roots[0].toString() + "\n" + roots[1].toString();
        Complex addTest = roots[0].add(roots[1]);
        Complex multiplyTest = roots[0].multiply(roots[1]);
        double negbOvera = (-1) * (testQ.b / testQ.a);
        double cOvera = testQ.c / testQ.a;
        output += "\nadding root1 and root2: " + addTest + "\nnegative b over a: " + Double.toString(negbOvera);
        output += "\nmultiplying root1 and root2: " + multiplyTest + "\nc over a: " + Double.toString(cOvera) + "\n\n";
        return output;

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

class QuadraticEquation {

    double a, b, c;

    public QuadraticEquation() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }

    public QuadraticEquation(double newA, double newB, double newC) {
        this.a = newA;
        this.b = newB;
        this.c = newC;
    }

    public Complex[] findRoots() {
        Complex[] roots = new Complex[2];
        Complex root1 = new Complex();
        Complex root2 = new Complex();
        double bOver2a = ((-b) / (2 * a));
        double discriminant = (b * b) - (4 * a * c);

        if (discriminant > 0) {
            double tempDiscriminant = Math.sqrt(discriminant) / (2 * a);
            root1.realpart = bOver2a + tempDiscriminant;
            root2.realpart = bOver2a - tempDiscriminant;

        } else if (discriminant < 0) {
            double tempDisciminant = (Math.sqrt((-1) * (discriminant))) / (2 * a);
            root1.realpart = bOver2a;
            root1.imaginarypart = tempDisciminant;
            root2.realpart = bOver2a;
            root2.imaginarypart = (-1) * tempDisciminant;

        } else if (discriminant == 0) {
            root1.realpart = bOver2a;
            root2.realpart = bOver2a;
        }
        roots[0] = root1;
        roots[1] = root2;
        return roots;
    }

    @Override
    public String toString() {
        return "a = " + Double.toString(this.a) + " b = " + Double.toString(this.b) + " c = " + Double.toString(this.c);
    }
}

class Complex {

    double realpart, imaginarypart;

    public Complex() {
        this.realpart = 0;
        this.imaginarypart = 0;
    }

    public Complex(double newRealpart, double newImaginarypart) {
        this.realpart = newRealpart;
        this.imaginarypart = newImaginarypart;
    }

    public void setRealpart(double newRealPart) {
        this.realpart = newRealPart;
    }

    public void setImaginarypart(double newImaginaryPart) {
        this.imaginarypart = newImaginaryPart;
    }

    public double getRealpart() {
        return realpart;
    }

    public double getImaginarypart() {
        return imaginarypart;
    }

    public Complex add(Complex complex2add) {
        Complex complex2return = new Complex();
        complex2return.realpart = this.realpart + complex2add.realpart;
        complex2return.imaginarypart = this.imaginarypart + complex2add.imaginarypart;
        return complex2return;
    }

    public Complex multiply(Complex complex2multiply) {
        Complex complex2return = new Complex();
        double x1 = this.realpart, y1 = this.imaginarypart;
        double x2 = complex2multiply.realpart, y2 = complex2multiply.imaginarypart;
        complex2return.realpart = (x1 * x2) + ((-1) * y1 * y2);
        complex2return.imaginarypart = (x1 * y2) + (x2 * y1);
        return complex2return;
    }

    @Override
    public String toString() {
        return "real part = " + Double.toString(this.realpart) + " imaginary part = " + Double.toString(this.imaginarypart);
    }
}