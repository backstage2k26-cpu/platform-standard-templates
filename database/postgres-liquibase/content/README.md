# Liquibase PostgreSQL

A simple Liquibase project for managing PostgreSQL database schema changes using Maven, Docker, and GitHub Actions.

## Overview

This repository packages Liquibase migrations into a Docker image and publishes it automatically through GitHub Actions.

Pipeline flow:

```
Developer
    │
    ▼
Git Push / Merge (main)
    │
    ▼
Maven Build
    │
    ▼
Package Liquibase Artifact
    │
    ▼
Docker Build
    │
    ▼
Push Image to Docker Hub
    │
    ▼
Create Git Tag
```

---

## Project Structure

```
.
├── changelog/
│   ├── db.changelog-master.xml
│   ├── user-changelog.xml
│   └── *.yaml
│
├── docker/
│   └── entrypoint.sh
│
├── scripts/
│   ├── build.sh
│   ├── push.sh
│   └── version.sh
│
├── src/
│   └── assembly/
│       └── liquibase-package.xml
│
├── Dockerfile
├── liquibase.properties
├── pom.xml
└── README.md
```

---

## Components

### changelog/

Contains all Liquibase changelog files.

- `db.changelog-master.xml` – Master changelog
- `user-changelog.xml` – User changes
- YAML files – Liquibase configuration

---

### scripts/

Helper scripts for:

- Build
- Push
- Version generation

---

## GitHub Actions

The CI workflow performs:

1. Checkout source
2. Build using Maven
3. Build Docker image
4. Push image to Docker Hub
5. Create Git tag

---

## Docker Image

Example image:

```
liquibase/liquibase:latest
```

Every successful merge to `main` produces a versioned Docker image.

---

## Requirements

- Java 8+
- Maven
- Docker
- PostgreSQL
- GitHub Actions


## Repository Purpose

This project provides a reusable Liquibase Docker image that can be used in deployment pipelines to execute PostgreSQL database migrations in a consistent and automated manner.