# ${{ values.functionName }} — GCP Cloud Function

This service was generated using the **GCP Cloud Function (Java) Backstage Template**.

## Purpose

This project provides a production-ready Google Cloud Function in Java with:

- Google Cloud Functions Framework (HTTP trigger)
- Maven build system with fat JAR packaging
- GitHub Actions CI/CD pipeline
- SonarCloud code quality scanning
- GCP deployment via `gcloud` CLI
- Backstage catalog registration
- TechDocs documentation

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Runtime | GCP Cloud Functions Gen 2 |
| Build Tool | Maven |
| CI/CD | GitHub Actions |
| Code Quality | SonarCloud |
| Documentation | Backstage TechDocs |

## GCP Details

| Property | Value |
|---|---|
| Function Name | `${{ values.functionName }}` |
| GCP Project | `${{ values.gcpProjectId }}` |
| Region | `${{ values.gcpRegion }}` |
| Runtime | `${{ values.javaRuntime }}` |
| Trigger | HTTP |

## Quick Start

```bash
# Run locally
mvn function:run

# Test locally
curl "http://localhost:8080?name=YourName"
```

## Repository Structure

```
${{ values.repoName }}/
├── src/
│   ├── main/java/com/example/
│   │   └── CloudFunction.java     # Main function entry point
│   └── test/java/com/example/
│       └── CloudFunctionTest.java # Unit tests
├── docs/                          # TechDocs documentation
├── .github/workflows/ci.yml       # CI/CD pipeline
├── catalog-info.yaml              # Backstage catalog entry
├── pom.xml                        # Maven build config
└── mkdocs.yml                     # TechDocs config
```
