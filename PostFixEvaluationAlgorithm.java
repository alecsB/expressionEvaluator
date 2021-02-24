package RadixSort;

import java.util.Stack;


/**************************************************************************************
 
 The algorithm uses a stack to perform operations in the correct order:
 Pseudocode: 
 
    As long as there is a reading entity:
  		> read the entity (i.e. operand or operator)
    If the entity is an operand (i.e. a number), then:
        > add the entity to the stack
    If the entity is an operator then:
        > extract an operand from the stack(can be op1)
        > extract an operand from the stack (can be op2)
    If the 2 operands do not exist (i.e. op1 and op2), then:
        > return error (i.e. postfixed expression is wrong)
    Add (op2 O op1) to result:
        > add result to stack
 
	Extract an operator from the stack and add to result
 
	If the stack is not empty, then:
  		> return error (i.e. postfixed expression is wrong)
 
	Displays / returns result.
	
	**********************************************************************************/

import static RadixSort.Algorithm.isOperator;

public class PostFixEvaluationAlgorithm {

	private static String evaluateExpression(String postfixExpression) {
		Stack<Integer> stack = new Stack<>();
		String output;

		for (int i = 0; i < postfixExpression.length(); ++i) {  // iterates through string
			char c = postfixExpression.charAt(i);

			if (Character.isDigit(c)){		// condition that verifies if entity is number
				stack.push(Character.getNumericValue(c));  // (if true) number is added
			} else if (isOperator(c)) {   // (if false) -> entity if operator
				Integer op1 = stack.pop();   // extract operand 
				Integer op2 = stack.pop();
				if (op1 == null || op2 == null) {   // verifies if o operand exists
					return "Error";
				}

				Integer result = applyOperator(c, op1, op2);
				if (result != null) {   // condition for adding result to stack
					stack.push(result);
				}
			}
		}

		output = String.valueOf(stack.pop());

		return output;
	}

	private static Integer applyOperator(char operator, Integer op1, Integer op2) {

		Integer result = null;
		if (operator == '+') {
			result = op2 + op1;
		} else if (operator == '-') {
			result = op2 - op1;
		} else if (operator == '*') {
			result = op2 * op1;
		} else if (operator == '/') {
			result = op2 / op1;
		} else if (operator == '^') {
			result = (int) Math.pow(op2, op1);
		}
		return result;
	}
	
	public static void main(String[] args) {

		String expression = "3 2 1 + 2 3 2 ^ ^ * + 8 5 1 2 * 2 / - / -";
		System.out.println("Expresia input: " + expression);
		System.out.println();
		System.out.println("Expresia dupa PostfixEvaluationAlgorithm: " + evaluateExpression(expression));

	}

}
