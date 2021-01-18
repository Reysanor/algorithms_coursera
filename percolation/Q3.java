/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Q3 {
    private int[] numbers;
    private int[] succesor;

    public Q3(int size) {
        numbers = new int[size];
        succesor = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i;
            succesor[i] = i + 1;
        }
        succesor[size - 1] = size - 1;
    }

    public void delete(int remove) {
        System.out.println("number to remove: " + numbers[remove] + " succesor: " + succesor[remove]);
        numbers[remove] = -1;
        succesor[remove - 1] = succesor[remove];
        System.out.println(
                "new succesor of: " + numbers[remove - 1] + " is: " + +succesor[remove - 1]);
        succesor[remove] = -1;
    }

    public int getSuccesor(int value) {
        return succesor[value];
    }

    public static void main(String[] args) {
        Q3 q3 = new Q3(10);
        q3.delete(0);

    }

}
