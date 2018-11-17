--운영DB에는 없는 테이블
SELECT * FROM notexistsinprd;


--테스트 코드가 실행되기전 rangers 데이터 삭제
DELETE FROM rangers;


--rangers 초기 데이터 3건 입력
INSERT INTO rangers VALUES ('brown', '브라운');
INSERT INTO rangers VALUES ('cony', '코니');
INSERT INTO rangers VALUES ('sally', '샐리');


-- posts
DELETE FROM posts;


-- board
DELETE FROM board;


--jspuser
DELETE FROM jspuser;


-- 5건
INSERT INTO jspuser VALUES ('brown','브라운','f01471c38113db263f9a532d8b6c054af31bf653aeea92d1c284cdd022b9','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/08/08','brown@gmail.con','123123123','/profile/brown.png');
INSERT INTO jspuser VALUES ('sally','샐리','65c8b7874e6e1d2bc0978232e2274f7764ac156926db381f74f7b46fc5dc','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/04/27','sally@gmail.con','123123123','/profile/sally.png');
INSERT INTO jspuser VALUES ('moon','문','03d1c81a2397bb51dface79a897decd50e7f4d8ea7fd547c8506d80e282905e','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/10/06','moon@gmail.con','123123123','/profile/moon.png');
INSERT INTO jspuser VALUES ('james','제임스','249bbac76e15fc9f93dba1ba0b5b7cc5a4b6f2a7d57a4a50b944dbd45ecd41','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/02/14','james@gmail.con','123123123','/profile/james.png');
INSERT INTO jspuser VALUES ('minions','미니언즈','fce41cf036529bd2cd7fd9e865a0c68a67514f2b9ddcedb1e4824fb4e2','대전시 중구 대흥동 76','2층 대덕인재개발원','34940','18/02/14','james@gmail.con','123123123','/profile/minions.png');


-- 월단위 달력 생성 데이터 초기화
DELETE FROM calendar WHERE ymd LIKE '201811%';


-- 7건
INSERT INTO board VALUES ('00004','minions','자유게시판','Y','18/10/21');
INSERT INTO board VALUES ('00002','minions','QnA게시판','Y','18/10/21');
INSERT INTO board VALUES ('00015','minions','테스트게시판','Y','18/10/21');
INSERT INTO board VALUES ('00016','minions','미사용게시판','N','18/10/21');
INSERT INTO board VALUES ('00017','minions','테스트게시판2','N','18/10/21');
INSERT INTO board VALUES ('00018','minions','시나리오게시판1','N','18/10/26');
INSERT INTO board VALUES ('00019','minions','시나리오게시판2','Y','18/10/26');

