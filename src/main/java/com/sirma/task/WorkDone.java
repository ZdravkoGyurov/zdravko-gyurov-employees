package com.sirma.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing the work done by an employee in a project. Corresponds to
 * a single line from the input file
 *
 */
public final class WorkDone {

    private static final Logger LOGGER = Logger.getLogger(WorkDone.class.getName());

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String EMPTY_DATE = "NULL";

    private final int employeeId;
    private final int projectId;
    private Date dateFrom;
    private Date dateTo;

    /**
     *
     * @param employeeId - ID of the employee
     * @param projectId  - ID of the project
     * @param dateFrom   - The date the employee started working on the project
     * @param dateTo     - The date the employee stopped working on the project. If
     *                   dateTo is NULL it is set to the current date.
     */
    public WorkDone(final String employeeId, final String projectId, final String dateFrom, final String dateTo) {
        this.employeeId = Integer.parseInt(employeeId);
        this.projectId = Integer.parseInt(projectId);

        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

        try {
            this.dateFrom = dateFormat.parse(dateFrom);
        } catch (final ParseException e) {
            final String logMessage = "Couldn't parse DateFrom for EmployeeId: " + this.employeeId + " and ProjectId: "
                    + this.projectId;
            LOGGER.log(Level.SEVERE, logMessage);
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        if (dateTo.equals(EMPTY_DATE)) {
            this.dateTo = new Date();
        } else {
            try {
                this.dateTo = dateFormat.parse(dateTo);
            } catch (final ParseException e) {
                final String logMessage = "Couldn't parse DateTo for EmployeeId: " + this.employeeId
                        + " and ProjectId: " + this.projectId;
                LOGGER.log(Level.SEVERE, logMessage);
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    /**
     * Calculates the number of days both employees worked together on the same
     * project
     *
     * @param otherWork - Work of the other employee
     * @return number of days the employees collaborated
     */
    public int daysWorkedTogether(final WorkDone otherWork) {
        if (otherWork == null) {
            throw new NullPointerException("otherWork cannot be null");
        }
        if (projectId != otherWork.getProjectId()) {
            throw new IllegalArgumentException("ProjectIDs do not match. Work should be done on the same project");
        }

        if (dateFrom.after(otherWork.getDateFrom())
                && (dateFrom.before(otherWork.getDateTo()) || dateFrom.equals(otherWork.getDateTo()))
                && dateTo.after(otherWork.getDateTo())) {
            return calculateTotalDayDifference(dateFrom, otherWork.getDateTo());
        } else if (dateFrom.before(otherWork.getDateFrom())
                && (dateTo.after(otherWork.getDateFrom()) || dateTo.equals(otherWork.getDateFrom()))
                && dateTo.before(otherWork.getDateTo())) {
            return calculateTotalDayDifference(otherWork.getDateFrom(), dateTo);
        } else if ((dateFrom.after(otherWork.getDateFrom()) || dateFrom.equals(otherWork.getDateFrom()))
                && (dateTo.before(otherWork.getDateTo()) || dateTo.equals(otherWork.getDateTo()))) {
            return calculateTotalDayDifference(dateFrom, dateTo);
        } else if ((otherWork.getDateFrom().after(dateFrom) || otherWork.getDateFrom().equals(dateFrom))
                && (otherWork.getDateTo().before(dateTo) || otherWork.getDateTo().equals(dateTo))) {
            return calculateTotalDayDifference(otherWork.getDateFrom(), otherWork.getDateTo());
        } else {
            return 0;
        }
    }

    private int calculateTotalDayDifference(final Date dateFrom, final Date dateTo) {
        if (dateFrom == null) {
            throw new NullPointerException("dateFrom cannot be null");
        }
        if (dateTo == null) {
            throw new NullPointerException("dateTo cannot be null");
        }
        if (dateFrom.after(dateTo)) {
            throw new IllegalArgumentException("dateFrom cannot be after dateTo");
        }

        final long difference = dateTo.getTime() - dateFrom.getTime();

        final int milliseconds = 1000;
        final int seconds = 60;
        final int minutes = 60;
        final int hours = 24;
        final long differenceInDays = difference / (milliseconds * seconds * minutes * hours) + 1;

        return (int) differenceInDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, projectId);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkDone)) {
            return false;
        }
        final WorkDone other = (WorkDone) obj;
        return employeeId == other.employeeId && projectId == other.projectId;
    }
}
