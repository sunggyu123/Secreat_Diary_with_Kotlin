# Secreat_Diary_with_Kotlin

비밀번호 입력 기능, 비밀 번호 설정 기능을 활용하여 나만의 시크릿 다이어리를 제작.

비밀 번호가 틀렸을시에 팝업 화면, 메세지를 출력하는기능

비밀번호 설정시 비밀번호 틀린 상태로 설정 불가 하도록 설정

1. ConstrainLayout
2. SharedPreference
3. Handler
4. Theme
5. AlertDialog

## SharedPreference
▶ getSharedPreference("name",MODE)

▶ edit 이라는 kt function 사용하여 데이터를 저장


## commit 과 apply 의 차이점

▶ commit은 ui thread를 멈추고 저장될때까지 기다린다

▶ apply는 반대.

## Theme

▶ NoActionBar사용

## AlertDialog

▶ Builder를 사용하여 비밀 번호 실패시 나오게 설정

## Handler

▶ Thread 간의 통신 기능

▶ runOnUi기능을 사용

▶ 다이어리 안의 글을 적은것이 앱이 꺼졌을때 사라지지 않는 작업을 함

## 완성.
