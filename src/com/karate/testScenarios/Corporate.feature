Feature: IndividualPlan

Background: 
* call read('classpath:com/cognizant/coreFunctions/CommonFunctions.feature@NavigateToLiefPage')
* def now = function(){ return java.lang.System.currentTimeMillis() }
@Corporate @Sanity6 @Regression
Scenario: VitalityPlan
# Launch the application
#* checkNoErrorOnPage()
#* callCoreFunction('Test')
#* scrollDown(2)
# Check the status of Vitality
#* print objects.textObj
# Sumbit the application
#* click(objects.okObj)
* print now
* print 'Success'
* robot.saveRecordingScreen("invoice.mp4",true)
@data
Scenario: Data
* def showData = 
"""
function(){ 
karate.log(testData[sn].Gender)
}
"""
* showData()
 @Check @Regression
Scenario: VitalityPlan2
* print karate.properties['user.dir']
# * print robot.root.locateAll('//window')
# * print robot.root.locate('//window{^My CRM}').name
* def name = karate.properties['user.dir']
* print java.io.File.separator
* def file = name.split(java.io.File.separator)[0]
* print file
* def result = karate.exec('xcopy "C:\\Users\\821988\\eclipse-workspace\\PDFComapre\\pom.xml" "C:\\Users\\821988\\eclipse-workspace\\SLC_Automation"')
* print result





