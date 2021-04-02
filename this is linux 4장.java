===========================4장 chapter1=========================

시작과 종료

 * 종료
   - poweroff, shutdown -P now, halt -p , init 0

 * 시스템 재부팅
   - shutdown -r now , reboot  init 6

 * 로그아웃
   - logout 또는 exit


가상 콘솔
 * 쉽게 '가상의 모니터' 라 생각하면 됨. 우분투는 총 6개의 가상 콘솔을 제공

 * 각각의 가상 콘솔로 이동한ㄴ 단축키는 Ctrl + Alt + F2 ~ F7 (Ctrl + Alt + F2 은 X윈도우 모드(Desktop Version))



===========================4장 chapter2=========================

Run Level


Run Level               영문 모드                  설명                                 비고
   0                     Power Off               종료 모드      
   1                     Rescue               시스템 복구 모드                     단일 사용자 모드
   2                     Multi-User                                                   사용하지 않음
   3                     Multi-User         텍스트 모드의 다중 사용자 모드
   4                     Multi-User                                                   사용하지 않음
   5                     Graphical         그래픽 모드의 다중 사용자 모드         
   6                     Reboot

<실습2>

cd /lib/systemd/system
ls -l runlevel?.target
ls -l default.target

// 
ln -sf /lib/systemd/systemd/multi-user.target /lib/systemd/system/default.target

startx

ln -sf /lib/systemd/system/graphical.target   /lib/systemd/system/default.target


자동 완성과 히스토리
   
   * 자동 완성 :: Tab
   * 방향기 상하로 이전에 썼던 명령어를 쓸 수 있음

history // 기존에 썼던 명령어 들이 나온다.
history -c // clear 된다.

===========================4장 chapter3 (gedit, nano, vi 기본 사용법)=========================

gedit :: 윈도우에 메모장과 같다. (x윈도우 한정 사용)

nano :: 어디서든 사용 가능 (ctrl + c :: 행 번호)
// nano -c fileName :: 행 번호가 항상 나온다.

vi :: 어디서든 사용 가능
// q! : 변경내용 저장하지 않고 나옴


===========================4장 chapter4 (vi 비정상 종료, CD-Rom 마운트)=========================



vi 기능요약 :: 161p

도움말 사용법 :: man

ex) man ls 


===========================4장 chapter5 (필수 개념과 명령어 - 리눅스 기본 명령어)=========================

1. ls

2. cd 

3. pwd

4. rm (제일 중요하고 유의 해야함)
   
   rm -r dir // 디렉터리 까지 지워짐
   rm -i abc.txt  // 정말 삭제할 것인지 확인 메시지가 나옴
   rm -rf abc // abc 디렉터리와 그 아래의 하위 디렉터리를 강자로 전부 삭제, 편리하지만 상당히 주의 해야한다.


5. cp :: 파일이나 디렉터리를 복사

6. touch :: 새 파일을 만들거나, 이미 존재한다면 파일의 최종 수정시간이 변한다.

7. mv :: 파일이나 디렉터리를 이름을 변경하거나 디렉터리로 옮길 수 있다.

8. mkdir :: 디렉터리를 만든다.

9. rmdir :: 디렉터리를 삭제한다. (단, 하위 디렉터리가 있다면 삭제할 수 없음)

10. cat :: 파일 내용을 보여준다.

11. head :: 상위 10줄만 보여준다.
   // head -3 abc.txt :: 상위 3줄만 보여준다.

12. tail :: 하위 10줄만 보여준다.

13. more :: 텍스트 형식으로 작성된 파일을 페이지 단위로 화면에 출력한다. space bar 를 누루면 다음페이로 이동
         B를 누루면 앞 페잊로 이동. Q 를 누르면 종료

14. less ::   more 명령어와 용도가 비슷하지만 기능이 더 확장 되어있다. Page Up | Down 키를 사용 할 수 있다.


15. file :: 해당 파일이 어떤 종류의 파일인지 표시해준다.

16. clear :: 터미널 화면을 깨끗하게 지워준다.


===========================4장 chapter6 & 7 (필수 개념과 명령어 - 사용자와 그룹)=========================


사용자와 그룹(1)
 * 리눅스느 다중 사용자 시스템임
 * 기본적으로 root 라는 이름을 가진 슈퍼유저가 있으며, 모든 작업을 할 수 있는 권한이 있음
 * 모든 사용자를 하나 이상의 그룹에 소속되어 있음
 * 사용자는 /etc/passwd 파일에 정의 되어 있음
 * 각 행이 의미는 다음과 같음
   사용자 이름:암호:사용자 ID:사용자가 소속된 그룹 ID:추가 정보:홈 디렉터리:기본 셸

