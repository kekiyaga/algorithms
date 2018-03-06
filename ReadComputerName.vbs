' Lianne Wong
' Jan. 10, 2014
' Read computer name from the registry and outputs registry value to console
' HEADER
option explicit
' declare 2 variables for every registry read
dim regComputerName, computerName
dim regUserName, userName
' used for WScript.Shell
dim objShell 
'REFERENCE
'location of the registry
' use copyKeyName from the regedit and use variable name of REG_SZ
' can use abbreviattions HKEY_LOCAL_MACHINE or HKLM,  HKEY_CURRENT_USER or HKCU
regComputerName = "HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\ComputerName\ComputerName\ComputerName"
regUserName = "HKEY_CURRENT_USER\Volatile Environment\USERNAME"
' initialize WScript.Shell
set objShell = CreateObject("WScript.Shell")

' WORKER
'read the registry
computerName = objShell.RegRead(regComputerName)
userName = objShell.RegRead(regUserName)

WScript.Echo "Registry key at location " & regComputerName & vbCrlf & " is " & computerName & vbcrlf & _
"Registry key at location " & regUserName & vbCrlf & " is " & userName

if userName = "administrator" then
	WScript.Echo "You are the administrator"
else
	WScript.Echo "You are NOT the administrator"
end if
