# Interview Questions Generator

This backend service generates random 5 interview questions for every requested technology <sup>*</sup>.
For now the following technologies are available: java, Spring, Sql, JavaScript.

The default host and port of the app is `localhost:8080`.

In order to make basic request, the following GET REST call should be make:
`http://localhost:8080/questions`, which returns all available questions. 

<sup>*</sup> - technology of interest could be requested via REST API by adding URL parameters.
