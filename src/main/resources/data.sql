INSERT INTO Question(id, question, answer, level, technology) VALUES (1, 'How API composition pattern works?', 'Implements a query that retrieves data from several services by querying each service via its API and combining the results', 'SENIOR', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (2, 'What is CQRS?', 'It splits a persistent data model and the modules that use it into two parts: the command side and the query side. The command side modules and data model implement create, update, and delete operations (abbreviated CUD—for example, HTTP POSTs, PUTs, and DELETEs). The query-side modules and data model implement queries (such as HTTP GETs). The query side keeps its data model synchronized with the command-side data model by subscribing to the events published by the command side', 'SENIOR', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (3, 'What is API Gateway pattern?', 'An API gateway is a service that’s the entry point into the application from the outside world. It’s responsible for request routing, API composition, and other functions, such as authentication.', 'SENIOR', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (4, 'What is Edge function?', 'Request-processing function, that comes as the first point of contact with backend, that might authenticate, authorize, cache, collect metrics or log requests.', 'SENIOR', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (5, 'What is Backends for frontends pattern?', 'Implements separate API gateway for each type of client (mobile, 3rd party, browser javascript)', 'SENIOR', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (6, 'What is type casting?', 'Assigning value to another type', 'JUNIOR', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (7, 'How would you explain what is a Stream?', 'A sequence of elements supporting sequential and parallel aggregate operations.', 'JUNIOR', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (8, 'What does it mean that streams are lazy?', 'Computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.', 'MIDDLE', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (9, 'Describe consumer-driven contract test?', 'Verifies that a service meets the expectations of its clients.', 'SENIOR', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (10, 'What is a web controller class?', 'Class that consists of a set of request handler methods. Each method implements a REST API endpoint. A method’s parameters represent values from the HTTP request, such as path variables. It typically invokes a domain service or a repository and returns a response object.', 'JUNIOR', 'SPRING');
INSERT INTO Question(id, question, answer, level, technology) VALUES (11, 'What is the purpose of @Mock annotation?', 'Creates mocked instance.', 'MIDDLE', 'JAVA');
INSERT INTO Question(id, question, answer, level, technology) VALUES (12, 'What annotation would you use to designate web controller: @Controller or @RestController and why?', '@RestController is convenient because it treats every methods responsse as web response body. No need to add additional annotation @ResponseBody', 'SENIOR', 'SPRING');
INSERT INTO Question(id, question, answer, level, technology) VALUES (13, 'How in Spring can we schedule asynchronous tasks?', 'One way is to add @EnableAsync with @Async on a scheduled tasks', 'SENIOR', 'SPRING');
INSERT INTO Question(id, question, answer, level, technology) VALUES (14, 'What @Profile("dev") annotation does?', 'Enables bean for "dev" profile', 'SENIOR', 'SPRING');
INSERT INTO Question(id, question, answer, level, technology) VALUES (15, 'WHERE name LIKE ''_tim%''. Explain this part of query', 'Looks for name that have "tim" after first character and might have some characters later', 'SENIOR', 'SQL');
INSERT INTO Question(id, question, answer, level, technology) VALUES (16, 'CHECK (salary>=3000). What Check constrains does?', 'It ensures specific values in a column, in this case that salary of added person should be more than 3000', 'SENIOR', 'SQL');
INSERT INTO Question(id, question, answer, level, technology) VALUES (17, 'Why do we need indices?', 'To ensure faster retrieval of data', 'SENIOR', 'SQL');
INSERT INTO Question(id, question, answer, level, technology) VALUES (18, 'What is hoisting?', 'Moving declarations of variables, class or function to the top', 'SENIOR', 'JAVA_SCRIPT');
INSERT INTO Question(id, question, answer, level, technology) VALUES (19, 'What is arrow function?', 'Syntax that similar in a sense to lambda in Java and helps declaring functions writing less code', 'SENIOR', 'JAVA_SCRIPT');
INSERT INTO Question(id, question, answer, level, technology) VALUES (20, 'When onload event occur?', 'When browser finishes loading the page', 'SENIOR', 'JAVA_SCRIPT');