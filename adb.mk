.PHONY: help devices install

export ANDROID_SERIAL ?= $(shell adb devices | head -2 | tail -1 | awk '{ print $$1 }')

APPLICATION_ID := com.tamakiii.tamakiii_sandbox.hello_android_12
INTENT := $(APPLICATION_ID)/$(APPLICATION_ID).MainActivity

help:
	@cat $(firstword $(MAKEFILE_LIST))

devices:
	adb devices -l

install: app/build/outputs/apk/debug/app-debug.apk
	adb -s $(ANDROID_SERIAL) install -r $<

start:
	adb -s $(ANDROID_SERIAL) shell am start --activity-clear-top -n $(INTENT)


# global options:
#  -a                       listen on all network interfaces, not just localhost
#  -d                       use USB device (error if multiple devices connected)
#  -e                       use TCP/IP device (error if multiple TCP/IP devices available)
#  -s SERIAL                use device with given serial (overrides $ANDROID_SERIAL)
#  -t ID                    use device with given transport id
#  -H                       name of adb server host [default=localhost]
#  -P                       port of adb server [default=5037]
#  -L SOCKET                listen on given socket for adb server [default=tcp:localhost:5037]
#  --one-device SERIAL|USB  only allowed with 'start-server' or 'server nodaemon', server will only connect to one USB device, specified by a serial number or USB device address.
#  --exit-on-write-error    exit if stdout is closed
