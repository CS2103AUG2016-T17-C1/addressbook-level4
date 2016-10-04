# User Guide

* [Quick Start](#quick-start)
* [Features](#features)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   > Having any Java 8 version is not enough. <br>
   This app will not work with earlier versions of Java 8.
   
1. Download the latest `Never Forget.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for your To-Do App.
3. Double-click the file to start the app. The GUI should appear in a few seconds. 

<H1> Insert Image Here </H1>

4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window. 
5. Some example commands you can try:
   * **`list`** : lists today's tasks
   * **`add`**` Grocery Shopping d/1-Jan-2016 t/1800 v/Hougang NTUC` : 
     adds a task `Grocery Shopping` to Never Forget.
   * **`delete`**` 3` : deletes the 3rd task shown in the current list
   * **`exit`** : exits the app
6. Refer to the [Features](#features) section below for details of each command.<br>


## Features

> **Command Format**
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * The order of parameters is fixed.

#### Viewing help : `help`
Format: `help`

> Help is also shown if you enter an incorrect command e.g. `abcd`
 
#### Adding a task: `add`
Adds a task to Never Forget<br>
Format: `add TASK d/SCHEDULED_DATE t/SCHEDULED_TIME v/SCHEDULED_PLACE [t/TAG]...` 

> Tasks can have any number of tags (including 0)

Examples: 
* `add Grocery Shopping d/1-Jan-2016 t/1800 v/Hougang NTUC`
* `add Dinner with JC Friends d/7-Oct-2016 t/1930 v/Clementi Mall t/Budget Meal t/buddies`

#### Listing all of today's tasks : `list`
Shows a list of all tasks today in Never Forget.<br>
Format: `list`

#### Finding all tasks containing any keyword : `find`
Finds tasks which contain any of the given keywords.<br>
Format: `find KEYWORD [MORE_KEYWORDS]`

> * The search is case insensitive. e.g `walk` will match `Walk`
> * <s> The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans` </s>
> * Only task name is searched.
> * Only full words will be matched e.g. `Shop` will not match `Shopping`
> * Tasks matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Buy` will match `Buy milk`

Example: 
<s> * `find John`<br>
  Returns `John Doe` but not `john` </s>
* `find eat out`<br>
  Returns Any task with `eat` or `out`

#### Deleting a task : `delete`
Deletes the specified task from the To-Do App. Irreversible.<br>
Format: `delete INDEX`

> Deletes the task at the specified `INDEX`. 
  The index refers to the index number shown in the most recent listing.<br>
  The index **must be a positive integer** 1, 2, 3, ...

Examples: 
* `list`<br>
  `delete 2`<br>
  Deletes the 2nd task in today's to-do list.
* `find Dinner`<br> 
  `delete 1`<br>
  Deletes the 1st task in the results of the `find` command.

#### Select a task : `select`
Selects the task identified by the index number used in the last task listing.<br>
Format: `select INDEX`

><s> Selects the task and loads the Google search page the person at the specified `INDEX`. </s>
  The index refers to the index number shown in the most recent listing.<br>
  The index **must be a positive integer** 1, 2, 3, ...

Examples: 
* `list`<br>
  `select 2`<br>
  Selects the 2nd task in the to-do app.
* `find milk` <br> 
  `select 1`<br>
  Selects the 1st task in the results of the `find` command.

#### Checking an entry : `check`
Checks and marks an entry as completed. <br>
Format: `check INDEX`  

Examples:
* `list`
`check 1`
Checks the 1st task in the to-do app.
* `find bank`
`check 2`
Checks the 2nd task in the results of the `find` command.

#### Clearing all entries : `clear`
Clears all entries from the to-do app.<br>
Format: `clear`  

#### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`  

#### Saving the data 
Never Forget data are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with 
       the file that contains the data of your previous Address Book folder.
       
## Command Summary

Command | Format  
-------- | :-------- 
Add | `add TASK d/DD-MMM-YY t/HHMM v/PLACE [t/TAG]...`
Clear | `clear`
Delete | `delete INDEX`
Find | `find KEYWORD [MORE_KEYWORDS]`
List | `list`
Help | `help`
Select | `select INDEX`
Check | `check INDEX`
