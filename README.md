# androidvilleapp
Androidville Android App

It works in conjunction with Androidville Server.

Good luck to us all.


Pending issues:
===============

- Bring up the soft keyboard.  Hit enter with a _hardware_ keyboard.  Try to dismiss the dialog.  The app crashes.

- If you edit a house, it will be updated with the "selected" value set to true.  So if you edit several houses, they will all appear as selected, when they aren't.  Fix this.

- Refactoring:
  - Access class instance variables through getters/setters.
  - Some verbosity when dealing with retrofit responses could be hidden (maybe through the use of lambdas?)
  - The main activity layout could be broken down.
  - Animation objects could be generalized.  Esp. for assigning custom/multiple "on end" events to them.
  - Method/variable/IDs naming needs more consistency.  Use camelCase or underscores consistently, for example.
  - DRY - don't repeat yourself.  For example, the house highlighting code is quite similar to the "on map touch" code. 
  - We're better off using a hash array or map instead of an array in order to store the houses.  That way, any given house could be easily found by its id.
  - Hardcoded values/magic numbers.  They should be stored in constants or value resource files.

- In fact, if the app crashes with the keyboard showing, the keyboard will remain.  Any way to tie the keyboard to the app, so it gets dismissed if the app crashes?  What's the behavior in other apps?

- Deleting houses should confirm before proceeding.

- Adding a house overwrites the old one.  There should be a confirmation dialog, or a refusal response.

- The "vacant" attribute needs to be handled.

- The "selected" attribute should not be stored in the server.

- Phone orientation change or activity reload handling.

- Security handling.

- A "selected" attribute is sometimes added to the database.  A real DB would have refused the add.  Prevent "selected" from being included in any api communication.

- Unit testing.
