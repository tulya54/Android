Model: This holds the data of the application. It cannot directly talk to the View. Generally, it’s recommended to expose the data to the ViewModel through Observables. <br/> <br/>
View: It represents the UI of the application devoid of any Application Logic. It observes the ViewModel. <br/> <br/>
ViewModel: It acts as a link between the Model and the View. It’s responsible for transforming the data from the Model. It provides data streams to the View. It also uses hooks or callbacks to update the View. It’ll ask for the data from the Model. <br/> <br/>
![ScreenShot](https://github.com/tulya54/Android/blob/master/MVVM/mvvm.png)
