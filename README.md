# CRUD Project (Spring Boot)

이 프로젝트는 Spring Boot를 사용하여 간단한 사용자(User) CRUD(생성, 조회, 수정, 삭제) REST API를 구현한 예제입니다.

## 기술 스택
- Java
- Spring Boot
- Spring Data JPA
- H2 Database (기본값) 또는 PostgreSQL (설정 변경 시)

## 실행 방법

### 1. 의존성 설치
```
./mvnw clean install
```

### 2. 애플리케이션 실행
```
./mvnw spring-boot:run
```

### 3. API 엔드포인트
- `GET    /api/users`           : 모든 사용자 조회
- `GET    /api/users/{id}`      : 특정 사용자 조회
- `POST   /api/users`           : 사용자 생성
- `PUT    /api/users/{id}`      : 사용자 정보 수정
- `DELETE /api/users/{id}`      : 사용자 삭제

## 데이터베이스 변경 (PostgreSQL)
1. `pom.xml`에 PostgreSQL 드라이버 추가

예시: 
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.5.0</version>
</dependency>
```

2. `src/main/resources/application.properties`에서 DB 설정 변경

예시:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```
3. PostgreSQL 데이터베이스 및 유저 생성

PostgreSQL를 설치되어 있지 않다면 [공식 사이트])(https://www.postgresql.org/download/)에서 설치하세요.

```bash
psql -U postgres
CREATE DATABASE your_db_name;
CREATE USER your_db_user WITH PASSWORD 'your_db_password';
GRANT ALL PRIVILEGES ON DATABASE your_db_name TO your_db_user;
```

## 참고
- H2 콘솔: `http://localhost:8080/h2-console`
- Postman, curl 등으로 API 테스트 가능
- IntelliJ Endpoint로 API 테스트도 가능

