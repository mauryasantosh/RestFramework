#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Validate place API
  @AddPlace
  Scenario Outline: Varfiy place is being add through API.
    Given Add place playload with "<name>" "<lan>" "<add>"
    When user calls "AddPlaceAPI" with "Post" Http request        
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And  verify place_ID created with "<name>" using "GetPlaceAPI"
   

   Examples:
   		|name|lan|add|
   		|QA|java|WTC|
   		|Automation|python|NYC|
	@DeletePlace
	Scenario: Verify Delete place API is working
		Given deletePlace payload.
		When user calls "DeletePlaceAPI" with "Post" Http request
		Then the API call got success with status code 200
    And "status" in response body is "OK"
		
		
