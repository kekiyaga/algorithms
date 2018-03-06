' User:		Kuteesa Kiyaga
' Date:		September 25,  2017
' Function:	Use FSO to create a folder and read files in a folder

' header	============================================
Option Explicit
Dim objFSO		'File System Object
Dim objFSOText		' FSO Text
Dim objFolder		' FSO Folder
Dim objFile		' FSO File
Dim getObjFile ' FSO file to get the INFO3099.txt file
Dim strDirectory	' string variable for Folder
Dim colFiles ' ' gets all the files within the folder in colFiles
Dim files ' string variable to represent each directory file
Dim fileStr


fileStr = "\INFO3099.txt"

Dim myArrayList
Set myArrayList = CreateObject( "System.Collections.ArrayList" )

strDirectory = "C:\INFO3099"

' Create the File System Object
Set objFSO = CreateObject("Scripting.FileSystemObject")

If objFso.FolderExists(strDirectory) = false Then
' Create the Folder specified by strDirectory on line 10
Set objFolder = objFSO.CreateFolder(strDirectory)
End If
'	The heart of the create file script
'	Creates the file using the value of strFile 
' 	-----------------------------------------------

' always open a file for reading, writing or appending
const FORAPPENDING = 8

	' if the file doesn't exist on your drive, then create the file and close the file
' whenever you open a file or create a file, you need to close the file
if(Not objFSO.FileExists(strDirectory & fileStr)) Then
	set objFile = objFSO.CreateTextFile(strDirectory & fileStr)
	objFile.Close
End if

Set getObjFile = objFSO.GetFile("C:\INFO3099\INFO3099.txt")

If (getObjFile.Attributes AND 1) = 1 Then
	Wscript.Echo "The attributes of C:\INFO3099\INFO3099.txt are: read-only, not hidden.  "
ElseIf (getObjFile.Attributes AND 1) = 2 Then
	Wscript.Echo "The attributes of C:\INFO3099\INFO3099.txt are: not read-only, hidden.  "
ElseIf (getObjFile.Attributes AND 1) = 3 Then
	Wscript.Echo "The attributes of C:\INFO3099\INFO3099.txt are: read-only, hidden.  "
Else
	Wscript.Echo "The attributes of C:\INFO3099\INFO3099.txt are: not read-only, not hidden.  "
End If
