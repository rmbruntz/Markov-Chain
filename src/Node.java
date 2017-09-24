import java.util.ArrayList;
import java.util.Random;

public class Node {

	private ArrayList<NodeProb> children;
	private String word;
	private int total;
	
	public Node(String word) {
		this.word = word;
		children = new ArrayList<NodeProb>();
		total = 0;
	}
	
	public Node getNext() {
		if (children.isEmpty()) {
			throw new IllegalStateException("No children of node \"" + word +"\"");
		}
		double rand = Math.random()*total;
		for (NodeProb node: children) {
			if (rand <= node.chance) {
				return node.node;
			} else {
				rand -= node.chance;
			}
		}
		return children.get(children.size()-1).node;
	}
	
	public String getWord() {
		return word;
	}

	public void registerChild(Node node) {
		boolean found = false;
		for (NodeProb np : children) {
			if (np.node == node) {
				np.chance++;
				total++;
				found = true;
				break;
			}
		}
		if (!found) {
			children.add(new NodeProb(node));
		}
	}
	
	
	
}
