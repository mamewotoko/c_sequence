c_sequence [![Build Status](https://travis-ci.org/mamewotoko/c_sequence.svg?branch=master)](https://travis-ci.org/mamewotoko/c_sequence)
==========
A sample C parser application using [JavaCC](https://javacc.java.net/)

Build
-----
```
make
```

Run
---
```
java -jar classes/c.jar example/hello.c
```
function call is represented as an xml-like file.

Output
------
1. xml-like file (default)
2. [vcg](https://directory.fsf.org/wiki/Xvcg) (-vcg option)

TODO
----
* build and run xvcg
  * apply patch included in ubuntu package
* output static call graph as dot, graphviz file

----
Takashi Masuyama < mamewotoko@gmail.com >  
http://mamewo.ddo.jp/
