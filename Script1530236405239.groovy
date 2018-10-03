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

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.eclipse.persistence.internal.oxm.record.json.JSONParser.value_return
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor


def RTs = ['RT18878', 'RT24605', 'RT17076', 'RT24274']

//Select the RT you want to test by changing the value, 0 is the 1st and 1 is the 2nd and so for.
def i = 2

def accounts = null

//WebUI.openBrowser('https://192.168.123.213/AuthName21.aspx?AccID=' + RTs[i])
WebUI.openBrowser('https://beta.paymentservicenetwork.com/AuthName21.aspx?AccID=' + RTs[i])

WebUI.maximizeWindow()

def badAcct = ['131865-001', 'AARON', 'MAYER', '228304-001', 'AX TC INDUSTRIAL II LP'], goodAcct = null

if (RTs[i].equals('RT17076')) {
	goodAcct = ['17055982-22', 'AGNESIAN', 'WAUPUN', '16090258-23', 'ANDING INVESTMENTS LLC']
} else if (RTs[i].equals('RT18878')) {
	goodAcct = ['0100003567011', 'ADAM & REBECCA1', 'WICKLUND', '3202939530', 'BLONIGEN']
} else if (RTs[i].equals('RT24605')) {
	goodAcct = ['170559829022', 'Soua', 'Thao', '160902580023', 'Soua Thao']
}

switch (RTs[i]) {
	case 'RT17076':		
	case 'RT18878':		
	case 'RT24605':		
			
		accounts = [
					'acct1': [
							'input_ctl00cphBodyanfCustAuthx': badAcct[0],
							'input_ctl00cphBodyanfCustAuthx_1': badAcct[1],
							'input_ctl00cphBodyanfCustAuthx_2': badAcct[2]
							],
					'acct2': [
							'input_ctl00cphBodyanfCustAuthx': '',
							'input_ctl00cphBodyanfCustAuthx_1': '',
							'input_ctl00cphBodyanfCustAuthx_2': '',
							'input_ctl00cphBodyanfCustAuthx_3': badAcct[3],
							'input_ctl00cphBodyanfCustAuthx_4': badAcct[4]
							],
					'acct3': [
							'input_ctl00cphBodyanfCustAuthx_3': '',
							'input_ctl00cphBodyanfCustAuthx_4': '',
							'input_ctl00cphBodyanfCustAuthx': goodAcct[0],
							'input_ctl00cphBodyanfCustAuthx_1': goodAcct[1],
							'input_ctl00cphBodyanfCustAuthx_2': goodAcct[2]
							],
					'acct4': [
							'input_ctl00cphBodyanfCustAuthx': '',
							'input_ctl00cphBodyanfCustAuthx_1': '',
							'input_ctl00cphBodyanfCustAuthx_2': '',
							'input_ctl00cphBodyanfCustAuthx_3': goodAcct[3],
							'input_ctl00cphBodyanfCustAuthx_4': goodAcct[4]
							]
					]

		setText(accounts)
		
		try {
			//WebUI.click(findTestObject('AuthName21/Page_PSN - Your Complete PAYMENT BI/a_Register and make a payment'))
			
			WebUI.click(findTestObject('AuthName21/Page_PSN - Your Complete PAYMENT BI/a_Make a one-time payment'))
			
			WebUI.delay(5)
		} catch (StepFailedException ex) {
			try {
				WebUI.click(findTestObject('AuthName21/Page_PSN - Your Complete PAYMENT BI/Cancel_Payment'))
			} catch (StepFailedException ex2) {}
		}
		
		break
		
	case 'RT24274':
	
		accounts = [
					'acct1': [
							'input_ctl00cphBodyanfCustAuthx2': '14026680-35',
							'input_ctl00cphBodyanfCustAuthx (1)': 'ALFRED',
							'input_ctl00cphBodyanfCustAuthx (2)': 'RAMIREZ',
							'input_ctl00cphBodyanfCustAuthx (3)': '240 1/2 S DRUMMOND ST'								
							],
					'acct2': [
							'input_ctl00cphBodyanfCustAuthx2': '',
							'input_ctl00cphBodyanfCustAuthx (1)': '',
							'input_ctl00cphBodyanfCustAuthx (2)': '',
							'input_ctl00cphBodyanfCustAuthx (3)': '',
							'input_ctl00cphBodyanfCustAuthx (4)': '16090258-23',
							'input_ctl00cphBodyanfCustAuthx (5)': 'ANDING INVESTMENTS LLC',
							'input_ctl00cphBodyanfCustAuthx (6)': '1122 W MAIN ST'
							],
					'acct3': [
							'input_ctl00cphBodyanfCustAuthx (4)': '',
							'input_ctl00cphBodyanfCustAuthx (5)': '',
							'input_ctl00cphBodyanfCustAuthx (6)': '',
							'input_ctl00cphBodyanfCustAuthx2': '131865-001',
							'input_ctl00cphBodyanfCustAuthx (1)': 'AARON',
							'input_ctl00cphBodyanfCustAuthx (2)': 'MAYER',
							'input_ctl00cphBodyanfCustAuthx (3)': '4100 QUAKER LN N'
							],
					'acct4': [
							'input_ctl00cphBodyanfCustAuthx2': '',
							'input_ctl00cphBodyanfCustAuthx (1)': '',
							'input_ctl00cphBodyanfCustAuthx (2)': '',
							'input_ctl00cphBodyanfCustAuthx (3)': '',
							'input_ctl00cphBodyanfCustAuthx (4)': '228304-001',
							'input_ctl00cphBodyanfCustAuthx (5)': 'AX TC INDUSTRIAL II LP',
							'input_ctl00cphBodyanfCustAuthx (6)': '1000 BERKSHIRE LN N'
							]
					]
		
		setText(accounts)
		
		try {
			WebUI.click(findTestObject('AuthName21/Page_PSN - Your Complete PAYMENT BI/1/a_Register and make a payment'))
			
			//WebUI.click(findTestObject('AuthName21/Page_PSN - Your Complete PAYMENT BI/1/a_Make a one-time payment'))
			
			WebUI.delay(3)
		} catch (StepFailedException ex) {
			try {
				WebUI.click(findTestObject('AuthName21/Page_PSN - Your Complete PAYMENT BI/Cancel_AuthName_Search'))		
			} catch (StepFailedException ex2) {}
		}
		
		break
}

def setText(def accounts) {
	try {
		for (def acct in accounts.keySet()) {
			
			for (def key in accounts.get(acct).keySet()) {
				WebUI.setText(findTestObject('AuthName21/Page_PSN - Your Complete PAYMENT BI/' + key), accounts[acct][key])
			}
			
			WebUI.click(findTestObject('AuthName21/Page_PSN - Your Complete PAYMENT BI/a_Search'))
			
			WebUI.delay(3)
		}
	} catch (Exception ex) {}
}

//WebUI.closeBrowser()