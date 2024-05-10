Feature: Logging in and adding a user address
  Scenario Outline: Successful  login and adding the address
    Given User is on the login page
    And   clicks the icon Sign in
    When User logs in with
      |email   |password   |
      |<email> | <password>|
    And  Clicks to Sign in
    Then Clicks to addresses and navigate to the addresses page

    And Clicks to the create new address button
    When Fill out the form with data
      |alias  | address   | city   | zip  | country  | phone   |
      |<alias>|  <address>|  <city>| <zip>| <country>|  <phone>|

    Then Check the data in the added address is correct-check alias,address,city,zip,country,phone
    Then Clicks save the new address



    Examples:
      |email             | password  |alias | address| city  | zip   | country | phone     |
      |janusz.szot@wp.pl |  Janusz   | dom  |ul.Cicha|Gdansk |80-102  |Polska  |710-100-100|







