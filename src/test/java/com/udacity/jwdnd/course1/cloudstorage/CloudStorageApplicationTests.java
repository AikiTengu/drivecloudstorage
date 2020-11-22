package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void getHomePage() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void wrongLogin() {
		String username = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);

		loginPage.login(username, password);

		try {
		Thread.sleep(1000);
		}
		catch (InterruptedException e){
		};
		Assertions.assertEquals("Invalid username or password", loginPage.loginResult());
	}

	@Test
	public void SignupLoginLogoutFLow() {
		String userName = "Teddy";
		String firstName = "Teddy";
		String lastName = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e){
		};

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName, password);

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
		};

		driver.get("http://localhost:" + this.port + "/home");
		HomePage homePage = new HomePage(driver);
		homePage.logout();

		};

	@Test
	public void getHomeAfterLogout() {
		String userName = "Teddy";
		String firstName = "Teddy";
		String lastName = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e){
		};

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName, password);

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
		};

		driver.get("http://localhost:" + this.port + "/home");
		HomePage homePage = new HomePage(driver);
		homePage.logout();

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	};


	@Test
	public void AddNotesTest() {
		String userName = "Teddy";
		String firstName = "Teddy";
		String lastName = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		HomePage homePage = new HomePage(driver);

		try {
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(userName, password);

			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/home");
			Thread.sleep(1000);

			homePage.addNewNote("1", "This is test note description");
			ResultsPage resultsPage = new ResultsPage(driver);
			resultsPage.getBackFromSaveNote();
			Thread.sleep(1000);
			homePage.nav2Notes();
			Thread.sleep(1000);

		}
		catch (InterruptedException e){
		};

		Assertions.assertEquals("1", homePage.getNoteTitle());
		Assertions.assertEquals("This is test note description", homePage.getNoteDescription());
	};

	@Test
	public void AddDeleteNotesTest() {
		String userName = "Teddy";
		String firstName = "Teddy";
		String lastName = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		try {
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(userName, password);

			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/home");
		}
		catch (InterruptedException e){
		};

		HomePage homePage = new HomePage(driver);


		try {
			homePage.addNewNote("1","This is test note description");
			ResultsPage resultsPage = new ResultsPage(driver);
			resultsPage.getBackFromSaveNote();
			Thread.sleep(1000);
			homePage.deleteNote();
			Thread.sleep(1000);
			resultsPage.getBackFromDelNote();
			Thread.sleep(1000);
			homePage.nav2Notes();
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {

		};

		Assertions.assertThrows(NoSuchElementException.class, () ->homePage.getNoteTitle());
	};

	@Test
	public void AddEditNotesTest() {
		String userName = "Teddy";
		String firstName = "Teddy";
		String lastName = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		try {
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(userName, password);

			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/home");
		}
		catch (InterruptedException e){
		};

		HomePage homePage = new HomePage(driver);


		try {
			homePage.addNewNote("1","This is test note description");
			ResultsPage resultsPage = new ResultsPage(driver);
			resultsPage.getBackFromSaveNote();
			Thread.sleep(1000);
			homePage.editNote("2", "Edited Note");
			Thread.sleep(1000);
			resultsPage.getBackFromEditNote();
			Thread.sleep(1000);
			homePage.nav2Notes();
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {

		};

		Assertions.assertEquals("2", homePage.getNoteTitle());
		Assertions.assertEquals("Edited Note", homePage.getNoteDescription());

	};

	@Test
	public void AddCredentialsTest() {
		String userName = "Teddy";
		String firstName = "Teddy";
		String lastName = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		HomePage homePage = new HomePage(driver);

		try {
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(userName, password);

			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/home");
			Thread.sleep(1000);

			homePage.addNewCred("google.com", "Teddy", "qweerty");
			ResultsPage resultsPage = new ResultsPage(driver);
			resultsPage.getBackFromSaveCred();
			Thread.sleep(1000);
			homePage.nav2Creds();
			Thread.sleep(1000);

		}
		catch (InterruptedException e){
		};

		Assertions.assertEquals("google.com", homePage.getCredUrl());
		Assertions.assertEquals("Teddy", homePage.getCredUser());
		Assertions.assertNotEquals("qweerty", homePage.getCredPassword());
	};

	@Test
	public void AddEditCredentialsTest() {
		String userName = "Teddy";
		String firstName = "Teddy";
		String lastName = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		try {
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(userName, password);

			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/home");
		}
		catch (InterruptedException e){
		};

		HomePage homePage = new HomePage(driver);


		try {
			homePage.addNewCred("google.com", "Teddy", "qweerty");
			ResultsPage resultsPage = new ResultsPage(driver);
			resultsPage.getBackFromSaveCred();
			Thread.sleep(1000);
			homePage.editCred("apple.com", "Teddy", "itty");
			Thread.sleep(1000);
			resultsPage.getBackFromEditCred();
			Thread.sleep(1000);
			homePage.nav2Creds();
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {

		};

		Assertions.assertEquals("apple.com", homePage.getCredUrl());
		Assertions.assertEquals("Teddy", homePage.getCredUser());
		Assertions.assertNotEquals("itty", homePage.getCredPassword());

	};

	@Test
	public void AddDeleteCredentialsTest() {
		String userName = "Teddy";
		String firstName = "Teddy";
		String lastName = "Teddy";
		String password = "qwerty";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		try {
			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login(userName, password);

			Thread.sleep(1000);
			driver.get("http://localhost:" + this.port + "/home");
		}
		catch (InterruptedException e){
		};

		HomePage homePage = new HomePage(driver);


		try {
			homePage.addNewCred("spring.io", "April", "1234");
			ResultsPage resultsPage = new ResultsPage(driver);
			resultsPage.getBackFromSaveCred();
			Thread.sleep(1000);
			homePage.deleteCred();
			Thread.sleep(1000);
			resultsPage.getBackFromDelCred();
			Thread.sleep(1000);
			homePage.nav2Creds();
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {

		};

		Assertions.assertThrows(NoSuchElementException.class, () ->homePage.getCredUrl());
	};
}


