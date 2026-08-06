FROM liquibase/liquibase:4.30

WORKDIR /liquibase

COPY changelog/ ./changelog/

COPY liquibase.properties .

COPY --chmod=755 docker/entrypoint.sh .

ENTRYPOINT ["./entrypoint.sh"]