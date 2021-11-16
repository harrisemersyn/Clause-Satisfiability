package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//PART ONE

		System.out.println("PART ONE: CNF TO CLAUSES");
		System.out.println("aim-50-1_6-yes1-4.cnf into a set of clauses:");
		File file = new File("aim-50-1_6-yes1-4.cnf");
		Clause exampleClause = new Clause();
		exampleClause.addClausesFromFile(file);
		exampleClause.printClauses();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Type anything to continue:");
		String a = s.next();
		
		//PART TWO
		System.out.println("PART TWO: MODEL CHECKING");
		ModelChecking.questionOne();
		ModelChecking.questionTwo();
		ModelChecking.questionThree();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Type anything to continue:");
		a = sc.next();
		
		//PART THREE
		System.out.println("PART THREE: GSAT");
		GSATTests.questionOne();
		GSATTests.questionTwo();
		GSATTests.questionThree();
    }
}

