Feature: IndividualPlan

@ignore @setup
Scenario:
* def data = read('classpath:com/cognizant/resources/Individual.json')
* print 'Setup'

@Vita @Sanity @Regression
Scenario: VitalityPlan
# Launch the application
* def readProperties =
"""
function(path) {
  var stream = karate.readAsStream(path);
  var props = new java.util.Properties();
  props.load(stream);
  return props;
}
"""
* print karate.properties['user.dir']
* def props = readProperties('file://'+karate.properties['user.dir']+'/configs.properties')
* print props.RunExcelData
@Vitality @Regression
Scenario Outline: VitalityPlan
* def sName = 'VitalityPlan'
* def testData = read('classpath:com/cognizant/resources/SYMD.json')
* print '<sName>'
* def sn ='<sName>'
* print sn
* print testData[sn].Gender
#* def runData = karate.setup().testData
#* print runData
#* print runData[sn].Gender
* call read('classpath:com/cognizant/testScenarios/Corporate.feature@data')

Examples:
| karate.setup().data |




