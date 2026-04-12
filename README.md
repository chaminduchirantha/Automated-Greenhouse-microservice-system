# AGMS - Agricultural Management System (Microservices)

A comprehensive microservices-based Agricultural Management System built with Spring Boot and Spring Cloud. This project demonstrates a distributed, scalable architecture for managing agricultural operations including sensor telemetry, crop inventory, zone management, automation, and task scheduling.

## 🏗️ Architecture Overview

The AGMS project follows a microservices architecture with the following components:

```
┌─────────────────────────────────────────────────────────────────┐
│                        API Gateway (Port 8080)                   │
│              - Routes requests to appropriate services           │
│              - Loads balance between microservices               │
└─────────────────────────────┬───────────────────────────────────┘
                              │
                ┌─────────────┼─────────────┐
                │             │             │
    ┌───────────▼──────┐  ┌──▼──────────┐   │
    │  Discovery Server│  │  Config     │   │
    │  (Eureka 8761)   │  │  Server     │   │
    └──────────────────┘  │  (8888)     │   │
                          └─────────────┘   │
                │
    ┌───────────┴──────────┬──────────────┬──────────────┬─────────────┐
    │                      │              │              │             │
┌───▼──────────────┐ ┌────▼────────┐ ┌──▼──────────┐ ┌─▼────────┐ ┌──▼──────────┐
│ Sensor Telemetry │ │ Crop        │ │Zone Mgmt    │ │Automation│ │ Sentura Task│
│ Service (8083)   │ │ Inventory   │ │Service      │ │ Service  │ │ Service     │
│                  │ │ Service     │ │ (8084)      │ │ (8085)   │ │ (8086)      │
│ • Read sensors   │ │ (8082)      │ │             │ │          │ │             │
│ • Store data     │ │             │ │ • Zones     │ │• Tasks   │ │ • Schedule  │
└──────────────────┘ │ • Crops     │ │ • Fields    │ │• Pumps   │ │ • Execute   │
                     │ • Inventory │ │ • Sections  │ │• Motors  │ │ • Track     │
                     │ • Status    │ │             │ │          │ │             │
                     └─────────────┘ └─────────────┘ └──────────┘ │             │
                                                                  └─────────────┘
                           ▼ ▼ ▼ ▼ ▼
                     ┌──────────────────┐
                     │   H2 Databases   │
                     │  (in-memory)     │
                     └──────────────────┘
```

## 📦 Microservices

### 1. **Discovery Server** (Port 8761)
Eureka Discovery Server for service registration and discovery.
- **Directory**: `discovery-server/`
- **Purpose**: Central registry for all microservices
- **Features**: Service discovery, health monitoring, load balancing

### 2. **Sensor Telemetry Service** (Port 8083)
Manages real-time sensor data collection and storage.
- **Directory**: `sensor-telemetry-service/`
- **Purpose**: Collect and store telemetry from agricultural sensors
- **Features**:
  - Create/read/update/delete sensor readings
  - Query by sensor ID, type, location, time range
  - Active records filtering
  - Health monitoring
- **Endpoints**: 11 REST endpoints
- **Database**: H2 in-memory

### 3. **Crop Inventory Service** (Port 8082)
Manages crop inventory and lifecycle tracking.
- **Directory**: `crop-inventory-service/`
- **Purpose**: Track crop inventory and status
- **Features**:
  - Crop lifecycle management (GROWING → HARVESTED)
  - Health tracking (HEALTHY, WEAK, DISEASED)
  - Advanced filtering and aggregation
  - Analytics (count by status, total quantity)
- **Endpoints**: 18 REST endpoints
- **Database**: H2 in-memory

### 4. **Zone Management Service** (Port 8084)
[To be implemented] Manages agricultural zones and fields.
- **Directory**: `zone-management-service/`
- **Purpose**: Organize and manage agricultural zones, fields, sections
- **Planned Features**:
  - Zone creation and management
  - Field mapping
  - Section organization
  - Cross-service zone queries

