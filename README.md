# Boruto-App
Boruto App with Boruto Server APi
This is the boruto app for the boruto server. It makes use of jetpack compose, clean architecture mvvm
paging 3 library to get data from the server in a paginated way, room persistence library and dagger hilt dependency injection.
On first opening there is a splash screen and then an onboarding screen that appears only the first time the user opens the app
shared preference is used to save the onboarding state when the user press finish on the last page of the onboarding screen.
And after that the user sees a shimmer effect while the data is being loaded from the server and it either shows the data or an error screen
according to the error encountered.
The app has 3 screens the main screen, the search screen and the details screen. The main screen shows the data on a lazy column along
with the name and some brief details and a rating star widget to show the power level of each character
and the details screen show the details of each character.
The search screen is used to search the characters
When the data is loaded it is saved in the room database before showing on the screen so the data show is from the database 
and the app only requests data from the database on request by the app and not just all the time
