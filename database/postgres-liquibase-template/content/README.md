# ${{ values.repoName }}

PostgreSQL schema migrations managed with Liquibase.

## Local validation

Start PostgreSQL:

```bash
docker compose up -d postgres
```

Run migrations:

```bash
docker compose run --rm liquibase update
```

Rollback the latest change set:

```bash
docker compose run --rm liquibase rollback-count 1
```

## Build the migration image

```bash
docker build -t ${{ values.imageRegistry }}/${{ values.repoName }}:latest .
```

The included GitHub Actions workflow publishes the image to GitHub Container Registry on pushes to `main`.

## Argo CD deployment

The Helm chart in `helm/` creates a Kubernetes Job annotated as an Argo CD hook. Argo CD runs the Job during sync and removes the completed hook before the next sync.

Create a database credentials secret in the target namespace:

```bash
kubectl create secret generic ${{ values.repoName }}-db \
  --from-literal=url='jdbc:postgresql://postgres-postgresql:5432/${{ values.databaseName }}' \
  --from-literal=username='postgres' \
  --from-literal=password='change-me'
```

Install with Helm:

```bash
helm upgrade --install ${{ values.repoName }} ./helm \
  --set image.repository=${{ values.imageRegistry }}/${{ values.repoName }} \
  --set image.tag=latest
```
