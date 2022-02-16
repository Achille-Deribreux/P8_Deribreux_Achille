cd gps || exit
docker build . -t gps

cd ..
cd Rewards || exit
docker build . -t rewards

cd ..
cd Pricer || exit
docker build . -t pricer

cd ..
cd User || exit
docker build . -t user

cd ..
cd TourGuideMain || exit
docker build . -t tourguidemain