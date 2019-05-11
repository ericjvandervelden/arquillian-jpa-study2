/ maak in eclipse een remote application debug config, 
naam: arquillian-jpa-study2-with-mvn-deps-attach
project: arquillian-jpa-study2
host: localhost
port: 8787
/ geen Source attachments, alles via mvn	,

/ start wildfly,
$ standalone.bat --debug
/ set b, 
run debug config: arquillian-jpa-study2-with-mvn-deps-attach
/ doe in forge: build test
/ hij valt in b,

