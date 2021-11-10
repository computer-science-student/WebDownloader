package webdownloader;
// The way to download a webpage was taken from here: https://www.tutorialspoint.com/Download-webpage-in-Java
// The way I took input in from the user was taken from here: (TODO add credit here later).
import java.net.URL;
// import modules for copying file.
import java.nio.file.Files;
import java.util.Scanner;
// modules for csv files
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class downloader {
   public static void main(String args[]) throws IOException {
	   // Creating BufferedReader Object
	   // InputStreamReader converts bytes to
	   // stream of character
	   BufferedReader BufferedReader_Name = new BufferedReader(new InputStreamReader(System.in));
	   
	   // Trying to read a file now.
	   // My teammate's .csv file has to contain the url of something I download.
	   String readFile = "C:\\Users\\jl\\Documents\\University\\Fall 2021\\CS 361\\project\\WebDownloader1 - Try4 - GUI\\src\\main\\java\\file.csv";
	   readDataLineByLine(readFile);
	      
	   // Asking for input from user
	   System.out.println("Save Webpage locally. Enter the full URL:");
	   System.out.println("Or type in \"app\" without qoutes to retrieve the URL from another application.");
	   
		// String reading internally
		String String_name = BufferedReader_Name.readLine();
		
		//download(String_name);
		//download("https://www.tutorialspoint.com");
		//download("http://www.google.com");
		
		BufferedReader translate = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Translate the webpage? (Must have webpage downloaded). Enter (y/n)");
		String translate_str = translate.readLine();
		
		// If user inputed "app" then read the url from the .csv file.
		if ("app".equals(translate_str) ||"APP".equals(translate_str) 
				||"application".equals(translate_str)
				|| "APPLICATION".equals(translate_str)) {
			System.out.println("Retrieved url from external application");
		}
		
		
		
		// If user inputed "y" char, then make a copy of the of the file in the directory
		// and rename it translated_page.html.
		if ("y".equals(translate_str) || "Y".equals(translate_str)
				|| "yes".equals(translate_str) || "YES".equals(translate_str)) {
			/*
			 * I watched this Youtube Tutorial: https://www.youtube.com/watch?v=R9Bmz-9ZAJM
			 * to learn how to copy a file. The following code assumes the user generated page.html
			 * correctly, and it makes a copy of the file to the same directory (the directory of
			 * this project).
			 */
			// If the user wants to translate, ask the user what language they want to translate to.
			System.out.println("What language would you like to translate to?");
			System.out.println("Please use the codes seen here: https://cloud.google.com/translate/docs/languages to find the language code, and enter the code for the language you want to translate.");
			System.out.println("For example, if you want to translate to Chinese enter zh-TW or if you want to translate to Spanish enter es.");
			System.out.println("Failure to enter the correct code in this program will result in no translation or the wrong translation.");
			
			// take code from user
			String language_code = translate.readLine();
			
			System.out.println("You chose to translate to: " + language_code);
			
			// Now write the code to a cvs file.
			String translateFile = "translate.csv";
			try (
		            Writer writer = Files.newBufferedWriter(Paths.get(translateFile));

		            CSVWriter csvWriter = new CSVWriter(writer,
		                    CSVWriter.DEFAULT_SEPARATOR,
		                    CSVWriter.NO_QUOTE_CHARACTER,
		                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
		                    CSVWriter.DEFAULT_LINE_END);
		        ) {
		            String[] headerRecord = {"Name", "Email", "Phone", "Country"};
		            csvWriter.writeNext(headerRecord);

		            csvWriter.writeNext(new String[]{"Sundar Pichai", "sundar.pichai@gmail.com", "+1-1111111111", "India"});
		            csvWriter.writeNext(new String[]{"Satya Nadella", "satya.nadella@outlook.com", "+1-1111111112", "India"});
		        }
			
			
			
			
			
			
			
			
			
			
			// Make sure that the translated file actually exists or else there may be errors!
			
			File writer = new File("page.html"); // makes a File object of the page.html.
			File translated_page = new File("translated_page.html");
			
			// make a copy of the text or translate it here.
			try {
				Files.copy(writer.toPath(),translated_page.toPath());
			}
			catch(Exception e) {
				//System.out.println("error");
			}
			System.out.println("Translated the web page.");
			
		} else if ("n".equals(translate_str) || "N".equals(translate_str) ||
				"no".equals(translate_str) || "NO".equals(translate_str)) {
			System.out.println("You decided not to translate the webpage.");
			
		} else {
			System.out.println("Text not recognized. Not translating the webpage.");
		}
		
		
		BufferedReader share = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Share webpage? Enter (y/n)");
		String share_str = share.readLine();		
		
   }
   public static void download(String urlString) throws IOException {
      URL url = new URL(urlString);
      try(
         BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
         BufferedWriter writer = new BufferedWriter(new FileWriter("page.html"));
      ) {
         String line;
         while ((line = reader.readLine()) != null) {
            writer.write(line);
         }
         System.out.println("Page downloaded.");
      }
   }
   
   
// Java code to illustrate reading a
// CSV file line by line taken from here:
// https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/
public static void readDataLineByLine(String file)
{
	try {
		Scanner sc = new Scanner(new File(file));  
		sc.useDelimiter(",");   //sets the delimiter pattern  
		while (sc.hasNext())  //returns a boolean value  
		{  
		System.out.print(sc.next());  //find and returns the next complete token from this scanner  
		}   
		sc.close();  //closes the scanner
	}
	catch (Exception e) {
		e.printStackTrace();	
	}
	finally {
        //csvReader.close();
		}

}

   
   
   
}