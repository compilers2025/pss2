$(eval TMP := $(shell mktemp))
JAVAC = "javac"
JAVA = 'java'                
SCANNER = "Scanner"
PARSER = "Parser"
MAIN = "Main"
GENERATOR = "CodeGenerator"
INP0 = "./input.txt"

all: compile
compile:
	$(JAVAC) -Xlint $(SCANNER).java $(PARSER).java $(MAIN).java $(GENERATOR).java

test:
			$(JAVA) $(MAIN) $(INP0)
			./output
			echo $?