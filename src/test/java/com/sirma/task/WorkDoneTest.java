package com.sirma.task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class WorkDoneTest {

    @Test
    public void testWorkDoneConstructor() {
        final String employeeId = "111";
        final String projectId = "222";
        final String dateFrom = "2010-10-10";
        final String dateTo = "2010-10-20";

        final WorkDone workDone = new WorkDone(employeeId, projectId, dateFrom, dateTo);

        final int expectedEmployeeId = 111;
        Assert.assertEquals(expectedEmployeeId, workDone.getEmployeeId());

        final int expectedProjectId = 222;
        Assert.assertEquals(expectedProjectId, workDone.getProjectId());

        final int expectedDateFromYear = 2010;
        final int expectedDateFromMonth = Calendar.OCTOBER;
        final int expectedDateFromDay = 10;
        final Date expectedDateFrom = new GregorianCalendar(expectedDateFromYear, expectedDateFromMonth,
                expectedDateFromDay).getTime();
        Assert.assertEquals(expectedDateFrom, workDone.getDateFrom());

        final int expectedDateToYear = 2010;
        final int expectedDateToMonth = Calendar.OCTOBER;
        final int expectedDateToDay = 20;
        final Date expectedDateTo = new GregorianCalendar(expectedDateToYear, expectedDateToMonth, expectedDateToDay)
                .getTime();
        Assert.assertEquals(expectedDateTo, workDone.getDateTo());
    }

    @Test
    public void testWorkDoneConstructorNullDate() {
        final String employeeId = "111";
        final String projectId = "222";
        final String dateFrom = "2010-10-10";
        final String dateTo = "NULL";

        final WorkDone workDone = new WorkDone(employeeId, projectId, dateFrom, dateTo);

        final Date expectedDateTo = new Date();
        Assert.assertEquals(expectedDateTo, workDone.getDateTo());
    }

    @Test
    public void testDaysWorkedTogether1() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-01";
        final String dateTo2 = "2015-05-03";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 0;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test
    public void testDaysWorkedTogether2() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-01";
        final String dateTo2 = "2015-05-05";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 1;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test
    public void testDaysWorkedTogether3() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-01";
        final String dateTo2 = "2015-05-07";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 3;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test
    public void testDaysWorkedTogether4() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-05";
        final String dateTo2 = "2015-05-07";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 3;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test
    public void testDaysWorkedTogether5() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-06";
        final String dateTo2 = "2015-05-09";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 4;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test
    public void testDaysWorkedTogether6() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-07";
        final String dateTo2 = "2015-05-10";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 4;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test
    public void testDaysWorkedTogether7() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-07";
        final String dateTo2 = "2015-05-12";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 4;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test
    public void testDaysWorkedTogether8() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-10";
        final String dateTo2 = "2015-05-12";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 1;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test
    public void testDaysWorkedTogether9() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-11";
        final String dateTo2 = "2015-05-13";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        final int expectedDaysWorkedTogether = 0;
        Assert.assertEquals(expectedDaysWorkedTogether, workDone1.daysWorkedTogether(workDone2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysWorkedTogether10() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "333";
        final String dateFrom2 = "2015-05-11";
        final String dateTo2 = "2015-05-13";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        workDone1.daysWorkedTogether(workDone2);
    }

    @Test(expected = NullPointerException.class)
    public void testDaysWorkedTogether11() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final WorkDone workDone2 = null;

        workDone1.daysWorkedTogether(workDone2);
    }

    @Test
    public void testWorkDoneEquals1() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "333";
        final String dateFrom2 = "2015-05-11";
        final String dateTo2 = "2015-05-13";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        Assert.assertFalse(workDone1.equals(workDone2));
    }

    @Test
    public void testWorkDoneEquals2() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "222";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-11";
        final String dateTo2 = "2015-05-13";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        Assert.assertFalse(workDone1.equals(workDone2));
    }

    @Test
    public void testWorkDoneEquals3() {
        final String employeeId1 = "111";
        final String projectId1 = "222";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "111";
        final String projectId2 = "222";
        final String dateFrom2 = "2015-05-11";
        final String dateTo2 = "2015-05-13";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId2, dateFrom2, dateTo2);

        Assert.assertTrue(workDone1.equals(workDone2));
    }
}
