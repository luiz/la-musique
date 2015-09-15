Prepared for running in Wildfly. Steps:

1. Install Wildfly
2. Run it
3. Configure database properties in `pom.xml.example` and save it as `pom.xml`
4. Configure `environment.properties.example` and save it as `environment.properties`
5. Run `mvn wildfly:deploy -Pfirst-run` from inside the project's folder

After the first run, the `-Pfirst-run` flag mustn't be present.
