/**
 * Student class
 *
 * @author Jing Xuan Long
 */

import java.io.Serializable;
import java.util.Objects;

class Student implements Serializable {

    private String fullName;
    private String firstName;
    private String lastName;
    private Integer studentNumber;
    private PlanOfStudy myPlan;

    /**
     * Empty Student constructor
     */
    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.fullName = "";
        this.studentNumber = 0;
        myPlan = new PlanOfStudy();
    }

    /**
     * Student constructor
     *
     * @param firstName     student first name
     * @param lastName      student last name
     * @param studentNumber student number
     * @param studentNumber student number
     * @param plan          student number
     */
    public Student(String firstName, String lastName, Integer studentNumber, PlanOfStudy plan) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.studentNumber = studentNumber;
        this.myPlan = plan;
    }

    /**
     * get full name
     *
     * @return gets full name of student
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * set full name of student
     *
     * @param fullName fullName name of student
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * set first name
     *
     * @param first first name of student
     */
    public void setFirstName(String first) {
        this.firstName = first;
    }

    /**
     * set last name
     *
     * @param last last name of student
     */
    public void setLastName(String last) {
        this.lastName = last;
    }

    /**
     * get first name
     *
     * @return gets first name of student
     */
    public String getFirstName() {
        return this.firstName;
    }


    /**
     * get last name
     *
     * @return gets last name of student
     */
    public String getLastName() {
        return this.lastName;
    }


    /**
     * sets student number
     *
     * @param studentNum student number
     */
    public void setStudentNumber(Integer studentNum) {
        this.studentNumber = studentNum;
    }


    /**
     * get student number
     *
     * @return gets student number
     */
    public Integer getStudentNumber() {
        return this.studentNumber;
    }

    /**
     * get plan of study
     *
     * @return plan of study
     */
    public PlanOfStudy getMyPlan() {
        return myPlan;
    }

    /**
     * set plan of study
     *
     * @param myPlan plan of study
     */
    public void setMyPlan(PlanOfStudy myPlan) {
        this.myPlan = myPlan;
    }

    /**
     * Overridden toString method
     */
    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentNumber=" + studentNumber +
                ", myPlan=" + myPlan +
                '}';
    }

    /**
     * Overridden equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(fullName, student.fullName) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(studentNumber, student.studentNumber) &&
                Objects.equals(myPlan, student.myPlan);
    }

    /**
     * Overridden hashCode method
     */
    @Override
    public int hashCode() {
        return Objects.hash(fullName, firstName, lastName, studentNumber, myPlan);
    }
}
