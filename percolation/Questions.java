/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Questions {
    int [] numbers;
    int [] succesor;

    public Questions(int size) {
        numbers = new int[size];
        succesor = new int[size];
        for(int i=0;i<size;i++){
            numbers[i]=i;
            succesor[i]=i+1;
        }
        succesor[size-1]=size-1;
    }

    public void delete(int remove){
        System.out.println("number: " +   numbers[remove] + " succesor: " + succesor[remove]);
        numbers[remove]=-1;
        succesor[remove-1]=succesor[remove];
        System.out.println("new succesor of: "+ numbers[remove-1] +" is: " + +succesor[remove-1]);
        succesor[remove]=-1;
    }
    public int getSuccesor(int value){
        return succesor[value];
    }


}
