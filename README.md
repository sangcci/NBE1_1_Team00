### 프로그래머스 1차 프로젝트 

### 요구사항

Product(Coffee)

- [ ] 고객은 커피 목록을 조회할 수 있다 (GET)
    - [ ] 상품 타입, 원두 이름, 가격, (stock, 맛 추가설명 )
    - 상품에 대한 재고는 무한정 있다고 가정
    - 커피에 대한 상세 조회는 없음
- [ ] 관리자는 커피 상품에 대한 CRUD를 할 수 있다
  - [ ] 이름이 같은 상품이 존재한다면 `IllegalARgumentException` 예외를 발생시킨다.
  - ~~재고 0 이하로 떨어지면 오류 발생~~ -> 현재 도메인에서는 구현하지 않는다
- [ ] 관리자는 모든 주문 내역들을 볼 수 있음 (GET)
  - [ ] ~~상태(배송 출발, 준비 중…)에 따라서 서로 다른 것을 보여주는 API 추가~~ -> 추후에 추가
  - [ ] ~~페이징 추가~~ -> 추후에 추가

Order

- [ ] 고객은 커피를 주문할 수 있음 (POST) - 화면 있음
  - [ ] 커피 메뉴 리스트 전달됨
  - [ ] 제공된 상품목록의 추가버튼을 통해 주문에 상품 추가
  - [ ] 필수적으로 이메일, 주소, 우편번호 입력받아 결제하기 버튼 클릭시 주문 생성
  - [ ] 총 금액을 제공받는다
  - [ ] 오후 2시 이전 주문들은 주문 번호로 따로 분리
- [ ] 자기가 주문한 주문내역을 볼 수 있음 (GET)
  - [ ] ~~페이징 기능 추가~~
- [ ] 자기가 주문한 커피의 수량을 수정 할 수 있음 (PUT)
  - [ ] 배송 중일 때 수정 불가
- [ ] 고객은 주문을 취소할 수 있음 (DELETE)
  - [ ] 배송 중일때 취소 불가




### dependency

- springboot 3.3.3

```gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	runtimeOnly 'com.mysql:mysql-connector-j'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

### git convention

| 태그       | 설명                      |
|:---------|:------------------------|
| feat     | 새로운 기능 추가               |
| fix      | 버그 수정                   |
| refactor | 코드 리팩토링                 |
| style    | 주석 추가(코드 변경 X) 혹은 오타 수정 |
| docs     | README와 같은 문서 수정        |
| rename   | 파일, 폴더명 수정 혹은 이동        |

