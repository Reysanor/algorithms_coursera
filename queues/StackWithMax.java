/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class StackWithMax extends Stack<Integer> {
    private Stack<Integer> maximums;

    public StackWithMax() {
        maximums = new Stack<>();
    }

    public void push(Integer item) {
        super.push(item);

        if (maximums.isEmpty()) {
            maximums.push(item);
        }

        else if (item >= maximums.peek()) {
            //System.out.println("item: "+ item + ", max: " + maximums.peek());

            maximums.push(item);
        }
    }

    public Integer pop() {
        int val = super.pop();
        //System.out.println("val: " + val + ", max: " + maximums.peek());
        if (maximums.peek() == val) {
            maximums.pop();

        }
        return val;
    }


    public static void main(String[] args) {
        StackWithMax stackWithMax = new StackWithMax();
        stackWithMax.push(2);// 1
        stackWithMax.push(4);// 2
        stackWithMax.push(6);// 3
        stackWithMax.push(3);
        stackWithMax.push(5);
        stackWithMax.pop();
        stackWithMax.pop();
        stackWithMax.pop();
        stackWithMax.pop();
        stackWithMax.pop();
    }
}
