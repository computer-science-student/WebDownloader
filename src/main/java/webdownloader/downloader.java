// The way to download a webpage was taken from here: https://www.tutorialspoint.com/Download-webpage-in-Java
// The way I took input in from the user was taken from here: (TODO add credit here later).
package webdownloader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
// import modules for copying file.
import java.io.File;
import java.nio.file.Files;


public class downloader {
   public static void main(String args[]) throws IOException {
	   // Creating BufferedReader Object
	   // InputStreamReader converts bytes to
	   // stream of character
	   BufferedReader BufferedReader_Name = new BufferedReader(new InputStreamReader(System.in));
	   
	   // Asking for input from user
	   System.out.println("Enter Webpage URL: ");
	   
		// String reading internally
		String String_name = BufferedReader_Name.readLine();
		
		download(String_name);
		//download("https://www.tutorialspoint.com");
		//download("http://www.google.com");
		
		BufferedReader translate = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Translate the webpage? Enter (y/n)");
		String translate_str = translate.readLine();
		
		// If user inputed "y" char, then make a copy of the of the file in the directory
		// and rename it translated_page.html.
		if ("y".equals(translate_str) || "Y".equals(translate_str)
				|| "yes".equals(translate_str) || "YES".equals(translate_str)) {
			System.out.println("Translated the web page.");
			// Make sure that the translated file actually exists! (This code assumes the user
			// inputed data correctly).
			//File translated_page = new File("translated_page.html");
			
			// make a copy of the text or translate it here.
			
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
}