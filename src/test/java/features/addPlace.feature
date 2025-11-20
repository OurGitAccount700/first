Feature: Verify add place API functionalit 
Scenario: verify add place is being addedd sucesfully
Given add addplace api payload
When User calls "addplace" api with post call
Then Success reposnse should retrieved with 200 statuc code
And Verify "status" field value is "OK"

