package com.sirma.task;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Application entry point
 *
 */
public final class Main {

    public static void main(final String[] args) {
        System.out.print("Enter file path: ");
        try (final Scanner scanner = new Scanner(System.in)) {
            final String filePath = scanner.next();

            final EmployeeParser employeeParser = new EmployeeParser(filePath);
            final Map<Integer, Set<WorkDone>> workDonePerProject = employeeParser.getWorkDonePerProject();

            final ProjectWorkAnalyzer analyzer = new ProjectWorkAnalyzer(workDonePerProject);
            final Map<Integer, Map<Integer, Integer>> collaborativeWork = analyzer.calculateCollaborativeWork();

            System.out.println(analyzer.printLongestCollaborativeWork(collaborativeWork));
        }
    }
}
