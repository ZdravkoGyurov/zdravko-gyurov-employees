package com.sirma.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class with methods used for reading an input file and structuring the
 * information
 *
 */
public final class EmployeeParser {

    private static final Logger LOGGER = Logger.getLogger(EmployeeParser.class.getName());

    private static final String SEPARATOR = ", ";
    private static final int EMPLOYEE_ID_INDEX = 0;
    private static final int PROJECT_ID_INDEX = 1;
    private static final int DATE_FROM_INDEX = 2;
    private static final int DATE_TO_INDEX = 3;

    private final String filePath;
    private final Map<Integer, Set<WorkDone>> workDonePerProject;

    /**
     * @param filePath - Path of the input file
     */
    public EmployeeParser(final String filePath) {
        if (filePath == null) {
            throw new NullPointerException("File path cannot be null");
        }
        this.filePath = filePath;
        workDonePerProject = readDataFromFile();
    }

    /**
     * Returns information about the work done by each employee from the input file
     * arranged in a map by projectID
     *
     * @return Map with keys projectIDs and their corresponding values Set of the
     *         WorkDone by each employee for the given project. Returns empty map if
     *         any errors occurred while reading the file.
     */
    public Map<Integer, Set<WorkDone>> getWorkDonePerProject() {
        return workDonePerProject;
    }

    private Map<Integer, Set<WorkDone>> readDataFromFile() {
        final Map<Integer, Set<WorkDone>> jobsPerProject = new LinkedHashMap<>();

        final File inputFile = new File(filePath);
        try (final FileReader fileReader = new FileReader(inputFile);
                final BufferedReader inFromFile = new BufferedReader(fileReader);) {

            String line;
            while ((line = inFromFile.readLine()) != null) {
                final WorkDone jobDone = parseLine(line);

                if (jobsPerProject.containsKey(jobDone.getProjectId())) {
                    final Set<WorkDone> jobsDone = jobsPerProject.get(jobDone.getProjectId());
                    jobsDone.add(jobDone);
                } else {
                    final Set<WorkDone> jobsDone = new LinkedHashSet<>();
                    jobsDone.add(jobDone);
                    jobsPerProject.put(jobDone.getProjectId(), jobsDone);
                }
            }

            return jobsPerProject;
        } catch (final FileNotFoundException e) {
            final String logMessage = "File could not be found";
            LOGGER.log(Level.SEVERE, logMessage);
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } catch (final IOException e) {
            final String logMessage = "Error while reading from cache";
            LOGGER.log(Level.SEVERE, logMessage);
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return new LinkedHashMap<>();
    }

    private WorkDone parseLine(final String line) {
        if (line == null) {
            throw new NullPointerException("Line cannot be null");
        }
        if (line.split(SEPARATOR).length != 4) {
            throw new IllegalArgumentException("Line format is incorrect");
        }

        final String[] workParameters = line.split(SEPARATOR);

        return new WorkDone(workParameters[EMPLOYEE_ID_INDEX], workParameters[PROJECT_ID_INDEX],
                workParameters[DATE_FROM_INDEX], workParameters[DATE_TO_INDEX]);
    }
}
