package jff.translation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import jff.utility.JFFParser;
import jff.utility.JFFParserImpl;
import jff.utility.JFFPath;

/**
 * An implementation of {@link JFFStrings}.<br />
 * When created, an instance of JFFStringsImpl will check in the INITFILE the language specified,
 * then it will search in the LANGFOLDER a file with this name (with .txt as extension) and load the language written in it, using a {@link JFFParser} 
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
public class JFFStringsImpl implements JFFStrings {

	/**
	 * The default language name
	 */
	private String Lang="english";
	
	private JFFParser P;
	
	private String AllProcesses;
	private String OpenedFiles;
	private String Processes;
	private String Options;
	private String AddFiles;
	private String AddFilesDescr;
	private String AddTask;
	private String DeleteAllTasks;
	private String DeleteFiles;
	private String DeleteGroupTask;
	private String HideOptions;
	private String ShowOptions;
	private String PauseTasks;
	private String Quit;
	private String ShowCredits;
	private String ShowTutorial;
	private String StartTasks;
	private String PlayFile;
	private String AddTaskDescr;
	private String DeleteAllTasksDescr;
	private String DeleteFilesDescr;
	private String DeleteGroupTaskDescr;
	private String HideOptionsDescr;
	private String ShowOptionsDescr;
	private String PauseTasksDescr;
	private String QuitDescr;
	private String ShowCreditsDescr;
	private String ShowTutorialDescr;
	private String StartTasksDescr;
	private String PlayFileDescr;
	private String FileMenu;
	private String OptionMenu;
	private String TaskMenu;
	private String InfoMenu;
	private String TableSel;
	private String TableName;
	private String TableLength;
	private String TableFormat;
	private String TablePAR;
	private String TableDAR;
	private String TTableProcesses;
	private String TTableFiles;
	private String TTableProgress;
	private String TTableInfo;
	private String OptionsDestFolder;
	private String OptionsChangeDestFolder;
	private String OptionsOutputFormat;
	private String OptionsOutputFormatTag;
	private String OptionsHardware;
	private String OptionsThreadsTag;
	private String OptionsConvertOptions;
	private String OptionsTwoPassesTag;
	private String OptionsPadsTag;
	private String OptionsSmallTag;
	private String OptionsDebugTag;
	private String Executing;
	private String InPause;
	private String OK;
	private String HideFiles;
	private String ShowFiles;
	private String HideFilesDescr;
	private String ShowFilesDescr;
	private String InvertSelection;
	private String SelectAll;
	private String SelectNone;
	private String SelectMenu;
	private String InvertSelectionDescr;
	private String SelectAllDescr;
	private String SelectNoneDescr;
	private String AppFrameName;
	private String TutorialFrameName;
	private String CreditsDialogName;
	private String Credits;
	private String DuplicateAppError; 
	
	/**
	 * It will load the default language, "english" defined in {@link #Lang}
	 * 
	 */
	public JFFStringsImpl(){
		parseLanguage(new File(JFFPath.LANGFOLDER+File.separator+Lang+".txt"));
	}
		
	/**
	 * 
	 * It will load the language with the name defined in the INITFILE in the line <br />
	 * language: name<br />
	 * from the file LANGFOLDER/name.txt
	 * 
	 * @param b the BufferedReader associated with the init file
	 */
	public JFFStringsImpl(BufferedReader b){
		
		JFFParser p;
		
		boolean findLanguage=false;
		
		do{
		
			try {
			 p=new JFFParserImpl(b.readLine());
			} catch (IOException e) {
			
				p=new JFFParserImpl(null);
				e.printStackTrace();
			}
		
		if (p.find("language")){
			
			String tempLang=p.getString();
			File f=new File("lang"+File.separator+tempLang+".txt");
			
			if (f.isFile()) {
				
				parseLanguage(f);
				Lang=tempLang;
				findLanguage=true;
	
			}
		}
		
		} while (!p.isEmpty());
		
		if (!findLanguage)
			parseLanguage(new File("lang"+File.separator+Lang+".txt"));
		
	}



	private void parseLanguage(File f) {
		
		FileReader fstream;
		BufferedReader b=null;
		try {
			
			fstream = new FileReader(f.getAbsolutePath());
			b=new BufferedReader(fstream);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		do{
			
			try {
				P=new JFFParserImpl(b.readLine());
			} catch (IOException e) {
			
				P=new JFFParserImpl(null);
				e.printStackTrace();
			}
		
			if (P.find("appframename"))
				AppFrameName=P.getString();
			
			else if (P.find("tutorialframename"))
				TutorialFrameName=P.getString();
			
			else if (P.find("creditsdialogname"))
				CreditsDialogName=P.getString();
			
			else if (P.find("allprocesses"))
				AllProcesses=P.getString();
			
			else if (P.find("openedfiles"))
				OpenedFiles=P.getString();
				
			else if (P.find("processes"))
				Processes=P.getString();
				
			else if (P.find("options"))
				Options=P.getString();
			
			else if (P.find("addfiles"))
				AddFiles=P.getString();
			
			else if (P.find("addfilesdescr"))
				AddFilesDescr=P.getString();
			
			else if (P.find("addtask"))
				AddTask=P.getString();
			
			else if (P.find("deletealltasks"))
				DeleteAllTasks=P.getString();
			
			else if (P.find("deletefiles"))
				DeleteFiles=P.getString();
			
			else if (P.find("deletegrouptask"))
				DeleteGroupTask=P.getString();
			
			else if (P.find("invertselection"))
				InvertSelection=P.getString();
			
			else if (P.find("selectall"))
				SelectAll=P.getString();
			
			else if (P.find("selectnone"))
				SelectNone=P.getString();
			
			else if (P.find("hideoptions"))
				HideOptions=P.getString();
			
			else if (P.find("showoptions"))
				ShowOptions=P.getString();
					
			else if (P.find("hidefiles"))
				HideFiles=P.getString();
			
			else if (P.find("showfiles"))
				ShowFiles=P.getString();
			
			else if (P.find("pausetasks"))
				PauseTasks=P.getString();
			
			else if (P.find("quit"))
				Quit=P.getString();
			
			else if (P.find("showcredits"))
				ShowCredits=P.getString();
			
			else if (P.find("showtutorial"))
				ShowTutorial=P.getString();
			
			else if (P.find("starttasks"))
				StartTasks=P.getString();
			
			else if (P.find("playfile"))
				PlayFile=P.getString();

			else if (P.find("addfilesdescr"))
				AddFilesDescr=P.getString();
			
			else if (P.find("addtaskdescr"))
				AddTaskDescr=P.getString();
			
			else if (P.find("deletealltasksdescr"))
				DeleteAllTasksDescr=P.getString();
			
			else if (P.find("deletefilesdescr"))
				DeleteFilesDescr=P.getString();
			
			else if (P.find("deletegrouptaskdescr"))
				DeleteGroupTaskDescr=P.getString();
													
			else if (P.find("invertselectiondescr"))
				InvertSelectionDescr=P.getString();
			
			else if (P.find("selectalldescr"))
				SelectAllDescr=P.getString();
			
			else if (P.find("selectnonedescr"))
				SelectNoneDescr=P.getString();
			
			else if (P.find("hideoptionsdescr"))
				HideOptionsDescr=P.getString();
												
			else if (P.find("showoptionsdescr"))
				ShowOptionsDescr=P.getString();
			
			else if (P.find("hidefilesdescr"))
				HideFilesDescr=P.getString();
			
			else if (P.find("showfilesdescr"))
				ShowFilesDescr=P.getString();
			
			else if (P.find("pausetasksdescr"))
				PauseTasksDescr=P.getString();
			
			else if (P.find("quitdescr"))
				QuitDescr=P.getString();
			
			else if (P.find("showcreditsdescr"))
				ShowCreditsDescr=P.getString();
			
			else if (P.find("showtutorialdescr"))
				ShowTutorialDescr=P.getString();
			
			else if (P.find("starttasksdescr"))
				StartTasksDescr=P.getString();
			
			else if (P.find("playfiledescr"))
				PlayFileDescr=P.getString();

			else if (P.find("filemenu"))
				FileMenu=P.getString();
			
			else if (P.find("selectmenu"))
				SelectMenu=P.getString();
			
			else if (P.find("optionmenu"))
				OptionMenu=P.getString();
			
			else if (P.find("taskmenu"))
				TaskMenu=P.getString();
			
			else if (P.find("infomenu"))
				InfoMenu=P.getString();

			else if (P.find("tsel"))
				TableSel=P.getString();
			
			else if (P.find("tname"))
				TableName=P.getString();
			
			else if (P.find("tlenght"))
				TableLength=P.getString();
			
			else if (P.find("tformat"))
				TableFormat=P.getString();
			
			else if (P.find("tpar"))
				TablePAR=P.getString();
			
			else if (P.find("tdar"))
				TableDAR=P.getString();
			
			else if (P.find("ttprocesses"))
				TTableProcesses=P.getString();
			
			else if (P.find("ttfiles"))
				TTableFiles=P.getString();
			
			else if (P.find("ttprogress"))
				TTableProgress=P.getString();
			
			else if (P.find("ttinfo"))
				TTableInfo=P.getString();
			
			else if (P.find("odestfolder"))
				OptionsDestFolder=P.getString();
			
			else if (P.find("ochangedestfolder"))
				OptionsChangeDestFolder=P.getString();
			
			else if (P.find("ooutputformat"))
				OptionsOutputFormat=P.getString();
			
			else if (P.find("ooutputformattag"))
				OptionsOutputFormatTag=P.getString();
			
			else if (P.find("ohardware"))
				OptionsHardware=P.getString();
			
			else if (P.find("othreadstag"))
				OptionsThreadsTag=P.getString();
			
			else if (P.find("oconvertoptions"))
				OptionsConvertOptions=P.getString();
			
			else if (P.find("otwopasses"))
				OptionsTwoPassesTag=P.getString();
			
			else if (P.find("opads"))
				OptionsPadsTag=P.getString();
			
			else if (P.find("osmall"))
				OptionsSmallTag=P.getString();
			
			else if (P.find("odebug"))
				OptionsDebugTag=P.getString();
			
			else if (P.find("executing"))
				Executing=P.getString();
			
			else if (P.find("inpause"))
				InPause=P.getString();
			
			else if (P.find("ok"))
				OK=P.getString();
			
			else if (P.find("credits"))
				Credits=P.getStringWithSeparators();
			
			else if (P.find("duplicateapperror"))
				DuplicateAppError=P.getString();



		
		} while (!P.isEmpty());
		
	
	}

	@Override
	public String allProcesses() {
		
		return AllProcesses;
	}

	@Override
	public String openedFiles() {
		
		return OpenedFiles;
	}

	@Override
	public String processes() {
		
		return Processes;
	}

	@Override
	public String options() {
		
		return Options;
	}

	@Override
	public String addFiles() {
		
		return AddFiles;
	}

	@Override
	public String addFilesDescription() {
		
		return AddFilesDescr;
	}

	@Override
	public String addTask() {
		
		return AddTask;
	}

	@Override
	public String deleteAllTasks() {
		
		return DeleteAllTasks;
	}

	@Override
	public String deleteFiles() {
		
		return DeleteFiles;
	}

	@Override
	public String deleteGroupTask() {
		
		return DeleteGroupTask;
	}

	@Override
	public String hideOptions() {
		
		return HideOptions;
	}

	@Override
	public String showOptions() {
		
		return ShowOptions;
	}

	@Override
	public String pauseTasks() {
		
		return PauseTasks;
	}

	@Override
	public String quit() {
		
		return Quit;
	}

	@Override
	public String showCredits() {
		
		return ShowCredits;
	}

	@Override
	public String showTutorial() {
		
		return ShowTutorial;
	}

	@Override
	public String startTasks() {
		
		return StartTasks;
	}

	@Override
	public String playFile() {
		
		return PlayFile;
	}

	@Override
	public String addTaskDescription() {
		
		return AddTaskDescr;
	}

	@Override
	public String deleteAllTasksDescription() {
		
		return DeleteAllTasksDescr;
	}

	@Override
	public String deleteFilesDescription() {
		
		return DeleteFilesDescr;
	}

	@Override
	public String deleteGroupTaskDescription() {
		
		return DeleteGroupTaskDescr;
	}

	@Override
	public String hideOptionsDescription() {
		
		return HideOptionsDescr;
	}

	@Override
	public String showOptionsDescription() {
		
		return ShowOptionsDescr;
	}

	@Override
	public String pauseTasksDescription() {
		
		return PauseTasksDescr;
	}

	@Override
	public String quitDescription() {
		
		return QuitDescr;
	}

	@Override
	public String showCreditsDescription() {
		
		return ShowCreditsDescr;
	}

	@Override
	public String showTutorialDescription() {
		
		return ShowTutorialDescr;
	}

	@Override
	public String startTasksDescription() {
		
		return StartTasksDescr;
	}

	@Override
	public String playFileDescription() {
		
		return PlayFileDescr;
	}

	@Override
	public String fileMenu() {
		
		return FileMenu;
	}

	@Override
	public String optionMenu() {
		
		return OptionMenu;
	}

	@Override
	public String taskMenu() {
		
		return TaskMenu;
	}

	@Override
	public String infoMenu() {
		
		return InfoMenu;
	}

	@Override
	public String tableSel() {
		
		return TableSel;
	}

	@Override
	public String tableName() {
		
		return TableName;
	}

	@Override
	public String tableLength() {
		
		return TableLength;
	}

	@Override
	public String tableFormat() {
		
		return TableFormat;
	}

	@Override
	public String tablePAR() {
		
		return TablePAR;
	}

	@Override
	public String tableDAR() {
		
		return TableDAR;
	}

	@Override
	public String treeTableProcesses() {
		
		return TTableProcesses;
	}

	@Override
	public String treeTableFiles() {
		
		return TTableFiles;
	}

	@Override
	public String treeTableProgress() {
		
		return TTableProgress;
	}

	@Override
	public String treeTableInfo() {
		
		return TTableInfo;
	}

	@Override
	public String optionsDestinationFolder() {
		
		return OptionsDestFolder;
	}

	@Override
	public String optionsChangeDestinationFolder() {
		
		return OptionsChangeDestFolder;
	}

	@Override
	public String optionsOutputFormat() {
			
		return OptionsOutputFormat;
	}

	@Override
	public String optionsOutputFormatTag() {
		
		return OptionsOutputFormatTag;
	}

	@Override
	public String optionsHardware() {
		
		return OptionsHardware;
	}

	@Override
	public String optionsThreadsTag() {
		
		return OptionsThreadsTag;
	}

	@Override
	public String optionsConvertOptions() {
		
		return OptionsConvertOptions;
	}

	@Override
	public String optionsTwoPassesTag() {
		
		return OptionsTwoPassesTag;
	}

	@Override
	public String optionsPadsTag() {
		
		return OptionsPadsTag;
	}

	@Override
	public String optionsSmallTag() {
		
		return OptionsSmallTag;
	}

	@Override
	public String optionsDebugTag() {
		
		return OptionsDebugTag;
	}

	@Override
	public String executing() {
		
		return Executing;
	}

	@Override
	public String inPause() {
		
		return InPause;
	}

	@Override
	public String ok() {
		
		return OK;
	}

	@Override
	public String hideFiles() {
		
		return HideFiles;
	}

	@Override
	public String showFiles() {
		
		return ShowFiles;
	}

	@Override
	public String hideFilesDescription() {
		
		return HideFilesDescr;
	}

	@Override
	public String showFilesDescription() {
		
		return ShowFilesDescr;
	}

	@Override
	public String invertSelection() {
		
		return InvertSelection;
	}

	@Override
	public String selectAll() {
		
		return SelectAll;
	}

	@Override
	public String selectNone() {
		
		return SelectNone;
	}

	@Override
	public String selectMenu() {
		
		return SelectMenu;
	}

	@Override
	public String invertSelectionDescription() {
		
		return InvertSelectionDescr;
	}

	@Override
	public String selectAllDescription() {
		
		return SelectAllDescr;
	}

	@Override
	public String selectNoneDescription() {
		
		return SelectNoneDescr;
	}

	@Override
	public String lang() {
		
		return Lang;
	}

	@Override
	public String appFrameName() {
		
		return AppFrameName;
	}

	@Override
	public String tutorialFrameName() {
		
		return TutorialFrameName;
	}

	@Override
	public String creditsDialogName() {
		
		return CreditsDialogName;
	}

	@Override
	public String credits() {
		
		return Credits;
	}

	@Override
	public String duplicateAppError() {
		
		return DuplicateAppError;
	}
	
	
}
