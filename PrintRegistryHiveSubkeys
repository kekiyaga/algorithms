On Error Resume Next

' create variable to store all HKLM subkeys
Dim sidArr
Dim regHive
Dim hiveVar

regHive=InputBox("Enter a registry hive (lowercase ie. hkcu, hkcr, hklm, hku, hkcc","reg hive")

If (regHive = "hklm") Then
hiveVar = &H80000002
ElseIf (regHive = "hkcu") Then
hiveVar = &H80000001
ElseIf (regHive = "hkcr") Then
hiveVar = &H80000000
ElseIf (regHive = "hku") Then
hiveVar = &H80000003
ElseIf (regHive = "hkcc") Then
hiveVar = &H80000005
Else
regHive=InputBox("You did not choose a valid registry hive.  \nEnter a registry hive (lowercase ie. hkcu, hkcr, hklm, hku, hkcc","reg hive")
End If

'Set the local computer as the target
strComputer = "."
'Enumerate All subkeys in hiveVar
objRegistry.EnumKey hiveVar, "", arrSubkeys
'Set Key to find
ContractExpress = "\Software\BusinessIntegrity"
Set oReg = GetObject("winmgmts:{impersonationLevel=impersonate}!\\.\root\default:StdRegProv")
oReg.EnumKey hiveVar, "", sidList
For Each sid In sidList
' MsgBox appends sid data to sidArr variable, with a new line after each subkey
sidArr = sidArr & sid & vbCrlf
NEXT

Wscript.echo "Subkeys in registry hive: " & regHive & vbLf & sidArr
