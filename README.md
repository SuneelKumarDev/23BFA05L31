# URL Shortener - Backend
This is a basic Spring Boot project to create short URLs, handle redirection, and track click analytics.

# How to run
Install Java 17 or above and Maven on your system.

# Clone the project:

git clone https://github.com/yourusername/your-repo-name.git
cd backend/shortener
Open CustomLoggerService.java and replace your_token_here with your actual bearer token.

# Start the application:

mvn spring-boot:run
The server will run at http://localhost:8080

# API Endpoints
POST /shorturls – Create a short URL
Example request body:

json

{
  "longUrl": "https://google.com",
  "customCode": "mygoogle",     // optional
  "validity": 10              // optional, in minutes
}
GET /{shortCode} – Redirects to the original URL

GET /shorturls/{shortCode} – Returns analytics for the given short URL

You can test these endpoints using Postman or any API testing tool.
