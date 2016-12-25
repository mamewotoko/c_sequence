SHELL=/bin/bash

DEST=/home/tak/bin/
LIB=/home/tak/lib/java/

JAVACC_DEFAULT_SOURCES=TokenMgrError.java ParseException.java Token.java SimpleCharStream.java
JAVACC_SOURCES=C.jj
JAVACC_RESULT_SOURCE=CParser.java CParserConstants.java CParserTokenManager.java

ARCHIVE=c.jar
CLASSES=classes
MANIFEST=C.MF
SOURCES=$(JAVACC_RESULT_SOURCE) $(JAVACC_DEFAULT_SOURCES) VCG.java
SOURCE_FILES=$(SOURCES) $(MANIFEST)

all: classes/c.jar 

#classes/VCG.class: VCG.java
#	javac -d classes VCG.java

#pcc: pcc.ml
#	ocamlc -dtypes unix.cma $< -o $@

$(CLASSES)/$(ARCHIVE): $(SOURCE_FILES)  $(MANIFEST) $(JAVACC_SOURCES)
	[ -e $(CLASSES) ] || mkdir $(CLASSES)
	rm -f $@
	javac -d $(CLASSES) $(SOURCES)
	cp $(SOURCE_FILES) $(CLASSES)
	(cd $(CLASSES) ; \
	if [ -z "$(MANIFEST)" ]; then \
		jar cvf $(ARCHIVE) * ; \
	else \
		jar cvmf $(MANIFEST) $(ARCHIVE) * ; \
	fi )

$(JAVACC_RESULT_SOURCE): $(JAVACC_SOURCES)
	javacc $(JAVACC_SOURCES)

################################################
# JavaCC
## Hoge.jj
JAVACC_SOURCE=C.jj
JAVACC_COMPILER=javacc

$(JAVACC_RESULT_JAVA_SOURCE): $(JAVACC_SOURCE)
	rm -f $(JAVACC_DEFAULT_SOURCES)
	$(JAVACC_COMPILER) $(JAVACC_SOURCE)


install: $(CLASSES)/$(ARCHIVE) pcc
	cp pcc ccall.sh $(DEST)
	cp classes/c.jar $(LIB)

update: clean
	( cd .. ; Tput.sh `dtar.pl c_sequence` )

clean:
	rm -rf $(CLASSES) *~ $(JAVACC_DEFAULT_SOURCES) $(JAVACC_RESULT_SOURCE)
#pcc *.cm[io] *.annot
