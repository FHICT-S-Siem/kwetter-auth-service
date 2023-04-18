./generate_jwtKeys.sh
cd user
mvn clean compile package

cd ../jwt-user
mvn clean compile package

cd ../
docker compose up