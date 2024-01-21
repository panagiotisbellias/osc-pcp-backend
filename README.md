# Personal Code Portfolio Backend

## Git configuration
```bash
git init
git add
git config --local user.name "Panagiotis Bellias"
git config --local user.email "belliaspan@gmail.com"
git commit -m "Initial commit"
git checkout -b development
git checkout development && git merge <TASK-BRANCH>
```

## Quarkus dependencies installation

### Environment: Ubuntu 22.04

#### Java 17

```bash
sudo apt update
sudo apt upgrade
apt-cache search openjdk | grep openjdk-17
sudo apt install openjdk-17-jre
sudo apt install openjdk-17-jdk
java --version
```

#### Maven 3.9.6

```bash
sudo apt update
sudo apt upgrade
sudo apt install maven
mvn -version
```
