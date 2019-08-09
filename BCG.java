/**
 * BCG class
 *
 * @author Jing Xuan Long
 */

import java.util.ArrayList;
import java.util.HashMap;

class BCG extends GeneralDegree {

    private static double cisORstat = 0.5;

    private static double cis3000 = 1.0;

    /**
     * checks for degree completion
     *
     * @param thePlan plan of study
     * @return true if degree is complete and false otherwise
     */
    boolean meetsRequirements(PlanOfStudy thePlan) {
        Double val;
        HashMap<String, Double> tracker = new HashMap<>();

        ArrayList<String> keys;

        ArrayList<Course> transcript = new ArrayList<>(thePlan.getTranscript());
        ArrayList<Course> degreeReq = new ArrayList<>(thePlan.getDegreeProgram().getRequiredCourses());

        String code[];

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
        tracker.put("CIS", 4.25);
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
                if (isScience(code[0])) {
                    if (Double.parseDouble(code[1]) >= 3000) {
                        level3000 = level3000 - i.getCourseCredit();
                    }
                    sciences = sciences - i.getCourseCredit();
                } else if (!isScience(code[0]) && code[0].compareTo("CIS") != 0) {
                    if (Double.parseDouble(code[1]) >= 3000) {
                        level3000 = level3000 - i.getCourseCredit();
                    }
                    arts = arts - i.getCourseCredit();
                }
                if (code[0].compareTo("CIS") == 0 && (Double.parseDouble(code[1]) >= 3000) && cis3000 > 0) {
                    cis3000 = cis3000 - i.getCourseCredit();
                } else if ((i.getCourseCode().contains("CIS*2") || i.getCourseCode().contains("STAT*2") && cisORstat > 0)) {
                    cisORstat = cisORstat - i.getCourseCredit();

                } else if (!isScience(code[0]) && code[0].compareTo("CIS") != 0) {
                    if (Double.parseDouble(code[1]) >= 3000) {
                        level3000 = level3000 - i.getCourseCredit();
                    }
                    arts = arts - i.getCourseCredit();
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
        if (requiredCredits <= 0 && cis3000 <= 0 && cisORstat <= 0 && arts <= 0 && sciences <= 0) {
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