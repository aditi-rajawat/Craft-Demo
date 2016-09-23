# craftdemo
SBG Platform Service Craft Demonstration

As part of enhancing the internal services of a company which are available to their employees, a Twitter like solution is proposed, where employees can tweet and have followers. 

Notes for execution and testing:
All RESTful APIs are secured using token authentication method, except for /user/login API.
In order to test any API, the tester must login first using /user/login API. Following two credentials can be used for user login.
("admin", "admin123") and ("arajawat", "aditi_91").
Actual login authentication to be done using LDAP service. Upon successfull authentication, an authorization token specific to the user is passed in the API response.
In order to test other APIs, the authorization token generated upon successful user login must be used in Authorization field of HTTP header of a request. Other APIs are authorized using HTTP basic authentication.