### 5. **Automation Service** (Port 8085)
[To be implemented] Manages irrigation and automation tasks.
- **Directory**: `automation-service/`
- **Purpose**: Automate irrigation, pumps, motors, and other devices
- **Planned Features**:
  - Pump control
  - Motor management
  - Irrigation scheduling
  - Automation rules

### 6. **Sentura Task Service** (Port 8086)
[To be implemented] Task scheduling and execution for agricultural operations.
- **Directory**: `sentura-task/`
- **Purpose**: Schedule and track agricultural tasks
- **Planned Features**:
  - Task scheduling
  - Task execution tracking
  - Task history and analytics

### 7. **API Gateway** (Port 8080)
[To be implemented] Central entry point for all client requests.
- **Directory**: `api-gateway/`
- **Purpose**: Route requests to appropriate services
- **Planned Features**:
  - Request routing
  - Load balancing
  - Request/response filtering
  - Authentication (future)

### 8. **Config Server** (Port 8888)
[To be implemented] Centralized configuration management.
- **Directory**: `config-server/`
- **Purpose**: Manage configuration for all services
- **Planned Features**:
  - Centralized configuration
  - Environment-specific configs
  - Dynamic configuration updates

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.5.10
- **Java Version**: Java 21
- **Cloud**: Spring Cloud 2024.0.1
- **Service Discovery**: Netflix Eureka
- **Database**: H2 (in-memory for development)
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven 3.6+
- **Data Transfer Objects**: Lombok
- **Validation**: Spring Validation
- **Monitoring**: Spring Boot Actuator
- **Testing**: JUnit 4, TestNG

## 🚀 Getting Started

### Prerequisites

- **Java 21** - Download from [Eclipse Adoptium](https://adoptium.net/) or [Microsoft](https://www.microsoft.com/openjdk)
- **Maven 3.6+** - Download from [Apache Maven](https://maven.apache.org/)
- **Git** - For version control

### Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd agms-project
   ```

2. **Start Discovery Server First**
   ```bash
   cd discovery-server
   mvn clean install
   mvn spring-boot:run
   # Or: java -jar target/discovery-server-0.0.1-SNAPSHOT.jar
   ```
   - Access Eureka Dashboard: http://localhost:8761

3. **Start Microservices** (in separate terminals)
   
   **Sensor Telemetry Service:**
   ```bash
   cd sensor-telemetry-service
   mvn clean install
   mvn spring-boot:run
   ```
   
   **Crop Inventory Service:**
   ```bash
   cd crop-inventory-service
   mvn clean install
   mvn spring-boot:run
   ```

4. **Verify Services**
   - Eureka: http://localhost:8761
   - Sensor Telemetry Health: http://localhost:8083/actuator/health
   - Crop Inventory Health: http://localhost:8082/actuator/health

## 📡 API Documentation

### Sensor Telemetry Service

**Base URL**: `http://localhost:8083/api/v1/sensor-telemetry`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create new sensor telemetry |
| GET | `/` | Get all telemetry |
| GET | `/{id}` | Get telemetry by ID |
| GET | `/sensor/{sensorId}` | Get telemetry by sensor ID |
| GET | `/type/{sensorType}` | Get telemetry by sensor type |
| GET | `/location/{location}` | Get telemetry by location |
| GET | `/time-range` | Get telemetry by time range |
| GET | `/active` | Get active telemetry records |
| PUT | `/{id}` | Update telemetry |
| DELETE | `/{id}` | Delete telemetry |
| PATCH | `/{id}/deactivate` | Deactivate telemetry |

### Crop Inventory Service

**Base URL**: `http://localhost:8082/api/v1/crop-inventory`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/` | Create new crop |
| GET | `/` | Get all crops |
| GET | `/{id}` | Get crop by ID |
| GET | `/code/{cropCode}` | Get crop by code |
| GET | `/name/{cropName}` | Get crops by name |
| GET | `/location/{location}` | Get crops by location |
| GET | `/status/{status}` | Get crops by status |
| GET | `/active` | Get active crops |
| GET | `/count/status/{status}` | Count crops by status |
| GET | `/count/location/{location}` | Count crops by location |
| GET | `/quantity/location/{location}` | Total quantity by location |
| PUT | `/{id}` | Update crop |
| DELETE | `/{id}` | Delete crop |
| PATCH | `/{id}/deactivate` | Deactivate crop |

## 📝 Sample API Calls

### Create Sensor Telemetry
```bash
curl -X POST http://localhost:8083/api/v1/sensor-telemetry \
  -H "Content-Type: application/json" \
  -d '{
    "sensorId": "SENSOR-001",
    "sensorType": "TEMPERATURE",
    "readingValue": 28.5,
    "unit": "Celsius",
    "location": "Field A - Section 1",
    "isActive": true
  }'
```

### Create Crop Inventory
```bash
curl -X POST http://localhost:8082/api/v1/crop-inventory \
  -H "Content-Type: application/json" \
  -d '{
    "cropName": "Rice",
    "cropCode": "RICE-001",
    "cropVariety": "Basmati",
    "quantity": 500,
    "unit": "kg",
    "location": "Field A - Zone 1",
    "plantedDate": "2024-01-15T09:00:00",
    "status": "GROWING",
    "cropCondition": "HEALTHY",
    "expectedYield": 750,
    "isActive": true
  }'
