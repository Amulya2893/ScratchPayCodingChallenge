Narrative:
Test the Authentication API

Lifecycle:

Scenario: Get Auth Token for valid credentials
Meta:
@Testing
Given valid user details
When user tries to login
Then user should be able to login

Scenario: Get Auth Token for invalid credentials
Meta:
@Testing
Given invalid user email details
When user tries to login
Then user should not be able to login


Scenario: Get Auth Token for invalid credentials
Meta:
@Testing
Given invalid user passcode details
When user tries to login
Then user should not be able to login


Scenario: Get Auth Token without sending credentials
Meta:
@Testing
When a user tries to login without credentials
Then user should not be able to login