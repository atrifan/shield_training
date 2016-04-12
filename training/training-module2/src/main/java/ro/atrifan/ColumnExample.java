package ro.atrifan;

import fit.ColumnFixture;

/**
 * Hello world!
 *
 */
public class ColumnExample extends ColumnFixture {
    private int firstNumber;
    public int secondNumber;


    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }
    public int total() {
        return firstNumber + secondNumber;
    }

    public boolean persistHero() {
        //TODO 1: persistHero return true false use training_module 1
        return false;
    }

    public boolean deleteHero() {
        //TODO 1: deleteHero return true false use training_module 2
        return false;
    }

    public void setMyFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }
}
