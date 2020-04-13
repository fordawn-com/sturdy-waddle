from pyspark import SparkContext

spark = SparkSession\
.builder\
.appName("My_Spark")\
.master("local[*]")\
.config("spark.cassandra.connection.host", "10.11.12.14,10.11.12.20,10.11.12.23")\
.enableHiveSupport()\
.getOrCreate()

dfFormCassandra = spark.read
      .format("org.apache.spark.sql.cassandra")
      .option("keyspace", "aarontest").option("table", "c_staff").load()
