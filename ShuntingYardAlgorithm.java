package RadixSort;

import java.util.Stack;

/**********************************************************************************************************
   
   Shunting-yard algorithm is among the most famous algorithms for converting expressions from fixed to post-fixed 
   and it belongs to one of the pioneers of Computer Science: Edsger W. Dijkstra.

	The algorithm uses a stack to correctly order the entities in the conversion.
	The effective steps described in detail, are:
   
    -As long as there is a reading entity:
    	read the entity (i.e. operand or operator)
    	-If the entity is an operand (i.e. a number), then:
        	add entity to the postfixed form
    	-If the entity is an operator (be it O1), then:
        	while (there is an operator at the top of the stack (be it O2)) and
                      (O2 is different from the left parenthesis) and
                      (previous (O1) <previous (O2) OR
                       previous (O1) = previous (O2) and O2 has left-associativity)
            Extract O2 and add it to the fixed form
        -Add O1 to the stack
    -If the entity is a left-bracket, then:
        add parentheses to the stack
    -If the entity is a right-bracket, then:
        as long as the operator at the top of the stack (be it O) is not a left parenthesis:
            extract O and add it to the postfixed form
        If the stack has been emptied (and no left parenthesis was found)
            return error (i.e. the expression had the wrong parentheses)
        Extract the left bracket from the top of the stack
 
	// there are no more reading entities at this time
 
	As long as there is an operator in the stack (be it O)
    	extract O and add it to the postfixed form
    	if O is a left-bracket, then:
        	return error (i.e. the expression had the wrong parentheses)
 
	Displays / returns the postfixed form.
	
	*******************************************************************************************************/

public class ShuntingYardAlgorithm extends Algorithm{

	private static boolean hasLeftAssociativity(char c) {
		return c != '^';
	}

	private static int getPrecedence(char ch) {

		if (ch == '+' || ch == '-') 
			return 11;
		else if (ch == '*' || ch == '/') 
			return 12;
		else if (ch == '^') 
			return 13;
		else
			return -1; 
	}

	 private static String infixToPostFix2(String expression) {
		Stack<Character> stack = new Stack<>();
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < expression.length(); ++i) {
			char c = expression.charAt(i);

			if (Character.isLetterOrDigit(c)){
				output.append(c);
			} else if (isOperator(c)) {
				while (!stack.isEmpty() && stack.peek() != '(' && (getPrecedence(c) <= getPrecedence(stack.peek()) && hasLeftAssociativity(c))) {
					output.append(stack.pop());
				}
				stack.push(c);
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					output.append(stack.pop());
				}
				if (stack.isEmpty()) {
					return "Expresia este invalida.";
				}
				stack.pop();
			}
		}

		while (!stack.isEmpty()) {
			if (stack.peek() == '(') {
				return "Expresia este invalida.";
			}
			output.append(stack.pop());
		}

		return output.toString();
	}
	 
	 public static void main(String[] args) {

			String expression = "3+(2+1)*2^3^2-8/(5-1*2/2)";
			System.out.println("Expresia input: " + expression);
			System.out.println();
			System.out.println("Expresia dupa ShantingYard: " + infixToPostFix2(expression));

	}

}
