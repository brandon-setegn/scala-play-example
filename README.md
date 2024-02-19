# Scala Play Example with Tracing

This is the example project for making an API in Play.  This API will also use Open Telemetry tracing.


### Running

You need to download and install sbt for this application to run.

Once you have sbt installed, the following at the command prompt will start up Play in development mode:

```bash
sbt run
```

Play will start up on the HTTP port at <http://localhost:9000/>.   You don't need to deploy or reload anything -- changing any source code while the server is running will automatically recompile and hot-reload the application on the next HTTP request.


## Deploying

To build a linux compatible package in a tgz file, use the sbt `Universal / packageZipTarball` command.

This builds a .tgz file in the `target/universal` directory.  This can be extracted and run.
#### Extract
```bash
tar -xvzf play-scala-rest-api-example-1.0-SNAPSHOT.tgz   
```

#### Run Extracted Command
```bash
play-scala-rest-api-example-1.0-SNAPSHOT/bin/play-scala-rest-api-example -Dplay.http.secret.key={secret}   
```

More details from the Play Framework website here: <https://www.playframework.com/documentation/3.0.x/Deploying>

## Building the Docker Image and Running

After the tgz file is created above, a Docker image can be built that will run the application.

#### Example Build Command
```bash
docker build -t scala-play-example .
```

#### Example Run Command
```bash
docker run -p 9000:9000 -e APPLICATION_SECRET={secret} scala-play-example 
```

## Terraform Cloud Run Deploy

#### Example Publish Command
```bash
docker tag scala-play-example us-east1-docker.pkg.dev/{project}/cloud-run-example/scala-play-example
docker push us-east1-docker.pkg.dev/{project}/cloud-run-example/scala-play-example
```