사용자와 그룹(2)
 * 사용자의 비밀번호는 /etc/shadow 파일에 정의되어 있다.
 * 그룹은 /etc/group 파이렝 정의되어 있음
 * 각 행이 의미는 다음과 같음
   그룹명:비밀번호:그룹 id:보조 그룹 사용자

사용자와 그룹 관련 명령어

* adduser :: 새로운 사용자를 추가
  >> 사용자 생성시 옵션
  --uid : ID 지정 // $ adduser --uid 1111 user02 :: user02 사용자를 생성하면서 사용자의 아이디는 1111로 지정
  --gid : 그룹 지정 // $ adduser --gid 1000 user01 :: user01 사용자 생성, 그룹 아이디가 1000인 곳에 포함시킴
  --home : 홈 디렉터리 지정 // $ adduser --home /newhome user01 :: user01 사용자 생성, 홈디렉터리는 /newhome으로 지정
  --shell : 셸 지정 // $ adduser --shell /bin/csh newuser5 :: newuser5 사용자 생성, 기본쉘은 /bin/csh 로 지정

* passwd :: 사용자의 비밀번호를 지정하거나 변경 // $ passwd user01

* usermod :: 사용자의 속성을 변경	// $ usermod --shell /bin/cash user02 
								// $ usermod --groups ubuntu user02 :: user02 사용자의 보조 그룹에 ubuntu 그룹 추가 

* userdel :: 사용자를 삭제	// $ userdel user01
							// $ userdel -r user01 :: 사용자의 홈 디렉터리 까지 삭제

* change :: 사용자의 암호를 주기적으로 변경하도록 설정	// $ change -l user01 :: user01의 사용자에 설정된 사항 확인

													// $ change -m 2 user01 :: user01 사용자에 설정한 암호를 사용해야 하는 최소 일지
																				(즉, 변경 후 최소 2일은 사용해야 함)

													// $ change -M 30 user01 :: 사용자에 설정한 암호를 사용할 수 있는 최대 일자
																				(즉, 변경 후 최대 30일 까지 사용 할 수 있음)

													// $ change -E 2026/12/12 user01 :: 사용자가 설정한 암호 만료일은 2026/12/12
																					
													// $ change -W 10 user01 :: 사용자에 설정한 암호가 만료되기 전에 경고하는 기간.
																				지정하지 않을 경우 기본값은 7일
																				(즉, 이와같이 설정하면 암호가 만료되기 10일 전부터 경고 메시지가 나감)

* groups :: 현재 사용자가 속한 그룹을 보여줌 // $ groups :: 현재 사용자가 소속된 그룹을 보여줌
										 // $ groups newuser1 :: newuser1 사용자가 소속된 그룹을 보여줌


* groupadd :: 새로운 그룹을 생성	// $ groupadd newgroup1 :: newgroup1 그룹 생성
								// $ groupadd --gid 2222 newgroup2 :: newgroup2 그룹을 생성하면서 그룹 ID를 2222로 지정

* groupmod :: 그룹의 속성을 변경	// $ groupmod --new-name mygruop1 newgroup1 :: newgroup1 그룹의 이름을 mygruop1 으로 바꾼다. 

* groupdel :: 그룹을 삭제 (그룹 안에 사용자가 없어야 한다.)	// $ groupdel newgroup2 :: 그룹을 삭제한다. 단, 해당 그룹을 주요 그룹으로 지정한 사용자가 없어야한다.)

* gpasswd :: 그룹의 암호를 설정하거나, 그룹의 관리를 수행	// $ gpasswd mygroup1 :: mygroup1 암호 지정
													// $ gpasswd -A user01 mygroup1 :: user01 사용자를 mygroup1 그룹 관리자로 지정
													// $ gpasswd -a user01 mygroup1 :: user01 사용자를 mygroup1 그룹의 사용자로 추가
													// $ gpasswd -d user01 mygroup1 :: user01 사용자를 mygroup1 그룹의 사용자에서 제거



===========================4장 chatper8 파일의 허가권과 소유권)=========================

- 파일 유형
	* 디렉터리일 경우에는 d, 일반적인 파일일 경우에는 -가 표시

- 파일 허가권
	r - read
	w - write 
	x - execute
	
 * 첫 번째 : 소유자(User)의 파일접근 권한
 * 두 번째 : 그룹(Group)의 파일접근 권한
 * 세 번째 : 사용자(Other)의 파일접근 권한

 숫자로도 표시 가능 (8진수)

 소유자(User)				그룹(Group)						그 외 사용자(Other)
 r	w	-					r	-	-							r	-	-
--------------------------------------------------------------------------------
 4	2	0					4	0	0							4	0	0
--------------------------------------------------------------------------------
	6							4									4
--------------------------------------------------------------------------------


- chmod 명령
	- 파일 허가권 변경 명령어
	- 예) $ chmod 777 sample.txt

