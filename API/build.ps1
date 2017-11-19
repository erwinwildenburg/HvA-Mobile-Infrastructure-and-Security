# Build the API
dotnet publish --configuration Release -o obj/Docker/publish
Remove-Item obj/Docker/publish/appsettings.json

# Build the docker container
docker build -t psdscc.azurecr.io/hvamis .

# Upload to Azure
docker push psdscc.azurecr.io/hvamis
