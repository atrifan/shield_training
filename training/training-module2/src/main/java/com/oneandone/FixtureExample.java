package com.oneandone;

import com.oneandone.fixture.implementations.Example;
import com.oneandone.fixture.util.CellWrapper;
import com.oneandone.fixture.util.Format;
import com.oneandone.fixture.util.RowWrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

/**
 * Created by atrifan on 10/7/2015.
 * get an environmentVariable $#name##
 * get json path $.field1.field2
 * get xml path /
 */
public class FixtureExample {
    protected RowWrapper row;

    public List<List<String>> doTable(List<List<String>> table)
            throws Exception {
        List<List<String>> res = new Vector<List<String>>();
        int rowNo = 0;
        for (List<String> r : table) {
            processRow(res, r, rowNo);
            rowNo++;
        }
        return res;
    }

    protected void processRow(List<List<String>> res, List<String> row,
                              int rowNo) throws Exception {
        RowWrapper rowWrapper = new RowWrapper(row);
        rowWrapper.setRowNumber(rowNo);
        Format format = new Format();
        try {
            evaluateRow(rowWrapper);
        } catch (Exception ex) {
            format.exception(rowWrapper.getCell(0), ex);
        } finally {
            row = mapRowToString(rowWrapper, row);
            res.add(row);
        }
        // res.add(row);
    }

    protected void evaluateRow(RowWrapper currentRow) {
        row = currentRow;
        Example exampleMethods = new Example(row);
        CellWrapper cell0 = row.getCell(0);
        if (cell0 == null) {
            throw new RuntimeException(
                    "Current RestFixture row is not parseable (maybe empty or not existent)");
        }
        String methodName = cell0.getText();
        if (methodName.isEmpty()) {
            throw new RuntimeException("RestFixture method not specified");
        }
        Method method1 = null;
        try {
            method1 = exampleMethods.getClass().getMethod(methodName);
            method1.invoke(exampleMethods);
        } catch (SecurityException e) {
            throw new RuntimeException(
                    "Not enough permissions to access method " + methodName
                            + " for this class "
                            + exampleMethods.getClass().getSimpleName(), e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Class " + exampleMethods.getClass().getName()
                    + " doesn't have a callable method named " + methodName, e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Method named " + methodName
                    + " invoked with the wrong argument.", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Method named " + methodName
                    + " is not public.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Method named " + methodName
                    + " threw an exception when executing.", e);
        }
    }

    protected List<String> mapRowToString(RowWrapper resultRow,
                                          List<String> actualRow) {

        List<String> resultCurrentRow = resultRow.getAsList();
        for (int i = 0; i < resultCurrentRow.size(); i++) {
            // HACK: it seems that even if the content is unchanged,
            // Slim renders red cell
            String cell = resultCurrentRow.get(i);
            if (cell.equals(actualRow.get(i))) {
                resultCurrentRow.set(i, "");
            }
        }

        return resultCurrentRow;

    }
}
