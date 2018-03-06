' User:		Kuteesa Kiyaga
' Date:		September 25,  2017
' Function:	Use FSO to create a folder and read files in a folder

' header	============================================
Option Explicit
Dim objFSO		'File System Object
Dim objFSOText		' FSO Text
Dim objFolder		' FSO Folder
Dim objFile		' FSO File
Dim strDirectory	' string variable for Folder
Dim strFile		' string variable for FILE
Dim colFiles ' ' gets all the files within the folder in colFiles
Dim files ' string variable to represent each directory file
Dim fileStrArr
fileStrArr = Array("\file1.txt", "\file2.txt","\file3.vbs", "\file4.bat", "\file5.doc", "\file6.ppt")

Dim myArrayList
Set myArrayList = CreateObject( "System.Collections.ArrayList" )

strDirectory = "C:\INFO3099"
strFile = "\file1"

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

Dim x
.   For x = 0 To 5
	' if the file doesn't exist on your drive, then create the file and close the file
' whenever you open a file or create a file, you need to close the file
if(Not objFSO.FileExists(strDirectory & fileStrArr(x))) Then
	set objFile = objFSO.CreateTextFile(strDirectory & fileStrArr(x))
	objFile.Close
End if
' open the file for appending, use WriteLine to write to the file
' Now gives the current date and time
set objFile = objFSO.OpenTextFile(strDirectory & fileStrArr(x),FORAPPENDING)
objFile.WriteLine "this is " & strDirectory & fileStrArr(x) & ".  " & Now
set objFolder = objFSO.GetFolder(strDirectory)
' gets all the files within the folder in colFiles
set colFiles = objFolder.Files
dim sum
Dim file
' loop to query all files in the folder
If x = 5 Then
for each file in colFiles
	' use a message box to display information about the file
	objFile.WriteLine file.name & " Size: " & file.size
	sum = sum + file.size
	
	' change all files except *.txt files to readonly
	if(NOT(file.type = "Text Document")) Then
		file.Attributes = (file.Attributes Xor 1)
		myArrayList.Add(file.name)
	end if
	
	Wscript.echo file.name & " | " & file.type
next
objFile.WriteLine "Total Size:" & sum
objFile.Close
End if
Next

Wscript.echo
Wscript.echo "Total files: " & x
Wscript.echo
Wscript.echo "Files with attributes that have been changed: " & myArrayList.Count
Wscript.echo
Dim y
For y = 0 To myArrayList.Count - 1
Wscript.echo myArrayList(y)
Next

Wscript.echo
Wscript.echo sum & " KB"
Wscript.quit