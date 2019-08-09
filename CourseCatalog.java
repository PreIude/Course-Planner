/**
 * CourseCatalog class
 *
 * @author Jing Xuan Long
 */

import java.io.*;
import java.util.*;

/**
 * Contains hash map of courses
 */
class CourseCatalog implements Serializable {

    private HashMap<String, Course> catalog;

    /**
     * Course Catalog Constructor with no data
     */
    public CourseCatalog() {
        catalog = new HashMap<>();
    }

    /**
     * Course Catalog Constructor that saves data into a hash map
     *
     * @param list a list of courses
     */
    public CourseCatalog(ArrayList<Course> list) {
        catalog = new HashMap<>();
        for (Course i : list) {
            catalog.put(i.getCourseCode(), i);
        }
    }

    /**
     * Course Catalog Constructor
     *
     * @param copy course catalog copy
     */
    public CourseCatalog(CourseCatalog copy) {
        catalog = copy.catalog;
    }

    /**
     * Gets course catalog
     *
     * @return the course catalog as a hash map
     */
    public HashMap<String, Course> getCatalog() {
        return this.catalog;
    }


    /**
     * Initializes course catalog from bootstrap file
     *
     * @param filename bootstrap filename
     * @throws IOException throw file reading errors
     */
    public void initializeCatalog(String filename) throws IOException {
        //clears catalog if user wants to override initial call
        this.catalog.clear();
        Course temp;
        String lineRead;

        //store records after parsing file
        ArrayList<String> records = new ArrayList<>();
        ArrayList<String[]> splitRecords = new ArrayList<>();
        ArrayList<String> prereqRecords = new ArrayList<>();

        //actual list of prerequisite courses
        ArrayList<Course> prereq = new ArrayList<>();

        List<String> keys;

        //holds prerequisite courses as a list of codes
        HashMap<String, ArrayList<String>> prereqHolder = new HashMap<>();

        BufferedReader reader;

        reader = new BufferedReader(new FileReader(filename));

        //loop through each line of file and add to records
        while ((lineRead = reader.readLine()) != null) {
            records.add(lineRead.trim());
        }

        reader.close();
        int i = 0;

        //split every line of file by a ','
        while (i < records.size()) {
            splitRecords.add(records.get(i).split(","));
            i++;
        }

        i = 0;
        //iterate through each line and store them into a temp course WITHOUT the prerequisites and add them to the catalog
        while (i < splitRecords.size()) {
            if (splitRecords.get(i).length == 3) {
                temp = new Course(splitRecords.get(i)[0], Double.parseDouble(splitRecords.get(i)[1]), splitRecords.get(i)[2]);
                addCourse(temp);
            } else if (splitRecords.get(i).length == 4) {
                //split last part of text by : to get list of perquisites and store them a hash map with it's respective course code
                prereqRecords.addAll(Arrays.asList(splitRecords.get(i)[3].split(":")));
                prereqHolder.put(splitRecords.get(i)[0], new ArrayList<>(prereqRecords));
                temp = new Course(splitRecords.get(i)[0], Double.parseDouble(splitRecords.get(i)[1]), splitRecords.get(i)[2]);
                addCourse(temp);
                prereqRecords.clear();
            }
            i++;
        }
        //create an array list of every key of hash map
        keys = new ArrayList<>(prereqHolder.keySet());
        //iterate through every entry and assign appropriate prerequisites
        for (String key : keys) {
            prereqRecords = new ArrayList<>(prereqHolder.get(key));
            for (String course : prereqRecords) {
                prereq.add(catalog.get(course));
            }
            catalog.get(key).setPrerequisites(prereq);
            //clear list for next batch of prerequisites
            prereq = new ArrayList<>();
        }
    }

    /**
     * Adds course to course catalog
     *
     * @param toAdd course to add
     */
    public void addCourse(Course toAdd) {
        catalog.put(toAdd.getCourseCode(), toAdd);
    }

    /**
     * Removes course from course catalog
     *
     * @param toRemove course to remove
     */
    public void removeCourse(Course toRemove) {
        catalog.remove(toRemove.getCourseCode());
    }

    /**
     * Saves state of course catalog as catalog.sav
     */
    public void saveCatalog() {
        try {
            ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("catalog.sav"));
            s.writeObject(this);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Searches for course in catalog
     *
     * @param courseCode the course to find
     * @return the course to find if successful or null if not found
     */
    public Course findCourse(String courseCode) {
        return catalog.get(courseCode);
    }

    /**
     * Overridden toString method
     */
    @Override
    public String toString() {
        return "CourseCatalog{" +
                "catalog=" + catalog +
                '}';
    }

    /**
     * Overridden equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCatalog that = (CourseCatalog) o;
        return Objects.equals(catalog, that.catalog);
    }

    /**
     * Overridden hashCode method
     */
    @Override
    public int hashCode() {
        return Objects.hash(catalog);
    }

    public boolean isEmpty() {
        return this.catalog.isEmpty();
    }


}

