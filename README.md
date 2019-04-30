# GeoDenStream based on MOA (Massive Online Analysis)

MOA is an open source framework for Big Data stream mining (https://github.com/Waikato/moa). The GeoDenStream is designed and developed by improving the DemStream module within it.

There are several problems when using the original DenStream in MOA, which include memory limitation, false noise, overlap points. In the GeoDenStream, these problems are solved.

Detialed modifications and improvements can be found in master/moa/src/main/java/moa/clusterers/denstream.
Example application that designed for Zika and Ebola tweet data stream can be foound in master/moa/src/main/java/denstream/zikaebola.
### And a configurable application which can support other analysis scenarios can be found in master/moa/src/main/java/denstream/configure.
