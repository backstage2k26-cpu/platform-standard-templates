# Architecture

## High-Level Overview

```
Client / Event Source
        │
        ▼
 GCP Cloud Functions (Gen 2)
        │
        ▼
  CloudFunction.java
   ├── CORS handling
   ├── Request parsing
   ├── Business logic (process())
   └── JSON response
```

## Components

**Entry Point**
- `CloudFunction.java` — implements `HttpFunction`, handles the full request lifecycle
- CORS headers pre-configured for browser clients
- Structured JSON responses using Gson

**Business Logic**
- `process()` method in `CloudFunction.java` — extend this with your domain logic
- Designed to be easily split into separate service classes as the function grows

**Testing**
- `CloudFunctionTest.java` — unit tests using JUnit + Mockito
- Mocks `HttpRequest` / `HttpResponse` so no GCP runtime needed locally

## Deployment Model

- **Gen 2** Cloud Functions backed by Cloud Run
- Packaged as a fat JAR via `maven-shade-plugin`
- Deployed directly from source using `gcloud functions deploy`
- HTTP trigger with optional authentication (configurable via IAM)

## Scaling

- Cloud Functions Gen 2 auto-scales to zero and handles concurrent requests
- Minimum instances can be set via `--min-instances` flag in the deploy step to avoid cold starts

## Security

- Secrets (GCP SA key, Sonar token) stored as GitHub Actions secrets
- IAM controls access to the deployed function
- Authentication can be enforced by removing `--allow-unauthenticated` and using service accounts

---

This architecture is intentionally simple and can be extended — add a service layer, integrate Cloud Firestore, Pub/Sub, or Secret Manager as the team grows.
