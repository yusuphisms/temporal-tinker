# temporal-tinker
monorepo of trying out some Temporal orchestration ideas

# Local Temporal Cluster
I use the new `temporal` CLI to start a local temporal cluster. [Other ways can be found here](https://docs.temporal.io/kb/all-the-ways-to-run-a-cluster).

# Maven Setup
Run `mvn install -N` to first install the parent pom in the local Maven repository.

I followed the Java instructions for [starting a new Temporal maven archetype.](https://learn.temporal.io/getting_started/java/hello_world_in_java/?build-tool=maven)

mvn -B archetype:generate \
-DgroupId=dev.yusuph.temporal \
-DartifactId={name_of_new_package} \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DarchetypeVersion=1.4