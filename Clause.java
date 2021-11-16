package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Random;



public class Clause {
	
	private List<HashSet<Integer>> clauseSet = new ArrayList<HashSet<Integer>>();
	private Integer[] variables;
	private List<HashSet<Integer>> kb = new ArrayList<HashSet<Integer>>();
	private Integer clauseVariables;
	//set at -1 so it doesn't effect other functions if the proposition is not created from a CNF
	private Integer clauseNumber = -1;
	//For GSAT
	private int maxClausesSatisfied = 0;
	private int clausesSatisfied = 0;
	private int maxIndex;
	private Boolean solutionFound = false;
	
	public Integer getClauseVariables() {
		return this.clauseVariables;
	}
	
	public void setClauseVariables(Integer clauseVariables) {
		this.clauseVariables = clauseVariables;
	}

	public Integer getClauseNumber() {
		return this.clauseNumber;
	}
	
	public void setClauseNumber(Integer clauseNumber) {
		this.clauseNumber = clauseNumber;
	}
	
	public List<HashSet<Integer>> getClauseSet(){
		return this.clauseSet;
	}
	
	public List<HashSet<Integer>> getKB(){
		return this.kb;
	}
	
	public void variablesSetArraySize() {
		variables = new Integer[clauseVariables];
	}
	
	//Sets all vals in array to 0
	public void intializeArray() {
		for(int i = 0; i < clauseVariables; i++) {
			variables[i] = 0;
		}
	}
	
