import java.io.FileNotFoundException;

public class Run {

	public static void main(String[] args) throws FileNotFoundException {
		Reader read = new Reader("TextTest");
		Chain chain = new Chain();
		boolean starter = true;
		while (read.hasNext()) {
			
			String next = read.getNextWord();
			if (starter){
				starter = false;
				chain.addStarter(next);
			} else {
				if (!next.equals(".")) {
					chain.add(next);
				} else {
					chain.stop();
					starter = true;
				}
			}
		}
		
		for (int i=0; i < 10; i++) chain.sentence();
	}
	
}
