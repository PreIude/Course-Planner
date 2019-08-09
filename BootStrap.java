/**
 * Bootstrap class
 *
 * @author Jing Xuan Long
 */

import java.io.*;
import java.util.Scanner;


/**
 * Central unit for entering course catalog and degree information throw .csv files
 */
class BootStrap {

    public static void main(String[] args) {

        University x = new University();

        Scanner input = new Scanner(System.in);

        String fileName;

        int option;
        boolean loop = true;

        while (loop) {
            System.out.println("Input Choice (1-4)\n1. Load course data\n2. Load degree requirements\n3. Save to file\n4. Exit");
            option = input.nextInt();
            while (option < 1 || option > 4) {
                System.out.println("Error - Invalid input\n");
                System.out.println("Input Choice (1-4)\n1. Load course data\n2. Load degree requirements\n3. Save to file\n4. Exit");
                option = input.nextInt();
            }
            if (option == 1) {
                System.out.println("Input Course Catalog file: ");
                fileName = input.next();
                try {
                    x.initializeCourseDb(fileName);
                } catch (IOException e) {
                    System.out.println("Error - file not found");
                    System.exit(0);
                }
            } else if (option == 2) {
                System.out.println("2");
                System.out.println("Input Degree file: ");
                fileName = input.next();
                try {
                    x.initializeDegreeRequirements(fileName);
                } catch (IOException e) {
                    System.out.println("Error - file not found");
                    System.exit(0);
                }
            } else if (option == 3) {
                x.writeToDisk();
            } else {
                loop = false;
            }
        }
    }
}
