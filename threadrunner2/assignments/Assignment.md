# ThreadRunner 2

## Distributed Software Systems 2021 Exercise 1 Assignment

In the first assignment we practice creating threads and assigning tasks for them. In addition we will study how the execution time varies between different types of tasks. The assignment will be done using the provided project template and your task is to implement the missing parts of the program. ThreadRunner2 depends on JavaFX libraries to provide graphical user interface, but completing the exercise does not require understanding on JavaFX.

The project template is a Maven project (<https://maven.apache.org/>). But as the Maven configuration has already been made, it is not necessary to understand how to configure Maven. The completed assignment should be able to compile and run using Maven command line interface though.

## Application user interface

![Main window](/assignments/images/UI_at_beginning_lowres.jpg "Interface at program startup.")


The program user interface is divided into two main parts. The left side contains controls for creating workload and the right side contains options on threads themselves.


![Main window](/assignments/images/UI_running_lowres.jpg "Interface during thread execution.")

The list view on the left displays the status of the work queue. The *Type* and *Load* fields specify the type of the workload and *Status* tells the current state of the work. Status can be one of the following: *waiting*, *queued*, *calculating* or *done*. Tasks that are currently in the *waiting* state are in the work queue and no thread has been assigned to work on that specific task. *Queued* tasks have been assigned to a thread but they are not being calculated yet (as a single thread can be assigned with multiple tasks at once). Tasks that are in *calculating* state, are being calculated at that moment. There can only be as many tasks at a time in *calumniated* state as there are threads being created. Tasks that have reached *Done* state, are complete.

Shortly put, the status of a single task proceeds as following: waiting -> queued -> calculating -> done. The shown status in UI is based on the reporting done by the working threads. The methods for reporting have are provided by the project template along with a couple of examples.

The list on the right shows the status of the threads. This list cannot be cleared. Each press on the "Execute" button adds more lines. *Thread id* is the hash value of the thread object. It is there on the list for identification purposes only. *Thread type* tells us the type of the thread. *Status* is either *created*, *running* or *ended*. The status reporting to the UI components works the same as with the tasks.

The thread status progresses as follows: created -> running -> ended.


### Menus

#### Job queue

- Task type

   This sets the type of the task. There are two options: DummyWork and ForLoop. The workload on both of these is mostly artificial small delay.
   
- Load

   Sets the workload (duration) of the task. There are four options: Light, medium, hard and mixed. The last of these is a mixture of the first three.
   
- Number of jobs to generate

   Number of jobs to be generated
   
- Generate button

   Create jobs according to the settings. The created jobs appear in the job queue list. More jobs can be generated even if the calculation is still in progress.
    
- Clear list button

   Clears the job queue. You can clear the job queue even while the threads are still running. The queuing solution is thread safe if used correctly.

#### Theads working
   
- Number of threads to run
   
   Sets the maximum amount of threads to be used. The maximum available value depends on the computer you are using (the number of CPU cores). Note that the number of processor cores in the virtual machine is set to two by default. If your computer's processor has got more cores, you may increase the value in the properties of your virtual machine.

- Block size
   
   Each thread retrieves this amount of jobs from the work queue at once

- Thread type

   Select the type of threads to be started. There are three options in the menu: Single, Thread, Executor. Only one of these, Single, works by default. The other two types need to be implemented by your group.

- Execute button

   Starts execution with the given values. A new batch of threads can be started even if the old ones are still running. All running threads share the same work queue.

---


## Description of the program structure



### App.java and fi.utu.tech.threadrunner2.ui package

`App.java` class is the main class that contains the `main` method. It also includes the methods required to start the jobs and to measure the execution times. The `UI` package contains classes related to the operation of the graphical user interface.

These classes should not be modified nor are they used directly in the assignment.


### fi.utu.tech.threadrunner2.mediator

This package handles the communication between the different parts of the program. It includes methods for obtaining information from the user interface and methods to report the current state of the threads and tasks.

The `ControlSet` class provides information about the number of threads to be created and the number of tasks to be assigned to threads at once (block size). `Mediator` class is used to report the state of the tasks and threads back to the UI. The Mediator interface is thread-safe. I.e. It can be used concurrently from different threads without any consideration to thread safety.

These classes should not be modified in the exercise, but the services provided by the ControlSet class and the Mediator interface will be used in the assignment.


### fi.utu.tech.threadrunner2.works

Task list generation. The package contains classes that generate different types of task lists as well as workloads.

These classes should not be modified nor are they used directly in the assignment.
 

### fi.utu.tech.threadrunner2.assignment

The `fi.utu.tech.threadrunner2.assignment` package contains the components responsible for distributing the workload to the different threads.

This package is the most important one regarding to the completion of the assignment. This package includes the classes `Task1UsingThreadDistributor` and `Task2UsingExecutorDistributor` that are used to implement the work distribution in exercises 1 and 2 respectively.

This package should also contain all the additional classes that are required to complete the assignment and get a correctly working application.

## Exercise 1: Using the Thread class

The first exercise will be implemented using either `Thread` class or `Runnable` interface. Using `ExecutorService` is prohibited. 

The exercise is done mainly by editing the `Task1UsingThreadDistributor` class. The UI will call the `execute` method implemented in this class, once the *Execute* button has been pressed and the *Thread* option is selected in the *Thread type* drop-down menu.

To complete the exercise, the class should be able to start the amount of threads selected in the user interface. Each thread should fetch a new set of tasks as log as there are tasks left in the task queue. If the task is empty, the thread will be terminated.

The amount of tasks that can be acquired by a single thread at once is defined by the Block size (in the UI). If there is less work available in the task queue, the thread will be only given the last tasks.

The tasks are internally held in `BlockingQueue<Work>` structure. `Work` objects contain `work` method that will execute the work once called.

### Exercise 2: ExecutorService

In the second exercise the tasks will be run using `ExecutorService`. Running tasks without `ExercutorService` is prohibited.

The exercise should be done in `Task2UsingExecutorDistributor` class. The UI will call the `execute` method implemented in this class, once the *Execute* button has been pressed and the *Executor* option is selected in the *Thread type* drop-down menu.

Otherwise, the exercise is similar to the exercise 1.

### Exercise 3: 

Answer to the following questions by giving short explanations. You may use the ThreadRunner application to test your hypotheses. Use the `answers.md` file on the root of the project to save your answers.

1. Familiarize yourselves with the ThreadRunner2 structure. How does the execution advance? You do not have to describe the UI classes.
2. In theory it would be possible to run more tasks with more threads than there are cores in the computer running the application. What would happen in this case? How would this affect the computation times? (We will assume that the task is CPU heavy)
3. MediatorService contains an object named workQueue that contains all the jobs being queued. The UI thread will add new jobs to the queue while multiple worker threads read the queue. Explain why workingQueue works here as it is. Also, explain why ArrayList would not.