	public void printClauses() {
		for(HashSet<Integer> i : clauseSet) {
			for(Integer t : i) {
				System.out.print(t);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	//puts all variables from the clause into an array
	public void clauseVariablesIntoArray(List<HashSet<Integer>> c) {
		//Goes through each clause
		for(HashSet<Integer> i : c) {
			//Goes through every integer in the clause
			for(Integer a : i) {
				//Goes through every integer in variables
				boolean contains = contains(a, variables);
				if (contains == false) {
					for (int l = 0; l < clauseVariables; l++) {
						if ((int)variables[l] == 0) {
							variables[l] = Math.abs(a);
							break;
						}
					}
				}
			}
		}
	}
	
	/*
	public List<Integer> listVariables(List<HashSet<Integer>> c) {
		List<Integer> newList = new ArrayList<Integer>();
		for(HashSet<Integer> hs : c) {
			for(Integer i : hs) {
				if(newList.contains(i) == false) {
					newList.add(i);
				}
			}
		}
		return newList;
	}
	*/
	
	//Checks to see if the variables array already contains a value
	public Boolean contains(Integer a, Integer[] array) {
		for(int k = 0; k < clauseVariables; k++) {
			if((int)array[k] == Math.abs((int)a)) {
				return true;
			}				
		}
		return false;
	}
	
	//for debugging
	public void printVariableArray() {
		for(int i = 0; i < clauseVariables; i++) {
			System.out.println(variables[i]);
		}
	}
	
	public void printArray(Integer[] arr) {
		for(int i = 0; i < clauseVariables; i++) {
			System.out.println(arr[i]);
		}
	}
	
	public void variableLine() {
		System.out.println("Order of variables: ");
		for(Integer i : variables) {
			System.out.print(i + " ");
		}
	}
	
	public void variableAssignmentLine(int[] arr) {
		System.out.println();
		System.out.println("Assignment of Variables:");
		for(int i : arr) {
			System.out.print(i + " ");
		}
	}
	
	public void addClausesFromFile(File a) throws FileNotFoundException {
		int currentHashSet = 0;
		Scanner reader = new Scanner(a);
		HashSet<Integer> firstHashSet = new HashSet<Integer>();
		clauseSet.add(firstHashSet);
		
		while(reader.hasNextLine() == true) {
			String currentLine = reader.nextLine();
			try {
			if(currentLine.charAt(0) == 'c') {
				//does nothing
			}
			
			else if(currentLine.charAt(0) == 'p') {
				int i = 0;
				while(currentLine.charAt(i) != '1' && currentLine.charAt(i) != '2' && currentLine.charAt(i) != '3' && currentLine.charAt(i) != '4' && currentLine.charAt(i) != '5' && currentLine.charAt(i) != '6' && currentLine.charAt(i) != '7' && currentLine.charAt(i) != '8' && currentLine.charAt(i) != '9' && currentLine.charAt(i) != '0') {
					i++;
				}
				String numbers = String.valueOf(currentLine.charAt(i));
				i++;
				while(currentLine.charAt(i) != ' ') {
					numbers = numbers + currentLine.charAt(i);
					i++;
				}
				int newNum = Integer.parseInt(numbers);
				setClauseVariables(newNum);
				i++;
				numbers = String.valueOf(currentLine.charAt(i));
				i++;
				while(i < currentLine.length() && currentLine.charAt(i) != ' ') {
					numbers = numbers + currentLine.charAt(i);
					i++;
				}
				newNum = Integer.parseInt(numbers);
				setClauseNumber(newNum);
				variables = new Integer[clauseVariables];
				//Sets all variables to unassigned intially
				for(int k = 0; k < clauseVariables; k++) {
					variables[k] = 0;
				}
			}
			else {
				for(int i = 0; i < currentLine.length(); i++){
					if(currentLine.charAt(i) == '0') {
						HashSet<Integer> nextHashSet = new HashSet<Integer>();
						clauseSet.add(nextHashSet);
						currentHashSet = currentHashSet + 1;
						break;
					}
					else if(currentLine.charAt(i) == ' ') {
						//does nothing if there is a space
					}
					else if(currentLine.charAt(i) == '-') {
						String numbers = String.valueOf(currentLine.charAt(i));
						i++;
						while(currentLine.charAt(i) != ' ') {
							
							numbers = numbers + currentLine.charAt(i);
							i++;
						
						}
						int newNum = Integer.parseInt(numbers);
						if(newNum < 0) {
							(clauseSet.get(currentHashSet)).add((Integer)newNum);
						}
						else {
							(clauseSet.get(currentHashSet)).add(-1 * (Integer)newNum);
						}
					}
					else {
						String numbers = String.valueOf(currentLine.charAt(i));
						i++;
						while(currentLine.charAt(i) != ' ') {
							numbers = numbers + currentLine.charAt(i);
							i++;
						}
						int newNum = Integer.parseInt(numbers);
						(clauseSet.get(currentHashSet)).add((Integer)newNum);
					}
				}
			}
			}
			catch(IndexOutOfBoundsException ex){
				break;
			}
			finally {
				
			}
		}
		
		
	}
	
	//Section 2 of the project
	
	/*
	//Testing all possible assignments of a proposition
	public void testAllAssignments() {
		int[] rootValues = new int[clauseVariables];
		for(int i = 0; i < clauseVariables; i++) {
			rootValues[i] = 0;
		}
		Node<int[]> root = new Node<>(rootValues);
		int count = 0;
		settingAssignments(root, count);	
	}
	
	public void settingAssignments(Node<int[]> root, int count) {
		if(count < clauseVariables) {
			int[] leftValues = new int[clauseVariables];
			int[] rightValues = new int[clauseVariables];
			for(int j = 0; j < clauseVariables; j++) {
				leftValues[j] = root.getData()[j];
				rightValues[j] = root.getData()[j];
			}
			leftValues[count] = -1;
			rightValues[count] = 1;
			Node<int[]> left = new Node<>(leftValues);
			Node<int[]> right = new Node<>(rightValues);
			left.setParent(root);
			right.setParent(root);
			root.setLeft(left);
			root.setRight(right);
			left.setTerminal(false);
			right.setTerminal(false);
			count =+ 1;
			settingAssignments(root.getLeft(), count);
			settingAssignments(root.getRight(), count);
		}
		else if(count == clauseVariables) {
			root.setTerminal(true);
		}
	}
	*/
	
	//Testing to see if a clause is true
	public Boolean clauseTorF(HashSet<Integer> set, int[] truthSet) {
		List<Integer> placeholder = new ArrayList<>();
		for(Integer item : set) {
			placeholder.add(item);
		}
		//If the set only contains one variable
		if(set.size() == 1) {
			int index = getIndex(placeholder.get(0));
			if((truthSet[index] == 1 && (int)placeholder.get(0) > 0) || (truthSet[index] == -1 && (int)placeholder.get(0) < 0)) {
				clausesSatisfied = clausesSatisfied + 1;
				return true;
			}
			else {
				return false;
			}
		}
		for(int i = 1; i < set.size(); i++) {
			if(testTorF(placeholder.get(i), placeholder.get(i - 1), truthSet) == true){
				clausesSatisfied = clausesSatisfied + 1;
				return true;
			}
		}
		//Returns false if none of the variables are true
		return false;
	}
	
	public Boolean testTorF(Integer a, Integer b, int[] truthSet) {
		int indexA = getIndex(a);
		int indexB = getIndex(b);
		if((truthSet[indexA] == 1 && a > 0) || (truthSet[indexB] == 1 && b > 0) || (truthSet[indexA] == -1 && a < 0) || (truthSet[indexB] == -1 && b < 0)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getIndex(int a) {
		for(int i = 0; i < clauseVariables; i++) {
			if(variables[i] == Math.abs(a)) {
				return i;
			}
		}
		//Only returns if integer doesn't exist in the creation of the set of clauses
		return -1000;
	}
	
	//Returns a set of all possible worlds from the tree created in testAllAssignments
	/*
	public void allPossibleWorlds(Node<int[]> root){
		if(root.getTerminal() == false) {
			allPossibleWorlds(root.getLeft());
			allPossibleWorlds(root.getRight());
		}
		else {
			allPossibleWorlds.add(root.getData());
		}
	}
	*/
	
	//Will return a list of everything from the possible worlds that fit the proposition
	/*
	public void addToModels(HashSet<Integer> set, Boolean bool, List<int[]> allPossibleWorlds){
		for(int[] a : allPossibleWorlds) {
			if(clauseTorF(set, a) == true && bool == true) {
				models.add(a);
			}
			else if(clauseTorF(set, a) == false && bool == false) {
				models.add(a);
			}
		}
	}
	*/
	
	//kb - knowledge base, what we already know
	//alpha - the sentence, the proposition that needs checking
	//symbols - all the symbols in a proposition
	//models - all allows solutions
	public Boolean TTEntails(List<HashSet<Integer>> kb, List<HashSet<Integer>> alpha) {
		int[] placeholder = new int[clauseVariables];
		//0 = unassigned
		for(int i = 0; i < clauseVariables; i++) {
			placeholder[i] = 0;
		}
		return TTCheckAll(kb, alpha, arrayToList(variables), placeholder);
	}
	
	public Boolean TTCheckAll(List<HashSet<Integer>> kb, List<HashSet<Integer>> alpha, List<Integer> symbols, int[] model) {
		if(symbols.isEmpty() == true) {
			if(PLTrue(kb, model) == true) {
				return PLTrue(alpha, model);
			}
			else {
				return true;
			}
		}
		else {
			Integer ph = first(symbols);
			int p = (int)ph;
			int[] copyOne = new int[clauseVariables];
			int[] copyTwo = new int[clauseVariables];
			for(int j = 0; j < clauseVariables; j++) {
				copyOne[j] = model[j];
				copyTwo[j] = model[j];
			}
			copyOne[getIndex(p)] = 1;
			copyTwo[getIndex(p)] = -1;
			List<Integer> restOne = new ArrayList<>();
			List<Integer> restTwo = new ArrayList<>();
			for(Integer i : symbols) {
				restOne.add(i);
				restTwo.add(i);
			}
			return(TTCheckAll(kb, alpha, restOne, copyOne) && TTCheckAll(kb, alpha, restTwo, copyTwo));
		}
	}
	
	//Union of the model and P from figure 7.10
	//Let 2 represent an assignment being both true and false
	
	/*
	public List<int[]> union(List<int[]> model, Integer symbol, Boolean bool){
		int index = getIndex(symbol);
		for(int[] m : model) {
			if((m[index] == 1 && bool == true) || m[index] == 0 && bool == false) {
				//does nothing
			}
			else if(m[index] == 0) {
				if(bool == true) {
					m[index] = 1;
				}
				else if(bool == false) {
					m[index] = -1;
				}
			}
			else {
				m[index] = 2;
			}
		}
		return model;
	}
	*/
	
	//Takes the first symbol from the list and then deletes it from the list
	public Integer first(List<Integer> list) {
		Integer i = list.get(0);
		list.remove(0);
		return i;
	}
	
	//Changes an array to a list to use in TTCheckAll
	public List<Integer> arrayToList(Integer[] array){
		List<Integer> list = new ArrayList<>();
		for(Integer a : array) {
			list.add(a);
		}
		return list;
	}
	
	//Will add a clause to the knowledge base
	public void addToKB(HashSet<Integer> c) {
		kb.add(c);
	}
	
	//Adds a clause to the set
	public void addClause(HashSet<Integer> newClause) {
		clauseSet.add(newClause);
	}
	
	public Boolean PLTrue(List<HashSet<Integer>> alpha, int[] model) {
		for(HashSet<Integer> c : alpha) {
				if(clauseTorF(c, model) == false) {
					return false;
				}
			}
		return true;
	}

	public int[] GSAT(List<HashSet<Integer>> alpha, int maxFlips, int maxTries) {
		int[] model = new int[clauseVariables];
		for (int i = 1; i <= maxTries; i++) {
			for (int l = 0; l < clauseVariables; l++) {
				model[l] = 0;
			}
			for (int j = 0; j < clauseVariables; j++) {
				double k = Math.random();
				if (k <= .5) {
					model[j] = -1;
				} else {
					model[j] = 1;
				}
			}
			if(PLTrue(alpha, model) == true) {
				return model;
			}
			for(int g = 1; g <= maxFlips; g++) {
				int[] flippedModel = flip(alpha, model);
				if(solutionFound == true) {
					return flippedModel;
				}
				if(PLTrue(alpha, flippedModel) == true) {
					return flippedModel;
				}
				for(int flipping = 0; flipping < clauseVariables; flipping++) {
					model[flipping] = flippedModel[flipping];
				}
			}
		}
		System.out.println("Not Found");
		for (int i = 0; i < clauseVariables; i++) {
			model[i] = 0;
		}
		return model;
	}
	
	public int[] flip(List<HashSet<Integer>> alpha, int[] model) {
		clausesSatisfied = 0;
		if(PLTrue(alpha, model) == true) {
			return model;
		}
		int modelClausesSatisfied = clausesSatisfied;
		maxClausesSatisfied = 0;
		maxIndex = -1;
		for (int a = 0; a < clauseVariables; a++) {
			clausesSatisfied = 0;
			int[] flipArray = new int[clauseVariables];
			for (int b = 0; b < clauseVariables; b++) {
				flipArray[b] = model[b];
			}
			if (flipArray[a] > 0) {
				flipArray[a] = -1;
			} else {
				flipArray[a] = 1;
			}
			for (HashSet<Integer> set : alpha) {
				clauseTorF(set, flipArray);
			}
			if (clausesSatisfied > maxClausesSatisfied && clausesSatisfied > modelClausesSatisfied) {
				maxClausesSatisfied = clausesSatisfied;
				maxIndex = a;
			}
		}

		if (maxIndex == -1) {
			return model;
		} else {
			if (model[maxIndex] > 0) {
				model[maxIndex] = -1;
			} else {
				model[maxIndex] = 1;
			}
			if(maxClausesSatisfied == clauseNumber) {
				solutionFound = true;
				return model;
			}
			return model;
		}
	}

}
