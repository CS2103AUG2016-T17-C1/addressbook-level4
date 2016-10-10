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
   > <img src="images/UI.png" width="600">



## Features

> **Command Format**
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * The order of parameters is fixed.



> <img src="images/add.png" width="600">

4. Type a action in the command box and press <kbd>Enter</kbd> or click on the "+" icon to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> or clicking on the help button will open the help window.
5. Some example commands you can try:
   * **`list`** : lists all tasks
   * **`add`**` Buy a cup of coffee` : Add a task to buy a cup of coffee without setting any deadlines for it to the to-do list.

   * **`list`** : lists today's tasks
   * **`add`**` Grocery Shopping d/1-Jan-2016 t/1800 v/Hougang NTUC` :
     adds a task `Grocery Shopping` to Never Forget.

   * **`delete`**` 3` : deletes the 3rd task shown in the current list
   * **`exit`** : exits the app
6. Refer to the [Features](#features) section below for details of each command.<br>


#### Viewing help : `help`
Format: `help`

> Help is also shown if you enter an incorrect command e.g. `abcd`
<<<<<<< HEAD

#### Finding all tasks containing any keyword in their name: `find`
Finds tasks whose names contain any of the given keywords.<br>
Format: `find KEYWORD [MORE_KEYWORDS]`

> * The search is not case sensitive. e.g `hans` will match `Hans`
> * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
> * Only the name is searched.
> * Non full words will still be matched e.g. `Han` WILL match `Hans`
> * tasks matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Hans` will match `Hans Bo`


Examples:
* `find meeting`<br>
  Returns `meetings` and `Meetings` but not `meet`
* `find coffe complete homework`<br>
  Returns Any tasks containing names `coffe`, `complete`, or `homework`


#### Adding a task: `add`
Adds a task to Never Forget<br>
Format: `add TASK d/SCHEDULED_DATE t/SCHEDULED_TIME v/SCHEDULED_PLACE [t/TAG]...`

> Tasks can have any number of tags (including 0)

Examples:
* `add Grocery Shopping d/1-Jan-2016 t/1800 v/Hougang NTUC`
* `add Dinner with JC Friends d/7-Oct-2016 t/1930 v/Clementi Mall t/Budget Meal t/buddies`



#### Deleting a task : `delete`
Deletes the specified task from the to-do list. Can be reversed with `undo` command.<br>
Format: `delete INDEX`

> Deletes the task at the specified `INDEX`.
  The index refers to the index number shown in the most recent listing.<br>
  The index **must be a positive integer** 1, 2, 3, ...


#### Completing a task : `complete`
Strikes off the specified task from the to-do list. Reversible.<br>
Format: `complete INDEX`



#### Listing all of today's tasks : `list`
Shows a list of all tasks today in Never Forget.<br>
Format: `list`

#### Selecting tasks : `select`
Examples:
* `list`<br>
  `select 2`<br>
  Selects the 2nd task in the to-do list.
* `find Betsy` <br>
  `select 1`<br>
  Selects the 1st task in the results of the `find` command.


#### Clearing all entries : `clear`
Clears all entries from the to-do list.<br>
Format: `clear`


#### Checking an entry : `check`
Checks and marks an entry as completed. <br>
Format: `check INDEX`

#### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`


#### Saving the data
Never Forget data are saved in the hard disk automatically after any command that changes the data.<br>

There is no need to save manually.


#### Sorting the displayed task list : `click on either Home(which displays everything) Today, Next 7 Days or Month`
Displays the task listing for that desired time frame<br>


> Displays the task listing for that desired time frame
  Tasks with no deadlines are only displayed in the Home listing where all tasks are displayed<br>
  User must select any of the tabs, HOME, TODAY, Next 7 Days or Month


## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with
       the file that contains the data of your previous to-do list folder.

## Command Summary


Command | Format
-------- | :--------
Add | `add TASK d/DD-MMM-YY t/HHMM v/PLACE [t/TAG]...`
Clear | `clear`
Delete | `delete INDEX`
Complete | `strikethrough the task at the INDEX`
Find | `find KEYWORD [MORE_KEYWORDS]`
List | `list`
Help | `help`
Select | `select INDEX`
Check | `check INDEX`
