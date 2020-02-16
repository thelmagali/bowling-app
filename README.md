# bowling-app
Java application that simulates a bowling game and prints out the final score.

## Compile
`mvn clean package`

## Run integration tests
`mvn failsafe:integration-test`

## Run
  <code>java -jar bowling-app.jar <name_of_input_file></code>
<p>To see quickly how the output looks, you can run the app for the sample input already included in the package</p>
  <code>java -jar bowling-app.jar sample-input</code>

## Input
In the input file each line represents a chance. The information provided for each chance is the name of the player and the number of pitfalls. See this [file](https://github.com/thelmagali/bowling-app/blob/master/src/main/resources/sample-input).

