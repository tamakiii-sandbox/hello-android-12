.PHONY: help check-requirements build test clean

help:
	@cat $(firstword $(MAKEFILE_LIST))

check-requirements:
	type gradle && gradle --version

build:
	gradle build

test:
	gradle test

clean:
	gradle clean
