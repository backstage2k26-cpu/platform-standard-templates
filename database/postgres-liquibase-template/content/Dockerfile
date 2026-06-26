FROM liquibase/liquibase:4.29

USER root
RUN lpm add postgresql --global

USER liquibase
WORKDIR /liquibase

COPY liquibase/changelog ./changelog
COPY liquibase/liquibase.properties ./liquibase.properties

ENTRYPOINT ["liquibase"]
CMD ["--defaults-file=/liquibase/liquibase.properties", "update"]
