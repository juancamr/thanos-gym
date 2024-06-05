build:
	@mvn compile

run: build
	@mvn exec:java -Dexec.mainClass="com.uni.thanosgym.Thanosgym"

