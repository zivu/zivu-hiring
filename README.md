# Interview Questions Generator

This backend service returns interview questions, that could be requested by technology (Java, Spring, JS, SQL)
and level (junior, middle, senior).

The default host and port of the app is `http://localhost:8080`.

In order to request interview questions for specific technology and level, run the following GET request:
`http://localhost:8080/questions/selected?level=junior&has_java=true&has_spring=false&has_sql=false&has_js=false`,
where appropriate level of complexity could be requested by specifying `level` URL parameter, that could be `junior`, `middle` or `senior`.
Add `has_java` URL parameter with boolean flag (true/false) to specify whether java questions should be returned.
Likewise, you could add `has_spring`, `has_sql` and `has_js` URL parameters.

Bear in mind that `level` is mandatory URL parameter and at least one technology should be specified in your request.

This app exposes also another endpoint that could be used to fetch all available interview questions:
`http://localhost:8080/questions/all`
