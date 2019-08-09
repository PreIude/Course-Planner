/**
 * Degree class
 *
 * @author Jing Xuan Long
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Degree blueprint
 */
abstract class Degree implements Serializable {

    static double level1000 = 6.0;

    private String degreeTitle;
    private ArrayList<String> listOfRequiredCourseCodes;
    private ArrayList<Course> requiredCourses;

    /**
     * Degree constructor
     */
    public Degree() {
        listOfRequiredCourseCodes = new ArrayList<>();
        requiredCourses = new ArrayList<>();
    }

    /**
     * get Degree title
     *
     * @return degree title
     */
    String getDegreeTitle() {
        return this.degreeTitle;
    }

    /**
     * set Degree title
     *
     * @param title degree title
     */
    void setDegreeTitle(String title) {
        this.degreeTitle = title;
    }

    /**
     * get list of required course codes for degree
     *
     * @return list of course codes
     */
    ArrayList<String> getListOfRequiredCourseCodes() {
        return this.listOfRequiredCourseCodes;
    }


    /**
     * sets list of required course codes for degree
     *
     * @param listOfRequiredCourseCodes list of course codes
     */
    void setRequiredCourseCodes(ArrayList<String> listOfRequiredCourseCodes) {
        this.listOfRequiredCourseCodes = listOfRequiredCourseCodes;
    }

    /**
     * gets list of required course for degree
     *
     * @return list of required courses
     */
    ArrayList<Course> getRequiredCourses() {
        return this.requiredCourses;
    }

    /**
     * sets list of required course for degree
     *
     * @param requiredCourses list of required courses
     */
    void setRequiredCourses(ArrayList<Course> requiredCourses) {
        this.requiredCourses = requiredCourses;
    }

    /**
     * checks for degree completion
     *
     * @param thePlan plan of study
     * @return true if degree is complete and false otherwise
     */
    abstract boolean meetsRequirements(PlanOfStudy thePlan);

    /**
     * checks number of credits remaining from total required
     *
     * @param thePlan plan of study
     * @return number of credits remaining
     */
    abstract double numberOfCreditsRemaining(PlanOfStudy thePlan);

    /**
     * Checks transcript for courses not represented that are in degree requirements
     *
     * @param thePlan plan of study
     * @return list of remaining courses
     */
    ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan) {
        ArrayList<Course> notInTranscript = new ArrayList<>();
        boolean courseFound = false;
        for (Course i : thePlan.getDegreeProgram().getRequiredCourses()) {
            for (Course j : thePlan.getTranscript()) {
                if (i.getCourseCode().compareTo(j.getCourseCode()) == 0) {
                    courseFound = true;
                }
            }
            if (!courseFound) {
                notInTranscript.add(i);
            }
            courseFound = false;
        }
        return notInTranscript;
    }

    /**
     * Checks if course code is a science course based on prefix
     *
     * @param code course code
     * @return true if course is science and false otherwise
     */
    boolean isScience(String code) {
        if (code.compareTo("BIOL") == 0 ||
                code.compareTo("MATH") == 0 ||
                code.compareTo("ECON") == 0 ||
                code.compareTo("BIOM") == 0 ||
                code.compareTo("ECOL") == 0 ||
                code.compareTo("FOOD") == 0 ||
                code.compareTo("PHYS") == 0 ||
                code.compareTo("CHEM") == 0 ||
                code.compareTo("ZOO") == 0 ||
                code.compareTo("STAT") == 0) {
            return true;
        }
        return false;
    }

    /**
     * Overridden toString method
     */
    @Override
    public String toString() {
        return "Degree{" +
                "degreeTitle='" + degreeTitle + '\'' +
                ", listOfRequiredCourseCodes=" + listOfRequiredCourseCodes +
                ", requiredCourses=" + requiredCourses +
                '}';
    }

    /**
     * Overridden equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Degree degree = (Degree) o;
        return Objects.equals(degreeTitle, degree.degreeTitle) &&
                Objects.equals(listOfRequiredCourseCodes, degree.listOfRequiredCourseCodes) &&
                Objects.equals(requiredCourses, degree.requiredCourses);
    }

    /**
     * Overridden hashCode method
     */
    @Override
    public int hashCode() {
        return Objects.hash(degreeTitle, listOfRequiredCourseCodes, requiredCourses);
    }
}
