/**
 * Course class
 *
 * @author Jing Xuan Long
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Contains course information
 */
class Course implements Serializable {

    private String courseCode;
    private String courseTitle;
    private double courseCredit;
    private ArrayList<Course> prerequisites;
    private String courseStatus;
    private String courseGrade;
    private String semesterTaken;
    private String relevance;

    /**
     * Course Constructor with no data
     */
    public Course() {
        this.courseCode = null;
        this.courseTitle = null;
        this.courseCredit = 0;
        this.prerequisites = null;
        this.courseCode = null;
        this.courseStatus = null;
        this.courseGrade = null;
        this.semesterTaken = null;
        this.relevance = null;
    }

    /**
     * Course Constructor with all available data
     *
     * @param courseCode    respective course code
     * @param courseTitle   respective course title
     * @param courseCredit  respective course credit
     * @param prerequisites list of perquisites for course
     * @param courseStatus  respective course status
     * @param courseGrade   respective course grade
     * @param semesterTaken semester course was taken
     */
    public Course(String courseCode, String courseTitle, double courseCredit, ArrayList<Course> prerequisites, String courseStatus, String courseGrade, String semesterTaken) {
        if ((courseCode != null && !courseCode.isEmpty())) {
            this.prerequisites = new ArrayList<>(prerequisites);
            this.courseCode = courseCode;
            this.courseTitle = courseTitle;
            this.courseCredit = courseCredit;
            this.prerequisites = prerequisites;
            this.courseStatus = courseStatus;
            this.courseGrade = courseGrade;
            this.semesterTaken = semesterTaken;
        }
    }

    /**
     * Course Constructor copy
     *
     * @param copy copy of Course
     */
    public Course(Course copy) {
        courseCode = copy.courseCode;
        courseTitle = copy.courseTitle;
        courseCredit = copy.courseCredit;
        prerequisites = copy.prerequisites;
        courseStatus = copy.courseStatus;
        courseGrade = copy.courseGrade;
        semesterTaken = copy.semesterTaken;
        courseCode = copy.relevance;

    }

    /**
     * Course Constructor for partial data
     *
     * @param courseCode    respective course code
     * @param courseTitle   respective course title
     * @param courseCredit  respective course credit
     * @param prerequisites list of perquisites for course
     */
    public Course(String courseCode, double courseCredit, String courseTitle, ArrayList<Course> prerequisites) {
        if ((courseCode != null && !courseCode.isEmpty())) {
            this.prerequisites = new ArrayList<>(prerequisites);
            this.courseCode = courseCode;
            this.courseTitle = courseTitle;
            this.courseCredit = courseCredit;
            this.prerequisites = prerequisites;
        }
    }

    /**
     * Course Constructor for partial data
     *
     * @param courseCode   respective course code
     * @param courseTitle  respective course title
     * @param courseCredit respective course credit
     */
    public Course(String courseCode, double courseCredit, String courseTitle) {
        if ((courseCode != null && !courseCode.isEmpty())) {
            this.prerequisites = new ArrayList<>();
            this.courseCode = courseCode;
            this.courseTitle = courseTitle;
            this.courseCredit = courseCredit;
        }
    }

    /**
     * Constructs a course for partial data
     *
     * @param courseCode    respective course code
     * @param courseStatus  respective course status
     * @param courseGrade   respective course grade
     * @param semesterTaken semester course was taken
     */
    public Course(String courseCode, String courseStatus, String courseGrade, String semesterTaken) {
        if ((courseCode != null && !courseCode.isEmpty())) {
            this.courseCode = courseCode;
            this.courseStatus = courseStatus;
            this.courseGrade = courseGrade;
            this.semesterTaken = semesterTaken;
        }
    }

    /**
     * Gets course code
     *
     * @return course code
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     * Sets course code
     *
     * @param courseCode course code to be assigned to course
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Gets course code
     *
     * @return course code
     */
    public String getCourseTitle() {
        return this.courseTitle;
    }

    /**
     * Sets course title
     *
     * @param courseTitle course title to be assigned to course
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * Gets course credit
     *
     * @return course credit
     */
    public double getCourseCredit() {
        return this.courseCredit;
    }

    /**
     * Sets course credit
     *
     * @param credit course credit value to be assigned to course
     */
    public void setCourseCredit(double credit) {
        this.courseCredit = credit;
    }

    /**
     * Gets list of prerequisites from course
     *
     * @return list of prerequisites
     */
    public ArrayList<Course> getPrerequisites() {
        return this.prerequisites;
    }

    /**
     * Sets list of prerequisites for course
     *
     * @param preReqList list of prerequisites to be assigned to course
     */
    public void setPrerequisites(ArrayList<Course> preReqList) {
        this.prerequisites = preReqList;
    }

    /**
     * Gets course status from course
     *
     * @return course status
     */
    public String getCourseStatus() {
        return this.courseStatus;
    }

    /**
     * Sets courseStatus for course
     *
     * @param courseStatus (Complete or InProgress)
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * Gets course grade
     *
     * @return course grade
     */
    public String getCourseGrade() {
        return this.courseGrade;
    }

    /**
     * Sets grade of course
     *
     * @param grade grade to be assigned to course
     */
    public void setCourseGrade(String grade) {
        this.courseGrade = grade;
    }

    /**
     * Gets semester taken
     *
     * @return semester taken
     */
    public String getSemesterTaken() {
        return this.semesterTaken;
    }

    /**
     * Sets semester taken for course
     *
     * @param semester semester taken to be assigned to course
     */
    public void setSemesterTaken(String semester) {
        this.semesterTaken = semester;
    }

    /**
     * Gets relevance of course
     *
     * @return relevance of course
     */
    public String getRelevance() {
        return relevance;
    }

    /**
     * Sets relevance of course
     *
     * @param relevance (required, elective or minor)
     */
    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    /**
     * Overridden toString method
     */
    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseCredit=" + courseCredit +
                ", courseStatus='" + courseStatus + '\'' +
                ", courseGrade='" + courseGrade + '\'' +
                ", semesterTaken='" + semesterTaken + '\'' +
                ", relevance='" + relevance + '\'' +
                '}' + '\n';
    }

    /**
     * Overridden equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Double.compare(course.courseCredit, courseCredit) == 0 &&
                Objects.equals(courseCode, course.courseCode) &&
                Objects.equals(courseTitle, course.courseTitle) &&
                Objects.equals(prerequisites, course.prerequisites) &&
                Objects.equals(courseStatus, course.courseStatus) &&
                Objects.equals(courseGrade, course.courseGrade) &&
                Objects.equals(semesterTaken, course.semesterTaken) &&
                Objects.equals(relevance, course.relevance);
    }

    /**
     * Overridden hashCode method
     */
    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseTitle, courseCredit, prerequisites, courseStatus, courseGrade, semesterTaken, relevance);
    }
}
