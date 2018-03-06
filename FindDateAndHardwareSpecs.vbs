' Name: Kuteesa Kiyaga
' Date: September 27, 2017
' Function: Write a log file collecting date and hardware specs
' =====================================================================
' Write a log file
option explicit

dim objFSO
Dim objWMIService ' variable to store various Win32 processes
dim objFile ' variable to store created text files
dim objPrinter ' variable to store printer properties
dim objPhysMem ' variable to store physical memory properties
dim objOS ' variable to store operating system properties
dim objDesktop ' variable to store desktop properties
Dim objBIOS ' variable to store BIOS properties
Dim objBattery ' variable to store BIOS properties
Dim objSerial ' variable to store serial port properties
Dim objHDD ' variable to store hard drive properties

Dim strDate ' variable to store current date
Dim strNinetyDate ' variable to store 90 days before the current date
Dim strDSTStart ' variable to store the start date of Daylight Savings Time
Dim strDSTEnd ' variable to store the end date of Daylight Savings Time

' declare variable to store the sum of memory of all memory slots
Dim memCapSum
' declare variable to store the sum of all serial ports
Dim serSum
' declare variable to store the sum of fixed drives
Dim driveSum
' declare variable to store the sum of all fixed drives' capacity
Dim driveCapSum

' declare variable used to loop through for each loops
Dim objItem

' store the current date in a variable
strDate = Date
' store the date ninety days prior to the current date in a variable
strNinetyDate = DateAdd("d",-90,strDate)
' store the start date of daylight savings time in a variable
strDSTStart = #2017/03/12#
' store the end date of daylight savings time in a variable
strDSTEnd = #2017/11/05#

' append to the end of a file
const ForAppending = 8

' create variables to store the path names of the text files
dim fileName 
Dim fileNameTwo
Dim fileNameThree
Dim fileNameFour

'create file system object to allow file and folder manipulation
set objFSO = CreateObject("Scripting.FileSystemObject")

'create variables for the output text file names
fileName = ".\Kuteesa-hw1_1.txt"
fileNameTwo = ".\Kuteesa-hw1_2.txt"
fileNameThree = ".\Kuteesa-hw1_3.txt"
fileNameFour = ".\Kuteesa-hw1_4.txt"



'check to see if file exists
if Not objFSO.FileExists(fileName) Then
	'create a text file in the current directory
	set objFile = objFSO.CreateTextFile(fileName)
	' close the text file and finish editing
	objFile.Close
end if

'open text file to allow it to be edited
set objFile = objFSO.OpenTextFile(fileName,ForAppending)
'output the date to a file
objFile.WriteLine "The date is " & strDate
'output the date and time to a file
objFile.WriteLine "The date and time is " & Now
'output whether or not the current date is in daylight savings time to a file
objFile.WriteLine "It is " & _
((strDate > strDSTStart) And (strDate < strDSTEnd)) & " that date is in daylight savings time.  "
'output 90 days before the current date to a file
objFile.WriteLine "The date that is 90 days before " & Date & " is " & strNinetyDate
'output whether or not 90 days before the current date is in 
'daylight savings time to a file
objFile.WriteLine "It is " & _
((strNinetyDate > strDSTStart) And (strNinetyDate < strDSTEnd)) & " that 90 days before the current date is in daylight savings time.  "
' close the text file and finish editing
ObjFile.Close



'create a variable for the text file name
fileNameTwo = ".\Kuteesa-hw1_2.txt"

' create WMI objects for the following: WMI Service, printer, physical memory,
' operating system, desktop, BIOS, battery, serial port, and fixed drive
set objWMIService = GetObject("winmgmts:\\.\root\cimv2")
set objPrinter = objWMIService.ExecQuery("SELECT * FROM Win32_Printer")
set objPhysMem = objWMIService.ExecQuery("SELECT * FROM Win32_PhysicalMemory")
set objOS = objWMIService.ExecQuery("SELECT * FROM Win32_OperatingSystem")
set objDesktop = objWMIService.ExecQuery("SELECT * FROM Win32_Desktop")

set objBIOS = objWMIService.ExecQuery("SELECT * FROM Win32_BIOS")
set objBattery = objWMIService.ExecQuery("SELECT * FROM Win32_Battery")
set objSerial = objWMIService.ExecQuery("SELECT * FROM Win32_SerialPort")
set objHDD = objWMIService.ExecQuery("SELECT * FROM Win32_DiskDrive")

'check to see if file exists
if Not objFSO.FileExists(fileNameTwo) Then
	'create a text file in the current directory
	set objFile = objFSO.CreateTextFile(fileNameTwo)
	' close the text file and finish editing
	objFile.Close
end if

'open text file to allow it to be edited
set objFile = objFSO.OpenTextFile(fileNameTwo,ForAppending)

