# SpringBoot-Crawling-Project
스프링 부트 + 파이썬을 활용한 스트레칭 추천 사이트


## 🖥️ 프로젝트 소개
스프링 부트와 파이썬을 활용하여 유튜브에서 크롤링한 데이터를 가공하여 DB에 업로드 후 동영상을 추천하여 보여주는 웹 프로젝트 입니다.
<br>

## 🕰️ 개발 기간
* 23.10.11일 - 23.11.15일

### 🧑‍🤝‍🧑 맴버구성
 - 팀장  : 윤진권 - 
 - 팀원1 : 김현기 - 백엔드, 관리자 페이지 제작, 백엔드-프론트 타임리프 연결
 - 팀원2 : 김나래 - 
 - 팀원3 : 안민수 - 

### ⚙️ 개발 환경
- `Java 17`
- `JDK 1.8.0`
- **IDE** : STS 4.2
- **Framework** : Springboot(3.1.4)
- **Database** : MySQL 8.0
- **ORM** : Spring Data JPA

## 📌 주요 기능
#### 메인 페이지
![image](https://github.com/kag0330/strching/assets/65347323/f2f32e26-15e6-440c-bc67-90aae54aec50)
- 최신 영상 표시
- 인기 영상 표시
- 인기 상품 표시
#### 프로그램 페이지
![image](https://github.com/kag0330/strching/assets/65347323/ebc28fa0-49d8-4523-9344-d76228e910a3)
- 크롤링 데이터 표시
- 검색(Search) 기능
- 페이지네이션(Pagination) 기능
#### 회원가입
![image](https://github.com/kag0330/strching/assets/65347323/233bd5c3-c155-42bf-860d-592083ca7d40)
- ID 중복 체크
- PW 체크
#### 로그인 페이지
![image](https://github.com/kag0330/strching/assets/65347323/edb22c42-f165-4816-ac8e-a16d05488135)
- DB값 검증
- 로그인 시 세션(Session) 생성
#### 마이 페이지
![image](https://github.com/kag0330/strching/assets/65347323/42a6f8d7-7119-4be2-a2f5-5b17595fc2fd)
- 관리자 페이지
- 북마크(즐겨찾기) 페이지
- 회원 탈퇴
#### 관리자 페이지
![image](https://github.com/kag0330/strching/assets/65347323/b8e3f548-138d-4f38-8bda-fc8798e4ed7c)
- 동영상 크롤링 페이지
- 동영상 추가 페이지
- 동영상 삭제 페이지
- 유저 권한 설정 페이지
#### 동영상 크롤링 페이지
![image](https://github.com/kag0330/strching/assets/65347323/a8af68a0-8709-49c8-bceb-2345933e0c11)
- 크롤링
- 검색어.Json 확인
#### 동영상 추가 페이지
![image](https://github.com/kag0330/strching/assets/65347323/49e47325-bf84-4cb9-8903-41f13065fd0d)
- 크롤링 Json 파일 선택
- 크롤링 데이터 업로드
#### 동영상 삭제 페이지
![image](https://github.com/kag0330/strching/assets/65347323/6eac8a5a-d6e5-4219-b762-1564765287eb)
- 동영상 삭제
#### 유저 권한 설정 페이지
![image](https://github.com/kag0330/strching/assets/65347323/a1545103-6cb7-4f5d-b8a7-a439fcfec7f1)
- 유저 정보 확인
- 유저 권한 변경
- 유저 삭제
