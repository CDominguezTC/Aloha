Dim arrFiles
arrFiles=Array("2.htm", "3.htm", "4.htm", "5.htm", "6.htm", "7.htm", "8.htm", "9.htm", "10.htm", "11.htm", "12.htm", "13.htm", "14.htm", "15.htm", "16.htm", "17.htm", "18.htm", "19.htm", "20.htm", "21.htm", "22.htm", "23.htm", "24.htm", "25.htm", "26.htm", "27.htm", "28.htm", "29.htm", "30.htm", "31.htm", "32.htm", "33.htm", "34.htm", "35.htm", "36.htm", "37.htm", "38.htm", "39.htm", "40.htm", "41.htm", "42.htm", "43.htm", "44.htm", "45.htm", "46.htm", "47.htm", "48.htm", "49.htm", "50.htm", "51.htm", "52.htm", "53.htm", "54.htm", "55.htm", "56.htm", "57.htm", "58.htm", "59.htm", "60.htm", "61.htm", "62.htm", "63.htm", "64.htm", "65.htm", "66.htm", "67.htm", "68.htm", "69.htm", "70.htm", "71.htm", "72.htm", "73.htm", "74.htm", "75.htm", "76.htm", "77.htm", "78.htm", "79.htm", "80.htm", "81.htm", "82.htm", "83.htm", "84.htm", "85.htm", "86.htm", "87.htm", "88.htm", "89.htm", "90.htm", "91.htm", "92.htm", "93.htm", "94.htm", "95.htm", "96.htm", "97.htm", "98.htm", "99.htm", "100.htm", "101.htm", "102.htm", "103.htm", "104.htm", "105.htm", "106.htm", "107.htm", "108.htm", "109.htm", "110.htm", "111.htm", "112.htm", "113.htm", "114.htm", "115.htm", "116.htm", "117.htm", "118.htm", "119.htm", "120.htm")

Sub NextPage(nIndex)

    Dim str
    Dim n, i, lb, ub

    str = self.Location.href
    n = InStrRev(str, "/")
    str = Mid(str, n + 1, Len(str) - n)

	n = Len(str)
    Do While InStrRev(str, "#", n) > 0
        n = n - 1
    Loop
    str = Left(str, n)
    
    n = InStrRev(str, "#")
    If n > 0 Then
        str = Left(str, n - 1)
    End If

    lb = LBound(arrFiles)
    ub = UBound(arrFiles)
    
    For i = lb To ub
        If str = arrFiles(i) Then
            i = i + nIndex
            Exit For
        End If
    Next

    If i >= lb And i <= ub Then
        self.Location.href = arrFiles(i)
    End If

End Sub 
