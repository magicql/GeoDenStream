# GeoDenStream based on MOA (Massive Online Analysis)

GeoDenStream is an improved DenStream clustering method for acquiring individual data point information within Big Data Streams. The implementation of GeoDenStream is based on the open source MOA project (Bifet et al., 2010), which is available at (https://github.com/Waikato/moa).

In GeoDenStream, several modifications and improvements are made based on the MOA package to address the memory limitation, overlapping points, and false noise issues. Also, pruning strategy is extended to consider the time stamp of the data records.

Detailed modifications and improvements can be found in master/moa/src/main/java/moa/clusterers/denstream.
Examples of its application using Twitter data streams can be found in master/moa/src/main/java/denstream/zikaebola.

---------------------------------------------------------------------------------------------------------------------------------
**For other analysis scenarios, a configurable application is provided in master/moa/src/main/java/denstream/configure.**

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
    <Directory description="string: output directory">c:/manqi/Manuscript/Zika2/clusterdata_e_3.0_tp_Median/</Directory>
    <StartIntervalIndex description="integer: start index of interval">0</StartIntervalIndex>
    <OutputPotential description="integer: 0-No,1-Yes">0</OutputPotential>
    <ImproveProcessing description="integer: 0-No,1-Yes">1</ImproveProcessing>
  </Output>
</DenStreamCase>
```





Bifet, A., Holmes, G., Kirkby, R., and Pfahringer, B. (2010). MOA: Massive Online Analysis. J. Mach. Learn. Res. 11, 1601–1604.
