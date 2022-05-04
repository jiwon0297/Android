# 🏫 슬기로운 단국생활
> 단국대학교 재학생의 편의를 위한 서비스 어플리케이션 📱

대부분의 학교에는 그 학교를 위한 많은 커뮤니티가 존재하지만, 대형 커뮤니티 특성상 대부분의 사용자들이 특정 게시판만 이용을 하기 때문에 활성화되기 원하는 게시판들이 몇 가지 있었습니다. 주변 대학생들의 의견을 들어보았을 때 현재 상용화된 어플리케이션은 너무 방대한 정보들 때문에 원하는 정보들이 묻힌다는 의견이 많았고, 사담보다는 실질적으로 이득을 얻을 수 있는 실용적인 서비스를 원하고 있었습니다.

따라서, '슬기로운 단국생활'은 재학생 설문조사를 기반으로 필요하다고 가장 많이 언급된 서비스 2가지를 제공하고, '단쿠키'의 닉네임제도와 '에브리타임'의 카테고리 서비스를 접목함과 동시에 단국대학교 전용 어플리케이션이라는 이름에 맞게 각 캠퍼스 근처 식당 및 카페의 정보와 위치를 제공하는 서비스를 추가로 구현하였습니다.

<img width="696" alt="스크린샷 2022-05-04 10 54 03" src="https://user-images.githubusercontent.com/63248831/166613560-d0ada5c0-f0b5-43fa-92a6-c106224e263b.png">


## 프로젝트 정보
|기술 스택|<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white"> <img src="https://img.shields.io/badge/express-000000?style=for-the-badge&logo=express&logoColor=white"> <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">|
|---|---|

|이름|역할|이름|역할|
|---|---|
|이승미|1. User 데이터베이스 설계 및 구축 <br>2. MyPage UI 및 정보출력 구현 <br>3. MyPage관련 Node JS 코드 작성 <br>4. 서버 관리 및 구축(RDS, EC2) <br>5. 로그인/회원가입 기능 <br>6. Push 알람 기능 <br>7. 이메일 인증기능|배정원|1. Home, Lost 게시판 UI 및 기능 구현 <br>2. Lost 게시글, 댓글 데이터베이스 설계 및 구축 <br>3. 게시판 이미지 업로드 구현 <br>4. Lost 게시판 관련 Node JS 코드 작성 <br>5. 회원가입 유효성 검사|
|박지원|1. Main, Home, Mate, Mail UI 구현 <br>2. 이메일 인증 및 쪽지 기능 구현 <br>3. Mate 게시글, 댓글, 쪽지 데이터베이스 설계 및 구축 <br>4. Mate 기능 구현 <br>5. Mate 게시판 관련 Node JS 코드 작성 <br>6. 전체적인 화면, 앱아이콘 설계 및 디자인 |김진하|1. Restaurant 관련 UI 및 기능 구현 <br>2. 서버 관리 및 구축(RDS, EC2) <br>3. Restaurant 관련 Node JS 코드 작성 <br>4. Home 화면 식당 및 카페 추천 서비스 구현 <br>5.Restaurant 기능 내 위치 서비스 기능 추가|

**개발환경**


<img width="629" alt="스크린샷 2022-05-04 11 10 02" src="https://user-images.githubusercontent.com/63248831/166614571-917681f8-3ced-4bb1-9489-b2e6f2781c9c.png">


## 프로젝트 요구사항
|**적용 대상**|**요구 기능**|**세부 요구 조건**|
|---|---|---|
|Home|추천 기능|추천서비스 사용 시 캠퍼스별로 나누어져 있어야 한다.|
|Login/Join|유효성검사<br>이메일인증<br>자동로그인|이메일, 닉네임이 기존회원가 중복되지 않아야 하고 이메일은 오직 단국대 이메일이어야 한다.<br>오직 단국대 이메일로 인증받아야 하며, 제한시간 안에 인증이 완료되어야 한다.<br>어플 재접속시 자동로그인이 되어야한다.|
|Mate/Lost|게시판기능<br>이미지업로드<br>검색<br>쪽지|로그인한 사용자와 작성자가 같을 경우에만 관리가 가능해야 한다.<br>글 작성 시 업로드가 되어야하며, 사진은 카메라와 앨범 모두 가능토록 한다<br>검색시 검색어를 포함하는 제목이 검색이 되어야하고, 각 게시판에 해당하는 글만 검색이 되어야한다.<br>자동으로 sender와 receiver가 생성되어야 한다.|

**Flow Chart**


<img width="786" alt="image" src="https://user-images.githubusercontent.com/63248831/166615060-522aa7cd-9e9a-4cf7-9926-212a552d5e2b.png">


**데이터베이스 구조**




## 개발 환경 설정


## 업데이트 내역
