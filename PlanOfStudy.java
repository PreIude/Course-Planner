/**
 * PlanOfStudy Class
 *
 * @author Jing Xuan Long
 */

import java.io.*;
import java.util.*;

/**
 * Where a student's transcript and degree choice is stored
 */
class PlanOfStudy implements Serializable {

    private Degree degreeProgram;

    private ArrayList<Course> transcript;

    /**
     * Plan of Study Constructor
     */
    public PlanOfStudy() {

        transcript = new ArrayList<>();
    }

    /**
     * Imports object from .sav file based on user input
     *
     * @param filename user filename
     */
    public void importData(String filename) {
        PlanOfStudy temp;
        try {
            ObjectInputStream inS = new ObjectInputStream(new FileInputStream(filename));
            temp = (PlanOfStudy) inS.readObject();
            this.degreeProgram = temp.degreeProgram;
            this.transcript = temp.transcript;
            inS.close();
        } catch (Exception e) {
            System.out.println("Error - file not found\n");
            System.out.println(e);
        }

    }

    /**
     * Initializes transcript from .csv  based on user input
     *
     * @param filename user filename
     * @throws IOException throw file reading errors
     */
    public void initializeTranscript(String filename) throws IOException {

        List<String> records = new ArrayList<>();
        ArrayList<String[]> splitRecords = new ArrayList<>();
        Course temp;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            records.add(line.trim());
        }
        reader.close();

        int i = 0;
        while (i < records.size()) {
            splitRecords.add(records.get(i).split(","));
            i++;
        }
        i = 0;

        while (i < splitRecords.size()) {
            temp = new Course(splitRecords.get(i)[0], splitRecords.get(i)[1], splitRecords.get(i)[2], splitRecords.get(i)[3]);
            this.transcript.add(temp);
            i++;
        }

        Set<Course> s = new HashSet<>();
        s.addAll(this.transcript);
        this.transcript = new ArrayList<>();
        this.transcript.addAll(s);

    }

    /**
     * Serializes object and stores it as a .sav file based on user input
     */
    public void saveState() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input .sav file to save as: ");
        String fileName = input.next();
        try {
            ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream(fileName));
            s.writeObject(this);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Adds course to transcript with course code and semester taken
     *
     * @param courseCode course code
     * @param semester   semester taken
     */
    public void addCourse(String courseCode, String semester) {
        Course temp = new Course();
        temp.setCourseCode(courseCode);
        temp.setSemesterTaken(semester);
        this.transcript.add(temp);
    }


    /**
     * Removes course from transcript with course code and semester taken
     *
     * @param courseCode course code
     * @param semester   semester taken
     */
    public void removeCourse(String courseCode, String semester) {

        for (Course i : this.transcript) {
            if (i.getCourseCode().compareTo(courseCode) == 0 && i.getSemesterTaken().compareTo(semester) == 0) {
                this.transcript.remove(i);
                return;
            }
        }
        System.out.println("Error - Course not found\n");
    }


    /**
     * get transcript
     *
     * @return student transcript
     */
    public ArrayList<Course> getTranscript() {
        return transcript;
    }


    /**
     * set transcript
     *
     * @param transcript student transcript
     */
    public void setTranscript(ArrayList<Course> transcript) {
        this.transcript = transcript;
    }


    /**
     * set degree program
     *
     * @param deg degree program
     */
    public void setDegreeProgram(Degree deg) {
        this.degreeProgram = deg;
    }

    /**
     * get degree program
     *
     * @return degree program
     */
    public Degree getDegreeProgram() {
        return this.degreeProgram;
    }


    /**
     * Searches for course in transcript with course code and semester taken to replace status
     *
     * @param courseCode   respective course code
     * @param semester     respective semester taken
     * @param courseStatus status to set to
     */
    public void setCourseStatus(String courseCode, String semester, String courseStatus) {
        for (Course i : this.transcript) {
            if (courseCode.compareTo(i.getCourseCode()) == 0 && semester.compareTo(i.getSemesterTaken()) == 0) {
                i.setCourseStatus(courseStatus);
            }
            System.out.println("Error - Course not found\n");

        }
    }


    /**
     * Searches for course in transcript with course code and semester taken to replace grade
     *
     * @param courseCode respective course code
     * @param semester   respective semester taken
     * @param grade      grade to set to
     */
    public void setCourseGrade(String courseCode, String semester, String grade) {

        for (Course i : this.transcript) {
            if (courseCode.compareTo(i.getCourseCode()) == 0 && semester.compareTo(i.getSemesterTaken()) == 0) {
                i.setCourseGrade(grade);
                return;
            }
        }
        System.out.println("Error - Course not found\n");
    }

    /**
     * Searches for course in transcript with course code and semester taken
     *
     * @param courseCode respective course code
     * @param semester   respective semester taken
     * @return course if found null otherwise
     */
    public Course getCourse(String courseCode, String semester) {
        for (Course i : this.transcript) {
            if (courseCode.compareTo(i.getCourseCode()) == 0 && semester.compareTo(i.getSemesterTaken()) == 0) {
                return i;
            }
        }
        System.out.println("Error - Course not found\n");
        return null;
    }

    /**
     * Overridden toString method
     */
    @Override
    public String toString() {
        return "PlanOfStudy{" +
                "degreeProgram=" + degreeProgram +
                ", transcript=" + transcript +
                '}';
    }

    /**
     * Overridden equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanOfStudy that = (PlanOfStudy) o;
        return Objects.equals(degreeProgram, that.degreeProgram) &&
                Objects.equals(transcript, that.transcript);
    }

    /**
     * Overridden hashCode method
     */
    @Override
    public int hashCode() {
        return Objects.hash(degreeProgram, transcript);
    }

}
