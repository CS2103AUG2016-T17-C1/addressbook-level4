package guitests;

import org.junit.Test;

public class ChangeDirectoryCommandTest extends TaskManagerGuiTest {

    @Test
    public void ChangeDirectory() {

        // no space at the end of the directory
        commandBox.runCommand("cd src/test/data/XmlAddressBookStorageTest/data");
        assertChangeDirectoryCommandFailure();

        // illegal characters in directory
        commandBox.runCommand("cd src/test/data/XmlAddressBook?StorageTest/data");
        assertChangeDirectoryCommandFailure();

        // illegal characters in directory
        commandBox.runCommand("cd src/test/*data/XmlAddressBook?StorageTest/data");
        assertChangeDirectoryCommandFailure();
    }

    private void assertChangeDirectoryCommandFailure() {
        assertResultMessage("Illegal directory name given, please use a different directory name and try again");
    }
}
