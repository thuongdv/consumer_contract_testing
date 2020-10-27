- [Consumer contract testing example](#consumer-contract-testing-example)
  * [To run test](#to-run-test)
  * [To start Pact Broker](#to-start-pact-broker)
  * [To publish contract file to Broker](#to-publish-contract-file-to-broker)

# Consumer contract testing example
This is a consumer contract testing with Pact JVM.

Language: Java

Test framework: JUnit/JUnit5

Build tool: Gradle

## To run test
`./gradlew testJUnit --info` or `./gradlew testJUnit5 --info` 

The output looks like:
```
$ ./gradlew testJUnit5 --info
...<snip>...
> Task :testJUnit5
Finished generating test XML results (0.003 secs) into: C:\tmp\consumer_contract_testing\build\test-results\testJUnit5
Generating HTML test report...
Finished generating test html results (0.017 secs) into: C:\tmp\consumer_contract_testing\build\reports\tests\testJUnit5
Stored cache entry for task ':testJUnit5' with cache key 575ba479be19d05ba79e5f3d1b39037e
:testJUnit5 (Thread[Execution worker for ':',5,main]) completed. Took 5.452 secs.

Deprecated Gradle features were used in this build, making it incompatible with Gradle 7.0.
Use '--warning-mode all' to show the individual deprecation warnings.
See https://docs.gradle.org/6.1/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 7s
2 actionable tasks: 1 executed, 1 up-to-date
```

The contract files locates in the ~/build/pacts

## To start Pact Broker
You can clone the [Pack Broker](https://github.com/pact-foundation/pact-broker-docker), then CD to this project, then
type `docker-compose up -d`.

To check the Broker start successfully, open browser then navigate to http://localhost or https://localhost:8443

For more details of using docker, please google it.

## To publish contract file to Broker
```
TERM=cygwin ./gradlew pactPublish --info --build-cache
```