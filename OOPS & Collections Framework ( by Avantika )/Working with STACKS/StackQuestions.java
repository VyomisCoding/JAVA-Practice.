import java.util.*;

public class StackQuestions{
    public static void main(String[] args){
    
        // 41. Browser Back/Forward Navigation using 2 Stacks **********************************************************************

        Stack<String> back = new Stack<>();
        Stack<String> forward = new Stack<>();

        back.push("Page1");
        back.push("Page2");
        back.push("Page3"); // current

        // User presses BACK
        forward.push(back.pop());  // move current to forward

        System.out.println("\nQ41 Browser Back Stack: " + back);
        System.out.println("Forward Stack: " + forward);


    
        // 42. Balanced Parentheses using Stack **********************************************************************

        String expr = "{[()]}";

        Stack<Character> st = new Stack<>();
        boolean balanced = true;

        for (char c : expr.toCharArray()) {

            // Opening brackets push
            if (c == '{' || c == '[' || c == '(') {
                st.push(c);
            }
            // Closing brackets
            else {
                if (st.isEmpty()) {
                    balanced = false; break;
                }
                char top = st.pop();

                if ((c == '}' && top != '{') ||
                    (c == ']' && top != '[') ||
                    (c == ')' && top != '(')) {
                    balanced = false;
                    break;
                }
            }
        }

        if (!st.isEmpty()) balanced = false;

        System.out.println("\nQ42 Balanced = " + balanced);


    
        // 43. Reverse String using Stack **********************************************************************

        String s = "Vyomesh";
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) stack.push(c);

        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) reversed.append(stack.pop());

        System.out.println("\nQ43 Reversed String = " + reversed);


    
        // 44. Infix → Postfix Conversion (Stack) **********************************************************************

        String infix = "A+B*C";

        Stack<Character> op = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        Map<Character, Integer> prec = new HashMap<>();
        prec.put('+', 1);
        prec.put('-', 1);
        prec.put('*', 2);
        prec.put('/', 2);

        for (char c : infix.toCharArray()) {

            // If operand → directly add
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            }
            // Operator
            else {
                // pop while operator on stack has greater priority
                while (!op.isEmpty() && prec.getOrDefault(op.peek(), 0) >= prec.get(c))
                    postfix.append(op.pop());
                op.push(c);
            }
        }

        // pop remaining operators
        while (!op.isEmpty()) postfix.append(op.pop());

        System.out.println("\nQ44 Postfix = " + postfix);


    
        // 45. Stack getMin() in O(1) using Extra Stack **********************************************************************

        class MinStack {
            Stack<Integer> main = new Stack<>();
            Stack<Integer> min = new Stack<>();

            void push(int x) {
                main.push(x);
                if (min.isEmpty() || x <= min.peek()) min.push(x);
            }

            int pop() {
                int val = main.pop();
                if (val == min.peek()) min.pop();
                return val;
            }

            int getMin() { return min.peek(); }
        }

        MinStack ms = new MinStack();
        ms.push(5); ms.push(3); ms.push(7); ms.push(2);

        System.out.println("\nQ45 Min Element = " + ms.getMin());


    
        // 46. Implement stack supporting getMin() in O(1) (Already done above, same logic)
        // **********************************************************************

        System.out.println("Q46 getMin() again = " + ms.getMin());


    
        // 47. Remove specific book from stack without breaking order **********************************************************************

        Stack<String> books = new Stack<>();
        books.push("Book1");
        books.push("Book2");
        books.push("Book3");
        books.push("Book4");

        String target = "Book2";
        Stack<String> temp = new Stack<>();

        // pop until found
        while (!books.isEmpty() && !books.peek().equals(target))
            temp.push(books.pop());

        // remove Book2
        if (!books.isEmpty()) books.pop();

        // restore back
        while (!temp.isEmpty()) books.push(temp.pop());

        System.out.println("\nQ47 After Removing Book2 = " + books);


    
        // 48. Sort a Stack (Only push/pop) **********************************************************************

        Stack<Integer> unsorted = new Stack<>();
        unsorted.push(30);
        unsorted.push(10);
        unsorted.push(50);
        unsorted.push(20);

        Stack<Integer> sorted = new Stack<>();

        // Sort using another stack
        while (!unsorted.isEmpty()) {
            int tempVal = unsorted.pop();

            // move elements back to unsorted to find right place
            while (!sorted.isEmpty() && sorted.peek() > tempVal)
                unsorted.push(sorted.pop());

            sorted.push(tempVal);
        }

        System.out.println("\nQ48 Sorted Stack = " + sorted);


    
        // 49. Next Greater Element for each element in a stack **********************************************************************

        Stack<Integer> ngeStack = new Stack<>();
        ngeStack.push(4);
        ngeStack.push(5);
        ngeStack.push(2);
        ngeStack.push(25);

        List<Integer> list = new ArrayList<>(ngeStack);

        Map<Integer, Integer> nge = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            int nextGreater = -1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) > list.get(i)) {
                    nextGreater = list.get(j);
                    break;
                }
            }
            nge.put(list.get(i), nextGreater);
        }

        System.out.println("\nQ49 Next Greater Elements = " + nge);


    
        // 50. Undo operation using a Stack **********************************************************************

        Stack<String> actions = new Stack<>();

        actions.push("Typed A");
        actions.push("Typed B");
        actions.push("Typed C");

        String last = actions.pop(); // undo last

        System.out.println("\nQ50 Actions After Undo: " + actions);
        System.out.println("Undo Performed (Removed): " + last);
    }
}