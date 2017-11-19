# Get the latest API files
autorest --input-file=http://localhost:5000/swagger/v1/swagger.json --java --output-folder="app/src/main/java/swagger" --license-header=NONE

# Build the application
gradlew build