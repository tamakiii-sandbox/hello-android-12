include $(NDK_HOME)/build/core/build-local.mk

.PHONY: help build clean

CLANG := $(NDK_HOME)/toolchains/llvm/prebuilt/darwin-x86_64/bin/armv7a-linux-androideabi34-clang

help:
	@cat $(firstword $(MAKEFILE_LIST))

build: hello

hello: hello.c
	$(CLANG) -o $@ $<

clean:
