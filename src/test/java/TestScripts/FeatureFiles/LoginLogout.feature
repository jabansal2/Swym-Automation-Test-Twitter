Feature: Login into Twitter Account

Scenario Outline: HomePage Login and LogOut
Given Open Login Page 
When User Provides Credentials with <email>, <username> and <Password>
Then User login Successful and user is on Homepage
And User Logs out
And User closes the browser

Examples:

| email 												| username 				| Password 			|
|aqua19841bansal.jatin@gmail.com|aqua19841bansal	|	Password@1234 |

Scenario Outline: HomePage Login and Send a Tweet
Given Open Login Page 
When User Provides Credentials with <email>, <username> and <Password>
Then User login Successful and user is on Homepage
And User Sends a Tweet as <tweet>
And User Logs out
And User closes the browser
Examples:

| email 												| username 				| Password 			| tweet											|
|aqua19841bansal.jatin@gmail.com|aqua19841bansal	|	Password@1234 |Guitar Music Instrument		|

Scenario Outline: HomePage Login and Search a Profile
Given Open Login Page 
When User Provides Credentials with <email>, <username> and <Password>
Then User login Successful and user is on Homepage
And User Searches with Text <searchText> for profile <Profile>
And User confirms landing on the profile page
And User Logs out
And User closes the browser

Examples:

| email 												| username 				| Password 			| searchText		|Profile												|								
|aqua19841bansal.jatin@gmail.com|aqua19841bansal	|	Password@1234 |cricket				|CricketNDTV										|
|aqua19841bansal.jatin@gmail.com|aqua19841bansal	|	Password@1234 |India					|Vice President of India				|
