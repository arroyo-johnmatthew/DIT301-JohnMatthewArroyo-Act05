💬 How did you implement CRUD using SQLite?
I used a custom kotlin class called NoteDao. This is the code that handles the main database operations like adding, reading, updating, and deleting a note.
It connects to another code which is called NoteDatabaseHelper, which manages the SQLite database.

💬 What challenges did you face in maintaining data persistence
The challenge I faced was the initial UI of the activity always bringing me back to my homescreen whenever I touch the "+" symbol. There I realized that I had to make some configuration
adjustments to the Android Manifests. It wasnt able to read my codes even tho the logic is good and without fault.

💬 How could you improve performance or UI design in future versions?
Add more confirmation modals for the creation and updating of notes for more user interactivity. It is also nice that I place a background text on the initial screen because it doesn't print 
anything other than the "+" symbol.

