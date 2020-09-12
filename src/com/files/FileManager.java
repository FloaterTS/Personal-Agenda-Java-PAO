package com.files;

import java.io.IOException;

public class FileManager
{
    private static FileManager file_manager = null;

    private final FileTaskMgr taskMgr;
    private final FileMeetMgr meetMgr;
    private final FileAlarmMgr alarmMgr;

    private FileManager()
    {
        taskMgr = FileTaskMgr.getTask_file_mgr();
        meetMgr = FileMeetMgr.getMeet_file_mgr();
        alarmMgr = FileAlarmMgr.getAlarm_file_mgr();
    }

    public static FileManager getFileManager()
    {
        if (file_manager == null)
            file_manager = new FileManager();
        return file_manager;
    }

    public void saveAgenda()
    {
        taskMgr.saveTasks();
        meetMgr.saveMeetings();
        alarmMgr.saveAlarms();
    }

    public void loadAgenda()
    {
        taskMgr.loadTasks();
        meetMgr.loadMeetings();
        alarmMgr.loadAlarms();
    }

}
