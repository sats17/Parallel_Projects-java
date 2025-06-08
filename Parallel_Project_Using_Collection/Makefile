# Makefile for Java project starting at com.bank.mainui.MainUi

# Directory paths
SRC_DIR=src
OUT_DIR=out

# Main class to run
MAIN_CLASS=com.bank.mainui.MainUi

# Find all .java files recursively
SOURCES=$(shell find $(SRC_DIR) -name "*.java")

.PHONY: all run clean

# Default target
all: $(OUT_DIR)/classes

# Compile all .java files
$(OUT_DIR)/classes:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(SOURCES)

# Run the main class
run: all
	java -cp $(OUT_DIR) $(MAIN_CLASS)

# Clean compiled classes
clean:
	rm -rf $(OUT_DIR)
