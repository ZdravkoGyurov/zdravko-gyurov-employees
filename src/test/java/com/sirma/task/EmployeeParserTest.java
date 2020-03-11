package com.sirma.task;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class EmployeeParserTest {

    @Test(expected = NullPointerException.class)
    public void testEmployeeParserConstructor() {
        final String filePath = null;
        new EmployeeParser(filePath);
    }

    @Test
    public void testGetWorkDonePerProject() {
        final String filePath = "src/test/resources/employees";
        final EmployeeParser parser = new EmployeeParser(filePath);

        final Map<Integer, Set<WorkDone>> workDonePerProject = parser.getWorkDonePerProject();

        final int expectedProjects = 3;
        Assert.assertEquals(expectedProjects, workDonePerProject.size());

        final int projectId1 = 10;
        final int expectedWorkDoneP1 = 2;
        final Set<WorkDone> workDone1 = workDonePerProject.get(projectId1);
        Assert.assertEquals(expectedWorkDoneP1, workDone1.size());
        final Set<WorkDone> expectedWorkDone1 = new LinkedHashSet<>();
        expectedWorkDone1.add(new WorkDone("143", "10", "2015-05-01", "2015-05-15"));
        expectedWorkDone1.add(new WorkDone("218", "10", "2015-05-05", "2015-05-10"));
        expectedWorkDone1.add(new WorkDone("218", "10", "2015-05-01", "2015-05-02"));
        expectedWorkDone1.add(new WorkDone("143", "10", "2015-05-03", "2015-05-05"));
        Assert.assertEquals(expectedWorkDone1, workDone1);

        final int projectId2 = 11;
        final int expectedWorkDoneP2 = 2;
        final Set<WorkDone> workDone2 = workDonePerProject.get(projectId2);
        Assert.assertEquals(expectedWorkDoneP2, workDone2.size());
        final Set<WorkDone> expectedWorkDone2 = new LinkedHashSet<>();
        expectedWorkDone2.add(new WorkDone("143", "11", "2015-06-01", "2015-06-10"));
        expectedWorkDone2.add(new WorkDone("218", "11", "2015-06-01", "2015-06-10"));
        Assert.assertEquals(expectedWorkDone2, workDone2);

        final int projectId3 = 12;
        final int expectedWorkDoneP3 = 2;
        final Set<WorkDone> workDone3 = workDonePerProject.get(projectId3);
        Assert.assertEquals(expectedWorkDoneP3, workDone3.size());
        final Set<WorkDone> expectedWorkDone3 = new LinkedHashSet<>();
        expectedWorkDone3.add(new WorkDone("144", "12", "2015-07-01", "2015-08-01"));
        expectedWorkDone3.add(new WorkDone("219", "12", "2015-07-01", "2015-08-01"));
        Assert.assertEquals(expectedWorkDone3, workDone3);
    }
}
