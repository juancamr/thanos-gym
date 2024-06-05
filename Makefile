build:
	@mvn compile

run: build
	@mvn exec:java
