While 1
Select
    Case WinActive( "Open" )
       Send($CmdLine[1])
		Send("{ENTER}")
        ExitLoop ;Stop checking for an active window
    Case WinActive( "File Upload" )
        Send($CmdLine[1])
		Send("{ENTER}")
        ExitLoop ;Stop checking for an active window
EndSelect
Sleep(200) ; So that the CPU doesn't get hogged
WEnd
	