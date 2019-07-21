# Hbase - Scala Hello World

## Description
- This project is a scala example of connecting to Hbase.
- Includes examples for put, get, batch, scan w/ prefix filter.
- The project uses `sbt` as a build tool.

## Running the Program
- Clone the Repository
- `sbt` to enter into the shell
- `run`

## Connecting to Hbase
- The Hbase connection configuration is set as follows in the code:
 ```
    val config: Configuration = HBaseConfiguration.create
                                  
    // Set config values of where to connect to zookeeper.
    // For this example, myhbase is a docker container I'm running locally
    // Address forwarding mapped in /etc/hosts
    config.set("hbase.zookeeper.quorum", "myhbase")
    config.set("hbase.zookeeper.property.clientPort", "2181")
 ```
- If you are trying out Hbase locally, you can use Docker to run a standalone container

### Hbase in Docker 
The easiest Docker installation I found is here: https://github.com/sel-fish/hbase.docker. 
The installation requires adding the machine IP address that the container runs on to `/etc/hosts`. In the git repo provided, the maintainer runs it on a separate computer. You can just as easily run on your own machine and, in `/etc/hosts` add the declaration to the existing local host line: `127.0.0.1 localhost myhbase`
