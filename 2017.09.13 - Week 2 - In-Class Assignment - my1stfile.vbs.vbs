'Kuteesa Kiyaga
'2017-09-13
'script generates random numbers

intHighNumber = 100
intLowNumber = 1

For i = 1 to 5
    Randomize
    intNumber = Int((intHighNumber - intLowNumber + 1) * Rnd + intLowNumber)
    Wscript.Echo intNumber
Next