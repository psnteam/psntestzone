import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import javax.crypto.extObjectInputStream

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


/* 
 * Credentials must be keyed in manually.
 */

def emails = ['2620170691736749612@psnpay.com', '2620170661711702824@psnpay.com', '2220170884912950242@psnpay.com']

enum EbillOptions {OptOutofElectronicBills, OptIntoElectronicBills, OptOutofPaperBills, OptIntoPaperBills}

WebUI.openBrowser('')

WebUI.navigateToUrl('https://beta.paymentservicenetwork.com/manager/ManagerLogin.aspx')

WebUI.maximizeWindow()

for (def email in emails) {
	eBillSignUp(email, EbillOptions.OptIntoElectronicBills)
} 

public void eBillSignUp(def email, def option) {
	
	WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_CustomerTracker'))
	
	WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_View and Maintain Customer I'))
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/select_Last name'), 
	    'a.Email', true)
	
	WebUI.setText(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/input_Filter Options_Filter1'), email)
	
	WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Refresh List'))
	
	WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/input_Options_Row1'))
	
	WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_ViewEdit'))
	
	try {
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/input_Sign up for EBills_ctl00'))
	} catch (StepFailedException ex) {}
	
	try {
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/span_OK'))
	} catch (StepFailedException ex) {}
	
	WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_My Profile'))
	
	WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Billing Options'))
	
	try {
		switch (option) {
			case EbillOptions.OptOutofElectronicBills:
				WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Opt Out of Electronic Bills'))
				break
			case EbillOptions.OptOutofPaperBills:
				WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Opt Out of Paper Bills'))
				break
			case EbillOptions.OptIntoElectronicBills:
				WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Opt Into Electronic Bills'))
				break
			case EbillOptions.OptIntoPaperBills:
				WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Opt Into Paper Bills'))
				break
		}		
	} catch (StepFailedException ex) {}
	
	WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Log Off'))
}

//WebUI.closeBrowser()

