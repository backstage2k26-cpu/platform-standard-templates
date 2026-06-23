# Local Setup

## Prerequisites

- Java 21+ (Temurin recommended)
- Maven 3.8+
- GCP CLI (`gcloud`) — optional, only needed for manual deploy

## Run Locally

```bash
# Clone the repo
git clone https://github.com/${{ values.repoOwner }}/${{ values.repoName }}.git
cd ${{ values.repoName }}

# Run the function locally on port 8080
mvn function:run
```

## Test Locally

```bash
# GET with query param
curl "http://localhost:8080?name=YourName"

# POST with JSON body
curl -X POST http://localhost:8080 \
  -H "Content-Type: application/json" \
  -d '{"name": "YourName"}'

# Expected response
# {"function":"${{ values.functionName }}","message":"Hello, YourName!","status":"success"}
```

## Build the JAR

```bash
mvn clean package

# Deployment JAR is at:
ls target/deployment.jar
```

## Run Unit Tests

```bash
mvn test
```

## Manual Deploy to GCP (optional)

```bash
# Authenticate
gcloud auth login
gcloud config set project ${{ values.gcpProjectId }}

# Deploy
gcloud functions deploy ${{ values.functionName }} \
  --gen2 \
  --runtime ${{ values.javaRuntime }} \
  --region ${{ values.gcpRegion }} \
  --trigger-http \
  --allow-unauthenticated \
  --entry-point com.example.CloudFunction \
  --source .

# Invoke the deployed function
gcloud functions call ${{ values.functionName }} \
  --region ${{ values.gcpRegion }} \
  --data '{"name":"World"}'
```

## IDE Setup (IntelliJ / VS Code)

- Import as Maven project
- Set SDK to Java 21
- Run `CloudFunctionTest` directly for fast feedback
