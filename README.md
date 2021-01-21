# GeoDenStream

Please refer to the following publication for more details of this project:

Li, M., Croitoru, A., & Yue, S. (2020). GeoDenStream: An improved DenStream clustering method for managing entity data within geographical data streams. *Computers & Geosciences*, 144, 104563. https://doi.org/10.1016/j.cageo.2020.104563

---------------------------------------------------------------------------------------------------------------------------------
GeoDenStream is a spatiotemporal entity-based stream clustering method. It is particularly suitable for clustering discrete entities due to its ability to track the relationship between entities and clusters over time. Evaluations of GeoDenStream suggest its ability to efficiently handle memory constraints, overlapping data points, and false noise. The implementation of GeoDenStream is based on the open source MOA project, which is available at (https://github.com/Waikato/moa).

Detailed modifications and improvements can be found in master/moa/src/main/java/moa/clusterers/denstream. There are four main classes in this folder:
+ **DenPoint.java** This class is used to record a data point in a stream.
+ **MicroCluster.java** In this class, function TryInsert is introduced for the attempt to add a point into a potential-cluster. Two lists are formulated to record: (1) IDs of the points that belong to a potential-cluster and (2) distance between the point under inspection and the center of a potential-cluster. 
+ **TimeStamp.java** This class is used for giving a timestamp as a long integer for each point. 
+ **WithDBSCAN.java** In this class, function trainOnInstanceImpl is newly implemented that supports the real-time based pruning strategy. Function trainOnInstanceImpl_TpStaticIndex supports the count-based pruning strategy.

Examples of GeoDenStream applications using Twitter data streams can be found in master/moa/src/main/java/denstream/zikaebola.

---------------------------------------------------------------------------------------------------------------------------------
**For other scenarios, a configurable application is provided in master/moa/src/main/java/denstream/configure.**
+ **DSC_Dynamic.java** This is an entry-level practice of GeoDenStream with dynamic memory optimization (loading points with an offline range and employing the indexing strategy).
+ **DSC_Static.java** This is another entry-level practice of GeoDenStream without memory optimization (loading all records to generate clusters).
+ **ProcessPotentialCluster.java** This class is used to handle point overlap and false noise. Detailed implementation is in the function fillPotentialCluster.
+ **ProcessOfflineCluster.java** This class is for obtaining offline-clusters based on the results of ProcessPotentialCluster.java.
For other classes, they are some utilies and could be easy understand.

A sample .xml document is presented as below:
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
-------------------------------------------------------------------------------------------------------------------------------
<div >
<table>
  <tr>Sample dataset #1. Dataset can be found in GeoDenStream/TestDatasets/Synthetic1/.<tr>
  <tr>
    <td><img style="align:left" src="https://raw.githubusercontent.com/manqili/GeoDenStream/master/TestDatasets/Synthetic1/SyntheticStream1-Reference.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://raw.githubusercontent.com/manqili/GeoDenStream/master/TestDatasets/Synthetic1/GeoDenStream_Cluster1.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>Figure 1. The static dataset on the left and its clustering results using GeoDenStream on the right.</tr>
</table>
</div>

<hr></hr>

<div >
<table>
  <tr>Sample dataset #2. Dataset could be found in GeoDenStream/TestDatasets/Synthetic2/</tr>
  <tr><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference-Overall.jpg" width = "400" height = "400" /></tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference1.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster1.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference2.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster2.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference3.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster3.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference4.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster4.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference5.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster5.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference6.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster6.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference7.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster7.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference8.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster8.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference9.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster9.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/SyntheticStream2-Reference10.jpg" width = "400" height = "400" /></td>
    <td><img style="align:left" src="https://github.com/manqili/GeoDenStream/blob/master/TestDatasets/Synthetic2/GeoDenStream_Cluster10.jpg" width = "400" height = "400" /></td>
  </tr>
  <tr>Figure 2. The evolving dataset on the left and its clustering results using GeoDenStream on the right at different time steps.</tr>
</table>
</div>




---------------------------------------------------------------------------------------------------------------------------------
Bifet, A., Holmes, G., Kirkby, R., and Pfahringer, B. (2010). MOA: Massive Online Analysis. J. Mach. Learn. Res. 11, 1601–1604.
