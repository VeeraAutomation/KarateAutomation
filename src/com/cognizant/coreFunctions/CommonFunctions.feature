Feature: CoreFunctions

@NavigateToLiefPage
Scenario: NavigateToLiefPage
# * call read('classpath:com/cognizant/utilities/UtilityFunctions.feature')
#* def checkNoErrorOnPage =
#"""
#function() {
#if(exists(objects.okObj)) { karate.fail(btn.name)}
#robot.active.screenshot()
#}
#"""
* robot { window: 'My CRM (Sample App)', fork: 'C:/Users/821988/OneDrive - Cognizant/Documents/Sumitomo/DemoApp/DemoApp/MyCRM.exe', highlight: true, highlightDuration: 500 }
* print windowExists('My CRM (Sample App)')
* robot.startRecordingScreen()
#* def bytes = robot.screenshot()
#* def file = karate.write(bytes, 'PAA0.png')