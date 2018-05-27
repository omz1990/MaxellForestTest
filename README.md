# MaxellForestTest - Omar Mujtaba

### Network:
* The app uses Retrofit and RxJava to fetch results from the API call
* All data models have been generated directly from http://www.jsonschema2pojo.org using the response object from the API call
* I have a code structure that I use to handle Errors and setting up everything for API calls. The RetrofitException, RxErrorHandlingCallAdapterFactory, OkHttpClientFactory, and ServiceFactory classes are directly ported from my own little code collection

### UI:
* The app uses 2 Fragments. VenuesListFragment is the one that fetches the results form the API and displays the results in the Recycler View
* A CardView is used to design the layout of each venue item in the Adapters (VenuesListAdapter)
* Glide is used to fetch the images and display them in the relevant image views from their URLs
* Fragments use interfaces to pass on the touch events to the SearchVenuesActivity which handles the touch events to open the Web View or display a toast that no URL is available for this venue
* Animations are defined in XML files inside the "anim" folder. They are simply added to the fragment transaction before committing via the setCustomAnimations() function

### Permission Checks and Location Manager
* The permissions are checked in the main activity before the fragments are loaded which uses the location
* Location Manager is initialized inside the VenuesListFragment, and provides a new location update every 20 seconds
* When the location is updated, the API call is made to get coffee spots in a 5km radius
* The fetched results are displayed in a RecyclerView with their address, icon, and sorted based on distance

### Orientation change and retaining data:
* For now the activity is locked in Portrait mode, by I have already done partial stuff as it can be seen in the SearchVenuesActivity to save the state of the data so it can be reused when orientation changes
* OnSaveInstance(true) has been enabled inside the fragments, however complete implementation could not be done in the given time for VenuesListFragment

### Video Demo:
* [Click here for demo](https://photos.app.goo.gl/6Hg0PlKsy8pihojf1)