package com.company;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static private String gridSearch(String[] G, String[] P) {


        boolean found = false;
        int count =0;
        int index =0;
        List<Integer> extraPos = new ArrayList<Integer>();

        for(int i =0; i<G.length;i++)
        {
            List<Integer> extraPosSecond = new ArrayList<Integer>();
            if(found)//If the first sting is found, Find all possible indexes for second string
            {
                int x =0;
                while (G[i].indexOf(P[count],x)!=-1)
                {
                    extraPosSecond.add(G[i].indexOf(P[count],x));
                    x++;
                }
            }

            if(!found && G[i].contains(P[count]))
            {
                //if the substring is found start motion to look for second string
                index = G[i].indexOf(P[count]);
                found = true;
                int extraFinds =index+1;
                //find all possible occurrences of first substring string in first string
                while (G[i].indexOf(P[count],extraFinds)!=-1)
                {
                    extraPos.add(G[i].indexOf(P[count],extraFinds));
                    extraFinds++;
                }
                count++;
            }
            else if(found && extraPosSecond.contains(index))
            {
                //if the end of the substrings are reach break out and return YES
                //otherwise continue moving through the substrings
                if(count+2>P.length)
                    break;
                else
                    count++;
            }
            else
            {
                //if a substring was found but does not result in a completed substring. search                    //line again
                if(found)
                    i--;
                //If there are no additional indexes to search in substring then continue                        //searching next line
                if(extraPos.isEmpty())
                {
                    found = false;
                    count =0;
                    index =0;
                }
                else
                {
                    //If more additional indexes are found in first line go back and try them
                    i=i-count+1;
                    found = true;
                    count = 1;
                    index = extraPos.remove(0);
                }
            }

        }
        if(found)
            return "YES";
        else
            return "NO";

    }

    static private String[] input(Scanner scanner, int count)
    {
        String []  newInput = new String[count];
        for(int i =0; i<count;i++)
        {
            newInput[i] = scanner.next();
        }
        return(newInput);
    }

    public static void main(String[] args) throws IOException {


        try (Scanner scanner = new Scanner(new FileReader("tests.txt"))) {


            int testCase = scanner.nextInt();
            for(int i =0; i<testCase; i++)
            {
            int row = scanner.nextInt();
            int column  = scanner.nextInt();

            String [] grid = input(scanner, row);

            row = scanner.nextInt();
            column = scanner.nextInt();

            String [] subgrid = input(scanner,row);


                System.out.println(gridSearch(grid, subgrid));



            }
        }
    }
}
