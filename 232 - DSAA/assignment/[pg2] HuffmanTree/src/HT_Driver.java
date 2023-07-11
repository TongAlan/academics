/*
 * Alan Tong
 * Program 2 - Huffman Tree Coding
 * 5/29/2018
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

// NEW CLASS: -------------------------------------------------------------------

class Node implements Comparable<Node>{
	Node right;
	Node left;
	Node parent;
	int value;
	char character;

	Node(char character, int value){
		this.character = character;
		this.value = value;
	}

	public int compareTo(Node n) {
		if(this.value < n.value){
			return -1;
		}else if(this.value > n.value){
			return 1;
		}else {
			return 0;
		}	
	}
}

// NEW CLASS: ---------------------------------------------------------------

class HuffmanTree {
	Node root;
	PriorityQueue<Node> pq = new PriorityQueue<Node>(2);
	ArrayList<Character> uniqueCharacterArray;
	int[] countArray;
	String originalText;
	static ArrayList<String> codeTableNumber = new ArrayList<String>();
	static ArrayList<Character> codeTableCharacter = new ArrayList<Character>();
	String fullBinaryString = "";
	String decodedString = "";
	static PrintWriter writer = null;

	public void inputFile() {
		File finput = new File("input.txt");
		String str = "";
		try {
			Scanner s = new Scanner(finput);

			while(s.hasNextLine()) {
				str = s.nextLine();
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		originalText = str;
		System.out.println("Original Message: "+str);
	}

	public void constructFreqTable() { //construct two corresponding array (char and int) with characters and its frequency at respective index
		char[] originalTextArray = originalText.toCharArray(); 
		uniqueCharacterArray = new ArrayList<Character>();
		for(int i = 0; i<originalTextArray.length; i++) {
			if(!uniqueCharacterArray.contains(originalTextArray[i])) {
				uniqueCharacterArray.add(originalTextArray[i]);
			}
		}

		countArray = new int[uniqueCharacterArray.size()];
		for(int j = 0;j<countArray.length;j++) {
			char current = uniqueCharacterArray.get(j);
			for(int m = 0; m<originalTextArray.length;m++) {
				if(current == originalTextArray[m]) {
					countArray[j]++;
				}
			}
		}
	}

	public void printFreqTable() {
		System.out.println("FREQUENCY TABLE -------------------------------------------");
		for(int i = 0;i<uniqueCharacterArray.size();i++) {
			System.out.println("Character "+uniqueCharacterArray.get(i)+" appears "+countArray[i]+" times.");
		}
	}

	public static void codeTable(Node root, String str){ //recursive method to print code table
		if (root.left == null && root.right == null){

			System.out.println(root.character + ":" + str);
			codeTableCharacter.add(root.character);
			codeTableNumber.add(str);
			return;
		}
		codeTable(root.left, str + "0"); 
		codeTable(root.right, str + "1");
	}

	public void createHuffmanTree() {
		// Added Nodes to Min Heap - Priority Queue
		for(int i = 0;i<uniqueCharacterArray.size();i++) {
			Node newNode = new Node(uniqueCharacterArray.get(i), countArray[i]);
			pq.add(newNode);
		}

		// Creating Tree, removing the two lowest frequency characters and adding a combination of the character as a new node
		while(pq.size() != 1) {
			Node fmin = pq.poll();
			Node smin = pq.poll();

			Node newNode = new Node('\0',fmin.value + smin.value );
			newNode.left = fmin;
			newNode.right = smin;

			root = newNode;
			pq.add(newNode);

		}
	}

	public void encode() { 
		char[] cArray = originalText.toCharArray();
		fullBinaryString = "";

		for(int i = 0;i<cArray.length;i++) {
			char current = cArray[i];
			int index = codeTableCharacter.indexOf(current);
			String s = codeTableNumber.get(index);
			fullBinaryString = fullBinaryString + s;
		}
		System.out.println("Encoded Message: " + fullBinaryString);
	}

	public void decode() {
		String s = fullBinaryString;

		while(s.length() != 0) {
			Node r = root;
			while(r.left != null && r.right != null) {
				if(s.substring(0,1).equals("0")) {
					r = r.left;
				} else if(s.substring(0,1).equals("1")) {
					r = r.right;
				}
				s = s.substring(1);
			}
			decodedString = decodedString + r.character;
		}
		System.out.println("Decoded Message: check output.txt");
	}

	public void outputToFile() {
		try { 
			writer = new PrintWriter("output.txt");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

		writer.write(decodedString);
		writer.close();
	}
}

// NEW CLASS: -----------------------------------------------

public class HT_Driver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HuffmanTree hf = new HuffmanTree();
		hf.inputFile();

		hf.constructFreqTable();
		hf.printFreqTable();

		hf.createHuffmanTree();

		System.out.println("CODE TABLE ------------------------------------------------");
		hf.codeTable(hf.root, "");

		hf.encode();
		hf.decode();
		hf.outputToFile();
	}
}
