package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class GSATTests {
	public static void questionOne() {
		System.out.println("GSAT QUESTION ONE");
		System.out.println("Suggested input: 5 5");
		Clause c = new Clause();
		c.setClauseVariables(4);
		c.variablesSetArraySize();
		c.intializeArray();
		
		HashSet<Integer> one = new HashSet<Integer>();
		one.add(1);
		one.add(3);
		one.add(-4);
		c.addClause(one);
		
		HashSet<Integer> two = new HashSet<Integer>();
		two.add(4);;
		c.addClause(two);
		
		HashSet<Integer> three = new HashSet<Integer>();
		three.add(2);
		three.add(-3);
		c.addClause(three);
		
		c.clauseVariablesIntoArray(c.getClauseSet());
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the max number of tries: ");
		int tries = s.nextInt();
		
		System.out.println("Enter the max number of flips: ");
		int flips = s.nextInt();
		
		
		int[] i = c.GSAT(c.getClauseSet(), tries, flips);
		c.variableLine();
		c.variableAssignmentLine(i);
	}
	
	public static void questionTwo() throws FileNotFoundException {
		System.out.println();
		System.out.println("QUESTION TWO");
		File four = new File("nqueens_4.cnf");
		System.out.println("Suggested input: 20 20");
		Clause fourClause = new Clause();
		fourClause.addClausesFromFile(four);
		fourClause.clauseVariablesIntoArray(fourClause.getClauseSet());
		
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the max number of tries: ");
		int tries = s.nextInt();
		
		System.out.println("Enter the max number of flips: ");
		int flips = s.nextInt();	
		
		System.out.println("Four Queens");
		int[] f = fourClause.GSAT(fourClause.getClauseSet(), tries, flips);
		fourClause.variableLine();
		fourClause.variableAssignmentLine(f);
	
	}
	
	public static void questionThree() throws FileNotFoundException {
		System.out.println();
		System.out.println("QUESTION 3: 2 FROM REPOSITORY");
		File one = new File("quinn.cnf");
		Clause oneClause = new Clause();
		oneClause.addClausesFromFile(one);
		oneClause.clauseVariablesIntoArray(oneClause.getClauseSet());
		
		File two = new File("aim-50-1_6-yes1-4.cnf");
		Clause twoClause = new Clause();
		twoClause.addClausesFromFile(two);
		twoClause.setClauseNumber(79);
		twoClause.clauseVariablesIntoArray(twoClause.getClauseSet());
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("QUINN");
		System.out.println("Suggested input: 50 50");
		System.out.println("Enter the max number of tries: ");
		int tries = s.nextInt();
		
		System.out.println("Enter the max number of flips: ");
		int flips = s.nextInt();
		
		int[] f = oneClause.GSAT(oneClause.getClauseSet(), tries, flips);
		oneClause.variableLine();
		oneClause.variableAssignmentLine(f);
		System.out.println();
		
		System.out.println("aim-50-1_6-yes1-4");
		System.out.println("Suggested input: 80 80");
		System.out.println("Enter the max number of tries: ");
		tries = s.nextInt();
		
		System.out.println("Enter the max number of flips: ");
		flips = s.nextInt();
		
		System.out.println();
		int[] t = twoClause.GSAT(twoClause.getClauseSet(), tries, flips);
		twoClause.variableLine();
		twoClause.variableAssignmentLine(t);
	}

}
