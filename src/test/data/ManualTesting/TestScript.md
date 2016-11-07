# Manual Testng
###### Loading of Manual Testing
``` 
	1. Since our program only reads xml files with the task name "taskmanger.xml", the SampleData.xml file has to be manually renamed to taskmanager.xml
	2. After renaming SampleData.xml to taskmanager.Xml, enter "cd src/test/data/ManualTesting/" in the command box. Now the to-do list has to restart.
	3. Re launch the to-do list and all the sample data should be loaded onto the task manager.
	4. Enter desired actions.

```
## Features

> **Command Format**
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * The order of parameters is fixed.


#### Viewing help : `help`
View help page for commands, including hotkeys. Or check how to use a specific command by typing the particular command word after 'help'.<br>

Examples of available [command word] for help: 
Key in `help` (Help Aler dialog opens, press enter to dismiss)
Key in `help add` and the below message will appear  
>add: Adds a task to the task manager. Parameters: TASK_NAME [sd/START_DATE] [st/START_TIME] [d/END_DATE] [e/END_TIME] [*] [t/TAG]...
>Example: add Buy milk d/15102016 e/1500 *
>Hotkey: a 
<br><br>

*
Key in `help mark` and the below message will appear.<br>
>mark: Marks the selected task as completed and display the task in the 'completed task panel'.
>Parameters: INDEX (must be a positive integer)
>Example: mark 1
>Hotkey: m


#### Finding all tasks containing any keyword in their name: `find`
Finds tasks whose names contain any of the given keywords.<br>
Key in: `find CS2103`<br>
Key in `find 2013` or
Key in `f 2013` and 
>"6 tasks listed" 
should appear


#### Adding a task: `add`
Adds a task to Never Forget<br>
Format: `add TASK_NAME d/SCHEDULED_DATE e/SCHEDULED_END_TIME i/IMPORTANCE [t/TAG]...`<br>
Shorthand: `a` 

Key in `add Grocery Shopping d/01012016 e/1800 i/*` <br>
Key in `a Dinner with JC Friends d/07102016 e/1930 i/** t/Budget`


#### Baring a task: `bare`
Bares an existing task of its date(s) and time(s)<br>
Format: `bare INDEX`<br>
Shorthand: `b` 
Tasks retain all the other details<br>

Key in: `bare 3` and all the task attributes will disappear, such as importance.



#### Editing a task: `edit`
Edits a task currently held in Never Forget<br>
Format: `edit INDEX d/SCHEDULED_DATE e/SCHEDULED_END_TIME i/IMPORTANCE [t/TAG]...`<br>
Shorthand: `e` 

> Tasks can have any number of tags (including 0).<br>
> Edit will remove an already existing tag if it specified again:`edit INDEX t/EXISTING_TAG` <br>
> All tags of a specific entry can be cleared with the command format: `edit INDEX t/NONE`


Key in : `edit 2 d/01112016 e/1700 i/***`<br>
KEY in `e 3 d/07122016 e/2030 i/* t/Budget t/Meal t/Friends`



#### Deleting a task : `delete`
Deletes the specified task from the to-do list. Can be reversed with `undo` command.<br>
Format: `delete INDEX`<br>
Shorthand: `d` 

> Deletes the task at the specified `INDEX`.
  The index refers to the index number shown in the most recent listing.<br>
  The index **must be a positive integer** 1, 2, 3, ...


#### Listing all of today's tasks : `list`
Shows a list of all tasks today in Never Forget.<br>
Key in: `list`<br>
All the tasks in the todo list should appear.
Shorthand: `l` 



#### Selecting tasks : `select`
Selects the specified task (by index) in the to-do list.
Format: `select INDEX`<br>
Shorthand: `s` <br>

Key in 
* `list`<br>
  `select 2`<br>
  Selects the 2nd task in the to-do list.
* `find 2103` <br>
  `select 2`<br>
  Selects the 2nd task in the results of the `find` command, which should be 'do up 2103 doc for collation'<br>
  

#### Marking an entry : `mark`
Marks an entry as completed. <br>
Format: `mark INDEX`<br>
Shorthand: `m` 
Key in: `mark 1` <br>
The first task should now be in the Right list of marked tasks


#### Undo : `undo`
Undo previous command. <br>
The Undo command can be executed as many times as needed to the point of application launch.
Key in: `undo`<br>
Shorthand: `u` 
The marked action done previously should be undone.




#### Redo : `redo`
Reverse undo command. <br>
The Redo command can only be executed immediately after one or more Undo commands.
If any command other than Undo makes changes to the task list, the Redo command is no longer available until Undo is executed again.
Format: `redo`<br>
Shorthand: `r` 
The marked action that was undone should be done again. <br>

#### Multiple Undos and Redos : `undo 2 redo2`
Key in `add sample task`<br>
Key in `undo 2` and both the add sample task and the marked action should be undone. <br>
Key in `redo 2` and both the add sample task and the marked action should be redone. <br>



#### Clearing all entries : `clear`
Clears all entries from the to-do list. The user is asked to confirm before proceeding.<br>
Key in: `clear` <br>
No more tasks should appear<br>
Key in: `undo` to retrieve the tasks again.




#### Change tasks storage directory : `cd`
Changes the storage location of the Task list for Never Forget <br>
> *Users are given an option whether they would like to import their tasks to the new storage location. <br><br>
> *If they would not like to do so, the task manager will start from an empty task list in the new storage location.<br><br>
> *The default location is at the 'data/' folder of where the Never Forget.jar is located.<br>

Note: Storage location has to be declared relative to the position of Never Forget.jar. If users would like to navigate to a higher directory,
	  they can use '../'<br>
Note: This action will cause Never Forget to terminate. Users will have to re-launch their application again.<br>
Key in: `cd data/data/`<br>
		 
		 
#### Exiting the program : `exit`
Exits the program.<br>
Key in: `exit`
		 
		 
