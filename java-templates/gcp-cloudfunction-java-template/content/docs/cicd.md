# CI/CD Pipeline

## Pipeline Overview

The GitHub Actions workflow (`ci.yml`) runs on `workflow_dispatch` and has an optional `deploy` input flag.

```
setversion → build → sonar → deploy (if deploy=true)
```

## Jobs

### setversion
- Reads current version from `pom.xml`
- Increments the patch version (e.g. `1.0.0` → `1.0.1`)
- Uploads the updated `pom.xml` as an artifact for downstream jobs

### build
- Downloads the updated `pom.xml`
- Runs `mvn clean verify` (compiles + unit tests)
- Uploads `target/deployment.jar` for the deploy job

### sonar
- Runs SonarCloud static analysis
- Auto-creates the project in SonarCloud if it doesn't exist
- Requires `SONAR_TOKEN` GitHub secret

### deploy
- Only runs when `deploy` input is set to `true`
- Authenticates to GCP using `GCP_SA_KEY` secret
- Deploys the function using `gcloud functions deploy` (Gen 2)
- Prints the deployed function URL

## Required GitHub Secrets

| Secret | Description |
|---|---|
| `GCP_SA_KEY` | GCP Service Account JSON key with Cloud Functions deployer role |
| `SONAR_TOKEN` | SonarCloud authentication token |

## Required GitHub Variables

| Variable | Description | Example |
|---|---|---|
| `GCP_PROJECT_ID` | GCP project ID | `my-project-123` |
| `GCP_REGION` | Deployment region | `us-central1` |
| `FUNCTION_NAME` | Cloud Function name | `${{ values.functionName }}` |
| `JAVA_RUNTIME` | GCP Java runtime | `java21` |

## Triggering a Deploy

1. Go to **Actions** → **CI/CD - GCP Cloud Function**
2. Click **Run workflow**
3. Set `deploy` to `true`
4. Click **Run workflow**
