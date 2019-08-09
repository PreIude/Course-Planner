/**
 * University class
 *
 * @author Jing Xuan Long
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Holds course catalog, list of degrees and students
 *
 * @author Jing Xuan Long
 */

public class University implements Serializable {

    private CourseCatalog courseDb;
    private ArrayList<Degree> listOfDegrees;
    private ArrayList<Student> listOfStudents;


    /**
     * University constructor
     */
    public University() {
        listOfDegrees = new ArrayList<>();
        courseDb = new CourseCatalog();
        listOfStudents = new ArrayList<>();
    }


    /**
     * get degrees
     *
     * @return list of degrees
     */
    public ArrayList<Degree> getListOfDegrees() {
        return listOfDegrees;
    }


    /**
     * get course data base
     *
     * @return course catalog
     */
    public CourseCatalog getCourseDb() {
        return courseDb;
    }

    /**
     * set course data base
     *
     * @param uniCourses catalog
     */
    public void setCourseDb(CourseCatalog uniCourses) {
        this.courseDb = uniCourses;
    }


    /**
     * set list of degrees
     *
     * @param listOfDegrees list Of Degrees
     */
    public void setListOfDegrees(ArrayList<Degree> listOfDegrees) {
        this.listOfDegrees = new ArrayList<>(listOfDegrees);
    }

    /**
     * get list of students
     *
     * @return list of students
     */
    public ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

    /**
     * set list of students
     *
     * @param listOfStudents list of students
     */
    public void setListOfStudents(ArrayList<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    /**
     * get course catalog
     *
     * @return course catalog
     */
    public HashMap<String, Course> getUniCourses() {
        return this.getCourseDb().getCatalog();
    }

    /**
     * initialize course catalog
     *
     * @param fileName name of file
     * @throws IOException throw file reading errors
     */
    public void initializeCourseDb(String fileName) throws IOException {
        this.courseDb.initializeCatalog(fileName);
    }

    /**
     * Initializes Degree Requirements for completion
     *
     * @param filename name of file
     * @throws IOException throw file reading errors
     */
    public void initializeDegreeRequirements(String filename) throws IOException {
        this.listOfDegrees.clear();
        if (this.courseDb.isEmpty()) {
            System.out.println("Error - function terminated - Course data has not been loaded\n");
            return;
        }

        List<String> records = new ArrayList<>();
        ArrayList<String[]> splitRecords = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        Degree temp;
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.replaceAll("\\s+", "");

            records.add(line);

        }
        reader.close();

        int i = 0;

        while (i < records.size()) {
            splitRecords.add(records.get(i).split(","));
            i++;
        }

        for (int x = 0; x < records.size(); x++) {
            i = 0;
            if (splitRecords.get(x)[0].compareTo("BCG") == 0) {
                temp = new BCG();
                temp.setDegreeTitle("BCG");
            } else if (splitRecords.get(x)[0].compareTo("CS") == 0) {
                temp = new CS();
                temp.setDegreeTitle("CS");

            } else if (splitRecords.get(x)[0].compareTo("SEng") == 0) {
                temp = new SEng();
                temp.setDegreeTitle("SEng");
            } else {
                continue;
            }

            while (i < splitRecords.get(x).length - 1) {
                temp.getListOfRequiredCourseCodes().add(splitRecords.get(x)[i + 1]);

                i++;
            }
            this.listOfDegrees.add(temp);
        }
        initializeDegreeCourses();
    }

    /**
     * Converts course codes to courses
     */
    public void initializeDegreeCourses() {
        for (Degree listOfDegree : this.listOfDegrees) {
            for (int x = 0; x < listOfDegree.getListOfRequiredCourseCodes().size(); x++) {
                listOfDegree.getRequiredCourses().add(this.courseDb.getCatalog().get(listOfDegree.getListOfRequiredCourseCodes().get(x)));
            }
        }
    }

    /**
     * Saves university to disk
     */
    public void writeToDisk() {
        try {
            ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("data.sav"));
            s.writeObject(this);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Overridden toString method
     */
    @Override
    public String toString() {
        return "University{" +
                "courseDb=" + courseDb +
                ", listOfDegrees=" + listOfDegrees +
                '}';
    }

    /**
     * Overridden equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(courseDb, that.courseDb) &&
                Objects.equals(listOfDegrees, that.listOfDegrees);
    }

    /**
     * Overridden hashCode method
     */
    @Override
    public int hashCode() {
        return Objects.hash(courseDb, listOfDegrees);
    }
}





