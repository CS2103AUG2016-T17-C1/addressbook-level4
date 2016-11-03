# User Guide

* [Quick Start](#quick-start)
* [Features](#features)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   > Having any Java 8 version is not enough. <br>
   This app will not work with earlier versions of Java 8.

1. Download the latest `neverforgetagain.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for your to-do list.
3. Double-click the file to start the app. The GUI should appear in a few seconds.
   <img src="images/newUI2.png" width="728">



## Features

> **Command Format**
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * The order of parameters is fixed.



> <img src="images/newUIadd2.png" width="728">

4. Type an action in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> or clicking on the help button will open the help window.
5. Some example commands you can try:
   * **`list`** : lists all tasks
   * **`add`**` Buy a cup of coffee` : Add a task to buy a cup of coffee without setting any deadlines for it to the to-do list.

   * **`add`**` Grocery Shopping d/01012016 e/1800 i/* t/NTUC` :
     adds a task `Grocery Shopping` with specified time, importance and tag to Never Forget.

   * **`delete`**` 3` : deletes the 3rd task shown in the current list
   * **`exit`** : exits the app

6. Refer to the [Features](#features) section below for details of each command.<br>


#### Viewing help : `help`
View help for commands, including hotkeys.<br>
Format: `help`


#### Finding all tasks containing any keyword in their name: `find`
Finds tasks whose names contain any of the given keywords.<br>
Format: `find KEYWORD [MORE_KEYWORDS]`

> * The search is not case sensitive. e.g `buy` will match `Buy`
> * The order of the keywords does not matter. e.g. `Buy Bread` will match `Bread Buy`
> * Only the name is searched.
> * Non full words will still be matched e.g. `brea` WILL match `bread`
> * tasks matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `buy` will match `buy bread`


Examples:
* `find meeting`<br>
  Returns `meetings` and `Meetings` but not `meet`
* `find coffee complete homework`<br>
  Returns Any tasks containing names `coffee`, `complete`, or `homework`


#### Adding a task: `add`
Adds a task to Never Forget<br>
Format: `add TASK_NAME d/SCHEDULED_DATE e/SCHEDULED_END_TIME i/IMPORTANCE [t/TAG]...`

> Tasks can have any number of tags (including 0)

Examples:
* `add Grocery Shopping d/01012016 e/1800 i/*`
* `add Dinner with JC Friends d/07102016 e/1930 i/** t/Budget t/Meal t/Buddies`


#### Editing a task: `edit`
Edits a task currently held in Never Forget<br>
Format: `edit INDEX d/SCHEDULED_DATE e/SCHEDULED_END_TIME i/IMPORTANCE [t/TAG]...`

> Tasks can have any number of tags (including 0)

Examples:
* `edit 2 d/01012016 e/1700 i/***`
* `edit 3 d/07102016 e/2030 i/* t/Budget t/Meal t/Friends`



#### Deleting a task : `delete`
Deletes the specified task from the to-do list. Can be reversed with `undo` command.<br>
Format: `delete INDEX`

> Deletes the task at the specified `INDEX`.
  The index refers to the index number shown in the most recent listing.<br>
  The index **must be a positive integer** 1, 2, 3, ...


#### Listing all of today's tasks : `list`
Shows a list of all tasks today in Never Forget.<br>
Format: `list`


#### Selecting tasks : `select`
Examples:
* `list`<br>
  `select 2`<br>
  Selects the 2nd task in the to-do list.
* `find Buy bread` <br>
  `select 1`<br>
  Selects the 1st task in the results of the `find` command.


#### Clearing all entries : `clear`
Clears all entries from the to-do list. The user is asked to confirm before proceeding.<br>
Format: `clear`



#### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`



#### Marking an entry : `mark`
Marks an entry as completed. <br>
Format: `mark INDEX`



#### Undo : `undo`
Undo previous command. <br>
The Undo command can be executed as many times as needed to the point of application launch.
Format: `undo`


#### Redo : `redo`
Reverse undo command. <br>
The Redo command can only be executed immediately after one or more Undo commands.
If any command other than Undo makes changes to the task list, the Redo command is no longer available until Undo is executed again.
Format: `redo`


#### Change tasks storage directory : `cd`
Changes the storage location of the Task list for Never Forget <br>
> *Users are given an option whether they would like to import their tasks to the new storage location. <br><br>
> *If they would not like to do so, the task manager will start from an empty task list in the new storage location.<br><br>
> *The default location is at the 'data/' folder of where the Never Forget.jar is located.<br>

Note: Storage location has to be declared relative to the position of Never Forget.jar. If users would like to navigate to a higher directory,
	  they can use '../'<br>
Note: This action will cause Never Forget to terminate. Users will have to re-launch their application again.<br>
Format: `cd {STORAGE LOCATION}/`<br>
		 `cd data/`


#### Saving the data
Never Forget data are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.


#### Sorting the displayed task list : `select any one of: Home(which displays everything), Daily, Weekly or Monthly`
Displays the task listing for that desired time frame.<br>


> Displays the task listing for that desired time frame.
  Tasks with no deadlines are only displayed in the Home listing where all tasks are displayed.<br>
  User must select any one of the tabs, Home, Daily, Weekly or Monthly.




## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A1**: Install the app in the other computer and overwrite the empty data file it creates with
       the file that contains the data of your previous to-do list folder.<br><br>
**A2**: An alternate way would be to use the inbuilt storage transfer command `cd` and File Hosting services such as dropbox.<br>
		Using the `cd` command, change the working directory of Never Forget & also select the option to transfer the tasks to a folder on dropbox.<br>
		From the other computer,use `cd` to change **_only_** the working directory of Never Forget to the same folder on dropbox.<br>
		Lastly, if needed, change the working directory of Never Forget & also select the option to transfer the tasks to a _**local**_ folder.

## Command Summary


Command | Format
-------- | :--------
Add | `add TASK d/DDMMYYYY t/HHMM i/*[*]... [t/TAG]...`
Change Directory | `cd {STORAGE LOCATION}/`
Clear | `clear`
Delete | `delete INDEX`
Edit | `edit TASK d/DDMMYYYY t/HHMM i/*[*]... [t/TAG]...`
Find | `find KEYWORD [MORE_KEYWORDS]`
Help | `help`
List | `list`
Mark | `mark INDEX`
Select | `select INDEX`
Redo | `redo`
Undo | `undo`
Exit | `exit`
