Narrative:
As a user
I want to make sure that all functionality on Work Shifts page as expected

Lifecycle:
Before:
Given I am on the login page of application
And I login to application with username 'admin' and password 'admin123'
And I go to Work Shifts page

!-- https://jbehave.org/reference/latest/parameter-converters.html

Scenario: AC-1 Check that by default General and Twilight work shifts types are shown on work shifts page
Meta: @regression
Then I check that rows with values General, Twilight in WorkShift column are shown by default

Scenario: AC-2 Check that Work Shift field on Add work shift model requiired
Meta: @regression
When I click on Add Work Shift button
Then I click on Save button in Add Work Shift window
Then I check that Required error message is shown under Work Shift field

!-- TODO implement this scenario
Scenario: AC-3 Check that value in Hours Per Day field calculated propertly
!-- Meta: @regression @debug
!-- Then Using time picker I set 10:50 value into From filed
!-- Then Using time picker I set 18:20 value into To filed
!-- Then I check that 7.50 value calculated in Hours Per Day field
!-- Then Using time picker I set 8:05 value into From filed
!-- Then Using time picker I set 20:25 value into To filed
!-- Then I check that 12.33 value calculated in Hours Per Day field




