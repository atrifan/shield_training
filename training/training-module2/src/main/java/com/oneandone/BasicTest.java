package com.oneandone;

import com.oneandone.training.app.App;
import fit.ColumnFixture;

/**
 * Hello world!
 *
 */
public class BasicTest extends ColumnFixture {
    public boolean persistHero() {
        App theapp = new App();
        return theapp.testPersist();
    }

    public boolean deleteHero() {
        App theApp = new App();
        return theApp.testDelete();
    }
}
