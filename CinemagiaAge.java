package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CinemagiaAge {

    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setupDriver() {
        this.driver = new FirefoxDriver();
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

    @Test(groups={"C1"})
    public void loadPage(){
        driver.get("http://www.cinemagia.ro");
    }

    @Test(groups={"C1"})
    public void searchMovie() throws InterruptedException {
        String movieName = "Deadpool";
        WebElement searchbox = driver.findElement(By.cssSelector("#topSearchFormQ"));
        searchbox.sendKeys(movieName);
        WebElement submitbutton = driver.findElement(By.cssSelector("#topSearchForm>.submit"));
        submitbutton.click();
    }

    @Test
    public void validateSearch(){
        WebElement searchResults = driver.findElement(By.cssSelector(".search-list>.search-subtitle.mb10"));
        Assert.assertEquals(searchResults.getText(),"Rezultate exacte");
    }

    @Test(dependsOnMethods = "validateSearch")
    public void selectMovie(){
        WebElement selectMovieYear = driver.findElement(By.xpath(".//*[@id='left']/div[1]/div[1]/a[1]"));
        selectMovieYear.click();
    }

    @Test(dependsOnMethods = "selectMovie")
    public void checkYear(){
        String yearMovie = "(2016)";
        WebElement year = driver.findElement(By.cssSelector(".link1"));
        Assert.assertEquals(year.getText(),yearMovie);
    }

    @Test(dependsOnMethods = "selectMovie")
    public void checkDurata(){
        String durataMovie = "106 minute";
        WebElement durata = driver.findElement(By.cssSelector(".list1 li:nth-child(4)>span"));
        Assert.assertEquals(durata.getText(),durataMovie);
    }

}
