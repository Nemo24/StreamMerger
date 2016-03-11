Design and Constraints
1. Main Class is a stream combiner class which reads two streams and starts the process. 2 Blocking queues are used for 2 channels ( We could use one as well , however this could cause issues if one of the channels is delayed and then pairing does not take place)
 Consumers of the blocking queues take from queue and put to new TypeSpecific target queue 
 Consumers of type specific queue merge 2 typespecific target queues and creates the Pair of data. This pair can then be fed to any Loader which can process this.

MainTest class tests the process with 2 filestreams ( FileStreams are a test implementation of stream for unit test purposes)


Constraints

1.Pairs within same type will be emitted within the same order of arrival
how different order between different types maynot be correct 
for example
 Channel 1: R1_1 R1_2 R1_3 B1_4 B1_8 G1_5
 Channel 2: B2_6 B2_8 R2_9 G2_10 B2_7 R2_20
Output can be 
(R1_1,R2_9)
(B1_4,B2_6)
(B1_8,B2_8)
(G1_5,G2_10)
(R1_2,R2_20)

also can be 

(B1_4,B2_6)
(R1_1,R2_9)
(B1_8,B2_8)
(G1_5,G2_10)
(R1_2,R2_20)

Constraint of order across types is at the cost of parallelism/performance.
Would need to sequentially  look through each types instead of spawning multiple consumer threads.(This is similar to situation where kafka guarantees order only within partition)

Constraint 2. If we get too much of same type within a channel without a relevant pair
process would apply backpressure on the stream and will appear to be blocked.This will get released whenever same type is received on other channel thereby freeing the queue buffers
We could increase buffer limit to ensure a large set of the stream buffer is available in memory to be processed
3. Currently system supports only two channels and 3 types
  Adding types to this system is a minor effort and could be changed to be config
 Supporing n channel merging would require a rethink of the design
 
