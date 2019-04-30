# GeoDenStream based on MOA (Massive Online Analysis)

GeoDenStream is an improved DenStream clustering method for acquiring individual data point information within Big Data Streams. The implementation of GeoDenStream is based on the open source MOA project (Bifet et al., 2010), which is available at (https://github.com/Waikato/moa).

In GeoDenStream, several modifications and improvements are made based on the MOA package to address the memory limitation, overlapping points, and false noise issues. Also, pruning strategy is extended to consider the time stamp of the data records.

Detailed modifications and improvements can be found in master/moa/src/main/java/moa/clusterers/denstream.
Examples of its application using Twitter data streams can be found in master/moa/src/main/java/denstream/zikaebola.
### For other analysis scenarios, a configurable application is provided in master/moa/src/main/java/denstream/configure.


Bifet, A., Holmes, G., Kirkby, R., and Pfahringer, B. (2010). MOA: Massive Online Analysis. J. Mach. Learn. Res. 11, 1601–1604.
