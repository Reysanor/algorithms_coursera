/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class QueueWithTwoStacks<T> {
    private Stack<T> intStack;
    private Stack<T> outStack;

    public QueueWithTwoStacks() {
        intStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void enqueue(T item) {
        intStack.push(item);

    }

    //jezeli out jest puste, to wypełniam ją z in
    //powoduje to ustawienie elementów w odwrotnej kolejności
    public T dequeue() {
        if (outStack.isEmpty()) {
            while (!intStack.isEmpty()) {
                outStack.push(intStack.pop());
            }
        }
        return outStack.pop();
    }

    public boolean isEmpty() {
        return outStack.isEmpty();
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> queueWithTwoStacks = new QueueWithTwoStacks<>();


    }
}
