# spring-cloud-contract

At this stage we have 
1. Written a failing consumer test
2. Implemented the consumer code
3. Have a test currently failing because it cannot connect to the Producer service on "http://localhost:8080/credit-scores"
4. We now have to decide whether to
  * Stub the producer
  * Use E2E testing and run the producer code locally
  * Use Consumer Driven Contracts
