On Error Resume Next

' create variable to store all HKLM subkeys
Dim sidArr

'Set the regisrty to search HKCU Hive
Const HKEY_LOCAL_MACHINE = &H80000002
'Set the local computer as the target
strComputer = "."
'Enumerate All subkeys in HKEY_LOCAL_MACHINE
objRegistry.EnumKey HKEY_LOCAL_MACHINE, "", arrSubkeys
'Set Key to find
ContractExpress = "\Software\BusinessIntegrity"
Set oReg = GetObject("winmgmts:{impersonationLevel=impersonate}!\\.\root\default:StdRegProv")
oReg.EnumKey HKEY_LOCAL_MACHINE, "", sidList
For Each sid In sidList
' MsgBox appends sid data to sidArr variable, with a new line after each subkey
sidArr = sidArr & sid & vbCrlf
NEXT

Wscript.echo sidArr
