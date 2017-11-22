cd %~dp0
echo %~dp0
:aaa
java -jar TestMoveFile.jar D:\fileFolder D:\POS1

timeout  /t 10 /nobreak
goto aaa