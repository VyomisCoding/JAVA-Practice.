import java.util.*;
import java.util.stream.*;

public class QueueQuestions{
    public static void main(String[] args){

        
        // 31. Task Scheduler using PriorityQueue (Higher priority executes first) **********************************************************************
        class Task {
            String name;
            int priority; // higher = important
            Task(String n, int p) { name = n; priority = p; }
        }

        PriorityQueue<Task> taskPQ = new PriorityQueue<>(
                Comparator.comparingInt((Task t) -> t.priority).reversed()
        );

        taskPQ.add(new Task("Email", 2));
        taskPQ.add(new Task("Server Restart", 5));
        taskPQ.add(new Task("Backup", 3));

        System.out.println("\nQ31 Task Execution Order:");
        while (!taskPQ.isEmpty()) {
            System.out.println(taskPQ.poll().name);
        }


        
        // 32. Process customer requests in FIFO (Queue) **********************************************************************
        Queue<String> requests = new LinkedList<>();
        requests.add("Req1");
        requests.add("Req2");
        requests.add("Req3");

        System.out.println("\nQ32 FIFO Processing:");
        while (!requests.isEmpty())
            System.out.println(requests.poll());


        
        // 33. Find & Remove Largest Number in Queue **********************************************************************
        Queue<Integer> q = new LinkedList<>(Arrays.asList(10, 50, 20, 40));

        int max = q.stream().max(Integer::compareTo).get(); // largest
        q.remove(max);  // remove largest

        System.out.println("\nQ33 Removed Largest = " + max);
        System.out.println("Remaining Queue = " + q);


        
        // 34. Implement Circular Queue (wraps around) **********************************************************************
        class CircularQueue {
            int[] arr;
            int front = 0, rear = -1, size = 0;

            CircularQueue(int capacity) { arr = new int[capacity]; }

            void enqueue(int x) {
                if (size == arr.length) {
                    System.out.println("Queue Full");
                    return;
                }
                rear = (rear + 1) % arr.length; // wrap
                arr[rear] = x;
                size++;
            }

            int dequeue() {
                if (size == 0) {
                    System.out.println("Empty");
                    return -1;
                }
                int val = arr[front];
                front = (front + 1) % arr.length; // wrap
                size--;
                return val;
            }
        }

        CircularQueue cq = new CircularQueue(3);
        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.dequeue();     // removes 10
        cq.enqueue(40);   // wraps around

        System.out.println("\nQ34 Circular Queue Working.");


        
        // 35. Move High-value Orders (>1000) to Front **********************************************************************
        Queue<Integer> orders = new LinkedList<>(Arrays.asList(500, 2000, 300, 1500));

        Queue<Integer> high = new LinkedList<>();
        Queue<Integer> normal = new LinkedList<>();

        for (int o : orders) {
            if (o > 1000) high.add(o);
            else normal.add(o);
        }

        high.addAll(normal); // high value in front

        System.out.println("\nQ35 Reordered Queue = " + high);


        
        // 36. Undo-Redo system using two stacks **********************************************************************
        Stack<String> undo = new Stack<>();
        Stack<String> redo = new Stack<>();

        undo.push("Type A");
        undo.push("Type B");
        undo.push("Type C");

        String lastAction = undo.pop(); // undo
        redo.push(lastAction);

        System.out.println("\nQ36 Undo Stack = " + undo);
        System.out.println("Redo Stack = " + redo);


        
        // 37. Ticket Counter (People served in arrival order) **********************************************************************
        Queue<String> people = new LinkedList<>();
        people.add("Person1");
        people.add("Person2");
        people.add("Person3");

        System.out.println("\nQ37 Serving People:");
        while (!people.isEmpty())
            System.out.println("Serving " + people.poll());


        
        // 38. Merge Two Priority Queues **********************************************************************
        PriorityQueue<Integer> p1 = new PriorityQueue<>(Arrays.asList(3, 1, 5));
        PriorityQueue<Integer> p2 = new PriorityQueue<>(Arrays.asList(4, 2, 6));

        PriorityQueue<Integer> mergedPQ = new PriorityQueue<>();
        mergedPQ.addAll(p1);
        mergedPQ.addAll(p2);

        System.out.println("\nQ38 Merged Priority Queue:");
        while (!mergedPQ.isEmpty())
            System.out.print(mergedPQ.poll() + " ");


        
        // 39. Recently Used Items List (Deque) **********************************************************************
        Deque<String> recent = new LinkedList<>();

        // Add to front (most recent first)
        recent.addFirst("File1");
        recent.addFirst("File2");
        recent.addFirst("File3");

        System.out.println("\n\nQ39 Recent Items = " + recent);


        
        // 40. Reverse a Queue using Java 8 Streams **********************************************************************
        Queue<Integer> toReverse = new LinkedList<>(Arrays.asList(10, 20, 30, 40));

        Queue<Integer> reversed =
                toReverse.stream()
                         .sorted(Comparator.reverseOrder())  // stream reverse
                         .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("\nQ40 Reversed Queue = " + reversed);
    }
}