- 파일 소유권
	- 파일을 소유한 사용자와 그룹을 의미
	- chown/chgrp 명령 :: 파일의 소유권을 바꾸는 명령어
	- $ chown ubuntu.ubuntu sample.txt 
	  $ chown ubuntu smaple.txt // 소유주만
	  $ chgrp ubutnu smaple.txt // 그룹만


===========================4장 chatper9 링크 =========================

링크 
	* 파일의 링크에는 하드링크와 심볼릭 링크 두 가지가 있음


* 하드 링크를 생성하면 "하드 링크파일"만 하나 생성되며 같은 indoe1 을 사용
	(명령 : $ ln 링크대상파일이름 링크파일 이름)

* 심볼릭 링크를 생성하면 새로운 indoe2를 만들고, 데잍터는 원본 파일을 연결하는 효과 
	(명령 : $ ln -s 링크대상파일이름 링크파일 이름)
	(※ 심볼릭 링크는 Windows의 바로가기 아이콘과 개념이 비슷하다.)


* 원본파일 수정시 하드 심볼릭 둘다 수정된다.
* 원본 삭제 또는 이동시 하드는 사용가능(유지) 심볼릭은 사용 불가



===========================4장 프로그램 설치를 위한 dpkg=========================

dpkg
 - windows의 setup.exe 와 비슷한 설치 파일
 - 확장면은 *.deb이며, 이를 패키지(package)라고 부름.

파일의 의미
 - 패키지이름_버전-개정번호-아키텍처.deb


dpkg 명령어 옵션
 - 설치
	* dpkg -i 패키지파일이름.deb
 - 삭제
 	* dpkg -r 패키지 이름
	* dpkg -P 패이키 이름 // 설정파일까지 삭제

 - 패키지 조회
	* dpkg -l 패키지 이름 // 설치된 패키지에 대한 정보를 보여줌
	* dpkg -L 패키지 이름 // 패키지가 설치한 파일 목록을 보여줌

 - 아직 설치되지 않은 deb 파일 조회
	* dpkg --info 패키지파일이름.deb // 패키지 파이렝 대한 정보를 보여줌


 - dpkg 명령의 단점
 	* 의존성문제
		// A패키지가 설치되기 위해서 B패키지가 필요한 경우, dpkg 명령으로는 해결하기 까다로움
	* 이를 해결하기 위해 apt 가 등장함

// 결론 : dpkg의 의존성 문제로 인해 dpkg 보단 apt 를 더 많이 쓴다. 


===========================4장 편리한 패키지 설치 apt =========================

apt 
 - dpkg 명령의 패키지 의존성 문제를 완전하게 해결됨.
 - 인터넷을 통하여 필요한 파일을 저장소에서 자동으로 모두 다운로드해서 설치하는 방식

apt 기본적인 사용법
 - 기본 설치 : apt install 패키지 이름
  * 주로 "apt -y install 패키지이름" 으로 사용한다.
  * 패키지 목록의 업데이트 : apt update 
  * 삭제 : apt remove / purge 패키지 이름
  * 사용하지 않는 패키지 제거 : apt autoremove
  * 내려 받은 파일 제거 : apt clean 또는 apt autoclean


apt-cache
 - 패키지를 설치하기 전에 패키지에 대한 정보나 의존성 문제를 미리 확인
 - 패키지 젇보 보기 // apt-cache show 패키지이름
 - 패키지 의존성 확인 // apt-cache depends 패키지이름
 - 패키지 역의존성 확인 // apt-cache rdepends 패키지 이름 ?? 누구누구한테 필요한지.. 
  

>> apt가 UI 가 더 이쁘고 유익한 메시지를 출력 받기 때문에 apt 로 쓰자

// sudo apt update 
>> 설치되어 있는 패키지를 새 버전을 업그레이드

// sudo apt dist-upgrade
>> 의존성 검사하면 업그레이드

// sudo add-apt-repository <저장소이름>
>> 저장소 추가

// sudo add-apt-repository --remove <저장소 이름>
>> 저장소 제거

// sudo apt install <패키지 이름>
>> 패키지 설치 ex) sudo apt intall vim

// sudo apt reinstall install <패키지 이름>
>> 패키지 재설치

// sudo apt remove <패키지 이름>
>> 패키지 삭제 (설정 파일은 지우지 않음)

// sudo apt --purge remove <패키지 이름>
>> 패키지 삭제 (설정 파일 포함)

// sudo apt source <패키지 이름>
>> 패키지 소스코드 다운로드

// sudo apt build-dep <패키지 이름>
>> 받은 소스코드를 의존성 있게 빌드

// sudo apt-cache search <패키지 이름>
>> 패키지 검색

// sudo apt-cache show <패키지 이름>
>> 패키지 정보 보기








