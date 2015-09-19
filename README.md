# La musique

A toy project developed using progressive enhancement techniques

## Dependencies

- PostgreSQL 9.3+
- Maven (tested only with 3.3.1)
- JDK 8
- Wildfly 8+

## Running

1. Create a database for the project inside PostgreSQL (recommended name: `lamusique`)
2. Run Wildfly (`bin/standalone.sh` inside Wildfly's folder)
3. Configure database properties in `pom.xml.example` and save it as `pom.xml`
4. Run `mvn wildfly:deploy -Pfirst-run` from inside the project's folder. The `first-run` profile has some additional commands to configure the datasource in Wildfly.
5. Access `localhost:8080/lamusique-<VERSION>/`, where `<VERSION>` is the version declared inside `pom.xml`

After the first run, the `-Pfirst-run` flag **mustn't** be present.

## Songs & playlists

There is sample data declared in `test-data.sql`. To use it, **run the project a first time** (so that it can create the required tables), connect to your database in PostgreSQL and run the commands (or the entire file). For instance:

```
$ sudo -u postgres psql lamusique
psql (9.3.9)
Type "help" for help.

lamusique=# \i test-data.sql
```

**No musics or playlist arts are included**. Please include them in the project by putting them inside `src/main/webapp/songs` and `src/main/webapp/playlist-art`, respectively.
