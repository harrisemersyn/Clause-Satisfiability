package src;

import java.util.HashSet;

public class ModelChecking {
	
	public static void questionOne() {
		System.out.println("QUESTION ONE");
		System.out.println("P, P then Q, entails Q");
		Clause c = new Clause();
		//Three variables in the clause (includes P and not P as seperate variables
		c.setClauseVariables(2);
		//Initializes the variables array with the proper size
		c.variablesSetArraySize();
		c.intializeArray();
		//One or statement 
		c.setClauseNumber(1);
		//let 3 represent P, let 4 represent Q
		//Knowledge Base
		//Just P
		HashSet<Integer> P = new HashSet<Integer>();
		P.add(3);
		c.addToKB(P);
		//Original statement turns into not P or Q
		HashSet<Integer> notPorQ = new HashSet<Integer>();
		notPorQ.add(-3);
		notPorQ.add(4);
		c.addToKB(notPorQ);
		//Creating Alpha, which is just Q
		HashSet<Integer> Q = new HashSet<Integer>();
		Q.add(4);
		c.addClause(Q);
		//Takes all variables from clause and puts them into an array
		c.clauseVariablesIntoArray(c.getClauseSet());
		c.clauseVariablesIntoArray(c.getKB());
		//Returns TTEntails
		System.out.println("Running TTEntails...");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));	
	}
	
	public static void questionTwo() {
		Clause c = new Clause();
		//Let P be represented by 1, B by 2
		//Let the X and Y value be represented by their respective value
		//Ex: P(1,2) = 112
		//Putting the background knowledge into the knowledge base here
		//Stays the same in CNF form
		HashSet<Integer> R1 = new HashSet<Integer>();
		R1.add(-111);
		c.addToKB(R1);
		
		//In CNF form, becomes (notB(1,1) v P(1,2) v P(2,1)) ^ (notP(1,2) v B(1,1)) ^ (notP(2,1) v B(1,1))
		//So total of three clauses added to the knowledge base
		HashSet<Integer> R21 = new HashSet<Integer>();
		HashSet<Integer> R22 = new HashSet<Integer>();
		HashSet<Integer> R23 = new HashSet<Integer>();
		R21.add(-211);
		R21.add(112);
		R21.add(121);
		c.addToKB(R21);
		R22.add(-112);
		R22.add(211);
		c.addToKB(R22);
		R23.add(-121);
		R23.add(211);
		c.addToKB(R23);
		
		//In CNF form, becomes (notB(2,1) v P(1,1) v P(2,2) v P(3,1)) ^ (notP(1,1) v B(2,1)) ^ (notP(2,2) v B(2,1) ^ (notP(3,1) v B(2,1))
		//So a total of four clauses add to the knowledge base
		HashSet<Integer> R31 = new HashSet<Integer>();
		HashSet<Integer> R32 = new HashSet<Integer>();
		HashSet<Integer> R33 = new HashSet<Integer>();
		HashSet<Integer> R34 = new HashSet<Integer>();
		R31.add(-221);
		R31.add(111);
		R31.add(122);
		R31.add(131);
		c.addToKB(R31);
		R32.add(-111);
		R32.add(221);
		c.addToKB(R32);
		R33.add(-122);
		R33.add(221);
		c.addToKB(R33);
		R34.add(-131);
		R34.add(221);
		c.addToKB(R34);
		
		//In CNF form, becomes (notB(1,2) v P(1,1) v P(2,2) v P(1,3)) ^ (notP(1,1) v B(1,2)) ^ (notP(2,2) v B(1,3) ^ (notP(1,3) v B(1,2))
		//So a total of four clauses add to the knowledge base
		HashSet<Integer> R71 = new HashSet<Integer>();
		HashSet<Integer> R72 = new HashSet<Integer>();
		HashSet<Integer> R73 = new HashSet<Integer>();
		HashSet<Integer> R74 = new HashSet<Integer>();
		R71.add(-212);
		R71.add(111);
		R71.add(122);
		R71.add(113);
		c.addToKB(R71);
		R72.add(-111);
		R72.add(212);
		c.addToKB(R72);
		R73.add(-122);
		R73.add(212);
		c.addToKB(R73);
		R74.add(-113);
		R74.add(212);
		c.addToKB(R74);	
		
		//Adding perception R4 to the knowledge base
		HashSet<Integer> R4 = new HashSet<Integer>();
		R4.add(-211);
		c.addToKB(R4);
		
		//Entailment tests
		////Takes all variables from clause and puts them into an array
		c.setClauseVariables(9);
		c.setClauseNumber(1);
		c.variablesSetArraySize();
		c.intializeArray();
		//c.clauseVariablesIntoArray(c.getClauseSet());
		c.clauseVariablesIntoArray(c.getKB());
		
		//Using the format a if and only if b
		HashSet<Integer> ET1 = new HashSet<Integer>();
		ET1.add(-112);
		c.addClause(ET1);
		System.out.println("QUESTION TWO");
		System.out.println("Knowledge Base with R1, R2, R3, R7, and R4");
		System.out.println("Testing TTEntails on not P(1,2)");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		
		c.getClauseSet().clear();
		
		HashSet<Integer> ET2 = new HashSet<Integer>();
		ET2.add(-121);
		c.addClause(ET2);
		System.out.println("Testing TTEntails on not P(2,1)");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		
		c.getClauseSet().clear();
		
		HashSet<Integer> ET3 = new HashSet<Integer>();
		ET3.add(122);
		c.addClause(ET3);
		System.out.println("Testing TTEntails on P(2,2)");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		
		c.getClauseSet().clear();
		
		HashSet<Integer> ET4 = new HashSet<Integer>();
		ET4.add(-122);
		c.addClause(ET4);
		System.out.println("Testing TTEntails on not P(2,2)");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		
		c.getClauseSet().clear();
		
		//Adding R5 to knowledge base
		HashSet<Integer> R5 = new HashSet<Integer>();
		R5.add(221);
		c.addToKB(R5);
		
		HashSet<Integer> ET5 = new HashSet<Integer>();
		ET5.add(122);
		ET5.add(131);
		c.addClause(ET5);
		System.out.println("Added R5 to the Knowledge Base");
		System.out.println("Testing TTEntails on P(2,2)vP(3,1)");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		
		c.getClauseSet().clear();
		
		HashSet<Integer> ET6 = new HashSet<Integer>();
		System.out.println("Testing TTEntails on P(2,2)");
		ET6.add(122);
		c.addClause(ET6);
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		System.out.println("Testing TTEntails on not P(2,2)");
		c.getClauseSet().clear();
		ET6.clear();
		ET6.add(-122);
		c.addClause(ET6);
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		System.out.println("Testing TTEntails on P(3,1)");
		c.getClauseSet().clear();
		ET6.clear();
		ET6.add(131);
		c.addClause(ET6);
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		System.out.println("Testing TTEntails on not P(3,1)");
		c.getClauseSet().clear();
		ET6.clear();
		ET6.add(-131);
		c.addClause(ET6);
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		c.getClauseSet().clear();
		ET6.clear();
		
		
		//Adding R6 to the knowledge base
		HashSet<Integer> R6 = new HashSet<Integer>();
		R6.add(-212);
		c.addToKB(R6);
		
		HashSet<Integer> ET7 = new HashSet<Integer>();
		System.out.println("Added R6 to the Knowledge Base");
		ET7.add(-122);
		c.addClause(ET7);
		System.out.println("Testing TTEntails on not P(2,2)");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		c.getClauseSet().clear();
		ET7.clear();
		ET7.add(131);
		c.addClause(ET7);
		System.out.println("Testing TTEntails on P(3,1)");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
	}
	
	public static void questionThree() {
		System.out.println("QUESTION THREE");
		Clause c = new Clause();
		c.setClauseVariables(5);
		//Initializes the variables array with the proper size
		c.variablesSetArraySize();
		c.intializeArray();
		/*1 = Mythical
		 *2 = Immortal
		 *3 = Mortal Mammal
		 *4 = Magical
		 *5 = Horned
		 */
		HashSet<Integer> K1 = new HashSet<Integer>();
		K1.add(-1);
		K1.add(2);
		c.addToKB(K1);
		
		HashSet<Integer> K2 = new HashSet<Integer>();
		K2.add(1);
		K2.add(3);
		c.addToKB(K2);
		
		HashSet<Integer> K3 = new HashSet<Integer>();
		K3.add(-2);
		K3.add(5);
		c.addToKB(K3);
		
		HashSet<Integer> K4 = new HashSet<Integer>();
		K4.add(-3);
		K4.add(5);
		c.addToKB(K4);
		
		HashSet<Integer> K5 = new HashSet<Integer>();
		K5.add(4);
		K5.add(-5);
		c.addToKB(K5);
		
		c.clauseVariablesIntoArray(c.getKB());
		
		HashSet<Integer> A = new HashSet<Integer>();
		A.add(1);
		c.addClause(A);
		System.out.println("Testing TTEntails on 'can we prove that the unicorn is mythical?'");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		
		c.getClauseSet().clear();
		
		HashSet<Integer> B= new HashSet<Integer>();
		B.add(4);
		c.addClause(B);
		System.out.println("Testing TTEntails on 'can we prove that the unicorn is magical?'");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
		
		c.getClauseSet().clear();
		
		HashSet<Integer> C= new HashSet<Integer>();
		C.add(5);
		c.addClause(C);
		System.out.println("Testing TTEntails on 'can we prove that the unicorn is horned?'");
		System.out.println(c.TTEntails(c.getKB(), c.getClauseSet()));
	}

}
