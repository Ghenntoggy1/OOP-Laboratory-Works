# Lab 1: University Management System Documentation
**Student:** Gusev Roman

**Group:** FAF-222

<a name="tasks"></a>
## Tasks
### General Task
> Design a Student Management application for Technical University of Moldova.

### Limitations
>* No third party libraries are allowed, you can use only the libraries that are
  available by default.
>* You are free to create as many classes as you need to achieve a well structured
   working system.
>* No limitation of concepts: You can create interface classes, use concepts like
   Polymorphism, Inheritance, Abstraction if you deem necessary. It is not a
   must!

### Base Tasks
>Build a program loop, an interactive command line for the TUM Board to be able to do the
next operations:
> 
> 
>* Faculty operations:
>  * Create and assign a student to a faculty.
>  * Graduate a student from a faculty.
>  * Display current enrolled students (Graduates would be ignored).
>  * Display graduates (Currently enrolled students would be ignored).
>  * Tell or not if a student belongs to this faculty.
>
> 
>* General operations:<a name="general"></a>
   >  * Create a new faculty.
>  * Search what faculty a student belongs to by a unique identifier (for example by email
      or a unique ID).
>  * Display University faculties.
>  * Display all faculties belonging to a field. (Ex. FOOD_TECHNOLOGY)

### Improved
>Design a saving and loading system for the Student Management system.

### Working System
>* Design an operation Logging System.
>
>* Add extra operations:
   >  * Batch enrollment operation for students via a text file.
>  * Batch graduation operation for students via text file.
>  * Validate inputs with meaningful error statements. **_(Can’t graduate student:
     ivan@isa.utm.md (student not present), Operation <operation> is not a
     valid operation, Operation requires extra data etc.)_**

