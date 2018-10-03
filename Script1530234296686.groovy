import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.sun.jna.Structure.FFIType.size_t
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString
import org.openqa.selenium.Keys as Keys
import com.psn.beta.Login21 as Login21


email = Login21.getEmail()
phone = Login21.getPhone()

WebUI.openBrowser('https://192.168.123.213/Register21.aspx?acc=RT18878')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodytxtFirstName'), "Soua")

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodytxtLastName'), "Thao")

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodytxtReceiptNa'), "Soua Thao")

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbAddress'), "123 Anderson ST")

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbCity'), "City")

WebUI.click(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/span_rddlFakeInput rddlDefault'))

WebUI.click(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/li_WI'))

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbZip'), "53714-5566")

WebUI.click(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/span_rddlFakeInput rddlDefault_1'))

WebUI.click(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/li_USA'))

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbEmailAddr'), email)

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbConfirmEm'), email)

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbPassword'), 'letmein')

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbVerifyPas'), 'letmein')

WebUI.click(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/span_--- Please select one ---'))

WebUI.click(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/li_What was your first pets na'))

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbAnswer'), "Snow")

WebUI.setText(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/input_ctl00cphBodyrtbPhone'), phone)

WebUI.delay(5)

WebUI.click(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/button_SaveContinue'))

WebUI.click(findTestObject('Register21/Page_PSN - Your Complete PAYMENT BI/button_Log Out'))

WebUI.closeBrowser()

