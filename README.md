# MTPizzle
Assignment for CS 246 Software Design and Development

File, network, and database operations can be very resource expensive, so you should not perform these kinds of operations on a main GUI thread. So, for this assignment, you will use multithreaded programming to be able to run these more complex, time consuming tasks on a background thread, without hanging the GUI.

Don't forget to collaborate with others and post questions and suggestions to the course website!

Instructions
Create an Android app that reads and writes a file and updates a list on the GUI and a progress bar as the file is read.

Create a new Android app with a blank activity.
Add your app's source to a new GitHub repository.
In Android Studio, this can be done in one step by clicking the "VCS" menu and selecting, "Import into Version Control" -> "Share Project on GitHub", which will prompt for GitHub information and perform the task.
Create a horizontal progress bar at the top, a ListView beneath it, and three buttons at the bottom:
Button 1 - Create
Button 2 - Load
Button 3 - Clear
Add an event handler for the Create button to do the following:
Create a new file in the internal storage area, called numbers.txt .
You can get a File object in the internal storage area like so: "File file = new File(context.getFilesDir(), filename);" (see this page for more information: http://developer.android.com/training/basics/data-storage/files.html )
In the file, print the numbers 1-10, one per line.
After writing each line, add a "Thread.sleep(250);" to pause for a quarter of a second to simulate a more difficult task.
Add an event handler for the Load button to do the following:
Load the file numbers.txt and read it line by line.
As you read each line, store the number in a list.
After reading each line, add a "Thread.sleep(250);" to simulate a more difficult task.
Then add this list your ListView (Hint: Use an ArrayAdapter)
Add an event handler for the Clear button that clears the list. (Hint: Clear the ArrayAdapter)
Ensure that you can write and then read the file and populate the ListView. And notice that the GUI hangs while it is reading and writing, because you are doing this on the main thread.
Change the file processes to happen on a background thread.
Start the reading and writing tasks on a background thread.
After reading/writing each line, update the progress bar to show the percentage of completion.
Keep in mind that you cannot manipulate the GUI from a background thread.
You are welcome to set up the background thread manually (using new Thread(), etc.) or you may also consider using an AsyncTask which requires you to set up a new derived class and override methods, but then handles the switching between the background and main threads for you.