## Used Tools
* The entire code in written in Java programming language in [IntelliJ IDEA Environment](https://www.jetbrains.com/idea/)
* In order to track the evolution of the code, I used [git](https://git-scm.com/) and [gitHub](https://github.com/), I worked on different branches for different tasks, that, in the end, I merged with main and deleted afterward

## Getting the code

You can download a copy of all the files in this repository by cloning the
[git](https://git-scm.com/) repository:

    git clone https://github.com/Ghenntoggy1/OOP-Laboratory-Works.git

## Functionality
I have implemented all the required functionalities in the [Tasks Section](#tasks)

## Usage
> In order to start the application, start 
> ``` java
> UniversityManagementSystem.java class (it holds the main function)
> ```

> You will get to introduce a username
> ```console
> | INPUT YOUR USERNAME: |
> ```
> This username will be used in Logger, it will be display who was the person that interacted with the System

> Eventually, you will get access to the Start Menu 
> ```console
> +---------------------------------------------+
> | WELCOME TO TUM's STUDENT MANAGEMENT SYSTEM! |
> | WHAT DO YOU WANT TO DO?                     |
> +---------------------------------------------+
> | g - GENERAL OPERATIONS                      |
> | f - FACULTY OPERATIONS                      |
> | bes - BATCH ENROLLMENT OPERATIONS STUDENTS  |
> | bgs - BATCH GRADUATION OPERATIONS STUDENTS  |
> | h - HELP                                    |
> +---------------------------------------------+
> | q - QUIT PROGRAM                            |
> +---------------------------------------------+
> ```
> And will be asked to input a certain choice
> ```console
> | INPUT CHOICE:                               |
> ```

> There you can choose to access General Operation Menu that holds the [General Tasks](#)
> ```console
> | GENERAL OPERATIONS:                                                                              |
> +--------------------------------------------------------------------------------------------------+
> | nf - CREATE A NEW FACULTY                                                                        |
> | nf/<facultyName>/<facultyAbbreviation>/<studyField> - CREATE A NEW FACULTY (FAST COMMAND)        |
> | sf - SEARCH FACULTY A STUDENT BELONGS TO                                                         |
> | sf/id/<idValue> - SEARCH FACULTY A STUDENT BELONGS TO BY ID (FAST COMMAND)                       |
> | sf/em/<email> - SEARCH FACULTY A STUDENT BELONGS TO BY EMAIL (FAST COMMAND)                      |
> | df - DISPLAY UNIVERSITY FACULTIES                                                                |
> | dff - DISPLAY FACULTIES BELONGING TO A FIELD                                                     |
> | dff/<studyField> - DISPLAY FACULTIES BELONGING TO A FIELD (FAST COMMAND)                         |
> | h - HELP MENU                                                                                    |
> +--------------------------------------------------------------------------------------------------+
> | q - QUIT MENU                                                                                    |
> +--------------------------------------------------------------------------------------------------+
> ```
> As you can notice, there are several functions that are listed in the [General Tasks](#general-task)
> and additionally I added the handling for fast commands.

> or you can choose to access Faculty Operations Menu that holds the [Faculty Tasks](#general-task)
> ```console
> | FACULTY OPERATIONS:                                                                                                                      |
> +------------------------------------------------------------------------------------------------------------------------------------------+
> | cs - CREATE AND ASSIGN A NEW STUDENT                                                                                                     |
> | cs/<facultyAbbreviation>/<firstName>/<lastName>/<email>/<enrollmentDate>/<dateOfBirth> - CREATE AND ASSIGN A NEW STUDENT (FAST COMMAND)  |
> | gs - GRADUATE STUDENT                                                                                                                    |
> | das - DISPLAY ALL STUDENTS                                                                                                               |
> | des - DISPLAY ENROLLED STUDENTS                                                                                                          |
> | dgs - DISPLAY GRADUATED STUDENTS                                                                                                         |
> | csf - CHECK STUDENT                                                                                                                      |
> | h - HELP MENU                                                                                                                            |
> +------------------------------------------------------------------------------------------------------------------------------------------+
> | q - QUIT MENU                                                                                                                            |
> +------------------------------------------------------------------------------------------------------------------------------------------+
> ```
>
> Type in the needed option, and follow the instructions on the screen.

> For the Batch functionality, I provided a log.txt file that is empty and a folder DataBases, that will
> hold the information about faculties, students and store them. Also, additionally, I created
> the function to reset the databases, that will empty faculty text file and will delete all related 
> to a specific faculty information.
> 
> In order to batch, be careful, batch graduation is based on the ID of the student
> whilst batch enrollment will load all the data necessary for the Student instantiation

> Batch Enrollment text file looks like this
> ```text
> John,Doe,johndoe@example.com,01-01-2023,15-05-2000,true,clc
> Alice,Smith,alicesmith@example.com,15-02-2023,20-04-2001,true,anm
> Bob,Johnson,bobjohnson@example.com,10-03-2023,12-07-2002,false,mcr
> John,Alex,alex@gmail.com,10-02-2022,02-02-2004,true,art
> Gusev,Roman,roman@mail.ru,29-05-2021,01-02-2004,true,gh
> ```

> Batch Graduation text file looks like this
> ```text
> 1
> 3
> 5
> 7
> 10
> 2
> ```

> There are a lot of handling operations that will not give the possibility to crash the program

> Every operation in the system is registered in the log, errors are included too.
> ```text
> 2023/10/04 14:52:08  :  USER_LOGGED  :  ghenntoggy  :  LOG_OPERATION  :  reset_logs  :  emptied logs file
> 2023/10/04 19:55:13  :  USER_LOGGED  :  Ghenntoggy  :  INITIATION  : entry  :  app initialized
> ```

> Everytime you quit the program, it will ask you to save or reset the database
> ```console
> +------------------------------------+
> | DO YOU WANT TO SAVE OR RESET DATA? |
> | 1. SAVE                            |
> | 2. RESET                           |
> +------------------------------------+
> ```
> 
> and to reset or not the log text file
> ```console
> +------------------------------------+
> | DO YOU WANT TO RESET LOG FILE?     |
> | 1. YES                             |
> | 2. NO                              |
> +------------------------------------+
> ```
> 
> Based on your choice, as I described above, will be emptied the database folder or saved the last state of the university
> in those txt files. Talking about the Logger, if you reset the log file, it will display only the moment when it was reset