package fruitninja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Save {
    // score[1] for level1
    //score[2] for level 2
    //score[3] for level 3


    public static int  reader() throws IOException
     {
  
   int  score =0;


  String line =null;
  int numOfLines=0;

  try {
      FileReader fileReader = 
          new FileReader("save.txt");

      BufferedReader bufferedReader = 
          new BufferedReader(fileReader);

    	  line = bufferedReader.readLine();
          System.out.println(line);
          try {
        score=Integer.parseInt(line);
          }
          catch (Exception e) {
              System.out.println(" error");

		}
        numOfLines++;
        
      bufferedReader.close();         
  }     catch (FileNotFoundException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }

     
return score;
     }
///////////////////////////////////////////////////////////////////////////*

public static void Writer(int score){    
int i=0;
     try {
         FileWriter fileWriter =
             new FileWriter("save.txt");

         BufferedWriter bufferedWriter =
             new BufferedWriter(fileWriter);

         bufferedWriter.write(Integer.toString(score));
                     bufferedWriter.newLine();
                     i++;



         bufferedWriter.close();
     }
     catch(IOException ex) {
         System.out.println(
             "Error writing to file '"
             + "save.txt" + "'");
     }
}
}
