# Workforce
Java program that optimizes workforce. The program optimizes the workforce capacity to the optimal value based on the number of available 
seniors and juniors per contract. The input data is of JSON format.
For example input data,
{"rooms":[35,21,17,28],"senior":10,"junior":6}
The program will optimize the input values and generate an outvalue of the format:
[ {senior: 3, junior: 1}, {senior: 1, junior: 2}, {senior: 2, junior: 0}, {senior: 1, junior: 3} ]

## Optimization Logic

The number of seniors and juniors is taken as the work rate for each of them. If a contractor has 10 seniors and 6 juniors then the
work rate of senior resources will be 10 rooms and work rate of junior resources will be 6 rooms. Optimization logic will iterate through 
the array of structures and keep adding juniors resource until the capacity matches or just exceeds the number of rooms. The team size per
contractor is limited based on the structure with the highest number of rooms for cleaning. If a team size has to exceed the limite in order
to match the efforts required. Then the iterator for the strucure will start from begning again and one more senior resource will be added 
to increaseing the performance.

Example,
{"rooms":[35,21,17,28],"senior":10,"junior":6}
In this contractors data, the highest number of rooms is 35, so the team size will be limited Ceil(35/10) = 4.
To match the efforts for 35 rooms. In iteration1 the number Juniors = 5 and seniors = 1 (5*6 + 1*10)to satisfy a the efforts for 35 rooms
but team size has exceeded 4 so add one more senior and iterate through the array. This iterator completes until the team size is satisfied
and the efforts required matched.
In this case of 35 rooms the optimized value will be {senior: 3, junior: 1}

## Executing the program
This program is built on maven so maven should be installed on the system
1. Clone the repository
2. Open command prompt or terminal and go to the directory in which the project has been cloned.
3. Go to the WorkforceOptimizer folder in the cloned directory
4. Run `mvn compile` & `mvn install`
5. Run `java -jar target/WorkOptimizer-1.jar`

## Test Cases

To execute the test cases separately run `mvn test`  
  
Test case 1 - calculateWorkforceLimitTest  
  
This function calculates the work force limit per team.
Example,
{"rooms":[35,21,17,28],"senior":10,"junior":6}
In this contractors data, the highest number of rooms is 35, so the team size will be limited Ceil(35/10) = 4.


Test Case 2 - RequiredEffortsForStructureTest  
  
This function estimates the efforts based on the number of seniors and juniors.
If Total number of seniors = 10 and Juniors = 6 and optimized number Juniors = 5 and optimized number of seniors = 1 then efforts  is equal
to (5*6 + 1*10) = 40

Test Case 3 - optimizeWorkforceTest  
  
This function optimizes the workforce capacity.
The optimizeWorkForce function returns an array of array indicating the optimized value (Seniors: <count> Juniors: <count>)
For example,
Input data : {"rooms":[35,21,17,28],"senior":10,"junior":6}
the function will return an array of array with values [[3,1],[1,3],[1,2],[2,0]]
