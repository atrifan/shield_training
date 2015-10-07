package com.oneandone;

import com.oneandone.training.app.App;
import fit.ColumnFixture;

/**
 * Hello world!
 *
 */
public class ColumnExample extends ColumnFixture {
    public int firstNumber;
    public int secondNumber;

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }
    public int total() {
        return firstNumber + secondNumber;
    }
    public boolean persistHero() {
        App theapp = new App();
        return theapp.testPersist();
    }

    public boolean deleteHero() {
        App theApp = new App();
        return theApp.testDelete();
    }
}
