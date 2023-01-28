all:
	echo Please be more speficic: make preseed or make run

preseed:
	mvn compile
	java -cp target/classes com.darwinsys.expenses_server.PreSeedDataFile

run:
	mvn spring-boot:run
