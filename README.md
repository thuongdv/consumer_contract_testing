# Consumer contract testing example
This is a consumer contract testing with Pact JVM.

Language: Java

Test framework: JUnit

Build tool: Gradle

## To run test
`./gradlew test --info`

The output looks like:
```
$ ./gradlew test --info
...<snip>...
> Task :test
...<snip>...
com.demo.contract.TemplateContractTest > successfulResponseContractTest STANDARD_OUT
    Request method:     GET
    Request URI:        http://kubernetes.docker.internal:52724/api/v1/users
    Proxy:                      <none>
    Request params:     <none>
    Query params:       <none>
    Form params:        <none>
    Path params:        <none>
    Headers:            Accept=*/*
    Cookies:            <none>
    Multiparts:         <none>
    Body:                       <none>

Gradle Test Executor 7 finished executing tests.

> Task :test
Finished generating test XML results (0.004 secs) into: C:\tmp\consumer_contract_testing\build\test-results\test
Generating HTML test report...
Finished generating test html results (0.009 secs) into: C:\tmp\consumer_contract_testing\build\reports\tests\test
:test (Thread[Execution worker for ':',5,main]) completed. Took 5.846 secs.

Deprecated Gradle features were used in this build, making it incompatible with Gradle 7.0.
Use '--warning-mode all' to show the individual deprecation warnings.
See https://docs.gradle.org/6.1/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 8s
2 actionable tasks: 2 executed
```

The contract files locates in the ~/build/pacts

## To start Pact Broker
You can clone the [Pack Broker](https://github.com/pact-foundation/pact-broker-docker), then CD to this project, then
type `docker-compose up -d`.

To check the Broker start successfully, open browser then navigate to http://localhost or https://localhost:8443

For more details of using docker, please google it.