package com.sirma.task;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that works with data arranged by projects and has methods that make
 * statistics
 *
 */
public final class ProjectWorkAnalyzer {

    private final Map<Integer, Set<WorkDone>> workDonePerProject;

    /**
     * @param workDonePerProject - information about the work done by each employee
     *                           arranged in a map by projectID
     */
    public ProjectWorkAnalyzer(final Map<Integer, Set<WorkDone>> workDonePerProject) {
        if (workDonePerProject == null) {
            throw new NullPointerException("Information about work done per project cannot be null");
        }

        this.workDonePerProject = workDonePerProject;
    }

    /**
     * Returns information about the collaborative work of each employee with each
     * other employee
     *
     * @return rearranged information in a map with a keys employeeIDs and values
     *         another map that has keys employeeIDs and values duration of
     *         collaborative work of the two employees
     */
    public Map<Integer, Map<Integer, Integer>> calculateCollaborativeWork() {
        final Map<Integer, Map<Integer, Integer>> collaborativeWork = new LinkedHashMap<>();

        for (final Map.Entry<Integer, Set<WorkDone>> entry : workDonePerProject.entrySet()) {
            final WorkDone[] jobsInProject = new WorkDone[entry.getValue().size()];
            entry.getValue().toArray(jobsInProject);

            for (int i = 0; i < jobsInProject.length - 1; i++) {
                for (int j = i + 1; j < jobsInProject.length; j++) {
                    reportWorkForEmployees(collaborativeWork, jobsInProject[i], jobsInProject[j]);
                    reportWorkForEmployees(collaborativeWork, jobsInProject[j], jobsInProject[i]);
                }
            }
        }

        return collaborativeWork;
    }

    /**
     * Generates a nice human-readable message about the employee pair that has the
     * longest time of collaborative work
     *
     * @param collaborativeWork - information about the collaborative work of each
     *                          employee with each other employee
     * @return Message with a format "Longest collaborative work was when
     *         employee(X) worked together with (Y) for Z days.". An appropriate
     *         message is printed if there's no information about any collaborative
     *         work.
     */
    public String printLongestCollaborativeWork(final Map<Integer, Map<Integer, Integer>> collaborativeWork) {
        if (collaborativeWork == null) {
            throw new NullPointerException("Information about collaborative work cannot be null");
        }
        if (collaborativeWork.isEmpty()) {
            return "There is no information about any collaborative work";
        }

        int employee1 = 0;
        int employee2 = 0;
        int longestCollaborativeWork = 0;
        for (final Map.Entry<Integer, Map<Integer, Integer>> entry1 : collaborativeWork.entrySet()) {
            final int currentEmployee1 = entry1.getKey();

            for (final Map.Entry<Integer, Integer> entry2 : entry1.getValue().entrySet()) {
                final int currentEmployee2 = entry2.getKey();
                final int currentCollaborativeWork = entry2.getValue();

                if (currentCollaborativeWork > longestCollaborativeWork) {
                    longestCollaborativeWork = currentCollaborativeWork;
                    employee1 = currentEmployee1;
                    employee2 = currentEmployee2;
                }
            }
        }

        if (longestCollaborativeWork == 0) {
            return "There is no information about any collaborative work";
        }

        return buildLongestCollabMessage(employee1, employee2, longestCollaborativeWork);
    }

    private String buildLongestCollabMessage(final int employee1, final int employee2,
            final int longestCollaborativeWork) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Longest collaborative work was when employee(");
        stringBuilder.append(employee1);
        stringBuilder.append(") worked together with (");
        stringBuilder.append(employee2);
        stringBuilder.append(") for ");
        stringBuilder.append(longestCollaborativeWork);
        stringBuilder.append(" days.");

        return stringBuilder.toString();
    }

    private void reportWorkForEmployees(final Map<Integer, Map<Integer, Integer>> collaborativeWork,
            final WorkDone employee1, final WorkDone employee2) {
        if (collaborativeWork == null) {
            throw new NullPointerException("CollaborativeWork cannot be null");
        }
        if (employee1 == null) {
            throw new NullPointerException("Work done by employee1 cannot be null");
        }
        if (employee2 == null) {
            throw new NullPointerException("Work done by employee2 cannot be null");
        }

        final int employeeId1 = employee1.getEmployeeId();

        if (!collaborativeWork.containsKey(employeeId1)) {
            final Map<Integer, Integer> statsForEmp1 = new LinkedHashMap<>();
            collaborativeWork.put(employeeId1, statsForEmp1);
        }

        final int employeeId2 = employee2.getEmployeeId();

        final Map<Integer, Integer> statsForEmp1 = collaborativeWork.get(employeeId1);

        if (statsForEmp1.containsKey(employeeId2)) {
            final int timeSoFar = statsForEmp1.get(employeeId2);
            statsForEmp1.put(employeeId2, timeSoFar + employee1.daysWorkedTogether(employee2));
        } else {
            statsForEmp1.put(employeeId2, employee1.daysWorkedTogether(employee2));
        }
    }
}
