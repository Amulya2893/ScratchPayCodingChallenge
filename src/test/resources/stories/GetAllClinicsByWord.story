Narrative:
Test the  Get Clinics by Term api

Lifecycle:
Before:
Scope: SCENARIO
Given valid user details
When user tries to login
Then user should be able to login


Scenario: Get all Clinics by the term
Meta:
@Testing
Given user is authenticated
When user searches clinics with <term>
Then the user is able to retrieve all the clinics for the term
Examples:
|term|
|veterinary|
|pet|
|care|


Scenario: Get Clinics for invalid term
Meta:
@Testing
Given user is authenticated
When user searches clinics with  <term>
Then the user is unable to retrieve clinic details
Examples:
|term|
|*|
| |

Scenario: User tries to Get Clinics without any term
Meta:
@Testing
Given user is authenticated
When user searches clinics without term
Then the user is unable to retrieve clinic details


Scenario: Unauthenticated user tries to Get Clinics with terms
Meta:
@Testing
When unauthenticated user searches clinics with <term>
Then unauthenticated user is unable to retrieve clinic details
Examples:
|term|
|veterinary|