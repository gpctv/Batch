 Dim fso
 Dim newpath
 Dim ws
 Dim a
Set ws=WScript.CreateObject("WScript.Shell")
Set fso=CreateObject("Scripting.FileSystemObject")
newpath = fso.GetAbsolutePathName(".")
a=ws.run("cmd.exe /C "& newpath&"\"&"TestKeyPress.bat",0,true)