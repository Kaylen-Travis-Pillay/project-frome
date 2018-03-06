# ***Project-Frome***

**Project-Frome** is an *Android* application that implements an online store. The store sells flowers and gifts to it's users in the local area. The major goal during the development of this application, is to build a platform and solution to sell goods on the Android platform. Therefore a lot of attention has been/will be paid on making the source code as reusable as possible. This is my first major Android application and as such I welcome any input anyone may have, be it on specific code or concept as a whole. 

My goal is to follow the [Google Design Guidelines](https://developer.android.com/design/index.html) during the development and give a detailed account of my successes and failures in this regard. This account will be take the form of a blog which should allow anyone to find solutions to problems that I have solved and possibly help me solve problems I face. 

# Solution Architecture (v1)

![Solution Architecture for project-frome](https://lh3.googleusercontent.com/26okLLBUsTiSWomjmUr4jEaK9sny5EAV9AOZFj8lM954LC4NqE0pMKJzRv6JWv_s_rQL_X7Y_jfd)

Above is a simple diagram illustrating my initial solution design for the project. I cover my decisions in detail in a blog post [TODO: insert blog post address here], however I'll briefly go over the major points here. The Android application is responsible only for the UI and accompanying logic, the goal is to put as little strain on the users device as possible. The majority of the logic is provided by a Node.js Express web server (*discussed more in my blog*). This web server serves the Android application through the HTTP protocol. MongoDB is used for data persistence and is accessed by the web server, through which the Android application access the DB.

**NOTE:** My choice of web server backend technologies and databases could be swapped out with your own choice. The underlying concept will remain the same.

Data is sent and received, between the Android application and the Web server, as JSON [[javaScript Object Notation]](https://www.json.org/).


----------
## Dedication
This major project is dedicated to my university professors 

<!--stackedit_data:
eyJoaXN0b3J5IjpbMzM3NTY1OTg0XX0=
-->