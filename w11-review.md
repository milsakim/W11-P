<데이터베이스 프로그래밍 11주차 회고록>
=============================

## 0. 시연 영상
[![](http://img.youtube.com/vi/EdJkROOCtfM/0.jpg)](http://www.youtube.com/watch?v=EdJkROOCtfM "")

## 1. 새로 배운 내용
* Docker 명령어
  * `docker ps [-a]`
  * `docker start <name>`
  * `docker exec -it <name> sqlplus`
* `lsof` 명령어
* ROWNUM
* Class.forName()
* Connection
  * java.sql.Connection
  * 인터페이스임.
  * 특정 데이터베이스와의 연결 세션을 의미함. 이 connection이 있어야 SQL문이 처리되고 결과를 반환할 수 있음.
* Statement와 PreparedStatement
  * PreparedStatement
    * Statement 인터페이스를 extends 한 인터페이스임.
    * precompile된 SQL문을 의미함. 즉, SQL문을 precompile 해서 PreparedStatement 객체로 저장할 수 있음.
    * 메서드
      * `executeUpdate()`
* ResultSet
* SQLException
* Java try-catch-finally
* Oracle sqlplus line width 설정
  * `SET LIN[ESIZE] <숫자>;`

## 2. 발생한 문제 & 해결 과정
* 오라클이 사용해야하는 포트를 다른 프로세스가 사용 중인 문제
  * `sudo lsof -i :포트번호`
    * 해당 포트를 사용하고 있는 프로세스를 확인할 수 있음.
  * `sudo kill -9 프로세스ID`
    * `sudo lsof -i :포트번호`로 확인한 PID를 이용해 해당 포트를 사용하고 있는 프로세스를 죽임.
* rownum 이용한 SELECT시 도민준이 안 나오는 문제
  * `SELECT * FROM (SELECT rownum, e.* FROM employees e ORDER BY rownum ASC) WHERE rownum = 1;`
* MacOS Big Sur 업데이트 후

## 3. 참고할 만한 내용
* Mac에서 Docker 이용하여 Oracle 설치하기
  * https://namubada.net/282
  * https://clearstar0817.tistory.com/11
* [Docker 명령어](https://jungwoon.github.io/docker/2019/01/11/Docker-1/)
* [리눅스 lsof 명령어](https://itmore.tistory.com/entry/리눅스-lsof-란)
* [특정 port 사용 중인 프로세스 확인 & 종료 방법](https://new93helloworld.tistory.com/138)
* Oracle ROWNUM
  * https://micropilot.tistory.com/134
  * https://tjdghkwnd1.tistory.com/entry/ROWNUM-이란
  * https://egipyo21.tistory.com/24
* https://developer.android.com/reference/kotlin/java/sql/PreparedStatement
* https://www.oreilly.com/library/view/oracle-sqlplus-the/0596007469/re69.html
* http://frommyhead.weebly.com/home/setting-the-sqlplus-page-and-row-size-in-oracle


## 4. 회고
* (+) 맥에 docker를 이용해서 oracle을 설치해 실습을 진행했음.
* (-)
* (!)
