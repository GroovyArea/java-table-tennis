# java-table-tennis with domain driven design

## 패키지 구조

---

### api

- presentation layer
- 외부의 요청과 응답의 책임
- 공통 외부 설정

### application

- 외부 요청의 business logic 을 처리하는 usecase
- domain service 를 의존하여 일련의 외부 요구 사항을 처리합니다.

### core

- domain entity, VO, domain service 정의
- usecase 에서는 최대한 domain service 를 활용하여 일련의 요구사항을 나열합니다.
- 최대한 pojo(plain old java object) 를 유지하도록 정의 (single module 이므로 Bean 등록하여 사용)
- domain entity
  - 주체적인 행위를 가집니다.
- domain service
  - domain entity 가 자체적으로 수행하지 못하는 domain business 정의

### infrastructure

- Persistence Layer에 Spring Data JPA 활용
  - core 모듈의 public interface 를 구현하며, DAO(Data Access Object) 로 활용
- 외부 API, Library 관련 Configuration

## api docs

---

- http://localhost:8080/api-docs
- swagger-ui 확인 가능합니다.