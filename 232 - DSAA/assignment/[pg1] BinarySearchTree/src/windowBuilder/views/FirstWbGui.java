/*
 *  Alan Tong
 *  Due 5/21/2018 @ 8 AM
 *  Program 1: Binary Search Tree Implementation
 *  Instructions: 
 *  I did add a GUI for the bonus points for this program. Running this will cause GUI to start.
 *  BST = Binary Search Tree
 *  Insert Button: 				Type an Integer into the Text Field and Hit Insert to add. 
 *  							Incorrectly inserting such as string or empty will result in a popup dialog error
 *  
 *  Delete Button: 				Type an Integer into the Text Field and Hit delete to delete
 *  							Incorrectly deleting such as a string, empty, or an integer not in the BST will result in a popup dialog error
 *  
 *  Insert to BST from File: 	Type a file name into the Text Field. I have an input.txt file included therefore "input.txt" is recommended
 *  							Unable to find file by spelling error or wrong directory path will result in an error dialog box popup
 *  
 *  Print BST Level By Level: 	Simply Clicking the button will print the BST level by level in the Text Area Box
 *  
 *  Clear BST:					Will Clear the BST by setting root to null
 *  
 *  In-Order Traversal: 		Clicking on the button will print the BST In Order Traversal in the Text Field next to it
 *  
 *  Pre-Order Traversal:		Same as In-Order Traversal but with Pre-Order
 *  
 *  Post-Order Traversal: 		Same as In-Order Traversal but with Post-Order
 *  
 *  Search: 					Type in Integer in the Middle Box (blank one) and hit the button "Search" will search through the BST
 *  							The result, either True or False, will display in the Text Box that reads "Result Will Show Here"
 */




