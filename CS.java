/**
 * CS class
 *
 * @author Jing Xuan Long
 */

import java.util.ArrayList;
import java.util.HashMap;

public class CS extends HonoursDegree {

    private static double cis3000 = 6.0;
    private static double cis4000 = 4.0;
    private static double cisORstat = 0.5;

    /**
     * checks for degree completion
     *
     * @param thePlan plan of study
     * @return true if degree is complete and false otherwise
     */
    public boolean meetsRequirements(PlanOfStudy thePlan) {

        Double val;
        HashMap<String, Double> tracker = new HashMap<>();
        double cisAverage = 0;
        int cisCount = 0;
        double culmalativeAverage = 0;
        ArrayList<String> keys;

        ArrayList<Course> transcript = new ArrayList<>(thePlan.getTranscript());
        ArrayList<Course> degreeReq = new ArrayList<>(thePlan.getDegreeProgram().getRequiredCourses());

        String code[];

        for (Course i : transcript) {
            if (i.getCourseCode().compareTo("Complete") == 0) {
                if (i.getCourseCode().contains("CIS")) {
                    cisCount++;
                    cisAverage = Double.parseDouble(i.getCourseGrade());
                    culmalativeAverage = Double.parseDouble(i.getCourseGrade());
                }
            }
        }

        cisAverage = cisAverage / cisCount;
        culmalativeAverage = culmalativeAverage / transcript.size();

        ArrayList<Course> transcriptRequired = new ArrayList<>();
        for (Course i : degreeReq) {
            for (Course j : transcript) {
                if (i.getCourseCode().compareTo(j.getCourseCode()) == 0) {
                    transcriptRequired.add(j);
                }
            }
        }
        transcript.removeAll(transcriptRequired);
//        req has less courses than in degree req so return false;
        if (transcriptRequired.size() != thePlan.getDegreeProgram().getRequiredCourses().size()) {
            return false;
        }
//        if any required courses are uncompleted return false;
        for (Course i : transcriptRequired) {
            if (i.getCourseStatus().compareTo("Complete") != 0) {
                return false;
            }
        }
        tracker.put("CIS", 7.5);
        tracker.put("MATH", 0.5);

        for (Course i : transcript) {
            if (i.getCourseStatus().compareTo("Complete") == 0) {
                code = i.getCourseCode().split("\\*");
                val = tracker.get(code[0]);
                if (val == null) {
                    val = i.getCourseCredit();
                } else {
                    val += 1;
                }
                tracker.put(code[0], val);
                if (Double.parseDouble(code[1]) >= 1000 && Double.parseDouble(code[1]) < 2000 && level1000 > 0) {
                    level1000 = level1000 - i.getCourseCredit();
                }
                if (code[0].compareTo("CIS") == 0) {
                    if (Double.parseDouble(code[1]) >= 4000 && cis4000 > 0) {
                        cis4000 = cis4000 - i.getCourseCredit();
                        freeElective = freeElective - i.getCourseCredit();

                    } else if (Double.parseDouble(code[1]) >= 3000 && cis3000 > 0) {
                        cis3000 = cis3000 - i.getCourseCredit();
                        freeElective = freeElective - i.getCourseCredit();
                    } else if (i.getCourseCode().compareTo("CIS*2640") != 0 && i.getCourseCode().compareTo("STAT*2040") != 0) {
                        cisORstat = cisORstat - i.getCourseCredit();
                    } else {
                        freeElective = freeElective - i.getCourseCredit();
                    }

                } else if (code[0].compareTo("CIS") != 0) {
                    if (Double.parseDouble(code[1]) >= 3000) {
                        aoa3000 = aoa3000 - i.getCourseCredit();
                        areaOfApplication = areaOfApplication - i.getCourseCredit();
                    } else {
                        areaOfApplication = areaOfApplication - i.getCourseCredit();
                    }

                }
                if (code[0].compareTo("CIS") == 0 && (Double.parseDouble(code[1]) >= 3000) && cis3000 > 0) {
                    cis3000 = cis3000 - i.getCourseCredit();
                }
            }
        }
        keys = new ArrayList<>(tracker.keySet());
        for (String key : keys) {
            if (tracker.get(key) > 11) {
                requiredCredits = requiredCredits - 11;
            } else {
                requiredCredits = requiredCredits - tracker.get(key);
            }
        }
        if (tracker.get("CIS") >= 11.25 && requiredCredits <= 0 && cis3000 <= 0 && freeElective <= 0 && cisORstat <= 0 && cis4000 <= 0 && aoa3000 <= 0 && areaOfApplication <= 0 && culmalativeAverage >= 60 && cisAverage >= 70) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks number of credits remaining from total required
     *
     * @param thePlan plan of study
     * @return number of credits remaining
     */
    double numberOfCreditsRemaining(PlanOfStudy thePlan) {
        double credits = 0;
        for (Course i : thePlan.getTranscript()) {
            if (i.getCourseStatus().compareTo("Complete") == 0) {
                credits = credits + i.getCourseCredit();
            }
        }
        return requiredCredits - credits;
    }


}
