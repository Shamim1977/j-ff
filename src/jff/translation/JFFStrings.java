package jff.translation;

public interface JFFStrings {

	public String lang();
	
	//frame names
	public String appFrameName();
	public String tutorialFrameName();
	public String creditsDialogName();
	
	//multiplegroptasks name
	public String allProcesses();

	
	//title of main items of the apps
	public String openedFiles();
	public String processes();
	public String options();

	
	//action titles
	public String addFiles();
	public String addTask();
	public String deleteAllTasks();
	public String deleteFiles();
	public String deleteGroupTask();
	public String invertSelection();
	public String selectAll();
	public String selectNone();
	public String hideOptions();
	public String showOptions();
	public String hideFiles();
	public String showFiles();
	public String pauseTasks();
	public String quit();
	public String showCredits();
	public String showTutorial();
	public String startTasks();
	public String playFile();

	
	//action descriptions
	public String addFilesDescription();
	public String addTaskDescription();
	public String deleteAllTasksDescription();
	public String deleteFilesDescription();
	public String deleteGroupTaskDescription();
	public String invertSelectionDescription();
	public String selectAllDescription();
	public String selectNoneDescription();
	public String hideOptionsDescription();
	public String showOptionsDescription();
	public String hideFilesDescription();
	public String showFilesDescription();
	public String pauseTasksDescription();
	public String quitDescription();
	public String showCreditsDescription();
	public String showTutorialDescription(); 
	public String startTasksDescription();
	public String playFileDescription();
	
	
	//menu voices
	public String fileMenu();
	public String selectMenu();
	public String optionMenu(); 
	public String taskMenu();
	public String infoMenu();

	
	//table headers
	public String tableSel();
	public String tableName();
	public String tableLength();
	public String tableFormat();
	public String tablePAR();
	public String tableDAR();

	
	//treetable headers
	public String treeTableProcesses();
	public String treeTableFiles();
	public String treeTableProgress();
	public String treeTableInfo();

	
	//options tags
	public String optionsDestinationFolder();
	public String optionsChangeDestinationFolder();
	public String optionsOutputFormat();
	public String optionsOutputFormatTag();
	public String optionsHardware();
	public String optionsThreadsTag();
	public String optionsConvertOptions();
	public String optionsTwoPassesTag();
	public String optionsPadsTag();
	public String optionsSmallTag();
	public String optionsDebugTag();


	//strings in the treetable
	public String executing();
	public String inPause();
	public String ok();
	
	//credits
	public String credits();
	

}
