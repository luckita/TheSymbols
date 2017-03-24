import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;

public class TheClass {

	public static void main(String[] args) throws IOException{
		//System.out.println("Welcome");
		
        String inputFile = "input.txt";
        String inputCharacters = "chars.txt";
        Hashtable<String, Integer> lettersCountTable = new Hashtable<String, Integer>();
        
        initializeCountTable(lettersCountTable, inputCharacters);

        readInput(lettersCountTable, inputFile);
        
        displayValues(lettersCountTable);
        
        writeOutput(lettersCountTable);
        
    }

	private static void writeOutput(Hashtable<String, Integer> lettersCountTable) {
		// TODO writes the input in a file
		String fileName = "output.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Enumeration<String> keys = lettersCountTable.keys();
            
            /*Enumeration enumeration = data.elements();
        List<Integer> list = Collections.list(enumeration);
        Collections.sort(list);*/
            List<String> list = Collections.list(keys); //
            Collections.sort(list);//
            System.out.print("Lista ordenada: " + list);//
            
            String currentKey = "";
            
            ListIterator<String> iterat = list.listIterator();
            while(iterat.hasNext()){
            	currentKey = iterat.next();
            	int count = lettersCountTable.get(currentKey);
            	for(int i=0; i<count; i++){
            		bufferedWriter.write(currentKey);
            	}
            }
            
            /*while(keys.hasMoreElements()){
            	currentKey = keys.nextElement();
            	int count = lettersCountTable.get(currentKey);
            	for(int i=0; i<count; i++){
            		bufferedWriter.write(currentKey);
            	}
            }esto lo dejaba en orden al reves*/
            

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

	private static void readInput(Hashtable<String, Integer> lettersCountTable, String inputFile) {
		// reads input
		String line = null;
		String filename = null;
		try {
            //get the name of the file to read
			String fileToRead = "";
            
            FileReader fileNameReader = new FileReader(inputFile);
            BufferedReader bufferedNameReader = new BufferedReader(fileNameReader);

            if((filename = bufferedNameReader.readLine()) != null) {
                System.out.println("\nFile to read: " + filename);
                fileToRead = filename.toLowerCase();
            }
            // Always close files.
            bufferedNameReader.close();

            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileToRead); //inputFile
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println("\nNext line: " + line + "\n");
            	String loweredLine;
                char c;
                for (int i = 0; i < line.length(); i++){
                	loweredLine = line.toLowerCase();
                    c = loweredLine.charAt(i);
                    if(c != ' '){
                    	lettersCountTable.put(c+"", lettersCountTable.get(c+"")+1);
                    	System.out.println("Read " + c);
                    }
                }
            }
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +  inputFile + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + inputFile + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}

	private static void displayValues(Hashtable<String, Integer> lettersCountTable) {
		// shows values in table on console
		System.out.println("\nDisplaying counts...");
		Enumeration<String> items = lettersCountTable.keys();
		String elem = "";
		while(items.hasMoreElements()){
			elem = items.nextElement();
		    System.out.println("Count for " + elem + " is " + lettersCountTable.get(elem));
		}
	}

	private static void initializeCountTable(Hashtable<String, Integer> lettersCountTable, String inputCharacters) {
		//adds letters and value 0 before start counting, can be done with EntrySet
		//hardcoded a,b,c,d //TODO read from a file the available characters and add them with zero count
		//inputCharacters es la ruta al archivo donde estan los characters aceptados
		
		/*lettersCountTable.put("a", 0);
		lettersCountTable.put("b", 0);
		lettersCountTable.put("c", 0);
		lettersCountTable.put("d", 0);*/
		
		System.out.println("Creating character set...");
		String line = null;
		try {
            FileReader fileReader = new FileReader(inputCharacters);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println("\nNext line: " + line);
                char c;
                for (int i = 0; i < line.length(); i++){
                    c = line.charAt(i); 
                    if(c != ' ' & !lettersCountTable.containsKey(c)){
                    	lettersCountTable.put(c+"", 0);
                    	//System.out.println("Added " + c);
                    }
                }
            }
            // Always close files.
            bufferedReader.close();
            System.out.println("Finished creating character set.");
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" +  inputCharacters + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + inputCharacters + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
}
