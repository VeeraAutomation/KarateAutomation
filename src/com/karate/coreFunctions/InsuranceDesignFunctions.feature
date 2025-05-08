Feature: CoreFunctions

@NavigateToLiefPage
Scenario: NavigateToLiefPage
* call read('classpath:com/cognizant/reusables/UtilityFunctions.feature')
* def checkNoErrorOnPage =
"""
function() {
if(exists(objects.okObj)) { karate.fail(btn.name)}
robot.active.screenshot()
}
"""
* robot { window: 'My CRM (Sample App)', fork: 'C:/Users/821988/Downloads/DemoApp/DemoApp/MyCRM.exe', highlight: true, highlightDuration: 500 }

@Test1
Scenario: Test
* print 'Success'