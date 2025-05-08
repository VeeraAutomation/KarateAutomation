Feature: CoreFunctions

@NavigateToLiefPage
Scenario: NavigateToLiefPage
* print windowExists('My CRM (Sample App)')
* robot.startRecordingScreen()
#* def bytes = robot.screenshot()