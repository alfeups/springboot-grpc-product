# Product Service with Spring Boot 2.5.5

This is a sample CRUD service from gRPC course which intends to manage products operations such as creating, reading, updating, and deleting. Tools used so far: 


## Prerequisites

Before you begin, ensure you have met the following requirements:

- [Java Development Kit (JDK) 17 or higher](https://adoptopenjdk.net/)
- [Docker and Docker Compose (for running PostgreSQL)](https://www.docker.com/)
- [Maven (for building the project)](https://maven.apache.org/)
- IDE of your choice (e.g., [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Visual Studio Code](https://code.visualstudio.com/))
- **Spring Boot Version:** [2.5.5](https://spring.io/projects/spring-boot)
- **Communication Protocol:** [gRPC](https://grpc.io/) (for communication between clients and the server)
- **Data Access:** [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- **gRPC Implementation:** [gRPC Net.devh](https://github.com/NetDevHui/grpc-spring-boot-starter) (for both server and client)
- **io.grpc:** [io.grpc](https://github.com/grpc/grpc-java) (for gRPC support)
- **gRPC Protobuf:** [gRPC Protobuf](https://developers.google.com/protocol-buffers) (for protocol definition)
- **Jakarta Annotation API:** [Jakarta Annotation API](https://jakarta.ee/) (for annotations)
- **AssertJ Core:** [AssertJ Core](https://assertj.github.io/doc/) (for testing)
- **Flyway:** [Flyway](https://flywaydb.org/documentation/) (for database migrations)


## Features

- Create, Read, Update, and Delete products.
- gRPC-based APIs for communication.
- Database migrations using Flyway.
- Easy-to-use product management.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 installed.
- A PostgreSQL database for storing product data. You can customize the database configuration in `application.properties`.
- gRPC client to interact with the service.

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/alfeups/product-crud-service.git
```

2. Start the PostgreSQL database using Docker Compose:

```bash
docker-compose up --build
```

3. Build the project:

```bash
cd product-crud-service
./mvnw clean install
```

3. Run the application:

```bash
./mvnw spring-boot:run
```

4. The gRPC server will start and listen on the specified port (default is 9090).

## Usage

### gRPC Endpoints
The gRPC server exposes the following endpoints:

- create: Create a new product.
- findById: Find a product by its ID.
- delete: Delete a product by its ID.
- findAll: Retrieve a list of all products.

You can use a gRPC client to interact with these endpoints. Refer to the protobuf files for message definitions.

### Database Configuration
The application uses a PostgreSQL database. You can configure the database connection in the application.properties file.


## Configuration

- Database configuration can be customized in `src/main/resources/application.properties`.


- Flyway migration scripts are located in `src/main/resources/db/migration`.

## Testing

- Unit tests are implemented using AssertJ Core. You can run tests with the following command:

  ```bash
  ./mvnw test
  ```

## Contributing

1. Fork the repository.
2. Create a new branch for your feature: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Create a pull request.

## Acknowledgments

- Special thanks to the Spring Boot, gRPC, and Flyway communities for their amazing tools and documentation.

## Contact

If you have any questions or need assistance, please feel free to contact [Alfeu Pereira](mailto:alfeupsjrs@gmail.com).