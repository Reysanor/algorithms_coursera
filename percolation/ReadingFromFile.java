/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadingFromFile {
    private static Scanner scanner;

    public ReadingFromFile(){ }


    //czy ma kolejny element
    public static boolean isEmpty() {
        return !scanner.hasNext();
    }
    //czy ma kolejną linie
    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }


    public static int readInt() {
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException  e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from standard input, "
                                                     + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from standard input, "
                                                     + "but no more tokens are available");
        }

    }





    public static void main(String[] args) {



    }
}
