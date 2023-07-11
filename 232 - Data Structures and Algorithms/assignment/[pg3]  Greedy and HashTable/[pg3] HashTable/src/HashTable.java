import java.util.Scanner;
public class HashTable {

	public static void main(String[] args) {
		HashArray ha = new HashArray(10);
		printMenu(ha);
	}

	public static void printMenu(HashArray ha) {
		int choice;
		do {
			System.out.println("");
			System.out.println("Press 1 to Insert");
			System.out.println("Press 2 to Search");
			System.out.println("Press 3 to Delete");
			System.out.println("Press 4 to Print HashTable");
			System.out.println("Press 0 to exit");
			Scanner r = new Scanner(System.in);
			choice = r.nextInt();
			switch (choice) {
			case 1: 
				System.out.println("Which number(key) would you like to insert? ");
				int c = r.nextInt();
				ha.insert(c);
				break;
			case 2:
				System.out.println("Enter key to find value ");
				int ch = r.nextInt();
				ha.search(ch);
				break;
			case 3:
				System.out.println("Enter key to delete ");
				int cho = r.nextInt();
				ha.delete(cho);
				break;
			case 4:
				ha.printHashTable();
				break;
			case 0:
				System.out.println("Goodbye!");
				break;

			}
		} while (choice != 0);
	}
}
// NEW CLASS -------------------------------------------------------------------------------------------
class HashNode { //Key Value Pair Class
	int value;
	int key;
	
	HashNode(int key){
		this.key = key;
	}
}
//NEW CLASS -------------------------------------------------------------------------------------------
class HashArray { // Array of Key Value Pairs
	static int count = 0;
	HashNode[] hashArray;
	static int size;
	
	HashArray(int s){
		HashArray.size = s;
		hashArray = new HashNode[s];
	}
	
	public void search(int key) {
		int index = this.hash(key);
		int i = 1;
		try {
			while(hashArray[index].key != key) {
				index = (hash(key) + (i*i)) % size;
				i = i + 1;
			}
			System.out.println("Key: "+key+" Value: "+this.hash(key)+" Index: "+index);
		} catch (NullPointerException e){
			System.out.println("Key "+key+" is not in the Hash Table");
		}
		
	}
	
	public void delete(int key) {
		count--;
		int index = this.hash(key);
		int i = 1;
		try {
			while(hashArray[index].key != key) {
				index = (hash(key) + (i*i)) % size;
				i = i + 1;
			}
			System.out.println("Deleted (Key: " + key + " Value " + hashArray[index].value + " Index " + index + ")");
			hashArray[index] = null;
		} catch (NullPointerException e){
			System.out.println("Key "+key+" is not in the Hash Table");
		}
	}
	
	public void insert(int key) {
		count++;
		int hashKey = this.hash(key);
		int index = hashKey;
		int i = 1;
		while(hashArray[index] != null) {
			index = (hash(key) + (i*i)) % size; //Quadratic Probing
			i = i + 1;
		}
		HashNode newHashNode = new HashNode(key);
		newHashNode.value = hashKey;
		hashArray[index] = newHashNode;
		if(this.loadFactor() >= .8) {  //Load Factor Rehash
			count = 0;
			this.rehash();
		}
	}
	
	public void printHashTable() {
		for(int i = 0; i < hashArray.length; i++) {
			if(hashArray[i] == null) {
				System.out.println("Index: " + i + "  \tKey: NONE  \tValue: NONE");
			} else { 
				System.out.println("Index: "+ i + "  \tKey: " + hashArray[i].key + "   \tValue: " + hashArray[i].value);
			}
		}
	}
	
	public void rehash() {
		int oldHashCapacity = hashArray.length;
		int newHashCapacity = oldHashCapacity * 2;
		size = newHashCapacity;
		HashNode[] newHashArray = new HashNode[newHashCapacity];
		HashNode[] oldHashArray = hashArray;
		hashArray = newHashArray;
		for(int i = 0; i < oldHashCapacity; i++) {
			if(oldHashArray[i] != null) {
				this.insert(oldHashArray[i].key);
			}
		}
	}
	
	public int hash(int key) { //Hash Function
		return key % size;
	}
	
	public double loadFactor() { //Calculate Load Factor
		double lf = (double) count / (double) size;
		return lf;
	}
}
