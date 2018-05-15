# SecurityTemplate

Nicely scaffolded Spring Boot 2 template with security

## Features

This application has the following: 
- A UserService to create new users 
- A RoleServic to create new roles 
- A password encoder (BCrypt) 
- In memory users (can now be taken out) 
- This example shows how people with certain authorities can share access to paths. 
- It also shows how certain paths can be restricted to users with ONLY a given authority. 
See the WebSecurity config file for details. 
