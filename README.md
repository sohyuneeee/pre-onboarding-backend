# wanted-pre-onboarding-backend
<br><br>
## 1. 요구사항 분석


(1) 회사는 **채용공고**를 `등록`합니다.

(2) 회사는 **채용공고**를 `수정`합니다.

- 회사Id 이외 모두 수정 가능합니다.

(3) 회사는 **채용공고**를 `삭제`합니다.

(4) 사용자는 **채용공고** `전체 목록`을 확인할 수 있습니다.

(5) 사용자는 **채용공고**를 `검색`할 수 있습니다. (선택사항)

(6) 사용자는 **채용공고** `상세페이지`를 확인할 수 있습니다.

- 상세페이지에는 **“채용 내용”이 포함**되어 있습니다.
- 해당 회사가 올린 **다른 채용공고 목록**을 볼 수 있습니다. (선택사항)

(7) 사용자는 **채용공고**에 `지원`합니다. (선택사항)

- 사용자는 **1회만 지원** 가능합니다.
<br><br>
## 2. 컨벤션 규정


(1) 코드 컨벤션 규칙

- 클래스 : UpperCamelCase 사용
    
    변수 및 함수 : LowerCamelCase 사용
    
    약어 사용 지양
    
    한줄주석 `//` 여러줄 주석 `/** */`
    
    bracket(`{}`) 규칙 : `{}`앞 한 칸 띄어쓰기
    

(2) Git Commit 컨벤션

- 태그 첫 글자 대문자 표기
- 제목 유형: `타입: 제목` (제목 앞에 띄어쓰기)
    
    
    | 태그 | 설명 |
    | --- | --- |
    | Feat | 새로운 기능 추가 |
    | Fix | 버그 수정 |
    | Docs | 문서 수정 |
    | Style | 코드 스타일 변경 (코드 포맷팅, 세미콜론 누락 등 기능 수정이 없는 경우) |
    | Test | 테스트 코드 추가 |
    | Refactor | 코드 리팩토링 |
    | Build | 빌드 파일 수정 |
    | Chore | 빌드 업무 수정, 패키지 매니저 수정 (gitignore 수정 등) |
    | Rename | 파일, 폴더명 수정한 경우 |
    | Remove | 파일 삭제 한 경우 |
<br><br>
## 3. 데이터 모델링


- 개체: 사용자, 회사, 채용공고, 지원
- 연관 관계
    - `1 : N` 관계 : 회사-채용공고, 채용공고-지원, 사용자-지원
    - `N : 1` 관계 : 채용공고-회사, 지원-채용공고, 지원-사용자

<img width="989" alt="스크린샷 2023-10-12 오후 6 29 55" src="https://github.com/sohyuneeee/wanted-pre-onboarding-backend/assets/110372498/755196f5-e34e-45bc-afc6-27b7a413db68">

<br><br>

## 4. API 설계


| 분류 | 기능 | Method | URL | 비고 |
| --- | --- | --- | --- | --- |
| 회원 | 사용자 등록 | **POST** | `/api/member` |  |
| 회사 | 회사 등록 | **POST** | `/api/company` |  |
| 채용공고 | 채용공고 등록 | **POST** | `/api/recruit` |  |
| 채용공고 | 채용공고 수정 | **PUT** | `/api/recruit/{id}` |  |
| 채용공고 | 채용공고 삭제 | **DELETE** | `/api/recruit/{id}` |  |
| 채용공고 | 채용공고 목록 조회 | **GET** | `/api/recruit` |  |
| 채용공고 | 채용공고 상세 조회 | **GET** | `/api/recruit/{id}` | 선택사항 |
| 채용공고 | 채용공고 검색 | **GET** | `/api/recruit/search?keyword=` | 선택사항 |
| 지원 | 지원 | **POST** | `api/apply/{recruitId}/{memberId}` | 선택사항 |

<br><br>
### 01 회원 등록

```json
//Requst

{
  "username" : "username",
  "password" : "password"
}

//Response

{
    "success": true,
    "data": {
        "id": 2
    },
    "error": null
}
```

- 아이디 중복일 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
          "code":"DUPLICATION_USERNAME",
           "message": "중복된 아이디입니다."
        }
    }
    ```
    
<br><br>
### 02 회사 등록

```json
//Requst

{
  "companyName" : "원티드랩",
  "country" : "한국",
  "city" : "서울"
}

//Response

{
    "success": true,
    "data": {
        "companyId": 1,                   
        "companyName": "원티드랩",             
        "country": "한국",
        "city": "서울"    
    },
    "error": **null**
}
```

- 회사이름 중복일 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
           "code":"DUPLICATION_COMPANY", 
           "message": "이미 존재하는 기업입니다."
        }
    }
    ```
    
