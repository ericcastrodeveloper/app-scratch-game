# **CyberSpeed Game Engine**

CyberSpeed Game Engine is a game engine that generates game matrices, validates winning combinations, and calculates rewards based on predefined configurations. The project includes a highly configurable setup for symbols, probabilities, and winning combinations.

## **Table of Contents**

- [Getting Started](#getting-started)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
  - [Running the Application](#running-the-application)
- [Testing](#testing)
- [Build](#build)

## **Getting Started**

This section provides information on how to get the project running on your local machine for development and testing purposes.

### **Requirements**

- Java 21+
- Maven
- A valid JSON configuration file (`config.json`)

### **Installation**

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/cyberspeed-game-engine.git
   cd cyberspeed-game-engine
   ```

2. **Build the project using Maven:**

```bash
   mvn clean compile assembly:single
   This will generate the JAR file along with its dependencies.
```
Check that the JAR file is created in the `target/` directory:

```bash
ls target/
```
You should see a JAR file similar to `cyberspeed-game-engine.jar`.


### **Usage**

### **Running the Application**

You can run the application using the following command:

```bash
java -jar target/cyberspeed-game-engine.jar --config config.json --betting-amount 100
```

`--config`: Path to the configuration file (in JSON format).

`--betting-amount`: The amount used for betting in the game.

### **Testing**
Integration tests are included in this project. To execute all tests, use the following command:

```bash
mvn test
```
The tests validate:


- Matrix generation.
- Winning combinations validation.
- Reward calculation.
  
The tests ensure that the game operates according to the provided configuration.

### **Build**
To package the application into a standalone JAR file that includes all dependencies, use the following Maven command:

```bash
mvn clean package
```

After running this command, the JAR file will be generated in the target directory as cyberspeed-game-engine.jar. This JAR includes all required dependencies and can be executed with the java -jar command.

   