' loop to iterate through each printer item
For each objItem in objPrinter
' write the following to the text file: printer name, status, 
' & total # of printers
objFile.WriteLine "Printer: name = " & objItem.Name & ", printer status = " & _ 
objItem.PrinterStatus & ", total number of printers =" & objPrinter.Count
Next

' add an empty line
objFile.WriteLine

' initialize memory capacity sum variable to 0
memCapSum = 0

' loop to iterate through each physical memory item
For each objItem in objPhysMem
' append each memory slot's memory capacity to the memory capacity sum variable
memCapSum = memCapSum + objItem.Capacity
' write the following to the text file: physical memory name, capacity, 
' total # of physical memory sticks, and total # of GB found
objFile.WriteLine "Physical memory: name = " & objItem.Name & ", capacity = " _ 
& objItem.Capacity & ", total number of physical memory sticks found = " _ 
& objPhysMem.Count & ", total number of GB found = " & memCapSum
Next

' add an empty line
objFile.WriteLine

' loop to iterate through each operating system item
For each objItem in objOS
' write the following to the text file: operating system serial number & version
objFile.WriteLine "Operating system: serial number = " & objItem.SerialNumber _ 
& ", version = " & objItem.Version
Next

' add an empty line
objFile.WriteLine

' loop to iterate through each desktop object
For each objItem in objDesktop
' write the following to the text file: desktop name & whether or not
' the screensaver is active
objFile.WriteLine "Desktop: name = " & objItem.Name _ 
& ", screensaver active = " & objItem.ScreensaverActive
Next
' close the text file and finish editing
objFile.Close



'create a variable for the text file name
fileNameThree = ".\Kuteesa-hw1_3.txt"

'check to see if file exists
if Not objFSO.FileExists(fileNameThree) Then
	'create a text file in the current directory
	set objFile = objFSO.CreateTextFile(fileNameThree)
	' close the text file and finish editing
	objFile.Close
end if

'open text file to allow it to be edited
set objFile = objFSO.OpenTextFile(fileNameThree,ForAppending)

' loop to iterate through each BIOS item
For each objItem in objBIOS
' write the following to the text file: BIOS name & version
objFile.WriteLine "BIOS: name = " & objItem.Name _ 
& ", version = " & objItem.Version
Next

' add an empty line
objFile.WriteLine

' loop to iterate through each serial port item
For each objItem in objBattery
' write the following to the text file: battery name, estimated run time, 
' and estimated charge remaining
objFile.WriteLine "Battery: name = " & objItem.Name _ 
& ", estimated run time = " & objItem.EstimatedRunTime & _ 
", estimated charge remaining = " & objItem.EstimatedChargeRemaining
Next

' add an empty line
objFile.WriteLine

' initialize serial sum counter variable to 0
serSum = 0

' check to see if there are any serial ports present
if objSerial.Count = 0 Then
' print a statement stating there are no serial ports
objFile.WriteLine "There are no serial ports."
Else
' loop to iterate through each serial port item
For each objItem in objSerial
' increment the serial sum variable each time a serial port is found 
serSum = serSum + objSerial.Count
' write the following to the text file: serial port name, provider type, 
' and total number of serial ports
objFile.WriteLine "Serial port: name = " & objItem.Name _ 
& ", provider type = " & objItem.ProviderType & _ 
", total number of serial ports = " & serSum
Next
End If

' close the text file and finish editing
objFile.Close



'create a variable for the text file name
fileNameFour = ".\Kuteesa-hw1_4.txt"

'check to see if file exists
if Not objFSO.FileExists(fileNameFour) Then
	'create a text file in the current directory
	set objFile = objFSO.CreateTextFile(fileNameFour)
	' close the text file and finish editing
	objFile.Close
end if

'open text file to allow it to be edited
set objFile = objFSO.OpenTextFile(fileNameFour,ForAppending)

' initialize the drive counter variable to 0
driveSum = 0
' initialize the total drive capacity variable 0
driveCapSum = 0

' write the table's top border line
objFile.WriteLine "__________________________________________________________________________________________________________________________________________"

' loop through each hard drive object property
For each objItem in objHDD

' count the number of hard drives physically present
driveSum = driveSum + 1
' tally up the total capacity of hard drive capacity from all drives
driveCapSum = driveCapSum + objItem.Size

' output the hard drive number, name, capacity, 
' and the total drive capacity of all drives
objFile.WriteLine "| Fixed drive " & driveSum & vbTab _ 
& " | Fixed drive: name = " & objItem.Name & vbTab & " | " & vbTab & _ 
" drive capacity = " & objItem.Size & vbTab & " | " & vbTab & "total drive capacity = " & (driveCapSum/1024) & " GB" & vbTab & " | "
Next

' write the table's bottom border line
objFile.WriteLine "__________________________________________________________________________________________________________________________________________"

' close the text file and finish editing
objFile.Close
