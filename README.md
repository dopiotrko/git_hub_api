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
{"code":404,"message":"User {user} not exist"}
```
For bigger rate limit token authentication implemented:
to authenticate, replace token variable in controller.GitHubHeader file.

## Run Spring Boot application
```
mvn spring-boot:run
```

