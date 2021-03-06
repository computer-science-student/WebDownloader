package webdownloader;
// The way to download a webpage was taken from
// here: https://www.tutorialspoint.com/Download-webpage-in-Java
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
import java.lang.StringBuilder; // for converting html to text

public class downloader {
	public static String urlString;
	public static String content;

	// Helper function for saveWebPage
	// Reads user's commands, returns the command back as a string.
	public static String downloadPrompt() throws IOException {	
		// Creating BufferedReader Object to read user's command.
		BufferedReader websiteToDownload = new BufferedReader(new InputStreamReader(System.in));
		// Asking for input from user
		System.out.println("Save Webpage locally. Enter the full URL:");
		System.out.println("Or type in \"app\" without qoutes to retrieve "
				+ "the URL from another, external application.");
		// Return user's input.
		return websiteToDownload.readLine();
	}
	
	// Downloads the webpage the user wanted. Helper function for saveWebPage.
	// If user typed in app, read the URL from the microservice.
	// If not, then download whatever URL the user wants.
	// WARNING: Hard-coded location will need to be changed in this function!
	// Please change it to the directory of your choosing!
	public static void chooseDownload(String downloadChosen) throws IOException {
		
		// If user inputed "app" then read the url from the .csv file.
		if ("app".equals(downloadChosen) ||"APP".equals(downloadChosen)
				|| "application".equals(downloadChosen)
				|| "APPLICATION".equals(downloadChosen)) {
			// Retrieve the url from website.csv file. Microservice generates website.csv.
			
			// This will give errors if website.csv is NOT located in python-cs-361.
			String readFile = "python-cs-361\\website.csv";
			
			readDataLineByLine(readFile);
			// Download the webpage here
			download(urlString);
			System.out.println("Retrieved url " + urlString + 
					" from external application. Webpage downloaded.");
		} else { // If the string is not app then download the webpage!
			download(downloadChosen); }}
	
	// Writes the downloaded webpage to the drive.
	// Helper function for saveWebPage.
	public static void writePage() {
		// Create string from page.html. Write the file.
		// Source: https://stackoverflow.com/questions/12035316/reading-entire-html-file-to-string
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader("page.html"));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
		}
		content = contentBuilder.toString();
	}
	
	// Prompts the user for webpage to download then downloads it.
	public static void saveWebPage() {
		try {
			chooseDownload(downloadPrompt());
			writePage();
		} catch (IOException e) {
			System.out.println("Failed to read user's webpage.");
			e.printStackTrace();
		}}
	
	
    // Checks if the user wants to translate the page.
    // Returns true if they want to translate; false otherwise.
	public static boolean checkIfTranslation(String translateStr){
		if ("y".equals(translateStr) || "Y".equals(translateStr)
				|| "yes".equals(translateStr) || "YES".equals(translateStr)) {
			return true;
		}
		else if ("n".equals(translateStr) || "N".equals(translateStr) ||
				"no".equals(translateStr) || "NO".equals(translateStr)) {
			System.out.println("You decided not to translate the webpage.");
			return false;
		}
		else {
			System.out.println("Text not recognized. Not translating the webpage.");
			return false;
			}
	}
	
	// translationPrompt() asks the user if they want
	// the webpage they downloaded to be translated.
	// Returns the user's answer.
	public static String translationPrompt() throws IOException {
		BufferedReader translate = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Translate the webpage? (Must have webpage downloaded). Enter (y/n)");
		System.out.println("If you choose to translate the webpage, you will be asked what language to translate it to.");
		return translate.readLine();
	}
	
	// languageCodePrompt() asks the user for the language code
	// of the langauge they want to translate a webpage to.
	// Returns the language code.
	public static String languageCodePrompt() throws IOException {
		// Read another prompt from the user
		BufferedReader translate = new BufferedReader(new InputStreamReader(System.in));
		String languageCode = translate.readLine();
		
		System.out.println("You chose to translate to: " + languageCode);
		return languageCode;
	}
	
	// Prints a prompt of text to inform the user of
	// how translation works.
	public static void printTranslationText() {
		System.out.println("What language would you like to translate to?");
		System.out.println("Please use the codes seen here: "
				+ "https://cloud.google.com/translate/docs/languages to find the language code,"
				+ " and enter the code for the language you want to translate.");
		System.out.println("For example, if you want to translate to Chinese "
				+ "enter zh-TW or if you want to translate to Spanish enter es.");
		System.out.println("Failure to enter the correct code in this program"
				+ " will result in no translation or the wrong translation.");		
	}
	
	// Helps translateWebPage() send a translation request to microservice.
	public static void sendTranslationRequest() throws IOException {
		// If page.html is not in the directory, then the following code my cause errors.
		// If the user wants to translate, ask the user what language they want to translate to.
		printTranslationText();
		// Take language code from the user.
		String languageCode = languageCodePrompt();
		// Write the language code to the csv file.
		String translateFile = "python-cs-361\\file.csv";
		try (
	            Writer writer = Files.newBufferedWriter(Paths.get(translateFile));

	            CSVWriter csvWriter = new CSVWriter(writer,
	                    CSVWriter.DEFAULT_SEPARATOR,
	                    CSVWriter.NO_QUOTE_CHARACTER,
	                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
	                    CSVWriter.DEFAULT_LINE_END);
	        ) {
	            String[] headerRecord = {}; // No headerRecord

	            csvWriter.writeNext(new String[]{"Original", content});
	            csvWriter.writeNext(new String[]{"Language", languageCode});
	        }
		// If the translated file does not exist, there may be errors!
		
		File writer = new File("page.html");

		System.out.println("Translation request sent.");

	}
	
	// TranslateWebPage asks the user if they want to translate the webpage,
	// then follows the user's command.
	public static void translateWebPage() throws IOException {
		String translateStr = translationPrompt();
		
		// If the user wants to translate the webpage...
		if ("y".equals(translateStr) || "Y".equals(translateStr)
				|| "yes".equals(translateStr) || "YES".equals(translateStr)) {
			sendTranslationRequest();
		} else if ("n".equals(translateStr) || "N".equals(translateStr) ||
				"no".equals(translateStr) || "NO".equals(translateStr)) {
			System.out.println("You decided not to translate the webpage.");
			
		} else {
			System.out.println("Text not recognized. Not translating the webpage.");
		}		
	}

	public static void main(String args[]) throws IOException {
		saveWebPage();
		translateWebPage();		
   }
	// prints out text that helps the download(String urlString) function.
	public static void printDownloadText() {
		//
        System.out.println("Page downloaded as page.html to the directory of this application.\n");
        System.out.println("Warning: Next time you download a page, the new page you "
        		+ "download will overwrite page.html if page.html is kept in the same directory.");
        System.out.println("Save page.html to another directory to save it permanently.\n");		
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
         printDownloadText();
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
				if (sc.next().equals("website")) { // if we are reading in the website url
					urlString = sc.next(); // set public string for url
				}
			}   
			sc.close();  //closes the scanner
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		finally {
			}
	}
   
   
}