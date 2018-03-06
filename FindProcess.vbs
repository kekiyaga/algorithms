' User:		Kuteesa Kiyaga
' Date:		September 15,  2017
' Function:	VB Script to Find Heat Virus Process
' ================================================================
set service = GetObject ("winmgmts:")

Dim outputStr

for each Process in Service.InstancesOf ("Win32_Process")
	If Process.Name = "Virus Heat 3.9.exe" then
		wscript.echo "Virus Heat 3.9.exe.  You have the Virus Heat.  "
		wscript.quit
		outputStr = outputStr & "  Virus Heat 3.9.exe.  You have the Virus Heat.  "
	ElseIf Process.Name = "wuuawkz.dll" then
		wscript.echo "wuuawkz.dll.  You have the Virus Heat.  "
		wscript.quit
		outputStr = outputStr & "  wuuawkz.dll.  You have the Virus Heat.  "
	ElseIf Process.Name = "iinqyl.dll" then
		wscript.echo "iinqyl.dll.  You have the Virus Heat.  "
		wscript.quit
		outputStr = outputStr & "  iinqyl.dll.  You have the Virus Heat.  "
	Else
		wscript.echo "Your computer does not have the Heat Virus."
		wscript.quit
	End If
	wscript.echo outputStr
	wscript.quit
next
