# GeoDenStream based on MOA (Massive Online Analysis)

GeoDenStream is an improved DenStream clustering method for acquiring individual data point information within Big Data Streams. The implementation of GeoDenStream is based on the open source MOA project (Bifet et al., 2010), which is available at (https://github.com/Waikato/moa).

In GeoDenStream, several modifications and improvements are made based on the MOA package to address the memory limitation, overlapping points, and false noise issues. Also, pruning strategy is extended to consider the time stamp of the data records.

Detailed modifications and improvements can be found in master/moa/src/main/java/moa/clusterers/denstream.
There are four main classes in this folder:
+ **DenPoint.java** This class is same with the original MOA, which is used to record a point(aka a record, an item, a tweet) in a stream.
+ **MicroCluster.java** In this class, a new function TryInsert was add for attempting add a point into a potential cluster and check whether it can be added. Two lists are added for recording: (1) Ids of the points that belongs to a potential cluster and (2) the distance between each involved point and the center of a potential cluster (could be seen in Line 36 and 37). 
+ **TimeStamp.java** This class is same with the original MOA, and it is used for giving a timestamp as long integer for each points. 
+ **WithDBSCAN.java** The code in this class has been modified largely. Function trainOnInstanceImpl is a new implemented function which supports the real time based pruning strategy. And function trainOnInstanceImpl_TpStaticIndex keeps the count based pruning strategy as MOA does. A minor issue but also a key point. the function **nearestCluster** was modified. The original nearestCluster has a bug in calculating the minimum distance. Could be seen in Line 483.

Examples of its application using Twitter data streams can be found in master/moa/src/main/java/denstream/zikaebola.

---------------------------------------------------------------------------------------------------------------------------------
**For other analysis scenarios, a configurable application is provided in master/moa/src/main/java/denstream/configure.**
+ **DSC_Dynamic.java** This is an entrance for using GeoDenStream, with dynamic memory optimization (loading points with an offline range and using the index strategy).
+ **DSC_Static.java** This is another entrance for using GeoDenStream, without memory optimization (loading all records for generating clusters).
+ **ProcessPotentialCluster.java** This class is used for handling the overlap and false noise issues. Detailed implementation could be checked in the funciton fillPotentialCluster.
+ **ProcessOfflineCluster.java** This class is for get offline clusters based on the results of ProcessPotentialCluster.java.
For other classes, they are some utilies and could be easy understand.

A sample xml document is presented as follows:
```xml
<DenStreamCase>
  <Stream>
    <FileName description="string: input csv file">../Zika/zika_export_forDEN.csv</FileName>
    <XColumnIndex description="integer: column index of X informaton">9</XColumnIndex>
    <YColumnIndex description="integer: column index of Y informaton">10</YColumnIndex>
    <TimeColumnIndex description="integer: column index of X informaton">0</TimeColumnIndex>
    <ConnectedXColumnIndex description="integer: column index of X1 informaton">12</ConnectedXColumnIndex>
    <ConnectedYColumnIndex description="integer: column index of Y1 informaton">13</ConnectedYColumnIndex>
  </Stream>
  <Time>
    <StartTime description="string: Year-month-day hour:minute:second">2015-12-12 00:00:00</StartTime>
    <IntervalType description="string: Year|Month|Week|Day|Hour|Minute|Second">Day</IntervalType>
    <IntervalValue description="integer: indicates interval">1</IntervalValue>
    <IntervalCount description="integer: indicates interval count">84</IntervalCount>
  </Time>
  <Cluster>
    <Epsilon description="float: epsilon neighbourhood">3.0</Epsilon>
    <MinPoints description="integer: minimal number of points cluster contain">1</MinPoints>
    <InitPoints description="integer: number of points to use for initialization">100</InitPoints>
    <Mu description="float: mu parameter for pruning">1.0</Mu>
    <Lambda description="float: lambda parameter for pruning">0.001</Lambda>
    <Beta description="float: beta parameter for pruning">0.2</Beta>
    <Offline description="float: epsilon*offline for offline DBSCAN">2.0</Offline>
    <PruningType description="string: Count|Time|Dynamic">Dynamic</PruningType>
    <PruningValue description="Count: number; Time: number; Dynamic: file">../Zika/mean_median.csv</PruningValue>
  </Cluster>
  <Output>
    <Directory description="string: output directory">../Zika2/clusterdata_e_3.0_tp_Median/</Directory>
    <StartIntervalIndex description="integer: start index of interval">0</StartIntervalIndex>
    <OutputPotential description="integer: 0-No,1-Yes">0</OutputPotential>
    <ImproveProcessing description="integer: 0-No,1-Yes">1</ImproveProcessing>
  </Output>
</DenStreamCase>
```


Sample dataset1
-------------------------------------------------------------------------------------------------------------------------------
<img style="align:left" src="https://raw.githubusercontent.com/manqili/GeoDenStream/master/TestDatasets/Synthetic1/SyntheticStream1-Reference.jpg" width = "400" height = "400" />

<img style="align:left" src="https://raw.githubusercontent.com/manqili/GeoDenStream/master/TestDatasets/Synthetic1/GeoDenStream_Cluster1.jpg" width = "400" height = "400" />


---------------------------------------------------------------------------------------------------------------------------------
Bifet, A., Holmes, G., Kirkby, R., and Pfahringer, B. (2010). MOA: Massive Online Analysis. J. Mach. Learn. Res. 11, 1601–1604.
