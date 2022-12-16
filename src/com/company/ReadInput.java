package com.company;

import javax.swing.tree.ExpandVetoException;
import java.util.Scanner;

public class ReadInput {
    private static Scanner scanner= new Scanner(System.in);
    public static Integer getInt(){
        Integer number;
        try {
            number=Integer.parseInt(scanner.next());
        }
        catch (Exception e){
            return null;
        }
        return Math.abs(number);
    }
}
