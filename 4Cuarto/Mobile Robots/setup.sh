#!/bin/sh
sudo apt-get update
echo "BASIC PACKAGES"
sudo apt install -y mlocate
sudo apt install -y git
echo "ROS INSTALLATION"
sudo sh -c 'echo "deb http://packages.ros.org/ros/ubuntu $(lsb_release -sc) main" > /etc/apt/sources.list.d/ros-latest.list'
sudo apt install -y curl
curl -s https://raw.githubusercontent.com/ros/rosdistro/master/ros.asc | sudo apt-key add -
sudo apt update
sudo apt install -y ros-noetic-desktop-full
echo "source /opt/ros/noetic/setup.bash" >> ~/.bashrc
source ~/.bashrc
sudo apt install -y python3-rosdep python3-rosinstall python3-rosinstall-generator python3-wstool build-essential
sudo apt install -y python3-rosdep
sudo rosdep init
rosdep update
echo "CATKIN INSTALLATION"
sudo sh -c 'echo "deb http://packages.ros.org/ros/ubuntu `lsb_release -sc` main" > /etc/apt/sources.list.d/ros-latest.list'
wget http://packages.ros.org/ros.key -O - | sudo apt-key add -
sudo apt-get update
sudo apt-get install -y python3-catkin-tools
echo "CATKIN WORKSPACE INIT"
mkdir -p ~/catkin_ws/src
cd ~/catkin_ws
catkin init
catkin_make
echo "source ~/catkin_ws/devel/setup.bash" >> ~/.bashrc
source ~/.bashrc
catkin config -DCMAKE_BUILD_TYPE=Release

sudo apt install snap
sudo snap refresh
sudo snap install code

code --install-extension ms-vscode.cpptools
code --install-extension ms-python.python
code --install-extension ms-iot.vscode-ros

sudo apt install -y ros-noetic-kobuki-msgs

cd ~/catkin_ws/src
git clone https://git.ist.tugraz.at/courses/mobile_robots_public.git
cd ..
catkin_make
