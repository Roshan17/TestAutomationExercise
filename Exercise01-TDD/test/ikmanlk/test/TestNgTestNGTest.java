/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ikmanlk.test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Roshan Nadeesha
 */
public class TestNgTestNGTest {

    WebDriver driver;
    
    //setting up prerequisitiries
    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "\\Users\\user\\Desktop\\Supun\\TestIkman\\chromedriver.exe");
        driver = new ChromeDriver(); //launch chrome
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://ikman.lk/");
        
    }
    
    @Test
    public void testIkman(){
        String title = driver.getTitle();
        System.out.println(title);
        
        //click on property. Here I used find element with link text method
        driver.findElement(By.linkText("Property")).click();
        System.out.println("Clicked On property");
        
        
        //click on houses. Here I used find element by xpath method
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[2]/div[2]/div[1]/div[1]/div/div/form/div/div[3]/div/ul/li/ul[1]/li/ul/li[2]/a")).click();
        System.out.println("Clicked On houses");
        
        //Click on Colombo. Here I used find element by css SElector method
        driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div:nth-child(4) > div > ul > li > ul > li.ui-link-tree-item.loc-1506 > a")).click();
        System.out.println("Clicked On colombo");
        
        //setting prices
        driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-price > a")).click();
        
        driver.findElement(By.xpath("//*[@id=\"filters[0][minimum]\"]")).sendKeys("5000000");
        driver.findElement(By.xpath("//*[@id=\"filters[0][maximum]\"]")).sendKeys("7500000");
        System.out.println("min price set to 5000000");
        System.out.println("max price set to 7500000");
        
        //click on apply button
        driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-price.is-open > div > div:nth-child(6) > div > div > button")).click();
        System.out.println("Price filters added succesfully");
        
        
        //setting number of beds
        driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-enum.filter-bedrooms > a > div > svg")).click();
        
        driver.findElement(By.cssSelector("body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-enum.filter-bedrooms.is-open > div > ul > li.ui-link-tree-item.bedrooms-3 > a")).click();
        System.out.println("Number beds set to 3");
        
        
        //Getting number of adds
        String desc_adds=driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div/div/div/span")).getText();
        String[] desc_list = desc_adds.split(" ");
        String num_adds=desc_list[3];
        System.out.println("Number of Ads Found :"+num_adds);
        
        System.out.println();
        //printing prices of houses
        int number_adds=Integer.parseInt(num_adds);
        
        int house_num=1;
        for(int i=0;i<=number_adds/25;i++){
            //catch the filtered items
            List<WebElement> listHouses = driver.findElements(By.className("ui-item"));

            //print the price of each list item and the validation of the record is attached to it
            for (WebElement house : listHouses) {
                
                //Getting prices of houses
                String price_details=house.findElement(By.className("item-info")).getText();
                int price=Integer.parseInt(price_details.replace("Rs ",",").replace(",",""));
                
                //Printing prices without any validation
                System.out.println("Ad Number "+house_num+" price is "+price_details);
                
                
                //Getting number of beds
                String beds = house.findElement(By.xpath("//p[@class='item-meta']//span[1]")).getText();
                String[] beds_details = beds.split(" ");
                Integer num_beds = Integer.parseInt(beds_details[1]);
                
                //validation
                if(price>= 5000000 && price<=7500000){
                    System.out.println("Ad Number "+house_num+" price is with in given range");
                }else{
                    System.out.println("Ad Number "+house_num+" price is not within defined range");
                }
                
                if(num_beds==3){
                    System.out.println("Ad Number "+house_num+"is having 03 beds");
                }else{
                    System.out.println("Ad Number "+house_num+"is not having given number of beds");
                }
                 
                System.out.println();
                house_num++;
            }
            
            
            //
            
            try {
                String nextBtnLink = "//a[@class='col-6 lg-3 pag-next']";
                WebElement nextBtn = ((ChromeDriver) driver).findElementByXPath(nextBtnLink);
                nextBtn.click();
            } catch (Exception e) {
                System.out.println("End of Ads");
            }
            
        }

    }
        
    
    
    
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    
}

