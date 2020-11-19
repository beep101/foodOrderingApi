This is the backend API for the food ordering app. API has two types of users, customers and restaurants. Customers don't need accounts and make their orders without authenticating themselves. Restaurants need to create accounts to upload their menus and check their orders.

API was developed using Spring Boot and Postgres and AWS S3 Buckets for data persistency. Architecture is split into 3 layers, controllers, services and repositories.

Controllers are components that expose API to the public. Controler parses requests and passes them to services.

Services handle core logic for requests. Services check authentication, check if the request is valid, then assemble services and repositories to do the job.

Repositories manage database access reading, writing and deleting entities.