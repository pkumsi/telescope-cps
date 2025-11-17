# Telescope CPS Simulator

This project implements a Cyber-Physical System (CPS) for telescope control using Java Spring Boot. The system is based on SRC (State–event–Response–Computation) diagrams provided in the course material. The project simulates a three-layer architecture consisting of Cyber, Sensor/Actuator (SA), and Physical components.

## Project Overview

The simulator models interactions across three layers:

### Cyber Layer
- `Clock`
- `MovementCalculator`
- `MovementMaker`

### Sensors and Actuators (SA) Layer
- `TargetDataSource`
- `PositionSensor`
- `Motor`

### Physical Layer
- `TargetData`
- `Telescope`

Spring Boot applications, events, and scheduling mechanisms are used to implement data flow and component interaction. Lombok is used for reducing boilerplate, and REST/WebSocket endpoints support real-time visualization.

---

## Setup Instructions

### Prerequisites
- Java 17+
- Maven 3.6+
- Git
- Any Java IDE (VS Code, IntelliJ, Eclipse)

### Step 1: Clone or Create the Project

```bash
git clone https://github.com/<your-username>/telescope-cps.git
cd telescope-cps
```

### Step 2: Build and Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

### Step 3: Access the Application

Dashboard: http://localhost:8080

Status API: http://localhost:8080/api/status

---

### SRC Mapping
Clock Component
State: int rpi, int rounds
A1: rounds, rpi → rounds, roundsPerInterval
A2: rounds, rpi → rounds, makeAdjustment (event)

MovementCalculator Component
State: float idealPos
A1: roundsPerInterval_in → roundsPerInterval_out
A2: nextIntervalRot, idealPos → idealAdjustment, idealPos

MovementMaker Component
State: Adjustment_Queue, float ca, float pa
A1: idealAdjustment → Adjustment_Queue
A2: currentRot, makeAdjustment, Adjustment_Queue → adjustment

Telescope Component
State: float rotation
A1: rotation → currentRotRaw
A2: rotateTelescope, rotation → rotation

Motor Component
State: float error
A1: adjustment → rotateTelescope (with Gaussian noise)

---

### API Endpoints
GET /api/status
GET /api/clock
GET /api/telescope
GET /api/movement-maker
GET /api/target-data

---

### Dependencies

Spring Boot 3.x
Spring Web
Spring WebSocket
Spring Events
Lombok
Jackson
Chart.js (frontend UI)
SockJS and STOMP

---

### Academic Note
This project is intended for educational use in CSE 564 – Software Design.
It is developed to match SRC diagrams and system behavior described in the course materials.

---