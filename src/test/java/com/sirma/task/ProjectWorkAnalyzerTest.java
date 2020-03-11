package com.sirma.task;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class ProjectWorkAnalyzerTest {

    @Test(expected = NullPointerException.class)
    public void testProjectWorkAnalyzerConstructorNull() {
        final Map<Integer, Set<WorkDone>> workDonePerProject = null;
        new ProjectWorkAnalyzer(workDonePerProject);
    }

    @Test
    public void testCalculateCollaborativeWork1() {
        final Map<Integer, Set<WorkDone>> workDonePerProject = new LinkedHashMap<>();

        final String projectId = "222";

        final String employeeId1 = "111";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId, dateFrom1, dateTo1);

        final String employeeId2 = "112";
        final String dateFrom2 = "2015-05-07";
        final String dateTo2 = "2015-05-08";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId, dateFrom2, dateTo2);

        final Set<WorkDone> workDone = new LinkedHashSet<>();
        workDone.add(workDone1);
        workDone.add(workDone2);

        workDonePerProject.put(Integer.valueOf(projectId), workDone);

        final ProjectWorkAnalyzer analyzer = new ProjectWorkAnalyzer(workDonePerProject);
        final Map<Integer, Map<Integer, Integer>> collabWork = analyzer.calculateCollaborativeWork();

        final int expectedMapSize = 2;
        Assert.assertEquals(expectedMapSize, collabWork.size());

        final int expectedCollabDays = 2;
        Assert.assertEquals(expectedCollabDays,
                collabWork.get(Integer.valueOf(employeeId1)).get(Integer.valueOf(employeeId2)).intValue());
        Assert.assertEquals(expectedCollabDays,
                collabWork.get(Integer.valueOf(employeeId2)).get(Integer.valueOf(employeeId1)).intValue());
    }

    @Test
    public void testCalculateCollaborativeWork2() {
        final Map<Integer, Set<WorkDone>> workDonePerProject = new LinkedHashMap<>();

        final String projectId1 = "222";

        final String employeeId1 = "111";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "112";
        final String dateFrom2 = "2015-05-07";
        final String dateTo2 = "2015-05-08";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId1, dateFrom2, dateTo2);

        final String projectId2 = "333";

        final String employeeId3 = "111";
        final String dateFrom3 = "2015-06-01";
        final String dateTo3 = "2015-06-05";
        final WorkDone workDone3 = new WorkDone(employeeId3, projectId2, dateFrom3, dateTo3);

        final String employeeId4 = "112";
        final String dateFrom4 = "2015-06-01";
        final String dateTo4 = "2015-06-10";
        final WorkDone workDone4 = new WorkDone(employeeId4, projectId2, dateFrom4, dateTo4);

        final Set<WorkDone> workDoneSet1 = new LinkedHashSet<>();
        workDoneSet1.add(workDone1);
        workDoneSet1.add(workDone2);
        workDonePerProject.put(Integer.valueOf(projectId1), workDoneSet1);

        final Set<WorkDone> workDoneSet2 = new LinkedHashSet<>();
        workDoneSet2.add(workDone3);
        workDoneSet2.add(workDone4);
        workDonePerProject.put(Integer.valueOf(projectId2), workDoneSet2);

        final ProjectWorkAnalyzer analyzer = new ProjectWorkAnalyzer(workDonePerProject);
        final Map<Integer, Map<Integer, Integer>> collabWork = analyzer.calculateCollaborativeWork();

        final int expectedMapSize = 2;
        Assert.assertEquals(expectedMapSize, collabWork.size());

        final int expectedCollabDays = 7;
        Assert.assertEquals(expectedCollabDays,
                collabWork.get(Integer.valueOf(employeeId1)).get(Integer.valueOf(employeeId2)).intValue());
        Assert.assertEquals(expectedCollabDays,
                collabWork.get(Integer.valueOf(employeeId2)).get(Integer.valueOf(employeeId1)).intValue());
    }

    @Test
    public void testPrintCollaborativeWork() {
        final Map<Integer, Set<WorkDone>> workDonePerProject = new LinkedHashMap<>();

        final String projectId1 = "222";

        final String employeeId1 = "111";
        final String dateFrom1 = "2015-05-05";
        final String dateTo1 = "2015-05-10";
        final WorkDone workDone1 = new WorkDone(employeeId1, projectId1, dateFrom1, dateTo1);

        final String employeeId2 = "112";
        final String dateFrom2 = "2015-05-07";
        final String dateTo2 = "2015-05-08";
        final WorkDone workDone2 = new WorkDone(employeeId2, projectId1, dateFrom2, dateTo2);

        final String projectId2 = "333";

        final String employeeId3 = "113";
        final String dateFrom3 = "2015-06-01";
        final String dateTo3 = "2015-06-05";
        final WorkDone workDone3 = new WorkDone(employeeId3, projectId2, dateFrom3, dateTo3);

        final String employeeId4 = "114";
        final String dateFrom4 = "2015-06-01";
        final String dateTo4 = "2015-06-10";
        final WorkDone workDone4 = new WorkDone(employeeId4, projectId2, dateFrom4, dateTo4);

        final Set<WorkDone> workDoneSet1 = new LinkedHashSet<>();
        workDoneSet1.add(workDone1);
        workDoneSet1.add(workDone2);
        workDonePerProject.put(Integer.valueOf(projectId1), workDoneSet1);

        final Set<WorkDone> workDoneSet2 = new LinkedHashSet<>();
        workDoneSet2.add(workDone3);
        workDoneSet2.add(workDone4);
        workDonePerProject.put(Integer.valueOf(projectId2), workDoneSet2);

        final ProjectWorkAnalyzer analyzer = new ProjectWorkAnalyzer(workDonePerProject);
        final Map<Integer, Map<Integer, Integer>> collabWork = analyzer.calculateCollaborativeWork();

        final String expectedMessage = "Longest collaborative work was when employee(113) worked together with (114) "
                + "for 5 days.";
        Assert.assertEquals(expectedMessage, analyzer.printLongestCollaborativeWork(collabWork));
    }
}
