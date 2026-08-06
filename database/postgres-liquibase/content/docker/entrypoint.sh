#!/bin/bash

set -e

echo "=================================="
echo " Liquibase Container Started"
echo "=================================="
echo "Running Liquibase..."

echo "Arguments received:"
echo "$@"

liquibase \
    --defaultsFile=/liquibase/liquibase.properties \
    --searchPath=/liquibase \
    "$@"