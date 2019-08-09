/**
 * Planner class
 *
 * @author Jing Xuan Long
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Main program where user interacts with his/her information
 */
class Planner implements Serializable {

    public static void main(String[] args) throws IOException {

        String fileName;
        String userInput;
        String grade;
        String semester;
        PlanOfStudy plan = new PlanOfStudy();
        University uni = null;
        double credits;
        int option;
        boolean loop = true;
        boolean courseFound = false;
        Scanner input = new Scanner(System.in);
        try {
            ObjectInputStream inS = new ObjectInputStream(new FileInputStream("data.sav"));
            uni = (University) inS.readObject();
            inS.close();
        } catch (Exception e) {
            System.out.println("Error - bootstrap file not found\n");
            System.out.println(e);
        }
        if (uni == null) {
            System.out.println("Error - bootstrap file is empty\n");
            return;
        }
        System.out.println("Would you like to load a transcript from a csv?");
        System.out.println("1. Yes\n2. No");
        option = input.nextInt();
        while (option < 1 || option > 2) {
            System.out.println("Error - Invalid input\n");
            System.out.println("Input Choice (1-12)\n1. Yes\n2. No\n");
            option = input.nextInt();
        }
        if (option == 1) {
            System.out.println("Input Transcript file: ");
            fileName = input.next();
            try {
                plan.initializeTranscript(fileName);
                for (Course i : plan.getTranscript()) {
                    i.setCourseCredit(uni.getUniCourses().get(i.getCourseCode()).getCourseCredit());
                    i.setCourseTitle(uni.getUniCourses().get(i.getCourseCode()).getCourseTitle());
                }
            } catch (IOException e) {
                System.out.println("Error - file not found");
                System.exit(0);
            }
        }
        while (loop) {
            System.out.println("1. Choose General Degree / Honours Major\n" +
                    "2. Add course to transcript\n" +
                    "3. View Transcript\n" +
                    "4. Mark course status in transcript\n" +
                    "5. Mark course relevance in transcript\n" +
                    "6. Remove course in transcript\n" +
                    "7. Change grade of course in transcript\n" +
                    "8. Save plan of study\n" +
                    "9. Load plan of study\n" +
                    "10. View required courses for degree that are not in transcript\n" +
                    "11. View prerequisite for a specific course\n" +
                    "12. View number of acquired credits\n" +
                    "13. View number of remaining credits to complete degree\n" +
                    "14. View if degree is complete\n" +
                    "15. Exit");
            option = input.nextInt();
            while (option < 1 || option > 15) {
                System.out.println("Error - Invalid input\n");
                System.out.println("1. Choose General Degree / Honours Major\n" +
                        "2. Add course to transcript\n" +
                        "3. View Transcript\n" +
                        "4. Mark course status in transcript\n" +
                        "5. Mark course relevance in transcript\n" +
                        "6. Remove course in transcript\n" +
                        "7. Change grade of course in transcript\n" +
                        "8. Save plan of study\n" +
                        "9. Load plan of study\n" +
                        "10. View required courses for degree that are not in transcript\n" +
                        "11. View prerequisite for a specific course\n" +
                        "12. View number of acquired credits\n" +
                        "13. View number of remaining credits to complete degree\n" +
                        "14. View if degree is complete\n" +
                        "15. Exit");
                option = input.nextInt();
            }
            if (option == 1) {

                if (plan.getDegreeProgram() != null) {
                    System.out.println("Error - Degree already registered\n");
                } else {
                    System.out.println("Degree Choice:\n1. Bachelor of Computing General \n2. Computer Science Honours\n3. Software Engineering Honours");
                    option = input.nextInt();
                    while (option < 1 || option > 3) {
                        System.out.println("\"Error - Invalid input\nDegree Choice:\n1. Bachelor of Computing General \n2. Computer Science Honours\n3. Software Engineering Honours\n");
                        option = input.nextInt();
                    }
                    if (option == 1) {
                        for (Degree i : uni.getListOfDegrees()) {
                            if (i.getDegreeTitle().compareTo("BCG") == 0) {
                                plan.setDegreeProgram(i);
                            }
                        }
                    } else if (option == 2) {
                        for (Degree i : uni.getListOfDegrees()) {
                            if (i.getDegreeTitle().compareTo("CS") == 0) {
                                plan.setDegreeProgram(i);
                            }
                        }
                    } else {
                        for (Degree i : uni.getListOfDegrees()) {
                            if (i.getDegreeTitle().compareTo("SEng") == 0) {
                                plan.setDegreeProgram(i);
                            }
                        }
                    }
                }
            } else if (option == 2) {
                System.out.println("Input course code:");
                userInput = input.next();

                while (uni.getUniCourses().get(userInput) == null) {
                    System.out.println("Error - Course not found\nInput course code:\n");
                    userInput = input.next();
                }
                System.out.println("Input semester:");
                semester = input.next();
                plan.addCourse(userInput, semester);

                System.out.println("Course Status:\n1. Complete\n2. InProgress");
                option = input.nextInt();
                while (option < 1 || option > 2) {
                    System.out.println("\"Error - Invalid input\nCourse Status\n1. Complete\n2. InProgress\n");
                    option = input.nextInt();
                }
                if (option == 1) {
                    plan.getTranscript().get(plan.getTranscript().size() - 1).setCourseStatus("Complete");
                } else {
                    plan.getTranscript().get(plan.getTranscript().size() - 1).setCourseStatus("InProgress");
                }
                plan.getTranscript().get(plan.getTranscript().size() - 1).setCourseTitle(uni.getUniCourses().get(plan.getTranscript().get(plan.getTranscript().size() - 1).getCourseCode()).getCourseTitle());
                plan.getTranscript().get(plan.getTranscript().size() - 1).setCourseCredit(uni.getUniCourses().get(plan.getTranscript().get(plan.getTranscript().size() - 1).getCourseCode()).getCourseCredit());

            } else if (option == 3) {
                System.out.println(plan.getTranscript());
            } else if (option == 4) {
                if (plan.getTranscript().isEmpty()) {
                    System.out.println("Error - Transcript is empty\n");
                } else {
                    System.out.println("Input course code:");
                    userInput = input.next();

                    System.out.println("Input semester:");
                    semester = input.next();

                    System.out.println("Course Status:\n1. Complete\n2. InProgress");
                    option = input.nextInt();

                    if (option == 1) {
                        plan.setCourseStatus(userInput, semester, "Complete");
                    } else {
                        plan.setCourseStatus(userInput, semester, "InProgress");
                    }

                }


            } else if (option == 5) {

                    System.out.println("Input course code:");
                    userInput = input.next();
                    System.out.println("Input semester:");
                    semester = input.next();
                    for (Course i : plan.getTranscript()) {
                        if (i.getCourseCode().compareTo(userInput) == 0 && i.getSemesterTaken().compareTo(semester) == 0) {
                            System.out.println("Course Relevance:\n1. Required\n2. Elective\n3. Minor/Area of Application");
                            option = input.nextInt();
                            while (option < 1 || option > 3) {
                                System.out.println("\"Error - Invalid input\nCourse Relevance:\n1. Required\n2. Elective\n3. Minor/Area of Application");
                                option = input.nextInt();
                            }
                            if (option == 1) {
                                i.setRelevance("Required");
                            } else if (option == 2) {
                                i.setRelevance("Elective");
                            } else {
                                i.setRelevance("Minor/Area of Application");
                            }

                        }else{
                            System.out.println("Error - Course not found\n");

                        }
                    }

            } else if (option == 6) {
                if (plan.getTranscript().isEmpty()) {
                    System.out.println("Error - Transcript is empty\n");
                } else {

                    System.out.println("Input course code:");
                    userInput = input.next();
                    System.out.println("Input semester:");
                    semester = input.next();
                    plan.removeCourse(userInput, semester);

                }
            } else if (option == 7) {
                if (plan.getTranscript().isEmpty()) {
                    System.out.println("Error - Transcript is empty\n");
                } else {
                    System.out.println("Input course code:");
                    userInput = input.next();
                    System.out.println("Input semester:");
                    semester = input.next();
                    System.out.println("Course Grade (Ex. 80):");
                    grade = input.next();
                    plan.setCourseGrade(userInput, semester, grade);
                    courseFound = false;
                }
            } else if (option == 8) {
                plan.saveState();
            } else if (option == 9) {
                System.out.println("Input .sav file to load: ");
                fileName = input.next();
                plan.importData(fileName);
            } else if (option == 10) {
                if (plan.getDegreeProgram() == null) {
                    System.out.println("Error - No degree registered\n");
                } else {
                    System.out.println(plan.getDegreeProgram().remainingRequiredCourses(plan));
                }


            } else if (option == 11) {

                while (!courseFound) {
                    System.out.println("Input course code:");
                    userInput = input.next();
                    if (uni.getUniCourses().get(userInput) != null) {
                        System.out.println(uni.getUniCourses().get(userInput).getPrerequisites());
                        courseFound = true;
                    }

                    if (!courseFound) {
                        System.out.println("Error - Course not found\n");
                    }
                }
                courseFound = false;

            } else if (option == 12) {
                credits = 0;
                for (Course i : plan.getTranscript()) {
                    if (i.getCourseStatus().compareTo("Complete") == 0) {
                        credits = credits + i.getCourseCredit();
                    }
                }
                System.out.println("Number of credits: " + credits + "\n");
            } else if (option == 13) {

                System.out.println("Number of credits remaining: " + (plan.getDegreeProgram().numberOfCreditsRemaining(plan)) + "\n");

            } else if (option == 14) {
                if (plan.getDegreeProgram() == null) {
                    System.out.println("Error - No degree registered\n");
                } else {
                    if (plan.getDegreeProgram().meetsRequirements(plan)) {
                        System.out.println("You have completed your degree!\n");
                        loop = false;
                    } else {
                        System.out.println("You are missing requirements for your degree\n");
                    }
                }
            } else {
                loop = false;
                System.out.println("Program terminated");
            }
        }
    }
}

