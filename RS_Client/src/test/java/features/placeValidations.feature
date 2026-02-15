Feature: Validating Plcae API's
Scenario: Verify if place is being successfully added using ADD Place API
	Given Add Place Payload
	When User call AddPlaceAPI with post http Request
	Then API call get success with status code 200
	And status in response body is "OK"