package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import windowBuilder.bst.BST;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class FirstWbGui extends JFrame {

	private JPanel contentPane;
	private JTextField insertStringText;
	private JButton Insert;
	private BST binaryTree = new BST();
	private JButton Delete;
	private JTextField deleteStringText;
	private JButton printBST;
	private JButton InOrderButton;
	private JButton PreOrderButton;
	private JButton PostOrderButton;
	private JButton searchBoolButton;
	private JButton insertInputButton;
	private JTextField inputFileText;
	private JTextField inOrderTraversalBox;
	private JTextField PreOrderTraversalBox;
	private JTextField PostOrderTraversalBox;
	private JTextArea printLBL;
	private JTextField inputSearch;
	private JTextField searchResultBox;
	private JButton ClearBst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		File file = new File("input.txt");
		Scanner s;
		String[] array = null;
		try {
			s = new Scanner(file);
			while(s.hasNext()) {
				String str = s.next();
				array = str.split(",");
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	////////////////////////////////////////////////////////////////////////////////
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstWbGui frame = new FirstWbGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirstWbGui() {
		initComponent();
		createEvents();
		
	}

	////////////////////////////////////
	// This method contains all of the codes for creating and initializing components
	////////////////////////////////////
	private void initComponent() {
		setTitle("Binary Search Tree GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFirstName = new JLabel("");
		insertStringText = new JTextField();
		insertStringText.setColumns(10);
		Insert = new JButton("Insert");
		Delete = new JButton("Delete");
		deleteStringText = new JTextField();
		deleteStringText.setColumns(10);
		printBST = new JButton("Print BST Level By Level");
		InOrderButton = new JButton("In-Order Traversal");
		PreOrderButton = new JButton("Pre-Order Traversal");
		PostOrderButton = new JButton("Post-Order Traversal");
		insertInputButton = new JButton("Insert to BST from File");
		inputFileText = new JTextField();
		inputFileText.setColumns(10);
		inOrderTraversalBox = new JTextField();
		inOrderTraversalBox.setColumns(10);
		PreOrderTraversalBox = new JTextField();
		PreOrderTraversalBox.setColumns(10);
		PostOrderTraversalBox = new JTextField();
		PostOrderTraversalBox.setColumns(10);
		printLBL = new JTextArea();
		searchBoolButton = new JButton("Search");
		inputSearch = new JTextField();
		inputSearch.setColumns(10);
		searchResultBox = new JTextField();
		searchResultBox.setText("Result Will Show Here");
		searchResultBox.setColumns(10);
		
		ClearBst = new JButton("Clear BST");
		
		JLabel lblAlanTongProgram = new JLabel("Alan Tong: Program 1 BST GUI");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAlanTongProgram)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFirstName, Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(searchBoolButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(PostOrderButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(inputSearch, 204, 204, 204)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(searchResultBox, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
										.addComponent(PostOrderTraversalBox, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(PreOrderButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(InOrderButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(ClearBst, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(printBST, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(Insert, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(Delete, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(insertInputButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(deleteStringText, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
										.addComponent(printLBL, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(insertStringText, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(inputFileText, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(inOrderTraversalBox, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(PreOrderTraversalBox, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))))
							.addGap(61))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFirstName)
						.addComponent(lblAlanTongProgram, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(insertStringText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(Insert, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Delete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(deleteStringText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(insertInputButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(inputFileText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(printLBL, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(printBST))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ClearBst, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(InOrderButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(inOrderTraversalBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(PreOrderButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(PreOrderTraversalBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(PostOrderButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(PostOrderTraversalBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchBoolButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(inputSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(searchResultBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	////////////////////////////////////
	// This method contains all of the codes for creating events
	////////////////////////////////////	
	private void createEvents() {
		Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isInteger(insertStringText.getText())) {
					System.out.println();
					JOptionPane.showMessageDialog(null, "Invalid Insert", "Error!", JOptionPane.PLAIN_MESSAGE);
					
				} else {
					int insertNumber = Integer.parseInt(insertStringText.getText());
					binaryTree.insert(insertNumber);
					insertStringText.setText("");
				}
			}
		});
		
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isInteger(deleteStringText.getText()) || ! binaryTree.search(Integer.parseInt(deleteStringText.getText()), binaryTree.root)) {
					System.out.println();
					JOptionPane.showMessageDialog(null, "Invalid Delete: NaN or Not In Tree", "Error!", JOptionPane.PLAIN_MESSAGE);
				} else {
					int deleteNumber = Integer.parseInt(deleteStringText.getText());
					binaryTree.delete(deleteNumber);
					deleteStringText.setText("");
				}
				
			}
		});
		
		printBST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printLBL.setText("");
				binaryTree.printLevelOrder(binaryTree.root);
				printLBL.append(binaryTree.BST_LBL);
				binaryTree.BST_LBL = "";
			}
		});
		
		InOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println();
				binaryTree.inOrder(binaryTree.root);
				inOrderTraversalBox.setText(binaryTree.inOrderString);
				binaryTree.inOrderString = "";
				
				
			}
		});
		
		PreOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println();
				binaryTree.preOrder(binaryTree.root);
				PreOrderTraversalBox.setText(binaryTree.PreOrderString);
				binaryTree.PreOrderString = "";
			}
		});
		
		PostOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println();
				binaryTree.postOrder(binaryTree.root);
				PostOrderTraversalBox.setText(binaryTree.PostOrderString);
				binaryTree.PostOrderString = "";
			}
		});
		
		insertInputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputFile = inputFileText.getText();
				
				File file = new File(inputFile);
				
				Scanner s;
				String[] array = null;
				try {
					s = new Scanner(file);
					while(s.hasNext()) {
						String str = s.next();
						array = str.split(",");
					}
					s.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null,  "Invalid File Type", "Error", JOptionPane.PLAIN_MESSAGE);
					inputFileText.setText("");
					return;
				}
				
				//Create BST object and insert
				int arrLength = array.length;
				
				for(int i = 0; i < arrLength;i++) {
					int key = Integer.parseInt(array[i]);
					binaryTree.insert(key);
				}
				inputFileText.setText("");
			}
		});	
		
		searchBoolButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isInteger(inputSearch.getText())) {
					JOptionPane.showMessageDialog(null,  "Invalid Search", "Error", JOptionPane.PLAIN_MESSAGE);
				} else {
					binaryTree.search(Integer.parseInt(inputSearch.getText()), binaryTree.root);
						searchResultBox.setText(binaryTree.searchResult);
					
				}
				inputSearch.setText("");
				binaryTree.searchResult = "";
			}
		});
		
		ClearBst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				binaryTree.clearBST();
			}
		});
		
	}
}
