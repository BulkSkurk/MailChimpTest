Feature: Creating user account

  Scenario Outline: Inputting information to create a user.
    Given I have opened "<browser>"
    Given I have written my "<email>"
    Given my Random username is "<length>" long
    Given I have written a "<password>"
    When I click the sign-up button
    Then We "<attempt>" to create an account

    Examples:
    |browser|email                  |length|password  |attempt |
    |chrome |adam@gurkmail.com      |10|swampMonster_21|Success|
    |edge   |adam@gurkmailen.com    |110|swampMonster_25|Fail  |
    |edge   |adam@gurkplaneten.com  |0|swampMonster_22|Fail    |
    |chrome |                       |12|swampMonster_22|Fail    |


    #Browser field uses chrome or edge depending on input.
    #Email field inputs info into the website email field.
    #Length generates a random username (letters) of the set numerical length.
    #To use/repeat a static username enter a 0 into the length field.
    #Password field inputs the chosen string into the Password field on the site.
    #Attempt field responds only to Success or Fail input.
    #Success means that the registration of the user succeeds is the desired result of the test.
    #Fail means that Failure of a new user is the desired outcome of the test.


