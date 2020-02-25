import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArrayRightShift {
    public static void main(String args[]) {
        String[] inFileNames_p1 = {"inputData.txt", "inputData2.txt", "inputData3.txt"};
        String[] outFileNames_p1 = {"outputData.txt", "outputData2.txt", "outputData3.txt"};
        runFirstPart(inFileNames_p1, outFileNames_p1);
        String[] inFileNames_p2 = {"inputData_p2.txt", "inputData2_p2.txt", "inputData3_p2.txt"};
        String[] outFileNames_p2 = {"outputData_p2.txt", "outputData2_p2.txt", "outputData3_p2.txt"};
        runSecondPart(inFileNames_p2, outFileNames_p2);

    }

    public static int findMissngNo(StringBuilder strBldMissingNo) {
        int runningSum = 0;
        String s = strBldMissingNo.toString();
        String[] sArr = s.split(",");
        for (int i = 0; i < sArr.length; ++i) {
            if (sArr[i].equals("x") != true) {
                runningSum += Integer.parseInt(sArr[i].trim());
            }
        }
        for (int i = 1; i <= sArr.length; ++i) {
            runningSum -= i;
        }
        int missingNo = runningSum * -1;
        return missingNo;
    }

    public static int[] rightshift(int[] inputArray, int places) {
        int[] holdvalues = new int[places];
        int[] holdvalues2 = new int[inputArray.length - places];
        int[] returnArray = new int[inputArray.length];
        for (int i = 0; i < places; i++) {
            holdvalues[i] = inputArray[(inputArray.length) - (places - i)];
        }
        for (int i = 0; i < inputArray.length - places; i++) {
            holdvalues2[i] = inputArray[i];
        }
        for (int i = 0; i < inputArray.length; ++i) {
            if (i < places) {
                returnArray[i] = holdvalues[i];
            } else {
                returnArray[i] = holdvalues2[i - places];
            }
        }
        return returnArray; //placeholder
    }

    public static String printAndReturnArray(int[] toPrint) {
        StringBuilder rStr = new StringBuilder();
        System.out.print("[");
        rStr.append("[");
        for (int i = 0; i < toPrint.length; ++i) {
            if (i != toPrint.length - 1) {
                System.out.print(toPrint[i] + ",");
                rStr.append(toPrint[i] + ",");
            } else {
                System.out.print(toPrint[i]);
                rStr.append(toPrint[i]);
            }
        }
        System.out.println("]");
        rStr.append("]");
        String returnString = rStr.toString();
        return returnString;
    }

    //returns the array of ints, and the places to shift as the last array value
    public static int[] getArrayInfo(StringBuilder inputSBuild) {
        String intermediateString = inputSBuild.toString();
        String[] strNumArray = intermediateString.split(",");
        String places_intermediate = strNumArray[strNumArray.length - 1];
        String[] places_final = places_intermediate.split("places=");
        System.out.println(places_final[1]);
        int placesToShift = Integer.parseInt(places_final[1].trim());
        int[] inputArray = new int[strNumArray.length];
        for (int i = 0; i < strNumArray.length - 1; ++i) {
            inputArray[i] = Integer.parseInt(strNumArray[i]);
        }
        inputArray[inputArray.length - 1] = placesToShift;
        return inputArray;
    }

    public static StringBuilder readFile(File inputfile) {
        FileInputStream fInStream = null;
        StringBuilder inputString = new StringBuilder();
        if (inputfile.exists()) {
            System.out.println("Exists =" + inputfile.exists());
            System.out.println("Is readable = " + inputfile.canRead());
            try {
                fInStream = new FileInputStream(inputfile);
                int content;
                while ((content = fInStream.read()) != -1) {
                    System.out.print((char) content);
                    inputString.append((char) content);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fInStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return inputString;
    }

    public static void writeFile(String outputfilepath, String output) {
        File outputfile = new File(outputfilepath);
        FileOutputStream fOutStream = null;
        if (outputfile.exists()) {
            System.out.println("Exists =" + outputfile.exists());
            System.out.println("Is readable = " + outputfile.canRead());
            try {
                fOutStream = new FileOutputStream(outputfile);
                byte[] bytesarray = output.getBytes();
                fOutStream.write(bytesarray);
                fOutStream.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fOutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void runSecondPart(String[] inFileNames, String[] outFileNames) {
        for (int j = 0; j < inFileNames.length; j++) {
            String inpathname = "C:\\Users\\anurkuma\\Documents\\MobaXterm\\home\\personal_git_repos\\CS3354\\Assignment02\\src\\" + inFileNames[j];
            File inputfile = new File(inpathname);
            StringBuilder readStringBuilder = new StringBuilder(readFile(inputfile));
            int returnMissingNo = findMissngNo(readStringBuilder);
            String outputNum = String.valueOf(returnMissingNo);
            String outpathname = "C:\\Users\\anurkuma\\Documents\\MobaXterm\\home\\personal_git_repos\\CS3354\\Assignment02\\src\\" + outFileNames[j];
            writeFile(outpathname, outputNum);

        }
    }

    public static void runFirstPart(String[] inFileNames, String[] outFileNames) {
        for (int j = 0; j < inFileNames.length; j++) {
            String inpathname = "C:\\Users\\anurkuma\\Documents\\MobaXterm\\home\\personal_git_repos\\CS3354\\Assignment02\\src\\" + inFileNames[j];
            File inputfile = new File(inpathname);
            StringBuilder readStringBuilder = new StringBuilder(readFile(inputfile));

            int[] arrayInfo = getArrayInfo(readStringBuilder);
            int[] arr2shfit = new int[arrayInfo.length - 1];
            for (int i = 0; i < arrayInfo.length - 1; ++i) {
                arr2shfit[i] = arrayInfo[i];
            }
            int places2shift = arrayInfo[arrayInfo.length - 1];
            int[] returnArray = rightshift(arr2shfit, places2shift);

            String outputArray = printAndReturnArray(returnArray);
            String outpathname = "C:\\Users\\anurkuma\\Documents\\MobaXterm\\home\\personal_git_repos\\CS3354\\Assignment02\\src\\" + outFileNames[j];
            writeFile(outpathname, outputArray);
        }
    }

}
