# Yoummunity
Youtube Community

## Style Guides
### Kotlin Code Style
[Android Developers Kotlin style guide](https://developer.android.com/kotlin/style-guide)

### XML Naming Style
##### Naming 규칙
`(WHAT)* WHERE (DESCRIPTION)* (SIZE)*`

* `WHAT` 자원 종류; e.g., activity
* `WHERE` 자원 생성/사용되는 위치; 여러 화면에서 사용되는 경우 all; e.g., main
* `DESCRIPTION` 같은 종류 자원 구분을 위한 이름; e.g., title
* `SIZE` 자원 크기 정보; e.g., 24dp

##### XML Naming Style 사용 예시
* 레이아웃 `WHAT_WHERE`
* String `WHERE_DESCRIPTION`
* Drawable `WHERE_DESCRIPTION_SIZE`
* 위젯id `WHAT_WHERE_DESCRIPTION`
* dimension `WHAT_WHERE_DESCRIPTION_SIZE`


### Commit Message Guide
##### Message 규칙
영어 소문자로 작성; 고유명사 첫 글자만 대문자, 그 외의 경우 모두 소문자

메세지 맨 앞에 아래 [Emoji 목록](#emoji-목록) 중 하나 써서 커밋하기

사용예시 `:sparkles: init project`

##### Emoji 목록
* :sparkles: `:sparkles:` 새로운 브랜치 추가, 초기화(initialize)
* :arrow_up: `:arrow_up:` 새로운 기능 추가
* :rocket: `:rocket:` 속도 개선
* :art: `:art:` 코드 형식/구조 개선
* :warning: `:warning:` 메모리 사용 변경, 변수명 변경
* :memo: `:memo:` 주석 작성, documentation
* :tada: `:tada:` merge 및 충돌(conflicts) 해결
* :bug: `:bug:` 버그 해결
* :lock: `:lock:` 보안 이슈 해결
