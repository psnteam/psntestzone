import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.stringtemplate.v4.compiler.STParser.namedArg_return as namedArg_return
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
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
import com.psn.beta.Login21 as Login21


def paymentInfos = [
				'payment1': ['email': 'sthao2@psnpay.com', 'acct': 'acct1', 'address': '345 test drive', 'city': 'madison', 'state': 'WI'
					, 'zip': '53711', 'acctType': '3', 'name': 'acct1', 'acctNum': '4111111111111111', 'expDate': '10/2020'
					, 'zip2': '53711', 'holder': '10102018', 'acct2': 'acctone', 'requiredField': 'test', 'amount': '6'],
				
				'payment2': ['email': 'sthao2@psnpay.com', 'acct': 'acct2', 'address': '345 test drive', 'city': 'madison', 'state': 'WI'
					, 'zip': '53711', 'acctType': '4', 'name': 'acct2', 'acctNum': '5111111111111118', 'expDate': '10/2020'
					, 'zip2': '53711', 'holder': '10102018', 'acct2': 'accttwo', 'requiredField': 'test', 'amount': '6'],
				
				'payment3': ['email': 'sthao2@psnpay.com', 'acct': 'acct3', 'address': '345 test drive', 'city': 'madison', 'state': 'WI'
					, 'zip': '53711', 'acctType': '5', 'name': 'acct3', 'acctNum': '6011111111111117', 'expDate': '10/2020'
					, 'zip2': '53711', 'holder': '10102018', 'acct2': 'acctthree', 'requiredField': 'test', 'amount': '6']
				]

WebUI.openBrowser('https://192.168.123.213/Search21.aspx')

WebUI.maximizeWindow()

searchForRT('RT24605')

testPayments(paymentInfo=null, cancelPayment=true, changePayment=false, newPaymentInfo=null)

searchForRT('RT24605')

testPayments(paymentInfo=null, cancelPayment=false, changePayment=true, paymentInfos['payment1'])

searchForRT('RT24605')

for (def payment in paymentInfos.keySet()) {
	testPayments(paymentInfos[payment])
}

//WebUI.closeBrowser()


def searchForRT(def RT) {
	WebUI.setText(findTestObject('RootPaymentPage/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodytxtClientID'), RT)
	
	WebUI.click(findTestObject('RootPaymentPage/Page_PSN - Your Complete PAYMENT BI/a_Search'))
	
	WebUI.delay(5)
	
	WebUI.click(findTestObject('RootPaymentPage/Page_PSN - Your Complete PAYMENT BI/a_Make a Payment (your info is'))
	
	WebUI.delay(5)
}

def testPayments(def paymentInfo=null, def cancelPayment=false, def changePayment=false, def newPaymentInfo=null) {
	def info = null
	
	if (changePayment) {
		info = newPaymentInfo
	} else {
		info = paymentInfo
	}
	
	if (info != null) {
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycadDetailstx'), 
		    info['email'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycadDetailstx_1'), 
		    info['acct'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycadDetailstx_2'), 
		    info['address'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycadDetailstx_3'), 
		    info['city'])
		
		WebUI.selectOptionByValue(findTestObject('RootPaymentPage/Page_Make a Payment/select_AKALAPARAZCACOCTDCDEFLG'), 
		    info['state'], true)
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycadDetailstx_4'), 
		    info['zip'])
		
		WebUI.selectOptionByValue(findTestObject('RootPaymentPage/Page_Make a Payment/select_-- Select Type --VISAMa'), 
		    info['acctType'], true)
		
		WebUI.delay(5)
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycapAddMethod'), 
		    info['name'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycapAddMethod_1'), 
		    info['acctNum'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycapAddMethod_2'), 
		    info['expDate'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycapAddMethod_3'), 
		    info['zip2'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycpdDetailsxm'), 
		    info['holder'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycpdDetailsxm_1'), 
		    info['acct2'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycpdDetailsxm_2'), 
			info['requiredField'])
		
		WebUI.setText(findTestObject('RootPaymentPage/Page_Make a Payment/input_ctl00cphBodycpdDetailstx'), 
		    info['amount'])
	}
	
	if (cancelPayment) {
		if (changePayment) {
			WebUI.click(findTestObject('RootPaymentPage/Page_Make a Payment/a_Continue'))
			
			WebUI.delay(5)
			
			WebUI.click(findTestObject('RootPaymentPage/Page_Verify a Payment/a_Cancel'))
		} else {
		
			WebUI.click(findTestObject('RootPaymentPage/Page_Make a Payment/a_Cancel'))
			
		}
		
		WebUI.delay(5)
		
		WebUI.navigateToUrl('https://192.168.123.213/Search21.aspx')
		
	} else if (changePayment) {
		WebUI.click(findTestObject('RootPaymentPage/Page_Make a Payment/a_Continue'))
		
		WebUI.delay(5)
		
		WebUI.click(findTestObject('RootPaymentPage/Page_Verify a Payment/a_Change Payment'))
		
		WebUI.delay(5)
		
		def test = ['email': 'sthao2@psnpay.com', 'acct': 'acct2', 'address': '345 test drive', 'city': 'madison', 'state': 'WI'
					, 'zip': '53711', 'acctType': '4', 'name': 'acct2', 'acctNum': '5111111111111118', 'expDate': '10/2020'
					, 'zip2': '53711', 'holder': '10102018', 'acct2': 'accttwo', 'requiredField': 'test', 'amount': '6']
		
		testPayments(paymentInfo=null, cancelPayment=true, changePayment=true, test)
		
	} else {
		WebUI.click(findTestObject('RootPaymentPage/Page_Make a Payment/a_Continue'))
		
		WebUI.delay(5)
		
		WebUI.click(findTestObject('RootPaymentPage/Page_Verify a Payment/a_Submit Payment'))
		
		WebUI.delay(5)
		
		WebUI.click(findTestObject('RootPaymentPage/Page_Receipt/a_Make Another Payment'))
		
		WebUI.delay(5)
	}
}
