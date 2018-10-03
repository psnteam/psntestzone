import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor


def loginMode = [false, true]

WebUI.openBrowser('')

WebUI.maximizeWindow()

for (boolean mode in loginMode) {		
	def passwd = 'mytestpassword'
	def IDs = ['sthao@psnpay.com']
	
	for (String id in IDs) {
		boolean managerLogin = TestLinks.login21(mode, id, passwd)	
		
		if (managerLogin) {
			WebUI.click(findTestObject('TestLinks21/a_PSN Managers'))
			
			WebUI.click(findTestObject('TestLinks21/span_New Note'))
		}
		
		WebUI.click(findTestObject('TestLinks21/a_Return to current site'))
		
		if (managerLogin) {
			try {
				WebUI.click(findTestObject('ManagerLogin21/div_Customer is missing'))
				
				WebUI.click(findTestObject('ManagerLogin21/input_ctl00rwiOptionsCrbtCance'))
				
				WebUI.delay(TestLinks.DELAY)
			} catch (StepFailedException ex) {}
		} 
		
		try {
			WebUI.click(findTestObject('ManagerLogin21/a_Try our new site'))
		} catch (StepFailedException ex) {}
		
		if (managerLogin) {
			try {
				WebUI.click(findTestObject('ManagerLogin21/button_Cancel'))
			} catch (StepFailedException ex) {}
		}
		
		WebUI.delay(TestLinks.DELAY)
		
		WebUI.click(findTestObject('TestLinks21/a_Messages'))
		
		def startPages = ['OnlineStatements.aspx', 'MeterRead.aspx', 'Options.aspx']
		
		for (String startPage in startPages) {
			def menuItems = ['a_Payments': ['a_Make a Payment', 'a_Manage Auto-Pay', 'a_Manage Text Payment', 'a_Payment History', 'a_Payment Methods'],
							'a_Bills': ['a_View Bills', 'a_Billing History', 'a_View Usage'],
							'a_Meters': ['a_Meter Readings'],
							'a_Settings': ['a_Personal Info', 'a_Contact Preferences', 'a_Login Info', 'a_Combine Accounts', 'a_Billing Options', 'a_Manage Text Payment (1)'],
							'a_Support': ['a_Support Services'],
							'leftMenu': ['a_Personal Info_1', 'a_Contact Preferences_1', 'a_Login Info_1', 'a_Combine Accounts_1', 'a_Billing Options_1', 'a_Text Payments'],
							'bottomLinks': ['a_Security', 'a_Privacy', 'a_About PSN']
							]
			
			for (String key in menuItems.keySet()) {
				for (String value in menuItems.get(key).iterator()) {
					WebUI.navigateToUrl('https://beta.paymentservicenetwork.com/MyProfile/' + startPage)
					
					try {
						if (key.equals('leftMenu')) {
							def m = ['Options.aspx', 'MyProfile.aspx', 'ContactPref.aspx', 'LoginInfo.aspx', 'ManageAccounts.aspx', 'ManageSmsPayment.aspx']
							
							if (!m.contains(startPage)) {
								WebUI.navigateToUrl('https://beta.paymentservicenetwork.com/MyProfile/MyProfile.aspx')
							}
						} else {
							if (!key.equals('bottomLinks')) {
								WebUI.click(findTestObject('TestLinks21/' + key))
							}
						}
					} catch (StepFailedException ex) {
						println('Error: ' + key + " menu doesn't exist")
						
						break
					}
					
					try {
						if (!key.equals(value)) {
							WebUI.click(findTestObject('TestLinks21/' + value))
						}
					} catch (StepFailedException ex) {
						println('Error: ' + value + " doesn't exist")
			
						continue
					}
					
					//WebUI.delay(3)
				}
			}
		}
		
		((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("document.getElementById(\'ctl00_rbtLogoff\').click()")		
	}
}

try {
	((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("document.getElementById(\'ctl00_cmdLogoff\').click()")
} catch (Exception ex) {}

WebUI.closeBrowser()


class TestLinks {
	static final int DELAY = 5
	static boolean loggedIn = false
	
	static boolean login21(boolean managerLogin=true, String userID, String passwd) {
		if (managerLogin) {
			if (!TestLinks.loggedIn) {
				WebUI.navigateToUrl('https://beta.paymentservicenetwork.com/Manager/ManagerLogin.aspx')
	
				WebUI.setText(findTestObject('ManagerLogin21/input_ctl00ContentPlaceHolder1'), 'sthao')
	
				WebUI.setText(findTestObject('ManagerLogin21/input_ctl00ContentPlaceHolder1_1'), 'testitagain')
	
				WebUI.click(findTestObject('ManagerLogin21/a_Login'))
	
				TestLinks.loggedIn = true
				
				WebUI.delay(TestLinks.DELAY)
			}
			
			WebUI.navigateToUrl('https://beta.paymentservicenetwork.com/Manager/default.asp?stab=3')
	
			WebUI.click(findTestObject('ManagerLogin21/a_CustomerTracker'))
	
			WebUI.click(findTestObject('ManagerLogin21/a_View and Maintain Customer I'))
	
			WebUI.selectOptionByValue(findTestObject('ManagerLogin21/select_Last name'), 'a.Email', true)
	
			WebUI.setText(findTestObject('ManagerLogin21/input_Filter1'), userID)
	
			WebUI.click(findTestObject('ManagerLogin21/a_Refresh List'))
	
			WebUI.click(findTestObject('ManagerLogin21/input_Row1'))
	
			WebUI.click(findTestObject('ManagerLogin21/a_ViewEdit'))
			
			WebUI.delay(TestLinks.DELAY)
			
			try {
				WebUI.click(findTestObject('ManagerLogin21/div_Customer is missing'))
	
				WebUI.click(findTestObject('ManagerLogin21/input_ctl00rwiOptionsCrbtCance'))
			} catch (StepFailedException ex) {}
	
			WebUI.click(findTestObject('ManagerLogin21/a_Try our new site'))
	
			try {
				WebUI.click(findTestObject('ManagerLogin21/button_Cancel'))
				
				WebUI.delay(TestLinks.DELAY)
			} catch (StepFailedException ex) {}
	
		} else {
			WebUI.navigateToUrl('https://beta.paymentservicenetwork.com/Login21.aspx')
	
			WebUI.setText(findTestObject('Login21/input_ctl00cphBodyrtbLoginID'), userID)
	
			WebUI.setText(findTestObject('Login21/input_ctl00cphBodyrtbPassword'), passwd)
	
			WebUI.click(findTestObject('Login21/button_Log In'))
			
			try {
				((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("document.getElementById(\'ctl00_cphBody_txtOldPass\').value = '" + passwd + "'")
					
				((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("document.getElementById(\'ctl00_cphBody_txtNewPass\').value = 'letmein321'")
					
				((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("document.getElementById(\'ctl00_cphBody_txtVerifyPass\').value = 'letmein321'")
					
				((JavascriptExecutor) DriverFactory.getWebDriver()).executeScript("document.getElementById(\'ctl00_cphBody_cmdChange\').click()")
			} catch (Exception ex) {}
		}
		
		return managerLogin
	}
}

