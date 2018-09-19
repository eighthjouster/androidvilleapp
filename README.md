# androidvilleapp
Androidville Android App

It works in conjunction with Androidville Server.

Good luck to us all.


Pending work notes:
===================

Next up:
 - Add a house.
   - Select an empty spot in the map and record its address.  Highlight empty spot.
   - Selecting an already highlighted empty spot clear highlighting (addresses to -1).
   - Selecting a house should clear any empty spots highlighting (addresses to -1).
   - Don't proceed until all fields are validated.
     - For now:  Just the name.
 
 - Edit a house.
   - If the spot is not empty, don't add.  Edit instead (retrieve house's name and add it to the dialog.)
     - Add button should change caption to "Edit."
       - And back to "Add" when empty spot is selected or no highlighting is ocurring.
 
 - Delete a house.
 
 - Once the project is done and showcased:
   - Shutdown the linode server:
     - /home/p*****/conf/web

Pending issues:
===============

- Bring up the soft keyboard.  Hit enter with a _hardware_ keyboard.  Try to dismiss the dialog.  The app crashes.

- Refactoring:
  - Access class instance variables through getters/setters.
  - Some verbosity when dealing with retrofit responses could be hidden (maybe through the use of lambdas?)
  - The main activity layout could be broken down.
  - Animation objects could be generalized.  Esp. for assigning custom/multiple "on end" events to them.
  - Method/variable/IDs naming needs more consistency.  Use camelCase or underscores consistently, for example.
  - DRY - don't repeat yourself.  For example, the house highlighting code is quite similar to the "on map touch" code. 
  - We're better off using a hash array or map instead of an array in order to store the houses.  That way, any given house could be easily found by its id.

- In fact, if the app crashes with the keyboard showing, the keyboard will remain.  Any way to tie the keyboard to the app, so it gets dismissed if the app crashes?  What's the behavior in other apps?

- Adding a house overwrites the old one.  There should be a confirmation dialog, or a refusal response.

- The "vacant" attribute needs to be handled.

- A "selected" attribute is sometimes added to the database.  A real DB would have refused the add.  Prevent "selected" from being included in any api communication.

- Unit testing.
