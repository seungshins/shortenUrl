# seungshins's shorten URL

프로젝트 구상 : 
* Spring Boot + H2 + JPA를 기반으로 생성
* War로 빌드하여 Tomcat으로 구동
* Java는 1.8, Tomcat 8버전

메인 설계 : 
* 특정 Flag를 두고 (/F, /G 등으로 )시작하는 8자리 문자의 Shorten URL을 만들자
* 1자리의 Fix된 문자를 제외한 7자는 Base Encoding을 이용
* 특수문자 및 헷갈리기 쉬운 문자를 제외한 Bitcoinj의 Base 58로 Enc/Dec
* 중복되서 들어오는 원본 URL은 생성하지 않는다
* Encript의 기본은 특정 Flag를 Padding값으로 한 6자리의 Index값 (ex. YYYY21 : Index 21인 경우)
* Decript시에는 특정 Flag 1번째 문자를 제외하고 7자리의 Encrypt된 문자열을 보고 Padding값을 제외하고 Index를 얻음

