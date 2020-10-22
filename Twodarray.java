//import necessary libraries
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
* This program uses 2d arrays to get student marks.
*
* @author  Ben Falsetto
* @version 1.0
* @since   2020-09-30
*/
public class Twodarray {
  
  /**
   * This is the main procedure.
  */
  public static void main(String[] args) {
    //getting and checking for correct input
    while (true) {
      try {
        //get the input type
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter '0' to have a dataset generated for you. ");
        System.out.print("Enter 1 to use your own dataset: ");
        int inputType = scanner.nextInt();
        System.out.println("");
        
        //check the input type
        if (inputType == 0) {
          genrandomcoordinator(); //call the gen random coordinator
          break;
        } else if (inputType == 1)  {
          parsetxtcoordinator(); //call the parse txt coordinator
          break;
        } else  {
          System.out.println("");
          System.out.println("Invalid input, try again");
        }
      } catch (Exception e) {
        System.out.println("");
        System.out.println("Invalid input, try again");
      }
    }
  }
  
  /**
   * This procedure takes a text file input and coordinates calculations.
  */
  public static void parsetxtcoordinator()  {
    //attenpting to get the filename
    try {
      //get the filename from the user
      Scanner scanner = new Scanner(System.in);
      System.out.print("Please enter filename with .txt: ");
      String fileName = scanner.next();
      
      //get the number of students in the class
      System.out.print("Enter the number of students: ");
      int classSize = scanner.nextInt();
      
      //close the scanner
      scanner.close();
      
      //getting the file and setting up a scanner to read it
      File myObj = new File(fileName);
      Scanner fileReader = new Scanner(myObj);
      
      //create the mark array
      float[][] markArray = new float[classSize][6];
      
      //creating the numStudent veriable
      int numStudent = 0;
      
      //actually starting to parse the file
      while (fileReader.hasNextLine() == true)  {
        //get the next line
        String line = fileReader.nextLine();
        
        //split the line by spaces and assign it to the mark array
        String[] assignArray = line.split("\\s+");
        
        for (int i = 0; i < assignArray.length; i++)  {
          //take each value from the temp array and assign them
          //to the int array
          markArray[numStudent][i] = Float.parseFloat(assignArray[i]);
        }
        //increment numStudent
        numStudent++;
      }
      
      //call each function to return the necessary values
        
      //calculating each student's average
      float[] studentMarks = splittoarray(numStudent, markArray);
      
      //calculating the class average
      float classAvg = calcavg(numStudent, studentMarks);
      
      //writing class average
      System.out.println("Class average: " + classAvg);
      
      //calculating the number of students who passed
      int numPassed = calcpass(numStudent, studentMarks);
      
      //writing numPassed
      System.out.println("Number of students passed: " + numPassed);
      
      //calculating the number of students who failed
      int numFail = calcfail(numStudent, studentMarks);
      
      //writing numFail
      System.out.println("Number of students failed: " + numFail);
      
      //calculating the number of students who achieved honour roll
      int honourRoll = honourroll(numStudent, studentMarks);
      
      //writing honour roll
      System.out.println("Number of students achieved honour roll: " + honourRoll);
      
      //writing all data to a txt file
      writetotxt(numStudent, studentMarks);
      
      //telling the user to check output.txt
      System.out.println("Each student's mark is available in output.txt");
      System.out.println(numStudent + " students are enrolled in this class");
      
    } catch (Exception err) {
      System.out.println("An error occurred.");
      err.printStackTrace();
    }
  }
  
  /**
   * This procedure generates marks and coordinates calculations.
  */
  public static void genrandomcoordinator() {
    //declaring variables and arrays
    int numStudent = 4;
    float[][] markArray = new float[4][6];
    
    //creating the random object
    Random randNumGen = new Random();
    
    //assigning random values to the array
    for (int i = 0; i < 4; i++) {
      for (int numMark = 0; numMark < 6; numMark++) {
        //generating a number between 1 and 100
        int randVal = randNumGen.nextInt(101);
        
        //assign that number to the array at index[i][numMark]
        markArray[i][numMark] = randVal;
      }
    }
    //call each function to return the necessary values
      
    //calculating each student's average
    float[] studentMarks = splittoarray(numStudent, markArray);
    
    //calculating the class average
    float classAvg = calcavg(numStudent, studentMarks);
    
    //writing class average
    System.out.println("Class average: " + classAvg);
    
    //calculating the number of students who passed
    int numPassed = calcpass(numStudent, studentMarks);
    
    //writing numPassed
    System.out.println("Number of students passed: " + numPassed);
    
    //calculating the number of students who failed
    int numFail = calcfail(numStudent, studentMarks);
    
    //writing numFail
    System.out.println("Number of students failed: " + numFail);
    
    //calculating the number of students who achieved honour roll
    int honourRoll = honourroll(numStudent, studentMarks);
    
    //writing honour roll
    System.out.println("Number of students achieved honour roll: " + honourRoll);
    
    //writing all data to a txt file
    writetotxt(numStudent, studentMarks);
    
    //telling the user to check output.txt
    System.out.println("Each student's mark is available in output.txt");
  }
  
