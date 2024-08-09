# Spring Boot 3 GITHUB REST API

It is simple RestApi app, to work with GitHub, with only one endpoint implemented.

Endpoint:
> http://localhost:8080/api/{user}

By replacing {user} with given github username, response is: all user non fork repositories, in format:

```
name={reposytory name}
login={login of repository owner}
branches= [ name={name of branch}
sha= {last commit sha of branch}
......
]
......
```
For not existing github user, response is:
```
{"code":404,"message":"Not Found"}
```
For bigger rate limit token authentication implemented. To authenticate, add optional parameter to endpoint:
> http://localhost:8080/api/{user}?token={your_token}

## Run Spring Boot application
```
mvn spring-boot:run
```

