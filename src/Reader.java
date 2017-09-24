import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

	private Scanner input;
	private boolean returnPeriod;
	
	public Reader(String fileName) throws FileNotFoundException {
		input = new Scanner(new File(fileName));
		returnPeriod = false;
	}
	
	public String getNextWord() {
		if (returnPeriod) {
			returnPeriod = false;
			return ".";
		}
		
		String next = input.next();
		
		if (next.charAt(next.length()-1) == '.' || next.charAt(next.length()-1) == '?' || next.charAt(next.length()-1) == '!') {
			returnPeriod = true;
			next = next.substring(0, next.length()-1);
		}
		
		for(int i = 0; i < next.length(); i++) {
			if (!(Character.isAlphabetic(next.charAt(i)) || next.charAt(i) == '-' || next.charAt(i) == '\'')) {
				next = next.substring(0, i) + next.substring(i+1);
				i--;
			}
		}
		
		return next.toLowerCase();
	}
	
	public boolean hasNext() {
		return input.hasNext() || returnPeriod;
	}
}
