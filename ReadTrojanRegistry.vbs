' User:		Kuteesa Kiyaga
' Date:		September 15,  2017
' Function:	VB Script to Read Trojan Registry 
' ================================================================
Option Explicit

' declare 2 variables for a registry read
dim regTrojan
dim trojanName
' used for WScript.Shell
dim objShell 

'REFERENCE	============================================
'location of the registry
' can use abbreviattions HKEY_LOCAL_MACHINE or HKLM,  HKEY_CURRENT_USER or HKCU
regTrojan = "HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Run\Trojan"
' initialize WScript.Shell
set objShell = CreateObject("WScript.Shell")

on error resume next

' WORKER ======================================
'read the registry
trojanName = objShell.RegRead(regTrojan)

If trojanName = "" then
	WScript.Echo "No Trojan found in the registry.  "
	Wscript.quit
Else
	WScript.Echo "Your registry is infected with the Trojan virus.  "
	Wscript.quit
End if
