package com.oneandone.fixture.implementations;

import com.oneandone.fixture.util.Format;
import com.oneandone.fixture.util.RowWrapper;
import com.oneandone.fixture.util.Tools;

/**
 * Created by atrifan on 10/7/2015.
 */
public class Example {

    private RowWrapper row;
    public Example(RowWrapper row) {
        this.row = row;
    }

    public void setVariable() {
        String variableName = row.getCell(1).getText();
        String variableValue = Tools.variableReplacer(row.getCell(2).getText());
        System.setProperty(variableName, variableValue);
    }

    public void assertVariableEquals() {

        Format format = new Format();
        if (row.getNoCells() == 3) {
            String variableName = row.getCell(1).getText();
            String expectedValue = Tools.variableReplacer(row.getCell(2).getText());

            if ((expectedValue.contains("{")) || (expectedValue.contains("<"))) {
                expectedValue = Tools.fromHtml(expectedValue);
            }

            //TODO: I COULD SOME JSON I COULD SOME XML

            if (System.getProperties().containsKey(variableName)) {
                String variableValue = System.getProperty(variableName);
                if (!variableValue.equals(expectedValue)) {
                    format.wrong(row.getCell(2),
                            System.getProperty(variableName));
                } else {
                    format.right(row.getCell(2));
                }

            } else {
                format.error(row.getCell(0), variableName
                        + " not found in saved variables queue.");
            }

        } else {
            format.error(row.getCell(0),
                    "assertEquals should use 3 cells only.");
        }

    }

    public void assertEquals() {

        Format format = new Format();
        if (row.getNoCells() == 3) {
            String variableName = Tools.variableReplacer(row.getCell(1).getText());
            String expectedValue = Tools.variableReplacer(row.getCell(2).getText());

            if ((expectedValue.contains("{")) || (expectedValue.contains("<"))) {
                expectedValue = Tools.fromHtml(expectedValue);
            }

            //TODO: I COULD SOME JSON I COULD SOME XML

            if (!variableName.equals(expectedValue)) {
                format.wrong(row.getCell(2),
                        System.getProperty(variableName));
            } else {
                    format.right(row.getCell(2));
            }

        } else {
            format.error(row.getCell(0),
                    "assertEquals should use 3 cells only.");
        }

    }

}
