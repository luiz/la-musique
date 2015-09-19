# La musique

A toy project developed using progressive enhancement techniques

## Dependencies

- PostgreSQL 9.3+
- Maven (tested only with 3.3.1)
- JDK 8
- Wildfly 8+

## Running

1. Install Wildfly
2. Run it (`bin/standalone.sh` inside Wildfly's folder)
3. Configure database properties in `pom.xml.example` and save it as `pom.xml`
4. Run `mvn wildfly:deploy -Pfirst-run` from inside the project's folder. The `first-run` profile has some additional commands to configure the datasource in Wildfly.
5. Access `localhost:8080/lamusique-<VERSION>/`, where `<VERSION>` is the version declared inside `pom.xml`

After the first run, the `-Pfirst-run` flag **mustn't** be present.
