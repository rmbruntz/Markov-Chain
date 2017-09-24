import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Chain {

	private Map<String, NodeProb> starters;
	private int starterTotal;
	private Node current;
	private Map<String, Node> nodes;
	private Node stopNode;
	
	
	public Chain() {
		starterTotal = 0;
		starters = new HashMap<String, NodeProb>();
		nodes = new HashMap<String, Node>();
		stopNode = new Node(".");
		current = null;
	}
	
	public void addStarter(String word) {
		if (starters.containsKey(word)) {
			increment(starters.get(word));
		} else {
			if (!nodes.containsKey(word)) {
				Node newWord = new Node(word);
				nodes.put(word, newWord);
			} 
			addToStarters(word, nodes.get(word));
			current = nodes.get(word);
		}
	}
	
	public void add(String word) {
		if (!nodes.containsKey(word)) {
			nodes.put(word, new Node(word));
		}
		current.registerChild(nodes.get(word));
		current = nodes.get(word);
	}
	
	public void stop() {
		current.registerChild(stopNode);
	}
	
	private Node getRandomStarter() {
		double chance = Math.random()*starterTotal;
		Set<String> ks = starters.keySet();
		for (String s: ks) {
			NodeProb cur = starters.get(s);
			if (cur.chance >= chance) {
				return cur.node;
			} else {
				chance -= cur.chance;
			}
		}
		return starters.values().iterator().next().node;
	}
	
	private void increment(NodeProb np) {
		np.chance++;
		starterTotal++;
	}
	
	private void addToStarters(String s, Node n) {
		starters.put(s, new NodeProb(n));
		starterTotal++;
	}
	
	public void printNodes() {
		System.out.print("Nodes: ");
		for (Node n : nodes.values()) {
			System.out.print(n.getWord()+ " ");
		}
		System.out.println();
	}
	
	public void printStarters() {
		System.out.print("Starters: ");
		for (NodeProb n : starters.values()) {
			System.out.print(n.node.getWord()+ " " + n.chance + " ");
		}
		System.out.println();
	}
	
	public void sentence() {
		Node node = getRandomStarter();
		System.out.print(node.getWord().substring(0, 1).toUpperCase() + node.getWord().substring(1));
		node = node.getNext();
		while (node != stopNode) {
			System.out.print(" " + node.getWord());
			node = node.getNext();
		}
		System.out.println(".");
	}
}
