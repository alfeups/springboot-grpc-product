# Product Service with Spring Boot 2.5.5

This is a sample CRUD service from gRPC course and intends to manage products. Tools used so far: 

<ul>
    <li><strong>Spring Boot Version:</strong> 2.5.5</li>
    <li><strong>Communication Protocol:</strong> gRPC (for communication between clients and the server)</li>
    <li><strong>Data Access:</strong> Spring Data JPA</li>
    <li><strong>Java Version:</strong> 17</li>
    <li><strong>gRPC Implementation:</strong> gRPC Net.devh (for both server and client)</li>
    <li><strong>Additional Libraries:</strong>
        <ul>
            <li>io.grpc (for gRPC support)</li>
            <li>gRPC Protobuf (for protocol definition)</li>
            <li>Jakarta Annotation API (for annotations)</li>
            <li>AssertJ Core (for testing)</li>
            <li>Flyway (for database migrations)</li>
        </ul>
    </li>
</ul>



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

2. Build the project:

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

- Access the gRPC server using a gRPC client and utilize the provided RPC methods for product management.

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