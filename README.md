# Article-NY-Times
As per the doc I have created a new app name "NY Times Assignment" on "https://developer.nytimes.com/get-started" 
with sample-key :'66dvslQ4AML4bZRQLDrmbIRuf2vF0czm'.

In this Assignment I have implemented list of NY-Times articles and on tap of aricle row, its detail screen will be displayed.

App will load one day articles on app launched and when user will scroll down to last item of one day articles list it will then load weekly articles list and when scroll down to end, app will load monthly article.

App is getting only 20 article at the moment for each "Daily","Weekly" and "Monthly" url but assumption have been made that weekly articles list will contain 'Today + few more articles' of last 6 days and Monthly articles list will have 'This week data + past 23 data'.

- Architecture : MVVM 
- Webservice consumed by :Retrofit
- Fragment Implemnetation: Navigation Graph
