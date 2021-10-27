// The way to download a webpage was taken from here: https://www.tutorialspoint.com/Download-webpage-in-Java
// The way I took input in from the user was taken from here: (TODO add credit here later).
package webdownloader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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