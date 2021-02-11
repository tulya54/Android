MVP pattern overcomes these challenges of MVC and provides an easy way to structure the project codes. The reason why MVP is widely accepted is that it provides modularity, testability, and a more clean and maintainable codebase. It is composed of the following three components:

Model: Layer for storing data. It is responsible for handling the domain logic(real-world business rules) and communication with the database and network layers. <br/> <br/>
View: UI(User Interface) layer. It provides the visualization of the data and keep a track of the user’s action in order to notify the Presenter. <br/> <br/>
Presenter: Fetch the data from the model and applies the UI logic to decide what to display. It manages the state of the View and takes actions according to the user’s input notification from the View. <br/> <br/>

![ScreenShot](https://github.com/tulya54/Android/blob/master/MVP/MVP.png)
