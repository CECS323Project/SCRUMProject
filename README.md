# SCRUMProject
Introduction to database fundamentals group project
[Progess so far](https://github.com/TeamBearsharks/SCRUMProject/milestones) | [Issues to be claimed](https://github.com/TeamBearsharks/SCRUMProject/issues?q=is%3Aopen+is%3Aissue+label%3A%22help+wanted%22+no%3Aassignee) | [Contribution Graph](https://github.com/TeamBearsharks/SCRUMProject/network)
## Development plan
*  Create java project first
    * Should have all menus and options possible for the scope of the project
    * Be sure to keep separate classes for DB operations vs connections vs gui/text presentation
*  Develop backend to support front end
    *  We already have tables in place but they can still be modified if need be

## Collaboration strategy
*  Master branch is sacred
    * I will handle all merges and pull requests
*  You must checkout a new branch when devleoping a new "Feature" 
    * ```git checkout -b nameoffeature ```
    * A feature could be creating the jdbc connection class or creating the main menu

### Standard workflow
1.  ```git pull origin master ```
2.  If developing new feautre```git checkout -b nameoffeature``` If feature already exists ```git checkout nameoffeature```
3.  ***Make changes***
4.  ```git add -A``` ***Capital A***
5.  ```git commit -a -m "Changes you made" ``` <- the message in quotes acts as a checkpoint in case you need to revert to a certain point
6.  ```git push origin nameoffeature``` <- You can choose to push either when you commit [Prefered] or you can push when your code is **Production ready**
    * At this point your code is on the server but not on the master branch

### Moving to master
Go on to this repositories website and you will see that your feature branch is at the top with a green ***Compare and send pull request*** button, click that and use the @ mentions to mention my username (@sellnat77) in the pull request message so that I can review it and merge your changes into the master branch. ***if the option to automatically merge is available do not click it***