<br><br>
### 03 채용공고 등록

```json
//Requst

{
  "companyId" : 1,
  "position" : "백엔드 주니어 개발자",
  "reward" : 1000000,
  "content" : "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "techStack" : "Python"
}

//Response

{
    "success": true,
    "data": {
        "recruitId": 1,
        "companyId": 1,
        "position": "백엔드 주니어 개발자",
        "reward": 1000000,
        "content": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
        "techStack": "Python"
    },
    "error": null
}
```

- 회사Id 존재하지 않을 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code":"COMPANY_NOT_FOUND",
            "message": "등록되지 않은 기업입니다."
        }
    }
    ```
    
<br><br>
### 04 채용공고 수정

```json
//Requst

{
    "companyId" : 1,
    "position" : "백엔드 주니어 개발자",
    "reward" : 1500000, #변경
    "content" : "원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..", #변경
    "techStack" : "JAVA" #변경
}

//Response

{
    "success": true,
    "data": {
        "recruitId": 6,
        "companyId": 1,
        "position": "백엔드 주니어 개발자",
        "reward": 1500000, #변경됨
        "content": "원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..", #변경됨
        "techStack": "JAVA" #변경됨
    },
    "error": null
}
```

- 회사Id 존재하지 않을 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code":"COMPANY_NOT_FOUND",
            "message": "등록되지 않은 기업입니다."
        }
    }
    ```
    
- 채용공고 존재하지 않을 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code": "RECRUITMENT_NOT_FOUND",
            "message": "채용공고가 존재하지 않습니다."
        }
    }
    ```
    
<br><br>
### 05 채용공고 삭제

```json
//Response

{
    "success": true,
    "data": "delete success",
    "error": null
}
```

- 채용공고 존재하지 않을 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code": "RECRUITMENT_NOT_FOUND",
            "message": "채용공고가 존재하지 않습니다."
        }
    }
    ```
    
<br><br>
### 06 채용공고 목록 조회

```json
//Response

{
    "success": **true**,
    "data": [
        {
            "recruitId": 1,
            "companyName": "원티드랩",
            "country": "한국",
            "city": "서울",
            "position": "백엔드 주니어 개발자",
            "reward": 1500000,
            "techStack": "JAVA"
        },
        {
            "recruitId": 3,
            "companyName": "네이버",
            "country": "한국",
            "city": "판교",
            "position": "백엔드 주니어 개발자",
            "reward": 500000,
            "techStack": "Python"
        }, …
    ],
    "error": null
}
```
<br><br>
### 07 채용공고 상세 조회

```json
//Response

{
    "success": true,
    "data": {
        "recruitId": 3,
        "companyName": "네이버",
        "country": "한국",
        "city": "판교",
        "position": "백엔드 주니어 개발자",
        "reward": 500000,
        "techStack": "Python",
        "content": "네이버에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..",
        "recruitIdList": [
            7
        ]
    },
    "error": null
}
```

- 채용공고 존재하지 않을 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code": "RECRUITMENT_NOT_FOUND",
            "message": "채용공고가 존재하지 않습니다."
        }
    }
    ```
    
<br><br>
### 08 채용공고 검색

```json
//Response

{
    "success": true,
    "data": [
        {
            "recruitId": 1,
            "companyName": "원티드랩",
            "country": "한국",
            "city": "서울",
            "position": "백엔드 주니어 개발자",
            "reward": 1500000,
            "techStack": "JAVA"
        },
        {
            "recruitId": 6,
            "companyName": "원티드랩",
            "country": "한국",
            "city": "서울",
            "position": "백엔드 주니어 개발자",
            "reward": 1500000,
            "techStack": "JAVA"
        }
    ],
    "error": null
}
```

- 해당하는 채용공고가 존재하지 않을 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code": "NOT_FOUND",
            "message": "데이터가 존재하지 않습니다."
        }
    }
    ```
    
<br><br>
### 09 지원

```json
//Response

{
    "success": true,
    "data": {
        "applyId": 1,
        "recruitId": 1,
        "memberId": 1
    },
    "error": null
}
```

- 해당 멤버가 존재하지 않을 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code": "MEMBER_NOT_FOUND",
            "message": "사용자를 찾을 수 없습니다."
        }
    }
    ```
    
- 채용공고 존재하지 않을 경우
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code": "RECRUITMENT_NOT_FOUND",
            "message": "채용공고가 존재하지 않습니다."
        }
    }
    ```
    
- 중복 지원 시
    
    ```json
    //Response
    
    {
        "success": false,
        "data": null,
        "error": {
            "code": "DUPLICATION_APPLY",
            "message": "지원은 한 번만 가능합니다."
        }
    }
    ```