```

### Get All Crops
```bash
curl http://localhost:8082/api/v1/crop-inventory
```

### Filter Crops by Status
```bash
curl http://localhost:8082/api/v1/crop-inventory/status/GROWING
```

## 📊 Project Structure

```
agms-project/
├── api-gateway/                 # API Gateway (TO DO)
├── automation-service/          # Automation Service (TO DO)
├── config-server/               # Config Server (TO DO)
├── crop-inventory-service/      
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── lk/ijse/gdse/cropinventoryservice/
│   │   │   │       ├── controller/
│   │   │   │       ├── service/
│   │   │   │       ├── repository/
│   │   │   │       ├── entity/
│   │   │   │       └── CropInventoryServiceApplication.java
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/
│   ├── pom.xml
│   └── README.md
├── discovery-server/            
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── sensor-telemetry-service/    
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── lk/ijse/gdse/sensortelemetryservice/
│   │   │   │       ├── controller/
│   │   │   │       ├── service/
│   │   │   │       ├── repository/
│   │   │   │       ├── entity/
│   │   │   │       └── SensorTelemetryServiceApplication.java
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/
│   ├── pom.xml
│   └── README.md
├── zone-management-service/     # Zone Management Service (TO DO)
├── sentura-task/                # Task Service (TO DO)
├── README.md                     # This file
└── pom.xml                       # Parent POM (if using multi-module)
```

## 🔄 Service Communication Flow

1. **Client Request** → API Gateway
2. **API Gateway** → Routes to appropriate service
3. **Microservice** → Queries Discovery Server (Eureka) to find other services
4. **Microservice** → Performs business logic
5. **Microservice** → Returns response to API Gateway
6. **API Gateway** → Returns response to Client

## 🔌 Service Registration

All microservices automatically register with the Discovery Server on startup. To verify:

1. Start Discovery Server on port 8761
2. Start other services
3. Visit: http://localhost:8761
4. View registered instances in the Eureka dashboard

## 🏥 Health Checks

Each service provides health endpoints via Spring Boot Actuator:

```bash
# Sensor Telemetry Service
curl http://localhost:8083/actuator/health

# Crop Inventory Service
curl http://localhost:8082/actuator/health

# Metrics
curl http://localhost:8083/actuator/metrics
```

## 📈 Monitoring & Metrics

Spring Boot Actuator provides endpoints for monitoring:
- `/actuator/health` - Service health status
- `/actuator/info` - Application information
- `/actuator/metrics` - Performance metrics

## 🧪 Testing

Run tests for a service:

```bash
cd sensor-telemetry-service
mvn test

cd crop-inventory-service
mvn test
```

## 🔐 Security (Future Enhancement)

- OAuth2/JWT authentication
- API key management
- Role-based access control (RBAC)
- SSL/TLS encryption

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Netflix Eureka](https://github.com/Netflix/eureka)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)


<img width="1577" height="894" alt="image" src="https://github.com/user-attachments/assets/ee36e56a-ee70-411f-b11b-a0f50cfd08fb" />
