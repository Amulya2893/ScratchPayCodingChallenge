Narrative:
Test get email address for given practiceId API


Lifecycle:
Before:
Scope: SCENARIO
Given valid user details
When user tries to login
Then user should be able to login

Scenario: Get a list of email addresses for a particular practiceId which has permission
Meta:
@Testing
Given user is authenticated
When user tries to get the list of email address for practice id 1
Then user should be able to get the list of emails

Scenario: Get a list of email addresses for a particular practiceId which doesn't have permission
Meta:
@Testing
Given user is authenticated
When user tries to get the list of email address for practice id 2
Then user should be prevented from getting the list with permission denied error message

Scenario:Get a list of email addresses for a invalid  practiceId
Meta:
@Testing
Given user is authenticated
When user tries to get the list of email address for practice id 91
Then user should be get a valid error message

Scenario: Get a list of email addresses without practiceId
Meta:
@Testing
Given user is authenticated
When user tries to get the list of email address without practiceId
Then user should be get a valid error message

Scenario: Unauthenticated user tries to get list of email addresses
Meta:
@Testing
When unauthenticated user tries to get the list of email address for practice id 1
Then user should be be prevented to get the list
