# Call Center

## Problem Statement
Imagine you have a call center with three levels of employees: respondent, manager, and director. An incoming telephone call must be first allocated to a respondent who is free. If the respondent can't handle the call, he or she must escalate the call to a manager. If the maanger is not free or not able to handle it, then the call should be escalated to a director. Design the classes and data structures for this problem. Implement a method `dispatchCall()` which assigns a call to the first avaialble employee.

## Assumptions
* There will be finite set of responders, managers and directors for the entire duration
* The call escalation will happen when a particular employee wants to escalate to next level, or if there are no employees in the current level
* There is no hierarchical relation between the different employees. Meaning, a responder will not escalate to "his/her" manager. Any manager in the group can take the escalated call
* The calls that cannot be answered will be put in a queue. Queue size is unlimited
* The queued calls will not be dropped automatically based on time limit
* The queue wait time will not be notified to the queued caller
* The call duration is not captured
* There is no auditing of number of calls attended by which responder, who made the calls etc.

## Class Diagram
![Call Center - Class Diagram](class-diagrams/Call%20Center%20-%20Class%20Diagram.jpeg)




