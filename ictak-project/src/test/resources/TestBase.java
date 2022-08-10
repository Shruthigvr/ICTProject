
public class TestBase {
	WebDriver driver;
    public static Properties prop = null;
    String driverPath = System.getProperty("user.dir")+"/drivers/chromedriver.exe";
    public static void TestBase() {
        try {
        	//Below line creates an object of Properties called 'prop'
            prop = new Properties();
            //Below line creates an object of FileInputStream called 'ip'. 
            //Give the path of the properties file which you have created
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources"
                    + "/Config.properties");
            prop.load(ip);
        	} catch (FileNotFoundException e) {
        	e.printStackTrace();
        	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeMethod
    public void onSetup() {
        TestBase(); // for loading the configurations
        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")) {
        	System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();

        }
        else if (browserName.equals("firefox")) {
        	
        	System.setProperty("webdriver.gecko.driver", driverPath);
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));        
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize(); 
        
        
    }
    @AfterMethod
    public void testEnd() {
      driver.close();
  	  System.out.println("Test Ended");
  	  
    }

}