  /**
   * calculating each student's average.
  */
  public static float[] splittoarray(int numStudent, float[][] markArray) {
    //creating the array to return at the end
    float[] studentMarks = new float[numStudent];
    
    //going through the array and calculating the averages
    for (int i = 0; i < numStudent; i++)  {
      //this is where each students average will be temporarily stored
      float runningAvg = 0;
      
      //adding each individual mark to the running average
      for (int c = 0; c < 6; c++) {
        //getting the mark
        String strAtC = String.valueOf(markArray[i][c]);
        
        //converting the mark to a float
        float floatAtC = Float.parseFloat(strAtC);
        
        //adding the mark to the running average
        runningAvg += floatAtC;
      }
      //calculating the student's average
      float studentAvg = runningAvg / 6;
      
      //adding the mark to the new array
      studentMarks[i] = studentAvg;
    }
    //returning the array
    return studentMarks;
  }
  
  /**
   * calculating the class average.
  */
  public static float calcavg(int numStudent, float[] studentMarks) {
    //declare variables
    float runAvg = 0;
    
    //add each average to the running average
    for (int i = 0; i < numStudent; i++)  {
      runAvg += studentMarks[i];
    }
    //calculate the class average
    float classAvg = runAvg / numStudent;
    
    //return the class average
    return classAvg;
  }
  
  /**
   * calculating the number of students who passed.
  */
  public static int calcpass(int numStudent, float[] studentMarks) {
    //declare variables
    int numPassed = 0;
    
    //determine how many passed
    for (int i = 0; i < numStudent; i++)  {
      //get the student's average
      float valAti = studentMarks[i];
      
      //determine passed/not passed
      if (valAti >= 50) {
        numPassed++;
      }
    }
    return numPassed;
  }
  
  /**
   * calculating the number of students who failed.
  */
  public static int calcfail(int numStudent, float[] studentMarks)  {
    int numFail = 0;
    
    //determine how many passed
    for (int i = 0; i < numStudent; i++)  {
      //get the student's average
      float valAti = studentMarks[i];
      
      //determine failed/not failed
      if (valAti < 50) {
        numFail++;
      }
    }
    return numFail;
  }
  
  /**
   * calculating the number of students who achieved honour roll.
  */
  public static int honourroll(int numStudent, float[] studentMarks)  {
    int honourRoll = 0;
    
    //determine number of students who achieved honour roll
    for (int i = 0; i < numStudent; i++)  {
      //get the student's average
      float valAti = studentMarks[i];
      
      //determine number of students who achieved honour roll
      if (valAti >= 80) {
        honourRoll++;
      }
    }
    return honourRoll;
  }
  
  /**
   * writing all data to a txt file.
  */
  public static void writetotxt(int numStudent, float[] studentMarks) {
    //declare variables
    //String strAti = "";
    String output = "Student's Marks:\r\n";
    
    //add each mark to the output string
    for (int i = 0; i < numStudent; i++)  {
      String strAti = String.valueOf(studentMarks[i]);
      
      //add strAti to the end of the output string
      output += strAti + "\r\n";
    }
    //filename
    String fileName = "output.txt";
    
    //writing to the output file
    try {
      //create the file writer
      FileWriter fileWriter = new FileWriter(fileName);
      
      //wraping the filewriter in a buffered reader
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      
      //writing the output string to the output file
      bufferedWriter.write(output);
      
      //closing the bufferedwriter
      bufferedWriter.close();
    } catch (Exception e) {
      //print the error to the console if an error occurs writing
      e.printStackTrace();
    }
  }
}