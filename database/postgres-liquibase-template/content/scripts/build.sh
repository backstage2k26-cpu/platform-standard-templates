#!/bin/bash

set -e

IMAGE_NAME=liquibase-postgresql

TAG=${1:-latest}

echo "Building Docker image..."

docker build \
-t ${IMAGE_NAME}:${TAG} \
.

echo "Build completed."