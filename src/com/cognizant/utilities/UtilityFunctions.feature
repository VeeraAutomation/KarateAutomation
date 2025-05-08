Feature: Utility Functions

Background:
* def coreFunctions = 'classpath:com/cognizant/reusables/CoreFunctions.feature@'
* def utilityFunctions = 'classpath:com/cognizant/reusables/UtilityFunctions.feature@'


@getObjects
Scenario: Read Object file
* def testData = Java.type('com.cognizant.utilities.ExcelHandling').jsonObject
#* def testData = read('file:C:/Users/821988/eclipse-workspace/SLC_Automation/data.json')
* def readObjects =
"""
function(path) {
  var stream = karate.readAsStream(path);
  var props = new java.util.Properties();
  props.load(stream);
  return props;
}
"""
* def objects = readObjects('classpath:com/cognizant/objects/CommonOR.properties')
* def scrollDown =
"""
function(count){
if(read RunExcelData == YES){
   testData[sName].InsuKanaName
}else {
for(let i=1;i<=count;i++){
  robot.input(Key.PAGE_DOWN);
  i++;
  }
  karate.log("Scoll down the page")
  robot.delay(2000)
}
}
"""
* def callCoreFunction =

"""
function(methodName){
karate.call(coreFunctions+methodName)
}
"""
 
