.PHONY: help check-requirements build clean

help:
	@cat $(firstword $(MAKEFILE_LIST))

check-requirements:
	type gradle && gradle --version

build:
	gradle build

clean:
	gradle clean
