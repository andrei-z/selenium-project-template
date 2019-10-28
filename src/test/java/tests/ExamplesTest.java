/*
 * source: https://github.com/andrei-z/selenium-project-template
 **/
package tests;

import drivermanager.Driver;

import static drivermanager.Driver.Browser.*;
import static drivermanager.Driver.getParameter;
import static drivermanager.Driver.selectBrowser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;

public class ExamplesTest {

    private static Driver driver;

    @BeforeClass
    public static void setUp(){

        String browserName = getParameter("browser"); // sets name to conjunct with -D (define a system property) Maven CLI option, results in -Dbrowser in this case
        driver = new Driver(browserName);

/*      The following is generally not recommended. But might be helpful for cross-browser tests preparation or debugging.
 *      To select a browser from inside of the class, uncomment these 2 lines
 *      (this should also work from inside of any particular @Test body as well):
*/
//      driver.quit(); // tear down browser instance invoked either by Maven or IDE
//      driver = new Driver(selectBrowser(FIREFOX));
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    /* exampleOne [recommended]. @Test will run with the default 'chrome' browser both from IDE and Maven
    *  unless a different browser is specified by maven in -Dbrowser or the browser is selected in @Before / @BeforeClass in IDE
    * */
    @Test
    public void exampleOne(){
        driver.get("http://yr.no/");
        Assert.assertTrue(driver.getTitle().startsWith("Yr"));
        Assert.assertSame(CHROME, currentDriver); // assert current driver is 'chrome'
    }

    /* exampleTwo [not recommended]. @Test will run with 'firefox' regardless of the run being started from IDE or Maven
    * Browser is selected from inside of the @Test body
    * */
    @Test
    public void exampleTwo(){
        driver.quit();
        driver = new Driver(selectBrowser(FIREFOX));

        driver.get("http://yr.no/");
        Assert.assertTrue(driver.getTitle().startsWith("Yr"));
        Assert.assertSame(FIREFOX, currentDriver); // assert current driver is 'firefox'
    }
}
