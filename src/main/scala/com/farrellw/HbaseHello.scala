package com.farrellw


import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.filter.PrefixFilter
import org.apache.hadoop.hbase.util.Bytes

object HbaseHello extends App {
  import scala.collection.JavaConverters._

  val config: Configuration = HBaseConfiguration.create

  // Set config values of where to connect to zookeeper.
  // For this example, myhbase is a docker container I'm running locally
  // Address forwarding mapped in /etc/hosts
  config.set("hbase.zookeeper.quorum", "myhbase")
  config.set("hbase.zookeeper.property.clientPort", "2181")

  val connection = ConnectionFactory.createConnection(config)

  // This example presumes you already have a table created named: myTable
  val table = connection.getTable(TableName.valueOf(Bytes.toBytes("myTable")))

  // Put three values to hbase table myTable. Note, must have column family with "fam_name" initiated
  println("Putting objects to a table")
  var put = new Put(Bytes.toBytes("groupA_1"))
  put.addColumn(Bytes.toBytes("col_fam"), Bytes.toBytes("col_name"), Bytes.toBytes("A1_value"))
  table.put(put)

  var putTwo = new Put(Bytes.toBytes("groupA_2"))
  putTwo.addColumn(Bytes.toBytes("col_fam"), Bytes.toBytes("col_name"), Bytes.toBytes("A2_value"))
  table.put(putTwo)

  var putThree = new Put(Bytes.toBytes("groupB_3"))
  putThree.addColumn(Bytes.toBytes("col_fam"), Bytes.toBytes("col_name"), Bytes.toBytes("B3_value"))
  table.put(putThree)

  // Get example
  println("Retrieving an object by its full row ID")
  var get = new Get(Bytes.toBytes("groupA_1"))
  var result = table.get(get)

  // Batch operation example
  println("Retrieving multiple objects by their full row IDs")
  val getOne = new Get(Bytes.toBytes("groupA_1"))
  val getTwo = new Get(Bytes.toBytes("groupB_3"))
  val batchGets: Array[Get] = Array[Get](getOne, getTwo)
  val results = table.get(batchGets.toList.asJava)

  //Scan w/ prefix example
  println("Retrieving objects by prefix")
  val scanner = new Scan
  val filter = new PrefixFilter(Bytes.toBytes("groupA"))
  scanner.setFilter(filter)

  var scan = table.getScanner(scanner)

  scan.asScala.foreach(result => {
    //Do something with each result
  })

  table.close()
  connection.close()
}