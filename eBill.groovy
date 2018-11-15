import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.lang.Throwable.WrappedPrintWriter

import java.nio.file.Path as Path
import java.nio.file.Files as Files
import java.nio.file.Paths as Paths
import java.io.File as File
import java.nio.file.LinkOption as LinkOption

import javax.crypto.extObjectInputStream

import org.testng.reporters.EmailableReporter

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

enum EbillOptions {OptOutofElectronicBills, OptIntoElectronicBills, OptOutofPaperBills, OptIntoPaperBills}

def i_file = "C:/Users/sthao/Desktop/Katalon Studio/Projects/files/i_file.txt"
def o_file = "C:/Users/sthao/Desktop/Katalon Studio/Projects/files/o_file.txt"

FileWriter writer = new FileWriter(o_file, true)

Path path = Paths.get(i_file)

if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {

	WebUI.openBrowser('')
	
	WebUI.navigateToUrl('https://beta.paymentservicenetwork.com/Manager/ManagerLogin.aspx')
	
	WebUI.maximizeWindow()
	
	def emails = loadEmails(i_file)	//file must be in the same dir as the script else provide the dir where file is found.
	
	for (String email in emails) {
		eBillSignUp(email, EbillOptions.OptOutofElectronicBills)
	} 
} else {
	println("$i_file doesn't exist")
}

writer.close()

//WebUI.closeBrowser()


def loadEmails(String dir=null, String file) {
	def emails
	
	try {
		if (dir == null) {
			emails = new File(file).collect {it}
		} else {
			emails = new File(dir, file).collect {it}
		}
	} catch (FileNotFoundException ex) {
		println("File doesn't exist")
	}
	
	return emails
}


def eBillSignUp(String email, EbillOptions option) {
	try {
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_CustomerTracker'))
		
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_View and Maintain Customer I'))
		
		WebUI.selectOptionByValue(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/select_Last name'), 
		    'a.Email', true)
		
		WebUI.setText(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/input_Filter Options_Filter1'), email)
		
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Refresh List'))
	
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/input_Options_Row1'))
	
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_ViewEdit'))
	
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/input_Sign up for EBills_ctl00'))
	
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/span_OK'))
	
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_My Profile'))
		
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Billing Options'))
	
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
			default:
				println("Invalid option!")
		}
	
		WebUI.click(findTestObject('Object Repository/eBills/Page_PSN - Your Complete PAYMENT BI/a_Log Off'))
		
	} catch (StepFailedException ex) {
		writer.write("Error: $ex")
		writer.write("Email: $email")
	}
}
