# 항해99 Spring 개인과제
#### 12기 B반 황원준

## UseCase Diagram
![UserDiagram ver 2](https://user-images.githubusercontent.com/122272525/220943219-80b89040-d884-48b9-b87e-4a233da3d074.png)

## API설계
https://docs.google.com/spreadsheets/d/1zR2KvZRiiOZI7CO4XUt8jO9o0KdpB0YE1VMBEKt0704/edit?usp=sharing

## ERD설계
![HangHae99Blog](https://user-images.githubusercontent.com/122272525/220943327-08fd67f4-813a-4195-96ae-0169807e9d99.png)

## 요구사항
### 1. 회원 가입 API
    - username, password를 Client에서 전달받기
    - username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자`로 구성되어야 한다.
    - DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
    - 회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 게시글, 댓글 수정 / 삭제 가능

### 2. 로그인 API
    - username, password를 Client에서 전달받기
    - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
    - 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
      발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기

### 3. 전체 게시글 목록 조회 API
    - 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
    - 각각의 게시글에 등록된 모든 댓글을 게시글과 같이 Client에 반환하기
    - 댓글은 작성 날짜 기준 내림차순으로 정렬하기
    - 게시글/댓글에 ‘좋아요’ 개수도 함께 반환하기

### 4. 게시글 작성 API
    - Spring Security 를 사용하여 토큰 검사 및 인증하기!
    - 제목, 작성자명(username), 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기

### 5. 선택한 게시글 조회 API
    - 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기 
      (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
    - 선택한 게시글에 등록된 모든 댓글을 선택한 게시글과 같이 Client에 반환하기
    - 댓글은 작성 날짜 기준 내림차순으로 정렬하기
    - 게시글/댓글에 ‘좋아요’ 개수도 함께 반환하기    

### 6. 선택한 게시글 수정 API
    - Spring Security 를 사용하여 토큰 검사 및 인증하기!
    - 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
    - 게시글에 ‘좋아요’ 개수도 함께 반환하기

### 7. 선택한 게시글 삭제 API
    - Spring Security 를 사용하여 토큰 검사 및 인증하기!
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기  

### 8. 댓글 작성 API
    - Spring Security 를 사용하여 토큰 검사 및 인증하기!
    - 선택한 게시글의 DB 저장 유무를 확인하기
    - 선택한 게시글이 있다면 댓글을 등록하고 등록된 댓글 반환하기

### 9. 댓글 수정 API
    - Spring Security 를 사용하여 토큰 검사 및 인증하기!
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기
    - 댓글에 ‘좋아요’ 개수도 함께 반환하기

### 10. 댓글 삭제 API
    - Spring Security 를 사용하여 토큰 검사 및 인증하기!
    - 선택한 댓글의 DB 저장 유무를 확인하기
    - 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기     
