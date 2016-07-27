
config and default value
#####################consumer#####################
group.id
zookeeper.connect
consumer.id	                        null
socket.timeout.ms	                30 * 1000
socket.receive.buffer.bytes	        64 * 1024
fetch.message.max.bytes	            1024 * 1024
auto.commit.enable	                TRUE
auto.commit.interval.ms	            60 * 1000
queued.max.message.chunks	        10
rebalance.max.retries	            4
fetch.min.bytes	                    1
fetch.wait.max.ms	                100
rebalance.backoff.ms	            2000
refresh.leader.backoff.ms	        200
auto.offset.reset	                largest
consumer.timeout.ms	                -1
client.id	                        group id value
zookeeper.session.timeout.ms 	    6000
zookeeper.connection.timeout.ms	    6000
zookeeper.sync.time.ms 	            2000


#####################producer#####################
metadata.broker.list
request.required.acks	            0
request.timeout.ms	                10000
producer.type	                    sync
serializer.class	                kafka.serializer.DefaultEncoder
key.serializer.class
partitioner.class	                kafka.producer.DefaultPartitioner
compression.codec	                none
compressed.topics	                null
message.send.max.retries	        3
retry.backoff.ms	                100
topic.metadata.refresh.interval.ms	600 * 1000
queue.buffering.max.ms	            5000
queue.buffering.max.messages	    10000
queue.enqueue.timeout.ms	        -1
batch.num.messages	                200
send.buffer.bytes	                100 * 1024
client.id	                        ""


#####################topic#####################
cleanup.policy	                    delete
delete.retention.ms	                86400000 (24 hours)
flush.messages	                    None
flush.ms	                        None
index.interval.bytes	            4096
max.message.bytes	                1,000,000
min.cleanable.dirty.ratio	        0.5
retention.bytes	                    None
retention.ms	                    7 days
segment.bytes	                    1 GB
segment.index.bytes	                10 MB
segment.ms	                        7 days


#####################broker#####################
broker.id
log.dirs                            /tmp/kafka-logs
port	                            6667
zookeeper.connect	                null
message.max.bytes	                1000000
num.network.threads	                3
num.io.threads	                    8
background.threads	                4
queued.max.requests	                500
host.name	                        null
advertised.host.name	            null
advertised.port	                    null
socket.send.buffer.bytes	        100 * 1024
socket.receive.buffer.bytes         100 * 1024
socket.request.max.bytes	        100 * 1024 * 1024
num.partitions	                    1
log.segment.bytes	                1024 * 1024 * 1024
log.roll.hours	                    24 * 7
log.cleanup.policy	                delete
log.retention.{minutes,hours}	    7 days
log.retention.bytes	                -1
log.retention.check.interval.ms	    5 minutes
log.cleaner.enable	                FALSE
log.cleaner.threads	                1
log.cleaner.io.max.bytes.per.second	None
log.cleaner.dedupe.buffer.size	    500*1024*1024
log.cleaner.io.buffer.size	        512*1024
log.cleaner.io.buffer.load.factor	0.9
log.cleaner.backoff.ms	            15000
log.cleaner.min.cleanable.ratio	    0.5
log.cleaner.delete.retention.ms	    1 day
log.index.size.max.bytes	        10 * 1024 * 1024
log.index.interval.bytes	        4096
log.flush.interval.messages	        None
log.flush.scheduler.interval.ms	    3000
log.flush.interval.ms	            None
log.delete.delay.ms	                60000
log.flush.offset.checkpoint.interval.ms	60000
auto.create.topics.enable	        TRUE
controller.socket.timeout.ms	    30000
controller.message.queue.size	    10
default.replication.factor	        1
replica.lag.time.max.ms	            10000
replica.lag.max.messages	        4000
replica.socket.timeout.ms	        30 * 1000
replica.socket.receive.buffer.bytes	64 * 1024
replica.fetch.max.bytes	            1024 * 1024
replica.fetch.wait.max.ms	        500
replica.fetch.min.bytes	            1
num.replica.fetchers	            1
replica.high.watermark.checkpoint.interval.ms	5000
fetch.purgatory.purge.interval.requests	10000
producer.purgatory.purge.interval.requests	10000
zookeeper.session.timeout.ms	    6000
zookeeper.connection.timeout.ms	    6000
zookeeper.sync.time.ms	            2000
controlled.shutdown.enable	        FALSE
controlled.shutdown.max.retries	    3
controlled.shutdown.retry.backoff.ms	5000
auto.leader.rebalance.enable	    FALSE
leader.imbalance.per.broker.percentage	10
leader.imbalance.check.interval.seconds	300
offset.metadata.max.bytes	        1024