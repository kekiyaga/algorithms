On Error Resume Next

Dim strComputer
Dim sum

strComputer = "."
Set objWMIService = GetObject("winmgmts:\\" & strComputer & "\root\cimv2")
Set colItems = objWMIService.ExecQuery("Select * from Win32_Process",,48)

sum = 0

For Each objItem in colItems
	
	
	If objItem.Name = "iexplore.exe" Then
	sum = sum + 1
	End If
Next

Wscript.echo "You have " & sum & " Internet Explorer processes running"
